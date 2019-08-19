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
import fawry.sofAutomation.dbVerification.basicDefinitions.VelocityCriteriaVerifications;
import fawry.sofAutomation.pages.basicDefinitions.AddVelocityCriteriaPage;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftClassFactorsPage;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspSettingsPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchFinanceProgramSetupPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.FinancePojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchCspTableVerifications;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchFinanceTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddVelocityCriteriaTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		AddVelocityCriteriaPage setup = new AddVelocityCriteriaPage(driver);
		setup.hovertopage();

	}


	@Test(description="Validate Functionality",priority=1, dataProvider="DataProvider")
	public static void addVelocityCriteria(VelocityPojo velocityobj)  
	{
		test = extent.createTest("Validate Add Velocity Criteria Functionality");

		SoftAssert sa = new SoftAssert();
		AddVelocityCriteriaPage setup = new AddVelocityCriteriaPage(driver);

		driver.navigate().to(Constants.ADD_VELOCITY_CRITERIA_URL);

		//Inserting Data into common Fields
		setup.CommonFields(velocityobj);
		// TAking Action of adding, resetting, confirming or canceling.
		setup.saveOrResetData(velocityobj);
		//Collecting Success or fail messages
		String actual = setup.ErrorMessagesAndSuccessMessage(velocityobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(velocityobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + velocityobj.getTestCaseId());

		//assert on DB when changes are made successfully
		if (velocityobj.getAction().contains("Success"))
		{
			VelocityCriteriaVerifications search = new VelocityCriteriaVerifications();
			SearchPojo searchobj = new SearchPojo();

			searchobj.setPeriod(velocityobj.getPeriod());
			searchobj.setPeriodUnit(velocityobj.getPeriodUnit());
			searchobj.setCsp(velocityobj.getCsp());
			searchobj.setAccountType(velocityobj.getAccountType());
			searchobj.setMeasureType(velocityobj.getMeasureType());
			searchobj.setErrorCode(velocityobj.getErrorCode());

			// Assert on Debit values
			VelocityPojo velocityInDb = search.velocityCriteriaVerification(searchobj, "Add").get(0);

			sa.assertTrue(velocityobj.getPeriod().equalsIgnoreCase(velocityInDb.getPeriod()), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + velocityobj.getTestCaseId());
			sa.assertTrue(velocityobj.getCsp().equalsIgnoreCase(velocityInDb.getCsp()), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + velocityobj.getTestCaseId());
			sa.assertTrue(velocityobj.getAccountType().equalsIgnoreCase(velocityInDb.getAccountType()), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + velocityobj.getTestCaseId());
			sa.assertTrue(velocityobj.getErrorCode().equalsIgnoreCase(velocityInDb.getErrorCode()), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + velocityobj.getTestCaseId());
			sa.assertTrue(velocityobj.getPeriodUnit().equalsIgnoreCase(velocityInDb.getPeriodUnit()), AssertionErrorMessages.SEARCH_TABLE_DB+ "In test Case With ID of" + velocityobj.getTestCaseId());

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
			VelocityPojo addAccountTestData = new VelocityPojo();

			addAccountTestData.setPeriod(resultArray.get(i).get(0).toString());
			addAccountTestData.setPeriodUnit(resultArray.get(i).get(1).toString());
			addAccountTestData.setMeasureType(resultArray.get(i).get(2).toString());
			addAccountTestData.setBillType(resultArray.get(i).get(3).toString());
			addAccountTestData.setCsp(resultArray.get(i).get(4).toString());
			addAccountTestData.setVelocityAction(resultArray.get(i).get(5).toString());
			addAccountTestData.setAccountType(resultArray.get(i).get(6).toString());
			addAccountTestData.setMeasureValue(resultArray.get(i).get(7).toString());
			addAccountTestData.setErrorCode(resultArray.get(i).get(8).toString());
			addAccountTestData.setCustomerCategory(resultArray.get(i).get(9).toString());

			addAccountTestData.setTestCaseId(resultArray.get(i).get(10).toString());
			addAccountTestData.setAction(resultArray.get(i).get(11).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(12).toString());




			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
