package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.FileDatabase;

public class TestMain {

	public static void main(String[] args) {
		FileDatabase pokebase;
		
		try {
			String[] columns = new String[] {"id", "pokemonName" , "type"};
			String[] dataType = new String[] {"integer", "varchar(50)", "varchar(50)"};
			String  tableName = "pokemon";
			pokebase = new FileDatabase(tableName, columns, dataType);
		
			System.out.println("-----------getColumnList Test----------------------");
			String[] columnList = pokebase.getColumnList();
			for(int x = 0; x < columnList.length; ++x) {
				System.out.println(columnList[x]);
			}				
			
			//Gets full Database
			System.out.println("------------getDatabase Test-----------");
			List<List<String>> fullDataTest = pokebase.getDatabase();
			for (int x = 0; x < fullDataTest.size(); ++x)
			{
			    for (int y = 0; y < fullDataTest.get(x).size(); ++y)
			    {
			    	System.out.print(fullDataTest.get(x).get(y) + " ");
			    } 
			    System.out.println("");
			}
			
			/*	String[][] fullDataTest = pokebase.getDatabase();
			for(int x = 0; x < fullDataTest.length; ++x ) {
				for(int y = 0; y < fullDataTest[0].length; ++y ) {
					System.out.print(fullDataTest[x][y] + " ") ;
				}
				System.out.println("");
			}	*/
			
			//Gets only the file name at that id
			//if using the pokemon method only values 1 to 151 are valid
			System.out.println("------------getRow test-----------");
			
			ArrayList<String> rowTest;
			for(int x = 1; x <= 10; ++x) {
				if (!pokebase.validKey(x))
					continue;
				 rowTest = pokebase.getRow(x);
				System.out.println(rowTest.get(0) + " " + rowTest.get(1));
			}
			
			System.out.println("------------getColumn test-----------");
			
			ArrayList<String> columnTest = pokebase.getColumn(1);
			for(int x = 0; x < 10; ++x) {
				System.out.println(columnTest.get(x));
			}
			
			columnTest = pokebase.getColumn(0);
			for(int x = 0; x < 10; ++x) {
				System.out.println(columnTest.get(x));
			}
			
			System.out.println("------------update and delete test -----------");
			//to test must run program twice cause it will throw an exception
			pokebase.editData(2, columns[2], "fire");
			pokebase.deleteRow(3);
			
			System.out.println("------------sqlQuery test-----------");
		  	ResultSet rs;
		    rs = pokebase.sqlQuery("SELECT * FROM " + tableName);
			while(rs.next()) {
					System.out.println(rs.getInt(columns[0]) + " " + rs.getString(columns[1]));		
			}	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}				
	}
}
