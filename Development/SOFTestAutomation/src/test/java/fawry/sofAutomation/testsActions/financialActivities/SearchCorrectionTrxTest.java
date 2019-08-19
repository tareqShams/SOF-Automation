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
import fawry.sofAutomation.pages.financialActivities.SearchCorrectionTrxPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.tablesVerification.financialActivities.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchCorrectionTrxTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchAccountTrxPage srchaccount = new SearchAccountTrxPage(driver);
		srchaccount.movetoSearchaccounttrxpage();
	}

	@Test(description="Validate Search Correction Trx Functionality",priority=1, dataProvider="SearchCorrectionTrxDataProvider")
	public static void searchCorrectionTrx(AccountTrxPojo srchcorrtrxobj)  
	{
		test = extent.createTest("Validate Search Correction Trx Functionality");

			SoftAssert sa = new SoftAssert();
			SearchCorrectionTrxPage srchAccounttrx = new SearchCorrectionTrxPage(driver);

			driver.navigate().to(Constants.Search_CORRECTION_TRX_URL);

			System.out.println(srchcorrtrxobj.getFawyCustomerrefnum());
			// Inserting Data into Fields
			srchAccounttrx.searchCorrectiontrxSearchField(srchcorrtrxobj);
			// Taking Action of adding, resetting, confirming or canceling.
			srchAccounttrx.searchOrResetData(srchcorrtrxobj);
			// Collecting Success or fail messages
			String actual = srchAccounttrx.srchAccountTrxErrorMessagesAndSuccessMessage(srchcorrtrxobj);
			sa.assertTrue(actual.contains(srchcorrtrxobj.getExpectedMessage()),
					AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + srchcorrtrxobj.getTestCaseId());
			//assert on DB Only when an account is added Successfully
			if (srchcorrtrxobj.getAction().contains("Success"))
			{
				//Get account Balance before transaction
				SearchVefication searchAcc = new SearchVefication();
				SearchPojo searchAccountCriteria = new SearchPojo();

				searchAccountCriteria.setFcrn(srchcorrtrxobj.getFawyCustomerrefnum());

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





	@DataProvider(name = "SearchCorrectionTrxDataProvider")
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

			addAccountTestData.setFawryCustomerrefnum(resultArray.get(i).get(0).toString());
			addAccountTestData.setTestCaseId(resultArray.get(i).get(1).toString());
			addAccountTestData.setAction(resultArray.get(i).get(2).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(3).toString());

			result[i][0] = addAccountTestData;
		}
		return result;
	}

}
