package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.FileDatabase;
import collection.FileCollector;
import collection.SearchEngine;

import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

public class Main extends JFrame implements ActionListener{
	

	public static void main(String[] args) {
		try {
			FileDatabase.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main ui = new Main();    
		ui.setVisible(true);
	}

JButton b_home, b_search , b_search_h , b_admin, b_admin_h;
JPanel  p_prime, p_home, p_main, p_bar, p_barTop, p_barBot, p_centerPanel, p_searchResults, p_admin, p_adminTop, p_adminBot ,
		p_homeBot,  p_homeCenter, p_homeCenterTop, p_homeCenterMid, p_homeCenterBot; 
JScrollPane  p_adminCenter;
JTextField tf_searchBar_b, tf_searchBar_h;
JLabel l_searchResults;
String searchQueary, searchReturn;
String[] fileInfo;
JTable fileList;
DefaultTableModel model;
JRadioButton andSearch, orSearch ,exactSearch, andSearch_h, orSearch_h ,exactSearch_h;
CardLayout cl_centerPane, cl_prime;

	
	public Main(){
		setSize(550, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Falcon Engine");
		setBackground(Color.white);
		setLocationRelativeTo(null);
		setResizable(true);				
	
		cl_prime = new CardLayout();
		p_prime = new JPanel(cl_prime);
		p_prime.setVisible(true);
		add(p_prime);
		
		addHomePane();
	 	p_prime.add(p_home, "home");
		
		p_main = new JPanel(new BorderLayout());
		p_main.setBackground(Color.white);
		p_main.setVisible(true);				
	 	p_prime.add(p_main, "main");

	 	cl_prime.show(p_prime, "home");

		addBarPane();
		cl_centerPane = new CardLayout();
	 	p_centerPanel = new JPanel(cl_centerPane);
	 	p_centerPanel.setBackground(Color.white);
		p_main.add(p_centerPanel, BorderLayout.CENTER);	 
		
		addSearchPane();
		addAdminPane();	
		
		p_admin.setVisible(false);
		p_searchResults.setVisible(false);
		p_bar.setVisible(true);
		
	}
	
	private void addHomePane() {
	 	p_home = new JPanel(new BorderLayout());
		Color pageColor = Color.lightGray;
	 	p_home.setVisible(true);
	 	
	 	p_homeBot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	 	p_homeBot.setBackground(pageColor);
	 	
	 	p_homeCenter = new JPanel(new GridBagLayout());
	 	p_homeCenter.setBackground(pageColor);
	 	GridBagConstraints gbc = new GridBagConstraints();
	 	
	 	p_homeCenterTop = new JPanel();
	 	p_homeCenterTop.setBackground(pageColor);
	 	
	 	p_homeCenterMid= new JPanel();
	 	p_homeCenterMid.setBackground(pageColor);
	 	
	 	p_homeCenterBot = new JPanel();
	 	p_homeCenterBot.setBackground(pageColor);
	 	
		p_home.add(p_homeBot, BorderLayout.PAGE_END);
		p_home.add(p_homeCenter, BorderLayout.CENTER);	
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		p_homeCenter.add(p_homeCenterTop, gbc);
		gbc.gridy = 1;
		p_homeCenter.add(p_homeCenterMid, gbc);
		gbc.gridy = 2;
		p_homeCenter.add(p_homeCenterBot, gbc);

		
		FontIcon searchIcon = FontIcon.of(MaterialDesign.MDI_MAGNIFY);
		b_search_h = new JButton(searchIcon);
		b_search_h.addActionListener(this);	
		
		FontIcon adminIcon = FontIcon.of(MaterialDesign.MDI_FORMAT_LIST_BULLETED);
		b_admin_h = new JButton(adminIcon);
		b_admin_h.addActionListener(this);
		
		JLabel l_banner = new JLabel("Falcon Engine");	
		Font font = new Font("SanSerif" , Font.PLAIN , 30);
		l_banner.setFont(font);
		
		tf_searchBar_h = new JTextField(20);
		
		ButtonGroup searchOption = new ButtonGroup();	
		andSearch_h = new JRadioButton("and" , true);
		andSearch_h.setBackground(pageColor);
		orSearch_h = new JRadioButton("or" , false);
		orSearch_h.setBackground(pageColor);
		exactSearch_h = new JRadioButton("exact" , false);
		exactSearch_h.setBackground(pageColor);
		searchOption.add(andSearch_h);
		searchOption.add(orSearch_h);
		searchOption.add(exactSearch_h);
		
		p_homeBot.add(b_admin_h);
		
		p_homeCenterTop.add(l_banner);
	 	
		p_homeCenterMid.add(tf_searchBar_h);
		p_homeCenterMid.add(b_search_h);
			
		p_homeCenterBot.add(andSearch_h);
		p_homeCenterBot.add(orSearch_h);
		p_homeCenterBot.add(exactSearch_h);
		

	}
	
	private void addBarPane(){
		p_bar = new JPanel(new BorderLayout());
		p_barTop = new JPanel();
		p_barBot = new JPanel();
		p_bar.add(p_barTop, BorderLayout.PAGE_START);
		p_bar.add(p_barBot, BorderLayout.PAGE_END);
		
		Color barColor = Color.lightGray;
		p_barTop.setBackground(barColor);
		p_barBot.setBackground(barColor);
		
		FontIcon homeIcon = FontIcon.of(MaterialDesign.MDI_HOME_OUTLINE);
		b_home = new JButton(homeIcon);
		b_home.addActionListener(this);		
		
		FontIcon searchIcon = FontIcon.of(MaterialDesign.MDI_MAGNIFY);
		b_search = new JButton(searchIcon);
		b_search.addActionListener(this);	
		
		FontIcon adminIcon = FontIcon.of(MaterialDesign.MDI_FORMAT_LIST_BULLETED);
		b_admin = new JButton(adminIcon);
		b_admin.addActionListener(this);
		
		ButtonGroup searchOption = new ButtonGroup();
		
		andSearch = new JRadioButton("and" , true);
		andSearch.setBackground(barColor);
		orSearch = new JRadioButton("or" , false);
		orSearch.setBackground(barColor);
		exactSearch = new JRadioButton("exact" , false);
		exactSearch.setBackground(barColor);
		searchOption.add(andSearch);
		searchOption.add(orSearch);
		searchOption.add(exactSearch);	
		
		tf_searchBar_b = new JTextField(20);	
		JLabel l_banner = new JLabel("Falcon Engine");	
		
		p_barTop.add(b_home);
		p_barTop.add(l_banner);			
		p_barTop.add(tf_searchBar_b);		
		p_barTop.add(b_search);
		p_barTop.add(b_admin);	

		p_barBot.add(andSearch);
		p_barBot.add(orSearch);
		p_barBot.add(exactSearch);
		
		p_main.add(p_bar, BorderLayout.PAGE_START);	
	}

	private void addSearchPane(){
		p_searchResults = new JPanel();
		p_searchResults.setBackground(Color.white);
		
		l_searchResults = new JLabel("test");
		
		p_searchResults.add(l_searchResults);
		
		p_searchResults.setVisible(true);

		p_centerPanel.add(p_searchResults, "search");	
	}
	
	private void addAdminPane(){
		p_admin = new JPanel(new BorderLayout());
        p_admin.setBackground(Color.white);
		p_adminTop = new JPanel();
		p_adminTop.setBackground(Color.white);
		p_adminCenter = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p_adminCenter.getViewport().setBackground(Color.white);
		p_adminBot = new JPanel();
		p_adminBot.setBackground(Color.LIGHT_GRAY);
        						
		JLabel l_banner_a = new JLabel("File List");
		
		 fileInfo= new String[2];
		 model = new DefaultTableModel();
		 
		 fileList = new JTable();
		 fileList.setModel(model);
		 //----------------test code---------------------
		 model.addColumn("id");
		 model.addColumn("Name");
		 
		 fileInfo[0] = ("1");
		 fileInfo[1] = ("Bulbasasaur");
		 model.addRow(fileInfo);
		 fileInfo[0] = ("2");
		 fileInfo[1] = ("Charmander");
		 model.addRow(fileInfo);
		 fileInfo[0] = ("3");
		 fileInfo[1] = ("Squirtle");
		 model.addRow(fileInfo);
		 //----------------------------------------------=
		
		JButton b_add = new JButton("Add");
		b_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				try {
					FileCollector.addFile();
					model.addRow(FileDatabase.getRow(1));
					
				} catch (IllegalArgumentException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
				
		JButton b_delete = new JButton("Remove");
		b_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index = fileList.getSelectedRow();
				if(index != -1) {
				model.removeRow(index);		
				}
			}
		});		
		
		p_adminTop.add(l_banner_a);		

		p_adminCenter.setViewportView(fileList);
		
		p_adminBot.add(b_add);
		p_adminBot.add(b_delete);
		
		p_admin.add(p_adminTop, BorderLayout.PAGE_START);
		p_admin.add(p_adminCenter, BorderLayout.CENTER);
		p_admin.add(p_adminBot, BorderLayout.PAGE_END);
		
		p_admin.setVisible(true);
		
		p_centerPanel.add(p_admin, "admin");
	}
	
	public void actionPerformed(ActionEvent e){
		//Home Button
		if (e.getSource() == b_home ) {
			tf_searchBar_h.setText("");
			cl_prime.show(p_prime, "home");
		}
		//Search Button
		else if (e.getSource() == b_search || e.getSource() == b_search_h ) {			
			
			
			if(e.getSource() == b_search_h) {	
				tf_searchBar_b.setText(tf_searchBar_h.getText());	
				
				if(andSearch_h.isSelected()) {
					andSearch.setSelected(true);
				}
				else if(orSearch_h.isSelected()){
					orSearch.setSelected(true);
				}
				else if(exactSearch_h.isSelected() ){
					exactSearch.setSelected(true);
				}		
			}			
					
				searchQueary = tf_searchBar_b.getText();
				
				if(andSearch.isSelected()) {
					searchReturn = SearchEngine.andSearch(searchQueary);
				}
				else if(orSearch.isSelected()){
					searchReturn = SearchEngine.orSearch(searchQueary);
				}
				else if(exactSearch.isSelected() ){
					searchReturn = SearchEngine.exactSearch(searchQueary);
				}			
			
			l_searchResults.setText(searchReturn);
					
			cl_prime.show(p_prime, "main");
			cl_centerPane.show(p_centerPanel, "search");
		}
		//Admin Button
		else if (e.getSource() == b_admin  || e.getSource() == b_admin_h) {
			cl_prime.show(p_prime, "main");
			cl_centerPane.show(p_centerPanel, "admin");
		} 		
	} 
}
