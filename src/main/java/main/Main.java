package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main extends JFrame implements ActionListener{
	
	JButton b_home_h , b_search_h , b_admin_h, b_home_s , b_search_s , b_admin_s, b_home_a , b_search_a , b_admin_a;
	JPanel p_home, p_search, p_admin;
	JTextField tf_searchBar_h, tf_searchBar_s;

	public static void main(String[] args) {		
		Main ui = new Main();    
		ui.setVisible(true);
	}
	
	public Main(){
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Falcon Eye");
		setBackground(Color.white);
		setResizable(true);	
		setVisible(true);

		addHomePane();
		addSearchPane();
		addAdminPane();	
		
		p_admin.setVisible(false);
		p_search.setVisible(false);
		p_home.setVisible(true);
		
	}
	
	private void addHomePane(){
		p_home = new JPanel();	
		p_home.setBackground(Color.white);
		
		b_home_h = new JButton("Home");
		b_home_h.addActionListener(this);
		
		b_search_h = new JButton("Search");
		b_search_h.addActionListener(this);
		
		b_admin_h = new JButton("Admin");
		b_admin_h.addActionListener(this);
		
		tf_searchBar_h = new JTextField(20);
		
		JLabel l_banner_h = new JLabel("Falcon Eye");

		p_home.add(l_banner_h);	
		
		p_home.add(tf_searchBar_h);
		
		p_home.add(b_search_h);
		
		p_home.add(b_admin_h);
		
		add(p_home);	
		
		setVisible(true);
	}

	private void addSearchPane(){
		p_search = new JPanel();
		p_search.setBackground(Color.white);
		
		b_home_s = new JButton("Home");
		b_home_s.addActionListener(this);
		
		b_search_s = new JButton("Search");
		b_search_s.addActionListener(this);
		
		b_admin_s = new JButton("Admin");
		b_admin_s.addActionListener(this);
		
		tf_searchBar_s = new JTextField(20);
		
		JLabel l_banner_s = new JLabel("Search");

		p_search.add(b_home_s);
		
		p_search.add(l_banner_s);	
		
		p_search.add(tf_searchBar_s);
		
		p_search.add(b_search_s);
				
		add(p_search);	
		
		setVisible(true);
	}
	
	private void addAdminPane(){
		p_admin = new JPanel();	
        p_admin.setBackground(Color.white);
        
		b_home_a = new JButton("Home");
		b_home_a.addActionListener(this);
		
		b_search_a = new JButton("Search");
		b_search_a.addActionListener(this);
		
		b_admin_a = new JButton("Admin");
		b_admin_a.addActionListener(this);
		
		JLabel l_banner_a = new JLabel("admin");
		p_admin.add(l_banner_a);

		p_admin.add(b_home_a);
		
		add(p_admin);	
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == b_home_h || e.getSource() == b_home_s  || e.getSource() == b_home_a ) {
			p_home.setVisible(true);
			p_search.setVisible(false);
			p_admin.setVisible(false);
		}
		else if (e.getSource() == b_search_h|| e.getSource() == b_search_s  || e.getSource() == b_search_a ) {
			p_home.setVisible(false);
			p_search.setVisible(true);
			p_admin.setVisible(false);
		}
		else if (e.getSource() == b_admin_h|| e.getSource() == b_admin_s  || e.getSource() == b_admin_a ) {
			p_home.setVisible(false);
			p_search.setVisible(false);
			p_admin.setVisible(true);
		} 		
	} 
}
