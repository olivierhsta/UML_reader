package models.relations;

import java.util.ArrayList;

import models.ModelDeclaration;
import models.classes.Class;

/**
 * This class is only the parsing class used to find what must be modify from what class
 * @author franc
 *
 */
public class RelationDeclaration extends ModelDeclaration {

	private ArrayList<String[]> roles = new ArrayList<>();
	
	private String strLines = "";
	
	public RelationDeclaration(String name) {
		super(name);
		
	}
	
	/**
	 * Function that, knowing was to do after the parse, apply the modification to the instance of classes
	 * @param classList
	 */
	public void apply(ArrayList<Class> classList) {
		for (String[] role : this.roles) {
			
			for (Class ownerClass : classList) {
				if (ownerClass.getName().equals(role[0])) {
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
