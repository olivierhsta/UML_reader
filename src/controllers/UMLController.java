package controllers;

import views.MainFrame;

import java.io.File;

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

    public void setFile(File file)
    {
        this.model.setFile(file);
    }

    public void refreshView()
    {
        
    }

}
