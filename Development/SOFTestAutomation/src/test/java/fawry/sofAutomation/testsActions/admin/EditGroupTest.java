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
import fawry.sofAutomation.pages.admin.EditGroupPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.GroupPojo;
import fawry.sofAutomation.pojos.admin.SearchGroupPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class EditGroupTest extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}
	@Test(description="Validate EditGroup Functionality",priority=7, dataProvider="EditGrouptestDataProvider")
	public static void Editgroup(GroupPojo groupObject)  
	{	
		test = extent.createTest("Validate EditGroup Functionality");

		log = Logger.getLogger(EditGroupTest.class.getName());

		SoftAssert sa = new SoftAssert();
		EditGroupPage page=new EditGroupPage(driver);
		page.navigateToTab("Admin","Search Group", driver);
		try {
			page.search(groupObject);
		
		String actual;
		SearchGroupPojo searchGroupCeritria=new SearchGroupPojo();
		searchGroupCeritria.setGroupCode(groupObject.GetGroupCode());
		SearchGroupVerification search=new SearchGroupVerification();
		GroupPojo searchGroupDB;
		if(groupObject.getFlag().contains("reset"))
		{
			searchGroupDB= search.addgroup(searchGroupCeritria );
			actual =page.editgroupfun(groupObject);

		}
		else
		{

			actual =page.editgroupfun(groupObject);
			searchGroupDB= search.addgroup(searchGroupCeritria );
		}
		if(actual.equalsIgnoreCase(groupObject.getExpectedMessage()))	
		{
			if(actual.equalsIgnoreCase("Group is deleted successfully"))
			{
				sa.assertTrue(searchGroupDB.GetGroupStatus().equals("DELETED"),"error");
				//SearchGroupVerification update=new SearchGroupVerification();
				//update.updategroup(groupObject.GetGroupCode());

			}
			else if (actual.equalsIgnoreCase("Group updated successfully"))
			{
				sa.assertTrue(((groupObject.GetGroupStatus().isEmpty()||groupObject.GetGroupStatus()==null)?(true):(groupObject.GetGroupStatus().contains(searchGroupDB.GetGroupStatus()))), "code in Excel And DB Mismatch");
				sa.assertTrue(((groupObject.GetGroupName().isEmpty()||groupObject.GetGroupName()==null)?(true):(groupObject.GetGroupName().contains(searchGroupDB.GetGroupName()))), "code in Excel And DB Mismatch");
				sa.assertTrue(((groupObject.GetDescription().isEmpty()||groupObject.GetDescription()==null)?(true):(groupObject.GetDescription().contains(searchGroupDB.GetDescription()))), "code in Excel And DB Mismatch");
				sa.assertTrue(((groupObject.GetGroupType().isEmpty()||groupObject.GetGroupType()==null)?(true):(groupObject.GetGroupType().contains(searchGroupDB.GetGroupType()))), "code in Excel And DB Mismatch");
				sa.assertTrue(((groupObject.GetCSP().isEmpty()||groupObject.GetCSP()==null)?(true):(groupObject.GetCSP().contains(searchGroupDB.GetCSP()))), "code in Excel And DB Mismatch");
				sa.assertTrue(((groupObject.GetGroupRole().isEmpty()||groupObject.GetGroupRole()==null)?(true):(groupObject.GetGroupRole().contains(searchGroupDB.GetGroupRole()))), "code in Excel And DB Mismatch");
				// Check that size of user added to group equals the size of users added in DB
				System.out.println(searchGroupDB.getUsersData().size()+"////////////////"+groupObject.getUsersData().size());
				if(searchGroupDB.getUsersData().size()==groupObject.getUsersData().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<groupObject.getUsersData().size();i++)
					{
						sa.assertTrue(searchGroupDB.getUsersData().contains(groupObject.getUsersData().get(i)), "group user not matched ");
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
			GroupPojo searchGroupDBAfterReset= search.addgroup(searchGroupCeritria );

			sa.assertTrue(searchGroupDBAfterReset.GetGroupStatus().contains(searchGroupDB.GetGroupStatus()), "code in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBAfterReset.GetGroupName().contains(searchGroupDB.GetGroupName()), "code in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBAfterReset.GetDescription().contains(searchGroupDB.GetDescription()), "code in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBAfterReset.GetGroupType().contains(searchGroupDB.GetGroupType()), "code in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBAfterReset.GetCSP().contains(searchGroupDB.GetCSP()), "code in Excel And DB Mismatch");
			sa.assertTrue(searchGroupDBAfterReset.GetGroupRole().contains(searchGroupDB.GetGroupRole()), "code in Excel And DB Mismatch");
			// Check that size of user added to group equals the size of users added in DB
			if(searchGroupDB.getUsersData().size()==groupObject.getUsersData().size())
			{
				// check that same users added to group and DB
				for(int i=0 ;i<groupObject.getUsersData().size();i++)
				{
					sa.assertTrue(searchGroupDB.getUsersData().contains(groupObject.getUsersData().get(i)), "group user not matched ");
				}
			}
			// size of user added to group does not equal the size of users added in DB
			else
			{
				sa.fail(" size of user added to group does not equal the size of users added in DB");

			}		

		}
		else if(actual.equalsIgnoreCase("back"))
		{
			sa.assertTrue(driver.getCurrentUrl().contains("SearchGroup"),"error accour while navigating back");
			SearchGroupVerification update=new SearchGroupVerification();
			update.updategroup(groupObject.GetGroupCode());
		}
		else
		{
			sa.fail("Edit Group Test Case OF Failed");
		}
		sa.assertAll();
		} catch (InterruptedException e) {
			log.error(e.getClass().getSimpleName());	

		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	

		}catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}
	}


	@DataProvider(name = "EditGrouptestDataProvider")
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
			GroupPojo editGroup=new GroupPojo();
			ArrayList<String> str=new ArrayList<>();

			editGroup.setGroupCode(resultArray.get(i).get(0).toString());
			editGroup.setGroupStatus(resultArray.get(i).get(1).toString());
			editGroup.setGroupName(resultArray.get(i).get(2).toString());
			editGroup.setDescription(resultArray.get(i).get(3).toString());
			editGroup.setGroupType(resultArray.get(i).get(4).toString());
			editGroup.setCSP(resultArray.get(i).get(5).toString());
			editGroup.setGroupRole(resultArray.get(i).get(6).toString());
			editGroup.setFlag(resultArray.get(i).get(8).toString());
			editGroup.setExpectedMessage(resultArray.get(i).get(9).toString());
			String[] splittedString =(resultArray.get(i).get(6).toString().split(","));
			for(int j=0; j<splittedString.length;j++)
			{
				str.add(splittedString[j]);
			}
			editGroup.setUsersData(str);
			result[i][0] = editGroup;

		}
		return result;

	}
}

