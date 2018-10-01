package models;

import java.util.ArrayList;

import sun.text.normalizer.ReplaceableUCharacterIterator;

public class Model {

	private String name;
	private ArrayList<ModelDeclaration> declarations = new ArrayList<>();


	public Model(String name, ArrayList<String> lines) {
		super();
		this.name = name;

		ArrayList<Class> classes = new ArrayList<>();
		ArrayList<Generalization> generalizations = new ArrayList<>();
		ArrayList<RelationDeclaration> relationDeclarations = new ArrayList<>();
		ArrayList<AggregationDeclaration> aggregationDeclarations = new ArrayList<>();

		ModelDeclaration declaration = null;
		for	(int i = 0 ; i < lines.size() ; i++) {
			String line = lines.get(i);

			if (line.indexOf("CLASS") == 0) {
				declaration = new Class(line.replace("CLASS", "").trim());
				this.declarations.add(declaration);
				classes.add((Class) declaration);

			} else if (line.indexOf("RELATION") == 0) {
				declaration = new RelationDeclaration(line.replace("RELATION", "").trim());
				this.declarations.add(declaration);
				relationDeclarations.add((RelationDeclaration)declaration);

			} else if (line.indexOf("GENERALIZATION") == 0) {
				declaration = new Generalization(line.replace("GENERALIZATION", "").trim());
				this.declarations.add(declaration);
				generalizations.add((Generalization) declaration);

			} else if (line.indexOf("AGGREGATION") == 0) {
				declaration = new AggregationDeclaration();
				this.declarations.add(declaration);
				aggregationDeclarations.add((AggregationDeclaration) declaration);

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
		
		for (RelationDeclaration relationDecl : relationDeclarations) {
			relationDecl.apply(classes);
		}

		for (AggregationDeclaration aggregationDeclaration : aggregationDeclarations) {
			aggregationDeclaration.apply(classes);
		}
		
		for (ModelDeclaration decl : this.declarations) {
			System.out.println(decl.toString());
		}

	}



}
