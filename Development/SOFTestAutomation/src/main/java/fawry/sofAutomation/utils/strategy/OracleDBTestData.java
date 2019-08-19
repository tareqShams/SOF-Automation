package fawry.sofAutomation.utils.strategy;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import fawry.sofAutomation.constants.admin.Constants;
import fawry.sofAutomation.utils.DatabaseConnection;
import fawry.sofAutomation.utils.PropertiesFilesHandler;


public class OracleDBTestData implements TestDataStrategy{

       public void connectToDB(String dataBaseConnectionString) {
              // TODO Auto-generated method stub
              
       }

       public ArrayList<ArrayList<Object>> loadTestData(String connectionProperties) {
              
              ArrayList<ArrayList<Object>> queryResults = new ArrayList<ArrayList<Object>>();

              try 
              {
                     String dbConfig = connectionProperties.split(";")[0];
                     String dbQuery = connectionProperties.split(";")[1];
                     
                     PropertiesFilesHandler propHnadler = new PropertiesFilesHandler();
                     Properties prop = propHnadler.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);
                     
                     String dbConnectionURL = prop.getProperty(dbConfig + "_" + Constants.DB_URL_KEY);
                     String dbConnectionUserName = prop.getProperty(dbConfig + "_" + Constants.DB_USERNAME_KEY);
                     String dbConnectionPassword = prop.getProperty(dbConfig + "_" + Constants.DB_PASSWORD_KEY);
                     
                     DatabaseConnection bdConnection = new DatabaseConnection();
              
                     Connection conn = bdConnection.openConnection(dbConnectionURL, dbConnectionUserName, dbConnectionPassword);
                     queryResults = bdConnection.executeQuery(conn, dbQuery);
                     bdConnection.closeDBConnection(conn);
              } 
              catch (Exception e) 
              {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
              } 
              
              return queryResults;
       }

}

 
