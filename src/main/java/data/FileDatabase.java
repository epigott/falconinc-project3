package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileDatabase {

	private Connection con;
	private boolean hasData = false;
	private String[] columns;
	private String[] dataType;
	private String tableName;
	
	/**Constructor that checks for database on creation.
	* @throws ClassNotFoundException, SQLException
	*/
	public FileDatabase() throws ClassNotFoundException, SQLException {	
		
		columns = new String[] {"id", "fileName"};
		dataType = new String[] {"integer", "varchar(50)"};
		tableName = "thePile";
		
		if (con == null) {
			getConnection();
		}
	}
	
	/**Constructor that checks for database on creation.
	* The name of the first column will be used as a primary key 
	* and should be type integer. 
	* @param The you want the table to have
	* @param The names of the columns
	* @param The data type of the columns
	* @throws ClassNotFoundException, SQLException
	*/
	public FileDatabase(String newTable, String[] newColumns, String[] newDataType) throws ClassNotFoundException, SQLException {	
		
		columns = newColumns;
		dataType = newDataType;
		tableName = newTable;
		
		if (con == null) {
			getConnection();
		}
	}
	
	/** Establishes a connection to the file containing the  
	* database and invokes the initialize method.
	* @throws ClassNotFoundException, SQLException
	*/
	private void getConnection() throws ClassNotFoundException, SQLException {		
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:TheFilePile.db");
		initialize();		
	}

	/** Looks for a database called thePile
	* and creates it if there isn't one.
	* @throws SQLException 
	*/
	private void initialize() throws SQLException {
		
		if ( !hasData) {
			hasData = true;
			String sql;
			
			/*SQLite uses a database to store its databases called sqlite_master.
			 * Here where looking in that database to find thePile.
			 */
			//"SELECT name FROM sqlite_master WHERE type='table' AND name='thePile'"
			sql = ("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
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
				
				pokemon();
			}
		}
	}


	/** Adds a file to the database.
	 * @param fileName The name of the file.
	 * @throws SQLException 
	 */
	public void addFile(String fileInfo) throws SQLException {

		// INSERT INTO  tableName (ColumnName) values(fileInfo)"
		PreparedStatement file = con.prepareStatement("INSERT INTO " + tableName + " (" + columns[1] + ") values(?)");
		file.setString(1, fileInfo);
		file.executeUpdate();
		
		//Test Code
		System.out.println(fileInfo + " added to datebase");
	}
	
	/** 
	 * @param  
	 * @return 
	 * @throws SQLException
	 */
	public void addFile(String[] fileInfo) throws SQLException {
		
		String sql;
		// INSERT INTO  tableName (ColumnName[1], ColumnName[2]) values(fileInfo[1], fileInfo[2] )"
		// Start at 1 cause you don't assign a primary key
		sql = ( "INSERT INTO " + tableName + " (" + columns[1] );
		for(int x = 2; x < columns.length; ++x) {
			sql += (", " + columns[x]);
		}
		sql += ( ") values('" + fileInfo[0] + "'");
		for(int x = 1; x < fileInfo.length; ++x) {
			sql += (", '" + fileInfo[x]);
		}
		sql += ( "')" );
		
		Statement state = con.createStatement();
		state.execute(sql);
		
		//Test Code
		System.out.println(fileInfo[1] + " added to datebase");
	}
	

/*	/** Deprecated ,All info in the database
	 * @return Returns the entire database
	 * @throws SQLException 
	 */
/*	public String[][] getDatabase() throws SQLException {
		
		//Im just making this to find out how many rows there our
		ArrayList<String> sizeArray = this.getColumn(0);
		int size = sizeArray.size();
		ArrayList<String> rowArray;
		String[][] returnArray = new String[size][columns.length];
		
		for(int x = 0; x < size; ++x ){
			//Needs to be plus 1 because primary keys start at 1 
			//but return array starts at 0
			if (!validKey(x+1))
				continue;
			rowArray = this.getRow(x+1);
			for(int y = 0; y < columns.length; ++y ) {
				returnArray[x][y] = (rowArray.get(y)) ;
			}
		}
		System.out.println("check");
		return returnArray;
	}	*/	
	
	
	 /** All info in the database
	 * @return Returns the entire database
	 * @throws SQLException 
	 */
	public List<List<String>> getDatabase() throws SQLException {
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT * FROM " + tableName);		
		
		int x = 0 ;
		List<List<String>> returnArray = new ArrayList<List<String>>();
		while(res.next()) {
			returnArray.add(new ArrayList<String>());	
			for(int y = 0; y < columns.length; ++y ) {
				returnArray.get(x).add(res.getString(columns[y]));
			}
			++x;
		}
		return returnArray;
	}
	
	
	/** Returns all file info attached to the id number submitted.
	 * @param the name of the column you wanted returned. 
	 * @return Returns a column from the database
	 * @throws SQLException, IllegalArgumentException
	 */
	public ArrayList<String> getColumn(int input) throws SQLException, IllegalArgumentException{
		
		if ( input >= columns.length ) {
			throw new IllegalArgumentException( "Not a column");
		}
		
		ArrayList<String> returnArray = new ArrayList<String>();
		
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT " + columns[input] +" FROM " + tableName);
		
		while(res.next()) {
			returnArray.add(res.getString(columns[input]));
		}
		
		return returnArray;
	}

	
	/** Returns all information in a row. Finds the row using the primary key.
	 * @param The primary key of the row you want to get
	 * @return Returns a row of information.
	 * @throws SQLException, IllegalArgumentException
	 */
	public ArrayList<String> getRow(int primaryKey) throws SQLException, IllegalArgumentException{
		
		if (!validKey(primaryKey)) {
			throw new IllegalArgumentException( "Invalid id Number");
		}
			
		//	SELECT * FROM  tableName WHERE primary_key = input
		String sql = ("SELECT * FROM " + tableName + " WHERE " + columns[0] + " = " + primaryKey);
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(sql);
		
		ArrayList<String> returnArray = new ArrayList<String>();
		
		for(int x = 1; x <= columns.length; ++x ) {
			returnArray.add(res.getString(x)) ;
		}
		
		return returnArray;
	 }	
	
	/**
	 * @param  
	 * @throws SQLException  
	 */
	public void editData(int primaryKey, String column, String fileInfo) throws SQLException {	
		String sql = ("UPDATE " + tableName + " SET " + column + " = '" + fileInfo + "' WHERE " + columns[0] + " = " + primaryKey);
		System.out.println(sql);
		
		Statement state = con.createStatement();
		state.execute(sql);
	}
	
	/** 
	 * @param  
	 * @throws SQLException 
	 */
	public void deleteRow(int primaryKey) throws SQLException {
		String sql = ("DELETE FROM " + tableName + " WHERE " + columns[0] + " = " + primaryKey);
		System.out.println(sql);
		
		Statement state = con.createStatement();
		state.execute(sql);
	}
	
	/** Returns all the column names
	 * @return An array containing all the column names.
	 */
	public String[] getColumnList() {
		return columns;
	}
	
	/** 
	 * @param  
	 * @throws SQLException IllegalArgumentException
	 */
	public boolean validKey(int primaryKey) throws IllegalArgumentException, SQLException {
		boolean valid = false;
		
		ArrayList<String> keyList = this.getColumn(0);
		for(int x = 0; x < keyList.size(); ++x) {
			int id = Integer.parseInt(keyList.get(x));
			if (primaryKey == id) {
				valid = true;
				break;
			}
		}
		
		
		return valid;
	}
	
	/** Executes an sql statement.
	 * @param An sql statement
	 * @return A ResultSet for your Query.
	 * @throws SQLException
	 */
	public ResultSet sqlQuery(String sql) throws SQLException {
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(sql);
		return res;
	}
	
	//Just adding Pokemon.
	public void pokemon() throws SQLException {		
		String[] Bulbasaur = {"Bulbasur", "grass"};
		String[] Charmander = {"Charmander", "fire"};
		addFile(Bulbasaur);
		addFile("Ivysaur");
		addFile("Venusaur");
		addFile(Charmander);
		addFile("Charmeleon");
		addFile("Charizard");
		addFile("Squirtle");
		addFile("Wartortle");
		addFile("Blastoise");
		addFile("Caterpie");
		addFile("Metapod");
		addFile("Butterfree");
addFile("Weedle");
addFile("Kakuna");
addFile("Beedrill");
addFile("Pidgey");
addFile("Pidgeotto");
addFile("Pidgeot");
addFile("Rattata");
addFile("Raticate");
addFile("Spearow");
addFile("Fearow");
addFile("Ekans");
addFile("Arbok");
addFile("Pikachu");
addFile("Raichu");
addFile("Sandshrew");
addFile("Sandslash");
addFile("NidoranMale");
addFile("Nidorina");
addFile("Nidoqueen");
addFile("NidoranFemale");
addFile("Nidorino");
addFile("Nidoking");
addFile("Clefairy");
addFile("Clefable");
addFile("Vulpix");
addFile("Ninetales");
addFile("Jigglypuff");
addFile("Wigglytuff");
addFile("Zubat");
addFile("Golbat");
addFile("Oddish");
addFile("Gloom");
addFile("Vileplume");
addFile("Paras");
addFile("Parasect");
addFile("Venonat");
addFile("Venomoth");
addFile("Diglett");
addFile("Dugtrio");
addFile("Meowth");
addFile("Persian");
addFile("Psyduck");
addFile("Golduck");
addFile("Mankey");
addFile("Primeape");
addFile("Growlithe");
addFile("Arcanine");
addFile("Poliwag");
addFile("Poliwhirl");
addFile("Poliwrath");
addFile("Abra");
addFile("Kadabra");
addFile("Alakazam");
addFile("Machop");
addFile("Machoke");
addFile("Machamp");
addFile("Bellsprout");
addFile("Weepinbell");
addFile("Victreebel");
addFile("Tentacool");
addFile("Tentacruel");
addFile("Geodude");
addFile("Graveler");
addFile("Golem");
addFile("Ponyta");
addFile("Rapidash");
addFile("Slowpoke");
addFile("Slowbro");
addFile("Magnemite");
addFile("Magneton");
addFile("Farfetch'd");
addFile("Doduo");
addFile("Dodrio");
addFile("Seel");
addFile("Dewgong");
addFile("Grimer");
addFile("Muk");
addFile("Shellder");
addFile("Cloyster");
addFile("Gastly");
addFile("Haunter");
addFile("Gengar");
addFile("Onix");
addFile("Drowzee");
addFile("Hypno");
addFile("Krabby");
addFile("Kingler");
addFile("Voltorb");
addFile("Electrode");
addFile("Exeggcute");
addFile("Exeggutor");
addFile("Cubone");
addFile("Marowak");
addFile("Hitmonlee");
addFile("Hitmonchan");
addFile("Lickitung");
addFile("Koffing");
addFile("Weezing");
addFile("Rhyhorn");
addFile("Rhydon");
addFile("Chansey");
addFile("Tangela");
addFile("Kangaskhan");
addFile("Horsea");
addFile("Seadra");
addFile("Goldeen");
addFile("Seaking");
addFile("Staryu");
addFile("Starmie");
addFile("Mr. Mime");
addFile("Scyther");
addFile("Jynx");
addFile("Electabuzz");
addFile("Magmar");
addFile("Pinsir");
addFile("Tauros");
addFile("Magikarp");
addFile("Gyarados");
addFile("Lapras");
addFile("Ditto");
addFile("Eevee");
addFile("Vaporeon");
addFile("Jolteon");
		addFile("Flareon");
		addFile("Porygon");
		addFile("Omanyte");
		addFile("Omastar");
		addFile("Kabuto");
		addFile("Kabutops");
		addFile("Aerodactyl");
		addFile("Snorlax");
		addFile("Articuno");
		addFile("Zapdos");
		addFile("Moltres");
		addFile("Dratini");
		addFile("Dragonair");
		addFile("Dragonite");
		addFile("Mewtwo");
		addFile("Mew");	
	}
}
