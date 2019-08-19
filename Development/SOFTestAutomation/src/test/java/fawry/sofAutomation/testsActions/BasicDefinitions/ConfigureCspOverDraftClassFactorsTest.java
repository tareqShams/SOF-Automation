package fawry.sofAutomation.testsActions.BasicDefinitions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.basicDefinitions.AssertionErrorMessages;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.dbVerification.basicDefinitions.SearchVefication;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftClassFactorsPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class ConfigureCspOverDraftClassFactorsTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		ConfigreCspOverDraftClassFactorsPage fees = new ConfigreCspOverDraftClassFactorsPage(driver);
		fees.hovertopage();

	}


	@Test(description="Validate Cofiguring CSP OverDraft Class Factors Functionality",priority=1, dataProvider="DataProvider")
	public static void configureOverDraftClassFactors(CSPFeesPojo feesobj)  
	{
		test = extent.createTest("Validate Cofiguring CSP OverDraft Class Factors Functionality");

		SoftAssert sa = new SoftAssert();
		ConfigreCspOverDraftClassFactorsPage fees = new ConfigreCspOverDraftClassFactorsPage(driver);

		driver.navigate().to(Constants.CSP_OVERDRAFT_CLASS_FACTORS_URL);

		//Inserting Data into common Fields
		fees.CommonFields(feesobj);
		// TAking Action of adding, resetting, confirming or canceling.
		fees.saveOrResetData(feesobj);
		//Collecting Success or fail messages
		String actual = fees.ErrorMessagesAndSuccessMessage(feesobj);
		sa.assertTrue(actual.contains(feesobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + feesobj.getTestCaseId());
		//assert on DB Only when an account is added Successfully
		if (feesobj.getAction().contains("Success"))
		{

			//Get 
			SearchVefication search = new SearchVefication();
			
			SearchPojo searchobj = new SearchPojo();
			searchobj.setCsp(feesobj.getCsp());
			
			// Assert on fees values OF B+ class
			searchobj.setAccountClass(feesobj.getBPlusaccclass());
			CSPFeesPojo bPlusInDb = search.searchFees(searchobj, "").get(0);

			System.out.println(bPlusInDb.getFactorValue() + " / " + feesobj.getBPlusFactor());
			sa.assertTrue(feesobj.getBPlusFactor().contains(bPlusInDb.getFactorValue()),
					AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getSaturdayValue().contains(feesobj.getBPlusSat()),
					AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getSundayValue().contains(feesobj.getBPlusSun()),
					AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getMondayValue().contains(feesobj.getBPlusMon()),
					AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getTuesdayValue().contains(feesobj.getBPlusTue()),
					AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getWedensdayValue().contains(feesobj.getBPlusWed()),
					AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getThursdayValue().contains(feesobj.getBPlusThu()),
					AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bPlusInDb.getFridayValue().contains(feesobj.getBPlusFri()),
					AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			
			// Assert on fees values OF C class
			searchobj.setAccountClass(feesobj.getCaccclass());
			CSPFeesPojo cInDb = search.searchFees(searchobj, "").get(0);

			System.out.println(cInDb.getFactorValue() + " / " + feesobj.getCFactor());
			sa.assertTrue(feesobj.getCFactor().contains(cInDb.getFactorValue()),
					AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getSaturdayValue().contains(feesobj.getCSat()),
					AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getSundayValue().contains(feesobj.getCSun()),
					AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getMondayValue().contains(feesobj.getCMon()),
					AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getTuesdayValue().contains(feesobj.getCTue()),
					AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getWedensdayValue().contains(feesobj.getCWed()),
					AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getThursdayValue().contains(feesobj.getCThu()),
					AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cInDb.getFridayValue().contains(feesobj.getCFri()),
					AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
					
			// Assert on fees values OF B class
			searchobj.setAccountClass(feesobj.getBaccclass());
			CSPFeesPojo bInDb = search.searchFees(searchobj, "").get(0);

			System.out.println(bInDb.getFactorValue() + " / " + feesobj.getBFactor());
			sa.assertTrue(bInDb.getFactorValue().contains(feesobj.getBFactor()),
					AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getSaturdayValue().contains(feesobj.getBSat()),
					AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getSundayValue().contains(feesobj.getBSun()),
					AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getMondayValue().contains(feesobj.getBMon()),
					AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getTuesdayValue().contains(feesobj.getBTue()),
					AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getWedensdayValue().contains(feesobj.getBWed()),
					AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getThursdayValue().contains(feesobj.getBThu()),
					AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(bInDb.getFridayValue().contains(feesobj.getBFri()),
					AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getBaccclass() +" With test case with id Of " + feesobj.getTestCaseId());

			// Assert on fees values OF A+ class
			searchobj.setAccountClass(feesobj.getAPlusaccclass());
			CSPFeesPojo aPlusInDb = search.searchFees(searchobj, "").get(0);

			System.out.println(aPlusInDb.getFactorValue() + " / " + feesobj.getAPlusFactor());
			sa.assertTrue(aPlusInDb.getFactorValue().contains(feesobj.getAPlusFactor()),
					AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() + " With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getSaturdayValue().contains(feesobj.getAPlusSat()),
					AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getSundayValue().contains(feesobj.getAPlusSun()),
					AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getMondayValue().contains(feesobj.getAPlusMon()),
					AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getTuesdayValue().contains(feesobj.getAPlusTue()),
					AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getWedensdayValue().contains(feesobj.getAPlusWed()),
					AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getThursdayValue().contains(feesobj.getAPlusThu()),
					AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aPlusInDb.getFridayValue().contains(feesobj.getAPlusFri()),
					AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
		
			// Assert on fees values OF A class
			searchobj.setAccountClass(feesobj.getAaccclass());
			CSPFeesPojo aInDb = search.searchFees(searchobj, "").get(0);
			
			System.out.println(aInDb.getFactorValue() + " / " + feesobj.getAFactor());
			sa.assertTrue(aInDb.getFactorValue().contains(feesobj.getAFactor()),
					AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() + " With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getSaturdayValue().contains(feesobj.getASat()),
					AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getSundayValue().contains(feesobj.getASun()),
					AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getMondayValue().contains(feesobj.getAMon()),
					AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getTuesdayValue().contains(feesobj.getATue()),
					AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getWedensdayValue().contains(feesobj.getAWed()),
					AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getThursdayValue().contains(feesobj.getAThu()),
					AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(aInDb.getFridayValue().contains(feesobj.getAFri()),
					AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
		
			// Assert on fees values OF C+ class
			searchobj.setAccountClass(feesobj.getCPlusaccclass());
			CSPFeesPojo cPlusInDb = search.searchFees(searchobj, "").get(0);

			System.out.println(cPlusInDb.getFactorValue() + " / " + feesobj.getCPlusFactor());
			sa.assertTrue(cPlusInDb.getFactorValue().contains(feesobj.getCPlusFactor()),
					AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() + " With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getSaturdayValue().contains(feesobj.getCPlusSat()),
					AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getSundayValue().contains(feesobj.getCPlusSun()),
					AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getMondayValue().contains(feesobj.getCPlusMon()),
					AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getTuesdayValue().contains(feesobj.getCPlusTue()),
					AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getWedensdayValue().contains(feesobj.getCPlusWed()),
					AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getThursdayValue().contains(feesobj.getCPlusThu()),
					AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
			sa.assertTrue(cPlusInDb.getFridayValue().contains(feesobj.getCPlusFri()),
					AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getCPlusaccclass() +" With test case with id Of " + feesobj.getTestCaseId());
				
		}
		sa.assertAll();

	}







	@DataProvider(name = "DataProvider")
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
			CSPFeesPojo addAccountTestData = new CSPFeesPojo();
			addAccountTestData.setCsp(resultArray.get(i).get(0).toString());
			
			addAccountTestData.setBPlusaccclass(resultArray.get(i).get(1).toString());
			addAccountTestData.setBPlusFactor(resultArray.get(i).get(2).toString());
			addAccountTestData.setBPlusSat(resultArray.get(i).get(3).toString());
			addAccountTestData.setBPlusSun(resultArray.get(i).get(4).toString());
			addAccountTestData.setBPlusMon(resultArray.get(i).get(5).toString());
			addAccountTestData.setBPlusTue(resultArray.get(i).get(6).toString());
			addAccountTestData.setBPlusWed(resultArray.get(i).get(7).toString());
			addAccountTestData.setBPlusThu(resultArray.get(i).get(8).toString());
			addAccountTestData.setBPlusFri(resultArray.get(i).get(9).toString());
			
			addAccountTestData.setCaccclass(resultArray.get(i).get(10).toString());
			addAccountTestData.setCFactor(resultArray.get(i).get(11).toString());
			addAccountTestData.setCSat(resultArray.get(i).get(12).toString());
			addAccountTestData.setCSun(resultArray.get(i).get(13).toString());
			addAccountTestData.setCMon(resultArray.get(i).get(14).toString());
			addAccountTestData.setCTue(resultArray.get(i).get(15).toString());
			addAccountTestData.setCWed(resultArray.get(i).get(16).toString());
			addAccountTestData.setCThu(resultArray.get(i).get(17).toString());
			addAccountTestData.setCFri(resultArray.get(i).get(18).toString());

			addAccountTestData.setBaccclass(resultArray.get(i).get(19).toString());
			addAccountTestData.setBFactor(resultArray.get(i).get(20).toString());
			addAccountTestData.setBSat(resultArray.get(i).get(21).toString());
			addAccountTestData.setBSun(resultArray.get(i).get(22).toString());
			addAccountTestData.setBMon(resultArray.get(i).get(23).toString());
			addAccountTestData.setBTue(resultArray.get(i).get(24).toString());
			addAccountTestData.setBWed(resultArray.get(i).get(25).toString());
			addAccountTestData.setBThu(resultArray.get(i).get(26).toString());
			addAccountTestData.setBFri(resultArray.get(i).get(27).toString());
			
			addAccountTestData.setAPlusaccclass(resultArray.get(i).get(28).toString());
			addAccountTestData.setAPlusFactor(resultArray.get(i).get(29).toString());
			addAccountTestData.setAPlusSat(resultArray.get(i).get(30).toString());
			addAccountTestData.setAPlusSun(resultArray.get(i).get(31).toString());
			addAccountTestData.setAPlusMon(resultArray.get(i).get(32).toString());
			addAccountTestData.setAPlusTue(resultArray.get(i).get(33).toString());
			addAccountTestData.setAPlusWed(resultArray.get(i).get(34).toString());
			addAccountTestData.setAPlusThu(resultArray.get(i).get(35).toString());
			addAccountTestData.setAPlusFri(resultArray.get(i).get(36).toString());
			
			addAccountTestData.setAaccclass(resultArray.get(i).get(37).toString());
			addAccountTestData.setAFactor(resultArray.get(i).get(38).toString());
			addAccountTestData.setASat(resultArray.get(i).get(39).toString());
			addAccountTestData.setASun(resultArray.get(i).get(40).toString());
			addAccountTestData.setAMon(resultArray.get(i).get(41).toString());
			addAccountTestData.setATue(resultArray.get(i).get(42).toString());
			addAccountTestData.setAWed(resultArray.get(i).get(43).toString());
			addAccountTestData.setAThu(resultArray.get(i).get(44).toString());
			addAccountTestData.setAFri(resultArray.get(i).get(45).toString());

			addAccountTestData.setCPlusaccclass(resultArray.get(i).get(46).toString());
			addAccountTestData.setCPlusFactor(resultArray.get(i).get(47).toString());
			addAccountTestData.setCPlusSat(resultArray.get(i).get(48).toString());
			addAccountTestData.setCPlusSun(resultArray.get(i).get(49).toString());
			addAccountTestData.setCPlusMon(resultArray.get(i).get(50).toString());
			addAccountTestData.setCPlusTue(resultArray.get(i).get(51).toString());
			addAccountTestData.setCPlusWed(resultArray.get(i).get(52).toString());
			addAccountTestData.setCPlusThu(resultArray.get(i).get(53).toString());
			addAccountTestData.setCPlusFri(resultArray.get(i).get(54).toString());
			
								
			addAccountTestData.setTestCaseId(resultArray.get(i).get(55).toString());
			addAccountTestData.setAction(resultArray.get(i).get(56).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(57).toString());




			result[i][0] = addAccountTestData;

		}


		return result;
	}

}
