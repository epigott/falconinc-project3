package falconinc.src.project_3;

/* Written by Akanksha Chavan */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
//import com.jgoodies.forms.layout.FormLayout;	Java problem, in Eclipse when copying folder over?
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class guiWinApp {

	private JFrame frame;
	private JTextField textField;
	private JButton btnAdmin;
	private JButton btnAbout;

	/**
	 * Launch the application. Main app for GUI
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiWinApp window = new guiWinApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public guiWinApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { // GUI Window background color, location
		frame =new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{323, 85, 0};
		gridBagLayout.rowHeights = new int[]{65, 29, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnSearch = new JButton("Search");	// GUI Button
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Searching..."); //Message Dialogue Box
			}
		});
		
		textField = new JTextField(); // Text box to add search files index
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		btnSearch.setForeground(Color.BLUE); 	// Color of text/font
		btnSearch.setBackground(Color.GRAY);	// Color of background button
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 1;
		frame.getContentPane().add(btnSearch, gbc_btnSearch);
		
		btnAdmin = new JButton("Admin/Maintainance"); // GUI Button to view Admin/Maintainance View
		GridBagConstraints gbc_btnAdmin = new GridBagConstraints();
		gbc_btnAdmin.anchor = GridBagConstraints.SOUTH;
		gbc_btnAdmin.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdmin.gridx = 0;
		gbc_btnAdmin.gridy = 7;
		frame.getContentPane().add(btnAdmin, gbc_btnAdmin);
		
		btnAbout = new JButton("About"); // GUI Button to view About Section
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Project 3 Solution \nWritten by Akanksha Chavan \n Tampa, FL");
			}
		});
		GridBagConstraints gbc_btnAbout = new GridBagConstraints(); // Panel to exit out of window
		gbc_btnAbout.anchor = GridBagConstraints.SOUTH;
		gbc_btnAbout.gridx = 1;
		gbc_btnAbout.gridy = 7;
		frame.getContentPane().add(btnAbout, gbc_btnAbout);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}