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
import fawry.sofAutomation.dbVerification.basicDefinitions.VelocityCriteriaVerifications;
import fawry.sofAutomation.pages.basicDefinitions.SearchFinanceProgramSetupPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchVelocityCriteriaPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchVelocityTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class SearchVelocityCriteriaTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		SearchVelocityCriteriaPage setup = new SearchVelocityCriteriaPage(driver);
		setup.hovertopage();

	}


	@Test(description="Validate Functionality",priority=1, dataProvider="DataProvider")
	public static void searchVelocityCriteria(VelocityPojo setupobj)  
	{
		test = extent.createTest("Validate Search Velocity Criteria Functionality");

		SoftAssert sa = new SoftAssert();
		SearchVelocityCriteriaPage velocity = new SearchVelocityCriteriaPage(driver);

		driver.navigate().to(Constants.SEARCH_VELOCITY_CRITERIA_URL);

		//Inserting Data into common Fields
		velocity.CommonFields(setupobj);
		// TAking Action of adding, resetting, confirming or canceling.
		velocity.saveOrResetData(setupobj);
		//Collecting Success or fail messages
		String actual = velocity.ErrorMessagesAndSuccessMessage(setupobj);
		System.out.println(actual);
		sa.assertTrue(actual.contains(setupobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + setupobj.getTestCaseId());

		//assert on DB when changes are made successfully
		if (setupobj.getAction().contains("Success"))
		{
			String x = "";
			VelocityCriteriaVerifications search = new VelocityCriteriaVerifications();
			SearchVelocityTableVerifications table = new SearchVelocityTableVerifications(driver);
			SearchPojo searchobj = new SearchPojo();
			searchobj.setCsp(setupobj.getCsp());
			searchobj.setPeriodUnit(setupobj.getPeriodUnit());
			searchobj.setMeasureType(setupobj.getMeasureType());
			searchobj.setBillType(setupobj.getBillType());
			searchobj.setVelocityAction(setupobj.getVelocityAction());
			searchobj.setAccountType(setupobj.getAccountType());

			// Assert Web Table And DB Values
			ArrayList<VelocityPojo> velocityInDb = search.velocityCriteriaVerification(searchobj, "Search");
			ArrayList<VelocityPojo> velocityInTable = table.searchVelocity();

			for (int i = 0; i < velocityInDb.size(); i++) 
			{
				if(velocityInDb.contains(velocityInTable.get(i))) {
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
			VelocityPojo addAccountTestData = new VelocityPojo();

			addAccountTestData.setCsp(resultArray.get(i).get(0).toString());
			addAccountTestData.setPeriodUnit(resultArray.get(i).get(1).toString());
			addAccountTestData.setMeasureType(resultArray.get(i).get(2).toString());
			addAccountTestData.setBillType(resultArray.get(i).get(3).toString());
			addAccountTestData.setVelocityAction(resultArray.get(i).get(4).toString());
			addAccountTestData.setAccountType(resultArray.get(i).get(5).toString());

			addAccountTestData.setTestCaseId(resultArray.get(i).get(6).toString());
			addAccountTestData.setAction(resultArray.get(i).get(7).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(8).toString());

			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
