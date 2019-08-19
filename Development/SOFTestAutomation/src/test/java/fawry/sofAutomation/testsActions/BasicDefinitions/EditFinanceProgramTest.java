package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.dbVerification.basicDefinitions.FinanceProgramVerifications;
import fawry.sofAutomation.pages.basicDefinitions.EditFinanceProgramPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchFinanceProgramPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class EditFinanceProgramTest extends BasicTest {

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate EditFinanceProgram Functionality",priority=14, dataProvider="EditFinanceProgramTestDataProvider")
	public static void EditFinanceProgram(FinanceProgramPojo FinanceProgramObj) throws InterruptedException 
	{
		test = extent.createTest("Validate EditFinanceProgram Functionality");

		SoftAssert sa =new SoftAssert();
		SearchFinanceProgramPage searchPage=new SearchFinanceProgramPage(driver);
		searchPage.navigateToTab("Basic Definitions", "Search Finance Program", driver);
		searchPage.SearchFinanceProgram(FinanceProgramObj);
		EditFinanceProgramPage editPageObj=new EditFinanceProgramPage(driver);
		String actual=editPageObj.EditFinanceProgram(FinanceProgramObj);
		FinanceProgramVerifications financeProgramVerObj=new FinanceProgramVerifications();

		if(actual.equalsIgnoreCase(FinanceProgramObj.getExpectedMessage()))
		{
			if(actual.equalsIgnoreCase("Updated successfully"))
			{
				FinanceProgramPojo financeProgramInDB =	financeProgramVerObj.FinanceProgramVerifyForEdit(FinanceProgramObj);
				sa.assertTrue(FinanceProgramObj.getProgramType().equalsIgnoreCase(financeProgramInDB.getProgramType()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"Program Type not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getCode().equalsIgnoreCase(financeProgramInDB.getCode()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Code not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getName().equalsIgnoreCase(financeProgramInDB.getName()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"Name not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getDescription().equalsIgnoreCase(financeProgramInDB.getDescription()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Description not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getMaximumFacilityLimit().equalsIgnoreCase(financeProgramInDB.getMaximumFacilityLimit()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Maximum Facility Limit not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getValidateSetupTime().contains(financeProgramInDB.getValidateSetupTime()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Validate Setup Time not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getDisableAccountRouting().contains(financeProgramInDB.getDisableAccountRouting()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"Disable Account Routing not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getMainAccountReturn().contains(financeProgramInDB.getMainAccountReturn()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Main Account Return not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getEnableEStatment().contains(financeProgramInDB.getEnableEStatment()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Enable E-Statment not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getNumOfDays().equalsIgnoreCase(financeProgramInDB.getNumOfDays()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Number Of Days not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getDueDatePerVisits().contains(financeProgramInDB.getDueDatePerVisits()), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"Due Date Per Visits not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getBlockSetupInDebit().contains(financeProgramInDB.getBlockSetupInDebit()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Block Setup In Debit not matched in Both Excel and Database");
				sa.assertTrue(FinanceProgramObj.getReactivateSetupInCredit().contains(financeProgramInDB.getReactivateSetupInCredit()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Reactivate Setup In Credit not matched in Both Excel and Database");

			}
			if(actual.equalsIgnoreCase("Finance Program is deleted successfully"))
			{
				FinanceProgramPojo finanaceProgramInDB =	financeProgramVerObj.FinanceProgramVerifyForEdit(FinanceProgramObj);
				sa.assertTrue(finanaceProgramInDB.getStatus().equalsIgnoreCase("3"),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "deleted Button Functionality In Edit  Finance Program Page Failed ");
				financeProgramVerObj.update(FinanceProgramObj);
			}
			else
			{
				sa.assertTrue(actual.equalsIgnoreCase(FinanceProgramObj.getExpectedMessage()),"Test Case ID = "+ FinanceProgramObj.getTestCaseId()+ "Error Massage In Edit Finance Program Page Failed ");
			}
		}
		else if (actual.equalsIgnoreCase("back"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchFinanceProgram"), "Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"Back Button Functionality In Edit Finance Program Page Failed  ");
		}
		else if (actual.equalsIgnoreCase("fail"))
		{
			sa.fail("Test Case ID = "+ FinanceProgramObj.getTestCaseId()+"NO Action Done In Edit Finance Program Page Failed  ");
		}
		sa.assertAll();
	}

	@DataProvider(name = "EditFinanceProgramTestDataProvider")
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
		{FinanceProgramPojo FinanceProgramObj=new FinanceProgramPojo();
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
