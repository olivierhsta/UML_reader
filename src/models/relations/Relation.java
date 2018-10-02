package models.relations;

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

	
//	public void remove() {
//		
//	}

}
