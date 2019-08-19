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
import fawry.sofAutomation.pages.accounts.ManageAccountStatusPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class ManageAccountStatusTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		ManageAccountStatusPage navigatetomanageacc = new ManageAccountStatusPage(driver);
		navigatetomanageacc.movetomanageaccountstatuspage();

	}


	@Test(description="Validate Manage Account Status Functionality",priority=1, dataProvider="ManageAccountStatusDataProvider")
	public static void manageAccountStatus(AccountPojo manageaccountobj)  
	{
		test = extent.createTest("Validate Account Verfication Functionality");

		//validate that account status hhas changed successfully in db
		SearchVefication searchAcc = new SearchVefication();
		SearchPojo searchAccountCriteria = new SearchPojo();
		searchAccountCriteria.setAccountCode(manageaccountobj.getAccountCode());
		searchAccountCriteria.SetTerminalCode(manageaccountobj.getTerminalCode());
		searchAccountCriteria.setAccountstatusid(manageaccountobj.getSparefield0());
		searchAccountCriteria.setTerminalstatusid(manageaccountobj.getSparefield1());

		
		try {
			if (!manageaccountobj.getSparefield0().isEmpty())
			{
			searchAcc.updateAccountStatus(searchAccountCriteria, "Update");
			}
			if (!manageaccountobj.getSparefield1().isEmpty())
			{
			searchAcc.updateTerminalStatus(searchAccountCriteria, "Update");
			}
		} catch (Exception e) 
		{
			System.out.println("SQL Exception: "+ e);
		}
		ManageAccountStatusPage manageacc = new ManageAccountStatusPage(driver);
		String actual = manageacc.manageAccountStatus(manageaccountobj);
		System.out.println(actual);

		
		  SoftAssert sa = new SoftAssert();

		sa.assertTrue(actual.contains(manageaccountobj.getExpectedMessage()), 
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+"In Test Case With Id of "+manageaccountobj.getTestCaseId());
		 
		sa.assertAll();
	}




	@DataProvider(name = "ManageAccountStatusDataProvider")
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
			AccountPojo UpdateAccountTestData = new AccountPojo();
			UpdateAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			UpdateAccountTestData.setSuspentionReason(resultArray.get(i).get(1).toString());
			UpdateAccountTestData.setTerminalCode(resultArray.get(i).get(2).toString());
			//Using spare field 0 and 1 to change account and terminal status directly in DB
			//Sparefield0 for account status and sparefield1 for terminal status
			UpdateAccountTestData.setSparefield0(resultArray.get(i).get(3).toString());
			UpdateAccountTestData.setSparefield1(resultArray.get(i).get(4).toString());
			UpdateAccountTestData.setTestCaseId(resultArray.get(i).get(5).toString());
			UpdateAccountTestData.setAction(resultArray.get(i).get(6).toString());
			UpdateAccountTestData.setExpectedMessage(resultArray.get(i).get(7).toString());


			result[i][0] = UpdateAccountTestData;

		}


		return result;
	}

}
