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
import fawry.sofAutomation.pages.accounts.EditControlAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class EditControlAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Edit Control Account Functionality",priority=1, dataProvider="EditControlAccountDataProvider")
	public static void EditControlAccount(AccountPojo editcontrolaccountobj)  
	{
		test = extent.createTest("Validate Edit Control Account Functionality");

		SoftAssert sa = new SoftAssert();
		EditControlAccountPage editcontrolaccount = new EditControlAccountPage(driver);
		String actual = editcontrolaccount.editControlAccount(editcontrolaccountobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(editcontrolaccountobj.getExpectedMessage()), 
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+"In Test Case With Id of "+editcontrolaccountobj.getTestCaseId());

		if (editcontrolaccountobj.getAction().contains("Success"))
		{
			//validate that account is Edited successfully in DB
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(editcontrolaccount.controlaccountcodeforDBcheck);
			searchAccountCriteria.setIsCredit("Control");
			AccountPojo accountInDb = searchAcc.searchAccount(searchAccountCriteria, "Account").get(0);
			System.out.println(editcontrolaccountobj.getTerminalStatus());
			System.out.println(accountInDb.getTerminalStatus());

			sa.assertTrue(accountInDb.getTerminalStatus().contains(editcontrolaccountobj.getTerminalStatus()),
					AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB+"In Test Case With Id of "+editcontrolaccountobj.getTestCaseId());
		}
		sa.assertAll();
	}




	@DataProvider(name = "EditControlAccountDataProvider")
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
			AccountPojo editControlAccountTestData = new AccountPojo();

			editControlAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			editControlAccountTestData.setBankTerminal(resultArray.get(i).get(1).toString());
			editControlAccountTestData.setTerminalStatus(resultArray.get(i).get(2).toString());
			editControlAccountTestData.setCreditLimit(resultArray.get(i).get(3).toString());
			editControlAccountTestData.setDailyLimit(resultArray.get(i).get(4).toString());
			editControlAccountTestData.setExpirationDate(resultArray.get(i).get(5).toString());
			editControlAccountTestData.setAction(resultArray.get(i).get(6).toString());
			editControlAccountTestData.setAction(resultArray.get(i).get(7).toString());
			editControlAccountTestData.setExpectedMessage(resultArray.get(i).get(8).toString());


			result[i][0] = editControlAccountTestData;

		}


		return result;
	}
}
