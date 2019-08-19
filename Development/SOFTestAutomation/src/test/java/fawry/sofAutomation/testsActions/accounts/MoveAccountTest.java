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
import fawry.sofAutomation.pages.accounts.MoveAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class MoveAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		MoveAccountPage movemethod = new MoveAccountPage(driver);
		movemethod.movetoMoveAccountpage();
	}

	@Test(description="Validate Move Account Functionality",priority=1, dataProvider="MoveAccountDataProvider")
	public static void moveAccount(AccountPojo moveaccountobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Move Account Functionality");

		SoftAssert sa = new SoftAssert();
		MoveAccountPage movemethod = new MoveAccountPage(driver);
		String actual = movemethod.moveAccount(moveaccountobj);
		sa.assertTrue(actual.equalsIgnoreCase(moveaccountobj.getExpectedMessage()), 
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+"In Test Case With Id of "+moveaccountobj.getTestCaseId());
		if (moveaccountobj.getAction().contains("Success"))
		{

			SearchVefication searchAccount = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(moveaccountobj.getAccountCode());

			AccountPojo accountInDb = searchAccount.searchAccount(searchAccountCriteria, "Account").get(0);
			System.out.println(moveaccountobj.getMerchantName());
			sa.assertTrue(accountInDb.getMerchantName().contains(moveaccountobj.getMerchantName()), 
					AssertionErrorMessages.MERCHANT_NAME_EXCEL_DB+"In Test Case With Id of "+moveaccountobj.getTestCaseId());
			sa.assertTrue(movemethod.newCsp.equalsIgnoreCase(moveaccountobj.getCsp()), 
					AssertionErrorMessages.CSP_NAME_EXCEL_DB+"In Test Case With Id of "+moveaccountobj.getTestCaseId());
		}
		sa.assertAll();

	}




	@DataProvider(name = "MoveAccountDataProvider")
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
			SearchAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			SearchAccountTestData.setCsp(resultArray.get(i).get(1).toString());
			SearchAccountTestData.setAction(resultArray.get(i).get(2).toString());
			SearchAccountTestData.setAction(resultArray.get(i).get(3).toString());
			SearchAccountTestData.setExpectedMessage(resultArray.get(i).get(4).toString());



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
