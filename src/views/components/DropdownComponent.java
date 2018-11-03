/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Component for displaying a simple label.
 * @author olivi
 */
public class DropdownComponent extends Component implements Listenable
{

    private JComboBox cb;
    final DefaultComboBoxModel comboBoxModel;
    private JLabel lbl;

    /**
     * Constructor of DropdownComponent.
     * Initialize a JLabel and an empty dropdown menu.
     * @param labelText Text to display on the label
     */
    public DropdownComponent(String labelText)
    {
        this.comboBoxModel = new DefaultComboBoxModel();
        this.cb = new JComboBox(comboBoxModel);
        cb.setPrototypeDisplayValue("----------------------------"); // set minimum width
        this.lbl = new JLabel(labelText);
        this.pnl.add(lbl);
        this.pnl.add(cb);
    }

    /**
     * Empty the combobox.
     */
    @Override
    public void clear()
    {
        this.comboBoxModel.removeAllElements();
    }

    /**
     * Set the text of the label
     * @param labelText Text to display.
     */
    public void setText(String labelText)
    {
        this.lbl.setText(labelText);
    }
    
    /**
     * Adds an element to the dropdown.
     * @param elementTxt String to add
     */
    public void addElement(String elementTxt)
    {
        this.comboBoxModel.addElement(elementTxt);
    }
    
    @Override
    public void setListener(ActionListener al)
    {
        this.cb.addActionListener(al);
    }
    
    public void setSelectedItem(String item)
    {
        this.comboBoxModel.setSelectedItem(item);
    }

}
