package models.classes;

import java.util.ArrayList;

import models.ModelDeclaration;
import models.aggregations.Aggregation;
import models.relations.Relation;

/**
 * Represent a class of the model. The relations, generalizations and aggregations declarations will
 * modify instances of this class to represent the model.
 * 
 * Extends ModelDeclaration, because the model will create an instance and then add lines to the instance,
 * so this instance must implement its own parsing function to reconize its attributes and methods.
 * 
 * @author franc
 *
 */
public class Class extends ModelDeclaration
{

//	Basic class attributes
    private ArrayList<Attribute> attributes = new ArrayList<>();
    private ArrayList<Operation> operations = new ArrayList<>();

//    Arrays that will be populated by other ModelDeclarations
    private ArrayList<Class> subClasses = new ArrayList<>();
    private ArrayList<Relation> relations = new ArrayList<>();
    private ArrayList<Aggregation> aggregations = new ArrayList<>();
    

    public Class(String name)
    {
        super(name);
    }
    
    public int getAna() {
    	int nbParam = 0;
    	
    	for (Operation op : this.operations) {
    		nbParam += op.getParameters().size();
    	}    	
    	
    	return nbParam / this.operations.size();
    }

    public String getName()
    {
        return this.name;
    }

    public ArrayList<Attribute> getAttributes()
    {
        return attributes;
    }

    public ArrayList<Operation> getOperations()
    {
        return operations;
    }

    public ArrayList<Class> getSubClasses()
    {
        return subClasses;
    }

    public ArrayList<Relation> getRelations()
    {
        return relations;
    }

    public ArrayList<Aggregation> getAggregations()
    {
        return aggregations;
    }

//    Functions used by other ModelDeclarations to represent the model
    public void addRelation(Relation relation)
    {
        this.relations.add(relation);
    }

    public void addAggregation(Aggregation aggregation)
    {
        this.aggregations.add(aggregation);
    }

    public void setSubClasses(ArrayList<Class> subClasses)
    {
        this.subClasses = subClasses;
    }
//    -------

    @Override
    public void parseLines()
    {
        ArrayList<Operation> operatorations = new ArrayList<>();

        int addTo = -1;

        for (String line : this.lines)
        {
            if (line.indexOf("ATTRIBUTES") == 0)
            {
                addTo = 0;
            } else if (line.indexOf("OPERATIONS") == 0)
            {
                addTo = 1;
            } else
            {
                switch (addTo)
                {
                    case 0:
                        this.attributes.addAll(Attribute.extractAttributesFromString(line));
                        break;
                    case 1:
                        Operation op = Operation.extractOperationFromString(line);
                        if (op != null)
                        {
                            this.operations.add(op);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        String str = "CLASS " + this.name + "\n";

//		Impression des attibuts
        str += "\tAttributs:\n";
        for (Attribute attr : this.attributes)
        {
            str += "\t\t"
                    + String.format("%1$-10s", "{" + attr.getType() + "}")
                    + attr.getName()
                    + "\n";
        }

//		Impression des operations
        str += "\tOperations:\n";
        for (Operation ope : this.operations)
        {
            str += "\t\t" + String.format("%1$-10s", "{" + ope.getType() + "}") + ope.getName() + "(";

            for (Attribute attr : ope.getParameters())
            {
                str += attr.getName() + ":" + attr.getType() + ", ";
            }

            str += ")\n";
        }

//		Impression des sous-classes
        str += "\tSous-classes:\n";
        for (Class subClass : this.subClasses)
        {
            str += "\t\t" + subClass.name
                    + "\n";
        }

//		Impression des relations
        str += "\tRelations:\n";
        for (Relation relation : this.relations)
        {
            str += "\t\t" + relation.getName()
                    + "\n";
        }

//		Impression des aggregations
        str += "\tAggregations:\n";
        for (Aggregation aggre : this.aggregations)
        {
            str += "\t\t" + aggre.getName() + "\n";
        }

        return str;
    }

}
