package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

public class Main extends JFrame {

	public static void main(String[] args) {
		
		try {
			FileDatabase.getConnection();
			SearchEngine.onStart();
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
String searchQueary;
String[] fileInfo;
JTable fileList;
DefaultTableModel model;
JRadioButton andSearch, orSearch ,exactSearch, andSearch_h, orSearch_h ,exactSearch_h;
CardLayout cl_centerPane, cl_prime;
ImageIcon icon;
int searchType;
	
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
 	
		//Creating SubPanels
	 	p_homeBot = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
	 	p_homeCenter = new JPanel(new GridBagLayout()); 	
	 	p_homeCenterTop = new JPanel(); 	
	 	p_homeCenterMid= new JPanel();	
	 	p_homeCenterBot = new JPanel();	
		p_home.add(p_homeBot, BorderLayout.PAGE_END);
		p_home.add(p_homeCenter, BorderLayout.CENTER);	
		
		//Buttons
		b_search_h = createSearchButton();
		b_admin_h =createAdminButton();
			 	
	 	//RadioButtons
		ButtonGroup searchOption = new ButtonGroup();	
		andSearch_h = createAndRadioGroup();
		orSearch_h = createOrRadioGroup();
		exactSearch_h = createExactRadioGroup();
		searchOption.add(andSearch_h);
		searchOption.add(orSearch_h);
		searchOption.add(exactSearch_h);	 	

		//SerchBar and Title
		tf_searchBar_h = new JTextField(20);
		JLabel l_banner = new JLabel("Falcon Engine");	
		Font font = new Font("SanSerif" , Font.PLAIN , 30);
		l_banner.setFont(font);
		
		//Page Color
		Color pageColor = Color.lightGray;
	 	p_homeBot.setBackground(pageColor); 
	 	p_homeCenter.setBackground(pageColor);
	 	p_homeCenterTop.setBackground(pageColor);	
	 	p_homeCenterMid.setBackground(pageColor); 
	 	p_homeCenterBot.setBackground(pageColor);
		andSearch_h.setBackground(pageColor);
		orSearch_h.setBackground(pageColor);
		exactSearch_h.setBackground(pageColor);
		
		//Placing on page
	 	GridBagConstraints gbc = new GridBagConstraints();	
		gbc.gridx = 0;
		gbc.gridy = 0;
		p_homeCenter.add(p_homeCenterTop, gbc);
		gbc.gridy = 1;
		p_homeCenter.add(p_homeCenterMid, gbc);
		gbc.gridy = 2;
		p_homeCenter.add(p_homeCenterBot, gbc);
		
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
		
		//Create SubPanes
		addBarPane();
		addSearchPane();
		addAdminPane();	
		
		//Creating card Layout for center of BorderLayout
		cl_centerPane = new CardLayout();
	 	p_centerPanel = new JPanel(cl_centerPane);
		p_main.add(p_centerPanel, BorderLayout.CENTER);	
		
		//adding bar to top of page
		p_main.add(p_bar, BorderLayout.PAGE_START);
		
		//Page Color
		Color pageColor = Color.white;
		p_main.setBackground(pageColor);	
	 	p_centerPanel.setBackground(pageColor);
		
		//adding to center layout
		p_centerPanel.add(p_searchResults, "search");	
		p_centerPanel.add(p_admin, "admin");
	}

	private void addBarPane(){
		p_bar = new JPanel(new BorderLayout());
		
		//Initializing Fields
		p_barTop = new JPanel();
		p_barBot = new JPanel();
		
		//Adding SubPanels
		p_bar.add(p_barTop, BorderLayout.PAGE_START);
		p_bar.add(p_barBot, BorderLayout.PAGE_END);
			
		//Buttons
		b_home = createHomeButton();
		b_search = createSearchButton();		
		b_admin = createAdminButton();
		
		//RadioButtons
		ButtonGroup searchOption = new ButtonGroup();			
		andSearch = createAndRadioGroup();
		orSearch = createOrRadioGroup();
		exactSearch = createExactRadioGroup();		
		searchOption.add(andSearch);
		searchOption.add(orSearch);
		searchOption.add(exactSearch);
		
		//SearchBar and Title
		tf_searchBar_b = new JTextField(20);	
		JLabel l_banner = new JLabel("Falcon Engine");	
		
		//Setting BG Color 
		Color barColor = Color.lightGray;
		p_barTop.setBackground(barColor);
		p_barBot.setBackground(barColor);
		andSearch.setBackground(barColor);
		orSearch.setBackground(barColor);
		exactSearch.setBackground(barColor);	
	
		//adding to page
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
		p_adminTop = new JPanel();
		p_adminCenter = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p_adminBot = new JPanel();
		
		//Table
		fileList = createFileTable();
		p_adminCenter.setViewportView(fileList); 
		
		//Buttons
		b_add = createAddButton();		
		b_delete = createDeleteButton();
		
		//Title
		JLabel l_banner_a = new JLabel("File List");
		
		//Page Color
		Color adminColor = Color.white;
		p_adminCenter.getViewport().setBackground(adminColor);
		p_adminTop.setBackground(adminColor);
        p_admin.setBackground(adminColor);
		p_adminBot.setBackground(adminColor);	 
		
		//adding to page
		p_adminTop.add(l_banner_a);		
		
		p_adminBot.add(b_add);
		p_adminBot.add(b_delete);
		
		p_admin.add(p_adminTop, BorderLayout.PAGE_START);
		p_admin.add(p_adminCenter, BorderLayout.CENTER);
		p_admin.add(p_adminBot, BorderLayout.PAGE_END);
	}

	private JTable createFileTable() {
		JTable table = new JTable();
		
		model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Name");
		model.addColumn("date");
		table.setModel(model);
		updateTable(model);
		
		return table;
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
	
	private JButton createHomeButton() {
		JButton button = new JButton();
		FontIcon homeIcon = FontIcon.of(MaterialDesign.MDI_HOME_OUTLINE);
		button.setIcon(homeIcon);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tf_searchBar_h.setText("");
				tf_searchBar_b.setText("");
				cl_prime.show(p_prime, "home");				
				}					
		});			
		return button;
	}
	
	private JButton createSearchButton() {
		JButton button = new JButton();
		FontIcon searchIcon = FontIcon.of(MaterialDesign.MDI_MAGNIFY);
		button.setIcon(searchIcon);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
						ArrayList<String> searchReturn;
						final int and = 1, or = 2, exact =3;
				
						//Do This only If using the home page search			
						if (!tf_searchBar_h.getText().equals("")) {
							tf_searchBar_b.setText(tf_searchBar_h.getText());
							tf_searchBar_h.setText("");
						}
						
						//Always do this. Gets search type.
						searchQueary = tf_searchBar_b.getText();					
						if(andSearch.isSelected()) {
							searchType = and;
						}
						else if(orSearch.isSelected()){
							searchType = or;;
						}
						else if(exactSearch.isSelected()){
							searchType = exact;
						}		
						
					searchReturn = SearchEngine.search(searchType,searchQueary);
					//Display Results
					//-----------------test code--------------------
					String resultDisplay = "<html>";
					
					for (int x = 0; x < searchReturn.size(); ++x)
					{
					    resultDisplay += (searchReturn.get(x) + "<br/>");					    
					}
					resultDisplay += "</html>";
					
					l_searchResults.setText(resultDisplay);
					//----------------------------------------------
					
					cl_prime.show(p_prime, "main");
					cl_centerPane.show(p_centerPanel, "search");
				}					
		});	
		return button;
	}
	
	private JButton createAdminButton() {
		JButton button = new JButton();
		FontIcon adminIcon = FontIcon.of(MaterialDesign.MDI_FORMAT_LIST_BULLETED);
		button.setIcon(adminIcon);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateTable(model);
				cl_prime.show(p_prime, "main");
				cl_centerPane.show(p_centerPanel, "admin");
				}					
		});			
		return button;
	}
	
	private JButton createAddButton() {
		JButton button = new JButton();
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
		return button;
	}
	// Delete any file according to database store
	private JButton createDeleteButton() {
		JButton button = new JButton();
		button.setText("Remove");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index = fileList.getSelectedRow();
				// If you don't have a row selected index equals -1
				if(index != -1) {
					String id = (model.getValueAt(index, 0).toString());
					System.out.println(id);
				// Added by A.Chavan
				try {
				//	Call the deleteRow method from FileDatabase class
					FileDatabase.deleteRow(id);	// index refers to specific file to delete per ID
					updateTable(model);						
				}
				catch
				 (IllegalArgumentException | SQLException e1) {
					// catch any exceptions that may cause an illegal argument 
					e1.printStackTrace();
					}
				}
			}
		});			
		return button;
	}
	
	private JRadioButton createExactRadioGroup() {
		JRadioButton exactButton = new JRadioButton("exact" , false);
		exactButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exactSearch_h.setSelected(true);
				exactSearch.setSelected(true);
			}
		});			
		return exactButton;
	}

	private JRadioButton createOrRadioGroup() {
		JRadioButton orButton = new JRadioButton("or" , false);
		orButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				orSearch_h.setSelected(true);
				orSearch.setSelected(true);
			}
		});			
		return orButton;
	}

	private JRadioButton createAndRadioGroup() {
		JRadioButton andButton = new JRadioButton("and" , true);
		andButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				andSearch_h.setSelected(true);
				andSearch.setSelected(true);
			}
		});			
		return andButton;
	}
}
