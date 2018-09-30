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

    private void setFile(File file)
    {
        this.cFileInput.setFile(file);
        this.controller.setFile(file);
    }

    public void setClasses(String[] classes)
    {
        for (String sClass : classes)
        {
            this.cClasses.addElement(sClass);
        }
    }

    public void setDetails(String[] details)
    {
        for (String detail : details)
        {
            this.cDetails.addElement(detail);
        }
    }

    public void setAttributes(String[] attributes)
    {
        for (String attribute : attributes)
        {
            this.cAttributes.addElement(attribute);
        }
    }

    public void setMethods(String[] methods)
    {
        for (String method : methods)
        {
            this.cMethods.addElement(method);
        }
    }

    public void setSubClasses(String[] subClasses)
    {
        for (String subClass : subClasses)
        {
            this.cSubClasses.addElement(subClass);
        }
    }

    public void setAssociations(String[] associations)
    {
        for (String association : associations)
        {
            this.cAssociations.addElement(association);
        }
    }
}
