/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Component for displaying a simple label.
 * @author olivi
 */
public class ButtonComponent extends Component implements Listenable
{

    private JButton btn;

    /**
     * Constructor of ButtonComponent.
     * Initialize a JButton and adds it to the panel.
     * @param btnText Text to display on the button
     */
    public ButtonComponent(String btnText)
    {
        this.btn = new JButton(btnText);
        this.pnl.add(btn);
    }

    /**
     * Does nothing (to satisfy parent constraint)
     */
    @Override
    public void clear()
    {
        // do nothing
        // QUESTION : worth changing?
    }
    
    /**
     * Sets the button listener.
     * @param al Action to execute upon click.
     */
    @Override
    public void setListener(ActionListener al)
    {
        this.btn.addActionListener(al);
    }

    /**
     * Set the text of the button
     * @param btnText Text to display.
     */
    public void setText(String btnText)
    {
        this.btn.setText(btnText);
    }

}
