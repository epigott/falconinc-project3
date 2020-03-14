package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

public class Main extends JFrame {

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

JButton b_home, b_search , b_search_h , b_admin, b_admin_h, b_add, b_delete;
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
ImageIcon icon;
	
	public Main(){
		//Set Window Parameters
		setSize(550, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Falcon Engine");
		setBackground(Color.white);
		setLocationRelativeTo(null);
		setResizable(true);				

		// The Primary Panel that Contains all Panels
		cl_prime = new CardLayout();
		p_prime = new JPanel(cl_prime);
		p_prime.setVisible(true);
		add(p_prime);
			
		//Creating MainPages
		addHomePane();
		addMainPane();
	 	p_prime.add(p_home, "home");	
	 	p_prime.add(p_main, "main");

	 	//Show home page
	 	cl_prime.show(p_prime, "home");	 	
	 	
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
		
		b_admin_h = new JButton();
		b_search_h = new JButton();
		createSearchButton(b_search_h);
		createAdminButton(b_admin_h);
		
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
	
	private void addMainPane() {
		p_main = new JPanel(new BorderLayout());
		p_main.setBackground(Color.white);
		p_main.setVisible(true);	
		
		//Create SubPanes
		addBarPane();
		addSearchPane();
		addAdminPane();	
		
		//Creating card Layout for center of BorderLayout
		cl_centerPane = new CardLayout();
	 	p_centerPanel = new JPanel(cl_centerPane);
	 	p_centerPanel.setBackground(Color.white);
		p_main.add(p_centerPanel, BorderLayout.CENTER);	
		
		//adding bar to top of page
		p_main.add(p_bar, BorderLayout.PAGE_START);
		
		//adding to center layout
		p_centerPanel.add(p_searchResults, "search");	
		p_centerPanel.add(p_admin, "admin");
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
		b_home = new JButton();
		b_admin = new JButton();
		b_search = new JButton();
		createHomeButton(b_home);
		createSearchButton(b_search);		
		createAdminButton(b_admin);
		
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
			
	}



	private void addSearchPane(){
		p_searchResults = new JPanel();
		p_searchResults.setBackground(Color.white);
		
		l_searchResults = new JLabel("test");
		
		p_searchResults.add(l_searchResults);
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
		 
		 model.addColumn("id");
		 model.addColumn("Name");
		 model.addColumn("date");
		 updateTable(model);
		 
		b_add = new JButton();
		b_delete = new JButton();
		createAddButton(b_add);		
		createDeleteButton(b_delete);
		
		p_adminTop.add(l_banner_a);		

		p_adminCenter.setViewportView(fileList);
		
		p_adminBot.add(b_add);
		p_adminBot.add(b_delete);
		
		p_admin.add(p_adminTop, BorderLayout.PAGE_START);
		p_admin.add(p_adminCenter, BorderLayout.CENTER);
		p_admin.add(p_adminBot, BorderLayout.PAGE_END);
	}

	private void updateTable(DefaultTableModel model) {
		
		model.setRowCount(0);
		String[][] fileInfo;
		try {
			fileInfo = FileDatabase.getDatabase() ;		
					
			for(int x = 0 ; x < fileInfo.length ; ++x) {
				String[] rowArray = new String[fileInfo[x].length];
				for(int y = 0; y < fileInfo[x].length ; ++y) {
					rowArray[y] = fileInfo[x][y];				
				}
				model.addRow(rowArray);
			}		
		 }							
		 catch (SQLException e) {
			e.printStackTrace();
		 }
	}
	
	private void createHomeButton(JButton button) {
		FontIcon homeIcon = FontIcon.of(MaterialDesign.MDI_HOME_OUTLINE);
		button.setIcon(homeIcon);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tf_searchBar_h.setText("");
				cl_prime.show(p_prime, "home");
				}					
		});			
	}
	
	private void createSearchButton(JButton button) {
		FontIcon searchIcon = FontIcon.of(MaterialDesign.MDI_MAGNIFY);
		button.setIcon(searchIcon);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
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
		});	
	}
	
	private void createAdminButton(JButton button) {
		FontIcon adminIcon = FontIcon.of(MaterialDesign.MDI_FORMAT_LIST_BULLETED);
		button.setIcon(adminIcon);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cl_prime.show(p_prime, "main");
				cl_centerPane.show(p_centerPanel, "admin");
				}					
		});			
	}
	
	private void createAddButton(JButton button) {
		button.setText("Add");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				try {
					FileDatabase.addFile();
					updateTable(model);	
					
				} catch (IllegalArgumentException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});			
	}
	
	private void createDeleteButton(JButton button) {
		button.setText("Remove");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index = fileList.getSelectedRow();
				//If you dont have a row selected index equals -1
				if(index != -1) {
				//----test code------
					String id = (model.getValueAt(index, 0).toString());
					System.out.println(id);
				//-------------------
					updateTable(model);						
				}
			}
		});			
	}
}
