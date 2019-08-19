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
import fawry.sofAutomation.pages.admin.AddRolePage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.RolePojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class AddRoleTest extends BasicTest{

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}  


	@Test(description="Validate AddRole Functionality",priority=8, dataProvider="testAddRoleDataProvider")
	public static void AddRole(RolePojo AddRoleObject) 
	{
		test = extent.createTest("Validate AddRole Functionality");

		log = Logger.getLogger(AddRoleTest.class.getName());

		SoftAssert sa = new SoftAssert();

		AddRolePage AddRole = new AddRolePage(driver);
		AddRole.navigateToTab("Admin" , "Add Role", driver);;
		String actual;
		try {
			actual = AddRole.AddRolefun(AddRoleObject);
		
		String expected = AddRoleObject.getExpectedMessage();
		if(AddRoleObject.getRoleType().equalsIgnoreCase("Service level"))
		{
			AddRoleObject.setRoleType("Y");
		}
		else if (AddRoleObject.getRoleType().equalsIgnoreCase("Per Csp"))
		{
			AddRoleObject.setRoleType("N");
		}
		if(actual.contains(expected)){
			if(actual.contains("Role Added Successfully"))
			{

				SearchRoleVerifications searchrole = new SearchRoleVerifications();
				RolePojo searchRoleCriteria = new RolePojo();
				searchRoleCriteria.setRoleCode(AddRoleObject.getRoleCode());
				RolePojo accountInDb = searchrole.addRole(searchRoleCriteria).get(0);	

				sa.assertTrue(AddRoleObject.getRoleCode().contains(accountInDb.getRoleCode()), "code in Excel And DB Mismatch");
				sa.assertTrue(AddRoleObject.getRoleName().contains(accountInDb.getRoleName()), "name in Excel And DB Mismatch");
				sa.assertTrue(AddRoleObject.getDescription().contains(accountInDb.getDescription()), "description in Excel And DB Mismatch");
				sa.assertTrue(AddRoleObject.getRoleType().contains(accountInDb.getRoleType()), "type in Excel And DB Mismatch");
				sa.assertTrue(AddRoleObject.getCsp().contains(accountInDb.getCsp()), "csp in Excel And DB Mismatch");
				// Check that size of user added to group equals the size of users added in DB
				if(accountInDb.getGroupsData().size()==AddRoleObject.getGroupsData().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<AddRoleObject.getGroupsData().size();i++)
					{
						System.out.println(accountInDb.getGroupsData().get(i)+"////////////"+AddRoleObject.getGroupsData().get(i));
						sa.assertTrue(accountInDb.getGroupsData().contains(AddRoleObject.getGroupsData().get(i)), "group Roles not matched ");
					}
				}
				// size of user added to group does not equal the size of users added in DB
				else
				{
					sa.fail(" size of user added to group does not equal the size of users added in DB");

				}		
				// Check that size of user added to group equals the size of users added in DB
				if(accountInDb.getPermissionsData().size()==AddRoleObject.getPermissionsData().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<AddRoleObject.getPermissionsData().size();i++)
					{
						sa.assertTrue(accountInDb.getPermissionsData().contains(AddRoleObject.getPermissionsData().get(i)), "group perm not matched ");
					}
				}
				// size of user added to group does not equal the size of users added in DB
				else
				{
					sa.fail(" size of user added to group does not equal the size of users added in DB");

				}	

			}
			else
			{
				System.out.println("error msgs correct");

			}
		}
		else if(actual.equalsIgnoreCase("reset"))
		{
			sa.assertTrue(AddRole.isreset(),"reset edit ROLE faild");

		}
		else if(actual.equalsIgnoreCase("fail"))
		{
			System.out.println("failed");

		}
		sa.assertAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error(e.getClass().getSimpleName());	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getClass().getSimpleName());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getClass().getSimpleName());	
		}
		
	}
	@DataProvider(name = "testAddRoleDataProvider")
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
			RolePojo AddRoleTestData = new RolePojo();
			ArrayList<String> str=new ArrayList<>();
			ArrayList<String> str2=new ArrayList<>();

			AddRoleTestData.setRoleCode(resultArray.get(i).get(0).toString());
			AddRoleTestData.setRoleName(resultArray.get(i).get(1).toString());
			AddRoleTestData.setDescription(resultArray.get(i).get(2).toString());
			AddRoleTestData.setRoleType(resultArray.get(i).get(3).toString());
			AddRoleTestData.setCsp(resultArray.get(i).get(4).toString());
			AddRoleTestData.setFlag(resultArray.get(i).get(8).toString());
			AddRoleTestData.setStatus(resultArray.get(i).get(7).toString());
            AddRoleTestData.setExpectedMessage(resultArray.get(i).get(9).toString());
			String[] splittedString =(resultArray.get(i).get(5).toString().split(","));
			for(int j=0; j<splittedString.length;j++)
			{
				if(splittedString[j] != null || !splittedString[j].isEmpty())
					
				{
				str.add(splittedString[j]);
				}
			}
			AddRoleTestData.setGroupsData(str);
			
			String[] splittedString1 =(resultArray.get(i).get(6).toString().split(","));
			for(int j=0; j<splittedString1.length;j++)
			{
				if(splittedString1[j] != null || !splittedString1[j].isEmpty())
					
				{
				str2.add(splittedString1[j]);
				}
			}
			AddRoleTestData.setPermissionsData(str2);
			result[i][0] = AddRoleTestData;


		}


		return result;
	}

}
