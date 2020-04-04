package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchEngine {
	
		static public Connection con;
		static String[] columns = new String[] {"id", "fileId", "word", "location"};
		static String[] dataType = new String[] {"integer", "integer", "varchar(50)", "varchar(50)"};
		static String tableName = "theWords";
	
		//Create word index table and get connection
		public static void onStart() {
			try {
				FileDatabase.initialize(tableName, columns, dataType);
				con = FileDatabase.con;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//main access
		public static ArrayList<String> search(int searchType, String query) {		
			ArrayList<String> returnArray = new ArrayList<String>();
			final int and = 1, or = 2, exact =3;
			
			ArrayList<Integer> validIds = validFile();
			ArrayList<String> queryList = parseQuery(query);
			
			switch(searchType) {		
				case and: returnArray = andSearch(validIds,queryList);
					break;
				case or: returnArray = orSearch(validIds,queryList);
					break;		
				case exact: returnArray = exactSearch(validIds,queryList);
					break;
			}
			
			return returnArray;
		}
		
		// or search
		private static ArrayList<String> orSearch(ArrayList<Integer> validFile, ArrayList<String> query) {
			ArrayList<String> returnArray = query;	
			return returnArray;
		}
		
		// and search
		private static ArrayList<String> andSearch(ArrayList<Integer> validFile, ArrayList<String> query) {
			ArrayList<String> returnArray = query;			
			return returnArray;
		}
		
		// exact phrase search
		private static ArrayList<String> exactSearch(ArrayList<Integer> validFile, ArrayList<String> query) {
			ArrayList<String> returnArray = query;
			return returnArray;
		}
		
		//Takes the String from the search bar and breaks it into smaller strings
		private static ArrayList<String> parseQuery(String query) {
			ArrayList<String> queryList = new ArrayList<String>();
			
			queryList.add("It's");
			queryList.add("Always");
			queryList.add("Picachu");
			queryList.add("!!!");
			
			return queryList;
		}
		
		// returns list of id's to valid files
		private static ArrayList<Integer> validFile(){
			ArrayList<Integer> validIds = new ArrayList<Integer>();
			
			validIds.add(1);
			validIds.add(2);
			validIds.add(3);
			validIds.add(4);
			
			return validIds;
		}

}
