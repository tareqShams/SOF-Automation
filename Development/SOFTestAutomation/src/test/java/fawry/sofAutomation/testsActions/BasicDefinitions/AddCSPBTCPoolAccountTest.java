package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.utils.PropertiesFilesHandler;
import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pages.basicDefinitions.AddCspBtcPoolAccountPage;

public class AddCSPBTCPoolAccountTest  extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate AddCSPBTCPoolAccount Functionality",priority=4, dataProvider="AddCSPBTCPoolAccountTestDataProvider")
	public static void AddCSPBTCPoolAccount(CspBtcPoolAccountPojo CspBtcPoolAccountObj) throws InterruptedException, SQLException  
	{
		test = extent.createTest("Validate AddCSPBTCPoolAccount Functionality");

		SoftAssert sa=new SoftAssert();
		AddCspBtcPoolAccountPage page=new AddCspBtcPoolAccountPage(driver);
		page.navigateToTab("Basic Definitions", "Add CSP Bill Type Pool Account", driver);
		String actual= page.addCspBtcPoolAccount(CspBtcPoolAccountObj);
		sa.assertTrue(actual.equalsIgnoreCase(CspBtcPoolAccountObj.getExpectedMessage()), "adsfdgfh");
		
		sa.assertAll();
	}


	@DataProvider(name = "AddCSPBTCPoolAccountTestDataProvider")
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
			CspBtcPoolAccountPojo CspBtcPoolAccountObj=new CspBtcPoolAccountPojo();
		
			CspBtcPoolAccountObj.setTestCaseId(resultArray.get(i).get(0).toString());
			CspBtcPoolAccountObj.setCsp(resultArray.get(i).get(1).toString());
			CspBtcPoolAccountObj.setBillTypeCode(resultArray.get(i).get(2).toString());
			CspBtcPoolAccountObj.setPoolAccount(resultArray.get(i).get(3).toString());
			CspBtcPoolAccountObj.setFlag(resultArray.get(i).get(4).toString());
			CspBtcPoolAccountObj.setExpectedMessage(resultArray.get(i).get(5).toString());
			result[i][0] = CspBtcPoolAccountObj;

		}
		return result;

	}
}
