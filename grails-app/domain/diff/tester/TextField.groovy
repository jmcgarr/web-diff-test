package diff.tester

class TextField implements Comparable {
	
	String 	content
	int		contentVersion
	
    static constraints = {
		content(blank:false)
		contentVersion(nullable:false)
    }

    String toString() {
    	"version $contentVersion"
    }

    int compareTo(obj) {
    	contentVersion.compareTo(obj.contentVersion)
    }
}
