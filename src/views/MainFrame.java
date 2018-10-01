/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.UMLController;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author 1485246
 */
public class MainFrame extends JFrame
{

    private UMLController controller;
    private Component cClasses, cDetails, cAttributes, cMethods, cSubClasses, cAssociations;
    private FileInputComponent cFileInput;
    private JPanel pnl, pnlElement;

    public MainFrame(UMLController controller)
    {
        this.controller = controller;

        this.pnl = new JPanel(new BorderLayout());
        this.pnlElement = new JPanel(new GridLayout(2, 2));

        this.cFileInput = new FileInputComponent("Select File", null);
        this.cFileInput.getJButton().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                FileChooser fileChooser = new FileChooser("uml", "ucd");
                if (!fileChooser.isValid())
                {
                    JOptionPane.showMessageDialog(null, "Invalid file type");
                } else if (!fileChooser.isCancel())
                {
                    setFile(fileChooser.getFile());
                }

            }
        });
        this.cClasses = new Component("Classes", 200, 100);
        this.cDetails = new Component("Details");
        this.cAttributes = new Component("Attributes");
        this.cMethods = new Component("Methods");
        this.cSubClasses = new Component("SubClasses");
        this.cAssociations = new Component("Associations");

        this.render();
        this.declareListeners();

    }

    private void render()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(2 * dim.width / 9, dim.height / 6);

        this.pnlElement.add(this.cAttributes.getJComponent());
        this.pnlElement.add(this.cMethods.getJComponent());
        this.pnlElement.add(this.cSubClasses.getJComponent());
        this.pnlElement.add(this.cAssociations.getJComponent());

        this.pnl.add(this.cFileInput.getJComponent(), BorderLayout.NORTH);
        this.pnl.add(this.cClasses.getJComponent(), BorderLayout.WEST);
        this.pnl.add(this.pnlElement, BorderLayout.CENTER);
        this.pnl.add(this.cDetails.getJComponent(), BorderLayout.SOUTH);

        this.add(this.pnl);

        this.setTitle("UML Reader");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(5 * dim.width / 9, 2 * dim.height / 3));
        this.setResizable(false);
        this.pack();
    }

    private void declareListeners()
    {
        new ListAction(cClasses.list, new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JList list = (JList) e.getSource();
                notifyActionToController(list.getSelectedValue().toString());
            }
        });

        Action elementAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JList list = (JList) e.getSource();
                notifyActionToController(list.getSelectedValue().toString());
            }
        };
        
        new ListAction(cAttributes.list, elementAction);
        new ListAction(cMethods.list, elementAction);
        new ListAction(cSubClasses.list, elementAction);
        new ListAction(cAssociations.list, elementAction);
    }

    private void setFile(File file)
    {
        this.cFileInput.setFile(file);
        this.controller.generateModel(file);
    }

    public void setClasses(ArrayList<String> classes)
    {
        this.cClasses.clear();
        for (String sClass : classes)
        {
            this.cClasses.addElement(sClass);
        }
    }

    public void setDetails(String detail)
    {
        this.cDetails.clear();
        this.cDetails.addElement(detail);
    }

    public void setAttributes(ArrayList<String> attributes)
    {
        this.cAttributes.clear();
        for (String attribute : attributes)
        {
            this.cAttributes.addElement(attribute);
        }
    }

    public void setMethods(ArrayList<String> methods)
    {
        this.cMethods.clear();
        for (String method : methods)
        {
            this.cMethods.addElement(method);
        }
    }

    public void setSubClasses(ArrayList<String> subClasses)
    {
        this.cSubClasses.clear();
        for (String subClass : subClasses)
        {
            this.cSubClasses.addElement(subClass);
        }
    }

    public void setAssociations(ArrayList<String> associations)
    {
        this.cAssociations.clear();
        for (String association : associations)
        {
            this.cAssociations.addElement(association);
        }
    }
    
    public void selectElementFromClassList(String className) {
        for (int i = 0; i < this.cClasses.getListSize(); i++)
        {
            if (className.equals(this.cClasses.getElementAt(i))){
                this.cClasses.selectIndex(i);
            }
        }
    }

    private void notifyActionToController(String componentName)
    {
        this.controller.componentWasClicked(componentName);
    }
}
