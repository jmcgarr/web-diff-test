package diff.tester

import name.fraser.neil.plaintext.diff_match_patch
import name.fraser.neil.plaintext.diff_match_patch.Diff
import name.fraser.neil.plaintext.diff_match_patch.Operation

class DiffService {


    def htmlDiff(String original, String replacement) {
    	String result = new String();
    	def handler = new diff_match_patch();
    	LinkedList<Diff> diffs = handler.diff_main(original, replacement)
    	diffs.each { it
    		if (it.operation == Operation.DELETE) {
    			result = result + "<del>"
    			result = result + it.text
    			result = result + "</del>"
    		}
    		else if (it.operation == Operation.INSERT) {
    			result = result + "<b>"
    			result = result + it.text
    			result = result + "</b>"
    		}
    		else {
    			result = result + it.text
    		}
    	}
    	println diffs
    	println result
    	result
    }
}
