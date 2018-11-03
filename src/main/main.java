package main;

import java.util.ArrayList;

import controllers.UMLController;
import models.Model;
import models.UMLDecoder;
import views.MainFrame;

public class main
{
    
    public static void main(String[] args)
    {
        UMLController controller = new UMLController();
        MainFrame view = new MainFrame(controller);
        UMLDecoder model = new UMLDecoder(controller);
        controller.setModel(model);
        controller.setView(view);
        
    }

}
