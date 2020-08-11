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
import fawry.sofAutomation.dbVerification.accounts.AccountsVerification;
import fawry.sofAutomation.pages.accounts.SearchAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pages.main.MainPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.tablesVerification.accounts.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchAccountTest extends BasicTest{



	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		MainPage srchAcc = new MainPage();
		srchAcc.moveToPage("Accounts", "Search Account", driver);
	}
	private static String actual;
	@Test(description="Validate Search Account Functionality",priority=1, dataProvider="SearchAccountDataProvider")
	public static void SearchAccount(SearchPojo searchaccountobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Search Account Functionality");

		SoftAssert sa = new SoftAssert();
		SearchAccountPage searchmethod = new SearchAccountPage(driver);
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		searchmethod.SearchAccount(searchaccountobj);
		//Search or reset
		searchmethod.saveOrReset(searchaccountobj);
		if(searchaccountobj.getAction().contains("Reset"))
		{
			//Collect on returned messages from UI
			actual = searchmethod.allTxtFieldsData;
			//Assert on returned messages from UI
			sa.assertTrue(searchaccountobj.getExpectedMessage().contains(actual),AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP);
		}
		//Assert on DB only if search is successful (Search button is clicked No error messages collected)
		else if(actual.isEmpty() && searchaccountobj.getAction().contains("Search")) {
			//Collect on returned messages from UI
			actual = searchmethod.errorMessagesAndSuccessMessage(driver);
			//Assert on returned messages from UI
			sa.assertTrue(searchaccountobj.getExpectedMessage().contains(actual),AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP);
			AccountsVerification searchAccount = new AccountsVerification();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(searchaccountobj.getAccountCode());
			searchAccountCriteria.setAccountGroup(searchaccountobj.getAccountGroup());
			searchAccountCriteria.setMerchantCode(searchaccountobj.getMerchantCode());
			searchAccountCriteria.setOfficialNumber(searchaccountobj.getOfficialNumber());
			searchAccountCriteria.setOfficialType(searchaccountobj.getOfficialType());
			searchAccountCriteria.setStatus(searchaccountobj.getStatus());
			searchAccountCriteria.setName(searchaccountobj.getName());
			searchAccountCriteria.setCsp(searchaccountobj.getCsp());
			searchAccountCriteria.setCurrency(searchaccountobj.getCurrency());
			searchAccountCriteria.setDescription(searchaccountobj.getDescription());
			searchAccountCriteria.setUsage(searchaccountobj.getUsage());

			ArrayList<AccountPojo> accountInDb = searchAccount.searchAccount(searchAccountCriteria, "Account");

			SearchTableVerifications searchTable=new SearchTableVerifications(driver);
			ArrayList<AccountPojo> accountInTable=searchTable.SearchAccountTable();

			String x = "false";
			for (int i = 0; i < accountInDb.size(); i++) {
				if(accountInDb.contains(accountInTable.get(i))) {
					x="true";
				}
				sa.assertTrue(x.equalsIgnoreCase("true"), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + searchaccountobj.getTestCaseId());
			}
		}
		System.out.println(actual);
		//sa.assertTrue(actual.contains("Success"), "Error");
		sa.assertAll();

	}




	@DataProvider(name = "SearchAccountDataProvider")
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
			SearchPojo SearchAccountTestData = new SearchPojo();
			SearchAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			SearchAccountTestData.setMerchantCode(resultArray.get(i).get(1).toString());
			SearchAccountTestData.setOfficialNumber(resultArray.get(i).get(2).toString());
			SearchAccountTestData.setOfficialType(resultArray.get(i).get(3).toString());
			SearchAccountTestData.setAccountGroup(resultArray.get(i).get(4).toString());
			SearchAccountTestData.setStatus(resultArray.get(i).get(5).toString());
			SearchAccountTestData.setName(resultArray.get(i).get(6).toString());
			SearchAccountTestData.setCsp(resultArray.get(i).get(7).toString());
			SearchAccountTestData.setCurrency(resultArray.get(i).get(8).toString());
			SearchAccountTestData.setDescription(resultArray.get(i).get(9).toString());
			SearchAccountTestData.setUsage(resultArray.get(i).get(10).toString());

			SearchAccountTestData.setAction(resultArray.get(i).get(11).toString());
			SearchAccountTestData.setExpectedMessage(resultArray.get(i).get(12).toString());
			SearchAccountTestData.setTestCaseId(resultArray.get(i).get(13).toString());

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
