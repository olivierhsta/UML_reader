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
	
    private JPanel pnlMain, pnlContent, pnlFile, pnlClasses, pnlDetails;
    private JTextArea taFile, taClasses, taAttributes, taMethods, taSubClasses, taAssociations, taDetails;
    private JButton btnFile;
    private JScrollPane spFile, spDetails, spClasses, spAttributes, spMethods, spSubClasses, spAssociations;
    
    Border border;   
        
    public Frame(){
        //positionner la fenetre principale
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(2 * dim.width / 9, dim.height / 6);

        pnlMain = new JPanel(new BorderLayout());

        pnlFile = new JPanel(new FlowLayout());
        taFile = new JTextArea("File", 2, 50);
        spFile = new JScrollPane(taFile, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        btnFile = new JButton("Select");
        pnlFile.add(btnFile);
        pnlFile.add(spFile);
        
        pnlContent = new JPanel(new GridLayout(2,2));
        taMethods = new JTextArea("Methods");
        spMethods = new JScrollPane(taMethods, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taAttributes = new JTextArea("Attributes");
        spAttributes = new JScrollPane(taAttributes, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taSubClasses = new JTextArea("SubClasses");
        spSubClasses = new JScrollPane(taSubClasses, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taAssociations = new JTextArea("Associations");
        spAssociations = new JScrollPane(taAssociations, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlContent.add(spMethods);
        pnlContent.add(spAttributes);
        pnlContent.add(spSubClasses);
        pnlContent.add(spAssociations);
        
        pnlClasses = new JPanel(new FlowLayout());
        taClasses = new JTextArea("Classes", 33, 15);
        spClasses = new JScrollPane(taClasses, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlClasses.add(spClasses);
        
        pnlDetails = new JPanel(new FlowLayout());
        taDetails = new JTextArea("Details", 5, 85);
        spDetails = new JScrollPane(taDetails, 
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlDetails.add(spDetails);
        
        pnlMain.add(pnlFile, BorderLayout.NORTH);
        pnlMain.add(pnlClasses, BorderLayout.WEST);
        pnlMain.add(pnlContent, BorderLayout.CENTER);
        pnlMain.add(pnlDetails, BorderLayout.SOUTH);
        
        this.add(pnlMain);

        this.setTitle("UML Reader");
        this.setVisible(true);

        //this.setPreferredSize(new Dimension(5 * dim.width / 9, 2 * dim.height / 3));
        this.setResizable(false);
        this.pack();
    }
}