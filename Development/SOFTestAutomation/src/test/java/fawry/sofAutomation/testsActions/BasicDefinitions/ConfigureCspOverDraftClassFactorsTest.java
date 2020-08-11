package fawry.sofAutomation.testsActions.basicDefinitions;

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
import fawry.sofAutomation.pojos.accounts.CustomerPojo;
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
			for(int i =1 ; i <= feesobj.getNumberOfClasses(); i++)
			{
				if(i==1)
				{
					searchobj.setAccountClass(feesobj.getFees().get(0).getAccountclass());
				}
				else if(i==2)
				{
					searchobj.setAccountClass(feesobj.getFeesA().get(0).getAccountclass());
				}				
				else if(i==3)
				{
					searchobj.setAccountClass(feesobj.getFeesB().get(0).getAccountclass());
				}
				// Assert on fees values 

				CSPFeesPojo feesInDb = search.searchFees(searchobj, "").get(0);

				System.out.println(feesInDb.getFactor() + " / " + feesobj.getFactor());
				sa.assertTrue(feesobj.getFactor().contains(feesInDb.getFactor()),
						AssertionErrorMessages.FACTOR_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getSat().contains(feesobj.getSat()),
						AssertionErrorMessages.SAT_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getSun().contains(feesobj.getSun()),
						AssertionErrorMessages.SUN_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getMon().contains(feesobj.getMon()),
						AssertionErrorMessages.MON_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getTue().contains(feesobj.getTue()),
						AssertionErrorMessages.TUE_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getWed().contains(feesobj.getWed()),
						AssertionErrorMessages.WED_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getThu().contains(feesobj.getThu()),
						AssertionErrorMessages.THU_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
				sa.assertTrue(feesInDb.getFri().contains(feesobj.getFri()),
						AssertionErrorMessages.FRI_VALUE_EXCEL_DB+ " In Account Class "+ feesobj.getAccountclass() +" With test case with id Of " + feesobj.getTestCaseId());
			}				
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
			CSPFeesPojo TestData = new CSPFeesPojo();
			TestData.setCsp(resultArray.get(i).get(0).toString());

			ArrayList<CSPFeesPojo> fees = new ArrayList<CSPFeesPojo>();
			CSPFeesPojo fee = new CSPFeesPojo();
			fee.setAccountclass(resultArray.get(i).get(1).toString());
			fee.setFactor(resultArray.get(i).get(2).toString());
			fee.setSat(resultArray.get(i).get(3).toString());
			fee.setSun(resultArray.get(i).get(4).toString());
			fee.setMon(resultArray.get(i).get(5).toString());
			fee.setTue(resultArray.get(i).get(6).toString());
			fee.setWed(resultArray.get(i).get(7).toString());
			fee.setThu(resultArray.get(i).get(8).toString());
			fee.setFri(resultArray.get(i).get(9).toString());
			fees.add(fee);
			TestData.setFees(fees);

			ArrayList<CSPFeesPojo> feesA = new ArrayList<CSPFeesPojo>();
			CSPFeesPojo feeA = new CSPFeesPojo();
			feeA.setAccountclass(resultArray.get(i).get(10).toString());
			feeA.setFactor(resultArray.get(i).get(11).toString());
			feeA.setSat(resultArray.get(i).get(12).toString());
			feeA.setSun(resultArray.get(i).get(13).toString());
			feeA.setMon(resultArray.get(i).get(14).toString());
			feeA.setTue(resultArray.get(i).get(15).toString());
			feeA.setWed(resultArray.get(i).get(16).toString());
			feeA.setThu(resultArray.get(i).get(17).toString());
			feeA.setFri(resultArray.get(i).get(18).toString());
			feesA.add(feeA);
			TestData.setFeesA(feesA);

			ArrayList<CSPFeesPojo> feesB = new ArrayList<CSPFeesPojo>();
			CSPFeesPojo feeB = new CSPFeesPojo();
			feeB.setAccountclass(resultArray.get(i).get(19).toString());
			feeB.setFactor(resultArray.get(i).get(20).toString());
			feeB.setSat(resultArray.get(i).get(21).toString());
			feeB.setSun(resultArray.get(i).get(22).toString());
			feeB.setMon(resultArray.get(i).get(23).toString());
			feeB.setTue(resultArray.get(i).get(24).toString());
			feeB.setWed(resultArray.get(i).get(25).toString());
			feeB.setThu(resultArray.get(i).get(26).toString());
			feeB.setFri(resultArray.get(i).get(27).toString());
			feesB.add(feeB);
			TestData.setFeesB(feesB);

			TestData.setTestCaseId(resultArray.get(i).get(28).toString());
			TestData.setAction(resultArray.get(i).get(29).toString());
			TestData.setExpectedMessage(resultArray.get(i).get(30).toString());

			result[i][0] = TestData;

		}


		return result;
	}

}
