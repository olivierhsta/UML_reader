package controllers;

import views.MainFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import models.Model;
import models.classes.Class;

import models.UMLDecoder;
import models.aggregations.Aggregation;
import models.classes.Attribute;
import models.classes.Operation;
import models.relations.Relation;

/**
 * Controller class that makes the bridge between the model and the view
 *
 * @author olivi
 */
public class UMLController
{

    /**
     * Entry point to the backend
     */
    private UMLDecoder model;
    /**
     * Entry point to the frontend
     */
    private MainFrame view;
    private HashMap<String,Model> UMLmodels;
    private HashMap<String,Class> classes;
    private Class currentClass = null;  // when a class is clicked, remember its instance
    private Model currentUMLModel = null;  // when an UMLmodel is chosen, remember its instance

    /**
     * Empty constructor. When this controller is used, the view and model are
     * expected to be set afterwards
     */
    public UMLController()
    {
        this.UMLmodels = new HashMap();
    }

    /**
     * Constructor receiving view and model.
     *
     * @param view Instance of MainFrame used as view
     * @param model Instance of UMLDecoder used as model
     */
    public UMLController(MainFrame view, UMLDecoder model)
    {
        this.view = view;
        this.model = model;
        this.UMLmodels = new HashMap();
    }

    public void setModel(UMLDecoder model)
    {
        this.model = model;
    }

    public void setView(MainFrame view)
    {
        this.view = view;
    }

    public UMLDecoder getModel()
    {
        return this.model;
    }

    public MainFrame getView()
    {
        return this.view;
    }

    /**
     * Generates the model encoded in a UML file.
     *
     * @param file File containing the UML model
     */
    public void generateModel(File file)
    {
        this.model = new UMLDecoder(this);
        this.model.setFile(file);
        if (!this.model.getUMLModels().isEmpty())
        {            
            for (Model model : this.model.getUMLModels())
            {
                if (model.getClasses().isEmpty())
                {
                    this.view.alert("Model " + model.getName() + " wrongly formated");
                }
                else if (this.currentUMLModel == null)
                {
                    this.currentUMLModel = model;
                }
                this.UMLmodels.put(model.getName(), model);
            }
            if (this.currentUMLModel != null)
            {
                System.out.println(this.currentUMLModel.getName());
                this.displayClasses(this.currentUMLModel); // display the information of the first model
                this.view.displayModelsNames(this.UMLmodels.keySet(), this.currentUMLModel.getName());
            }
        }
        else
        {
            this.view.alert("No Model declared in the given file.  Make sure the"
                    + " Model follows the UML standards.");
        }

    }

    /**
     * Displays the classes of the current model
     * and puts them in a HashMap
     * @param model model from which to display the classes
     */
    public void displayClasses(Model model)
    {
        this.classes = new HashMap();

        for (Class mClass : model.getClasses())
        {
            classes.put(mClass.getName(), mClass);
        }
        this.view.displayClasses(this.classes.keySet());
    }
    
    public void modelWasClicked(String modelName)
    {
        System.out.println(modelName);
        this.displayClasses(this.UMLmodels.get(modelName));
    }
    
    /**
     * Get the descriptive elements of a given class and display them. Will
     * display attributes, methods, associations, aggregations and subclasses
     *
     * @param className Name of the class which was clicked
     */
    public void classWasClicked(String className)
    {
        this.currentClass = this.classes.get(className);  // remember the current class

        /* --- Get and display the descriptive elements ---*/
        ArrayList<String> elementNames = new ArrayList();

        for (Attribute attribute : this.currentClass.getAttributes())
        {
            elementNames.add(attribute.getName());
        }
        this.view.displayAttributes(elementNames);
        elementNames.clear();

        for (Class subClass : this.currentClass.getSubClasses())
        {
            elementNames.add(subClass.getName());
        }
        this.view.displaySubClasses(elementNames);
        elementNames.clear();

        for (Operation operation : this.currentClass.getOperations())
        {
            elementNames.add(operation.getName());
        }
        this.view.displayMethods(elementNames);
        elementNames.clear();

        for (Aggregation aggreagation : this.currentClass.getAggregations())
        {
            elementNames.add(aggreagation.getName());
        }
        this.view.displayAggregations(elementNames);
        elementNames.clear();

        for (Relation relation : this.currentClass.getRelations())
        {
            elementNames.add(relation.getName());
        }
        this.view.displayAssociations(elementNames);
        elementNames.clear();
    }

    /**
     * Get and display the details of the attribute if there is some. As of now,
     * attributes have no details.
     *
     * @param attributeName Name of an attribute stored in the class' attributes
     * list
     */
    public void attributeWasClicked(String attributeName)
    {
        this.view.displayDetails("");
        for (Attribute attribute : this.currentClass.getAttributes())
        {
            if (attribute.getName().equals(attributeName))
            {
                this.view.displayDetails(attribute.getDetails());
            }
        }
    }

    /**
     * Get and display the details of the method if there is some. As of now,
     * methods have no details.
     *
     * @param methodName Name of a method stored in the class' methods list
     */
    public void methodWasClicked(String methodName)
    {
        this.view.displayDetails("");
        for (Operation method : this.currentClass.getOperations())
        {
            if (method.getName().equals(methodName))
            {
                this.view.displayDetails(method.getDetails());
            }
        }
    }

    /**
     * Get and display the details of the association if there is some.
     *
     * @param associationName Name of an association stored in the class'
     * association list
     */
    public void associationWasClicked(String associationName)
    {
        this.view.displayDetails("");
        for (Relation association : this.currentClass.getRelations())
        {
            if (association.getName().equals(associationName))
            {
                this.view.displayDetails(association.getDetails());
            }
        }
    }

    /**
     * Get and display the details of the association if there is some.
     *
     * @param aggregationName Name of an aggregation stored in the class'
     * aggregation list
     */
    public void aggregationWasClicked(String aggregationName)
    {
        this.view.displayDetails("");

        for (Aggregation aggregation : this.currentClass.getAggregations())
        {
            if (aggregation.getName().equals(aggregationName))
            {
                this.view.displayDetails(aggregation.getDetails());
            }
        }
    }
    
    /**
     * Ask the model to generate the metrics and sends the results to the view
     */
    public void calculateMetricsWasClicked()
    {
        // TODO
        ArrayList<String> metrics = new ArrayList();
        metrics.add(Integer.toString(this.currentClass.getCAC()));
        this.view.displayMetrics(metrics);
    }
}
