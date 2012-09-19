<%@ page import="diff.tester.Note" %>



<div class="fieldcontain ${hasErrors(bean: noteInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="note.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${noteInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noteInstance, field: 'textContent', 'error')} ">
	<label for="textContent">
		<g:message code="note.textContent.label" default="Text Content" />
		
	</label>
	<!--g:textArea name="textContent" required="" value="${noteInstance?.textVersions?.get(0)?.content}" /-->

	<textarea name="textContent" rows="10" cols="50">${noteInstance?.textVersions?.get(0)?.content}</textarea>
	
</div>

