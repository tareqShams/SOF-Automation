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
import fawry.sofAutomation.pages.financialActivities.SearchAccountTrxPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.tablesVerification.financialActivities.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchAccountTrxTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchAccountTrxPage srchaccount = new SearchAccountTrxPage(driver);
		srchaccount.movetoSearchaccounttrxpage();
	}


	@Test(description="Validate Search Account Trx Functionality",priority=1, dataProvider="SearchAccountTrxDataProvider")
	public static void searchAccountTrx(AccountTrxPojo srchaccounttrxobj)  
	{
		test = extent.createTest("Validate Search Account Trx Functionality");


			SoftAssert sa = new SoftAssert();
			SearchAccountTrxPage srchAccounttrx = new SearchAccountTrxPage(driver);

			driver.navigate().to(Constants.Search_ACCOUNT_TRX_URL);

			// Inserting Data into Fields
			srchAccounttrx.SearchAccounttrxSearchFields(srchaccounttrxobj);
			// Taking Action of adding, resetting, confirming or canceling.
			srchAccounttrx.searchOrResetData(srchaccounttrxobj);
			// Collecting Success or fail messages
			String actual = srchAccounttrx.srchAccountTrxErrorMessagesAndSuccessMessage(srchaccounttrxobj);
			sa.assertTrue(actual.contains(srchaccounttrxobj.getExpectedMessage()),
					AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + srchaccounttrxobj.getTestCaseId());
			//assert on DB Only when an account is added Successfully
			if (srchaccounttrxobj.getAction().contains("Success"))
			{
				//Get account Balance before transaction
				SearchVefication searchAcc = new SearchVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();
				searchAccountCriteria.setAccountcode(srchaccounttrxobj.getAccountcode());
				searchAccountCriteria.setTransactiontype(srchaccounttrxobj.getTransactiontype());
				searchAccountCriteria.setFcrn(srchaccounttrxobj.getFawyCustomerrefnum());
				searchAccountCriteria.setSoftrxrefnum(srchaccounttrxobj.getSoftrxrefnum());


				ArrayList<AccountTrxPojo> accountTrxInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "");

				SearchTableVerifications searchTable=new SearchTableVerifications(driver);
				ArrayList<AccountTrxPojo> accounttrxInTable=searchTable.SearchAccounttrxTable();


				for (int i = 0; i < accountTrxInDb.size(); i++) {
					if(!accountTrxInDb.contains(accounttrxInTable.get(i))) {
						System.out.println(AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + "searchTermobj.getTestCaseId()");
					}
				}


			}
			sa.assertAll();
	}






	@DataProvider(name = "SearchAccountTrxDataProvider")
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
			addAccountTestData.setBilltypecode(resultArray.get(i).get(0).toString());
			addAccountTestData.setAccounttype(resultArray.get(i).get(1).toString());
			addAccountTestData.setAccountcode(resultArray.get(i).get(2).toString());
			addAccountTestData.setCsp(resultArray.get(i).get(3).toString());
			addAccountTestData.setFromdate(resultArray.get(i).get(4).toString());
			addAccountTestData.setTodate(resultArray.get(i).get(5).toString());
			addAccountTestData.setMerchant(resultArray.get(i).get(6).toString());
			addAccountTestData.setSoftrxrefnum(resultArray.get(i).get(7).toString());
			addAccountTestData.setFawryCustomerrefnum(resultArray.get(i).get(8).toString());
			addAccountTestData.setTerminalstatus(resultArray.get(i).get(9).toString());
			addAccountTestData.setTransactiontype(resultArray.get(i).get(10).toString());
			addAccountTestData.setRevcustomerrefnum(resultArray.get(i).get(11).toString());
			addAccountTestData.setUsage(resultArray.get(i).get(12).toString());
			addAccountTestData.setTestCaseId(resultArray.get(i).get(13).toString());
			addAccountTestData.setAction(resultArray.get(i).get(14).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(15).toString());


			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
