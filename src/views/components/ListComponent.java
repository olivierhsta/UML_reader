package views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Component to display a list of element.
 * This Component displays a JLabel above a JScrollPane containing a JList
 * @author olivi
 */
public class ListComponent extends Component
{

    private JList list;
    private JLabel lbl;
    private JScrollPane sp;
    private CustomListModel listModel;
    private ArrayList<String> listModelString = new ArrayList<String>();

    /**
     * Constructor to be use when the list size should be automatically adjusted.
     * @param label Label of the ListComponent
     */
    public ListComponent(String label)
    {
        this.pnl = new JPanel(new BorderLayout());
        this.lbl = new JLabel(label);
        this.list = new JList();
        this.listModel = new CustomListModel();
        this.list.setModel(listModel);
        this.sp = new JScrollPane(this.list);
        this.pnl.add(this.lbl, BorderLayout.NORTH);
        this.pnl.add(this.sp, BorderLayout.CENTER);
    }

    /**
     * Constructor to be use when the list size should be manually adjusted.
     * Note that the given dimension might not be respected at all cost.
     * @param label Label of the ListComponent.
     * @param preferredWidth Preferred width of the Component JList.
     * @param preferredHeight Preferred height of the Component JList
     */
    public ListComponent(String label, int preferredWidth, int preferredHeight)
    {
        this.pnl = new JPanel(new BorderLayout());
        this.pnl.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        this.lbl = new JLabel(label);
        this.list = new JList();
        this.listModel = new CustomListModel();
        this.list.setModel(listModel);
        this.sp = new JScrollPane(this.list);
        this.pnl.add(this.lbl, BorderLayout.NORTH);
        this.pnl.add(this.sp, BorderLayout.CENTER);
    }

    /**
     * Sets an ActionListener mapped to the ENTER key and the mouse double click.
     * @param al ActionListement to map
     */
    public void setListener(ActionListener al)
    {

        /* Map the al to the ENTER key */
        this.list.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    al.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "ENTER"));
                }
            }
        });

        /* Map the al to the mouse's double click */
        this.list.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    al.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "DOUBLECLICK"));
                }
            }
        });

    }

    /**
     * Manually clears all the highlighting of the JList.
     * Note that this function's purpose is solely visual. It does not trigger
     * any action.
     */
    public void unselectAll()
    {
        this.list.clearSelection();
    }

    /**
     * Adds an element to the list.
     * @param obj Element to add, must have a defined toString() function.
     */
    public void addElement(Object obj)
    {
        this.listModelString.add(obj.toString());
        this.listModel.addElement(obj.toString());
    }

    /**
     * Deletes all the entry in the list.
     */
    @Override
    public void clear()
    {
        this.listModelString.clear();
        this.listModel.deleteAll();
    }

    /**
     * @return The list size 
     */
    public int getListSize()
    {
        return this.listModel.getSize();
    }

    /**
     * Getter for the name of the element at index <code>index</code>
     * @param index Index of the element to get
     * @return Name of the element at index <code>index</code>
     */
    public String getElementAt(int index)
    {
        return (String) this.listModel.getElementAt(index);
    }

    /**
     * Highlights element at given index.
     * Note that this function's purpose is solely visual. It does not trigger
     * any action.
     * @param index Index of the element to highlight
     */
    public void selectIndex(int index)
    {
        this.list.setSelectedIndex(index);
    }

}

/**
 * List model for the ListComponent.
 * @author olivi
 */
class CustomListModel extends AbstractListModel
{

    ArrayList<String> elements;

    /**
     * CustomListModel's Constructor.
     */
    protected CustomListModel()
    {
        elements = new ArrayList<String>();
    }

    /**
     * Add an element to the list.
     * @param s String to append to the list
     */
    protected void addElement(String s)
    {
        if (elements.add(s))
        {
            fireContentsChanged(this, 0, getSize());
        }
    }

    /**
     * Delete all the data in the list.
     */
    protected void deleteAll()
    {
        elements.clear();
        fireContentsChanged(this, 0, getSize());
    }

    @Override
    public int getSize()
    {
        return elements.size();
    }

    @Override
    public Object getElementAt(int index)
    {
        return elements.get(index);
    }

}
