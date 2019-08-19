package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.utils.PropertiesFilesHandler;
import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspBillTypeVerifications;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pages.basicDefinitions.AddCspBillTypePage;

public class AddCspBillTypeTest extends BasicTest {

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate addCspBillType Functionality",priority=1, dataProvider="AddCspBillTypetestDataProvider")
	public static void addCspBillType(CspBillTypePojo addCspBillTypeObject)  
	{
		test = extent.createTest("Validate addCspBillType Functionality");

		CspBillTypeVerifications searchCspBillTypeInDB=new CspBillTypeVerifications();
		SoftAssert sa=new SoftAssert();
		AddCspBillTypePage page=new  AddCspBillTypePage(driver);
		page.navigateToTab("Basic Definitions", "Add CSP Bill Type", driver);
		String actual=page.addCspBillType(addCspBillTypeObject);
		if(addCspBillTypeObject.getExpectedMessage().equalsIgnoreCase(actual))
		{
			if(actual.equalsIgnoreCase("Added successfully"))
			{
				CspBillTypePojo cspBillTypeInDB=searchCspBillTypeInDB.addCspBillType(addCspBillTypeObject);
				sa.assertTrue(addCspBillTypeObject.getCustomerServiceProvider().equalsIgnoreCase(cspBillTypeInDB.getCustomerServiceProvider()), "Customer Service Provide not matched in Both Excel and Database");
				sa.assertTrue(addCspBillTypeObject.getBillTypeCode().equalsIgnoreCase(cspBillTypeInDB.getBillTypeCode()), " Bill Type  Code not matched in Both Excel and Database");
				sa.assertTrue(addCspBillTypeObject.getAmount().equalsIgnoreCase(cspBillTypeInDB.getAmount()), " Amount not matched in Both Excel and Database ");
				sa.assertTrue(addCspBillTypeObject.getLoyaltyNumOfMonths().equalsIgnoreCase(cspBillTypeInDB.getLoyaltyNumOfMonths()), " Loyalty Num Of Months not matched in Both Excel and Database ");

			}
			else 
			{
				sa.assertTrue(addCspBillTypeObject.getExpectedMessage().equalsIgnoreCase(actual), "Customer Service Provide not matched in Both Excel and Database");
			}
		}
		else if(actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(page.reset(), "Reset Functionality in Add Csp Bill Type Failed");
		}
		else 
		{
			sa.fail("Actual and Expectted Results not Matched");
		}
		sa.assertAll();

	}

	@DataProvider(name = "AddCspBillTypetestDataProvider")
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
			CspBillTypePojo cspPojo=new CspBillTypePojo();

			cspPojo.setTestCaseId(resultArray.get(i).get(0).toString());
			cspPojo.setCustomerServiceProvider(resultArray.get(i).get(1).toString());
			cspPojo.setBillTypeCode(resultArray.get(i).get(2).toString());
			cspPojo.setAmount(resultArray.get(i).get(3).toString());
			cspPojo.setLoyaltyNumOfMonths(resultArray.get(i).get(4).toString());
			cspPojo.setExpectedMessage(resultArray.get(i).get(6).toString());
			cspPojo.setFlag(resultArray.get(i).get(5).toString());

			result[i][0] = cspPojo;

		}
		return result;

	}

}
