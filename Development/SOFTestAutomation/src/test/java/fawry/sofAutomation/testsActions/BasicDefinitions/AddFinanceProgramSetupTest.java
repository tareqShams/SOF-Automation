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
import fawry.sofAutomation.dbVerification.basicDefinitions.FinanceProgramSetupVerifications;
import fawry.sofAutomation.pages.basicDefinitions.AddFinanceProgramSetupPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramSetupPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class AddFinanceProgramSetupTest extends BasicTest {

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}   

	@Test(description="Validate AddFinanceProgramSetup Functionality",priority=14, dataProvider="AddFinanceProgramSetupTestDataProvider")
	public static void AddFinanceProgramSetup(FinanceProgramSetupPojo FinanceProgramSetupObj) throws InterruptedException, SQLException  
	{
		test = extent.createTest("Validate AddFinanceProgramSetup Functionality");

		SoftAssert sa =new SoftAssert();
		FinanceProgramSetupVerifications financeProgSetupVerify=new FinanceProgramSetupVerifications();
		AddFinanceProgramSetupPage financeProgramSetupPage=new AddFinanceProgramSetupPage(driver);
		financeProgramSetupPage.navigateToTab("Basic Definitions", "Finance Program Setup", driver);
		String financeProgram=financeProgSetupVerify.getFinanceProgram(FinanceProgramSetupObj.getFlag());
		String mainAccount = null;
		String subAccount = null;
		if(FinanceProgramSetupObj.getFlag().contains("subaccount"))
		{
			mainAccount=financeProgSetupVerify.getAccount("");
			subAccount=financeProgSetupVerify.getAccount("NOT");
		}
		else if(FinanceProgramSetupObj.getFlag().contains("btc"))
		{
			mainAccount=financeProgSetupVerify.getAccount("");
		}
		FinanceProgramSetupObj.setFinanceProgram(financeProgram);
		FinanceProgramSetupObj.setMainAccount(mainAccount);
		FinanceProgramSetupObj.setSubAccount(subAccount);
		String actual=financeProgramSetupPage.FinanceProgramSetup(FinanceProgramSetupObj);
		System.out.println(actual);
		System.out.println(FinanceProgramSetupObj.getExpectedMessage());
		FinanceProgramSetupObj.setBillTypes(financeProgramSetupPage.billType);
		FinanceProgramSetupObj.setBillTypeProfiles(financeProgramSetupPage.billTypeProfile);
		System.out.println(FinanceProgramSetupObj.getBillTypes().size());

		if(FinanceProgramSetupObj.getFlag().equals("save"))
		{
			if(actual.equalsIgnoreCase("Added successfully"))
			{
				FinanceProgramSetupPojo financeProgramInDB= financeProgSetupVerify.financeProgramSetupVerify(FinanceProgramSetupObj);
				sa.assertTrue(FinanceProgramSetupObj.getFinanceProgram().equalsIgnoreCase(financeProgramInDB.getFinanceProgram()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Finance Program not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getMainAccount().equalsIgnoreCase(financeProgramInDB.getMainAccount()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Main Account not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getSubAccount().equalsIgnoreCase(financeProgramInDB.getSubAccount()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Sub Account not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getAccountClassification().equalsIgnoreCase(financeProgramInDB.getAccountClassification()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Account Classification not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getMaximumAmount().equalsIgnoreCase(financeProgramInDB.getMaximumAmount()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Maximum Amount not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getOpenDate().contains(financeProgramInDB.getOpenDate()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Open Date not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getCloseDate().contains(financeProgramInDB.getCloseDate()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Close Date not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getEnableTime().contains(financeProgramInDB.getEnableTime()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Enable Time not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getDisableTime().contains(financeProgramInDB.getDisableTime()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Disable Time not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getNumOfMinFaultPayments().equalsIgnoreCase(financeProgramInDB.getNumOfMinFaultPayments()), "Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+"Num Of Min Fault Payments not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramSetupObj.getNumOfFullFaultPayments().contains(financeProgramInDB.getNumOfFullFaultPayments()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Num Of Full Fault Payments not matched in Both Excel and Database");
				System.out.println(FinanceProgramSetupObj.getBillTypes().get(0));
				if(FinanceProgramSetupObj.getBillTypes().size()!=0)
				{
					if(financeProgramInDB.getBillTypes().size()==FinanceProgramSetupObj.getBillTypes().size())
					{
						// check that same users added to group and DB
						for(int i=0 ;i<FinanceProgramSetupObj.getBillTypes().size();i++)
						{
							//System.out.println(searchGroupDB.get(0).getUsersData().get(i)+"////////////////////////"+AddGroupObject.getUsersData().get(i));
							sa.assertTrue(financeProgramInDB.getBillTypes().contains(FinanceProgramSetupObj.getBillTypes().get(i)), "Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+"Bill Types in Excel And DB Mismatch");
						}
					}
				}
				if(FinanceProgramSetupObj.getBillTypeProfiles().size()!=0)
				{
					if(financeProgramInDB.getBillTypeProfiles().size()==FinanceProgramSetupObj.getBillTypeProfiles().size())
					{
						// check that same users added to group and DB
						for(int i=0 ;i<FinanceProgramSetupObj.getBillTypeProfiles().size();i++)
						{
							//System.out.println(searchGroupDB.get(0).getUsersData().get(i)+"////////////////////////"+AddGroupObject.getUsersData().get(i));
							sa.assertTrue(financeProgramInDB.getBillTypeProfiles().contains(FinanceProgramSetupObj.getBillTypeProfiles().get(i)),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Bill Type Profiles in Excel And DB Mismatch");
						}
					}
				}
				financeProgSetupVerify.update();
			}
			else
			{
				sa.assertTrue(actual.equalsIgnoreCase(FinanceProgramSetupObj.getExpectedMessage()),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Actual Error Massage not Matched With The Expected Error Massage in Add Finance Program Page  ");
			}

		}
		else if (FinanceProgramSetupObj.getFlag().equalsIgnoreCase("reset"))
		{
			sa.assertTrue(actual.equalsIgnoreCase("reset"),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Error Happend When Reset Button Clicked in Add Finance Program Page");
			sa.assertTrue(financeProgramSetupPage.reset(),"Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "Reset Button Functionality in Add Finance Program Page Faild");
		}

		else {
			sa.fail("Test Case ID = "+ FinanceProgramSetupObj.getTestCaseId()+ "No Action Done In Add Finance Program ");
		}
		sa.assertAll();
	}



	@DataProvider(name = "AddFinanceProgramSetupTestDataProvider")
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
			ArrayList<String> str=new ArrayList<>();
			ArrayList<String> str2=new ArrayList<>();

			FinanceProgramSetupPojo FinanceProgramSetupObj=new FinanceProgramSetupPojo();

			FinanceProgramSetupObj.setFinanceProgram(resultArray.get(i).get(0).toString());
			FinanceProgramSetupObj.setMainAccount(resultArray.get(i).get(1).toString());
			FinanceProgramSetupObj.setSubAccount(resultArray.get(i).get(2).toString());
			FinanceProgramSetupObj.setAccountClassification(resultArray.get(i).get(3).toString());
			FinanceProgramSetupObj.setMaximumAmount(resultArray.get(i).get(4).toString());
			FinanceProgramSetupObj.setOpenDate(resultArray.get(i).get(5).toString());
			FinanceProgramSetupObj.setCloseDate(resultArray.get(i).get(6).toString());
			FinanceProgramSetupObj.setEnableTime(resultArray.get(i).get(7).toString());
			FinanceProgramSetupObj.setDisableTime(resultArray.get(i).get(8).toString());
			FinanceProgramSetupObj.setNumOfMinFaultPayments(resultArray.get(i).get(9).toString());
			FinanceProgramSetupObj.setNumOfFullFaultPayments(resultArray.get(i).get(10).toString());
			FinanceProgramSetupObj.setRollOver(resultArray.get(i).get(11).toString());
			FinanceProgramSetupObj.setFlag(resultArray.get(i).get(14).toString());
			FinanceProgramSetupObj.setExpectedMessage(resultArray.get(i).get(15).toString());

			result[i][0] = FinanceProgramSetupObj;

		}
		return result;

	}
}
