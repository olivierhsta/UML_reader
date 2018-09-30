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
	
	JPanel pnl = new JPanel(new FlowLayout());
	JButton btnFileSelect;
	JTextArea taFilePath;
	JScrollPane spFilePath;
	
	public FileInputComponent(String btnText, String taText)
	{
		this.btnFileSelect = new JButton(btnText);
		this.btnFileSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				FileChooser fileChooser = new FileChooser();
				if (!fileChooser.cancelled())
				{
					setFile(fileChooser.getFile());
				}
				
			}
		});

		this.taFilePath = new JTextArea(taText, 2, 40);
		this.spFilePath = new JScrollPane(taFilePath);
		this.pnl.add(btnFileSelect);
		this.pnl.add(spFilePath);
		super.setTextArea(this.taFilePath);
	}
	
	private void setFile(File file) {
		this.taFilePath.setText(file.getPath());
	}
	
	public JPanel getJComponent() {
		return this.pnl;
	}
	
	public JButton getButton()
	{
		return this.btnFileSelect;
	}
	
}
