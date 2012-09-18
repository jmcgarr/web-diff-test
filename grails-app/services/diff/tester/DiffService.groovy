package diff.tester

import name.fraser.neil.plaintext.diff_match_patch
import name.fraser.neil.plaintext.diff_match_patch.Diff
import name.fraser.neil.plaintext.diff_match_patch.Operation

class DiffService {

	def handler = new diff_match_patch()

    def htmlDiff(String original, String replacement) {
    	def result = new String()
    	LinkedList<Diff> diffs = handler.diff_main(original, replacement)
    	diffs.each { it
    		if (it.operation == Operation.DELETE) {
    			result += "<del>" + it.text + "</del>"
    		}
    		else if (it.operation == Operation.INSERT) {
    			result += "<b>" + it.text + "</b>"
    		}
    		else {
    			result += it.text
    		}
    	}
    	result
    }
}
