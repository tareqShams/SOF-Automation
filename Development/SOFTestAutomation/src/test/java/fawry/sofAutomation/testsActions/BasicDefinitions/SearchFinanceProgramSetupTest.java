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
import fawry.sofAutomation.dbVerification.basicDefinitions.FinanceProgramsVerifications;
import fawry.sofAutomation.dbVerification.basicDefinitions.SearchVefication;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftClassFactorsPage;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspSettingsPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchFinanceProgramSetupPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.FinancePojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchCspTableVerifications;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchFinanceTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchFinanceProgramSetupTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchFinanceProgramSetupPage setup = new SearchFinanceProgramSetupPage(driver);
		setup.hovertopage();

	}


	@Test(description="Validate Functionality",priority=1, dataProvider="DataProvider")
	public static void searchFinanceProgramSetup(FinancePojo setupobj)  
	{
		test = extent.createTest("Validate Search Finance Program Setup Functionality");

		SoftAssert sa = new SoftAssert();
		SearchFinanceProgramSetupPage setup = new SearchFinanceProgramSetupPage(driver);

		driver.navigate().to(Constants.SEARCH_FINANCE_PROGRAM_SETUP_URL);

		//Inserting Data into common Fields
		setup.CommonFields(setupobj);
		// TAking Action of adding, resetting, confirming or canceling.
		setup.saveOrResetData(setupobj);
		//Collecting Success or fail messages
		String actual = setup.ErrorMessagesAndSuccessMessage(setupobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(setupobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + setupobj.getTestCaseId());

		//assert on DB when changes are made successfully
		if (setupobj.getAction().contains("Success"))
		{
			String x = "";
			FinanceProgramsVerifications search = new FinanceProgramsVerifications();
			SearchFinanceTableVerifications table = new SearchFinanceTableVerifications(driver);
			SearchPojo searchobj = new SearchPojo();
			searchobj.setFinanceProgCode(setupobj.getFinanceProgCode());
			searchobj.setAccountNumber(setupobj.getAccountNumber());
			
			// Assert on Debit values
			ArrayList<FinancePojo> financeSetupInDb = search.searchFinancesSetup(searchobj, "");
			ArrayList<FinancePojo> financeSetupInTable = table.searchFinanceSetup();

			for (int i = 0; i < financeSetupInDb.size(); i++) 
			{
				if(financeSetupInDb.contains(financeSetupInTable.get(i))) {
					x = "true";
				}
				sa.assertTrue(x.equalsIgnoreCase("true"), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + setupobj.getTestCaseId());
			}	
		}
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
			FinancePojo addAccountTestData = new FinancePojo();

			addAccountTestData.setFinanceprogname(resultArray.get(i).get(0).toString());
			addAccountTestData.setFinanceProgCode(resultArray.get(i).get(1).toString());
			addAccountTestData.setAccountNumber(resultArray.get(i).get(2).toString());
			addAccountTestData.setBtc(resultArray.get(i).get(3).toString());
			addAccountTestData.setStatus(resultArray.get(i).get(4).toString());

			addAccountTestData.setTestCaseId(resultArray.get(i).get(5).toString());
			addAccountTestData.setAction(resultArray.get(i).get(6).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(7).toString());




			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
