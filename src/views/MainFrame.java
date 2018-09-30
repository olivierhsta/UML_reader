/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.UMLController;

/**
 *
 * @author 1485246
 */
public class MainFrame extends JFrame
{

	private UMLController		controller;
	private Component			cClasses, cDetails, cAttributes, cMethods, cSubClasses, cAssociations;
	private FileInputComponent	cFileInput;
	private JPanel				pnl, pnlElement;

	public MainFrame(UMLController controller)
	{
		this.controller = controller;

		this.pnl = new JPanel(new BorderLayout());
		this.pnlElement = new JPanel(new GridLayout(2, 2));

		this.cFileInput = new FileInputComponent("Select File", null);
		this.cFileInput.getJButton().addActionListener(new ActionListener() {

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
		this.cClasses = new Component(null, "Classes", 30, 15);
		this.cDetails = new Component(null, "Details", 5, 35);
		this.cAttributes = new Component(null, "Attributes");
		this.cMethods = new Component(null, "Methods");
		this.cSubClasses = new Component(null, "SubClasses");
		this.cAssociations = new Component(null, "Associations");

		this.render();

	}

	private void render()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(2 * dim.width / 9, dim.height / 6);

		this.pnlElement.add(this.cAttributes.getJComponent());
		this.pnlElement.add(this.cMethods.getJComponent());
		this.pnlElement.add(this.cSubClasses.getJComponent());
		this.pnlElement.add(this.cAssociations.getJComponent());

		this.pnl.add(this.cFileInput.getJComponent(), BorderLayout.NORTH);
		this.pnl.add(this.cClasses.getJComponent(), BorderLayout.WEST);
		this.pnl.add(this.pnlElement, BorderLayout.CENTER);
		this.pnl.add(this.cDetails.getJComponent(), BorderLayout.SOUTH);

		this.add(this.pnl);

		this.setTitle("UML Reader");
		this.setVisible(true);

		this.setPreferredSize(new Dimension(5 * dim.width / 9, 2 * dim.height / 3));
		this.setResizable(false);
		this.pack();
	}
	
	private void setFile(File file)
	{
		this.cFileInput.setFile(file);
		this.controller.setFile(file);
	}
}