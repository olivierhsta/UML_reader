package models;

import java.io.File;

import controllers.UMLController;
import views.MainFrame;

public class UMLDecoder
{
	File file;
	UMLController controller;

	public UMLDecoder(UMLController controller)
	{
		this.controller = controller;
	}
	
	public void setFile(File file)
	{
		this.file = file;
	}
	
}
