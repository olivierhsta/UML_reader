/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import javax.swing.JLabel;

/**
 * Component for displaying a simple label.
 * @author olivi
 */
public class LabelComponent extends Component
{

    private JLabel lbl;

    /**
     * Constructor of LabelComponent.
     * Initialize a JLabel and adds it to the panel.
     * @param labelText Text to display on the label
     */
    public LabelComponent(String labelText)
    {
        this.lbl = new JLabel(labelText);
        this.pnl.add(lbl);
    }

    /**
     * Empty the label text.
     */
    @Override
    public void clear()
    {
        this.lbl.setText("");
    }

    /**
     * Set the text of the label
     * @param labelText Text to display.
     */
    public void setText(String labelText)
    {
        this.lbl.setText(labelText);
    }

}
