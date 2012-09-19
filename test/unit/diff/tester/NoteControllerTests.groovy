package diff.tester



import org.junit.*
import grails.test.mixin.*

@TestFor(NoteController)
@Mock(Note)
class NoteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/note/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.noteInstanceList.size() == 0
        assert model.noteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.noteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.noteInstance != null
        assert view == '/note/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/note/show/1'
        assert controller.flash.message != null
        assert Note.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/note/list'

        populateValidParams(params)
        def note = new Note(params)

        assert note.save() != null

        params.id = note.id

        def model = controller.show()

        assert model.noteInstance == note
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/note/list'

        populateValidParams(params)
        def note = new Note(params)

        assert note.save() != null

        params.id = note.id

        def model = controller.edit()

        assert model.noteInstance == note
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/note/list'

        response.reset()

        populateValidParams(params)
        def note = new Note(params)

        assert note.save() != null

        // test invalid parameters in update
        params.id = note.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/note/edit"
        assert model.noteInstance != null

        note.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/note/show/$note.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        note.clearErrors()

        populateValidParams(params)
        params.id = note.id
        params.version = -1
        controller.update()

        assert view == "/note/edit"
        assert model.noteInstance != null
        assert model.noteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/note/list'

        response.reset()

        populateValidParams(params)
        def note = new Note(params)

        assert note.save() != null
        assert Note.count() == 1

        params.id = note.id

        controller.delete()

        assert Note.count() == 0
        assert Note.get(note.id) == null
        assert response.redirectedUrl == '/note/list'
    }
}
