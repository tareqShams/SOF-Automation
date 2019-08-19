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
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftClassFactorsPage2;
import fawry.sofAutomation.pages.basicDefinitions.ConfigreCspOverDraftFeesPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class ConfigureCspOverDraftClassFactorsTest2 extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		ConfigreCspOverDraftFeesPage fees = new ConfigreCspOverDraftFeesPage(driver);
		fees.hovertopage();

	}


	@Test(description="Validate Functionality",priority=1, dataProvider="DataProvider")
	public static void configureOverDraftClassFactors(CSPFeesPojo feesobj)  
	{

		SoftAssert sa = new SoftAssert();
		ConfigreCspOverDraftClassFactorsPage2 fees = new ConfigreCspOverDraftClassFactorsPage2(driver);
		
		CSPFeesPojo bplusobject = new CSPFeesPojo();
		CSPFeesPojo cobj = new CSPFeesPojo();
		CSPFeesPojo bobj = new CSPFeesPojo();
		CSPFeesPojo aplusobj = new CSPFeesPojo();
		CSPFeesPojo aobj = new CSPFeesPojo();
		CSPFeesPojo cplusobj = new CSPFeesPojo();
		
		System.out.println(feesobj.getCsp());
		System.out.println(bplusobject.getClassname());
		System.out.println(cobj.getClassname());
		System.out.println(bobj.getClassname());
		System.out.println(aplusobj.getClassname());
		System.out.println(aobj.getClassname());
		System.out.println(cplusobj.getClassname());
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
		/*		if (feesobj.getAction().contains("Success"))
		{

			//Get account Balance before transaction
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			searchAccountCriteria.setAccountcode("");
			CSPFeesPojo accountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);

			sa.assertTrue(accountInDb.getAmount().equalsIgnoreCase(addaccounttrxobj.getAmount()),
					AssertionErrorMessages.ACCOUNT_AMOUNT_DB_EXCEL+" in test case with id Of " + addaccounttrxobj.getTestCaseId());

		}*/
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

			CSPFeesPojo bplusTestData = new CSPFeesPojo();
			bplusTestData.setClassname(resultArray.get(i).get(1).toString());
			bplusTestData.setFactorValue(resultArray.get(i).get(2).toString());
			bplusTestData.setSaturdayValue(resultArray.get(i).get(3).toString());
			bplusTestData.setSundayValue(resultArray.get(i).get(4).toString());
			bplusTestData.setMondayValue(resultArray.get(i).get(5).toString());
			bplusTestData.setTuesdayValue(resultArray.get(i).get(6).toString());
			bplusTestData.setWedensdayValue(resultArray.get(i).get(7).toString());
			bplusTestData.setThursdayValue(resultArray.get(i).get(8).toString());
			bplusTestData.setFridayValue(resultArray.get(i).get(9).toString());

			CSPFeesPojo cTestData = new CSPFeesPojo();
			cTestData.setClassname(resultArray.get(i).get(10).toString());
			cTestData.setFactorValue(resultArray.get(i).get(11).toString());
			cTestData.setSaturdayValue(resultArray.get(i).get(12).toString());
			cTestData.setSundayValue(resultArray.get(i).get(13).toString());
			cTestData.setMondayValue(resultArray.get(i).get(14).toString());
			cTestData.setTuesdayValue(resultArray.get(i).get(15).toString());
			cTestData.setWedensdayValue(resultArray.get(i).get(16).toString());
			cTestData.setThursdayValue(resultArray.get(i).get(17).toString());
			cTestData.setFridayValue(resultArray.get(i).get(18).toString());

			CSPFeesPojo bTestData = new CSPFeesPojo();
			bTestData.setClassname(resultArray.get(i).get(19).toString());
			bTestData.setFactorValue(resultArray.get(i).get(20).toString());
			bTestData.setSaturdayValue(resultArray.get(i).get(21).toString());
			bTestData.setSundayValue(resultArray.get(i).get(22).toString());
			bTestData.setMondayValue(resultArray.get(i).get(23).toString());
			bTestData.setTuesdayValue(resultArray.get(i).get(24).toString());
			bTestData.setWedensdayValue(resultArray.get(i).get(25).toString());
			bTestData.setThursdayValue(resultArray.get(i).get(26).toString());
			bTestData.setFridayValue(resultArray.get(i).get(27).toString());

			CSPFeesPojo aplusTestData = new CSPFeesPojo();
			aplusTestData.setClassname(resultArray.get(i).get(28).toString());
			aplusTestData.setFactorValue(resultArray.get(i).get(29).toString());
			aplusTestData.setSaturdayValue(resultArray.get(i).get(30).toString());
			aplusTestData.setSundayValue(resultArray.get(i).get(31).toString());
			aplusTestData.setMondayValue(resultArray.get(i).get(32).toString());
			aplusTestData.setTuesdayValue(resultArray.get(i).get(33).toString());
			aplusTestData.setWedensdayValue(resultArray.get(i).get(34).toString());
			aplusTestData.setThursdayValue(resultArray.get(i).get(35).toString());
			aplusTestData.setFridayValue(resultArray.get(i).get(36).toString());

			CSPFeesPojo aTestData = new CSPFeesPojo();
			aTestData.setClassname(resultArray.get(i).get(37).toString());
			aTestData.setFactorValue(resultArray.get(i).get(38).toString());
			aTestData.setSaturdayValue(resultArray.get(i).get(39).toString());
			aTestData.setSundayValue(resultArray.get(i).get(40).toString());
			aTestData.setMondayValue(resultArray.get(i).get(41).toString());
			aTestData.setTuesdayValue(resultArray.get(i).get(42).toString());
			aTestData.setWedensdayValue(resultArray.get(i).get(43).toString());
			aTestData.setThursdayValue(resultArray.get(i).get(44).toString());
			aTestData.setFridayValue(resultArray.get(i).get(45).toString());

			CSPFeesPojo cplusTestData = new CSPFeesPojo();
			cplusTestData.setClassname(resultArray.get(i).get(46).toString());
			cplusTestData.setFactorValue(resultArray.get(i).get(47).toString());
			cplusTestData.setSaturdayValue(resultArray.get(i).get(48).toString());
			cplusTestData.setSundayValue(resultArray.get(i).get(49).toString());
			cplusTestData.setMondayValue(resultArray.get(i).get(50).toString());
			cplusTestData.setTuesdayValue(resultArray.get(i).get(51).toString());
			cplusTestData.setWedensdayValue(resultArray.get(i).get(52).toString());
			cplusTestData.setThursdayValue(resultArray.get(i).get(53).toString());
			cplusTestData.setFridayValue(resultArray.get(i).get(54).toString());


			addAccountTestData.setTestCaseId(resultArray.get(i).get(55).toString());
			addAccountTestData.setAction(resultArray.get(i).get(56).toString());
			addAccountTestData.setExpectedMessage(resultArray.get(i).get(57).toString());




			result[i][0] = addAccountTestData;
		}
		return result;
	}

}
