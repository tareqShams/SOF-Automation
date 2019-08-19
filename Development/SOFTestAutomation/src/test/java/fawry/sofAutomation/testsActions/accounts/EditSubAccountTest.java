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
import fawry.sofAutomation.dbVerification.accounts.SubAccountsVefication;
import fawry.sofAutomation.pages.accounts.AddAccountPage;
import fawry.sofAutomation.pages.accounts.AddSubAccountPage;
import fawry.sofAutomation.pages.accounts.EditSubAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class EditSubAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Edit Sub Account Functionality",priority=1, dataProvider="EditSubAccountDataProvider")
	public static void EditSubAccount(AccountPojo editsubaccountobj)  
	{

		test = extent.createTest("Validate Edit Sub Account Functionality");

		SoftAssert sa = new SoftAssert();
		
		/*AddAccountPage addacc = new AddAccountPage(driver);
		addacc.addStaticAccount(editsubaccountobj);
		
		AddSubAccountPage addsub = new AddSubAccountPage(driver);
		addsub.addFinanceSubAccountForedititing(editsubaccountobj);
		addsub.addnonfinanceSubAccountForedititing(editsubaccountobj);
		*/
		EditSubAccountPage editsubaccount = new EditSubAccountPage(driver);
		String actual = editsubaccount.editSubAccount(editsubaccountobj);
		System.out.println(actual);
		System.out.println(editsubaccountobj.getExpectedMessage());
		sa.assertTrue(actual.contains(editsubaccountobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP+"In Test Case With Id of "+editsubaccountobj.getTestCaseId());

		if (editsubaccountobj.getAction().contains("Success") || editsubaccountobj.getAction().contains("Delete") )
		{
			//validate that account is Edited successfully in DB
			SubAccountsVefication searchAcc = new SubAccountsVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(editsubaccountobj.getAccountCode());
			searchAccountCriteria.setIsCredit("Sub");

		
			System.out.println(editsubaccountobj.getAction());
/*			if(editsubaccountobj.getAction().contains("Delete"))
			{
				AccountPojo accountInDb = searchAcc.addSubAccount(searchAccountCriteria, "Edit").get(0);
				System.out.println(accountInDb.getAccountStatus());
				sa.assertTrue(accountInDb.getAccountStatus().contains(editsubaccountobj.getAction()), 
						AssertionErrorMessages.ACCOUNT_STATUS_EXCEL_DB+"In Test Case With Id of "+editsubaccountobj.getTestCaseId());
			}
			else {*/
				AccountPojo accountInDb = searchAcc.addSubAccount(searchAccountCriteria, "Add").get(0);
				System.out.println(accountInDb.getAccountStatus());
				System.out.println(editsubaccountobj.getAccountStatus());
			sa.assertTrue(accountInDb.getAccountStatus().contains(editsubaccountobj.getAccountStatus()), 
					AssertionErrorMessages.ACCOUNT_STATUS_EXCEL_DB+"In Test Case With Id of "+editsubaccountobj.getTestCaseId());
			//}
		}
		sa.assertAll();

	}




	@DataProvider(name = "EditSubAccountDataProvider")
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
			AccountPojo editSubAccountTestData = new AccountPojo();

			editSubAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			editSubAccountTestData.setAccountStatus(resultArray.get(i).get(1).toString());
			editSubAccountTestData.setTestCaseId(resultArray.get(i).get(2).toString());
			editSubAccountTestData.setAction(resultArray.get(i).get(3).toString());
			editSubAccountTestData.setExpectedMessage(resultArray.get(i).get(4).toString());


			result[i][0] = editSubAccountTestData;

		}


		return result;
	}
}
