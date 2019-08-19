package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.basicDefinitions.AssertionErrorMessages;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.dbVerification.basicDefinitions.SearchVefication;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftFeesPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class ConfigureCspOverDraftFeesTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		ConfigreCspOverDraftFeesPage fees = new ConfigreCspOverDraftFeesPage(driver);
		fees.hovertopage();

	}


	@Test(description="Validate ConfigureCspOverDraftFees Functionality",priority=1, dataProvider="DataProvider")
	public static void configureOverDraftFees(CSPFeesPojo feesobj)  
	{
		test = extent.createTest("Validate ConfigureCspOverDraftFees Functionality");

		SoftAssert sa = new SoftAssert();
		ConfigreCspOverDraftFeesPage addAccounttrx = new ConfigreCspOverDraftFeesPage(driver);

		driver.navigate().to(Constants.CSP_OVERDRAFT_FEES_URL);

		//Inserting Data into common Fields
		addAccounttrx.CommonFields(feesobj);
		// TAking Action of adding, resetting, confirming or canceling.
		addAccounttrx.saveOrResetData(feesobj);
		//Collecting Success or fail messages
		String actual = addAccounttrx.ErrorMessagesAndSuccessMessage(feesobj);
		sa.assertTrue(actual.contains(feesobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + feesobj.getTestCaseId());
		//assert on DB Only when an account is added Successfully
/*		if (feesobj.getAction().contains("Success"))
		{

			//Get account Balance before transaction
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountcode("");
			CSPFeesPojo accountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);

			sa.assertTrue(accountInDb.getAmount().equalsIgnoreCase(addaccounttrxobj.getAmount()),
					AssertionErrorMessages.ACCOUNT_AMOUNT_DB_EXCEL+" in test case with id Of " + addaccounttrxobj.getTestCaseId());

		}*/
		sa.assertAll();

	}







	@DataProvider(name = "DataProvider")
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
			CSPFeesPojo addAccountTestData = new CSPFeesPojo();
			
			addAccountTestData.setCsp(resultArray.get(i).get(0).toString());
			addAccountTestData.setAccountclass(resultArray.get(i).get(1).toString());
			addAccountTestData.setFromvalue(resultArray.get(i).get(2).toString());
			addAccountTestData.setTovalue(resultArray.get(i).get(3).toString());
			addAccountTestData.setFixedfees(resultArray.get(i).get(4).toString());
			addAccountTestData.setTestCaseId(resultArray.get(i).get(5).toString());
			addAccountTestData.setAction(resultArray.get(i).get(6).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(7).toString());




			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
