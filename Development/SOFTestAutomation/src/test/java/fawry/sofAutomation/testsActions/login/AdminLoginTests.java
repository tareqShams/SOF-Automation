package fawry.sofAutomation.testsActions.login;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fawry.sofAutomation.constants.admin.Constants;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.LoginPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class AdminLoginTests extends BasicTest{
	
	@Test(description="Validate Login Functionality",priority=1, dataProvider="testDataProvider")
	public static void adminLogin(LoginPojo loginObject)  
	{

			LoginPage login = new LoginPage(driver);
			String actual = login.adminLogin(loginObject);
			String expected = loginObject.getExpectedMessage();
			int actualLength = actual.length();
			int expectedLength = expected.length();
			System.out.println(actual + "--" + actualLength);
			System.out.println(expected + "--" + expectedLength);
			Assert.assertEquals(actual, expected);

		}
	
	
	
	@DataProvider(name = "testDataProvider")
	public Object[][] provideTestData(Method method)
	{
				
		String methodFullName = method.getName();
		
		PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
		Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);
		
		String connectionProperties = prop.getProperty(methodFullName);
		
		ArrayList<ArrayList<Object>> resultArray = provideTestData(connectionProperties);
		Object[][] result = new Object[resultArray.size()][1];
		
		
		for(int i=0; i<resultArray.size(); i++)
		{
			LoginPojo loginTestData = new LoginPojo();
			
			loginTestData.setUserName(resultArray.get(i).get(0).toString());
			loginTestData.setPassword(resultArray.get(i).get(1).toString());
			loginTestData.setExpectedMessage(resultArray.get(i).get(2).toString());
			
			result[i][0] = loginTestData;
			
			
		}
		
		
		return result;
	}
	
}
