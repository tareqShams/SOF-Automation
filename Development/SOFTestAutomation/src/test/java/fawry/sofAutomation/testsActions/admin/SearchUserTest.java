package fawry.sofAutomation.testsActions.admin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import fawry.sofAutomation.constants.admin.Constants;
import fawry.sofAutomation.dbVerification.admin.SearchUserVerifications;
import fawry.sofAutomation.pages.admin.SearchUserPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.SearchUserPojo;
import fawry.sofAutomation.pojos.admin.UserPojo;
import fawry.sofAutomation.tablesVerification.admin.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.Helper;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class SearchUserTest extends BasicTest {

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}  

	@Test(description="Validate searchUser Functionality",priority=3, dataProvider="testDataProvider")
	public static void searchUser(SearchUserPojo SearchObject) 
	{
		test = extent.createTest("Validate searchUser Functionality");

		log = Logger.getLogger(SearchUserTest.class.getName());

		SoftAssert sa = new SoftAssert();
		SearchUserPage Search = new SearchUserPage(driver);
		Search.navigateToTab("Admin", "Search User",driver);
		String actual = Search.SearchUserfun(SearchObject);
		boolean actualdownload;

		if (actual.equalsIgnoreCase("search")) {
			SearchUserVerifications searchUser = new SearchUserVerifications();
			SearchUserPojo searchUserCriteria = setSearchCriatria(SearchObject);
			ArrayList<UserPojo> accountInDb = searchUser.SearchUserVerificationsForSearch(searchUserCriteria);
			SearchTableVerifications test=new SearchTableVerifications(driver);
			ArrayList<UserPojo> userInTable;
			try {
				userInTable = test.SearchuserTable();
				if(userInTable.size()==accountInDb.size())
				{
					for(int i=0;i<userInTable.size();i++)
					{
						if(!(accountInDb.contains(userInTable.get(i))))
						{
							sa.fail("user doesnt exist in database");		
						}
					}
				}
				else{
					sa.fail("results from database and web table not the same ");
				}
			} catch (InterruptedException e) {
				log.error(e.getCause());	
			}
			try 
			{
				actualdownload = Search.testDownloading();
				sa.assertTrue(actualdownload, "File was not Downloaded Correctly");
			} 
			catch (InterruptedException e) 
			{
				log.error(e.getCause());	
			}

		}
		else {
			boolean isReset=Search.reset();
			sa.assertTrue(isReset, "error happen in reset functionality");
			Helper.captureScreenshot(driver, "Checking Functionality of 'Reset' Search User Button");
		}
		sa.assertAll();

	}


	@DataProvider(name = "testDataProvider")
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
			SearchUserPojo SearchTestData = new SearchUserPojo();
			SearchTestData.setUserName(resultArray.get(i).get(0).toString());
			SearchTestData.setFirstName(resultArray.get(i).get(1).toString());
			SearchTestData.setLastName(resultArray.get(i).get(2).toString());
			SearchTestData.setEmail(resultArray.get(i).get(3).toString());
			SearchTestData.setJobTitle(resultArray.get(i).get(4).toString());
			SearchTestData.setTelephoneExt(resultArray.get(i).get(5).toString());
			SearchTestData.setCsp(resultArray.get(i).get(6).toString());
			SearchTestData.setDepartment(resultArray.get(i).get(7).toString());
			SearchTestData.setGroups(resultArray.get(i).get(8).toString());
			SearchTestData.setStatus(resultArray.get(i).get(9).toString());
			SearchTestData.setGrade(resultArray.get(i).get(10).toString());
			SearchTestData.setMobileNumber(resultArray.get(i).get(11).toString());
			SearchTestData.setFlag(resultArray.get(i).get(12).toString());
			result[i][0] = SearchTestData;
		}
		return result;
	}
	public static SearchUserPojo setSearchCriatria(SearchUserPojo user)
	{
		SearchUserPojo searchUserCriteria = new SearchUserPojo();
		searchUserCriteria.setUserName(user.getUserName());
		searchUserCriteria.setFirstName(user.getFirstName());
		searchUserCriteria.setLastName(user.getLastName());
		searchUserCriteria.setEmail(user.getEmail());
		searchUserCriteria.setJobTitle(user.getJobTitle());
		searchUserCriteria.setTelephoneExt(user.getTelephoneExt());
		searchUserCriteria.setStatus(user.getStatus());
		searchUserCriteria.setCsp(user.getCsp());
		searchUserCriteria.setDepartment(user.getDepartment());
		searchUserCriteria.setGroups(user.getGroups());
		searchUserCriteria.setGrade(user.getGrade());
		searchUserCriteria.setMobileNumber(user.getMobileNumber());
		return searchUserCriteria;
	}

}
