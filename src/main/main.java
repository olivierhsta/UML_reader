package main;

import controllers.UMLController;
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
