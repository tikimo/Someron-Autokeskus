package com.somero_autocenter;

import java.sql.SQLException;
import java.util.ArrayList;

import com.somero_autocenter.SQLWorker;

public class Test {

	private static String table = "wp_users";
	private static String column = "user_login";
	private static String attribute = "admin";

	public static void main(String[] args) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			result = SQLWorker.SQLFetcher(table, column, attribute);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("Tuli virhe");
			e.printStackTrace();
		}
		
		System.out.println(result);
		
	}
		

}
