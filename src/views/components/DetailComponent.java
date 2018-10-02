/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author olivi
 */
public class DetailComponent extends Component
{

    private JButton btnFileSelect;

    private JTextArea ta;

    protected DetailComponent(String label)
    {
        super();

        super.pnl = new JPanel(new BorderLayout());
        super.lbl = new JLabel(label);
        this.ta = new JTextArea("", 5,90);
        super.sp = new JScrollPane(this.ta);
        super.pnl.add(super.lbl, BorderLayout.NORTH);
        super.pnl.add(super.sp, BorderLayout.CENTER);
    }

    protected DetailComponent(String label, String taText)
    {
        super();

        super.pnl = new JPanel(new FlowLayout());

        this.ta = new JTextArea(taText);
        super.sp = new JScrollPane(this.ta);
        super.pnl.add(this.btnFileSelect);
        super.pnl.add(super.sp);
    }

    @Override
    protected void addElement(String text)
    {
        String current = this.ta.getText();
        this.ta.setText(current + "\n" + text);
    }

    @Override
    protected void clear()
    {
        this.ta.setText("");
    }
}
