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
import fawry.sofAutomation.pages.basicDefinitions.AddCspPushAlertPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;


public class AddCspPushAlertTest extends BasicTest { 
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}   

	@Test(description="Validate AddCspPushAlertTest Functionality",priority=10, dataProvider="AddCspPushAlertTestDataProvider")
	public static void AddCspPushAlert(CspPushAlertPojo AddCspPushAlertObj) throws InterruptedException, SQLException  
	{
		test = extent.createTest("Validate AddCspPushAlertTest Functionality");

		SoftAssert sa=new SoftAssert();
		AddCspPushAlertPage page=new AddCspPushAlertPage(driver);
		page.navigateToTab("Basic Definitions", "Add CSP Push Alert Config", driver);
		String 	actual=	page.AddCspPushAlert(AddCspPushAlertObj);
		System.out.println(actual);

		if(actual.equalsIgnoreCase(AddCspPushAlertObj.getExpectedMessage()))
		{
			if(actual.equalsIgnoreCase("Added successfully"))
			{
				CspPushAlertConfigVerifications cspPusAlertVerificationObj=new CspPushAlertConfigVerifications();
				CspPushAlertPojo CspPushAlertInDB=	cspPusAlertVerificationObj.CspPushAlertConfig(AddCspPushAlertObj);
				sa.assertTrue(AddCspPushAlertObj.getCsp().equalsIgnoreCase(CspPushAlertInDB.getCsp()),"Test Case ID = "+ AddCspPushAlertObj.getTestCaseId()+"Csp not matched in Both Excel and Database");
				sa.assertTrue(AddCspPushAlertObj.getAccountType().equalsIgnoreCase(CspPushAlertInDB.getAccountType()), "Test Case ID = "+ AddCspPushAlertObj.getTestCaseId()+ "Account Type not matched in Both Excel and Database");
				sa.assertTrue(AddCspPushAlertObj.getAllowPushAlert().equalsIgnoreCase(CspPushAlertInDB.getAllowPushAlert()), "Test Case ID = "+ AddCspPushAlertObj.getTestCaseId()+ "Allow Push Alert not matched in Both Excel and Database");
				//	System.out.println(cspPusAlertVerificationObj.id);
				//cspPusAlertVerificationObj.update(cspPusAlertVerificationObj.id);
			}
		}
		else if(actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(page.reset(),"Test Case ID = "+ AddCspPushAlertObj.getTestCaseId()+ "Reset Button Functionality In Add Csp Push Alert Config Page Failed");

		}
		else if(actual.equalsIgnoreCase("cancel"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchPOS"),"Test Case ID = "+ AddCspPushAlertObj.getTestCaseId()+ "Cancel Button Functionality In Add Csp Push Alert Config Page Failed");
		}
		else if(actual.equalsIgnoreCase("fail"))
		{
			sa.fail("Test Case ID = "+ AddCspPushAlertObj.getTestCaseId()+ " Add Csp Push Alert Config Failed (no Action Done)");
		}

		sa.assertAll();

	}


	@DataProvider(name = "AddCspPushAlertTestDataProvider")
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
			CspPushAlertPojo AddCspPushAlertObj=new CspPushAlertPojo();
			AddCspPushAlertObj.setTestCaseId(resultArray.get(i).get(0).toString());
			AddCspPushAlertObj.setCsp(resultArray.get(i).get(1).toString());
			AddCspPushAlertObj.setAccountType(resultArray.get(i).get(2).toString());
			AddCspPushAlertObj.setAllowPushAlert(resultArray.get(i).get(3).toString());
			AddCspPushAlertObj.setFlag(resultArray.get(i).get(4).toString());
			AddCspPushAlertObj.setExpectedMessage(resultArray.get(i).get(5).toString());

			result[i][0] = AddCspPushAlertObj;

		}
		return result;

	}
}
