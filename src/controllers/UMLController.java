package controllers;

import com.sun.nio.sctp.Association;
import views.MainFrame;

import java.io.File;
import java.util.ArrayList;
import models.ModelDeclaration;
import models.classes.Class;
import models.Model;

import models.UMLDecoder;
import models.aggregations.Aggregation;
import models.classes.Attribute;
import models.classes.Operation;
import models.relations.Relation;

public class UMLController
{

    private UMLDecoder model;
    private MainFrame view;
    private Class currentClass = null;

    public UMLController()
    {

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

    public void generateModel(File file)
    {
        this.model.setFile(file);
        this.setClasses(this.model.getUMLModels());
    }

    public void setClasses(ArrayList<Model> models)
    {
        ArrayList<String> classes = new ArrayList<String>();
        for (Model model : models)
        {
            ArrayList<Class> listClasses = model.getClasses();
            for (Class mClass : listClasses)
            {
                classes.add(mClass.getName());
            }
        }
        this.view.setClasses(classes);
    }
    
    public void classWasClicked(String className) {
        ArrayList<String> elementNames = new ArrayList();
        
        Class clickedElement = null;
        for (Class mClass : this.model.getUMLModels().get(0).getClasses())
        {
            if (className.equals(mClass.getName()))
            {
                clickedElement = mClass;
                break; // for now, if there is more than one component with the same name, the first one will always be the chosen one
            }
        }
        
        this.currentClass = clickedElement;
        
        for (Attribute attribute : clickedElement.getAttributes())
        {
            elementNames.add(attribute.getName());
        }
        this.view.setAttributes(elementNames);
        elementNames.clear();

        for (Class subClass : clickedElement.getSubClasses())
        {
            elementNames.add(subClass.getName());
        }
        this.view.setSubClasses(elementNames);
        elementNames.clear();

        for (Operation operation : clickedElement.getOperations())
        {
            System.out.println(operation.getName());
        }
        this.view.setMethods(elementNames);
        elementNames.clear();

        for (Aggregation aggreagation : clickedElement.getAggregations())
        {
            elementNames.add(aggreagation.getName());
        }
        for (Relation relation : clickedElement.getRelations())
        {
            elementNames.add(relation.getName());
        }
        this.view.setAssociations(elementNames);
        elementNames.clear();
    }
    
    public void attributeWasClicked(String attributeName){
        this.view.setDetails("");
        for (Attribute attribute : this.currentClass.getAttributes()){
            if (attribute.getName().equals(attributeName)){
                this.view.setDetails("");
            }
        }
    }
    
    public void methodWasClicked(String methodName){
        this.view.setDetails("");
        for (Operation method : this.currentClass.getOperations()){
            if (method.getName().equals(methodName)){
                this.view.setDetails("");
            }
        }
    }
    
    public void associationWasClicked(String associationName){
        this.view.setDetails("");
        for (Relation association : this.currentClass.getRelations()){
            if (association.getName().equals(associationName)){
                this.view.setDetails(association.getDetails());
            }
        }
        
        for (Aggregation aggregation : this.currentClass.getAggregations()){
            if (aggregation.getName().equals(associationName)){
                this.view.setDetails(aggregation.getDetails());
            }
        }
    }
//
//    public void componentWasClicked(String componentName)
//    {
//        // This takes the UMLModel 0 because, for now, we only expect one model per file
//        ArrayList<ModelDeclaration> declarations = this.model.getUMLModels().get(0).getModelDeclarations();
//        ModelDeclaration clickedElement = null;
//        for (ModelDeclaration declaration : declarations)
//        {
//            if (declaration.getName().equals(componentName))
//            {
//                clickedElement = declaration;
//                break; // for now, if there is more than one component with the same name, the first one will always be the chosen one
//            }
//        }
//
//        ArrayList<String> elementNames = new ArrayList<String>();
//
//        if (clickedElement == null)
//        {
//            this.view.setDetails("");
//        } else if (clickedElement instanceof Class)
//        {
//            this.view.selectElementFromClassList(((Class) clickedElement).getName());
//            for (Attribute attribute : ((Class) clickedElement).getAttributes())
//            {
//                elementNames.add(attribute.getName());
//            }
//            this.view.setAttributes(elementNames);
//            elementNames.clear();
//
//            for (Class subClass : ((Class) clickedElement).getSubClasses())
//            {
//                elementNames.add(subClass.getName());
//            }
//            this.view.setSubClasses(elementNames);
//            elementNames.clear();
//
//            for (Operation operation : ((Class) clickedElement).getOperations())
//            {
//                System.out.println(operation.getName());
//            }
//            this.view.setMethods(elementNames);
//            elementNames.clear();
//
//            for (Aggregation aggreagation : ((Class) clickedElement).getAggregations())
//            {
//                elementNames.add(aggreagation.getName());
//            }
//            for (Relation relation : ((Class) clickedElement).getRelations())
//            {
//                elementNames.add(relation.getName());
//            }
//            this.view.setAssociations(elementNames);
//            elementNames.clear();
//        } else 
//        {
//            this.view.setDetails(clickedElement.getDetails());
//        }
//    }

}
