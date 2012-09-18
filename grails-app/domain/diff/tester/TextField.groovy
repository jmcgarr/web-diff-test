package diff.tester

class TextField {
	
	String content
	int version
	

    static constraints = {
		
    }

    String toString() {
    	"$content (version $version)"
    }
}
