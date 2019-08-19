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
import fawry.sofAutomation.pages.accounts.ChangeTerminalPinPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class ChangeTerminalPinTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		ChangeTerminalPinPage chtermpg = new ChangeTerminalPinPage(driver);
		chtermpg.movetoChangeTerminalPinpage();
	}

	@Test(description="Validate Change Terminal Pin Functionality",priority=1, dataProvider="ChangeTerminalPinDataProvider")
	public static void ChangeTerminalPin(TerminalPojo changeTermpinobj) throws InterruptedException  
	{
		test = extent.createTest("Validate Change Terminal Pin Functionality");

		SoftAssert sa = new SoftAssert();
		ChangeTerminalPinPage chngtrmmethod = new ChangeTerminalPinPage(driver);
		String actual = chngtrmmethod.ChangeTerminalPin(changeTermpinobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(changeTermpinobj.getExpectedMessage()), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+"In testCase With id of " +changeTermpinobj.getTestCaseId());
		if (changeTermpinobj.getAction().contains("Success"))
		{
			SearchVefication searchAccount = new SearchVefication();
			SearchPojo searchTerminalCriteria = new SearchPojo();
			System.out.println(changeTermpinobj.getAccountcode());
			searchTerminalCriteria.setAccountCode(changeTermpinobj.getAccountcode());

			TerminalPojo terminalInDb = searchAccount.searchTerminal(searchTerminalCriteria, "").get(0);
			//String hashed = chngtrmmethod.Hashing(changeTermpinobj.getTerminalPin(), "MD5");
			//System.out.println(hashed);
			//sa.assertTrue(chngtrmmethod.Hashing(changeTermpinobj.getTerminalPin(), "MD5").contains(terminalInDb.getTerminalPin()), "");
			sa.assertTrue(terminalInDb.getTerminalPin().contains(changeTermpinobj.getHashedPin()), AssertionErrorMessages.PIN_EXCEL_DB+"In testCase With id of " +changeTermpinobj.getTestCaseId());
		}
		System.out.println(actual);
		//sa.assertTrue(actual.contains("Success"), "Error");
		sa.assertAll();

	}




	@DataProvider(name = "ChangeTerminalPinDataProvider")
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
			TerminalPojo SearchTerminalTestData = new TerminalPojo();
			SearchTerminalTestData.setAccountcode(resultArray.get(i).get(0).toString());
			SearchTerminalTestData.setTerminalCsp(resultArray.get(i).get(1).toString());
			SearchTerminalTestData.setTerminalType(resultArray.get(i).get(2).toString());
			SearchTerminalTestData.setSerialNumber(resultArray.get(i).get(3).toString());
			SearchTerminalTestData.setTerminalPin(resultArray.get(i).get(4).toString());
			SearchTerminalTestData.setTerminalConfirmPin(resultArray.get(i).get(5).toString());
			SearchTerminalTestData.setHashedPin(resultArray.get(i).get(6).toString());
			SearchTerminalTestData.setTestCaseId(resultArray.get(i).get(7).toString());
			SearchTerminalTestData.setAction(resultArray.get(i).get(8).toString());
			SearchTerminalTestData.setExpectedMessage(resultArray.get(i).get(9).toString());

			result[i][0] = SearchTerminalTestData;

		}


		return result;
	}

}
