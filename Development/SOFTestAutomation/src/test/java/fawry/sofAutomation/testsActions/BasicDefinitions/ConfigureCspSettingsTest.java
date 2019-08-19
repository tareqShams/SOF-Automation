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
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftClassFactorsPage;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspSettingsPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class ConfigureCspSettingsTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		ConfigreCspSettingsPage settings = new ConfigreCspSettingsPage(driver);
		settings.hovertopage();

	}


	@Test(description="Validate configure CSP Settings Functionality",priority=1, dataProvider="DataProvider")
	public static void configureCSPSettings(CSPFeesPojo settingsobj)  
	{
		test = extent.createTest("Validate Cofiguring CSP Settings Functionality");

		SoftAssert sa = new SoftAssert();
		ConfigreCspSettingsPage settings = new ConfigreCspSettingsPage(driver);

		driver.navigate().to(Constants.CSP_SETTINGS_URL);

		//Inserting Data into common Fields
		settings.CommonFields(settingsobj);
		// TAking Action of adding, resetting, confirming or canceling.
		settings.saveOrResetData(settingsobj);
		//Collecting Success or fail messages
		String actual = settings.ErrorMessagesAndSuccessMessage(settingsobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(settingsobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + settingsobj.getTestCaseId());

		//assert on DB when changes are made successfully
		if (settingsobj.getAction().contains("Success"))
		{
			String x = "";
			SearchVefication search = new SearchVefication();
			SearchPojo searchobj = new SearchPojo();
			searchobj.setCsp(settingsobj.getCsp());

			// Assert on Debit values
			searchobj.setAccountType("1");
			ArrayList<CSPFeesPojo> debitInDb = search.searchCspDebitCreditSettings(searchobj, "");
			ArrayList<CSPFeesPojo> debit = settings.checkClickedDebitButtons(settingsobj);

			for (int i = 0; i < debitInDb.size(); i++) 
			{
				if(!debitInDb.contains(debit.get(i))) {
					x = "true";
				}
				sa.assertTrue(x.equalsIgnoreCase("true"), AssertionErrorMessages.DEBIT_NATURE_TABLE_DB+ "In test Case With ID of" + settingsobj.getTestCaseId());
			}
			// Assert on Credit values
			searchobj.setAccountType("2");
			ArrayList<CSPFeesPojo> creditInDb = search.searchCspDebitCreditSettings(searchobj, "");
			ArrayList<CSPFeesPojo> credit = settings.checkClickedCredititButtons();

			for (int i = 0; i < creditInDb.size(); i++) 
			{
				if(!creditInDb.contains(credit.get(i))) {
					x = "true";
				}
				sa.assertTrue(x.equalsIgnoreCase("true"), AssertionErrorMessages.CREDIT_NATURE_TABLE_DB+ "In test Case With ID of" + settingsobj.getTestCaseId());
			}
			// Assert on Terminal types values
			ArrayList<CSPFeesPojo> terminaInDb = search.searchCspTerminalTypesSettings(searchobj, "");
			ArrayList<CSPFeesPojo> term = settings.checkClickedTerminalButtons();

			for (int i = 0; i < terminaInDb.size(); i++) 
			{
				if(!terminaInDb.contains(term.get(i))) {
					x = "true";
				}
				sa.assertTrue(x.equalsIgnoreCase("true"), AssertionErrorMessages.TERMINAL_TYPES_TABLE_DB+ "In test Case With ID of" + settingsobj.getTestCaseId());
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
			CSPFeesPojo addAccountTestData = new CSPFeesPojo();

			addAccountTestData.setCsp(resultArray.get(i).get(0).toString());
			addAccountTestData.setDebitNature(resultArray.get(i).get(1).toString());
			addAccountTestData.setCreditNature(resultArray.get(i).get(2).toString());
			addAccountTestData.setTerminalTypes(resultArray.get(i).get(3).toString());

			addAccountTestData.setTestCaseId(resultArray.get(i).get(4).toString());
			addAccountTestData.setAction(resultArray.get(i).get(5).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(6).toString());




			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
