package models;

import java.util.ArrayList;

public class Model {

	private String name;
	private ArrayList<ModelDeclaration> declarations = new ArrayList<>();


	public Model(String name, ArrayList<String> lines) {
		super();
		this.name = name;

		System.out.println("Creating model " + name);

		ModelDeclaration declaration = null;
		for	(int i = 0 ; i < lines.size() ; i++) {
			String line = lines.get(i);

			if (line.indexOf("CLASS") == 0) {
				declaration = new Class(line.replace("CLASS", "").trim());
				this.declarations.add(declaration);

			} else if (line.indexOf("RELATION") == 0) {
//				Not implemented yet
				declaration = null;

			} else if (line.indexOf("GENERALIZATION") == 0) {
//				Not implemented yet
				declaration = null;

			} else if (line.indexOf("AGGREGATION") == 0) {
//				Not implemented yet
				declaration = null;

			} else {
				if (declaration != null)
					declaration.addLine(line);
			}
		}	
		
		System.out.println("MODEL " + this.name + " has " + this.declarations.size() + " declarations:");
		for (ModelDeclaration decl : this.declarations) {
			decl.parseLines();
			System.out.println(decl.toString());
		}

	}



}
