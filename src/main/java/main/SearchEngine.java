package main;

public class SearchEngine {
		
                // checks if file is valid
                public static boolean validFile(){
                        boolean validFile = true;
                        return validFile;
                }
    
                // or search
		public static String orSearch(String query) {
			String queryList = parseQuery(query);			
			return queryList;
		}
		
                // and search
		public static String andSearch(String query) {
			String queryList = parseQuery(query);			
			return queryList;
		}
		
                // exact phrase search
		public static String exactSearch(String query) {
			String queryList = "Its always Pikachu";
			return queryList;
		}
		
                // sorts words from input in search bar
		private static String parseQuery(String query) {
			String returnArray = "Its always Pikachu";
			return returnArray;
		}

}
