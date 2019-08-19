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
import fawry.sofAutomation.pages.accounts.EditAccountTerminalPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class EditAccountTerminalTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Edit Account Terminal Functionality",priority=1, dataProvider="EditAccountTerminalDataProvider")
	public static void EditAccountTerminal(AccountPojo editccounttermobj)  
	{
		test = extent.createTest("Validate Edit Account Terminal Functionality");

		SoftAssert sa = new SoftAssert();
		EditAccountTerminalPage editaccountterm = new EditAccountTerminalPage(driver);
		String actual = editaccountterm.editAccountTerminal(editccounttermobj);
		System.out.println(actual);
		sa.assertTrue(editccounttermobj.getExpectedMessage().contains(actual), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+"In testCase With id of " +editccounttermobj.getTestCaseId());

		if (editccounttermobj.getAction().contains("Success"))
		{
			//validate that account is Edited successfully in DB
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(editccounttermobj.getAccountCode());
			searchAccountCriteria.SetTerminalCode(editccounttermobj.getTerminalCode());
			searchAccountCriteria.setIsCredit("Sub");
			AccountPojo accountInDb = searchAcc.searchAccountTerminals(searchAccountCriteria, "Account").get(0);
			sa.assertTrue(accountInDb.getTerminalStatus().contains(editccounttermobj.getTerminalStatus()), AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB+"In testCase With id of " +editccounttermobj.getTestCaseId());
		}
		sa.assertAll();

	}




	@DataProvider(name = "EditAccountTerminalDataProvider")
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
			AccountPojo editAccountterminalTestData = new AccountPojo();

			editAccountterminalTestData.setAccountCode(resultArray.get(i).get(0).toString());
			editAccountterminalTestData.setTerminalStatus(resultArray.get(i).get(1).toString());
			editAccountterminalTestData.setTerminalName(resultArray.get(i).get(2).toString());
			editAccountterminalTestData.setTerminalType(resultArray.get(i).get(3).toString());
			//using profile id as profile code
			editAccountterminalTestData.setProfileid(resultArray.get(i).get(4).toString());
			editAccountterminalTestData.setSerialNumber(resultArray.get(i).get(5).toString());
			editAccountterminalTestData.setDescription(resultArray.get(i).get(6).toString());
			editAccountterminalTestData.setTestCaseId(resultArray.get(i).get(7).toString());
			editAccountterminalTestData.setAction(resultArray.get(i).get(8).toString());
			editAccountterminalTestData.setExpectedMessage(resultArray.get(i).get(9).toString());


			result[i][0] = editAccountterminalTestData;

		}


		return result;
	}
}
