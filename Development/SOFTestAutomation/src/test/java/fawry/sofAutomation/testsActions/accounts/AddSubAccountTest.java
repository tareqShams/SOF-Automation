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
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddSubAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	@Test(description="Validate Add Sub Account Functionality",priority=1, dataProvider="AddSubAccountDataProvider")
	public static void AddSubAccount(AccountPojo addsubaccountobj)  
	{
		test = extent.createTest("Validate Add Sub Account Functionality");

		SoftAssert sa = new SoftAssert();
		//Create a new Account to add sub accounts
/*		AddAccountPage addacc = new AddAccountPage(driver);
		addacc.addStaticAccount(addsubaccountobj);
		*/
		AddSubAccountPage addsubaccount = new AddSubAccountPage(driver);
		addsubaccount.AddSubAccountcommon(addsubaccountobj);
		
		if(addsubaccountobj.getAction().contains("Finance"))
		{
			addsubaccount.financecommon(addsubaccountobj);
		}
		else if (addsubaccountobj.getAction().contains("AquireBank"))
		{
			addsubaccount.nonFinancecommon(addsubaccountobj);
		}
		
		addsubaccount.saveOrReset(addsubaccountobj);
		
		String actual = addsubaccount.ErrorMessagesAndSuccessMessage(addsubaccountobj);
		if (actual != null) 
		{
			System.out.println(actual);
			System.out.println(addsubaccountobj.getExpectedMessage());

			sa.assertTrue(actual.contains(addsubaccountobj.getExpectedMessage()), AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + "In TestCase with id of "+addsubaccountobj.getTestCaseId());
			System.out.println(addsubaccountobj.getAction());
			if (addsubaccountobj.getAction().contains("Success"))
			{
				//validate that account is added successfully in DB
				SubAccountsVefication searchAcc = new SubAccountsVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();
				searchAccountCriteria.setAccountCode(addsubaccount.newsubaccountcode);
				searchAccountCriteria.setIsCredit("Sub");
				AccountPojo accountInDb = searchAcc.addSubAccount(searchAccountCriteria, "Add").get(0);

				sa.assertTrue(addsubaccountobj.getAccountStatus().contains(accountInDb.getAccountStatus()), AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getAccountAlias().contains(accountInDb.getAccountAlias()), AssertionErrorMessages.ALIAS_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getCurrency().contains(accountInDb.getCurrency()), AssertionErrorMessages.CURRENCY_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getCreditLimit().contains(accountInDb.getCreditLimit()), AssertionErrorMessages.CREDIT_LIMIT_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getDailyLimit().contains(accountInDb.getDailyLimit()), AssertionErrorMessages.DAILY_LIMIT_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getBankTerminal().contains(accountInDb.getBankTerminal()), AssertionErrorMessages.BANK_TERMINAL_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getAccountCode().contains(accountInDb.getPrimaryAccountCode()), AssertionErrorMessages.PRIMARY_ACCOUNT_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());
				sa.assertTrue(addsubaccountobj.getAccountNature().contains(accountInDb.getAccountNature()), AssertionErrorMessages.ACCOUNT_NATURE_EXCEL_DB + "In TestCase with id of"+addsubaccountobj.getTestCaseId());

			}

			sa.assertAll();

		}




	}




	@DataProvider(name = "AddSubAccountDataProvider")
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
			AccountPojo addSubAccountTestData = new AccountPojo();

			addSubAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());
			addSubAccountTestData.setAccountStatus(resultArray.get(i).get(1).toString());
			addSubAccountTestData.setDescription(resultArray.get(i).get(2).toString());
			addSubAccountTestData.setAccountAlias(resultArray.get(i).get(3).toString());
			addSubAccountTestData.setCurrency(resultArray.get(i).get(4).toString());
			addSubAccountTestData.setDailyLimit(resultArray.get(i).get(5).toString());
			addSubAccountTestData.setCreditLimit(resultArray.get(i).get(6).toString());
			addSubAccountTestData.setBankTerminal(resultArray.get(i).get(7).toString());
			addSubAccountTestData.setAquireBank(resultArray.get(i).get(8).toString());
			addSubAccountTestData.setMerchantName(resultArray.get(i).get(9).toString());
			addSubAccountTestData.setAccountNature(resultArray.get(i).get(10).toString());
			addSubAccountTestData.setFinanceProgram(resultArray.get(i).get(11).toString());
			addSubAccountTestData.setUsage(resultArray.get(i).get(12).toString());
			addSubAccountTestData.setAccountIdentifier(resultArray.get(i).get(13).toString());
			addSubAccountTestData.setAccountType(resultArray.get(i).get(14).toString());
			// bank no and branch code control account
			addSubAccountTestData.setSparefield0(resultArray.get(i).get(15).toString());
			addSubAccountTestData.setSparefield1(resultArray.get(i).get(16).toString());
			addSubAccountTestData.setSparefield2(resultArray.get(i).get(17).toString());

			addSubAccountTestData.setTestCaseId(resultArray.get(i).get(18).toString());
			addSubAccountTestData.setAction(resultArray.get(i).get(19).toString());
			addSubAccountTestData.setExpectedMessage(resultArray.get(i).get(20).toString());


			result[i][0] = addSubAccountTestData;

		}


		return result;
	}

}
