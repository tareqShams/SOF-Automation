package fawry.sofAutomation.testsActions.accounts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.accounts.AssertionErrorMessages;
import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.dbVerification.accounts.SearchVefication;
import fawry.sofAutomation.pages.accounts.SearchTerminalPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;
import fawry.sofAutomation.tablesVerification.accounts.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchTerminalTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchTerminalPage searchterm = new SearchTerminalPage(driver);
		searchterm.movetoSearchTerminalpage();
	}

	@Test(description="Validate Search Account Functionality",priority=1, dataProvider="SearchTerminalDataProvider")
	public static void SearchTerminal(TerminalPojo searchTermobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Search Account Functionality");

		SoftAssert sa = new SoftAssert();
		SearchTerminalPage searchmethod = new SearchTerminalPage(driver);
		String actual = searchmethod.SearchTerminal(searchTermobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(searchTermobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+ "In test Case With ID of" + searchTermobj.getTestCaseId());
		if (searchTermobj.getAction().contains("Success"))
		{
			SearchVefication searchAccount = new SearchVefication();
			SearchPojo searchTerminalCriteria = new SearchPojo();
			searchTerminalCriteria.SetTerminalCode(searchTermobj.getTerminalCode());;
			searchTerminalCriteria.setSerialNumber(searchTermobj.getSeriallNumber());
			searchTerminalCriteria.setTerminalType(searchTermobj.getTerminalType());
			searchTerminalCriteria.setSapreNormalType(searchTermobj.getSapreNormalType());

			ArrayList<TerminalPojo> terminalInDb = searchAccount.searchTerminal(searchTerminalCriteria, "");

			SearchTableVerifications searchTable=new SearchTableVerifications(driver);
			ArrayList<TerminalPojo> terminalInTable=searchTable.SearchTerminalTable();


			for (int i = 0; i < terminalInDb.size(); i++) {
				String x = "False";
				if(terminalInDb.contains(terminalInTable.get(i))) {	
					x = "True";
				}
				sa.assertTrue(x.equalsIgnoreCase("True"), 
						AssertionErrorMessages.SEARCH_TABLE_DB + "In test Case With ID of" + searchTermobj.getTestCaseId());
			}

		}
		System.out.println(actual);
		//sa.assertTrue(actual.contains("Success"), "Error");
		sa.assertAll();

	}




	@DataProvider(name = "SearchTerminalDataProvider")
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
			TerminalPojo SearchTerminalTestData = new TerminalPojo();
			SearchTerminalTestData.SetTerminalCode(resultArray.get(i).get(0).toString());
			SearchTerminalTestData.setSerialNumber(resultArray.get(i).get(1).toString());
			SearchTerminalTestData.setTerminalType(resultArray.get(i).get(2).toString());
			SearchTerminalTestData.setSapreNormalType(resultArray.get(i).get(3).toString());
			SearchTerminalTestData.setTestCaseId(resultArray.get(i).get(4).toString());
			SearchTerminalTestData.setAction(resultArray.get(i).get(5).toString());
			SearchTerminalTestData.setExpectedMessage(resultArray.get(i).get(6).toString());

			result[i][0] = SearchTerminalTestData;

		}


		return result;
	}

}
