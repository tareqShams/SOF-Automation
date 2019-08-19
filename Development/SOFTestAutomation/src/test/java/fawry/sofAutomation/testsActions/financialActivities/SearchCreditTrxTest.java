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
import fawry.sofAutomation.pages.financialActivities.SearchCreditTrxPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.tablesVerification.financialActivities.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchCreditTrxTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchCreditTrxPage srchaccount = new SearchCreditTrxPage(driver);
		srchaccount.movetoSearchcredittrxpage();;
	}

	@Test(description="Validate Search Credit Trx Functionality",priority=1, dataProvider="TrxDataProvider")
	public static void searchCreditTrx(AccountTrxPojo srchcredittrxobj)  
	{
		test = extent.createTest("Validate Search Credit Trx Functionality");

			SoftAssert sa = new SoftAssert();
			SearchCreditTrxPage srchCreditTrx = new SearchCreditTrxPage(driver);

			driver.navigate().to(Constants.SEARCH_CREDIT_TRX_URL);

			// Inserting Data into Fields
			srchCreditTrx.searchCredittrxMainFields(srchcredittrxobj);
			// Taking Action of adding, resetting, confirming or canceling.
			srchCreditTrx.searchOrResetData(srchcredittrxobj);
			// Collecting Success or fail messages
			String actual = srchCreditTrx.ErrorMessagesAndSuccessMessage(srchcredittrxobj);
			sa.assertTrue(actual.contains(srchcredittrxobj.getExpectedMessage()),
					AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + srchcredittrxobj.getTestCaseId());
			//assert on DB Only when an account is added Successfully
			if (srchcredittrxobj.getAction().contains("Success"))
			{
				//Get account Balance before transaction
				SearchVefication searchAcc = new SearchVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();

				//searchAccountCriteria.setFcrn(srchcorrtrxobj.getFawyCustomerrefnum());

				ArrayList<AccountTrxPojo> accountTrxInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "");

				SearchTableVerifications searchTable=new SearchTableVerifications(driver);
				ArrayList<AccountTrxPojo> accounttrxInTable=searchTable.SearchAccounttrxTable();


				for (int i = 0; i < accountTrxInDb.size(); i++) {
					if(!accountTrxInDb.contains(accounttrxInTable.get(i))) {
						System.out.println(AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + srchcredittrxobj.getTestCaseId());
					}
				}
			}
			sa.assertAll();
	}





	@DataProvider(name = "TrxDataProvider")
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
			AccountTrxPojo transactionTestData = new AccountTrxPojo();

			transactionTestData.setBankterminalnum(resultArray.get(i).get(0).toString());
			transactionTestData.setFromdate(resultArray.get(i).get(1).toString());
			transactionTestData.setTodate(resultArray.get(i).get(2).toString());
			transactionTestData.setFawryCustomerrefnum(resultArray.get(i).get(3).toString());
			transactionTestData.setAuthenticationid(resultArray.get(i).get(4).toString());
			transactionTestData.setReconciliation(resultArray.get(i).get(5).toString());
			transactionTestData.setActioncommand(resultArray.get(i).get(6).toString());
			transactionTestData.setTransactiontype(resultArray.get(i).get(7).toString());
			transactionTestData.setAccountcode(resultArray.get(i).get(8).toString());
			transactionTestData.setBillingaccount(resultArray.get(i).get(9).toString());
			transactionTestData.setCsp(resultArray.get(i).get(10).toString());
			transactionTestData.setUserName(resultArray.get(i).get(11).toString());
			transactionTestData.setIssuerid(resultArray.get(i).get(12).toString());
			transactionTestData.setCustomerip(resultArray.get(i).get(13).toString());
			transactionTestData.setTestCaseId(resultArray.get(i).get(14).toString());
			transactionTestData.setAction(resultArray.get(i).get(15).toString());
			transactionTestData.setExpectedMessage(resultArray.get(i).get(16).toString());

			result[i][0] = transactionTestData;
		}
		return result;
	}

}
