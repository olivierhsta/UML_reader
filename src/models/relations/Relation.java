package models.relations;

/**
 * Class contained in each classes, with the information to be displayed
 * 
 * @author franc
 *
 */
public class Relation {

	private String name;
	private String details;


	public Relation(String name, String details) {
		super();
		this.name = name;
		this.details = details;
	}

	public String getName() {
		return this.name;
	}

	public String getDetails(){
		return this.details;
	}
}
