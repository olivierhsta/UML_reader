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
public class TextAreaComponent extends Component
{
    private JTextArea ta;
    private JLabel lbl;
    private JScrollPane sp;

    /**
     * Constructor to instantiate a DetailComponent with empty text area.
     * @param label Component label
     */
    public TextAreaComponent(String label)
    {
        this.newTextAreaComponent(label, "");
    }

    /**
     * Constructor to instantiate a DetailComponent with text in the text area.
     * @param label Component label
     * @param taText Component's text area text
     */
    public TextAreaComponent(String label, String taText)
    {
        this.newTextAreaComponent(label, taText);
    }
    
    /**
     * Hidden helper function that simply prevents code duplication between 
     * the different constructors.
     * @param label Component label
     * @param taText Component's text area text (pass "" for empty text area)
     */
    private void newTextAreaComponent(String label, String taText) {
        this.pnl = new JPanel(new BorderLayout());
        this.lbl = new JLabel(label);
        this.ta = new JTextArea(taText, 5,60);
        this.ta.setEditable(false);
        this.sp = new JScrollPane(this.ta);
        this.pnl.add(this.lbl, BorderLayout.NORTH);
        this.pnl.add(this.sp, BorderLayout.CENTER);
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
        if (current.equals(""))
        {
            this.ta.setText(text);
        }
        else
        {
            this.ta.setText(current + "\n" + text);
        }
    }

    /**
     * Delete the text in the DetailComponent's text area.
     */
    @Override
    public void clear()
    {
        this.ta.setText("");
    }
}
