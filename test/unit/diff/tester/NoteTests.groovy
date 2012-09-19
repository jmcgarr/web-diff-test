package diff.tester



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Note)
class NoteTests {

    @Test void testToString () {
       // given
       def versions = [new TextField(content: "This is the first Note", contentVersion: 1), 
       					new TextField(content: "this is the second Note", contentVersion: 2)]
       def note = new Note(title: "this is a note", textVersions: versions)

       // when
       def actual = note.toString()

       // then
       assertEquals "this is a note (2 versions)", actual
    }
}
