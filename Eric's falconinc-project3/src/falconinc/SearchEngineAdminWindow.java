/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package falconinc;
import falconinc.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author epigott
 */
public class SearchEngineAdminWindow {
    
    public static void main (String[] args) {
        SearchEngineAdminWindow admin = new SearchEngineAdminWindow();
        JFrame admin_window = admin.panel();
    
        // Will prevent the system process created by running in IDE from hanging when closed.
        admin_window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent we ) {
               System.out.println("Have A Good Day!");
               System.exit(0);
               }
            }
          );
    
        admin_window.setVisible(true);
    }
    
    public JFrame panel() {
        
// -- STARTUP -- //

    // Create main window frame.
    JFrame admin_window = new JFrame("Search Engine Admin Window- Falcon INC");
    
    // Set search engine admin panel window size.
    admin_window.setSize(800, 600);
    
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
    JLabel heading = new JLabel("Falcon INC Search Engine Admin Panel");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    heading_panel.add(heading, c);
    
// -- FILE LIST PANEL -- //
    
    // General GridBagConstraints.
    //c.fill = GridBagConstraints.CENTER;
    //c.insets = new Insets(0, 5, 0, 5);
    
    // Container used for file list panel.
    JPanel filelist_panel = new JPanel(new GridBagLayout());
    
    // JLabel for file list.
    JLabel filelist_heading = new JLabel("List of files:", JLabel.CENTER);
    
    // String[] fake_file_list = {'C\:\\iamafile\\file1.txt', 'C\:\\iamafile\\file1.txt', 'C\:\\iamafile\\file1.txt'};
    String[] data = new String[3];
    // Netbeans complains about errors on the next three lines, but compiles and runs with issue:
    data[0] = "C:\\myfiles\\file1.txt";
    data[1] = "C:\\myfiles\\file2.txt";
    data[2] = "C:\\myfiles\\file3.txt";
    JList<String> myList = new JList<String>(data);
    
    c.gridx = 0;
    c.gridy = 0;
    filelist_panel.add(filelist_heading, c);
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    filelist_panel.add(myList, c);
    
// -- MENU BUTTONS -- //
    
    // JPanel used for buttons.
    JPanel buttons_panel = new JPanel(new GridBagLayout());
      
    // Create and add the add file button to the buttons panel.
    JButton add_file_button = new JButton("Add File");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    buttons_panel.add(add_file_button, c);
    
    // Event Handler for Add File Button
    add_file_button.addActionListener(new AdminWindowAddFileButtonEventHandler());
    
    // Create and add the remove file button to the buttons panel.
    JButton remove_file_button = new JButton("Remove File");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    buttons_panel.add(remove_file_button, c);
    
    // Event Handler for Remove File Button
    remove_file_button.addActionListener(new AdminWindowRemoveFileButtonEventHandler());
    
    // Create and add the update index button to the buttons panel.
    JButton update_index_button = new JButton("Update Index");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 0;
    c.insets = new Insets(10, 100, 10, 100);
    c.anchor = GridBagConstraints.PAGE_END;
    buttons_panel.add(update_index_button, c);
    
    // Event Handler for Update Index Button
    update_index_button.addActionListener(new AdminWindowUpdateIndexButtonEventHandler());
    
// -- PAINTING MAIN WINDOW -- //
    
    // Add heading panel to main window.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    admin_window.add(heading_panel, BorderLayout.NORTH);
    
    // Add search panel to main window.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    admin_window.add(filelist_panel, BorderLayout.CENTER);
    
    // Add buttons panel to main window.
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 2;
    admin_window.add(buttons_panel, BorderLayout.SOUTH);
    
// -- END WINDOWs CREATION -- //        
    return admin_window;
        
    }
    
}
