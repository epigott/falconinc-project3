package data;

import java.sql.*;
import java.util.*;

public class FileDatabase {
	private Connection con;

	public FileDatabase() throws ClassNotFoundException, SQLException {	
		
			getConnection();
	}
	

	private void getConnection() throws ClassNotFoundException, SQLException {		
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:TheFilePile.db");
		initialize();		
	}


	private void initialize() throws SQLException {	
		
	}


	public void addFile(String[] fileInfo) throws SQLException {
		
	}	
	

	public String[][] getDatabase() throws SQLException {
		String[][] returnArray = new String[10][3];
		return returnArray;
	}
	
	
	public String[] getRow(int primaryKey) throws SQLException, IllegalArgumentException{

		String[] returnArray = new String[10];
		
		return returnArray;
	 }	
	
	
	public void deleteRow(int primaryKey) throws SQLException {
	
	}
	
}
