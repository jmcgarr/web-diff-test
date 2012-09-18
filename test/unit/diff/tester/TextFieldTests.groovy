package diff.tester



import grails.test.mixin.*
import org.junit.*

@TestFor(TextField)
class TextFieldTests {

	@Test void testToString() {
    	// given
    	def field = new TextField(content:'this is a string', contentVersion: 1)

    	// when
    	def log = field.toString()

    	// then
        assertEquals 'this is a string (version 1)', log
    }
}
