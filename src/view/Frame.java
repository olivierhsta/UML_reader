/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author 1485246
 */
public class Frame extends JFrame {
	
    private JPanel pnlMain, pnlContent, pnlFile, pnlClasses, pnlDetails, pnlMethods, pnlAttributes, pnlSubClasses, pnlAssociations;
    private JTextArea taFile, taClasses, taAttributes, taMethods, taSubClasses, taAssociations, taDetails;
    private JButton btnFile;
    private JScrollPane spFile, spDetails, spClasses, spAttributes, spMethods, spSubClasses, spAssociations;
    private JLabel lblMethods, lblAttributes, lblSubClasses, lblAssociations, lblClasses, lblDetails;
    
    public Frame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(2 * dim.width / 9, dim.height / 6);

        pnlMain = new JPanel(new BorderLayout());
        
        
        pnlContent = new JPanel(new GridLayout(2,2));
        
        pnlFile = new JPanel(new FlowLayout());
        btnFile = new JButton("Select");
        taFile = new JTextArea("File", 2, 40);
        spFile = new JScrollPane(taFile,
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnlFile.add(btnFile);
        pnlFile.add(taFile);
        
        pnlMethods = this.createContentPanel("Methods");
        pnlAttributes = this.createContentPanel("Attributes");    
        pnlSubClasses = this.createContentPanel("SubClasses");   
        pnlAssociations = this.createContentPanel("Associations");
        
        pnlContent.add(pnlMethods);
        pnlContent.add(pnlAttributes);
        pnlContent.add(pnlSubClasses);
        pnlContent.add(pnlAssociations);
        
        pnlClasses = new JPanel(new BorderLayout());
        taClasses = new JTextArea("Classes", 33, 15);
        spClasses = new JScrollPane(taClasses);
        lblClasses = new JLabel("Classes");
        pnlClasses.add(lblClasses, BorderLayout.NORTH);
        pnlClasses.add(spClasses, BorderLayout.CENTER);
        
        pnlDetails = new JPanel(new BorderLayout());
        taDetails = new JTextArea("Details", 5, 85);
        spDetails = new JScrollPane(taDetails);
        lblDetails = new JLabel("Details");
        pnlDetails.add(lblDetails, BorderLayout.NORTH);
        pnlDetails.add(spDetails, BorderLayout.CENTER);
        
        pnlMain.add(pnlFile, BorderLayout.NORTH);
        pnlMain.add(pnlClasses, BorderLayout.WEST);
        pnlMain.add(pnlContent, BorderLayout.CENTER);
        pnlMain.add(pnlDetails, BorderLayout.SOUTH);
        
        this.add(pnlMain);

        this.setTitle("UML Reader");
        this.setVisible(true);

        this.setPreferredSize(new Dimension(5 * dim.width / 9, 2 * dim.height / 3));
        this.setResizable(false);
        this.pack();
    }
    
    private JPanel createContentPanel(String elementName) {
    	
    	JPanel pnlElement = new JPanel(new BorderLayout());
        JTextArea taElement = new JTextArea(elementName);
        JScrollPane spElement = new JScrollPane(taElement);
        JLabel lblElement = new JLabel(elementName);
        pnlElement.add(lblElement, BorderLayout.NORTH);
        pnlElement.add(spElement, BorderLayout.CENTER);
        
        return pnlElement;
    }
}