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
import fawry.sofAutomation.pages.accounts.SearchCreditCardAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.tablesVerification.accounts.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchCreditCardAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchCreditCardAccountPage srch = new SearchCreditCardAccountPage(driver);
		srch.movetoSearchcreditaccountpage();

	}

	@Test(description="Validate Search Credit Account Functionality",priority=1, dataProvider="SearchCreditAccountDataProvider")
	public static void SearchCreditAccount(AccountPojo searchcreditaccountobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Search Credit Account Functionality");

		SoftAssert sa = new SoftAssert();
		SearchCreditCardAccountPage searchmethod = new SearchCreditCardAccountPage(driver);
		String actual = searchmethod.SearchCreditAccount(searchcreditaccountobj);
		if (searchcreditaccountobj.getAction().equalsIgnoreCase("SearchSuccess")) {
			SearchTableVerifications searchTable=new SearchTableVerifications(driver);
			ArrayList<AccountPojo> accountInTable=searchTable.SearchCreditAccountTable();
			if (!searchcreditaccountobj.getAccountCode().isEmpty() ) {

				SearchVefication searchAccount = new SearchVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();
				searchAccountCriteria.setAccountCode(searchcreditaccountobj.getAccountCode());
				searchAccountCriteria.setIsCredit("SubCredit");

				ArrayList<AccountPojo> accountInDb = searchAccount.searchAccount(searchAccountCriteria, "Account");



				for (int i = 0; i < accountInDb.size(); i++) {
					String x = "False";
					if(accountInDb.contains(accountInTable.get(i))) {
						x="True";
					}
					sa.assertTrue(x.equalsIgnoreCase("True"), 
							AssertionErrorMessages.SEARCH_TABLE_DB + "In test Case With ID of" + searchcreditaccountobj.getTestCaseId());
				}
			}
		}
		System.out.println(actual);
		System.out.println(searchcreditaccountobj.getExpectedMessage());

		sa.assertTrue(actual.contains(searchcreditaccountobj.getExpectedMessage()), "Error");
		sa.assertAll();

	}




	@DataProvider(name = "SearchCreditAccountDataProvider")
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
			SearchAccountTestData.setBankTerminal(resultArray.get(i).get(0).toString());
			SearchAccountTestData.setAccountCode(resultArray.get(i).get(1).toString());
			SearchAccountTestData.setAccountType(resultArray.get(i).get(2).toString());
			SearchAccountTestData.setTerminalStatus(resultArray.get(i).get(3).toString());
			SearchAccountTestData.setAquireBank(resultArray.get(i).get(4).toString());
			SearchAccountTestData.setAccountNature(resultArray.get(i).get(5).toString());
			SearchAccountTestData.setMerchantName(resultArray.get(i).get(6).toString());
			SearchAccountTestData.setTestCaseId(resultArray.get(i).get(7).toString());
			SearchAccountTestData.setAction(resultArray.get(i).get(8).toString());
			SearchAccountTestData.setExpectedMessage(resultArray.get(i).get(9).toString());


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
