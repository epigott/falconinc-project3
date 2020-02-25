/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * NOTE: Code is not considered 'clean', 'tidy', or 'complete', until explicitly indicated here or submitted as final code.
 */
package com.falconinc.project3;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author epigott
 */
public class SearchEngineMainWindow {
    
    public static void main (String[] args) {

// -- STARTUP -- //

    // Create main window frame.
    JFrame sewindow = new JFrame("Search Engine - Falcon INC");
    
    // Create admin window frame.
    // SearchEngineAdminWindow admin_panel = new SearchEngineAdminWindow();
    // JFrame admin_window = admin_panel.panel();
    
    // Will prevent the system process created by running in IDE from hanging when closed.
    sewindow.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing( WindowEvent we ) {
           System.out.println("Have A Good Day!");
           System.exit(0);
           }
        }
      );
    
    // Set search engine window size.
    sewindow.setSize(800, 600);
    
// -- MISCELLANEOUS -- //

    // Reusable line borders.
    LineBorder jpanel_borders1 = new LineBorder(Color.black, 1);
    LineBorder jpanel_borders3 = new LineBorder(Color.black, 3);
    LineBorder jpanel_borders5 = new LineBorder(Color.black, 5);
    
    // GridBagLayout Constraints.
    GridBagConstraints c = new GridBagConstraints();
    
// -- WINDOW HEADING -- //
    
    // Container used for heading.
    JPanel heading_panel = new JPanel(new GridBagLayout());
    //heading_panel.setBorder(jpanel_borders3);
    
    // Create and add the heading JLabel to the heading JPane.
    JLabel heading = new JLabel("Falcon INC Search Engine");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    heading_panel.add(heading, c);
    
// -- SEARCH PANEL -- //
    
    // General GridBagConstraints for search panel.
    c.fill = GridBagConstraints.CENTER;
    c.insets = new Insets(0, 5, 0, 5);
    
    // Container used for search bar.
    JPanel search_panel = new JPanel(new GridBagLayout());
    //search_panel.setBorder(jpanel_borders3);
    
    // Text field for search engine lookup.
    JTextField search_text = new JTextField();
    
    // Radio buttons for search engine lookup.
    JRadioButton search_and = new JRadioButton("AND");
    JRadioButton search_or = new JRadioButton("OR");
    JRadioButton search_phrase = new JRadioButton("PHRASE");
    
    // Button group for search engine lookup radio buttons.
    ButtonGroup search_radio_buttons = new ButtonGroup();
    
    // Adding search engine radio buttons to button group.
    search_radio_buttons.add(search_and);
    search_radio_buttons.add(search_or);
    search_radio_buttons.add(search_phrase);
    
    // JButton for submitting search terms.
    JButton submit_search = new JButton("Submit");
    
    // JLabel for search text box.
    JLabel search_heading = new JLabel("Enter a search term:");
    
    // Layout and add label for search text box.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    search_panel.add(search_heading, c);
    
    // Layout and add search text box.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    search_panel.add(search_text, c);
    
    //Layout and add search AND radio button.
    c.fill = GridBagConstraints.NONE;
    c.gridx = 0;
    c.gridy = 2;
    search_panel.add(search_and, c);

    //Layout and add search OR radio button.
    c.fill = GridBagConstraints.NONE;
    c.gridx = 1;
    c.gridy = 2;
    search_panel.add(search_or, c);
    
    //Layout and add search PHRASE radio button.
    c.fill = GridBagConstraints.NONE;
    c.gridx = 2;
    c.gridy = 2;
    search_panel.add(search_phrase, c);
    
    //Layout and add search submit button.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 3;
    search_panel.add(submit_search, c);
    
    
// -- MENU BUTTONS -- //
    
    // JPanel used for buttons.
    JPanel buttons_panel = new JPanel(new GridBagLayout());
      
    // Create and add the about button to the buttons panel.
    JButton about_button = new JButton("About");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    buttons_panel.add(about_button, c);
    
    // Event Handler for About Button.
    about_button.addActionListener(new MainWindowAboutButtonEventHandler());
    
    // Create and add the admin button to the buttons panel.
    JButton admin_button = new JButton("Admin");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    buttons_panel.add(admin_button, c);
    
    // Event Handler for Admin Button.
    admin_button.addActionListener(new MainWindowAdminButtonEventHandler());
    
    // Create and add the quit button to the buttons panel.
    JButton quit_button = new JButton("Quit");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    buttons_panel.add(quit_button, c);
    
    // Event Handler for Quit Button.
    quit_button.addActionListener(new MainWindowQuitButtonEventHandler());
    
// -- PAINTING MAIN WINDOW -- //
    
    // Add heading panel to main window.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    sewindow.add(heading_panel, BorderLayout.NORTH);
    
    // Add search panel to main window.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    sewindow.add(search_panel, BorderLayout.CENTER);
    
    // Add buttons panel to main window.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 2;
    sewindow.add(buttons_panel, BorderLayout.SOUTH);
    
    // Draw search engine window.
    sewindow.setVisible(true);
    
// -- END WINDOWs CREATION -- //
    
    }
    
    
       
}