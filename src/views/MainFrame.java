/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import views.components.FileInputComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

import main.config;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.UMLController;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import views.components.ButtonComponent;
import views.components.LabelComponent;
import views.components.ListComponent;
import views.components.TextAreaComponent;

/**
 *
 * @author 1485246
 */
public class MainFrame extends JFrame
{

    private UMLController controller;
    private ListComponent cClasses, cAttributes, cMethods, cSubClasses, cAssociations, cAggregations, cMetrics;
    private FileInputComponent cFileInput;
    private LabelComponent cModelLabel;
    private TextAreaComponent cDetails;
    private ButtonComponent cCalculateMetrics;
    private JPanel pnl, pnlElement, pnlHeader;

    /**
     * Constructor of the main frame. The frame contains a FileInput field, and
     * six (6) fields :
     * <ul>
     * <li>Classes</li>
     * <li>Methods</li>
     * <li>Attributes</li>
     * <li>Associations</li>
     * <li>Aggregations</li>
     * <li>SubClasses</li>
     * </ul>
     *
     * @param controller This running instance's controller.
     */
    public MainFrame(UMLController controller)
    {
        this.controller = controller;

        this.cFileInput = new FileInputComponent("Select File", null);
        this.cModelLabel = new LabelComponent("");
        this.cCalculateMetrics = new ButtonComponent("Calculate Metrics");

        this.cClasses = new ListComponent("Classes", 200, 100);
        this.cAttributes = new ListComponent("Attributes");
        this.cMethods = new ListComponent("Methods");
        this.cSubClasses = new ListComponent("SubClasses");
        this.cAssociations = new ListComponent("Associations");
        this.cAggregations = new ListComponent("Aggregations");
        this.cMetrics = new ListComponent("Metriques");

        this.cDetails = new TextAreaComponent("Details");

        this.render();
        this.declareListeners();

    }

    /**
     * Renders the frame and its elements.
     */
    private void render()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(2 * dim.width / 9, dim.height / 6);
        
        this.pnl = new JPanel(new BorderLayout());
        this.pnlHeader = new JPanel(new BorderLayout());
        this.pnlElement = new JPanel(new GridLayout(3, 2));

        this.pnlHeader.add(this.cModelLabel.toDisplay(), BorderLayout.WEST);
        this.pnlHeader.add(this.cFileInput.toDisplay(), BorderLayout.CENTER);
        this.pnlHeader.add(this.cCalculateMetrics.toDisplay(), BorderLayout.EAST);
        
        this.pnlElement.add(this.cAttributes.toDisplay());
        this.pnlElement.add(this.cMethods.toDisplay());
        this.pnlElement.add(this.cAssociations.toDisplay());
        this.pnlElement.add(this.cAggregations.toDisplay());
        this.pnlElement.add(this.cSubClasses.toDisplay());

        this.pnl.add(this.pnlHeader, BorderLayout.NORTH);
        this.pnl.add(this.cClasses.toDisplay(), BorderLayout.WEST);
        this.pnl.add(this.cMetrics.toDisplay(), BorderLayout.EAST);
        this.pnl.add(this.pnlElement, BorderLayout.CENTER);
        this.pnl.add(this.cDetails.toDisplay(), BorderLayout.SOUTH);

        this.add(this.pnl);

        this.setTitle(config.PROJECT_NAME);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(5 * dim.width / 9, 2 * dim.height / 3));
        this.setResizable(false);
        this.pack();
    }

    /**
     * Declare the listeners of the components.
     */
    private void declareListeners()
    {
        this.cFileInput.setListener((ActionEvent e) ->
        {

            FileChooser fileChooser = new FileChooser(config.ACCEPTED_EXTENSIONS);
            clearData();
            if (!fileChooser.isCancel())
            {
                if (!fileChooser.isValid())
                {
                    alert("Invalid file type");
                } else if (fileChooser.isEmpty())
                {
                    alert("The file is empty");
                } else
                {
                    setFile(fileChooser.getFile());
                }
            }
        });

        this.cClasses.setListener((ActionEvent e) ->
        {
            JList list = (JList) e.getSource();
            classIsClicked(list.getSelectedValue().toString());
        });

        this.cAttributes.setListener((ActionEvent e) ->
        {
            JList list = (JList) e.getSource();
            attributeIsClicked(list.getSelectedValue().toString());
        });

        this.cMethods.setListener((ActionEvent e) ->
        {
            JList list = (JList) e.getSource();
            methodIsClicked(list.getSelectedValue().toString());
        });

        this.cSubClasses.setListener((ActionEvent e) ->
        {
            JList list = (JList) e.getSource();
            classIsClicked(list.getSelectedValue().toString());
        });

        this.cAssociations.setListener((ActionEvent e) ->
        {
            JList list = (JList) e.getSource();
            associationIsClicked(list.getSelectedValue().toString());
        });

        this.cAggregations.setListener((ActionEvent e) ->
        {
            JList list = (JList) e.getSource();
            aggregationIsClicked(list.getSelectedValue().toString());
        });
        
        this.cCalculateMetrics.setListener((ActionEvent e) ->
        {
            calculateMetricsIsClicked();
        });
    }

    /**
     * Private function that displays the file path in the FileInput text field
     * and notify the controller that a file has been selected.
     *
     * @param file Selected file.
     */
    private void setFile(File file)
    {
        this.cFileInput.setFile(file);
        this.controller.generateModel(file);
    }

    /**
     * Affiche une alerte.
     *
     * @param message Chaine de caractère à afficher dans l'alerte.
     */
    public void alert(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Display a list of String in the Class field.
     *
     * @param classes Elements to display
     */
    public void displayClasses(ArrayList<String> classes)
    {
        this.unselectAllSubs();
        this.cClasses.clear();
        for (String sClass : classes)
        {
            this.cClasses.addElement(sClass);
        }
    }

    /**
     * Display a String in the Details field.
     *
     * @param detail Element to display
     */
    public void displayDetails(String detail)
    {
        this.cDetails.clear();
        this.cDetails.addElement(detail);
    }
    
    /**
     * Display the UML Model name in the label on the top left.
     * @param name Name of the UML Model.
     */
    public void displayModelName(String name)
    {
        this.cModelLabel.setText(name);
    }

    /**
     * Display a list of String in the Attributes field.
     *
     * @param attributes Elements to display.
     */
    public void displayAttributes(ArrayList<String> attributes)
    {
        this.cAttributes.clear();
        for (String attribute : attributes)
        {
            this.cAttributes.addElement(attribute);
        }
    }

    /**
     * Display a list of String in the Methods field.
     *
     * @param methods Elements to display.
     */
    public void displayMethods(ArrayList<String> methods)
    {
        this.cMethods.clear();
        for (String method : methods)
        {
            this.cMethods.addElement(method);
        }
    }

    /**
     * Display a list of String in the SubClasses field.
     *
     * @param subClasses Elements to display.
     */
    public void displaySubClasses(ArrayList<String> subClasses)
    {
        this.cSubClasses.clear();
        for (String subClass : subClasses)
        {
            this.cSubClasses.addElement(subClass);
        }
    }

    /**
     * Display a list of String in the Association field.
     *
     * @param associations Elements to display.
     */
    public void displayAssociations(ArrayList<String> associations)
    {
        this.cAssociations.clear();
        for (String association : associations)
        {
            this.cAssociations.addElement(association);
        }
    }

    /**
     * Display a list of String in the Aggregations field.
     *
     * @param aggregations Elements to display.
     */
    public void displayAggregations(ArrayList<String> aggregations)
    {
        this.cAggregations.clear();
        for (String aggregation : aggregations)
        {
            this.cAggregations.addElement(aggregation);
        }
    }
    
    /**
     * Display a list of String in the Metrics field.
     * @param metrics Elements to display
     */
    public void displayMetrics(ArrayList<String> metrics)
    {
        for (String metric : metrics)
        {
            this.cMetrics.addElement(metric);
        }
    }

    /**
     * Manually select an element from the class list. This only highlights the
     * element, it does not trigger any action.
     *
     * @param className Name of the class element to select.
     */
    public void selectElementFromClassList(String className)
    {
        for (int i = 0; i < this.cClasses.getListSize(); i++)
        {
            if (className.equals(this.cClasses.getElementAt(i)))
            {
                this.cClasses.selectIndex(i);
            }
        }
    }

    /**
     * Clears all selected fields and notify the controller when a class item is
     * clicked.
     *
     * @param className Name of the clicked class
     */
    private void classIsClicked(String className)
    {
        unselectAllSubs();
        this.controller.classWasClicked(className);
    }

    /**
     * Clears all selected fields and notify the controller when an attribute
     * item is clicked.
     *
     * @param attributeName Name of the clicked attribute
     */
    private void attributeIsClicked(String attributeName)
    {
        unselectAllSubs();
        this.controller.attributeWasClicked(attributeName);
    }

    /**
     * Clears all selected fields and notify the controller when a method item
     * is clicked.
     *
     * @param methodName Name of the clicked method.
     */
    private void methodIsClicked(String methodName)
    {
        unselectAllSubs();
        this.controller.methodWasClicked(methodName);
    }

    /**
     * Clears all selected fields and notify the controller when an association
     * item is clicked.
     *
     * @param associationName Name of the clicked association.
     */
    private void associationIsClicked(String associationName)
    {
        unselectAllSubs();
        this.controller.associationWasClicked(associationName);
    }

    /**
     * Clears all selected fields and notify the controller when an aggregation
     * item is clicked.
     *
     * @param aggregationName Name of the clicked aggregation.
     */
    private void aggregationIsClicked(String aggregationName)
    {
        unselectAllSubs();
        this.controller.aggregationWasClicked(aggregationName);
    }
    
    /**
     * Clears the metrics fields and notify the controller when the 
     * calculateMetrics component is clicked
     */
    private void calculateMetricsIsClicked()
    {
        this.cMetrics.clear();
        this.controller.calculateMetricsWasClicked();
    }

    /**
     * Unselect every list element except for the class' list
     */
    private void unselectAllSubs()
    {
        this.cAssociations.unselectAll();
        this.cAggregations.unselectAll();
        this.cAttributes.unselectAll();
        this.cMethods.unselectAll();
        this.cSubClasses.unselectAll();
    }

    /**
     * Clears everything
     */
    private void clearData()
    {
        this.cFileInput.clear();
        this.cModelLabel.clear();
        this.cAggregations.clear();
        this.cAssociations.clear();
        this.cAttributes.clear();
        this.cMethods.clear();
        this.cSubClasses.clear();
        this.cClasses.clear();
        this.cDetails.clear();
        this.cMetrics.clear();
    }
}
