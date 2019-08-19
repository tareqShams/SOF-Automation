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
import fawry.sofAutomation.dbVerification.basicDefinitions.FinanceProgramVerifications;
import fawry.sofAutomation.pages.basicDefinitions.AddFinanceProgramPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class AddFinanceProgramTest extends BasicTest{

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}   

	@Test(description="Validate AddFinanceProgram Functionality",priority=13, dataProvider="AddFinanceProgramTestDataProvider")
	public static void AddFinanceProgram(FinanceProgramPojo FinanceProgramObj) throws InterruptedException, SQLException  
	{
		test = extent.createTest("Validate AddFinanceProgram Functionality");

		SoftAssert sa =new SoftAssert();
		AddFinanceProgramPage financeProgramPage=new AddFinanceProgramPage(driver);
		financeProgramPage.navigateToTab("Basic Definitions", "Add Finance Program", driver);
		String actual=financeProgramPage.AddFinanceProgram(FinanceProgramObj);
		System.out.println(actual);
		System.out.println(FinanceProgramObj.getExpectedMessage());

		if(FinanceProgramObj.getFlag().equals("save"))
		{
			if(FinanceProgramObj.getExpectedMessage().equalsIgnoreCase("Added successfully")  
					&& actual.equalsIgnoreCase("Added successfully"))
			{
				FinanceProgramVerifications financeProgVerify=new FinanceProgramVerifications();
				FinanceProgramPojo financeProgramInDB= financeProgVerify.FinanceProgramVerify(FinanceProgramObj);
				sa.assertTrue(FinanceProgramObj.getProgramType().equalsIgnoreCase(financeProgramInDB.getProgramType()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+" Program Type not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getCode().equalsIgnoreCase(financeProgramInDB.getCode()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Code not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getName().equalsIgnoreCase(financeProgramInDB.getName()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+" Name not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getDescription().equalsIgnoreCase(financeProgramInDB.getDescription()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Description not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getMaximumFacilityLimit().equalsIgnoreCase(financeProgramInDB.getMaximumFacilityLimit()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Maximum Facility Limit not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getValidateSetupTime().contains(financeProgramInDB.getValidateSetupTime()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Validate Setup Time not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getDisableAccountRouting().contains(financeProgramInDB.getDisableAccountRouting()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Disable Account Routing not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getMainAccountReturn().contains(financeProgramInDB.getMainAccountReturn()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Main Account Return not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getEnableEStatment().contains(financeProgramInDB.getEnableEStatment()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Enable E-Statment not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getNumOfDays().equalsIgnoreCase(financeProgramInDB.getNumOfDays()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ " Number Of Days not matched in Both Excel and Database");

				System.out.println(FinanceProgramObj.getDueDatePerVisits());
				System.out.println(financeProgramInDB.getDueDatePerVisits());

				sa.assertTrue(FinanceProgramObj.getDueDatePerVisits().contains(financeProgramInDB.getDueDatePerVisits()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Due Date Per Visits not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getBlockSetupInDebit().contains(financeProgramInDB.getBlockSetupInDebit()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Block Setup In Debit not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getReactivateSetupInCredit().contains(financeProgramInDB.getReactivateSetupInCredit()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"Reactivate Setup In Credit not matched in Both Excel and Database");
			}
			else
			{
				sa.assertTrue(actual.equalsIgnoreCase(FinanceProgramObj.getExpectedMessage()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Actual Error Massage not Matched With The Expected Error Massage in Add Finance Program Page  ");
			}
		}
		else if (FinanceProgramObj.getFlag().equalsIgnoreCase("reset"))
		{
			sa.assertTrue(actual.equalsIgnoreCase("reset"),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Error Happend When Reset Button Clicked in Add Finance Program Page");
			sa.assertTrue(financeProgramPage.reset(),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Reset Button Functionality in Add Finance Program Page Faild");
		}
		else if (FinanceProgramObj.getFlag().equalsIgnoreCase("cancel"))
		{
			sa.assertTrue(actual.equalsIgnoreCase("cancel"),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Error Happend When cancel Button Clicked in Add Finance Program Page");
			sa.assertTrue(driver.getCurrentUrl().contains("SearchPOS"),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Cancel Button Functionality in Add Finance Program Page Faild");
		}
		else {
			sa.fail("Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"No Action Done In Add Finance Program ");
		}
		sa.assertAll();
	}



	@DataProvider(name = "AddFinanceProgramTestDataProvider")
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
			FinanceProgramPojo FinanceProgramObj=new FinanceProgramPojo();
			FinanceProgramObj.setTestCaseId(resultArray.get(i).get(0).toString());
			FinanceProgramObj.setProgramType(resultArray.get(i).get(1).toString());
			FinanceProgramObj.setCode(resultArray.get(i).get(2).toString());
			FinanceProgramObj.setName(resultArray.get(i).get(3).toString());
			FinanceProgramObj.setDescription(resultArray.get(i).get(4).toString());
			FinanceProgramObj.setMaximumFacilityLimit(resultArray.get(i).get(5).toString());
			FinanceProgramObj.setValidateSetupTime(resultArray.get(i).get(6).toString());
			FinanceProgramObj.setDisableAccountRouting(resultArray.get(i).get(7).toString());
			FinanceProgramObj.setMainAccountReturn(resultArray.get(i).get(8).toString());
			FinanceProgramObj.setEnableEStatment(resultArray.get(i).get(9).toString());
			FinanceProgramObj.setNumOfDays(resultArray.get(i).get(10).toString());
			FinanceProgramObj.setDueDatePerVisits(resultArray.get(i).get(11).toString());
			FinanceProgramObj.setBlockSetupInDebit(resultArray.get(i).get(12).toString());
			FinanceProgramObj.setReactivateSetupInCredit(resultArray.get(i).get(13).toString());
			FinanceProgramObj.setFlag(resultArray.get(i).get(14).toString());
			FinanceProgramObj.setExpectedMessage(resultArray.get(i).get(15).toString());

			result[i][0] = FinanceProgramObj;

		}
		return result;

	}
}
