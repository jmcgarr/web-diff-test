package diff.tester



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DiffService)
class DiffServiceTests {

    @Test void basicDiffWorks() {
        // given
        String first = "This is the first String"
        String second = "This is the second String"
        def service = new DiffService()

        // when
        String diff = service.htmlDiff(first, second)

        // then
        assertEquals "This is the <del>fir</del>s<del>t</del><b>econd</b> String", diff
    }
}
