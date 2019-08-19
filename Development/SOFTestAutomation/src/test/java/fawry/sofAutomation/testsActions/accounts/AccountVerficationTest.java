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
import fawry.sofAutomation.pages.accounts.AccountVerficationPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AccountVerficationTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Account Verfication Functionality",priority=1, dataProvider="AccountVerficationDataProvider")
	public static void accountVerfication(AccountPojo accountverficationobj)  
	{
		test = extent.createTest("Validate Account Verfication Functionality");

		SoftAssert sa = new SoftAssert();
		AccountVerficationPage accountverfication = new AccountVerficationPage(driver);
		String actual = accountverfication.accountVerfication(accountverficationobj);

		System.out.println(accountverficationobj.getExpectedMessage());
		System.out.println(actual);
		
		sa.assertTrue(accountverficationobj.getExpectedMessage().contains(actual), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+ " IN testCase With id of "+accountverficationobj.getTestCaseId());

		if (accountverficationobj.getAction().contains("Success"))
		{
			//validate that account is added successfully in DB
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(accountverficationobj.getAccountCode());
			searchAccountCriteria.setIsCredit("Verify");
			AccountPojo accountInDb = searchAcc.searchAccount(searchAccountCriteria, "Account").get(0);

			sa.assertTrue(accountverficationobj.getTerminalStatus().contains(accountInDb.getTerminalStatus()), AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB+" In test Case with id of "+accountverficationobj.getTestCaseId());
		}

		sa.assertAll();


	}




	@DataProvider(name = "AccountVerficationDataProvider")
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
			AccountPojo accountVerficationTestData = new AccountPojo();

			accountVerficationTestData.setAccountCode(resultArray.get(i).get(0).toString());
			accountVerficationTestData.setTestCaseId(resultArray.get(i).get(1).toString());
			accountVerficationTestData.setAction(resultArray.get(i).get(2).toString());
			accountVerficationTestData.setTerminalStatus(resultArray.get(i).get(3).toString());
			accountVerficationTestData.setExpectedMessage(resultArray.get(i).get(4).toString());


			result[i][0] = accountVerficationTestData;

		}


		return result;
	}

}
