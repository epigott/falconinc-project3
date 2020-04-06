package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
	
		static public Connection con;
		static String[] columns = new String[] {"id", "fileId", "word", "location"};
		static String[] dataType = new String[] {"integer", "integer", "varchar(50)", "integer)"};
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
		
		//
		private static ArrayList<String> orSearch(ArrayList<Integer> validFile, ArrayList<String> query) {
			ArrayList<String> returnArray = query;	
			return returnArray;
		}
		
		// Method I'm working on.
		private static ArrayList<String> andSearch(ArrayList<Integer> validFile, ArrayList<String> query) {
                        //ArrayList to store and searched files.
			ArrayList<String> andSrchArray = new ArrayList<String>();
                        // match valid file id's to the queried string.
                        for (String s : query){
                            try{
                                // sql statemet for index
                                String index = "SELECT DISTINCT fileId FROM theWord WHERE word ="+ s +"";
                                //create statement for conn
                                Statement state = con.createStatement();

                                // create result statement 
                                ResultSet result = state.executeQuery(index);

                                // ArrayList to convert ResultSet to String
                                ArrayList<String> resArray = new ArrayList<>();

                                while(result.next()){
                                    resArray.add(result.getString(index));
                                    for (String i : resArray){

                                    andSrchArray.add(i);                               
                                    }
                                }                                                                   
                            }catch(SQLException ex){
                                System.out.println(ex);
                            }
                        }                        
		        return andSrchArray;
		}
		
		//
		private static ArrayList<String> exactSearch(ArrayList<Integer> validFile, ArrayList<String> query) {
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
				System.out.println(queryList.get(x));
			}
			//--------------------------------

			return queryList;
		}
		
		// returns list of id's to valid files
		private static ArrayList<Integer> validFile(){
			ArrayList<Integer> validIds = new ArrayList<Integer>();
			
			String[][] fileInfo;
			try {
				fileInfo = FileDatabase.getDatabase() ;		
						
				for(int x = 0 ; x < fileInfo.length ; ++x) {
					validIds.add(Integer.parseInt(fileInfo[x][0]));			
				}		
			 }							
			 catch (SQLException e) {
				e.printStackTrace();
			 }
			
			//---------------------------------
			for(int x = 0; x < validIds.size(); ++x) {
				System.out.println(validIds.get(x));
			}
			//--------------------------------
			return validIds;
		}

}
