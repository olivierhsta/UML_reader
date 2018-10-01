package models.generalizations;

import java.util.ArrayList;

import models.ModelDeclaration;
import models.classes.Class;

public class Generalization extends ModelDeclaration {

	ArrayList<String> strSubClasses = new ArrayList<>();
	
	public Generalization(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void applyGeneralisation(ArrayList<Class> classList) {
		for (Class parentClass : classList) {
			if (parentClass.getName().equals(this.name)) {
				ArrayList<Class> subClasses = new ArrayList<>();
				
				for (Class subClass : classList) {
					for (String strSubClass : this.strSubClasses) {
						if (subClass.getName().equals(strSubClass)) {
							subClasses.add(subClass);
						}
					}
				}
				
				parentClass.setSubClasses(subClasses);
				
			}
		}

	}

	@Override
	public void parseLines() {
		for (String line : this.lines) {
			if (line.contains("SUBCLASSES")) {
				line = line.replace("SUBCLASSES", "").trim();
				
				String[] strSubClasses = line.split(",");
				
				for (String strSubClass : strSubClasses) {
					if (!strSubClass.isEmpty()) {
						this.strSubClasses.add(strSubClass.trim());
					}
				}
			}
		}

	}

	@Override
	public String toString() {
		String str = "GENERALIZATION " + this.name + "\n";
		
		for (String strSubClass : this.strSubClasses) {
			str += "\t" + strSubClass + "\n";
		}
		return str;
	}

}
