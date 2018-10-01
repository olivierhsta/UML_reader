package controllers;

import views.MainFrame;

import java.io.File;
import java.util.ArrayList;
import models.ModelDeclaration;
import models.Class;
import models.Model;

import models.UMLDecoder;

public class UMLController
{

    private UMLDecoder model;
    private MainFrame view;

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
            ArrayList<ModelDeclaration> declarations = model.getModelDeclarations();
            for (ModelDeclaration declaration : declarations)
            {
                if (declaration instanceof Class)
                {
                    classes.add(declaration.getName());
                }
            }
        }
        this.view.setClasses(classes);
    }
    
    public void componentWasClicked(String componentName){
        
    }

}
