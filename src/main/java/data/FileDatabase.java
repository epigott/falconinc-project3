package data;

import java.sql.*;
import java.util.*;

public class FileDatabase {
	static private Connection con;

	public static void getConnection() throws ClassNotFoundException, SQLException {		
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:TheFilePile.db");
		initialize();		
	}


	static private void initialize() throws SQLException {	
		
	}


	static public void addFile(String[] fileInfo) throws SQLException {
		
	}	
	

	static public String[][] getDatabase() throws SQLException {
		String[][] returnArray = new String[10][3];
		return returnArray;
	}
	
	
	static public String[] getRow(int primaryKey) throws SQLException, IllegalArgumentException{

		String[] returnArray = new String[10];
		
		return returnArray;
	 }	
	
	
	static public void deleteRow(int primaryKey) throws SQLException {
	
	}
	
}
