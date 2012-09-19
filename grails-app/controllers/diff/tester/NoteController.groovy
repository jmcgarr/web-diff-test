package diff.tester

import org.springframework.dao.DataIntegrityViolationException

class NoteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def diffService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [noteInstanceList: Note.list(params), noteInstanceTotal: Note.count()]
    }

    def create() {
        [noteInstance: new Note(params)]
    }

    def save() {
        def noteInstance = new Note(params).addToTextVersions(new TextField(content: params.textContent, contentVersion: 1))
        if (!noteInstance.save(flush: true)) {
            render(view: "create", model: [noteInstance: noteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'note.label', default: 'Note'), noteInstance.id])
        redirect(action: "show", id: noteInstance.id)
    }

    def show(Long id) {
        def noteInstance = Note.get(id)
        def diff = ""
        if (!noteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'note.label', default: 'Note'), id])
            redirect(action: "list")
            return
        }
        if (noteInstance.textVersions.size() > 1) {
        	def original = noteInstance.textVersions.toArray()[noteInstance.textVersions.size()-2].content
        	def replacement = noteInstance.textVersions.last().content
        	diff = diffService.htmlDiff(original, replacement)
        }

        [noteInstance: noteInstance, textDiff: diff]
    }

    def edit(Long id) {
        def noteInstance = Note.get(id)
        if (!noteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'note.label', default: 'Note'), id])
            redirect(action: "list")
            return
        }

        [noteInstance: noteInstance]
    }

    def update(Long id, Long version) {
        def noteInstance = Note.get(id)
        if (!noteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'note.label', default: 'Note'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (noteInstance.version > version) {
                noteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'note.label', default: 'Note')] as Object[],
                          "Another user has updated this Note while you were editing")
                render(view: "edit", model: [noteInstance: noteInstance])
                return
            }
        }

        
        noteInstance.properties['title'] = params

        // Add a new text version if they are different.  Should move this to a service or separate method.
        if (!params.textContent.equals(noteInstance.textVersions?.last().content)) {
	        def nextNum = noteInstance.textVersions?.last()?.contentVersion + 1
	        noteInstance.addToTextVersions(new TextField(content: params.textContent, contentVersion: nextNum))
	    }

        if (!noteInstance.save(flush: true)) {
            render(view: "edit", model: [noteInstance: noteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'note.label', default: 'Note'), noteInstance.id])
        redirect(action: "show", id: noteInstance.id)
    }

    def delete(Long id) {
        def noteInstance = Note.get(id)
        if (!noteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'note.label', default: 'Note'), id])
            redirect(action: "list")
            return
        }

        try {
            noteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'note.label', default: 'Note'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'note.label', default: 'Note'), id])
            redirect(action: "show", id: id)
        }
    }
}
