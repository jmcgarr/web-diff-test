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

    @Test void getnextNumber_whenNew () {
      // given
      def note = new Note(title: "this is a note")

      // when
      def next = note.getNextNumber()

      // then
      assertEquals 1, next
    }

    @Test void getNexuNumber_whenVersionsExist () {
      // given
      def versions = [new TextField(content: "This is the first Note", contentVersion: 1), 
                new TextField(content: "this is the second Note", contentVersion: 2)]
      def note = new Note(title: "this is a note", textVersions: versions)  

      // when
      def next = note.getNextNumber()

      // then
      assertEquals 3, next
    }

    @Test void addVersions () {
      // given
      def versions = [new TextField(content: "This is the first Note", contentVersion: 1), 
                new TextField(content: "this is the second Note", contentVersion: 2)]
      def note = new Note(title: "this is a note", textVersions: versions)  

      // when
      note.addVersion("this is the third note")

      // then
      assertEquals 3, note.textVersions.last().contentVersion
    }
}
