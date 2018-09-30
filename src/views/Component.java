package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Component {

    protected JPanel pnl;
    protected JLabel lbl;
    protected JList list;
    protected JScrollPane sp;
    CustomListModel listModel;

    protected Component() {} // to allow children to create different constructor
    
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

    protected Component(String label, int preferredWidth, int preferredHeight) {
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

    protected JComponent getJComponent() {
        return this.pnl;
    }

    protected void addElement(String s) {

        this.listModel.addElement(s);
    }

}

class CustomListModel extends AbstractListModel {

    ArrayList<String> elements;

    protected CustomListModel() {
        elements = new ArrayList<String>();
    }
    
    public void addElement(String s) {
        elements.add(s);
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public Object getElementAt(int index) {
        return elements.get(index);
    }

}
