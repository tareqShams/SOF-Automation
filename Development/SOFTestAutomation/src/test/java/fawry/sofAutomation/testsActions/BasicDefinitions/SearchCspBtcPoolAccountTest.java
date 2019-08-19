package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspBtcPoolAccountVerifications;
import fawry.sofAutomation.pages.basicDefinitions.SearchCspBillTypesPoolAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchCspBillTypesPoolAccountTableVerification;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class SearchCspBtcPoolAccountTest  extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate Search_Csp_Btc_Pool_Account Functionality",priority=6, dataProvider="Search_Csp_Btc_Pool_Account_TestDataProvider")
	public static void SearchCspBtcPoolAccount(CspBtcPoolAccountPojo CspBtcPoolAccountObj) throws InterruptedException 
	{
		test = extent.createTest("Validate Search_Csp_Btc_Pool_Account Functionality");

		SoftAssert sa =new SoftAssert();

		SearchCspBillTypesPoolAccountPage searchPageObj=new SearchCspBillTypesPoolAccountPage(driver);
		searchPageObj.navigateToTab("Basic Definitions", "Search CSP Bill Types Pool Account", driver);
		String actual=	searchPageObj.SearchCspBillTypesPoolAccount(CspBtcPoolAccountObj);

		if(actual.equalsIgnoreCase("search"))
		{
			SearchCspBillTypesPoolAccountTableVerification tableSearchObj=new SearchCspBillTypesPoolAccountTableVerification(driver);
			ArrayList<CspBtcPoolAccountPojo> CspBtcPoolAccountInWebTable=tableSearchObj.searchCspBtcPoolAccount();
			System.out.println(CspBtcPoolAccountInWebTable.size());
			CspBtcPoolAccountVerifications DBSearchObj=new CspBtcPoolAccountVerifications();
			ArrayList<CspBtcPoolAccountPojo> CspBtcPoolAccountInDB=DBSearchObj.addCspBtcPoolAccountForSearch(CspBtcPoolAccountObj);
			System.out.println(CspBtcPoolAccountInDB.size());
			if(CspBtcPoolAccountInDB.size()==CspBtcPoolAccountInWebTable.size())
			{
			for(int i=0;i<CspBtcPoolAccountInWebTable.size();i++)
			{
				if(!(CspBtcPoolAccountInDB.contains(CspBtcPoolAccountInWebTable.get(i))))
				{
					sa.fail("Test Case ID = "+ CspBtcPoolAccountObj.getTestCaseId()+"CspBtcPoolAccount doesnt exist in database");		
				}
			}
			}
			else
			{
				sa.fail("Test Case ID = "+ CspBtcPoolAccountObj.getTestCaseId()+"results from database and web table not the same ");
			}
		}
		else if (actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(searchPageObj.reset(),"Test Case ID = "+ CspBtcPoolAccountObj.getTestCaseId()+ "Reset Csp Btc Pool Account Functionality Failed"); 
		}
		else 
		{
			sa.fail("Test Case ID = "+ CspBtcPoolAccountObj.getTestCaseId()+"Search Csp Btc Pool Account Functionality Failed");
		}
		
		
	}

	@DataProvider(name = "Search_Csp_Btc_Pool_Account_TestDataProvider")
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
			CspBtcPoolAccountObj.setPoolAccount(resultArray.get(i).get(1).toString());
			CspBtcPoolAccountObj.setCsp(resultArray.get(i).get(2).toString());
			CspBtcPoolAccountObj.setBillTypeCode(resultArray.get(i).get(3).toString());
			CspBtcPoolAccountObj.setFlag(resultArray.get(i).get(4).toString());
			CspBtcPoolAccountObj.setExpectedMessage(resultArray.get(i).get(5).toString());


			result[i][0] = CspBtcPoolAccountObj;

		}
		return result;

	}
}
