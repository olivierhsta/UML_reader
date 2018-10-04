package models;

import java.util.ArrayList;

import models.aggregations.AggregationDeclaration;
import models.classes.Class;
import models.generalizations.Generalization;
import models.relations.RelationDeclaration;

/**
 * Class representing a model in the file. Has a name and a list of classes
 * 
 * @author franc
 *
 */
public class Model {

	private String name;
	private ArrayList<Class> classes = new ArrayList<>();


	public Model(String name, ArrayList<String> lines) {
		super();
		this.name = name;

		ArrayList<ModelDeclaration> declarations = new ArrayList<>();
		ArrayList<Generalization> generalizations = new ArrayList<>();
		ArrayList<RelationDeclaration> relationDeclarations = new ArrayList<>();
		ArrayList<AggregationDeclaration> aggregationDeclarations = new ArrayList<>();

		ModelDeclaration declaration = null;
//		Goes through all the lines of the model, to find classes, relations, etc.
		for	(int i = 0 ; i < lines.size() ; i++) {
			String line = lines.get(i);

			if (line.indexOf("CLASS") == 0) {
				declaration = new Class(line.replace("CLASS", "").trim());
				this.classes.add((Class) declaration);
				declarations.add(declaration);

			} else if (line.indexOf("RELATION") == 0) {
				declaration = new RelationDeclaration(line.replace("RELATION", "").trim());
				declarations.add(declaration);
				relationDeclarations.add((RelationDeclaration)declaration);

			} else if (line.indexOf("GENERALIZATION") == 0) {
				declaration = new Generalization(line.replace("GENERALIZATION", "").trim());
				declarations.add(declaration);
				generalizations.add((Generalization) declaration);

			} else if (line.indexOf("AGGREGATION") == 0) {
				declaration = new AggregationDeclaration();
				declarations.add(declaration);
				aggregationDeclarations.add((AggregationDeclaration) declaration);

			} else {
				if (declaration != null)
					declaration.addLine(line);
			}
		}

//		Once everything is identified, parse them.
		for (ModelDeclaration decl : declarations) {
			decl.parseLines();
		}

//		Once everything knows what to do, apply the declarations to the existing classes
		for (Generalization generalization : generalizations) {
			generalization.apply(this.classes);
		}

		for (RelationDeclaration relationDecl : relationDeclarations) {
			relationDecl.apply(this.classes);
		}

		for (AggregationDeclaration aggregationDeclaration : aggregationDeclarations) {
			aggregationDeclaration.apply(this.classes);
		}

	}


	public ArrayList<Class> getClasses(){
		return this.classes;
	}
        
        public String getName(){
            return this.name;
        }

}
