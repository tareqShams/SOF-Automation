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
import fawry.sofAutomation.dbVerification.admin.SearchGroupVerification;
import fawry.sofAutomation.pages.admin.SearchGroupPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.GroupPojo;
import fawry.sofAutomation.pojos.admin.SearchGroupPojo;
import fawry.sofAutomation.tablesVerification.admin.SearchTableVerifications;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class SearchGroupTest extends BasicTest{

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}  


	@Test(description="Validate searchgroup Functionality",priority=6, dataProvider="testSearchGroupDataProvider")
	public static void searchGroup(SearchGroupPojo SearchGroupObject)  
	{
		test = extent.createTest("Validate searchgroup Functionality");

		log = Logger.getLogger(SearchGroupTest.class.getName());
		try {
			SoftAssert sa = new SoftAssert();
			SearchGroupPage SearchGroup = new SearchGroupPage(driver);
			SearchGroup.navigateToTab("Admin", "Search Group",driver);
			String actual = SearchGroup.SearchGroupfun(SearchGroupObject);
			if(SearchGroupObject.GetGroupType().equalsIgnoreCase("Service level"))
			{
				SearchGroupObject.setGroupType("Y");
			}
			else if (SearchGroupObject.GetGroupType().equalsIgnoreCase("Per Csp"))
			{
				SearchGroupObject.setGroupType("N");
			}

			if (actual.equalsIgnoreCase("search"))
			{
				SearchGroupVerification searchgroup = new SearchGroupVerification();
				SearchGroupPojo searchGroupCriteria = new SearchGroupPojo();
				searchGroupCriteria.setGroupCode(SearchGroupObject.GetGroupCode());
				searchGroupCriteria.setCSP(SearchGroupObject.GetCSP());
				searchGroupCriteria.setGroupType(SearchGroupObject.GetGroupType());
				searchGroupCriteria.setStatus(SearchGroupObject.GetStatus());
				ArrayList<GroupPojo> accountInDb = searchgroup.searchgroup(searchGroupCriteria);
				SearchTableVerifications test=new SearchTableVerifications(driver);
				ArrayList<GroupPojo> userInTable=test.SearchgroupTable();
				for(int i=0;i<accountInDb.size();i++)
				{
					if(!(accountInDb.contains(userInTable.get(i))))
					{
						System.out.println("user doesnt exist in database");
					}
				}
				boolean actualdownload = SearchGroup.testDownloading();
				sa.assertTrue(actualdownload, "File was not Downloaded Correctly");
			}
			else if(actual.equalsIgnoreCase("reset"))
			{
				sa.assertTrue(SearchGroup.isreset(), "reset search group error");
			}
			sa.assertAll();

		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	

		} catch (InterruptedException e) {
			log.error(e.getClass().getSimpleName());	

		}catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}

	}
	@DataProvider(name = "testSearchGroupDataProvider")
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
			SearchGroupPojo SearchTestData = new SearchGroupPojo();

			SearchTestData.setGroupCode(resultArray.get(i).get(0).toString());
			SearchTestData.setCSP(resultArray.get(i).get(1).toString());
			SearchTestData.setStatus(resultArray.get(i).get(2).toString());
			SearchTestData.setGroupType(resultArray.get(i).get(3).toString());
			SearchTestData.setFlag(resultArray.get(i).get(4).toString());



			result[i][0] = SearchTestData;


		}


		return result;
	}

}
