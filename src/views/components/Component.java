package views.components;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Master class Component.
 * Meant to be extended.  Only declares a JPanel and a JLabel.
 * @author olivi
 */
public abstract class Component
{

    protected JPanel pnl;
    
    /**
     * Sole constructor. (For invocation by subclass 
     * constructors, typically implicit.)
     */
    protected Component() {}


    /**
     * To add the present component to a JComponent.
     * @return JPanel containing all the JComponent of the present Component
     */
    public JComponent toDisplay()
    {
        return this.pnl;
    }
    
    /**
     * Clear the data from the Component.
     * Must be defined by subclasses.
     */
    public abstract void clear();

}
