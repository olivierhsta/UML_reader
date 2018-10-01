package models;

import java.util.ArrayList;

public class RelationDeclaration extends ModelDeclaration {

	private ArrayList<String[]> roles = new ArrayList<>();
	
	private String strLines = "";
	
	public RelationDeclaration(String name) {
		super(name);
		
	}
	
	public void apply(ArrayList<Class> classList) {
		for (String[] role : this.roles) {
			
			for (Class ownerClass : classList) {
				if (ownerClass.name.equals(role[0])) {
					ownerClass.addRelation(new Relation(this.name, this.strLines));
				}
			}
			
		}
	}

	@Override
	public void parseLines() {
		String str = "";
		for (String line : this.lines) {
			str += line;
		}
		this.strLines = str;
		str = str.replace("ROLES", "").replace(";", "").trim();
		
		String[] strRelations = str.split(",");
		
		for (String strRelation : strRelations) {
			strRelation = strRelation.replace("CLASS", "").trim();
			
			String[] params = strRelation.split(" ");
			
			this.roles.add(params);
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
