package views;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Component
{
	protected JPanel pnl;
	protected JLabel lbl;
	protected JTextArea ta;
	protected JScrollPane sp;
	
	protected Component() {} // to allow children to create different constructor
	
	protected Component(String text, String label)
	{
		this.pnl = new JPanel(new BorderLayout());
		this.lbl = new JLabel(label);
		this.ta = new JTextArea(text);
		this.sp = new JScrollPane(this.ta);
		this.pnl.add(this.lbl, BorderLayout.NORTH);
		this.pnl.add(this.sp, BorderLayout.CENTER);
	}
	
	protected Component(String text, String label, int rows, int columns)
	{
		this.pnl = new JPanel(new BorderLayout());
		this.lbl = new JLabel(label);
		this.ta = new JTextArea(text, rows, columns);
		this.sp = new JScrollPane(this.ta);
		this.pnl.add(this.lbl, BorderLayout.NORTH);
		this.pnl.add(this.sp, BorderLayout.CENTER);
	}
	
	
	protected JComponent getJComponent()
	{
		return this.pnl;
	}
	
	protected void setText(String text)
	{
		this.ta.setText(text);
	}

	protected Object getJButton()
	{
		return null;
	}
	
}
