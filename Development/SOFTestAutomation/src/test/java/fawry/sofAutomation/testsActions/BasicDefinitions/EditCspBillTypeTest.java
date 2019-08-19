package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.util.ArrayList;
import java.util.Properties;
import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.utils.PropertiesFilesHandler;
import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;
import fawry.sofAutomation.pages.basicDefinitions.SearchCspBillTypePage;
import fawry.sofAutomation.pages.basicDefinitions.EditCspBillTypePage;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspBillTypeVerifications;

public class EditCspBillTypeTest extends BasicTest {
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate searchCspBillType Functionality",priority=2, dataProvider="SearchCspBillTypetestDataProvider")
	public static void updateCspBillType(CspBillTypePojo searchCspBillTypeObject) throws InterruptedException  
	{
		test = extent.createTest("Validate searchCspBillType Functionality");

		SoftAssert sa=new SoftAssert();
		SearchCspBillTypePage searchPage=new  SearchCspBillTypePage(driver);
		searchPage.navigateToTab("Basic Definitions", "Search CSP Bill Types", driver);
		searchPage.searchCspBillType(searchCspBillTypeObject);
		EditCspBillTypePage updatePage= new EditCspBillTypePage(driver);
		String actual=updatePage.updateCspBillType(searchCspBillTypeObject);
		CspBillTypeVerifications cspDatabaseVerification=new CspBillTypeVerifications();
		searchCspBillTypeObject.setCustomerServiceProvider(updatePage.CustomerServiceProvider);

		if(actual.contains(searchCspBillTypeObject.getExpectedMessage()))
		{
			if(searchCspBillTypeObject.getUpdatebillTypeCode() != null || !searchCspBillTypeObject.getUpdatebillTypeCode().isEmpty())
			{
				searchCspBillTypeObject.setBillTypeCode(searchCspBillTypeObject.getUpdatebillTypeCode());
			}
			
			CspBillTypePojo cspDataFromDatabase=cspDatabaseVerification.addCspBillType(searchCspBillTypeObject);
			
			if(actual.contains("Updated successfully"))
			{
				sa.assertTrue(((searchCspBillTypeObject.getBillTypeCode().isEmpty()||searchCspBillTypeObject.getBillTypeCode()==null)?(true):(searchCspBillTypeObject.getBillTypeCode().contains(cspDataFromDatabase.getBillTypeCode()))), "Bill Type Code in Excel And DB Mismatch");
				sa.assertTrue(((searchCspBillTypeObject.getAmount().isEmpty()||searchCspBillTypeObject.getAmount()==null)?(true):(searchCspBillTypeObject.getAmount().contains(cspDataFromDatabase.getAmount()))), "Amount in Excel And DB Mismatch");
				sa.assertTrue(((searchCspBillTypeObject.getLoyaltyNumOfMonths().isEmpty()||searchCspBillTypeObject.getLoyaltyNumOfMonths()==null)?(true):(searchCspBillTypeObject.getLoyaltyNumOfMonths().contains(cspDataFromDatabase.getLoyaltyNumOfMonths()))), "LoyaltyNumOfMonths in Excel And DB Mismatch");
			}

			else if(actual.contains("CSP Bill Type Code is deleted successfully"))
			{
				sa.assertTrue(cspDataFromDatabase == null ,"Delete Button Functionality in Update Csp Bill Type Faild");
			}
		}

		else if (actual.contains("reset"))
		{
			sa.assertTrue(searchPage.reset(), "Reset Button Functionality in Update Csp Bill Type Faild");
		}

		else if (actual.contains("back"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchCSPBillTypes"), "Back Button Functionlity In Update CSP Bill Type Page");
		}

		else if (actual.contains("fail")) 
		{
			sa.fail(" Update CSP Bill Type Functionality ");
		}

		sa.assertAll();
	}


	@DataProvider(name = "SearchCspBillTypetestDataProvider")
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
			cspPojo.setUpdatebillTypeCode(resultArray.get(i).get(3).toString());
			cspPojo.setAmount(resultArray.get(i).get(4).toString());
			cspPojo.setLoyaltyNumOfMonths(resultArray.get(i).get(5).toString());
			cspPojo.setFlag(resultArray.get(i).get(6).toString());
			cspPojo.setExpectedMessage(resultArray.get(i).get(7).toString());
			result[i][0] = cspPojo;

		}
		return result;

	}
}
