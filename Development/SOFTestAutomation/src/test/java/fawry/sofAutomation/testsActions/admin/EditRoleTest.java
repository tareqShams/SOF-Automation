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
import fawry.sofAutomation.pages.admin.EditRolePage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.RolePojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class EditRoleTest extends BasicTest {

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}
	@Test(description="Validate EditRole Functionality",priority=10, dataProvider="EditRoletestDataProvider")
	public static void EditRole(RolePojo roleObject) 
	{
		test = extent.createTest("Validate EditRole Functionality");

		log = Logger.getLogger(EditRoleTest.class.getName());

		SoftAssert sa = new SoftAssert();
		EditRolePage page=new EditRolePage(driver);
		page.navigateToTab("Admin","Search Role",driver);
		try {
			page.search(roleObject);
		
		RolePojo searchRoleCeritria=new RolePojo();
		searchRoleCeritria.setRoleCode(roleObject.getRoleCode());
		SearchRoleVerifications search=new SearchRoleVerifications();
		ArrayList<RolePojo> searchGroupDBALL;
		String actual;
		if(roleObject.getFlag().contains("reset"))
		{
			searchGroupDBALL	= search.addRole(searchRoleCeritria);
			actual =page.EditRolefun(roleObject);
		}
		else
		{
			actual =page.EditRolefun(roleObject);
			searchGroupDBALL	= search.addRole(searchRoleCeritria);
		}

		if(actual.equalsIgnoreCase(roleObject.getExpectedMessage()))	
		{
			if(actual.equalsIgnoreCase("Role is deleted successfully"))
			{
				sa.assertTrue(searchGroupDBALL.size()==0, "delete functionality is failed");
			}

			else if(actual.equalsIgnoreCase("Role updated successfully"))
			{
				RolePojo searchGroupDB = searchGroupDBALL.get(0);
				sa.assertTrue(((roleObject.getStatus().isEmpty()||roleObject.getStatus()==null)?(true):(roleObject.getStatus().contains(searchGroupDB.getStatus()))), "status in Excel And DB Mismatch");
				sa.assertTrue(((roleObject.getRoleName().isEmpty()||roleObject.getRoleName()==null)?(true):(roleObject.getRoleName().contains(searchGroupDB.getRoleName()))), "name in Excel And DB Mismatch");
				sa.assertTrue(((roleObject.getDescription().isEmpty()||roleObject.getDescription()==null)?(true):(roleObject.getDescription().contains(searchGroupDB.getDescription()))), "description in Excel And DB Mismatch");
				sa.assertTrue(((roleObject.getRoleType().isEmpty()||roleObject.getRoleType()==null)?(true):(roleObject.getRoleType().contains(searchGroupDB.getRoleType()))), "roletype in Excel And DB Mismatch");
				sa.assertTrue(((roleObject.getCsp().isEmpty()||roleObject.getCsp()==null)?(true):(roleObject.getCsp().contains(searchGroupDB.getCsp()))), "csp in Excel And DB Mismatch");
				// Check that size of user added to group equals the size of users added in DB
				if(searchGroupDB.getGroupsData().size()==roleObject.getGroupsData().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<roleObject.getGroupsData().size();i++)
					{
						sa.assertTrue(searchGroupDB.getGroupsData().contains(roleObject.getGroupsData().get(i)), "Role Group not matched ");
					}
				}
				// size of user added to group does not equal the size of users added in DB
				else
				{
					sa.fail(" size of user added to group does not equal the size of users added in DB");

				}		
				// Check that size of user added to group equals the size of users added in DB
				if(searchGroupDB.getPermissionsData().size()==roleObject.getPermissionsData().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<roleObject.getPermissionsData().size();i++)
					{
						sa.assertTrue(searchGroupDB.getPermissionsData().contains(roleObject.getPermissionsData().get(i)), "Role Permisions not matched ");
					}
				}
				// size of user added to group does not equal the size of users added in DB
				else
				{
					sa.fail(" size of user added to group does not equal the size of users added in DB");

				}		
			}
		}
		else if(actual.equalsIgnoreCase("reset"))
		{
			RolePojo searchGroupDBafterreset = search.addRole(searchRoleCeritria ).get(0);
			sa.assertTrue(searchGroupDBafterreset.getStatus().contains(searchGroupDBALL.get(0).getStatus()), "status in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBafterreset.getRoleName().contains(searchGroupDBALL.get(0).getRoleName()), "name in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBafterreset.getDescription().contains(searchGroupDBALL.get(0).getDescription()), "description in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBafterreset.getRoleType().contains(searchGroupDBALL.get(0).getRoleType()), "roletype in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBafterreset.getCsp().contains(searchGroupDBALL.get(0).getCsp()), "csp in Excel And DB Mismatch");
			//sa.assertTrue(searchGroupDBafterreset.getGroupsData().contains(searchGroupDBALL.get(0).getGroupsData()), "GroupsData in Excel And DB Mismatch");
			//sa.assertTrue(searchGroupDBafterreset.getPermissionsData().contains(searchGroupDBALL.get(0).getPermissionsData()), "PermissionsData in Excel And DB Mismatch");

		
		}
		else if(actual.equalsIgnoreCase("back"))
		{
			sa.assertTrue(page.navigateback(),"error accour while navigating back ");
		}
		else
		{
			sa.fail("Edit Role Test Case OF Failed");
		}
		sa.assertAll();
		} catch (InterruptedException e) {
			log.error(e.getClass().getSimpleName());	

		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	

		}

	}


	@DataProvider(name = "EditRoletestDataProvider")
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
			str.clear();
			String[] splittedString1 =(resultArray.get(i).get(6).toString().split(","));
			for(int j=0; j<splittedString1.length;j++)
			{
				if(splittedString1[j] != null || !splittedString1[j].isEmpty())
					
				{
				str.add(splittedString1[j]);
				}
			}
			AddRoleTestData.setPermissionsData(str);
			result[i][0] = AddRoleTestData;


		}


		return result;
	}
}
