package collection;

public class SearchEngine {
		
		public static String orSearch(String query) {
			String queryList = parseQuery(query);			
			return queryList;
		}
		
		public static String andSearch(String query) {
			String queryList = parseQuery(query);			
			return queryList;
		}
		
		public static String exactSearch(String query) {
			String queryList = "Its always Pikachu";
			return queryList;
		}
		
		private static String parseQuery(String query) {
			String returnArray = "Its always Pikachu";
			return returnArray;
		}

}
