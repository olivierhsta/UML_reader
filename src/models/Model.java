package models;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class Model {

	private String name;
	private ArrayList<ModelDeclaration> declarations = new ArrayList<>();


	public Model(String name, ArrayList<String> lines) {
		super();
		this.name = name;

		ArrayList<Class> classes = new ArrayList<>();
		ArrayList<Generalization> generalizations = new ArrayList<>();

		ModelDeclaration declaration = null;
		for	(int i = 0 ; i < lines.size() ; i++) {
			String line = lines.get(i);

			if (line.indexOf("CLASS") == 0) {
				declaration = new Class(line.replace("CLASS", "").trim());
				this.declarations.add(declaration);
				classes.add((Class) declaration);

			} else if (line.indexOf("RELATION") == 0) {
//				Not implemented yet
				declaration = null;

			} else if (line.indexOf("GENERALIZATION") == 0) {
				declaration = new Generalization(line.replace("GENERALIZATION", "").trim());
				this.declarations.add(declaration);
				generalizations.add((Generalization) declaration);

			} else if (line.indexOf("AGGREGATION") == 0) {
//				Not implemented yet
				declaration = null;

			} else {
				if (declaration != null)
					declaration.addLine(line);
			}
		}	
		
		for (ModelDeclaration decl : this.declarations) {
			decl.parseLines();
		}
		
		for (Generalization generalization : generalizations) {
			generalization.applyGeneralisation(classes);
		}
		
		for (ModelDeclaration decl : this.declarations) {
			System.out.println(decl.toString());
		}

	}



}
