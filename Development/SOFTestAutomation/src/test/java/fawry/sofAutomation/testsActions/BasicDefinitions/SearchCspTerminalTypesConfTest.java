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
import fawry.sofAutomation.pages.basicDefinitions.SearchCspTerminalTypesConfPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspAccountTerminalConfPojo;
import fawry.sofAutomation.tablesVerification.basicDefinitions.SearchCspTerminalTypesConfTableVerification;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class SearchCspTerminalTypesConfTest extends BasicTest {

	@BeforeClass
	public void login()
	{
		LoginPage login=new LoginPage(driver);
		login.loginadd();
	}  


	@Test(description="Validate SearchCspTerminalTypesConf Functionality",priority=8, dataProvider="SearchCspTerminalTypesConfTestDataProvider")
	public static void searchCSPTerminalTypes(CspAccountTerminalConfPojo CspAccountTerminalConObj) throws SQLException, InterruptedException  
	{
		test = extent.createTest("Validate Search Csp Terimanl Types Functionality");

		SoftAssert sa=new SoftAssert();
		SearchCspTerminalTypesConfPage page=new SearchCspTerminalTypesConfPage(driver);
		page.navigateToTab("Basic Definitions", "Search CSP Terminal Types", driver);
		String actual=page.SearchCspTerminalTypesConf(CspAccountTerminalConObj);
		if(CspAccountTerminalConObj.getFlag().equalsIgnoreCase("search"))
		{
			CspTerminalTypesConfVerification DBVerificationsObj=new CspTerminalTypesConfVerification();
			ArrayList<CspAccountTerminalConfPojo> resualtFromDB =DBVerificationsObj.CspAccountTerminalConf(CspAccountTerminalConObj);
		
			if(actual.equalsIgnoreCase("No Results Found"))
			{

				sa.assertTrue(resualtFromDB==null,	"Test Case ID = "+ CspAccountTerminalConObj.getTestCaseId()+ "No Result displayed in Web Table although there is matched record in DataBase ");

			}
			else if (actual.equalsIgnoreCase("search"))
			{
				SearchCspTerminalTypesConfTableVerification SearchCspTerminalTableVerificationObj=new SearchCspTerminalTypesConfTableVerification(driver);
				ArrayList<CspAccountTerminalConfPojo> resultFromWebTable = SearchCspTerminalTableVerificationObj.searchCspBtcPoolAccount();

				System.out.println(resultFromWebTable.size());
				System.out.println(resualtFromDB.size());
				for(int i=0;i<resultFromWebTable.size();i++)
				{
					System.out.println(resultFromWebTable.get(i).getCspCode());
					System.out.println(resualtFromDB.get(i).getCspCode());
					System.out.println(resultFromWebTable.get(i).getTerminalType());
					System.out.println(resualtFromDB.get(i).getTerminalType());

				}
				if(resultFromWebTable.size()==resualtFromDB.size())
				{
					for(int i=0;i<resultFromWebTable.size();i++)
					{
						if(!(resualtFromDB.contains(resultFromWebTable.get(i))))
						{
							sa.fail(	"Test Case ID = "+ CspAccountTerminalConObj.getTestCaseId()+"user doesnt exist in database");		
						}
					}
				}
				else 
				{
					sa.fail(	"Test Case ID = "+ CspAccountTerminalConObj.getTestCaseId()+"results from database and web table not the same ");
				}
			}
		}
		else if (actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(page.reset(),	"Test Case ID = "+ CspAccountTerminalConObj.getTestCaseId()+ "Reset Functionality in Search Csp Terminal Types Conf Failed");
		}
		else if (actual.equalsIgnoreCase("Failed"))
		{
			sa.fail(	"Test Case ID = "+ CspAccountTerminalConObj.getTestCaseId()+ "Search Csp Terminal Types Conf Functionality Failed");
		}
		sa.assertAll();
	}

	@DataProvider(name = "SearchCspTerminalTypesConfTestDataProvider")
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
			CspAccountTerminalConfPojo CspAccountTerminalConf=new CspAccountTerminalConfPojo();
			CspAccountTerminalConf.setTestCaseId(resultArray.get(i).get(0).toString());
			CspAccountTerminalConf.setCspCode(resultArray.get(i).get(1).toString());
			CspAccountTerminalConf.setTerminalType(resultArray.get(i).get(2).toString());
			CspAccountTerminalConf.setFlag(resultArray.get(i).get(3).toString());
			CspAccountTerminalConf.setExpectedMessage(resultArray.get(i).get(4).toString());


			result[i][0] = CspAccountTerminalConf;

		}
		return result;

	}


}
