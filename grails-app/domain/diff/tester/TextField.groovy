package diff.tester

class TextField {
	
	String 	content
	int		contentVersion
	

    static constraints = {
		content(blank:false)
		contentVersion(nullable:false)
    }

    String toString() {
    	"$content (version $contentVersion)"
    }
}
