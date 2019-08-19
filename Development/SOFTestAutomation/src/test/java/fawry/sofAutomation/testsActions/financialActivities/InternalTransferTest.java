package fawry.sofAutomation.testsActions.financialActivities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.financialActivities.AssertionErrorMessages;
import fawry.sofAutomation.constants.financialActivities.Constants;
import fawry.sofAutomation.dbVerification.financialActivities.SearchVefication;
import fawry.sofAutomation.pages.financialActivities.InternalTransferPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class InternalTransferTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		InternalTransferPage internaltransfer = new InternalTransferPage(driver);
		internaltransfer.movetointernaltransferpage();

	}


	@Test(description="Validate internal Transfer Functionality",priority=1, dataProvider="TransferDataProvider")
	public static void internalTransfer(AccountTrxPojo internaltransferobj)  
	{
		test = extent.createTest("Validate internal Transfer Functionality");

		SoftAssert sa = new SoftAssert();
		InternalTransferPage interntra = new InternalTransferPage(driver);

		driver.navigate().to(Constants.INTERNAL_TRANSFER_URL);

		//Inserting Data into common Fields
		interntra.CommonFields(internaltransferobj);
		// TAking Action of adding, resetting, confirming or canceling.
		interntra.saveOrResetData(internaltransferobj);
		//Collecting Success or fail messages
		String actual = interntra.ErrorMessagesAndSuccessMessage(internaltransferobj);
		sa.assertTrue(actual.contains(internaltransferobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + internaltransferobj.getTestCaseId());
		//assert on DB Only when an account is added Successfully
		if (internaltransferobj.getAction().contains("Success"))
		{
			// Get account Balance before transaction
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			
			// Get First account amount
			searchAccountCriteria.setAccountcode(internaltransferobj.getAccountcode());
			AccountTrxPojo fromaccountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);
			
			// Get second account transaction amount
			searchAccountCriteria.setAccountcode(internaltransferobj.getToaccountCode());
			AccountTrxPojo toaccountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);

			sa.assertTrue(fromaccountInDb.getAmount().equalsIgnoreCase(toaccountInDb.getAmount()),
					AssertionErrorMessages.ACCOUNT_AMOUNT_DB_EXCEL+" in test case with id Of " + internaltransferobj.getTestCaseId());


		}
		sa.assertAll();

	}







	@DataProvider(name = "TransferDataProvider")
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

			AccountTrxPojo testData = new AccountTrxPojo();

			testData.setCsp(resultArray.get(i).get(0).toString());
			testData.setAccountcode(resultArray.get(i).get(1).toString());
			testData.setToaccountCode(resultArray.get(i).get(2).toString());
			testData.setAmount(resultArray.get(i).get(3).toString());
			testData.setDescription(resultArray.get(i).get(4).toString());
			testData.setTestCaseId(resultArray.get(i).get(5).toString());
			testData.setAction(resultArray.get(i).get(6).toString());
			testData.setExpectedMessage(resultArray.get(i).get(7).toString());

			result[i][0] = testData;

		}


		return result;
	}

}
