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
import fawry.sofAutomation.pages.admin.AddGroupPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.GroupPojo;
import fawry.sofAutomation.pojos.admin.SearchGroupPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class AddGroupTest  extends BasicTest{

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}

	@Test(description="Validate AddGroup Functionality",priority=5, dataProvider="AddGrouptestDataProvider")
	public static void AddGroup(GroupPojo AddGroupObject)  
	{
		test = extent.createTest("Validate AddGroup Functionality");

		log = Logger.getLogger(AddGroupTest.class.getName());

		SoftAssert sa =new SoftAssert();
		AddGroupPage group =new AddGroupPage(driver);
		group.navigateToTab("Admin" , "Add Group",driver);
		String actual;
		try {
			actual = group.AddGroup(AddGroupObject);

			String expected =AddGroupObject.getExpectedMessage();
			System.out.println(actual);
			if(AddGroupObject.GetGroupType().equalsIgnoreCase("Service level"))
			{
				AddGroupObject.setGroupType("Y");
			}
			else
			{
				AddGroupObject.setGroupType("N");
			}
			if(actual.contains(expected))
			{
				if(actual.contains("Group Added Successfully"))
				{
					SearchGroupPojo searchGroupCeritria=new SearchGroupPojo();
					searchGroupCeritria.setGroupCode(AddGroupObject.GetGroupCode());
					SearchGroupVerification search=new SearchGroupVerification();
					GroupPojo searchGroupDB=   search.addgroup(searchGroupCeritria );
					sa.assertTrue(searchGroupDB.GetGroupCode().equalsIgnoreCase(AddGroupObject.GetGroupCode()), "group code not matched ");
					sa.assertTrue(searchGroupDB.GetGroupName().equalsIgnoreCase(AddGroupObject.GetGroupName()), "group name not matched ");
					sa.assertTrue(searchGroupDB.GetDescription().equalsIgnoreCase(AddGroupObject.GetDescription()), "group description not matched ");
					sa.assertTrue(searchGroupDB.GetGroupType().equalsIgnoreCase(AddGroupObject.GetGroupType()), "group type not matched ");
					sa.assertTrue(searchGroupDB.GetCSP().equalsIgnoreCase(AddGroupObject.GetCSP()), "group csp not matched ");
					System.out.println(searchGroupDB.GetGroupRole()+".........."+(AddGroupObject.GetGroupRole()));
					sa.assertTrue(searchGroupDB.GetGroupRole().equalsIgnoreCase(AddGroupObject.GetGroupRole()), "group role not matched ");

					// Check that size of user added to group equals the size of users added in DB
					if(searchGroupDB.getUsersData().size()==AddGroupObject.getUsersData().size())
					{
						// check that same users added to group and DB
						for(int i=0 ;i<AddGroupObject.getUsersData().size();i++)
						{
							System.out.println(searchGroupDB.getUsersData().get(i)+"////////////////////////"+AddGroupObject.getUsersData().get(i));
							sa.assertTrue(searchGroupDB.getUsersData().contains(AddGroupObject.getUsersData().get(i)), "group user not matched ");
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
					System.out.println("error msg appear");
				}
			}
			else if (actual.equalsIgnoreCase("reset"))

			{
				sa.assertTrue(group.isreset(), "error in reset Functionality ");

			}

			else
			{
				sa.fail("Add Group Test Case with Id = "+AddGroupObject.GetGroupCode()+" failed ");
			}

			sa.assertAll();
		} catch (InterruptedException e) {
			log.error(e.getClass().getSimpleName());	

		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	

		} catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}
	}

	@DataProvider(name = "AddGrouptestDataProvider")
	public Object[][] provideTestData(Method method)
	{


		String methodFullName = method.getName();

		PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
		Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);

		String connectionProperties = prop.getProperty(methodFullName);

		ArrayList<ArrayList<Object>> resultArray = provideTestData(connectionProperties);
		//System.out.print(resultArray);
		Object[][] result = new Object[resultArray.size()][1];
		System.out.println(resultArray.size());
		for(int i=0; i<resultArray.size(); i++)
		{
			ArrayList<String> str=new ArrayList<>();
			GroupPojo addGroup=new GroupPojo();

			addGroup.setGroupCode(resultArray.get(i).get(0).toString());
			addGroup.setGroupName(resultArray.get(i).get(1).toString());
			addGroup.setDescription(resultArray.get(i).get(2).toString());
			addGroup.setGroupType(resultArray.get(i).get(3).toString());
			addGroup.setCSP(resultArray.get(i).get(4).toString());
			addGroup.setGroupRole(resultArray.get(i).get(5).toString());
			addGroup.setFlag(resultArray.get(i).get(7).toString());
			addGroup.setExpectedMessage(resultArray.get(i).get(8).toString());
			String[] splittedString =(resultArray.get(i).get(6).toString().split(","));
			for(int j=0; j<splittedString.length;j++)
			{
				str.add(splittedString[j]);
			}
			addGroup.setUsersData(str);
			result[i][0] = addGroup;

		}
		return result;

	}
}
