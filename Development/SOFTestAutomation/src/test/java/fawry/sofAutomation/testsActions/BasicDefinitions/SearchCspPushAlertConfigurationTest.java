package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspPushAlertConfigVerifications;
import fawry.sofAutomation.pages.basicDefinitions.SearchCspPushAlertConfigurationPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchCspPushAlertConfigurationTableVerification;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class SearchCspPushAlertConfigurationTest extends BasicTest{


	@BeforeClass
	public void login()
	{
		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate SearchCspPushAlertConfiguration Functionality",priority=11, dataProvider="SearchCspPushAlertConfigurationTestDataProvider")
	public static void SearchCspPushAlertCon(CspPushAlertPojo CspPushAlertObj) throws SQLException, InterruptedException  
	{
		test = extent.createTest("Validate SearchCspPushAlertConfiguration Functionality");

		SoftAssert sa = new SoftAssert();
		SearchCspPushAlertConfigurationPage page=new SearchCspPushAlertConfigurationPage(driver);
		page.navigateToTab("Basic Definitions", "Search CSP Push Alert Config", driver);
		String actual=page.SearchCspPushAlertConfig(CspPushAlertObj);
		if (actual.equalsIgnoreCase("search"))
		{
			SearchCspPushAlertConfigurationTableVerification cspPushAlertSearchTableObj=new SearchCspPushAlertConfigurationTableVerification(driver);
			ArrayList<CspPushAlertPojo> cspPushAlartInWebTable =cspPushAlertSearchTableObj.SearchCspPushAlertConfigurationTable();
			CspPushAlertConfigVerifications cspPushAlertSearchDBObj=new CspPushAlertConfigVerifications();
			ArrayList<CspPushAlertPojo> cspPushAlartInDB= cspPushAlertSearchDBObj.CspPushAlertConfigForSearch(CspPushAlertObj);
			System.out.println(cspPushAlartInWebTable.size());
			System.out.println(cspPushAlartInDB.size());

			if(cspPushAlartInWebTable.size()==cspPushAlartInDB.size())
			{
				for(int i=0;i<cspPushAlartInWebTable.size();i++)
				{
					if(!(cspPushAlartInDB.contains(cspPushAlartInWebTable.get(i))))
					{
						sa.fail("Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"user doesnt exist in database");		
					}
				}
			}
			else 
			{
				sa.fail("Test Case ID = "+ CspPushAlertObj.getTestCaseId()+ "results from database and web table not the same ");
			}

		}
		else if(actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(page.reset(), "Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"Reset Button Funcationality In Search CSP Push Alert Configuration Failed ");
		}
		else if (actual.equalsIgnoreCase("fail"))
		{
			sa.fail("Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"Search CSP Push Alert Configuration Failed ");
		}
		else 
		{
			sa.assertTrue(actual.equalsIgnoreCase(CspPushAlertObj.getExpectedMessage()), "Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"Error Massages In Search CSP Push Alert Configuration Failed ");
		}
		sa.assertAll();
	}

	@DataProvider(name = "SearchCspPushAlertConfigurationTestDataProvider")
	public Object[][] provideTestData(Method method)
	{
		String methodFullName = method.getName();
		PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
		Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);
		String connectionProperties = prop.getProperty(methodFullName);
		ArrayList<ArrayList<Object>> resultArray = provideTestData(connectionProperties);
		Object[][] result = new Object[resultArray.size()][1];
		System.out.println(resultArray.size());
		for(int i=0; i<resultArray.size(); i++)
		{
			CspPushAlertPojo CspPushAlertObj=new CspPushAlertPojo();
			CspPushAlertObj.setTestCaseId(resultArray.get(i).get(0).toString());
			CspPushAlertObj.setCsp(resultArray.get(i).get(1).toString());
			CspPushAlertObj.setAccountType(resultArray.get(i).get(2).toString());
			CspPushAlertObj.setAllowPushAlert(resultArray.get(i).get(3).toString());
			CspPushAlertObj.setFlag(resultArray.get(i).get(4).toString());
			CspPushAlertObj.setExpectedMessage(resultArray.get(i).get(5).toString());


			result[i][0] = CspPushAlertObj;

		}
		return result;

	}


}
