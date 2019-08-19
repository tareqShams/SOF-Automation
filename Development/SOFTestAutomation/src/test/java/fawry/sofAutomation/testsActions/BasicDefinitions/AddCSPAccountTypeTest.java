package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.util.ArrayList;
import java.util.Properties;
import java.lang.reflect.Method;
import java.sql.SQLException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


import fawry.sofAutomation.utils.PropertiesFilesHandler;
import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspAccountTypesVerifications;
import fawry.sofAutomation.pages.basicDefinitions.AddCSPAccountTypePage;
import fawry.sofAutomation.pages.login.LoginPage;



public class AddCSPAccountTypeTest extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate AddCSPAccountType Functionality",priority=3, dataProvider="AddCSPAccountTypetestDataProvider")
	public static void addCSPAccountType(CSPAccountTypePojo addCSPAccountTypeobj) throws SQLException  
	{
		test = extent.createTest("Validate AddCSPAccountType Functionality");

		SoftAssert sa=new SoftAssert();
		AddCSPAccountTypePage accountTypePageObject=new AddCSPAccountTypePage(driver);
		accountTypePageObject.navigateToTab("Basic Definitions", "Add CSP Account Type", driver);
		String actual=accountTypePageObject.addCspAccountType(addCSPAccountTypeobj);
		CspAccountTypesVerifications DBVerifications=new CspAccountTypesVerifications();
		CSPAccountTypePojo cspAccountTypeInDB;
		if(actual.equalsIgnoreCase(addCSPAccountTypeobj.getExpectedMessage()))
		{
			if(actual.equalsIgnoreCase("Added successfully"))
			{

				cspAccountTypeInDB=DBVerifications.CspAccount(addCSPAccountTypeobj);
				sa.assertTrue(addCSPAccountTypeobj.getCsp().equalsIgnoreCase(cspAccountTypeInDB.getCsp()), "Customer Service Provide not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getAccountType().equalsIgnoreCase(cspAccountTypeInDB.getAccountType()), "Account Type not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getBalanceBTC().equalsIgnoreCase(cspAccountTypeInDB.getBalanceBTC()), "Balance BTC not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getPaymentBTC().equalsIgnoreCase(cspAccountTypeInDB.getPaymentBTC()), "Payment BTC not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getActivationMethodParameter().equalsIgnoreCase(cspAccountTypeInDB.getActivationMethodParameter()), "Activation Method Parameter not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getAccountCodePrefix().equalsIgnoreCase(cspAccountTypeInDB.getAccountCodePrefix()), "Account Code Prefix not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getCancelExtremeBalanceAmount().equalsIgnoreCase(cspAccountTypeInDB.getCancelExtremeBalanceAmount()), "Cancel Extreme Balance Amount not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getMaxNumberOfAccounts().equalsIgnoreCase(cspAccountTypeInDB.getMaxNumberOfAccounts()), "Max Number Of Accounts not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getExcludedBTCS().equalsIgnoreCase(cspAccountTypeInDB.getExcludedBTCS()), "Excluded BTCS not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getAddEnabled().equalsIgnoreCase(cspAccountTypeInDB.getAddEnabled()), "Add Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getDeleteEnabled().equalsIgnoreCase(cspAccountTypeInDB.getDeleteEnabled()), "Delete Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getFundLoadEnabled().equalsIgnoreCase(cspAccountTypeInDB.getFundLoadEnabled()), "Fund Load Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getBalanceINQEnabled().equalsIgnoreCase(cspAccountTypeInDB.getBalanceINQEnabled()), "Balance INQ Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getPinRequired().equalsIgnoreCase(cspAccountTypeInDB.getPinRequired()), "Pin Required not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getAllowAnonymousAccounts().equalsIgnoreCase(cspAccountTypeInDB.getAllowAnonymousAccounts()), "Allow Anonymous Accounts not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getThreeDSecuredEnabled().equalsIgnoreCase(cspAccountTypeInDB.getThreeDSecuredEnabled()), "ThreeD Secured Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getUpdateEnabled().equalsIgnoreCase(cspAccountTypeInDB.getUpdateEnabled()), "CashOut Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getCashOutEnabled().equalsIgnoreCase(cspAccountTypeInDB.getCashOutEnabled()), "Delete Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getCreateEnabled().equalsIgnoreCase(cspAccountTypeInDB.getCreateEnabled()), "Create Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getPmtEnabled().equalsIgnoreCase(cspAccountTypeInDB.getPmtEnabled()), "Pmt Enabled not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getKycRequired().equalsIgnoreCase(cspAccountTypeInDB.getKycRequired()), "Kyc Required not matched in Both Excel and Database");
				sa.assertTrue(addCSPAccountTypeobj.getDecryptAccountNumberParameter().equalsIgnoreCase(cspAccountTypeInDB.getDecryptAccountNumberParameter()), "Decrypt Account Number Parameter not matched in Both Excel and Database");
				if(addCSPAccountTypeobj.getActivationMethodParameter().equalsIgnoreCase("new"))
				{
					sa.assertTrue(addCSPAccountTypeobj.getActivationMethod().equalsIgnoreCase(cspAccountTypeInDB.getActivationMethod()), "Activation Method not matched in Both Excel and Database");
					sa.assertTrue(addCSPAccountTypeobj.getBtc().equalsIgnoreCase(cspAccountTypeInDB.getBtc()), "Btc not matched in Both Excel and Database");
					sa.assertTrue(addCSPAccountTypeobj.getMerchant().equalsIgnoreCase(cspAccountTypeInDB.getMerchant()), "Merchant not matched in Both Excel and Database");
					sa.assertTrue(addCSPAccountTypeobj.getActivationAuthorizerSystemCode().equalsIgnoreCase(cspAccountTypeInDB.getActivationAuthorizerSystemCode()), "Activation Authorizer System Code not matched in Both Excel and Database");
				}
			}	

			else 
			{
				sa.assertTrue(actual.equalsIgnoreCase(addCSPAccountTypeobj.getExpectedMessage()), "Error alert not matched in Both Excel and Database");
			}		
		}
		else if (actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(accountTypePageObject.reset(), "Reset Button Functionality in Update Csp Account Type Faild");
		}
		else
		{
			sa.fail("Add CSP Account Type Functionality Failed");

		}
		sa.assertAll();

	}


	@DataProvider(name = "AddCSPAccountTypetestDataProvider")
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
			CSPAccountTypePojo accountType=new CSPAccountTypePojo();
			accountType.setTestCaseId(resultArray.get(i).get(0).toString());
			accountType.setCsp(resultArray.get(i).get(1).toString());
			accountType.setAccountType(resultArray.get(i).get(2).toString());
			accountType.setCustomerProfileType(resultArray.get(i).get(3).toString());
			accountType.setBalanceBTC(resultArray.get(i).get(4).toString());
			accountType.setPaymentBTC(resultArray.get(i).get(5).toString());
			accountType.setActivationMethodParameter(resultArray.get(i).get(6).toString());
			accountType.setAccountCodePrefix(resultArray.get(i).get(7).toString());
			accountType.setCancelExtremeBalanceAmount(resultArray.get(i).get(8).toString());
			accountType.setMaxNumberOfAccounts(resultArray.get(i).get(9).toString());
			accountType.setExcludedBTCS(resultArray.get(i).get(10).toString());
			accountType.setAddEnabled(resultArray.get(i).get(11).toString());
			accountType.setDeleteEnabled(resultArray.get(i).get(12).toString());
			accountType.setFundLoadEnabled(resultArray.get(i).get(13).toString());
			accountType.setBalanceINQEnabled(resultArray.get(i).get(14).toString());
			accountType.setPinRequired(resultArray.get(i).get(15).toString());
			accountType.setAllowAnonymousAccounts(resultArray.get(i).get(16).toString());
			accountType.setThreeDSecuredEnabled(resultArray.get(i).get(17).toString());
			accountType.setUpdateEnabled(resultArray.get(i).get(18).toString());
			accountType.setCashOutEnabled(resultArray.get(i).get(19).toString());
			accountType.setCreateEnabled(resultArray.get(i).get(20).toString());
			accountType.setPmtEnabled(resultArray.get(i).get(21).toString());
			accountType.setKycRequired(resultArray.get(i).get(22).toString());
			accountType.setDecryptAccountNumberParameter(resultArray.get(i).get(23).toString());
			accountType.setPurchaseEnable(resultArray.get(i).get(24).toString());
			accountType.setCode(resultArray.get(i).get(25).toString());
			accountType.setActivationMethod(resultArray.get(i).get(26).toString());
			accountType.setMerchant(resultArray.get(i).get(27).toString());
			accountType.setBtc(resultArray.get(i).get(28).toString());
			accountType.setActivationAuthorizerSystemCode(resultArray.get(i).get(29).toString());
			accountType.setFlag(resultArray.get(i).get(30).toString());
			accountType.setExpectedMessage(resultArray.get(i).get(31).toString());


			result[i][0] = accountType;

		}
		return result;

	}

}
