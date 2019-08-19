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
import fawry.sofAutomation.dbVerification.basicDefinitions.CspTerminalTypesConfVerification;
import fawry.sofAutomation.pages.basicDefinitions.EditCSPAccountTerminalConfPage;
import fawry.sofAutomation.pages.basicDefinitions.SearchCspTerminalTypesConfPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspAccountTerminalConfPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class EditCSPAccountTerminalConfTest extends BasicTest {
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate EditCSPAccountTerminalConf Functionality",priority=9, dataProvider="EditCSPAccountTerminalConfTestDataProvider")
	public static void editCSPAccountTerminalConf(CspAccountTerminalConfPojo CspAccountTerminalConfObj) throws InterruptedException, SQLException  
	{
		test = extent.createTest("Validate EditCSPAccountTerminalConf Functionality");

		SoftAssert sa=new SoftAssert();
		CspTerminalTypesConfVerification cspTerminalVerificationObj=new CspTerminalTypesConfVerification();
		EditCSPAccountTerminalConfPage page=new EditCSPAccountTerminalConfPage(driver);
		page.navigateToTab("Basic Definitions", "Search CSP Terminal Types", driver);
		SearchCspTerminalTypesConfPage searchPage=new SearchCspTerminalTypesConfPage(driver);
		searchPage.SearchCspTerminalTypesConfForEdit(CspAccountTerminalConfObj);
		EditCSPAccountTerminalConfPage editPageObj=new EditCSPAccountTerminalConfPage(driver);
		CspAccountTerminalConfPojo CspAccountTerminalObjAfterRest = null;
		if(CspAccountTerminalConfObj.getFlag().equalsIgnoreCase("reset"))
		{
			 CspAccountTerminalObjAfterRest=cspTerminalVerificationObj.CspAccountTerminalConfForEdit(CspAccountTerminalConfObj);
		}
		String actual=editPageObj.EditCSPAccountTerminalConf(CspAccountTerminalConfObj);
		System.out.println(actual);

		if(actual.equalsIgnoreCase(CspAccountTerminalConfObj.getExpectedMessage()))
		{
			if(actual.equalsIgnoreCase("Updated successfully"))
			{
				CspAccountTerminalConfPojo cspTerminalInDB=cspTerminalVerificationObj.CspAccountTerminalConfForEdit(CspAccountTerminalConfObj);
				sa.assertTrue(((CspAccountTerminalConfObj.getCspCode().isEmpty()||CspAccountTerminalConfObj.getCspCode()==null)?(true):(CspAccountTerminalConfObj.getCspCode().contains(cspTerminalInDB.getCspCode()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Csp Code  not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getTerminalType().isEmpty()||CspAccountTerminalConfObj.getTerminalType()==null)?(true):(CspAccountTerminalConfObj.getTerminalType().contains(cspTerminalInDB.getTerminalType()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Terminal Type not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getMaxSequentialNumber().isEmpty()||CspAccountTerminalConfObj.getTerminalType()==null)?(true):(CspAccountTerminalConfObj.getTerminalType().contains(cspTerminalInDB.getTerminalType()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Excluded BTCS not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getPinRegularExperssion().isEmpty()||CspAccountTerminalConfObj.getPinRegularExperssion()==null)?(true):(CspAccountTerminalConfObj.getPinRegularExperssion().contains(cspTerminalInDB.getPinRegularExperssion()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Pin Regular Experssion not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getForcePinChange().isEmpty()||CspAccountTerminalConfObj.getForcePinChange()==null)?(true):(CspAccountTerminalConfObj.getForcePinChange().contains(cspTerminalInDB.getForcePinChange()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Force Pin Change not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getAutoGenPin().isEmpty()||CspAccountTerminalConfObj.getForcePinChange()==null)?(true):(CspAccountTerminalConfObj.getForcePinChange().contains(cspTerminalInDB.getForcePinChange()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Force Pin Change not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getValidatePIN().isEmpty()||CspAccountTerminalConfObj.getValidatePIN()==null)?(true):(CspAccountTerminalConfObj.getValidatePIN().contains(cspTerminalInDB.getValidatePIN()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Validate PIN not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getPinEncryptionEnabled().isEmpty()||CspAccountTerminalConfObj.getPinEncryptionEnabled()==null)?(true):(CspAccountTerminalConfObj.getPinEncryptionEnabled().contains(cspTerminalInDB.getPinEncryptionEnabled()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Pin Encryption Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getAllowAnonymousAccounts().isEmpty()||CspAccountTerminalConfObj.getAllowAnonymousAccounts()==null)?(true):(CspAccountTerminalConfObj.getAllowAnonymousAccounts().contains(cspTerminalInDB.getAllowAnonymousAccounts()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Allow Anonymous Accounts  not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getRegTerminalsInCustReg().isEmpty()||CspAccountTerminalConfObj.getRegTerminalsInCustReg()==null)?(true):(CspAccountTerminalConfObj.getRegTerminalsInCustReg().contains(cspTerminalInDB.getRegTerminalsInCustReg()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Reg Terminals In Cust Reg not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getValidateDeviceId().isEmpty()||CspAccountTerminalConfObj.getValidateDeviceId()==null)?(true):(CspAccountTerminalConfObj.getValidateDeviceId().contains(cspTerminalInDB.getValidateDeviceId()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Validate Device Id not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountTerminalConfObj.getCheckPinHistory().isEmpty()||CspAccountTerminalConfObj.getCheckPinHistory()==null)?(true):(CspAccountTerminalConfObj.getCheckPinHistory().contains(cspTerminalInDB.getCheckPinHistory()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Check Pin History not matched in Both Excel and Database");

			}
		
			else if(actual.equalsIgnoreCase("CSP Terimnal Type is deleted successfully"))
			{
				//*****************************************************************************************************************************\\
				CspAccountTerminalConfPojo cspTerminalInDB=cspTerminalVerificationObj.CspAccountTerminalConfForEdit(CspAccountTerminalConfObj);
				sa.assertTrue(cspTerminalInDB==null,"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Delete Functionality In Edit CSP Account Terminal Conf Page Failed");
			
			}
			else
			{
				sa.assertTrue(actual.equalsIgnoreCase(CspAccountTerminalConfObj.getExpectedMessage()), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+" Expectted Error Massage"+CspAccountTerminalConfObj.getExpectedMessage()+" but Find Error Massage "+actual);
			
			}
		}
		else if (actual.equalsIgnoreCase("reset"))
		{

			CspAccountTerminalConfPojo cspTerminalInDB=cspTerminalVerificationObj.CspAccountTerminalConfForEdit(CspAccountTerminalConfObj);
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getCspCode().isEmpty()||CspAccountTerminalObjAfterRest.getCspCode()==null)?(true):(CspAccountTerminalObjAfterRest.getCspCode().contains(cspTerminalInDB.getCspCode()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Csp Code  not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getTerminalType().isEmpty()||CspAccountTerminalObjAfterRest.getTerminalType()==null)?(true):(CspAccountTerminalObjAfterRest.getTerminalType().contains(cspTerminalInDB.getTerminalType()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Terminal Type not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getMaxSequentialNumber().isEmpty()||CspAccountTerminalObjAfterRest.getTerminalType()==null)?(true):(CspAccountTerminalObjAfterRest.getTerminalType().contains(cspTerminalInDB.getTerminalType()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Excluded BTCS not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getPinRegularExperssion().isEmpty()||CspAccountTerminalObjAfterRest.getPinRegularExperssion()==null)?(true):(CspAccountTerminalObjAfterRest.getPinRegularExperssion().contains(cspTerminalInDB.getPinRegularExperssion()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Pin Regular Experssion not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getForcePinChange().isEmpty()||CspAccountTerminalObjAfterRest.getForcePinChange()==null)?(true):(CspAccountTerminalObjAfterRest.getForcePinChange().contains(cspTerminalInDB.getForcePinChange()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Force Pin Change not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getAutoGenPin().isEmpty()||CspAccountTerminalObjAfterRest.getForcePinChange()==null)?(true):(CspAccountTerminalObjAfterRest.getForcePinChange().contains(cspTerminalInDB.getForcePinChange()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Force Pin Change not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getValidatePIN().isEmpty()||CspAccountTerminalObjAfterRest.getValidatePIN()==null)?(true):(CspAccountTerminalObjAfterRest.getValidatePIN().contains(cspTerminalInDB.getValidatePIN()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Validate PIN not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getPinEncryptionEnabled().isEmpty()||CspAccountTerminalObjAfterRest.getPinEncryptionEnabled()==null)?(true):(CspAccountTerminalObjAfterRest.getPinEncryptionEnabled().contains(cspTerminalInDB.getPinEncryptionEnabled()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Pin Encryption Enabled not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getAllowAnonymousAccounts().isEmpty()||CspAccountTerminalObjAfterRest.getAllowAnonymousAccounts()==null)?(true):(CspAccountTerminalObjAfterRest.getAllowAnonymousAccounts().contains(cspTerminalInDB.getAllowAnonymousAccounts()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Allow Anonymous Accounts  not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getRegTerminalsInCustReg().isEmpty()||CspAccountTerminalObjAfterRest.getRegTerminalsInCustReg()==null)?(true):(CspAccountTerminalObjAfterRest.getRegTerminalsInCustReg().contains(cspTerminalInDB.getRegTerminalsInCustReg()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Reg Terminals In Cust Reg not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getValidateDeviceId().isEmpty()||CspAccountTerminalObjAfterRest.getValidateDeviceId()==null)?(true):(CspAccountTerminalObjAfterRest.getValidateDeviceId().contains(cspTerminalInDB.getValidateDeviceId()))),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Validate Device Id not matched in Both Excel and Database");
			sa.assertTrue(((CspAccountTerminalObjAfterRest.getCheckPinHistory().isEmpty()||CspAccountTerminalObjAfterRest.getCheckPinHistory()==null)?(true):(CspAccountTerminalObjAfterRest.getCheckPinHistory().contains(cspTerminalInDB.getCheckPinHistory()))), "Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+"Check Pin History not matched in Both Excel and Database");

		}
		else if (actual.equalsIgnoreCase("back"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchCspTerminalTypes"),"Test Case ID = "+ CspAccountTerminalConfObj.getTestCaseId()+ "Back Functionality In Edit CSP Account Terminal Conf Page Failed");
		}
		sa.assertAll();
	}


	@DataProvider(name = "EditCSPAccountTerminalConfTestDataProvider")
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
			CspAccountTerminalConfPojo CspAccountTerminalConfObj=new CspAccountTerminalConfPojo();
CspAccountTerminalConfObj.setTestCaseId(resultArray.get(i).get(0).toString());
			CspAccountTerminalConfObj.setCspCode(resultArray.get(i).get(1).toString());
			CspAccountTerminalConfObj.setTerminalType(resultArray.get(i).get(2).toString());
			CspAccountTerminalConfObj.setMaxSequentialNumber(resultArray.get(i).get(3).toString());
			CspAccountTerminalConfObj.setPinRegularExperssion(resultArray.get(i).get(4).toString());
			CspAccountTerminalConfObj.setForcePinChange(resultArray.get(i).get(5).toString());
			CspAccountTerminalConfObj.setAutoGenPin(resultArray.get(i).get(6).toString());
			CspAccountTerminalConfObj.setValidatePIN(resultArray.get(i).get(7).toString());
			CspAccountTerminalConfObj.setPinEncryptionEnabled(resultArray.get(i).get(8).toString());
			CspAccountTerminalConfObj.setAllowAnonymousAccounts(resultArray.get(i).get(9).toString());
			CspAccountTerminalConfObj.setRegTerminalsInCustReg(resultArray.get(i).get(10).toString());
			CspAccountTerminalConfObj.setValidateDeviceId(resultArray.get(i).get(11).toString());
			CspAccountTerminalConfObj.setCheckPinHistory(resultArray.get(i).get(12).toString());
			CspAccountTerminalConfObj.setFlag(resultArray.get(i).get(13).toString());
			CspAccountTerminalConfObj.setExpectedMessage(resultArray.get(i).get(14).toString());

			result[i][0] = CspAccountTerminalConfObj;

		}
		return result;

	}
}
