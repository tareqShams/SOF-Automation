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
import fawry.sofAutomation.pages.admin.AddUserPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.SearchUserPojo;
import fawry.sofAutomation.pojos.admin.UserPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class AddUserTest  extends BasicTest{
	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
		AddUserPage user =new AddUserPage(driver);
		user.navigateToTab("Admin","Add User",driver);
	}

	@Test(description="Validate AddUser Functionality",priority=1, dataProvider="AddUsertestDataProvider")
	public static void AddUser(UserPojo AddUserObject) 
	{
		test = extent.createTest("Validate AddUser Functionality");

		//Initialize  logger
		log = Logger.getLogger(AddUserTest.class.getName());
		//Initialize  Assert
		SoftAssert sa = new SoftAssert();
		//Initialize Object From User Page
		AddUserPage user =new AddUserPage(driver);
		//Get Expected Massage From Excel
		String expected =AddUserObject.getExpectedMessage();
		String actual;

		try {
			// Add User and gET Actual Result
			actual = user.AddUser(AddUserObject);
			// check if Reset Button Clicked
			if(actual.equalsIgnoreCase("reset"))
			{
				// assert that when Click  Reset ALL Field  
				sa.assertTrue(user.isreset(),"Error In Reset Functionality In Add-User-Page");
			}

			if(actual.equalsIgnoreCase("User Added Successfully"))
			{
				SearchUserVerifications searchUser = new SearchUserVerifications();
				SearchUserPojo searchUserCriteria = new SearchUserPojo();
				searchUserCriteria.setUserName(AddUserObject.GetUserName());
				UserPojo accountInDb = searchUser.SearchUserVerificationsForAdd(searchUserCriteria);		
				sa.assertTrue(AddUserObject.GetUserName().contains(accountInDb.GetUserName()), "Username in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetFirstName().contains(accountInDb.GetFirstName()), "firstname in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetLastName().contains(accountInDb.GetLastName()), "lastname in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.getEmail().contains(accountInDb.getEmail()), "email in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetJobTitle().contains(accountInDb.GetJobTitle()), "job in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetGrade().equalsIgnoreCase(accountInDb.GetGrade()), "grade in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetTelephoneExt().contains(accountInDb.GetTelephoneExt()), "telephone in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetMobileNum().contains(accountInDb.GetMobileNum()), "mobile in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetDepartment().equalsIgnoreCase(accountInDb.GetDepartment()), "department in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetUserExpirationDate().contains(accountInDb.GetUserExpirationDate()), "date in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.getUserType().contains(accountInDb.getUserType()), "User type in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.GetUserGroup().contains(accountInDb.GetUserGroup()), "group in Excel And DB Mismatch");
				sa.assertTrue(AddUserObject.getStutas().contains(accountInDb.getStutas()), "status in Excel And DB Mismatch");
				sa.assertTrue(accountInDb.Getenforce().equalsIgnoreCase("Y"), "Enforce in Excel And DB Mismatch");

				// Check that size of user added to group equals the size of users added in DB
				if(accountInDb.getCsp().size()==AddUserObject.getCsp().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<AddUserObject.getCsp().size();i++)
					{
						//System.out.println(searchGroupDB.get(0).getUsersData().get(i)+"////////////////////////"+AddGroupObject.getUsersData().get(i));
						sa.assertTrue(accountInDb.getCsp().contains(AddUserObject.getCsp().get(i)), "csp in Excel And DB Mismatch");
					}
				}
				// size of user added to group does not equal the size of users added in DB
				else
				{
					sa.fail(" size of user added to group does not equal the size of users added in DB");

				}


			}
			else if(AddUserObject.Getflag().contains("Error"))
			{
				sa.assertTrue(expected.contains(actual),"error msg faild");

			}
			sa.assertAll();
		} catch (InterruptedException e) {
			log.error(e.getClass().getSimpleName());	


		} catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}

	}



	@DataProvider(name = "AddUsertestDataProvider")
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
			UserPojo adduser=new UserPojo();

			adduser.setFirstName(resultArray.get(i).get(0).toString());
			adduser.setLastName(resultArray.get(i).get(1).toString());
			adduser.setUserName(resultArray.get(i).get(2).toString());
			adduser.setEmail(resultArray.get(i).get(3).toString());
			adduser.setJobTitle(resultArray.get(i).get(4).toString());
			adduser.setGrade(resultArray.get(i).get(5).toString());
			adduser.setTelephoneExt(resultArray.get(i).get(6).toString());
			adduser.setMobileNum(resultArray.get(i).get(7).toString());
			adduser.setDepartment(resultArray.get(i).get(8).toString());
			adduser.setUserExpirationDate(resultArray.get(i).get(9).toString());
			adduser.setUserType(resultArray.get(i).get(10).toString());
			adduser.setUserGroup(resultArray.get(i).get(12).toString());
			adduser.SetEnforce(resultArray.get(i).get(14).toString());
			adduser.setExpectedMessage(resultArray.get(i).get(13).toString());
			adduser.Setflag(resultArray.get(i).get(15).toString());
			adduser.setPassword(resultArray.get(i).get(16).toString());
			adduser.setConfirmPassword(resultArray.get(i).get(17).toString());
			String[] splittedString =(resultArray.get(i).get(11).toString().split(","));
			for(int j=0; j<splittedString.length;j++)
			{
				str.add(splittedString[j]);
			}
			adduser.setCsp(str);
			result[i][0] = adduser;

		}
		return result;

	}
}
