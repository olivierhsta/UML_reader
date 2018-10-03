package views.components;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Component for displaying a File Input field.
 * This Component contains a button and a non-editable text field.
 * @author olivi
 */
public class FileInputComponent extends Component
{
    private JButton btnFileSelect;
    private JTextArea ta;
    private JScrollPane sp;
    private File file;

    /**
     * FileInputComponent's constructor.
     * @param btnText Text displayed on the button.
     * @param taText Text initially displayed in the text field.
     */
    public FileInputComponent(String btnText, String taText)
    {
        this.pnl = new JPanel(new FlowLayout());

        this.btnFileSelect = new JButton(btnText);

        this.ta = new JTextArea(taText, 2, 40);
        this.ta.setEditable(false);
        this.sp = new JScrollPane(this.ta);
        this.pnl.add(this.btnFileSelect);
        this.pnl.add(this.sp);

        this.file = null;
    }

    /**
     * Sets the file contained in the FileInputComponent.
     * This also shows it's path in the text field.
     * @param file File selected by the user.
     */
    public void setFile(File file)
    {
        this.file = file;
        this.ta.setText(file.getPath());
    }

    /**
     * Sets the button listener.
     * @param actionListener Action to execute upon click.
     */
    public void setListener(ActionListener actionListener)
    {
        this.btnFileSelect.addActionListener(actionListener);
    }
    
    @Override
    public void clear(){
        this.file = null;
        this.ta.setText("");
    }

}
