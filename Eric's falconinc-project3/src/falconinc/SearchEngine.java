/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * NOTE: Code is not considered 'clean', 'tidy', or 'complete', until explicitly indicated here or submitted as final code.
 */
package falconinc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author epigott
 */
public class SearchEngine {
    
    public static void main (String[] args) {
    
    JFrame sewindow;
    
    sewindow = new JFrame("Search Engine - Falcon INC");
    
    // Will prevent the system process created by running in IDE from hanging when closed.
    sewindow.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing( WindowEvent we ) {
           System.out.println("Have A Good Day!");
           System.exit(0);
           }
        }
      );
    
    
    sewindow.setSize(800, 600);
    
    // Create and add a Text Heading.
    JLabel heading = new JLabel("Falcon INC Search Engine", JLabel.CENTER);
    // Font heading_font = new Font(); // In Progress;
    sewindow.add(heading);
    
    sewindow.setVisible(true);
    
    }
        
}