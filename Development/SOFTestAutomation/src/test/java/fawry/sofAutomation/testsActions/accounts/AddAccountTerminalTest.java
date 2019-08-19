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
import fawry.sofAutomation.pages.accounts.AddAccountTerminalPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddAccountTerminalTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Add Accout Terminal Functionality",priority=1, dataProvider="AddAccountTerminalDataProvider")
	public static void AddAccountTerminal(AccountPojo addaccountterminalobj)  
	{
		test = extent.createTest("Validate Add Accout Terminal Functionality");

		SoftAssert sa = new SoftAssert();
		AddAccountTerminalPage addaccountterminal = new AddAccountTerminalPage(driver);
		String actual = addaccountterminal.AddAccountTerminal(addaccountterminalobj);
		System.out.println(addaccountterminalobj.getTerminalCode());
		sa.assertTrue(actual.contains(addaccountterminalobj.getExpectedMessage()), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+ " IN testCase With id of "+addaccountterminalobj.getTestCaseId());
		if (addaccountterminalobj.getAction().contains("Success"))
		{	
			//validate that Terminal is added successfully in DB
				SearchVefication searchAcc = new SearchVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();
				searchAccountCriteria.setAccountCode(addaccountterminalobj.getAccountCode());
				searchAccountCriteria.SetTerminalCode(addaccountterminalobj.getTerminalCode());
				searchAccountCriteria.setIsCredit("Terminal");
				AccountPojo accountInDb = searchAcc.searchAccountTerminals(searchAccountCriteria, "Account").get(0);
				System.out.println(addaccountterminalobj.getTerminalType());
				System.out.println(accountInDb.getTerminalType());
				sa.assertTrue(accountInDb.getTerminalStatus().contains(addaccountterminalobj.getTerminalStatus()), AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB+ " IN testCase With id of "+addaccountterminalobj.getTestCaseId());
				sa.assertTrue(addaccountterminalobj.getTerminalType().contains(accountInDb.getTerminalType()), AssertionErrorMessages.TERMINAL_TYPE_EXCEL_DB+ " IN testCase With id of "+addaccountterminalobj.getTestCaseId());


		}
		sa.assertAll();	
	}




	@DataProvider(name = "AddAccountTerminalDataProvider")
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
			AccountPojo addAccountTerminalTestData = new AccountPojo();

			addAccountTerminalTestData.setAccountCode(resultArray.get(i).get(0).toString());
			addAccountTerminalTestData.setTerminalStatus(resultArray.get(i).get(1).toString());
			//addAccountTerminalTestData.setTerminalName(resultArray.get(i).get(2).toString());
			addAccountTerminalTestData.setTerminalType(resultArray.get(i).get(3).toString());
			//Using Profileid as terminal profile code
			addAccountTerminalTestData.setProfileid(resultArray.get(i).get(4).toString());
			addAccountTerminalTestData.setSerialNumber(resultArray.get(i).get(5).toString());
			addAccountTerminalTestData.setPin(resultArray.get(i).get(6).toString());
			addAccountTerminalTestData.setDescription(resultArray.get(i).get(7).toString());
			addAccountTerminalTestData.setTestCaseId(resultArray.get(i).get(8).toString());
			addAccountTerminalTestData.setAction(resultArray.get(i).get(9).toString());
			addAccountTerminalTestData.setExpectedMessage(resultArray.get(i).get(10).toString());


			result[i][0] = addAccountTerminalTestData;

		}


		return result;
	}

}
