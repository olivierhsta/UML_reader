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
    private String prefix;
    private final static int DEFAULT_HEIGHT = 5;
    private final static int DEFAULT_WIDTH = 5;
    private final static String DEFAULT_PREFIX = "  ";

    /**
     * Constructor to instantiate a DetailComponent with empty text area.
     * @param label Component label
     */
    public TextAreaComponent(String label)
    {
        this.newTextAreaComponent(label, "", DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    /**
     * Constructor to instantiate a DetailComponent with text in the text area.
     * @param label Component label
     * @param taText Component's text area text
     */
    public TextAreaComponent(String label, String taText)
    {
        this.newTextAreaComponent(label, taText, DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }
    
    /**
     * Constructor to instantiate a DetailComponent with the dimensions
     * @param label Component label
     * @param height Component's height
     * @param width Component's width
     */
    public TextAreaComponent(String label, int height, int width){
        this.newTextAreaComponent(label, "", height, width);
    }
    
    /**
     * Constructor to instantiate a DetailComponent with text in the text area
     * and the dimensions.
     * @param label Component label
     * @param taText Component's text area text
     * @param height Component'S height
     * @param width Component's width
     */
    public TextAreaComponent(String label, String taText, int height, int width){
        this.newTextAreaComponent(label, taText, height, width);
    }
    
    /**
     * Hidden helper function that simply prevents code duplication between 
     * the different constructors.
     * @param label Component label
     * @param taText Component's text area text (pass "" for empty text area)
     */
    private void newTextAreaComponent(String label, String taText, int height, int width) {
        this.pnl = new JPanel(new BorderLayout());
        this.lbl = new JLabel(label);
        this.ta = new JTextArea(taText, height,width);
        this.ta.setEditable(false);
        this.sp = new JScrollPane(this.ta);
        this.pnl.add(this.lbl, BorderLayout.NORTH);
        this.pnl.add(this.sp, BorderLayout.CENTER);
        this.prefix = DEFAULT_PREFIX;
    }
    
    public void setPrefix(String prefix){
        this.prefix = prefix;
    }
    
    /**
     * Set the text of the DetailComponent.
     * Overwrites any text currently displayed.
     * @param text The new text to be set.
     */
    public void setText(String text)
    {
        this.ta.setText(this.prefix + text);
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
            this.ta.setText(this.prefix + text);
        }
        else
        {
            this.ta.setText(current + "\n" + this.prefix + text);
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
