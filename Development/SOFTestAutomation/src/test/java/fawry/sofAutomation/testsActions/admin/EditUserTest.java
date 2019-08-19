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
import fawry.sofAutomation.pages.admin.EditUserPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.SearchUserPojo;
import fawry.sofAutomation.pojos.admin.UserPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class EditUserTest extends BasicTest{

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}

	@Test(description="Validate EditUser Functionality",priority=4, dataProvider="EditUsertestDataProvider")
	public static void EditUser(UserPojo EditUserObject) 
	{
		test = extent.createTest("Validate EditUser Functionality");

		log = Logger.getLogger(EditUserTest.class.getName());

		SoftAssert ass=new SoftAssert();
		EditUserPage user =new EditUserPage(driver);  
		user.navigateToTab("Admin", "Search User",driver);
		try {
			user.search(EditUserObject);
		
		SearchUserPojo searchCriteria=new SearchUserPojo();
		SearchUserVerifications search=new SearchUserVerifications();
		searchCriteria.setUserName(EditUserObject.GetUserName());
		UserPojo  accountInDb;
		String actual = null;
		if(EditUserObject.Getflag().contains("reset"))
		{

			accountInDb = search.SearchUserVerificationsForAdd(searchCriteria);	
			actual	=user.EditUserfun(EditUserObject);

		}
		else {
			actual	=user.EditUserfun(EditUserObject);
			accountInDb = search.SearchUserVerificationsForAdd(searchCriteria);
		}
		if(actual.equalsIgnoreCase(EditUserObject.getExpectedMessage()))	
		{
			if(actual.contains("User deleted Successfully"))
			{
				ass.assertTrue(accountInDb.getStutas().equals("DELETED"), "delete functionality is failed");
				SearchUserVerifications update=new SearchUserVerifications();
				update.updateuser(EditUserObject.GetUserName());

			}
			else if (actual.contains("User updated successfully"))
			{
				
				ass.assertTrue(((EditUserObject.GetUserName().isEmpty()||EditUserObject.GetUserName()==null)?(true):(EditUserObject.GetUserName().contains(accountInDb.GetUserName()))), "Username in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetFirstName().isEmpty()||EditUserObject.GetFirstName()==null)?(true):(EditUserObject.GetFirstName().contains(accountInDb.GetFirstName()))), "firstname in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetLastName().isEmpty()||EditUserObject.GetLastName()==null)?(true):(EditUserObject.GetLastName().contains(accountInDb.GetLastName()))), "lastname in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.getEmail().isEmpty()||EditUserObject.getEmail()==null)?(true):(EditUserObject.getEmail().contains(accountInDb.getEmail()))), "email in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetJobTitle().isEmpty()||EditUserObject.GetJobTitle()==null)?(true):(EditUserObject.GetJobTitle().contains(accountInDb.GetJobTitle()))), "job in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetGrade().isEmpty()||EditUserObject.GetGrade()==null)?(true):(EditUserObject.GetGrade().contains(accountInDb.GetGrade()))), "grade in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetTelephoneExt().isEmpty()||EditUserObject.GetTelephoneExt()==null)?(true):(EditUserObject.GetTelephoneExt().contains(accountInDb.GetTelephoneExt()))), "telephone in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetMobileNum().isEmpty()||EditUserObject.GetMobileNum()==null)?(true):(EditUserObject.GetMobileNum().contains(accountInDb.GetMobileNum()))), "mobile in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetDepartment().isEmpty()||EditUserObject.GetDepartment()==null)?(true):(EditUserObject.GetDepartment().contains(accountInDb.GetDepartment()))), "department in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.getUserType().isEmpty()||EditUserObject.getUserType()==null)?(true):(EditUserObject.getUserType().contains(accountInDb.getUserType()))), "user type in Excel And DB Mismatch");
				//ass.assertTrue(((EditUserObject.getCsps().isEmpty()||EditUserObject.getUsername()==null)?(true):(EditUserObject.getCsps().contains(accountInDb.GetCSPData()))), "csp in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.GetUserGroup().isEmpty()||EditUserObject.GetUserGroup()==null)?(true):(EditUserObject.GetUserGroup().contains(accountInDb.GetUserGroup()))), "user group in Excel And DB Mismatch");
				ass.assertTrue(((EditUserObject.getStatus().isEmpty()||EditUserObject.getStatus()==null)?(true):(EditUserObject.getStatus().contains(accountInDb.getStutas()))), "status in Excel And DB Mismatch");
				// Check that size of user added to group equals the size of users added in DB
				System.out.println(accountInDb.getCsp().size()+"================"+EditUserObject.getCsp().size());
				if(accountInDb.getCsp().size()==EditUserObject.getCsp().size())
				{
					// check that same users added to group and DB
					for(int i=0 ;i<EditUserObject.getCsp().size();i++)
					{
						ass.assertTrue(accountInDb.getCsp().contains(EditUserObject.getCsp().get(i)), "group user not matched ");
					}
				}
				// size of user added to group does not equal the size of users added in DB
				else
				{
					ass.fail(" size of user added to group does not equal the size of users added in DB");

				}		
			}
		}
		else if (actual.equalsIgnoreCase("reset"))
		{

			UserPojo  accountsInDbAfterReset = search.SearchUserVerificationsForAdd(searchCriteria);	
			ass.assertTrue(accountsInDbAfterReset.GetUserName().contains(accountInDb.GetUserName()), "Username in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetFirstName().contains(accountInDb.GetFirstName()), "firstname in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetLastName().contains(accountInDb.GetLastName()), "lastname in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.getEmail().contains(accountInDb.getEmail()), "email in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetJobTitle().contains(accountInDb.GetJobTitle()), "job in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetGrade().contains(accountInDb.GetGrade()), "grade in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetTelephoneExt().contains(accountInDb.GetTelephoneExt()), "telephone in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetMobileNum().contains(accountInDb.GetMobileNum()), "mobile in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetDepartment().contains(accountInDb.GetDepartment()), "department in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.getUserType().contains(accountInDb.getUserType()), "user type in Excel And DB Mismatch");
			//ass.assertTrue(accountsInDbAfterReset.GetCSPData().contains(accountsInDb.get(0).GetCSPData()))), "csp in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.GetUserGroup().contains(accountInDb.GetUserGroup()), "user group in Excel And DB Mismatch");
			ass.assertTrue(accountsInDbAfterReset.getStutas().contains(accountInDb.getStutas()), "status in Excel And DB Mismatch");
			// Check that size of user added to group equals the size of users added in DB
			if(accountsInDbAfterReset.getCsp().size()==EditUserObject.getCsp().size())
			{
				// check that same users added to group and DB
				for(int i=0 ;i<EditUserObject.getCsp().size();i++)
				{
					ass.assertTrue(accountsInDbAfterReset.getCsp().contains(EditUserObject.getCsp().get(i)), "group user not matched ");
				}
			}
			// size of user added to group does not equal the size of users added in DB
			else
			{
				ass.fail(" size of user added to group does not equal the size of users added in DB");

			}		

		}
		else if (actual.equalsIgnoreCase("back"))
		{
			ass.assertTrue(driver.getCurrentUrl().contains("SearchUser"), "back functionality faild");
		}
		else
		{
			ass.fail("Edit User Test Case OF Failed");
		}


		ass.assertAll();
		} catch (InterruptedException e) {
			
			log.error(e.getClass().getSimpleName());	

		} catch (Exception e) {
		
			log.error(e.getClass().getSimpleName());	
		}
	}


	@DataProvider(name = "EditUsertestDataProvider")
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
			UserPojo editUser=new UserPojo();
			ArrayList<String> str=new ArrayList<>();

			
			editUser.setStatus(resultArray.get(i).get(0).toString());
			editUser.setFirstName(resultArray.get(i).get(1).toString());
			editUser.setLastName(resultArray.get(i).get(2).toString());
			editUser.setUserName(resultArray.get(i).get(3).toString());
			editUser.setEmail(resultArray.get(i).get(4).toString());
			editUser.setJobTitle(resultArray.get(i).get(5).toString());
			editUser.setGrade(resultArray.get(i).get(6).toString());
			editUser.setTelephoneExt(resultArray.get(i).get(7).toString());
			editUser.setMobileNum(resultArray.get(i).get(8).toString());
			editUser.setDepartment(resultArray.get(i).get(9).toString());
			editUser.setUserExpirationDate(resultArray.get(i).get(10).toString());
			editUser.setUserType(resultArray.get(i).get(11).toString());
			editUser.setBlock(resultArray.get(i).get(12).toString());
			editUser.setUserGroup(resultArray.get(i).get(14).toString());
			editUser.Setflag(resultArray.get(i).get(15).toString());
			editUser.setExpectedMessage(resultArray.get(i).get(16).toString());
			String[] splittedString =(resultArray.get(i).get(13).toString().split(","));
			for(int j=0; j<splittedString.length;j++)
			{
				str.add(splittedString[j]);
			}
			editUser.setCsp(str);
			result[i][0] = editUser;

		}
		return result;

	}


}
