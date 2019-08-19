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
import fawry.sofAutomation.dbVerification.accounts.ControlAccountVefication;
import fawry.sofAutomation.dbVerification.accounts.SearchVefication;
import fawry.sofAutomation.pages.accounts.AddControlAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddControlAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Add Control Account Functionality",priority=1, dataProvider="AddControlAccountDataProvider")
	public static void AddControlAccount(AccountPojo addcontrolaccountobj)  
	{
		test = extent.createTest("Validate Add Control Account Functionality");

		SoftAssert sa = new SoftAssert();
		AddControlAccountPage addcontrolaccount = new AddControlAccountPage(driver);
		String actual = addcontrolaccount.AddControlAccount(addcontrolaccountobj);

			sa.assertTrue(actual.contains(addcontrolaccountobj.getExpectedMessage()), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());

			if (addcontrolaccountobj.getAction().contains("Success"))
			{
				//validate that account is added successfully in DB
				ControlAccountVefication searchAcc = new ControlAccountVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();
				searchAccountCriteria.setAccountCode(addcontrolaccount.newcontrolaccountcode);
				searchAccountCriteria.setIsCredit("Control");
				AccountPojo accountInDb = searchAcc.controlAccount(searchAccountCriteria, "Account").get(0);

				sa.assertTrue(addcontrolaccountobj.getAccountStatus().contains(accountInDb.getAccountStatus()), AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());
				sa.assertTrue(addcontrolaccountobj.getDescription().contains(accountInDb.getDescription()), AssertionErrorMessages.ACCOUNT_DESCRIPTION_EXCEL_DB+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());
				sa.assertTrue(addcontrolaccountobj.getCreditLimit().contains(accountInDb.getCreditLimit()), AssertionErrorMessages.ACCOUNT_DESCRIPTION_EXCEL_DB+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());
				sa.assertTrue(addcontrolaccountobj.getAccountType().contains(accountInDb.getAccountType()), AssertionErrorMessages.ACCOUNT_DESCRIPTION_EXCEL_DB+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());
				sa.assertTrue(accountInDb.getUsage().equalsIgnoreCase("Control"), AssertionErrorMessages.ACCOUNT_DESCRIPTION_EXCEL_DB+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());
				sa.assertTrue(addcontrolaccountobj.getPsp().contains(accountInDb.getPsp()), AssertionErrorMessages.ACCOUNT_DESCRIPTION_EXCEL_DB+ " IN testCase With id of "+addcontrolaccountobj.getTestCaseId());
			}
			sa.assertAll();
		

	}




	@DataProvider(name = "AddControlAccountDataProvider")
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
			AccountPojo addControlAccountTestData = new AccountPojo();

			addControlAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			addControlAccountTestData.setAccountStatus(resultArray.get(i).get(1).toString());
			addControlAccountTestData.setDescription(resultArray.get(i).get(2).toString());
			addControlAccountTestData.setAccountAlias(resultArray.get(i).get(3).toString());
			addControlAccountTestData.setCreditLimit(resultArray.get(i).get(4).toString());
			addControlAccountTestData.setPsp(resultArray.get(i).get(5).toString());
			addControlAccountTestData.setPspCode(resultArray.get(i).get(6).toString());
			addControlAccountTestData.setBankTerminal(resultArray.get(i).get(7).toString());
			addControlAccountTestData.setAccountType(resultArray.get(i).get(8).toString());
			addControlAccountTestData.setExpirationDate(resultArray.get(i).get(9).toString());
			addControlAccountTestData.setAccountIdentifier(resultArray.get(i).get(10).toString());
			addControlAccountTestData.setTestCaseId(resultArray.get(i).get(11).toString());
			addControlAccountTestData.setAction(resultArray.get(i).get(12).toString());
			addControlAccountTestData.setExpectedMessage(resultArray.get(i).get(13).toString());
			

			result[i][0] = addControlAccountTestData;

		}


		return result;
	}

}
