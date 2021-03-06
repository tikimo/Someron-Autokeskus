package com.somero_autocenter;

import java.sql.*;
import java.util.ArrayList;

public class SQLWorker {
	   private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   private static final String DB_URL = "jdbc:mysql://database.moradiallinone.org/uno4723";
	   private static final String USER = "uno4723";
	   private static final String PASS = "Xbw8Hc";
	   
	   /**
	    * @throws SQLException
	    * @throws InstantiationException
	    * @throws IllegalAccessException
	    * @throws ClassNotFoundException
	    */
	   public static ArrayList<String> SQLFetcher(String table, String column, String attribute) 
			   throws SQLException, InstantiationException, 
			   IllegalAccessException, ClassNotFoundException {
		   
		   Connection conn = null;
		   Statement stmt = null;
		   // Luodaan vastauksille lista
		   ArrayList<String> resultsFromQuery = new ArrayList<String>();

		try {
			   Class.forName(JDBC_DRIVER).newInstance();

			   System.out.println("Yhdistet��n palvelimeen...");
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);

			   // Suoritetaan kysely ...
			   stmt = conn.createStatement();
			   String sql_command = "SELECT * FROM "+table+" WHERE "+column+" = "+attribute;
			   ResultSet rs = stmt.executeQuery(sql_command);


			   // Puretaan vastaus vastaukselta
			   int index = 0;
			   while (rs.next()) {
				   resultsFromQuery.add(rs.getString(index));
				   index++;
			   }



		   } catch (SQLException se) {
			   se.printStackTrace();
		   } catch (Exception e) {
			   e.printStackTrace();
		   } finally {
		      //Suljetaan resursseja
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		    	// silence is golden
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		
		return resultsFromQuery;
	   }
	   
	   public static void SQLWriter(String table, String[] column, String[] data) 
			   throws ClassNotFoundException {
		   
		   Connection conn = null;
		   Statement  stmt = null;
		   String sqlCommand = formatInsertString(table, column, data);
		   
		   try {
			   // Rekister�id��n ajuri
			   Class.forName(JDBC_DRIVER);
			   
			   // Avataan yhteys
			   System.out.println("Yhdistet��n tietokantaan...");
			   conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   System.out.println("Yhdistetty onnistuneest!");
			   
			   // Suoritetaan kysely
			   System.out.println("Suoritetaan kysely�...");
			   stmt = conn.createStatement();
			   stmt.executeUpdate(sqlCommand);
			   System.out.println("Kysely suoritettu!");
			   
		   } catch (SQLException se) {
			   se.printStackTrace();
		   } finally {
			   try {
				   if (stmt != null) {
					   conn.close();
				   }
			   } catch (SQLException se2) {/*silencio*/}
			   
			   try {
				   if (conn != null) {
					   conn.close();
				   }
			   } catch (SQLException se2) {
				   se2.printStackTrace();
			   }
		   }
	   }

	private static String formatInsertString(String table, String[] column, String[] data) {
		
		String sqlCommand = "INSERT INTO "+table+" (";
		for(String col : column) {
			sqlCommand += col+",";
		}
		sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
		sqlCommand += ") VALUES (";
		
		for (String dat : data) {
			sqlCommand += "'"+dat+"', ";
		}
		sqlCommand = sqlCommand.substring(0, sqlCommand.length()-2);
		sqlCommand += ");";
		
		return sqlCommand;
	}
}
