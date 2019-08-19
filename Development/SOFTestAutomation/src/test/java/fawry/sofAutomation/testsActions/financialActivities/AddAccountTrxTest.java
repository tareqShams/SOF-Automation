package fawry.sofAutomation.testsActions.financialActivities;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.financialActivities.AssertionErrorMessages;
import fawry.sofAutomation.constants.financialActivities.Constants;
import fawry.sofAutomation.dbVerification.financialActivities.SearchVefication;
import fawry.sofAutomation.dbVerification.financialActivities.TrxVefication;
import fawry.sofAutomation.pages.financialActivities.AddAccountTrxPage;
import fawry.sofAutomation.pages.financialActivities.MainTrxPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddAccountTrxTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		AddAccountTrxPage addaccount = new AddAccountTrxPage(driver);
		addaccount.movetoaddaccounttrxpage();

	}


	@Test(description="Validate Add Account Trx Functionality",priority=1, dataProvider="AddAccountTrxDataProvider")
	public static void addAccountTrx(AccountTrxPojo addaccounttrxobj)  
	{
		test = extent.createTest("Validate Add Account Trx Functionality");
		
		String timestamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());

		SoftAssert sa = new SoftAssert();
		AddAccountTrxPage addAccounttrx = new AddAccountTrxPage(driver);

		driver.navigate().to(Constants.ADD_ACCOUNT_TRX_URL);

		//Inserting Data into common Fields
		addAccounttrx.AddAccountTrxCommonFields(addaccounttrxobj);
		// TAking Action of adding, resetting, confirming or canceling.
		addAccounttrx.saveOrResetData(addaccounttrxobj);
		//Collecting Success or fail messages
		String actual = addAccounttrx.addAccountTrxErrorMessagesAndSuccessMessage(addaccounttrxobj);
		System.out.println(actual);
		System.out.println(addaccounttrxobj.getExpectedMessage());
		sa.assertTrue(actual.contains(addaccounttrxobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + addaccounttrxobj.getTestCaseId());
		//assert on DB Only when an account is added Successfully
		if (addaccounttrxobj.getAction().contains("Success"))
		{

			//Get account Balance before transaction
			TrxVefication searchAcc = new TrxVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountcode(addaccounttrxobj.getAccountcode());
			//Collect Transaction And account Nature, Amount and balance
			AccountTrxPojo calcNature = searchAcc.TrxCalcs(searchAccountCriteria, "Trx").get(0);

			if(calcNature.getActionNature().equalsIgnoreCase("Dr"))
			{
				addaccounttrxobj.setActionNature("Debit");
			}
			else if(calcNature.getActionNature().equalsIgnoreCase("Cr"))
			{
				addaccounttrxobj.setActionNature("Credit");
			}
			addaccounttrxobj.setAccountNature(calcNature.getAccountNature());

			MainTrxPage trxcal = new MainTrxPage(driver);
			int balance = trxcal.trxCalculations(addaccounttrxobj.getAccountNature(), addaccounttrxobj.getActionNature(), Float.parseFloat(calcNature.getValuebefore()), Integer.parseInt(calcNature.getAmount()));
			addaccounttrxobj.setBalance(Integer.toString(balance));
			System.out.println(addaccounttrxobj.getBalance());
			AccountTrxPojo accountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);

			sa.assertTrue(accountInDb.getBalance().equalsIgnoreCase(addaccounttrxobj.getBalance()),
					AssertionErrorMessages.BALANCE_DB_EXPECTED+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			sa.assertTrue(accountInDb.getAmount().equalsIgnoreCase(addaccounttrxobj.getAmount()),
					AssertionErrorMessages.ACCOUNT_AMOUNT_DB_EXCEL+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			System.out.println(accountInDb.getTransactiontype());
			System.out.println(addaccounttrxobj.getTransactiontype());
			sa.assertTrue(accountInDb.getTransactiontype().contains(addaccounttrxobj.getTransactiontype()),
					AssertionErrorMessages.TRX_TYPE_DB_EXCEL+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			sa.assertTrue(accountInDb.getCurrency().equalsIgnoreCase(addaccounttrxobj.getCurrency()),
					AssertionErrorMessages.ACCOUNT_CURRENCY_DB_EXCEL+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			sa.assertTrue(accountInDb.getDescription().equalsIgnoreCase(addaccounttrxobj.getDescription()),
					AssertionErrorMessages.ACCOUNT_DESCRIPTION_DB_EXCEL+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			//Making Sure That Transaction have succeeded
			sa.assertTrue(accountInDb.getTrxStatus().equalsIgnoreCase("Success"),
					AssertionErrorMessages.TRX_STATUS+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			//Making Sure That the value have changed
			sa.assertTrue(!accountInDb.getValuebefore().equalsIgnoreCase(addaccounttrxobj.getValueafter()),
					AssertionErrorMessages.ACCOUNT_TRANSACTION_VALUE_BEFORE_AFTER+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			System.out.println(accountInDb.getCreationDate());
			System.out.println(timestamp);
			sa.assertTrue(accountInDb.getCreationDate().equalsIgnoreCase(timestamp),
					AssertionErrorMessages.TRX_DATE+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
			sa.assertTrue(accountInDb.getActor().equalsIgnoreCase(Constants.LOGIN_USERNAME),
					AssertionErrorMessages.ACTOR+" in test case with id Of " + addaccounttrxobj.getTestCaseId());
		}
		sa.assertAll();

	}







	@DataProvider(name = "AddAccountTrxDataProvider")
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
			AccountTrxPojo addAccountTestData = new AccountTrxPojo();
			addAccountTestData.setCsp(resultArray.get(i).get(0).toString());
			addAccountTestData.setAccountcode(resultArray.get(i).get(1).toString());
			addAccountTestData.setTransactiontype(resultArray.get(i).get(2).toString());
			addAccountTestData.setClassification(resultArray.get(i).get(3).toString());
			addAccountTestData.setFawryCustomerrefnum(resultArray.get(i).get(4).toString());
			addAccountTestData.setAmount(resultArray.get(i).get(5).toString());
			addAccountTestData.setCurrency(resultArray.get(i).get(6).toString());
			addAccountTestData.setDate(resultArray.get(i).get(7).toString());
			addAccountTestData.setDescription(resultArray.get(i).get(8).toString());
			addAccountTestData.setTestCaseId(resultArray.get(i).get(9).toString());
			addAccountTestData.setAction(resultArray.get(i).get(10).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(11).toString());




			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
