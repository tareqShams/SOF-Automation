package fawry.sofAutomation.testsActions.accounts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mongodb.diagnostics.logging.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import fawry.sofAutomation.constants.accounts.AssertionErrorMessages;
import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.dbVerification.accounts.AccountsVerification;
import fawry.sofAutomation.dbVerification.accounts.SearchVefication;
import fawry.sofAutomation.pages.accounts.SearchAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
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
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);

	}

	@Test(description="Validate Search Account Functionality",priority=1, dataProvider="SearchAccountDataProvider")
	public static void SearchAccount(SearchPojo searchaccountobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Search Account Functionality");
		
		SoftAssert sa = new SoftAssert();
		SearchAccountPage searchmethod = new SearchAccountPage(driver);
		String actual = searchmethod.SearchAccount(searchaccountobj);
		AccountsVerification searchAccount = new AccountsVerification();
		SearchPojo searchAccountCriteria = new SearchPojo();
		searchAccountCriteria.setAccountCode(searchaccountobj.getAccountCode());
		searchAccountCriteria.setAccountGroup(searchaccountobj.getAccountGroup());
		searchAccountCriteria.setMerchantCode(searchaccountobj.getMerchantCode());
		searchAccountCriteria.setOfficialNumber(searchaccountobj.getOfficialNumber());
		searchAccountCriteria.setOfficialType(searchaccountobj.getOfficialType());
		searchAccountCriteria.setStatus(searchaccountobj.getStatus());

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
			SearchAccountTestData.setTestCaseId(resultArray.get(i).get(6).toString());

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
