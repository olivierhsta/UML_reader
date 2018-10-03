package views.components;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Master class Component.
 * Meant to be extended.  Only declares a JPanel and a JLabel.
 * @author olivi
 */
public class Component
{

    protected JPanel pnl;
    protected JLabel lbl;
    
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

}
