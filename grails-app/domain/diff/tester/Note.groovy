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
}
