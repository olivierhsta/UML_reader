/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controllers.UMLController;
import models.UMLDecoder;

/**
 *
 * @author 1485246
 */
public class MainFrame extends JFrame
{

	private JTextArea		taFile, taClasses, taAttributes, taMethods, taSubClasses, taAssociations, taDetails;
	private FileChooser		fileChooser;
	private JButton			btnFile;
	private UMLController	controller;

	public MainFrame(UMLController controller)
	{
		this.controller = controller;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(2 * dim.width / 9, dim.height / 6);

		JPanel pnlMain = createMainPanel();

		this.add(pnlMain);

		this.setTitle("UML Reader");
		this.setVisible(true);

		this.setPreferredSize(new Dimension(5 * dim.width / 9, 2 * dim.height / 3));
		this.setResizable(false);
		this.pack();
	}

	private JPanel createMainPanel()
	{
		JPanel pnlMain = new JPanel(new BorderLayout());

		JPanel pnlContent = new JPanel(new GridLayout(2, 2));

		JPanel pnlFile = new JPanel(new FlowLayout());
		btnFile = new JButton("Select");
		createBtnFileEvent();
		taFile = new JTextArea("File", 2, 40);
		JScrollPane spFile = new JScrollPane(taFile, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlFile.add(btnFile);
		pnlFile.add(taFile);

		taMethods = new JTextArea("Methods");
		taAttributes = new JTextArea("Attributes");
		taSubClasses = new JTextArea("SubClasses");
		taAssociations = new JTextArea("Associations");
		JPanel pnlMethods = this.createContentPanel(taMethods,"Methods");
		JPanel pnlAttributes = this.createContentPanel(taAttributes,"Attributes");
		JPanel pnlSubClasses = this.createContentPanel(taSubClasses,"SubClasses");
		JPanel pnlAssociations = this.createContentPanel(taAssociations,"Associations");

		pnlContent.add(pnlMethods);
		pnlContent.add(pnlAttributes);
		pnlContent.add(pnlSubClasses);
		pnlContent.add(pnlAssociations);

		JPanel pnlClasses = new JPanel(new BorderLayout());
		taClasses = new JTextArea("Classes", 33, 15);
		JScrollPane spClasses = new JScrollPane(taClasses);
		JLabel lblClasses = new JLabel("Classes");
		pnlClasses.add(lblClasses, BorderLayout.NORTH);
		pnlClasses.add(spClasses, BorderLayout.CENTER);

		JPanel pnlDetails = new JPanel(new BorderLayout());
		taDetails = new JTextArea("Details", 5, 85);
		JScrollPane spDetails = new JScrollPane(taDetails);
		JLabel lblDetails = new JLabel("Details");
		pnlDetails.add(lblDetails, BorderLayout.NORTH);
		pnlDetails.add(spDetails, BorderLayout.CENTER);

		pnlMain.add(pnlFile, BorderLayout.NORTH);
		pnlMain.add(pnlClasses, BorderLayout.WEST);
		pnlMain.add(pnlContent, BorderLayout.CENTER);
		pnlMain.add(pnlDetails, BorderLayout.SOUTH);

		return pnlMain;
	}

	private JPanel createContentPanel(JTextArea taElement, String elementName)
	{
		JPanel pnlElement = new JPanel(new BorderLayout());
		JScrollPane spElement = new JScrollPane(taElement);
		JLabel lblElement = new JLabel(elementName);
		pnlElement.add(lblElement, BorderLayout.NORTH);
		pnlElement.add(spElement, BorderLayout.CENTER);

		return pnlElement;
	}

	private void createBtnFileEvent()
	{

		btnFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{

				fileChooser = new FileChooser();
				if (!fileChooser.cancelled())
				{
					setFile(fileChooser.getFile());
				}

			}
		});

	}

	private void setFile(File file)
	{
		this.controller.setFile(file);
		this.taFile.setText(file.getPath());
	}
}