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
import fawry.sofAutomation.pages.financialActivities.MultipleTransferPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;



public class MultipleTransferTest extends BasicTest{


	@BeforeClass
	public void login() 
	{
		LoginPage login = new LoginPage(driver);
		login.successfulllogin();
		MultipleTransferPage multitransfer = new MultipleTransferPage(driver);
		multitransfer.movetomultipletransferpage();

	}


	@Test(description="Validate Multiple Transfer Functionality",priority=1, dataProvider="TransferDataProvider")
	public static void multipleTransfer(AccountTrxPojo multipletransferobj)  
	{
		test = extent.createTest("Validate Multiple Transfer Functionality");

		driver.navigate().to(Constants.MULTIPLE_TRANSFER_URL);
		
		SoftAssert sa = new SoftAssert();
		MultipleTransferPage multitra = new MultipleTransferPage(driver);



		//Inserting Data into common Fields
		multitra.CommonFields(multipletransferobj);
		// TAking Action of adding, resetting, confirming or canceling.
		multitra.saveOrResetData(multipletransferobj);
		//Collecting Success or fail messages
		String actual = multitra.ErrorMessagesAndSuccessMessage(multipletransferobj);
		sa.assertTrue(actual.contains(multipletransferobj.getExpectedMessage()),
				AssertionErrorMessages.EXPECTED_ACTUAL_EXCEL_WEBAPP + multipletransferobj.getTestCaseId());
		//assert on DB Only when an account is added Successfully
		if (multipletransferobj.getAction().contains("new"))
		{
			// Get account Balance before transaction
			SearchVefication searchAcc = new SearchVefication();
			SearchPojo searchAccountCriteria = new SearchPojo();
			
			// Get First account amount
			searchAccountCriteria.setAccountcode(multipletransferobj.getAccountcode());
			AccountTrxPojo fromaccountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);
			
			// Get second account transaction amount
			searchAccountCriteria.setAccountcode(multipletransferobj.getToaccountCode());
			AccountTrxPojo toaccountInDb = searchAcc.searchAccountTrx(searchAccountCriteria, "Add").get(0);

			sa.assertTrue(fromaccountInDb.getAmount().equalsIgnoreCase(toaccountInDb.getAmount()),
					AssertionErrorMessages.ACCOUNT_AMOUNT_DB_EXCEL+" in test case with id Of " + multipletransferobj.getTestCaseId());


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
			testData.setAmount(resultArray.get(i).get(1).toString());
			testData.setSecondamount(resultArray.get(i).get(2).toString());
			testData.setThirdamount(resultArray.get(i).get(3).toString());
			testData.setDescription(resultArray.get(i).get(4).toString());
			testData.setTestCaseId(resultArray.get(i).get(5).toString());
			testData.setAction(resultArray.get(i).get(6).toString());
			testData.setExpectedMessage(resultArray.get(i).get(7).toString());

			result[i][0] = testData;

		}


		return result;
	}

}
