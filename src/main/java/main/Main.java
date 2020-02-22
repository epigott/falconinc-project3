package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame implements ActionListener{
	
	static JButton b_home, b_search , b_admin;
	static JPanel p_main, p_bar, p_centerPanel, p_home, p_search, p_admin, p_adminTop, p_adminCenter, p_adminBot ;
	static JTextField tf_searchBar;
	static JLabel l_searchResults;
	static String searchQueary, searchReturn;
	static String[] l_fileInfo;
	static JTable fileList;
	static DefaultTableModel model;
	static JRadioButton andSearch, orSearch ,exactSearch;
	static CardLayout cl = new CardLayout();


	public static void main(String[] args) {		
		Main ui = new Main();    
		ui.setVisible(true);
	}
	
	public Main(){
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Falcon Engine");
		setBackground(Color.white);
		setResizable(true);	

		p_main = new JPanel(new BorderLayout());
		p_main.setBackground(Color.white);
		p_main.setVisible(true);
		
	 	p_centerPanel = new JPanel(cl);
	 	p_centerPanel.setBackground(Color.white);
		p_main.add(p_centerPanel, BorderLayout.CENTER);
	 	
	 	p_home = new JPanel();
	 	p_home.setBackground(Color.white);
	 	p_home.setVisible(true);
	 	p_centerPanel.add(p_home, "home");
	 	cl.show(p_centerPanel, "home");

	 	
		add(p_main);
		
		addBarPane();
		addSearchPane();
		addAdminPane();	
		
		p_admin.setVisible(false);
		p_search.setVisible(false);
		p_bar.setVisible(true);
		
	}
	
	private void addBarPane(){
		p_bar = new JPanel( new FlowLayout());	
		p_bar.setBackground(Color.white);		
		
		b_home = new JButton("Home");
		b_home.addActionListener(this);
		
		b_search = new JButton("Search");
		b_search.addActionListener(this);
		
		b_admin = new JButton("Admin");
		b_admin.addActionListener(this);
		
		ButtonGroup searchOption = new ButtonGroup();
		
		andSearch = new JRadioButton("and" , true);
		orSearch = new JRadioButton("or" , false);
		exactSearch = new JRadioButton("exact" , false);
		searchOption.add(andSearch);
		searchOption.add(orSearch);
		searchOption.add(exactSearch);

		p_bar.add(b_home);
		
		tf_searchBar = new JTextField(20);
		
		JLabel l_banner = new JLabel("Falcon Engine");

		p_bar.add(l_banner);			

		p_bar.add(tf_searchBar);		

		p_bar.add(b_search);
		
		p_bar.add(b_admin);
		
		p_bar.add(andSearch);
		p_bar.add(orSearch);
		p_bar.add(exactSearch);
		
		p_main.add(p_bar, BorderLayout.PAGE_START);	
	}

	private void addSearchPane(){
		p_search = new JPanel();
		p_search.setBackground(Color.white);
		
		l_searchResults = new JLabel("test");
		
		p_search.add(l_searchResults);
		
		p_search.setVisible(true);

		p_centerPanel.add(p_search, "search");	
	}
	
	private void addAdminPane(){
		p_admin = new JPanel(new BorderLayout());
        p_admin.setBackground(Color.white);
		p_adminTop = new JPanel(new GridLayout());
		p_adminTop.setBackground(Color.white);
		p_adminCenter = new JPanel();
		p_adminCenter.setBackground(Color.white);
		p_adminBot = new JPanel();
		p_adminBot.setBackground(Color.white);
        						
		JLabel l_banner_a = new JLabel("admin");
		
		 l_fileInfo= new String[2];
		 model = new DefaultTableModel();
		 
		 fileList = new JTable();
		 fileList.setModel(model);
		 //----------------test code---------------------
		 model.addColumn("Name");
		 model.addColumn("Type");
		 
		 l_fileInfo[0] = ("Bulbasasaur");
		 l_fileInfo[1] = ("Grass");
		 model.addRow(l_fileInfo);
		 l_fileInfo[0] = ("Charmander");
		 l_fileInfo[1] = ("Fire");
		 model.addRow(l_fileInfo);
		 l_fileInfo[0] = ("Squirtle");
		 l_fileInfo[1] = ("Water");		 
		 model.addRow(l_fileInfo);
		 //----------------------------------------------=
		
		 
		JButton b_add = new JButton("add");
		b_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				l_fileInfo[0] = ("Pikachu");
				l_fileInfo[1] =("Electric");
				model.addRow(l_fileInfo);
			}
		});		
				
		
		JButton b_delete = new JButton("delete");
		b_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index = fileList.getSelectedRow();
				if(index != -1) {
				model.removeRow(index);		
				}
			}
		});		
		
		p_adminTop.add(l_banner_a);
		
		p_adminCenter.add(fileList);
		
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
		cl.show(p_centerPanel, "home");
		}
		//Search Button
		else if (e.getSource() == b_search) {			
			
			searchQueary = tf_searchBar.getText();
			
			if(andSearch.isSelected()) {
				searchReturn = ("and " + searchQueary);
			}
			else if(orSearch.isSelected()){
				searchReturn = ("or " + searchQueary);
			}
			else if(exactSearch.isSelected() ){
				searchReturn = ("exact " + searchQueary);
			}
			
			l_searchResults.setText(searchReturn);
			
			tf_searchBar.setText("");
			
			cl.show(p_centerPanel, "search");
		}
		//Admin Button
		else if (e.getSource() == b_admin) {			
			cl.show(p_centerPanel, "admin");
		} 		
	} 
}
