package data;

import java.sql.*;
import java.util.*;

public class FileDatabase {
	static private Connection con;
	static String[] columns;
	static String[] dataType;
	static String tableName;

	public static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:TheFilePile.db");
		initialize();		
	}


	static private void initialize() throws SQLException {	
		columns = new String[] {"id", "fileLocation"};
		dataType = new String[] {"integer", "varchar(50)"};
		tableName = "thePile";
		
		/*SQLite uses a database to store its databases called sqlite_master.
		 * Here where looking in that database to find thePile.
		 */
		//"SELECT name FROM sqlite_master WHERE type='table' AND name='thePile'"
		String sql = ("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(sql);
		
		//If the ResultSet comes back empty we create a table.
		if( !res.next()){				
			//sql CREATE TABLE thePile(id integer, fileName varchar(50), primary key(id);
			sql = "CREATE TABLE " + tableName + "(";
			for(int x = 0 ; x < columns.length ; ++x) {				
				sql += " ";
				sql += columns[x];
				sql += " ";
				sql +=  dataType[x] + ",";
			}
			sql += " primary key(" + columns[0] + "));";
			
			Statement state2 = con.createStatement();
			state2.execute(sql);
		}
		
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
