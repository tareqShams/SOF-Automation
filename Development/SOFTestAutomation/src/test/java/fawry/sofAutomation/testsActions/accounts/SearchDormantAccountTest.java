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
import fawry.sofAutomation.pages.accounts.SearchDormantAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.tablesVerification.accounts.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchDormantAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		driver.navigate().to(Constants.SEARCH_DORMANT_ACCOUNT_URL);

	}

	@Test(description="Validate Search Account Functionality",priority=1, dataProvider="SearchDormantAccountDataProvider")
	public static void SearchDormantAccount(AccountPojo searchdoraccountobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Search Account Functionality");

		SoftAssert sa = new SoftAssert();
		SearchDormantAccountPage searchmethod = new SearchDormantAccountPage(driver);
		String actual = searchmethod.SearchDormantAccount(searchdoraccountobj);
		sa.assertTrue(actual.equalsIgnoreCase(searchdoraccountobj.getExpectedMessage()), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+ "In test Case With ID of" + searchdoraccountobj.getTestCaseId());
		if (searchdoraccountobj.getTerminalStatus().equalsIgnoreCase("Dormant") && searchdoraccountobj.getAction().equalsIgnoreCase("Search"))
		{
			SearchVefication searchAccount = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(searchdoraccountobj.getAccountCode());
			searchAccountCriteria.setStatus(searchdoraccountobj.getTerminalStatus());
			searchAccountCriteria.setCsp(searchdoraccountobj.getCsp());

			ArrayList<AccountPojo> accountInDb = searchAccount.searchAccount(searchAccountCriteria, "Account");

			SearchTableVerifications searchTable=new SearchTableVerifications(driver);
			ArrayList<AccountPojo> accountInTable=searchTable.SearchDormanAccountTable();


			for (int i = 0; i < accountInDb.size(); i++) {
				String x = "False";
				if(accountInDb.contains(accountInTable.get(i))) {
					x="True";
				}
				sa.assertTrue(x.equalsIgnoreCase("True"), 
						AssertionErrorMessages.SEARCH_TABLE_DB + "In test Case With ID of" + searchdoraccountobj.getTestCaseId());
			}
		}


		System.out.println(actual);
		//sa.assertTrue(actual.contains("Success"), "Error");
		sa.assertAll();

	}




	@DataProvider(name = "SearchDormantAccountDataProvider")
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
			AccountPojo SearchAccountTestData = new AccountPojo();
			SearchAccountTestData.setTerminalStatus(resultArray.get(i).get(0).toString());
			SearchAccountTestData.setAccountCode(resultArray.get(i).get(1).toString());
			SearchAccountTestData.setCsp(resultArray.get(i).get(2).toString());
			SearchAccountTestData.setNewCsp(resultArray.get(i).get(3).toString());
			SearchAccountTestData.setTestCaseId(resultArray.get(i).get(4).toString());
			SearchAccountTestData.setAction(resultArray.get(i).get(5).toString());
			SearchAccountTestData.setExpectedMessage(resultArray.get(i).get(6).toString());

			result[i][0] = SearchAccountTestData;

		}


		return result;
	}
	/*
	int TableSize=userInTable.size();
	int DBSize=accountInDb.size();
	sa.assertEquals(TableSize, DBSize);
	 */
}
