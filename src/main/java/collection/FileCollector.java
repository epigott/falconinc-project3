package collection;

import java.sql.SQLException;

import data.FileDatabase;

public class FileCollector {
	
	public static String[] collectFileInfo() {
		String[] fileInfo = {"Pokemon\\Pikachu.mon"};
		return fileInfo;
	}
	
	public static void addFile() throws SQLException {
		String[] fileInfo = collectFileInfo();// changed from String[] to String
		FileDatabase.addFile(fileInfo);
	}
	
	public static boolean validFile(){
		boolean validFile = true;
		return validFile;
	}
}