package views.components;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Component for displaying details of a descriptive model element.
 * More generally, this Component contains a JLabel above a non-editable JTextArea.
 * @author olivi
 */
public class DetailComponent extends Component
{
    private JTextArea ta;
    private JScrollPane sp;

    /**
     * Constructor to instantiate a DetailComponent with empty text area.
     * @param label Component label
     */
    public DetailComponent(String label)
    {
        this.newDetailComponent(label, "");
    }

    /**
     * Constructor to instantiate a DetailComponent with text in the text area.
     * @param label Component label
     * @param taText Component's text area text
     */
    public DetailComponent(String label, String taText)
    {
        this.newDetailComponent(label, taText);
    }
    
    /**
     * Hidden helper function that simply prevents code duplication between 
     * the different constructors.
     * @param label Component label
     * @param taText Component's text area text (pass "" for empty text area)
     */
    private void newDetailComponent(String label, String taText) {
        super.pnl = new JPanel(new BorderLayout());
        super.lbl = new JLabel(label);
        this.ta = new JTextArea("", 5,60);
        this.ta.setEditable(false);
        this.sp = new JScrollPane(this.ta);
        super.pnl.add(super.lbl, BorderLayout.NORTH);
        super.pnl.add(this.sp, BorderLayout.CENTER);
    }
    
    /**
     * Set the text of the DetailComponent.
     * Overwrites any text currently displayed.
     * @param text The new text to be set.
     */
    public void setText(String text)
    {
        this.ta.setText(text);
    }

    /**
     * Add the given text to the DetailComponent.
     * Appends the new text to the old text, separated by a line break
     * @param text The new text to be append to the old one.
     */
    public void addElement(String text)
    {
        String current = this.ta.getText();
        this.ta.setText(current + "\n" + text);
    }

    /**
     * Delete the text in the DetailComponent's text area
     */
    public void clear()
    {
        this.ta.setText("");
    }
}
