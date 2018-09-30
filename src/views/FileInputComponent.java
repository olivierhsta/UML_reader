package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FileInputComponent extends Component
{
	
	private JButton btnFileSelect;
	private File file;
	
	protected FileInputComponent(String btnText, String taText)
	{
		super();
		
		super.pnl = new JPanel(new FlowLayout());
		
		this.btnFileSelect = new JButton(btnText);
		
		super.ta = new JTextArea(taText, 2, 40);
		super.sp = new JScrollPane(super.ta);
		super.pnl.add(this.btnFileSelect);
		super.pnl.add(super.sp);
		
		this.file=null;
	}
	
	protected void setFile(File file) {
		this.file = file;
		super.setText(file.getPath());
	}
	
	protected JButton getJButton()
	{
		return this.btnFileSelect;
	}
	
}
