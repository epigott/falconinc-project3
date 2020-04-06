package main;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SearchEngine {
	
		static public Connection con;
		static final String[] columns = new String[] {"id", "fileId", "word", "location"};
		static final String[] dataType = new String[] {"integer", "integer", "varchar(50)", "integer)"};
		static final String tableName = "theWords";
	
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
			ArrayList<String> searchResults = new ArrayList<String>();
			final int and = 1, or = 2, exact =3;
			
			//
			ArrayList<String> validIds = validFile();
			ArrayList<String> queryList = parseQuery(query);
			
			switch(searchType) {		
				case and: searchResults = andSearch(queryList);
					break;
				case or: searchResults = orSearch(queryList);
					break;		
				case exact: searchResults = exactSearch(queryList);
					break;
			}
			//--------------test-----------------------
			searchResults = new ArrayList<String>();
			for(int x = 0; x < 10; ++x) {
				searchResults.add(Integer.toString(x));
			}
			//-----------------------------------------
			
			//check that all Id's are valid ------------also to be moved to own method
			for (int x=0 , y=0; x < searchResults.size() && y < validIds.size() ;++x) {
				System.out.println(searchResults.get(x) + validIds.get(y));
				if(searchResults.get(x).equals(validIds.get(y))) {
					returnArray.add(searchResults.get(x));
					++y;
				}
				
			}
			return returnArray;
		}
		
		//
		private static ArrayList<String> orSearch(ArrayList<String> query) {
			ArrayList<String> returnArray = query;	
			return returnArray;
		}
		
		//
		private static ArrayList<String> andSearch(ArrayList<String> query) {
			ArrayList<String> returnArray = query;	
			return returnArray;
		}
		
		//
		private static ArrayList<String> exactSearch(ArrayList<String> query) {
			ArrayList<String> returnArray = query;	
			return returnArray;
		}
		
		//Takes the String from the search bar and breaks it into smaller strings
		private static ArrayList<String> parseQuery(String query) {
			ArrayList<String> queryList = new ArrayList<String>();
			
			Scanner src  = new Scanner(query);				
				while(src.hasNext() != false) {
					queryList.add(src.next());
				}
				
			src.close();
			
			//--------------------------------
			for(int x = 0; x < queryList.size(); ++x) {
				System.out.println("in query "+ queryList.get(x));
			}
			//--------------------------------

			return queryList;
		}
		
		// returns list of id's to valid files
		private static ArrayList<String> validFile(){
			ArrayList<String> validIds = new ArrayList<String>();
			String[][] fileInfo;
			
			try {
				fileInfo = FileDatabase.getDatabase() ;			
				
				//checking to see if the files still there
				for(int x = 0 ; x < fileInfo.length ; ++x) {				
					try(FileInputStream file = new FileInputStream(fileInfo[x][1])) {
					} catch (IOException e) {
						continue;
					}
					//make sure index is accurate and up to date
					validateFile(fileInfo[x][0]);	
					validIds.add(fileInfo[x][0]);			
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}					
			//--------------------------------
			for(int x = 0; x < validIds.size(); ++x) {
				System.out.println(validIds.get(x) + " Is Valid ID" );
			}
			//--------------------------------
			return validIds;
		}

		//checks if files need to be reindexed
		private static void validateFile(String id) {
			try {			
				String[] fileInfo = FileDatabase.getRow(Integer.parseInt(id));
				
				//collect dates
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				File file = new File(fileInfo[1]);
				String dateModified  = dateFormat.format(file.lastModified()); 		
				String dateIndexed = fileInfo[2];
				
				//if dateIndexed is after dateModified
				if(dateIndexed.compareTo(dateModified) < 0) {
					//reindex file
					
					
					//update date indexed, this might need to go somewhere else but i made it before i thought about it.
					//get current time
					String currentTime  = dateFormat.format(Calendar.getInstance().getTime());
					
					//UPDATE thePile SET date = 'current time' WHERE id = id
					String sql = "UPDATE " + FileDatabase.tableName + " SET " + FileDatabase.columns[2] + " = '" 
							+ currentTime +"' WHERE " + FileDatabase.columns[0] + "= "+ id;
					System.out.println(sql);
					Statement state = con.createStatement();
					state.execute(sql);
				}
				
			} catch (IllegalArgumentException | SQLException e) {
				e.printStackTrace();
			}
			return;
		}

}
