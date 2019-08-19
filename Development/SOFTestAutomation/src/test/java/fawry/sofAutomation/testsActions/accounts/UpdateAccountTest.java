package fawry.sofAutomation.testsActions.accounts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.accounts.AssertionErrorMessages;
import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.dbVerification.accounts.AccountsVerification;
import fawry.sofAutomation.dbVerification.accounts.SearchVefication;
import fawry.sofAutomation.pages.accounts.UpdateAccountPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.RegionPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class UpdateAccountTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();

	}

	/*
	 * sparefield0 is for new region expected
	 * sparefield1 is for new region Actual
	 * using sparefield5 for data before resetting
	 * using sparefield6 for data after resetting
	 */

	@Test(description="Validate Update Account Functionality",priority=1, dataProvider="UpdateAccountDataProvider")
	public static void UpdateAccount(AccountPojo updateaccountobj)  
	{
		SoftAssert sa = new SoftAssert();

		UpdateAccountPage updateacc = new UpdateAccountPage(driver);
		//Calling the main method which navigates to the specified account and collects the data before updating
		updateacc.updateAccountMain(updateaccountobj);
		//Select either using a location id or select by region details or add a new Region
		if (updateaccountobj.getAction().contains("ExistingLocation"))
		{
			updateacc.updateAccountUsingLocationId(updateaccountobj.getRegions().get(0));
		}
		else if (updateaccountobj.getAction().contains("ExistingRegion"))
		{
			updateacc.updateAccountUsingRegionDetails(updateaccountobj.getRegions().get(0));
		}
		else if (updateaccountobj.getAction().contains("NewgRegion"))
		{
			updateacc.updateAccountWthAddingNewRegion(updateaccountobj);
			//asserting on Expected and actual from adding a new Region
			sa.assertTrue(updateaccountobj.getSparefield1().contains(updateaccountobj.getSparefield0()), "Expected and actual from adding a new region dosn't match");
		}
		//Insert all other Data that will be updated
		updateacc.updateAccountCommonFields(updateaccountobj);
		//Save or Reset Data
		updateacc.saveOrResetInsertedData(updateaccountobj);
		//When Resetting data assertion is done in five steps: collect data before updating, update, reset, collect data after update then assert on data before and after update
		if (updateaccountobj.getAction().contains("Reset"))
		{
			sa.assertTrue(updateaccountobj.getSparefield5().equalsIgnoreCase(updateaccountobj.getSparefield6()), 
					AssertionErrorMessages.DATA_BEFORE_AFTER_RESET+ "In test Case With ID of" + updateaccountobj.getTestCaseId());
		}

		String actual = updateacc.collectingActualMessages(updateaccountobj);
		System.out.println(actual);
		// Asserting on expected in excel and Actual
		sa.assertTrue(actual.contains(updateaccountobj.getExpectedMessage()), "Error; Value of Expected Message/URL is Wrong");

		// Assert on Updated data in DB only When Updating
		if (updateaccountobj.getAction().contains("Success")) 
		{
			//validate that account is added successfully in DB
			AccountsVerification searchAcc = new AccountsVerification();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountCode(updateaccountobj.getAccountCode());

			AccountPojo accountInDb = searchAcc.updateAccount(searchAccountCriteria, "Account").get(0);
			//Check On Added or existing Data
			if(updateaccountobj.getAction().contains("Region"))
			{
				sa.assertTrue(updateaccountobj.getRegions().get(0).getName().contains(accountInDb.getRegions().get(0).getName()), 
						AssertionErrorMessages.REGION_NAME_EXCEL_DB+ "In test Case With ID of" + updateaccountobj.getTestCaseId());
				sa.assertTrue(updateaccountobj.getRegions().get(0).getDistrict().contains(accountInDb.getRegions().get(0).getDistrict()), 
						AssertionErrorMessages.REGION_DISTRICT_EXCEL_DB+ "In test Case With ID of" + updateaccountobj.getTestCaseId());
				if(updateaccountobj.getAction().contains("NewRegion"))
				{
					sa.assertTrue(updateaccountobj.getRegions().get(0).getCode().contains(accountInDb.getRegions().get(0).getCode()), "code");
				}
			}
			else if (updateaccountobj.getAction().contains("Location"))
			{
				sa.assertTrue(updateaccountobj.getRegions().get(0).getCode().contains(accountInDb.getRegions().get(0).getCode()), "loca");
			}
			if (!updateaccountobj.getAccountStatus().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getAccountStatus().contains(accountInDb.getAccountStatus()), "stat");
			}
			if (!updateaccountobj.getUsage().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getUsage().contains(accountInDb.getUsage()), "usage");
			}
			if (!updateaccountobj.getDescription().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getDescription().contains(accountInDb.getDescription()), "desc");
			}
			if (!updateaccountobj.getAccountClass().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getAccountClass().contains(accountInDb.getAccountClass()), 
						AssertionErrorMessages.ACCOUNT_CLASS_EXCEL_DB+ "In test Case With ID of" + updateaccountobj.getTestCaseId());
			}
			if (!updateaccountobj.getAccountGroup().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getAccountGroup().contains(accountInDb.getAccountGroup()), "grou");
			}
			if (!updateaccountobj.getCurrency().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getCurrency().contains(accountInDb.getCurrency()), "cur");
			}
			if (!updateaccountobj.getDailyLimit().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getDailyLimit().contains(accountInDb.getDailyLimit()), "dai");
			}
			if (!updateaccountobj.getCreditLimit().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getCreditLimit().contains(accountInDb.getCreditLimit()), "cre");
			}
			if (!updateaccountobj.getMobileNumber().isEmpty())
			{
				sa.assertTrue(updateaccountobj.getMobileNumber().contains(accountInDb.getMobileNumber()), "mob");
			}
		}
		sa.assertAll();
	}




	@DataProvider(name = "UpdateAccountDataProvider")
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
			AccountPojo UpdateAccountTestData = new AccountPojo();

			UpdateAccountTestData.setAccountCode(resultArray.get(i).get(0).toString());

			ArrayList<RegionPojo> regions = new ArrayList<RegionPojo>();
			RegionPojo regobj = new RegionPojo();

			regobj.setLocationId(resultArray.get(i).get(1).toString());
			regobj.setGovernorate(resultArray.get(i).get(2).toString());
			regobj.setDistrict(resultArray.get(i).get(3).toString());
			regobj.setCode(resultArray.get(i).get(4).toString());
			regobj.setName(resultArray.get(i).get(5).toString());

			regions.add(regobj);

			UpdateAccountTestData.setRegions(regions);

			//Using sparefield0 as expected for region adding 
			UpdateAccountTestData.setSparefield0(resultArray.get(i).get(6).toString());

			UpdateAccountTestData.setAccountStatus(resultArray.get(i).get(7).toString());
			UpdateAccountTestData.setUsage(resultArray.get(i).get(8).toString());
			UpdateAccountTestData.setDescription(resultArray.get(i).get(9).toString());
			UpdateAccountTestData.setAccountClass(resultArray.get(i).get(10).toString());
			UpdateAccountTestData.setAccountGroup(resultArray.get(i).get(11).toString());
			//UpdateAccountTestData.setSalesRep(resultArray.get(i).get(12).toString());
			UpdateAccountTestData.setCurrency(resultArray.get(i).get(13).toString());
			UpdateAccountTestData.setDailyLimit(resultArray.get(i).get(14).toString());
			UpdateAccountTestData.setCreditLimit(resultArray.get(i).get(15).toString());
			UpdateAccountTestData.setMobileNumber(resultArray.get(i).get(16).toString());
			UpdateAccountTestData.setOfficialType(resultArray.get(i).get(17).toString());
			UpdateAccountTestData.setOfficialnumber(resultArray.get(i).get(18).toString());
			UpdateAccountTestData.setSuspentionReason(resultArray.get(i).get(19).toString());

			UpdateAccountTestData.setTestCaseId(resultArray.get(i).get(20).toString());
			UpdateAccountTestData.setAction(resultArray.get(i).get(21).toString());
			UpdateAccountTestData.setExpectedMessage(resultArray.get(i).get(22).toString());


			result[i][0] = UpdateAccountTestData;

		}


		return result;
	}

}
