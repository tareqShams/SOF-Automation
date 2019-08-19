package fawry.sofAutomation.testsActions.admin;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.admin.Constants;
import fawry.sofAutomation.dbVerification.admin.SearchRoleVerifications;
import fawry.sofAutomation.pages.admin.SearchRolePage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.RolePojo;
import fawry.sofAutomation.tablesVerification.admin.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.Helper;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class SearchRoleTest extends BasicTest {

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}  


	@Test(description="Validate searchRole Functionality",priority=9, dataProvider="testsearchRoleDataProvider")
	public static void searchRole(RolePojo searchRoleObject) 
	{
		test = extent.createTest("Validate searchRole Functionality");

		log = Logger.getLogger(SearchRoleTest.class.getName());

		try {
			SoftAssert sa = new SoftAssert();
			SearchRolePage searchRole= new SearchRolePage(driver);
			searchRole.navigateToSearchRoleTab();
			String actual=searchRole.SearchRolefun(searchRoleObject);

			if(searchRoleObject.getRoleType().equalsIgnoreCase("Service level"))
			{
				searchRoleObject.setRoleType("Y");
			}
			else if (searchRoleObject.getRoleType().equalsIgnoreCase("Per Csp"))
			{
				searchRoleObject.setRoleType("N");
			}


			if(actual.equalsIgnoreCase("search"))
			{
				try {
				SearchRoleVerifications searchRolever = new SearchRoleVerifications();
				RolePojo searchRoleCriteria = new RolePojo();
				searchRoleCriteria.setRoleCode(searchRoleObject.getRoleCode());
				searchRoleCriteria.setStatus(searchRoleObject.getStatus());
				searchRoleCriteria.setRoleType(searchRoleObject.getRoleType());
				searchRoleCriteria.setCsp(searchRoleObject.getCsp());				
				ArrayList<RolePojo> accountInDb = searchRolever.searchRole(searchRoleCriteria);
					SearchTableVerifications test=new SearchTableVerifications(driver);
					ArrayList<RolePojo> roleInTable=test.SearchroleTable();
					for(int i=0;i<accountInDb.size();i++)
					{
						if(!(accountInDb.contains(roleInTable.get(i))))
						{
							System.out.println("user doesnt exist in database");
						}
					}
					boolean actualdownload = searchRole.testDownloading();
					sa.assertTrue(actualdownload, "File was not Downloaded Correctly");
				} catch (SQLException e) {
					log.error(e.getClass().getSimpleName());	

				} catch (InterruptedException e) {
					log.error(e.getClass().getSimpleName());	

				}

			}
			else if (actual.equalsIgnoreCase("reset"))
			{
				boolean isResetx=searchRole.reset();
				System.out.println(isResetx);
				sa.assertTrue(isResetx, "error happen in reset functionality");

				Helper.captureScreenshot(driver, "Checking Functionality of 'Reset' Search User Button");
			}
			sa.assertAll();
		} catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}
	}



	@DataProvider(name = "testsearchRoleDataProvider")
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
			RolePojo SearchRoleTestData = new RolePojo();
			SearchRoleTestData.setRoleCode(resultArray.get(i).get(0).toString());
			SearchRoleTestData.setCsp(resultArray.get(i).get(1).toString());
			SearchRoleTestData.setStatus(resultArray.get(i).get(2).toString());
			SearchRoleTestData.setRoleType(resultArray.get(i).get(3).toString());
			SearchRoleTestData.setFlag(resultArray.get(i).get(4).toString());
			result[i][0] = SearchRoleTestData;

		}


		return result;
	}
}
