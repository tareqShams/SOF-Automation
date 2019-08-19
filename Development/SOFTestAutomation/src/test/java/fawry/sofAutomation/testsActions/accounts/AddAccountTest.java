package fawry.sofAutomation.testsActions.accounts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import fawry.sofAutomation.constants.accounts.AssertionErrorMessages;
import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.dbVerification.accounts.AccountsVerification;
import fawry.sofAutomation.dbVerification.accounts.SearchVefication;
import fawry.sofAutomation.pages.accounts.AddAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.CustomerPojo;
import fawry.sofAutomation.pojos.accounts.RegionPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class AddAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		AddAccountPage addaccount = new AddAccountPage(driver);
		addaccount.movetoaddaccountpage();

	}

	/*
	 * saprefield0 is for region expected
	 * sparefield1 is for terminal expected
	 * sparefield2 is for customer expected
	  sparefield5 is for -- adding new region actual
	  sparefield6 is for -- adding new customer actual
	  sparefield7 is for -- adding new Terminal actual
	 */

	@Test(description="Validate Add Account Functionality",priority=1, dataProvider="AddAccountDataProvider")
	public static void AddAccount(AccountPojo addaccountobj)  
	{
		test = extent.createTest("Validate Add Account Functionality");
		
		SoftAssert sa = new SoftAssert();
		AddAccountPage addAccount = new AddAccountPage(driver);

		driver.navigate().to(Constants.ADD_ACCOUNT_URL);
		//Choosing to add account by location id with either an existing region or by adding new region
		if (addaccountobj.getAddAccountMethod().contains("ByLocationID"))
		{
			if (addaccountobj.getAddAccountMethod().contains("ExsistingLocation"))
			{
				addAccount.addAccountUsingLocationId(addaccountobj);
			}
			else if (addaccountobj.getAddAccountMethod().contains("NewRegion"))
			{
				addAccount.AddAccountAddNewRegion(addaccountobj);
			}
		}
		//Choosing to add account by Region details with either an existing region or by adding new region
		if (addaccountobj.getAddAccountMethod().contains("ByRegionDetails"))
		{
			if (addaccountobj.getAddAccountMethod().contains("ExistingRegion"))
			{
				addAccount.AddAccountUsingRegionDetails(addaccountobj);
			}
			else if (addaccountobj.getAddAccountMethod().contains("NewRegion"))
			{
				addAccount.AddAccountAddNewRegion(addaccountobj);
			}
		}
		//Checking to use a current merchant or add a new Merchant
		if (addaccountobj.getAddAccountMethod().contains("NewCustomer"))
		{
			addAccount.AddAccountAddNewCustomer(addaccountobj);
		}
		//Inserting Data in all common fields
		addAccount.AddAccountCommonFields(addaccountobj);

		//Check For Saving or resetting data

		addAccount.saveOrResetData(addaccountobj);

		// Collect Error or success Messages
		String actual = addAccount.addAccountErrorMessagesAndSuccessMessage(addaccountobj);
		System.out.println(actual);
		// assert on messages
		System.out.println(addaccountobj.getExpectedMessage());
		sa.assertTrue(actual.contains(addaccountobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + "In TestCase with id of "+addaccountobj.getTestCaseId());
		if (addaccountobj.getSparefield7() != null && addaccountobj.getSparefield7() != "")
		{
			System.out.println(addaccountobj.getSparefield7());
			System.out.println(addaccountobj.getSparefield1());

			sa.assertTrue(addaccountobj.getSparefield7().contains(addaccountobj.getSparefield1()),
					AssertionErrorMessages.EXPECTED_TERMINAL_ACTUAL_EXCEL_WEBAPP + "In TestCase with id of "+addaccountobj.getTestCaseId());
		}
		if (addaccountobj.getSparefield5() != null && addaccountobj.getSparefield5() != "")
		{
			System.out.println(addaccountobj.getSparefield5());
			System.out.println(addaccountobj.getSparefield0());

			sa.assertTrue(addaccountobj.getSparefield5().contains(addaccountobj.getSparefield0()),
					AssertionErrorMessages.EXPECTED_REGION_ACTUAL_EXCEL_WEBAPP + "In TestCase with id of "+addaccountobj.getTestCaseId());
		}
		if (addaccountobj.getSparefield6() != null && addaccountobj.getSparefield6() != "")
		{
			System.out.println(addaccountobj.getSparefield6());
			System.out.println(addaccountobj.getSparefield2());

			sa.assertTrue(addaccountobj.getSparefield6().contains(addaccountobj.getSparefield2()),
					AssertionErrorMessages.EXPECTED_REGION_ACTUAL_EXCEL_WEBAPP + "In TestCase with id of "+addaccountobj.getTestCaseId());
		}

		//assert on DB Only when an account is added Successfully
		System.out.println(addaccountobj.getAddAccountMethod());
		if (addaccountobj.getAddAccountMethod().contains("Success"))
		{
			//validate that account is added successfully in DB
			AccountsVerification searchAcc = new AccountsVerification();
			SearchPojo searchAccountCriteria = new SearchPojo();
			System.out.println(addAccount.newacccode);
			searchAccountCriteria.setAccountCode(addAccount.newacccode);
			AccountPojo accountInDb = searchAcc.addPrimaryAccount(searchAccountCriteria, "Account").get(0);

			sa.assertTrue(accountInDb.getAccountStatus().equalsIgnoreCase("Active"), AssertionErrorMessages.ACCOUNT_STATUS_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getUsage().contains(accountInDb.getUsage()), AssertionErrorMessages.USAGE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getCurrency().contains(accountInDb.getCurrency()), AssertionErrorMessages.CURRENCY_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getAccountClass().contains(accountInDb.getAccountClass()), AssertionErrorMessages.ACCOUNT_CLASS_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getAccountGroup().contains(accountInDb.getAccountGroup()), AssertionErrorMessages.ACCOUNT_GROUP_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getOfficialType().contains(accountInDb.getOfficialType()), AssertionErrorMessages.OFFICIAL_TYPE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getDescription().contains(accountInDb.getDescription()), AssertionErrorMessages.ACCOUNT_DESCRIPTION_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(accountInDb.getBalance().equals("0"), AssertionErrorMessages.BALANCE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getCreditLimit().contains(accountInDb.getCreditLimit()), AssertionErrorMessages.CREDIT_LIMIT_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getDailyLimit().contains(accountInDb.getDailyLimit()), AssertionErrorMessages.DAILY_LIMIT_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getOfficialnumber().contains(accountInDb.getOfficialnumber()), AssertionErrorMessages.OFFICIAL_NUMBER_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			sa.assertTrue(addaccountobj.getCsp().contains(accountInDb.getCsp()), AssertionErrorMessages.CSP_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());

			//Customer Verifications only when a new customer is added
			System.out.println(addaccountobj.getCustomer().get(0).getCustomerprofilecode());
			System.out.println(accountInDb.getCustomer().get(0).getCustomerprofilecode());

			sa.assertTrue(addaccountobj.getCustomer().get(0).getCustomerprofilecode().contains(accountInDb.getCustomer().get(0).getCustomerprofilecode()), AssertionErrorMessages.CUSTOMER_PROFILE_CODE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			if (addaccountobj.getAddAccountMethod().contains("NewCustomer"))
			{
				sa.assertTrue(addaccountobj.getCustomer().get(0).getName().contains(accountInDb.getCustomer().get(0).getName()), AssertionErrorMessages.CUSTOMER_NAME_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				sa.assertTrue(addaccountobj.getCustomer().get(0).getCustomerprofiletype().contains(accountInDb.getCustomer().get(0).getCustomerprofiletype()), AssertionErrorMessages.CUSTOMER_PROFILE_TYPE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				sa.assertTrue(addaccountobj.getCustomer().get(0).getCustomercatagory().contains(accountInDb.getCustomer().get(0).getCustomercatagory()), AssertionErrorMessages.CUSTOMER_CATEGORY_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());

			}
			//Regions all data Verifications only happen when a new Region is added but region code is done any way
			if (addaccountobj.getAddAccountMethod().contains("NewRegion"))
			{
				System.out.println(addaccountobj.getRegions().get(0).getDistrict());
				System.out.println(accountInDb.getRegions().get(0).getDistrict());

				sa.assertTrue(addaccountobj.getRegions().get(0).getLocationId().contains(accountInDb.getRegions().get(0).getCode()), AssertionErrorMessages.REGION_CODE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				sa.assertTrue(addaccountobj.getRegions().get(0).getName().contains(accountInDb.getRegions().get(0).getName()), AssertionErrorMessages.REGION_NAME_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				sa.assertTrue(addaccountobj.getRegions().get(0).getDistrict().contains(accountInDb.getRegions().get(0).getDistrict()), AssertionErrorMessages.REGION_DISTRICT_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			}
			else if (addaccountobj.getAddAccountMethod().contains("ExistingLocation"))
			{
				sa.assertTrue(addaccountobj.getLocationId().contains(accountInDb.getRegions().get(0).getCode()), AssertionErrorMessages.REGION_CODE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
			}
			for (int i = 0 ; i< accountInDb.getTerminals().size();i++)
			{
				System.out.println(addaccountobj.getTerminals().get(0).getSeriallNumber()+"/"+accountInDb.getTerminals().get(0).getSeriallNumber());
				sa.assertTrue(addaccountobj.getTerminals().get(0).getSeriallNumber().contains(accountInDb.getTerminals().get(0).getSeriallNumber()), AssertionErrorMessages.TERMINAL_SERIAL_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				System.out.println(addaccountobj.getTerminals().get(0).getDescription()+"/"+accountInDb.getTerminals().get(0).getDescription());
				sa.assertTrue(addaccountobj.getTerminals().get(0).getDescription().contains(accountInDb.getTerminals().get(0).getDescription()), AssertionErrorMessages.TERMINAL_DESCRIPTION_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				System.out.println(addaccountobj.getTerminals().get(0).getName()+"/"+accountInDb.getTerminals().get(0).getName());
				sa.assertTrue(addaccountobj.getTerminals().get(0).getName().contains(accountInDb.getTerminals().get(0).getName()), AssertionErrorMessages.TERMINAL_NAME_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				System.out.println(addaccountobj.getTerminals().get(0).getTerminalType()+"/"+accountInDb.getTerminals().get(0).getTerminalType());
				sa.assertTrue(addaccountobj.getTerminals().get(0).getTerminalType().contains(accountInDb.getTerminals().get(0).getTerminalType()), AssertionErrorMessages.TERMINAL_TYPE_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				System.out.println(addaccountobj.getTerminals().get(0).getTerminalstatus()+"/"+accountInDb.getTerminals().get(0).getTerminalstatus());
				sa.assertTrue(addaccountobj.getTerminals().get(0).getTerminalstatus().contains(accountInDb.getTerminals().get(0).getTerminalstatus()), AssertionErrorMessages.TERMINAL_STATUS_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());
				sa.assertTrue(accountInDb.getTerminals().get(0).getHashedPin().equals("gdyb21LQTcIANtvYMT7QVQ=="), AssertionErrorMessages.TERMINAL_PIN_EXCEL_DB + "In TestCase with id of "+addaccountobj.getTestCaseId());

			}

		}
		sa.assertAll();

	}







	@DataProvider(name = "AddAccountDataProvider")
	public Object[][] provideTestData(Method method)
	{

		String methodFullName = method.getName();

		PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
		Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);

		String connectionProperties = prop.getProperty(methodFullName);

		ArrayList<ArrayList<Object>> resultArray = provideTestData(connectionProperties);
		Object[][] result = new Object[resultArray.size()][1];


		for(int i=0; i<resultArray.size(); i++)
		{
			AccountPojo addAccountTestData = new AccountPojo();

			addAccountTestData.setLocationId(resultArray.get(i).get(0).toString());

			ArrayList<RegionPojo> regions = new ArrayList<RegionPojo>();
			RegionPojo reg = new RegionPojo();

			reg.setGovernorate(resultArray.get(i).get(1).toString());
			reg.setDistrict(resultArray.get(i).get(2).toString());
			reg.setName(resultArray.get(i).get(3).toString());
			reg.setCode(resultArray.get(i).get(4).toString());

			regions.add(reg);
			addAccountTestData.setRegions(regions);
			// using extra field 0 for expected when adding new region
			addAccountTestData.setSparefield0(resultArray.get(i).get(5).toString());

			addAccountTestData.setCsp(resultArray.get(i).get(6).toString());
			addAccountTestData.setCutomerProfile(resultArray.get(i).get(7).toString());
			addAccountTestData.setUsage(resultArray.get(i).get(8).toString());
			addAccountTestData.setDescription(resultArray.get(i).get(9).toString());

			short terminalsCount = Short.parseShort(resultArray.get(i).get(17).toString());
			ArrayList<TerminalPojo> accountTerminals =  new ArrayList<TerminalPojo>();

			for(int j=0; j< terminalsCount; j++)
			{
				TerminalPojo terminal =  new TerminalPojo();

				terminal.setTerminalstatus(resultArray.get(i).get(10).toString());
				terminal.setName(resultArray.get(i).get(11).toString() + j);
				terminal.setTerminalType(resultArray.get(i).get(12).toString());
				terminal.setProfilecode(resultArray.get(i).get(13).toString());
				terminal.setSerialNumber(resultArray.get(i).get(14).toString());
				terminal.setTerminalPin(resultArray.get(i).get(15).toString());
				terminal.setDescription(resultArray.get(i).get(16).toString());

				accountTerminals.add(terminal);

			}
			addAccountTestData.setTerminals(accountTerminals);
			// using extra field 1 for expected when adding new Terminal
			addAccountTestData.setSparefield1(resultArray.get(i).get(18).toString());

			addAccountTestData.setCurrency(resultArray.get(i).get(19).toString());
			addAccountTestData.setDailyLimit(resultArray.get(i).get(20).toString());
			addAccountTestData.setCreditLimit(resultArray.get(i).get(21).toString());


			ArrayList<CustomerPojo> customers = new ArrayList<CustomerPojo>();
			CustomerPojo cust = new CustomerPojo();

			cust.setName(resultArray.get(i).get(22).toString());
			cust.setCutomerdescription(resultArray.get(i).get(23).toString());
			cust.setCustomercatagory(resultArray.get(i).get(24).toString());
			cust.setCustomerprofiletype(resultArray.get(i).get(25).toString());
			cust.setCustomerprofilecode(resultArray.get(i).get(26).toString());
			cust.setCspname(resultArray.get(i).get(6).toString());
			cust.setMobilenumber(resultArray.get(i).get(28).toString());

			customers.add(cust);
			addAccountTestData.setCustomer(customers);

			// using extra field 2 for expected when adding new Customer
			addAccountTestData.setSparefield2(resultArray.get(i).get(27).toString());

			addAccountTestData.setMobileNumber(resultArray.get(i).get(28).toString());
			addAccountTestData.setAccountClass(resultArray.get(i).get(29).toString());
			addAccountTestData.setAccountGroup(resultArray.get(i).get(30).toString());
			addAccountTestData.setOfficialType(resultArray.get(i).get(31).toString());
			addAccountTestData.setOfficialnumber(resultArray.get(i).get(32).toString());
			addAccountTestData.setProfileid(resultArray.get(i).get(33).toString());
			addAccountTestData.setAddAccountMethod(resultArray.get(i).get(34).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(35).toString());
			addAccountTestData.setTestCaseId(resultArray.get(i).get(36).toString());






			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
