package fawry.sofAutomation.testsActions.financialActivities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.financialActivities.AssertionErrorMessages;
import fawry.sofAutomation.constants.financialActivities.Constants;
import fawry.sofAutomation.dbVerification.financialActivities.SearchVefication;
import fawry.sofAutomation.pages.financialActivities.AddCreditTrxPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddCreditTransactionTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		AddCreditTrxPage addCredit = new AddCreditTrxPage(driver);
		addCredit.movetoaddcredittrxpage();
	}

	@Test(description="Validate Adding Credit Card Trx Functionality",priority=1, dataProvider="AddCreditCardTrxDataProvider")
	public static void AddCreditTransaction(AccountTrxPojo srchcorrtrxobj)  
	{
		test = extent.createTest("Validate Adding Credit Card Trx Functionality");

			SoftAssert sa = new SoftAssert();
			AddCreditTrxPage addcredittrx = new AddCreditTrxPage(driver);

			driver.navigate().to(Constants.ADD_CREDIT_TRX_URL);

			// Inserting Data into Fields
			addcredittrx.addCreditTrxCommonFields(srchcorrtrxobj);
			// Taking Action of adding, resetting, confirming or canceling.
			addcredittrx.saveOrResetData(srchcorrtrxobj);
			// Collecting Success or fail messages
			String actual = addcredittrx.addCreditTrxErrorMessagesAndSuccessMessage(srchcorrtrxobj);
			sa.assertTrue(actual.contains(srchcorrtrxobj.getExpectedMessage()),
					AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + srchcorrtrxobj.getTestCaseId());
			//assert on DB Only when an account is added Successfully
			if (srchcorrtrxobj.getAction().contains("Success"))
			{
				//Get account Balance before transaction
				SearchVefication searchAcc = new SearchVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();

				searchAccountCriteria.setAccountcode(srchcorrtrxobj.getAccountcode());

				AccountTrxPojo accountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);
				
				sa.assertTrue(accountInDb.getAmount().equalsIgnoreCase(srchcorrtrxobj.getAmount()),
						AssertionErrorMessages.ACCOUNT_AMOUNT_DB_EXCEL+" in test case with id Of " + srchcorrtrxobj.getTestCaseId());

			}
			sa.assertAll();
	}





	@DataProvider(name = "AddCreditCardTrxDataProvider")
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

			addAccountTestData.setAccountcode(resultArray.get(i).get(0).toString());
			addAccountTestData.setCsp(resultArray.get(i).get(1).toString());
			addAccountTestData.setBankterminalnum(resultArray.get(i).get(2).toString());
			addAccountTestData.setLastfourdigits(resultArray.get(i).get(3).toString());
			addAccountTestData.setIssuerid(resultArray.get(i).get(4).toString());
			addAccountTestData.setDate(resultArray.get(i).get(5).toString());
			addAccountTestData.setAlias(resultArray.get(i).get(6).toString());
			addAccountTestData.setTransactiontype(resultArray.get(i).get(7).toString());
			addAccountTestData.setAmount(resultArray.get(i).get(8).toString());
			addAccountTestData.setAccounttype(resultArray.get(i).get(9).toString());
			addAccountTestData.setClassification(resultArray.get(i).get(10).toString());
			addAccountTestData.setDescription(resultArray.get(i).get(11).toString());
			addAccountTestData.setTestCaseId(resultArray.get(i).get(12).toString());
			addAccountTestData.setAction(resultArray.get(i).get(13).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(14).toString());

			result[i][0] = addAccountTestData;
		}
		return result;
	}

}
