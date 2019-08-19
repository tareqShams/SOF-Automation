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
import fawry.sofAutomation.pages.basicDefinitions.EditCspBtcPoolAccountPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchCspBillTypesPoolAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class EditCspBtcPoolAccountTest extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate CspBtcPoolAccount Functionality",priority=7, dataProvider="CspBtcPoolAccounttestDataProvider")
	public static void EditCspBtcPoolAccount(CspBtcPoolAccountPojo cspBtcPoolAccountObj) 
	{
		test = extent.createTest("Validate Edit_Csp_Btc_Pool_Account Functionality");

		SoftAssert sa =new SoftAssert();
		EditCspBtcPoolAccountPage editPage=new EditCspBtcPoolAccountPage(driver);
		SearchCspBillTypesPoolAccountPage searchPage=new SearchCspBillTypesPoolAccountPage(driver);
		editPage.navigateToTab("Basic Definitions", "Search CSP Bill Types Pool Account", driver);
		searchPage.SearchCspBillTypesPoolAccountForEditPage(cspBtcPoolAccountObj);
		String actual=editPage.editCspBtcPoolAccount(cspBtcPoolAccountObj);
		if(actual.equalsIgnoreCase(cspBtcPoolAccountObj.getExpectedMessage()))
		{
			CspBtcPoolAccountVerifications SearchInDB=new CspBtcPoolAccountVerifications();
			cspBtcPoolAccountObj.setPoolAccount(cspBtcPoolAccountObj.getNewCode());
			cspBtcPoolAccountObj.setBillTypeCode(editPage.setBillTypeCode);
			if(cspBtcPoolAccountObj.getFlag().equalsIgnoreCase("save")&& !cspBtcPoolAccountObj.getNewCode().isEmpty())
			{
			cspBtcPoolAccountObj.setCsp(editPage.setCsp);
			System.out.println("exchange");
			}
			CspBtcPoolAccountPojo cspBtcPoolAccountInDB=SearchInDB.addCspBtcPoolAccountForEdit(cspBtcPoolAccountObj);

			if (actual.equalsIgnoreCase("Updated successfully"))
			{
				
				System.out.println(cspBtcPoolAccountObj.getCsp()+"              //////////////////////////");
				System.out.println(cspBtcPoolAccountInDB.getCsp());
				sa.assertTrue(((cspBtcPoolAccountObj.getCsp().isEmpty()||cspBtcPoolAccountObj.getCsp()==null)?(true):(cspBtcPoolAccountObj.getCsp().contains(cspBtcPoolAccountInDB.getCsp()))),"Test Case ID = "+ cspBtcPoolAccountObj.getTestCaseId()+ "CSP in Excel And DB Mismatch");
				sa.assertTrue(((cspBtcPoolAccountObj.getBillTypeCode().isEmpty()||cspBtcPoolAccountObj.getBillTypeCode()==null)?(true):(cspBtcPoolAccountObj.getBillTypeCode().contains(cspBtcPoolAccountInDB.getBillTypeCode()))),"Test Case ID = "+ cspBtcPoolAccountObj.getTestCaseId()+ "BillTypeCode in Excel And DB Mismatch");
				sa.assertTrue(((cspBtcPoolAccountObj.getNewCode().isEmpty()||cspBtcPoolAccountObj.getNewCode()==null)?(true):(cspBtcPoolAccountObj.getNewCode().contains(cspBtcPoolAccountInDB.getPoolAccount()))), "Test Case ID = "+ cspBtcPoolAccountObj.getTestCaseId()+"PoolAccount in Excel And DB Mismatch");



			}
			else if(actual.equalsIgnoreCase("Deleted successfully"))
			{
				sa.assertTrue(cspBtcPoolAccountInDB==null, "Test Case ID = "+ cspBtcPoolAccountObj.getTestCaseId()+"Delete Button Functionality in Edit Csp Btc Pool Account Faild");

			}
		}
		else if(actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(editPage.reset(), "Test Case ID = "+ cspBtcPoolAccountObj.getTestCaseId()+"Reset Button Functionality in Edit Csp Btc Pool Account Faild");
		}
		else if(actual.equalsIgnoreCase("back"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchCSP_BTC_POOL_ACCOUNT"),"Test Case ID = "+ cspBtcPoolAccountObj.getTestCaseId()+ "Back Button Functionlity In Edit Csp Btc Pool Account Page Failed");
		}

	}

	@DataProvider(name = "CspBtcPoolAccounttestDataProvider")
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
			CspBtcPoolAccountObj.setNewCode(resultArray.get(i).get(2).toString());
			CspBtcPoolAccountObj.setCsp(resultArray.get(i).get(3).toString());
			CspBtcPoolAccountObj.setBillTypeCode(resultArray.get(i).get(4).toString());
			CspBtcPoolAccountObj.setFlag(resultArray.get(i).get(5).toString());
			CspBtcPoolAccountObj.setExpectedMessage(resultArray.get(i).get(6).toString());


			result[i][0] = CspBtcPoolAccountObj;

		}
		return result;

	}
}
