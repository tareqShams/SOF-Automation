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
import fawry.sofAutomation.pages.admin.ResetPasswordPage;
import fawry.sofAutomation.pages.login.LoginPage;
import fawry.sofAutomation.pojos.admin.ResetPasswordPojo;
import fawry.sofAutomation.testsActions.basic.BasicTest;
import fawry.sofAutomation.utils.PropertiesFilesHandler;

public class ResetPasswordTest  extends BasicTest{
	

	@BeforeClass
	public void login(){

		LoginPage login=new LoginPage(driver);
		login.successfulllogin();
	}
	@Test(description="Validate ResetPassword Functionality",priority=2, dataProvider="ResetPasswordtestDataProvider")
	public static void resetPassword(ResetPasswordPojo resetObject) 
	{
		test = extent.createTest("Validate ResetPassword Functionality");

		log = Logger.getLogger(ResetPasswordTest.class.getName());

		try {
			LoginPage login=new LoginPage(driver);

			SoftAssert sa = new SoftAssert();
			ResetPasswordPage page = new ResetPasswordPage(driver);
			page.navigateToTab("Admin","Reset User Password",driver);
			String actual =page.resetPassword(resetObject);
			String expected=resetObject.getExpectedMessage();
			if(actual.equalsIgnoreCase("reset"))
			{
				sa.assertTrue(page.isreset(), "reset failed");

			}
			else if(actual.contains(expected))
			{
				if(actual.contains("Password Reset Successfully"))
				{
				page.navigateToTab("User Profile","Logout",driver);
				sa.assertTrue(driver.getCurrentUrl().contains("Login"), "LogOut Failed");
			    login.login(resetObject.getUsername(), resetObject.getOldpassword());
				sa.assertTrue(driver.getCurrentUrl().contains("Login"), "password reset fail and login with old password ");
				login.login(resetObject.getUsername(), resetObject.getNewpassword());
				sa.assertTrue(driver.getCurrentUrl().contains("ChangeOldPassword"), "Can Not Login with NewPassword");
				page.navigateToTab("User Profile","Logout",driver);
				login.successfulllogin();
				}
				else
				{
					sa.fail("Password Reset Failed and 'Successful' Massage did not appear ");
				}

			}
			sa.assertAll();
		} catch (Exception e) {
			log.error(e.getClass().getSimpleName());	
		}
	}

	@DataProvider(name = "ResetPasswordtestDataProvider")
	public Object[][] provideTestData(Method method)
	{

		String methodFullName = method.getName();

		PropertiesFilesHandler propLoader = new PropertiesFilesHandler();
		Properties prop = propLoader.loadPropertiesFile(Constants.TEST_DATA_CONFIG_FILE_NAME);

		String connectionProperties = prop.getProperty(methodFullName);

		ArrayList<ArrayList<Object>> resultArray = provideTestData(connectionProperties);
		Object[][] result = new Object[resultArray.size()][1];
		//System.out.println(resultArray.size());

		for(int i=0; i<resultArray.size(); i++)
		{
			ResetPasswordPojo resetPassword = new ResetPasswordPojo();

			resetPassword.setUsername(resultArray.get(i).get(0).toString());
			resetPassword.setOldpassword(resultArray.get(i).get(1).toString());
			resetPassword.setNewpassword(resultArray.get(i).get(2).toString());
			resetPassword.setEnforce(resultArray.get(i).get(3).toString());
			resetPassword.setFlag(resultArray.get(i).get(4).toString());
			resetPassword.setExpectedMessage(resultArray.get(i).get(5).toString());

			result[i][0] = resetPassword;


		}


		return result;
	}
}
