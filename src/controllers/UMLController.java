package controllers;

import views.MainFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    private Class currentClass = null;  // when a class is clicked, remember its instance

    /**
     * Empty constructor. When this controller is used, the view and model are
     * expected to be set afterwards
     */
    public UMLController()
    {
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
            if (!this.model.getUMLModels().get(0).getClasses().isEmpty())
            {
                this.displayClasses();
                this.displayModelName();
            } else
            {
                this.view.alert("Model is not well formated");
            }
        } else
        {
            this.view.alert("No Model declared in the given file.  Make sure the"
                    + " Model follows the UML standards.");
        }

    }

    /**
     * Displays the classes of the model.
     */
    public void displayClasses()
    {
        ArrayList<String> classes = new ArrayList<String>();

        for (Class mClass : this.model.getUMLModels().get(0).getClasses())
        {
            classes.add(mClass.getName());
        }
        this.view.displayModelName(this.model.getUMLModels().get(0).getName());
        this.view.displayClasses(classes);
    }

    /**
     * Displays the name for the model.
     */
    public void displayModelName()
    {
        this.view.displayModelName(this.model.getUMLModels().get(0).getName());
    }

    /**
     * Get the descriptive elements of a given class and display them. Will
     * display attributes, methods, associations, aggregations and subclasses
     *
     * @param className Name of the class which was clicked
     */
    public void classWasClicked(String className)
    {

        Class clickedElement = null;
        for (Class mClass : this.model.getUMLModels().get(0).getClasses())
        {
            if (className.equals(mClass.getName()))
            {
                clickedElement = mClass;
                break; // we assume that there is only one class with the same name
            }
        }

        this.currentClass = clickedElement;  // remember the current class

        /* --- Get and display the descriptive elements ---*/
        ArrayList<String> elementNames = new ArrayList();

        for (Attribute attribute : clickedElement.getAttributes())
        {
            elementNames.add(attribute.getName());
        }
        this.view.displayAttributes(elementNames);
        elementNames.clear();

        for (Class subClass : clickedElement.getSubClasses())
        {
            elementNames.add(subClass.getName());
        }
        this.view.displaySubClasses(elementNames);
        elementNames.clear();

        for (Operation operation : clickedElement.getOperations())
        {
            elementNames.add(operation.getName());
        }
        this.view.displayMethods(elementNames);
        elementNames.clear();

        for (Aggregation aggreagation : clickedElement.getAggregations())
        {
            elementNames.add(aggreagation.getName());
        }
        this.view.displayAggregations(elementNames);
        elementNames.clear();

        for (Relation relation : clickedElement.getRelations())
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
        metrics.add("1");
        this.view.displayMetrics(metrics);
    }
}
