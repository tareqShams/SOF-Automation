package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspPushAlertConfigVerifications;
import fawry.sofAutomation.pages.basicDefinitions.EditCspPushAlertConfigPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchCspPushAlertConfigurationPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class EditCspPushAlertConfigTest extends BasicTest{

	@BeforeClass
	public void login()
	{
		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  

	@Test(description="Validate EditCspPushAlertConfig Functionality",priority=12, dataProvider="EditCspPushAlertConfigTestDataProvider")
	public static void editCspPushAlertCon(CspPushAlertPojo CspPushAlertObj) throws SQLException, InterruptedException  
	{
		test = extent.createTest("Validate EditCspPushAlertConfig Functionality");

		SoftAssert sa=new SoftAssert();
		EditCspPushAlertConfigPage editPage=new EditCspPushAlertConfigPage(driver);
		editPage.navigateToTab("Basic Definitions", "Search CSP Push Alert Config", driver);
		SearchCspPushAlertConfigurationPage search=new SearchCspPushAlertConfigurationPage(driver);
		search.SearchCspPushAlertConfForEdit(CspPushAlertObj);
		String actual=editPage.EditCspPushAlertConfig(CspPushAlertObj);
		System.out.println(actual);

		if(actual.equalsIgnoreCase(CspPushAlertObj.getExpectedMessage()))
		{
			if(actual.equalsIgnoreCase("CSP push alert configuration is added successfully"))
			{
				CspPushAlertConfigVerifications cspPushAlertObjinDB= new CspPushAlertConfigVerifications();
				CspPushAlertPojo CspPushAlertInDB= cspPushAlertObjinDB.CspPushAlertConfig(CspPushAlertObj);
				sa.assertTrue(CspPushAlertObj.getAllowPushAlert().contains(CspPushAlertInDB.getAllowPushAlert()), "Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"Allow Push Alert not matched in Both Excel and Database");

			}
			if(actual.equalsIgnoreCase("CSP push alert configuration is deleted successfully"))
			{
				CspPushAlertConfigVerifications cspPushAlertObjinDB= new CspPushAlertConfigVerifications();
				CspPushAlertPojo CspPushAlertInDB= cspPushAlertObjinDB.CspPushAlertConfig(CspPushAlertObj);
				sa.assertTrue(CspPushAlertInDB==null, "Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"deleted Button Functionality In Edit Csp Push Alert Config Page Failed ");

			}
			else
			{
				sa.assertTrue(actual.equalsIgnoreCase(CspPushAlertObj.getExpectedMessage()),"Test Case ID = "+ CspPushAlertObj.getTestCaseId()+ "Error Massage In Edit Csp Push Alert Config Page Failed ");
			}
		}
		else if (actual.equalsIgnoreCase("back"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchCspPushAlertConfig"),"Test Case ID = "+ CspPushAlertObj.getTestCaseId()+ "Back Button Functionality In Edit Csp Push Alert Config Page Failed ");
		}
		else if (actual.equalsIgnoreCase("fail"))
		{
			sa.fail("Test Case ID = "+ CspPushAlertObj.getTestCaseId()+"NO Action Done In Edit Csp Push Alert Config Page Failed ");
		}

		sa.assertAll();
	}

	@DataProvider(name = "EditCspPushAlertConfigTestDataProvider")
	public Object[][] provideTestData(Method method)
	{
		String methodFullName = method.getName();
		PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
		Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);
		String connectionProperties = prop.getProperty(methodFullName);
		ArrayList<ArrayList<Object>> resultArray = provideTestData(connectionProperties);
		Object[][] result = new Object[resultArray.size()][1];
		System.out.println(resultArray.size());
		for(int i=0; i<resultArray.size(); i++)
		{
			CspPushAlertPojo CspPushAlertObj=new CspPushAlertPojo();
			CspPushAlertObj.setTestCaseId(resultArray.get(i).get(0).toString());
			CspPushAlertObj.setCsp(resultArray.get(i).get(1).toString());
			CspPushAlertObj.setAccountType(resultArray.get(i).get(2).toString());
			CspPushAlertObj.setAllowPushAlert(resultArray.get(i).get(3).toString());
			CspPushAlertObj.setFlag(resultArray.get(i).get(4).toString());
			CspPushAlertObj.setExpectedMessage(resultArray.get(i).get(5).toString());


			result[i][0] = CspPushAlertObj;

		}
		return result;

	}

}
