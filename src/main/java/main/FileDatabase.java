package main;

import java.sql.*;
import java.util.*;
import javax.swing.*; // swing imported for JOptionPane messages in addFile()
import java.io.File;  // for code in addFile() 


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

        // Inserts file into database
	static public void addFile(String[] fileInfo) throws SQLException { 
		// Written by R. Spangler
    
                    try{
                        // UIManager gives fileChooser the look and feel of the  users system. 
                        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                            
                    // created fileChooser to navigate file system
                    JFileChooser fileChooser = new JFileChooser();
                    int status = fileChooser.showOpenDialog( null );
                    if ( status == JFileChooser.APPROVE_OPTION ) {
                        File addedFile = fileChooser.getSelectedFile();     
                        
                        // var to store the filepath of selected file. 
                        String filePath = addedFile.getParent() + "\\" +
                                addedFile.getName();
                                
                        // prints out file added as a message for testing
                        JOptionPane.showMessageDialog(null,"Added File: " + 
                                filePath );

                                
                        // create new record
                        String newRec = "INSERT INTO "+ tableName +
                                " (fileName, dateModified) VALUES('" + filePath +"', date('now'))";
  
                        Statement state;
                        try {
                            state = con.createStatement();
                            state.execute(newRec);
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                                
                    }
                            
                           
	}	
	

	static public String[][] getDatabase() throws SQLException {
		// Written by B.Cloud
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
		// Written by E. Pigott
		String[] returnArray = {"4","Pokemon\\Pikachu.mon"};
		
		return returnArray;
	 }	
	
	
	static public void deleteRow(String primaryKey) throws SQLException {
		// Written by A.Chavan	
		// Source help: https://www.boraji.com/jdbc-delete-record-example
		// Secondary source help: https://www.sqlitetutorial.net/sqlite-java/delete/
		
		// set variables to select file to delete, from table---make connection to table being used
		// using id #, filename or date of file created
		
		String sql = "DELETE FROM " + tableName + " WHERE id = " + primaryKey;

		// create table connection with statement variable
		    try (Statement stmt = con.createStatement();) 
		    {
		// create the execute statement for deleting the row from table
		      stmt.executeUpdate(sql);
		      System.out.println("Record deleted successfully");
		    } 
		// catch exception if record not deleted successfully    
		    catch (SQLException e) 
		    {	
		      System.out.println ("Record was not deleted successfully! Try again!");
		      System.out.println(e.getMessage());
		    }
		
	}
	
}
