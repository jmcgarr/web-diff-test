package diff.tester

class Note {

	String title
	SortedSet textVersions

    static constraints = {
    	title(blank: false)
    }

    static hasMany = [textVersions: TextField]

    String toString() {
    	"$title (${textVersions.size()} versions)"
    }

    void addVersion(String version) {
    	textVersions.add(new TextField(content: version, contentVersion: getNextNumber()))
    }

    int getNextNumber() {
    	if (textVersions == null) {
    		1
    	} else {
    		textVersions.last().contentVersion + 1
    	}
    }
}
