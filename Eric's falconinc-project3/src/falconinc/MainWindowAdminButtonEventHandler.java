/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package falconinc;
import falconinc.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author epigott
 */
public class MainWindowAdminButtonEventHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        
      
        
        System.out.println("Opening Admin Interface");
        
        // Create admin window.
        SearchEngineAdminWindow admin_panel = new SearchEngineAdminWindow();
        JFrame admin_window = admin_panel.panel();
        
        // Set actions to perform when window is closed
        admin_window.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing( WindowEvent we ) {
             System.out.println("Exiting Admin Interface");
             }
          }
        );
        
        // Show the admin window.
        admin_window.setVisible(true);
        
    }
}
