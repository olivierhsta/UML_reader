package models.classes;

import java.util.ArrayList;

import models.ModelDeclaration;
import models.aggregations.Aggregation;
import models.relations.Relation;

public class Class extends ModelDeclaration
{

    private ArrayList<Attribute> attributes = new ArrayList<>();
    private ArrayList<Operation> operations = new ArrayList<>();

    private ArrayList<Class> subClasses = new ArrayList<>();

    private ArrayList<Relation> relations = new ArrayList<>();

    private ArrayList<Aggregation> aggregations = new ArrayList<>();

    /**
     * Pas utile pour l'instant, mais j'estime qu'il soit possible que l'on en
     * ait peu-etre besoin plus tard. peu-etre ;)
     */
    private Boolean parsed;

    public Class(String name)
    {
        super(name);
        this.parsed = false;
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

    public void addRelation(Relation relation)
    {
        this.relations.add(relation);
    }

    public void addAggregation(Aggregation aggregation)
    {
        this.aggregations.add(aggregation);
    }

//	public void removeRelation(String relationName) {
//		
//	}
    public void setSubClasses(ArrayList<Class> subClasses)
    {
        this.subClasses = subClasses;
    }

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
        this.parsed = true;
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
        str += "\tOp�rations:\n";
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