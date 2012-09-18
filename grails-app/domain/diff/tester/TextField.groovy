package diff.tester

class TextField {
	
	String content
	int contentVersion
	

    static constraints = {
		
    }

    String toString() {
    	"$content (version $contentVersion)"
    }
}
