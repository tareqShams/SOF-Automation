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
import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;
import fawry.sofAutomation.dbVerification.basicDefinitions.CspAccountTypesVerifications;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pages.basicDefinitions.EditCspAccountTypePage;
import fawry.sofAutomation.pages.basicDefinitions.SearchCSPAccountTypesPage;

public class EditCspAccountTypeTest extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate EditCspAccountType Functionality",priority=4, dataProvider="EditCspAccountTypetestDataProvider")
	public static void editCspAccountType(CSPAccountTypePojo CspAccountObject) throws InterruptedException, SQLException  
	{
		test = extent.createTest("Validate EditCspAccountType Functionality");

		SoftAssert sa=new SoftAssert();
		SearchCSPAccountTypesPage search=new SearchCSPAccountTypesPage(driver);
		search.navigateToTab("Basic Definitions", "Search CSP Account Types", driver);
		search.searchCspAccountType(CspAccountObject);
		EditCspAccountTypePage page=new EditCspAccountTypePage(driver);
		String actual;
		CSPAccountTypePojo  cspAccountTypeInDB = null;
		CspAccountTypesVerifications  cspAccountType =new CspAccountTypesVerifications();

		if(CspAccountObject.getFlag().equalsIgnoreCase("reset"))
		{
			 cspAccountTypeInDB =cspAccountType.CspAccount(CspAccountObject);
		}

		 actual=	page.editCspAccountType(CspAccountObject);
		if(actual.equals(CspAccountObject.getExpectedMessage()))
		{
			if(actual.equalsIgnoreCase("Updated successfully"))
			{
			 cspAccountTypeInDB =cspAccountType.CspAccount(CspAccountObject);

				sa.assertTrue(((CspAccountObject.getCsp().isEmpty()||CspAccountObject.getCsp()==null)?(true):(CspAccountObject.getCsp().contains(cspAccountTypeInDB.getCsp()))), "Customer Service Provide not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getAccountType().isEmpty()||CspAccountObject.getAccountType()==null)?(true):(CspAccountObject.getAccountType().contains(cspAccountTypeInDB.getAccountType()))), "Account Type not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getBalanceBTC().isEmpty()||CspAccountObject.getBalanceBTC()==null)?(true):(CspAccountObject.getBalanceBTC().contains(cspAccountTypeInDB.getBalanceBTC()))), "Balance BTC not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getPaymentBTC().isEmpty()||CspAccountObject.getPaymentBTC()==null)?(true):(CspAccountObject.getPaymentBTC().contains(cspAccountTypeInDB.getPaymentBTC()))), "Payment BTC not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getActivationMethodParameter().isEmpty()||CspAccountObject.getActivationMethodParameter()==null)?(true):(CspAccountObject.getActivationMethodParameter().contains(cspAccountTypeInDB.getActivationMethodParameter()))), "Activation Method Parameter not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getAccountCodePrefix().isEmpty()||CspAccountObject.getAccountCodePrefix()==null)?(true):(CspAccountObject.getAccountCodePrefix().contains(cspAccountTypeInDB.getAccountCodePrefix()))), "Account Code Prefix not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getCancelExtremeBalanceAmount().isEmpty()||CspAccountObject.getCancelExtremeBalanceAmount()==null)?(true):(CspAccountObject.getCancelExtremeBalanceAmount().contains(cspAccountTypeInDB.getCancelExtremeBalanceAmount()))), "Cancel Extreme Balance Amount not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getMaxNumberOfAccounts().isEmpty()||CspAccountObject.getMaxNumberOfAccounts()==null)?(true):(CspAccountObject.getMaxNumberOfAccounts().contains(cspAccountTypeInDB.getMaxNumberOfAccounts()))), "Max Number Of Accounts not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getExcludedBTCS().isEmpty()||CspAccountObject.getExcludedBTCS()==null)?(true):(CspAccountObject.getExcludedBTCS().contains(cspAccountTypeInDB.getExcludedBTCS()))), "Excluded BTCS not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getAddEnabled().isEmpty()||CspAccountObject.getAddEnabled()==null)?(true):(CspAccountObject.getAddEnabled().contains(cspAccountTypeInDB.getAddEnabled()))), "Add Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getDeleteEnabled().isEmpty()||CspAccountObject.getDeleteEnabled()==null)?(true):(CspAccountObject.getDeleteEnabled().contains(cspAccountTypeInDB.getDeleteEnabled()))), "Delete Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getFundLoadEnabled().isEmpty()||CspAccountObject.getFundLoadEnabled()==null)?(true):(CspAccountObject.getFundLoadEnabled().contains(cspAccountTypeInDB.getFundLoadEnabled()))), "Fund Load Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getBalanceINQEnabled().isEmpty()||CspAccountObject.getBalanceINQEnabled()==null)?(true):(CspAccountObject.getBalanceINQEnabled().contains(cspAccountTypeInDB.getBalanceINQEnabled()))), "Balance INQ Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getPinRequired().isEmpty()||CspAccountObject.getPinRequired()==null)?(true):(CspAccountObject.getPinRequired().contains(cspAccountTypeInDB.getPinRequired()))), "Pin Required not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getAllowAnonymousAccounts().isEmpty()||CspAccountObject.getAllowAnonymousAccounts()==null)?(true):(CspAccountObject.getAllowAnonymousAccounts().contains(cspAccountTypeInDB.getAllowAnonymousAccounts()))), "Allow Anonymous Accounts not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getThreeDSecuredEnabled().isEmpty()||CspAccountObject.getThreeDSecuredEnabled()==null)?(true):(CspAccountObject.getThreeDSecuredEnabled().contains(cspAccountTypeInDB.getThreeDSecuredEnabled()))), "ThreeD Secured Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getUpdateEnabled().isEmpty()||CspAccountObject.getUpdateEnabled()==null)?(true):(CspAccountObject.getUpdateEnabled().contains(cspAccountTypeInDB.getUpdateEnabled()))), "CashOut Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getCashOutEnabled().isEmpty()||CspAccountObject.getCashOutEnabled()==null)?(true):(CspAccountObject.getCashOutEnabled().contains(cspAccountTypeInDB.getCashOutEnabled()))), "Delete Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getCreateEnabled().isEmpty()||CspAccountObject.getCreateEnabled()==null)?(true):(CspAccountObject.getCreateEnabled().contains(cspAccountTypeInDB.getCreateEnabled()))), "Create Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getPmtEnabled().isEmpty()||CspAccountObject.getPmtEnabled()==null)?(true):(CspAccountObject.getPmtEnabled().contains(cspAccountTypeInDB.getPmtEnabled()))), "Pmt Enabled not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getKycRequired().isEmpty()||CspAccountObject.getKycRequired()==null)?(true):(CspAccountObject.getKycRequired().contains(cspAccountTypeInDB.getKycRequired()))), "Kyc Required not matched in Both Excel and Database");
				sa.assertTrue(((CspAccountObject.getDecryptAccountNumberParameter().isEmpty()||CspAccountObject.getDecryptAccountNumberParameter()==null)?(true):(CspAccountObject.getDecryptAccountNumberParameter().contains(cspAccountTypeInDB.getDecryptAccountNumberParameter()))), "Decrypt Account Number Parameter not matched in Both Excel and Database");


				if(CspAccountObject.getActivationMethodParameter().equalsIgnoreCase("new"))
				{
					sa.assertTrue(((CspAccountObject.getActivationMethod().isEmpty()||CspAccountObject.getActivationMethod()==null)?(true):(CspAccountObject.getActivationMethod().contains(cspAccountTypeInDB.getActivationMethod()))), "Activation Method not matched in Both Excel and Database");
					sa.assertTrue(((CspAccountObject.getBtc().isEmpty()||CspAccountObject.getBtc()==null)?(true):(CspAccountObject.getBtc().contains(cspAccountTypeInDB.getBtc()))), "Btc not matched in Both Excel and Database");
					sa.assertTrue(((CspAccountObject.getMerchant().isEmpty()||CspAccountObject.getMerchant()==null)?(true):(CspAccountObject.getMerchant().contains(cspAccountTypeInDB.getMerchant()))), "Merchant not matched in Both Excel and Database");
					sa.assertTrue(((CspAccountObject.getActivationAuthorizerSystemCode().isEmpty()||CspAccountObject.getActivationAuthorizerSystemCode()==null)?(true):(CspAccountObject.getActivationAuthorizerSystemCode().contains(cspAccountTypeInDB.getActivationAuthorizerSystemCode()))), "Activation Authorizer System Code not matched in Both Excel and Database");
				}
			}
			else if(actual.equalsIgnoreCase("CSP Account Type Conf. is deleted successfully"))
			{
				 cspAccountTypeInDB =cspAccountType.CspAccount(CspAccountObject);
				
				
				sa.assertTrue(cspAccountTypeInDB==null, "Delete Button Functionality in Update Csp Account Type Faild");

			}
			else if(actual.equalsIgnoreCase("reset"))
			{
			CSPAccountTypePojo	cspAccountTypeInDBAfterReset =cspAccountType.CspAccount(CspAccountObject);
				

				sa.assertTrue(((cspAccountTypeInDBAfterReset.getCsp().isEmpty()||cspAccountTypeInDBAfterReset.getCsp()==null)?(true):(cspAccountTypeInDBAfterReset.getCsp().contains(cspAccountTypeInDB.getCsp()))), "Customer Service Provide not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getAccountType().isEmpty()||cspAccountTypeInDBAfterReset.getAccountType()==null)?(true):(cspAccountTypeInDBAfterReset.getAccountType().contains(cspAccountTypeInDB.getAccountType()))), "Account Type not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getBalanceBTC().isEmpty()||cspAccountTypeInDBAfterReset.getBalanceBTC()==null)?(true):(cspAccountTypeInDBAfterReset.getBalanceBTC().contains(cspAccountTypeInDB.getBalanceBTC()))), "Balance BTC not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getPaymentBTC().isEmpty()||cspAccountTypeInDBAfterReset.getPaymentBTC()==null)?(true):(cspAccountTypeInDBAfterReset.getPaymentBTC().contains(cspAccountTypeInDB.getPaymentBTC()))), "Payment BTC not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getActivationMethodParameter().isEmpty()||cspAccountTypeInDBAfterReset.getActivationMethodParameter()==null)?(true):(cspAccountTypeInDBAfterReset.getActivationMethodParameter().contains(cspAccountTypeInDB.getActivationMethodParameter()))), "Activation Method Parameter not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getAccountCodePrefix().isEmpty()||cspAccountTypeInDBAfterReset.getAccountCodePrefix()==null)?(true):(cspAccountTypeInDBAfterReset.getAccountCodePrefix().contains(cspAccountTypeInDB.getAccountCodePrefix()))), "Account Code Prefix not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getCancelExtremeBalanceAmount().isEmpty()||cspAccountTypeInDBAfterReset.getCancelExtremeBalanceAmount()==null)?(true):(cspAccountTypeInDBAfterReset.getCancelExtremeBalanceAmount().contains(cspAccountTypeInDB.getCancelExtremeBalanceAmount()))), "Cancel Extreme Balance Amount not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getMaxNumberOfAccounts().isEmpty()||cspAccountTypeInDBAfterReset.getMaxNumberOfAccounts()==null)?(true):(cspAccountTypeInDBAfterReset.getMaxNumberOfAccounts().contains(cspAccountTypeInDB.getMaxNumberOfAccounts()))), "Max Number Of Accounts not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getExcludedBTCS().isEmpty()||cspAccountTypeInDBAfterReset.getExcludedBTCS()==null)?(true):(cspAccountTypeInDBAfterReset.getExcludedBTCS().contains(cspAccountTypeInDB.getExcludedBTCS()))), "Excluded BTCS not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getAddEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getAddEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getAddEnabled().contains(cspAccountTypeInDB.getAddEnabled()))), "Add Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getDeleteEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getDeleteEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getDeleteEnabled().contains(cspAccountTypeInDB.getDeleteEnabled()))), "Delete Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getFundLoadEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getFundLoadEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getFundLoadEnabled().contains(cspAccountTypeInDB.getFundLoadEnabled()))), "Fund Load Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getBalanceINQEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getBalanceINQEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getBalanceINQEnabled().contains(cspAccountTypeInDB.getBalanceINQEnabled()))), "Balance INQ Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getPinRequired().isEmpty()||cspAccountTypeInDBAfterReset.getPinRequired()==null)?(true):(cspAccountTypeInDBAfterReset.getPinRequired().contains(cspAccountTypeInDB.getPinRequired()))), "Pin Required not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getAllowAnonymousAccounts().isEmpty()||cspAccountTypeInDBAfterReset.getAllowAnonymousAccounts()==null)?(true):(cspAccountTypeInDBAfterReset.getAllowAnonymousAccounts().contains(cspAccountTypeInDB.getAllowAnonymousAccounts()))), "Allow Anonymous Accounts not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getThreeDSecuredEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getThreeDSecuredEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getThreeDSecuredEnabled().contains(cspAccountTypeInDB.getThreeDSecuredEnabled()))), "ThreeD Secured Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getUpdateEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getUpdateEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getUpdateEnabled().contains(cspAccountTypeInDB.getUpdateEnabled()))), "CashOut Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getCashOutEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getCashOutEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getCashOutEnabled().contains(cspAccountTypeInDB.getCashOutEnabled()))), "Delete Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getCreateEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getCreateEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getCreateEnabled().contains(cspAccountTypeInDB.getCreateEnabled()))), "Create Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getPmtEnabled().isEmpty()||cspAccountTypeInDBAfterReset.getPmtEnabled()==null)?(true):(cspAccountTypeInDBAfterReset.getPmtEnabled().contains(cspAccountTypeInDB.getPmtEnabled()))), "Pmt Enabled not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getKycRequired().isEmpty()||cspAccountTypeInDBAfterReset.getKycRequired()==null)?(true):(cspAccountTypeInDBAfterReset.getKycRequired().contains(cspAccountTypeInDB.getKycRequired()))), "Kyc Required not matched in Both Excel and Database");
				sa.assertTrue(((cspAccountTypeInDBAfterReset.getDecryptAccountNumberParameter().isEmpty()||cspAccountTypeInDBAfterReset.getDecryptAccountNumberParameter()==null)?(true):(cspAccountTypeInDBAfterReset.getDecryptAccountNumberParameter().contains(cspAccountTypeInDB.getDecryptAccountNumberParameter()))), "Decrypt Account Number Parameter not matched in Both Excel and Database");


				if(CspAccountObject.getActivationMethodParameter().equalsIgnoreCase("new"))
				{
					sa.assertTrue(((cspAccountTypeInDBAfterReset.getCode().isEmpty()||cspAccountTypeInDBAfterReset.getCode()==null)?(true):(cspAccountTypeInDBAfterReset.getCode().contains(cspAccountTypeInDB.getCode()))), "code not matched in Both Excel and Database");
					sa.assertTrue(((cspAccountTypeInDBAfterReset.getActivationMethod().isEmpty()||cspAccountTypeInDBAfterReset.getActivationMethod()==null)?(true):(cspAccountTypeInDBAfterReset.getActivationMethod().contains(cspAccountTypeInDB.getActivationMethod()))), "Activation Method not matched in Both Excel and Database");
					sa.assertTrue(((cspAccountTypeInDBAfterReset.getBtc().isEmpty()||cspAccountTypeInDBAfterReset.getBtc()==null)?(true):(cspAccountTypeInDBAfterReset.getBtc().contains(cspAccountTypeInDB.getBtc()))), "Btc not matched in Both Excel and Database");
					sa.assertTrue(((cspAccountTypeInDBAfterReset.getMerchant().isEmpty()||cspAccountTypeInDBAfterReset.getMerchant()==null)?(true):(cspAccountTypeInDBAfterReset.getMerchant().contains(cspAccountTypeInDB.getMerchant()))), "Merchant not matched in Both Excel and Database");
					sa.assertTrue(((cspAccountTypeInDBAfterReset.getActivationAuthorizerSystemCode().isEmpty()||cspAccountTypeInDBAfterReset.getActivationAuthorizerSystemCode()==null)?(true):(cspAccountTypeInDBAfterReset.getActivationAuthorizerSystemCode().contains(cspAccountTypeInDB.getActivationAuthorizerSystemCode()))), "Activation Authorizer System Code not matched in Both Excel and Database");
				}
			}
			else if(actual.equalsIgnoreCase("back"))
			{
				sa.assertTrue(driver.getCurrentUrl().contains("/SearchCSP_AccTypes"), "Back Button Functionlity In Update CSP Account Type Page");

			}
			
		}
sa.assertAll();
	}


	@DataProvider(name = "EditCspAccountTypetestDataProvider")
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
			CSPAccountTypePojo cspAccountType=new CSPAccountTypePojo();
			cspAccountType.setTestCaseId(resultArray.get(i).get(0).toString());
			cspAccountType.setCsp(resultArray.get(i).get(1).toString());
			cspAccountType.setAccountType(resultArray.get(i).get(2).toString());
			cspAccountType.setCustomerProfileType(resultArray.get(i).get(3).toString());
			cspAccountType.setBalanceBTC(resultArray.get(i).get(4).toString());
			cspAccountType.setPaymentBTC(resultArray.get(i).get(5).toString());
			cspAccountType.setActivationMethodParameter(resultArray.get(i).get(6).toString());
			cspAccountType.setAccountCodePrefix(resultArray.get(i).get(7).toString());
			cspAccountType.setCancelExtremeBalanceAmount(resultArray.get(i).get(8).toString());
			cspAccountType.setMaxNumberOfAccounts(resultArray.get(i).get(9).toString());
			cspAccountType.setExcludedBTCS(resultArray.get(i).get(10).toString());
			cspAccountType.setAddEnabled(resultArray.get(i).get(11).toString());
			cspAccountType.setDeleteEnabled(resultArray.get(i).get(12).toString());
			cspAccountType.setFundLoadEnabled(resultArray.get(i).get(13).toString());
			cspAccountType.setBalanceINQEnabled(resultArray.get(i).get(14).toString());
			cspAccountType.setPinRequired(resultArray.get(i).get(15).toString());
			cspAccountType.setAllowAnonymousAccounts(resultArray.get(i).get(16).toString());
			cspAccountType.setThreeDSecuredEnabled(resultArray.get(i).get(17).toString());
			cspAccountType.setUpdateEnabled(resultArray.get(i).get(18).toString());
			cspAccountType.setCashOutEnabled(resultArray.get(i).get(19).toString());
			cspAccountType.setCreateEnabled(resultArray.get(i).get(20).toString());
			cspAccountType.setPmtEnabled(resultArray.get(i).get(21).toString());
			cspAccountType.setKycRequired(resultArray.get(i).get(22).toString());
			cspAccountType.setDecryptAccountNumberParameter(resultArray.get(i).get(23).toString());
			cspAccountType.setPurchaseEnable(resultArray.get(i).get(24).toString());
			cspAccountType.setCode(resultArray.get(i).get(25).toString());
			cspAccountType.setActivationMethod(resultArray.get(i).get(26).toString());
			cspAccountType.setMerchant(resultArray.get(i).get(27).toString());
			cspAccountType.setBtc(resultArray.get(i).get(28).toString());
			cspAccountType.setActivationAuthorizerSystemCode(resultArray.get(i).get(29).toString());
			cspAccountType.setFlag(resultArray.get(i).get(30).toString());
			cspAccountType.setExpectedMessage(resultArray.get(i).get(31).toString());


			result[i][0] = cspAccountType;

		}
		return result;

	}
}
