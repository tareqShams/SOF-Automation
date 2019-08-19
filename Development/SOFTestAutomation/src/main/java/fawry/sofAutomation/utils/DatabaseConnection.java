package fawry.sofAutomation.utils;


import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import fawry.sofAutomation.constants.admin.Constants;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DatabaseConnection {

	public  Connection openConnection(String dbUrl, String username, String password)
			throws ClassNotFoundException, SQLException {
		//            dbUrl = "jdbc:oracle:thin:@10.95.0.38:1521:orcl";


		Connection con = null;
		// Create Connection to DataBase

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			con = DriverManager.getConnection(dbUrl, username, password);
			System.out.print("connection done");

		} catch (SQLException e) {
			System.out.print("connection error");

		}
		// Create Statement Object

		// closing DataBase Connection

		return con;

	}




	public  Connection openConnection() {
		Connection con = null;
		// Create Connection to DataBase

		try {
			PropertiesFilesHandler propHnadler = new PropertiesFilesHandler();
			Properties prop = propHnadler.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);

			String dbUrl = prop.getProperty(Constants.DB_URL_KEY);
			String username = prop.getProperty(Constants.DB_USERNAME_KEY);
			String password = prop.getProperty(Constants.DB_PASSWORD_KEY);

			Class.forName("oracle.jdbc.OracleDriver");

			con = DriverManager.getConnection(dbUrl, username, password);
			System.out.print("connection done");

		} catch (Exception e) {
			System.out.print("connection error");
			e.printStackTrace();
		}
		// Create Statement Object

		// closing DataBase Connection

		return con;

	}


	public ArrayList<ArrayList<Object>> executeQuery(Connection conn, String sqlQuery)
	{
		ArrayList<ArrayList<Object>> queryResults = new ArrayList<ArrayList<Object>>();

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlQuery);
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();

			while(rs.next())
			{
				ArrayList<Object> row = new ArrayList<Object>();
				for ( int i=1; i<columnsNumber+1; i++ ) 
				{
					Object columnValue = new Object();
					columnValue =  rs.getObject(i);
					row.add(columnValue);
				}
				queryResults.add(row); 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return queryResults;
	}


	public ResultSet executeVerificationQuery(Connection conn, String sqlQuery)
	{
		Statement statement = null;
		ResultSet rs = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sqlQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return rs;
	}
	public void executeVerificationQuery1(Connection conn, String sqlQuery)
	{
		Statement statement = null;
	

		try {
			statement = conn.createStatement();
			statement.executeQuery(sqlQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}


	public  void closeDBConnection(Connection con) {


		try {
			con.close();
		}

		catch (SQLException e) {
			System.out.println("Query Execution Error");
			e.printStackTrace();

		}
	}
	
	
}