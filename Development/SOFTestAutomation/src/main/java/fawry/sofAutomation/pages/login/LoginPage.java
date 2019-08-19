package fawry.sofAutomation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.admin.LoginPojo;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}



	public String  adminLogin(LoginPojo loginObject)
	{

		System.out.println("Username " + loginObject.getUserName() + " password " + loginObject.getPassword());

		//Enter username
		driver.findElement(By.xpath("//*[@id='form1:usernameLabel']")).clear();
		driver.findElement(By.xpath("//*[@id='form1:usernameLabel']")).sendKeys(loginObject.getUserName());
		//Enter password
		driver.findElement(By.xpath("//*[@id='form1:passwordLabel']")).clear();
		driver.findElement(By.xpath("//*[@id='form1:passwordLabel']")).sendKeys(loginObject.getPassword());

		driver.findElement(By.xpath("//*[@id='form1:btn_login']")).click();	
		//String actual = driver.findElement(By.cssSelector("#home-container>h4")).getText();

		return "Success";
	}

	public void loginadd()
	{
		driver.findElement(By.xpath("//*[@id='form1:usernameLabel']")).clear();
		driver.findElement(By.xpath("//*[@id='form1:usernameLabel']")).sendKeys("superuser2");
		driver.findElement(By.xpath("//*[@id='form1:passwordLabel']")).clear();
		driver.findElement(By.xpath("//*[@id='form1:passwordLabel']")).sendKeys("1234");

		driver.findElement(By.xpath("//*[@id='form1:btn_login']")).click();	


		
	}
	
	
	public void successfulllogin() {

		driver.findElement(By.id("form1:usernameLabel")).clear();
		driver.findElement(By.id("form1:usernameLabel")).sendKeys(Constants.LOGIN_USERNAME);
		driver.findElement(By.id("form1:passwordLabel")).clear();
		driver.findElement(By.id("form1:passwordLabel")).sendKeys(Constants.LOGIN_PASSWORD);
		driver.findElement(By.id("form1:btn_login")).click();
	}

	public String  login(String username , String password)
	{


		//Enter username
		driver.findElement(By.xpath("//*[@id='form1:usernameLabel']")).clear();
		driver.findElement(By.xpath("//*[@id='form1:usernameLabel']")).sendKeys(username);
		//Enter password
		driver.findElement(By.xpath("//*[@id='form1:passwordLabel']")).clear();
		driver.findElement(By.xpath("//*[@id='form1:passwordLabel']")).sendKeys(password);

		driver.findElement(By.xpath("//*[@id='form1:btn_login']")).click();	
		
		return "";
	}


}
