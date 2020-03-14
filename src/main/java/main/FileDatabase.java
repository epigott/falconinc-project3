package data;

import java.sql.*;
import java.util.*;

public class FileDatabase {
	static private Connection con;
	static final String[] columns = new String[] {"id", "fileName", "dateModified"};
	static final String[] dataType = new String[] {"integer", "varchar(50)", "date"};
	static final String tableName= "thePile";

	public static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:TheFilePile.db");
		initialize();		
	}


	static private void initialize() throws SQLException {	
		
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

		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT * FROM " + tableName);
		Statement state2 = con.createStatement();
		ResultSet resCount = state2.executeQuery("SELECT COUNT (*) FROM " + tableName);	
		
		int rowCount = 0;
		if (resCount.next()) {
		  rowCount = resCount.getInt(1);
		}
		
		String[][] returnArray = new String[rowCount][columns.length];
		for(int x = 0; x < rowCount ; ++x) {	
			res.next();
			for(int y = 0; y < columns.length; ++y ) {
				returnArray[x][y] = (res.getString(columns[y]));
			}
		}
		return returnArray;
	}
	
	
	static public String[] getRow(int primaryKey) throws SQLException, IllegalArgumentException{

		String[] returnArray = {"4","Pokemon\\Pikachu.mon"};
		
		return returnArray;
	 }	
	
	
	static public void deleteRow(int primaryKey) throws SQLException {
	
	}
	
}
