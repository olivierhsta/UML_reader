package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Component
{

    protected JPanel pnl;
    protected JLabel lbl;
    protected JList list;
    protected JScrollPane sp;
    private CustomListModel listModel;
    private ArrayList<String> listModelString = new ArrayList<String>();

    protected Component()
    {
    } // to allow children to create different constructor

    protected Component(String label)
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

    protected Component(String label, int preferredWidth, int preferredHeight)
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

    protected JComponent getJComponent()
    {
        return this.pnl;
    }
    
    protected void unselectAll(){
        this.list.clearSelection();
    }

    protected void addElement(String s)
    {
        this.listModelString.add(s);
        this.listModel.addElement(s);
    }
    
    protected void clear() {
        this.listModelString.clear();
        this.listModel.deleteAll();
    }
    
    protected int getListSize(){
        return this.listModel.getSize();
    }
    
    protected String getElementAt(int index){
        return (String)this.listModel.getElementAt(index);
    }
    
    protected void selectIndex(int index){
        this.list.setSelectedIndex(index);
    }

}

class CustomListModel extends AbstractListModel
{

    ArrayList<String> elements;

    protected CustomListModel()
    {
        elements = new ArrayList<String>();
    }

    public void addElement(String s)
    {
        if (elements.add(s))
        {
            fireContentsChanged(this, 0, getSize());
        }
    }
    
    public void deleteAll(){
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
