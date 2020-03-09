package data;

import java.sql.*;
import java.util.*;
import javax.swing.*; // swing imported for testing purposes
import java.io.File;  // for test code in addFile() 


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

        // Method I'm working on
	static public void addFile(String[] fileInfo) throws SQLException { 
               
                SwingUtilities.invokeLater( new Runnable() {
                        public void run () {
                            try{
                                // UIManager gives fileChooser the look and feel of the users system. 
                                UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                            }
                            catch (Exception e){
                                JOptionPane.showMessageDialog(null, "Error with UIManager");
                            }
                            
                            // created fileChooser to navigate file system
                            JFileChooser fileChooser = new JFileChooser();
                            int status = fileChooser.showOpenDialog( null );
                            if ( status == JFileChooser.APPROVE_OPTION ) {
                                File addedFile = fileChooser.getSelectedFile();                                
                                // prints out file added as a message for testing
                                JOptionPane.showMessageDialog(null,"Added File: " 
                                        + addedFile.getParent()+ "\\" + addedFile.getName() );
                            }
                            
                        }
                } );
                           
	}	
	

	static public String[][] getDatabase() throws SQLException {
		String[][] returnArray = new String[10][3];
		return returnArray;
	}
	
	// test method?
	static public String[] getRow(int primaryKey) throws SQLException, IllegalArgumentException{

		String[] returnArray = {"4","Pokemon\\Pikachu.mon"};
		
		return returnArray;
	 }	
	
	
	static public void deleteRow(int primaryKey) throws SQLException {
	
	}
	
}
