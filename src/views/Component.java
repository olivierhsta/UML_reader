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
	private JPanel pnl;
	private JLabel lbl;
	private JTextArea ta;
	private JScrollPane sp;
	
	public Component()
	{
		this.pnl = new JPanel(new BorderLayout());
		this.ta = new JTextArea();
		this.sp = new JScrollPane(this.ta);
		this.pnl.add(this.sp);
	}
	
	public Component(String text, int rows, int columns)
	{
		this.pnl = new JPanel(new BorderLayout());
		this.ta = new JTextArea(text, rows, columns);
		this.sp = new JScrollPane(ta);
		this.pnl.add(this.sp);
	}
	
	public Component(String text, String label)
	{
		this.pnl = new JPanel(new BorderLayout());
		this.lbl = new JLabel(label);
		this.ta = new JTextArea(text);
		this.sp = new JScrollPane(this.ta);
		this.pnl.add(this.lbl, BorderLayout.NORTH);
		this.pnl.add(this.sp, BorderLayout.CENTER);
	}
	
	public Component(String text, String label, int rows, int columns)
	{
		this.pnl = new JPanel(new BorderLayout());
		this.lbl = new JLabel(label);
		this.ta = new JTextArea(text, rows, columns);
		this.sp = new JScrollPane(this.ta);
		this.pnl.add(this.lbl, BorderLayout.NORTH);
		this.pnl.add(this.sp, BorderLayout.CENTER);
	}
	
	
	public JComponent getJComponent()
	{
		return this.pnl;
	}
	
	public void setText(String text)
	{
		this.ta.setText(text);
	}
	
	protected void setTextArea(JTextArea ta) {
		this.ta = ta;
	}
	
}
