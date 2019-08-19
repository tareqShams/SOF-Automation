package fawry.sofAutomation.pages.admin;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.admin.UserPojo;

public class AddUserPage extends MainPage {
	WebDriver driver;

	public AddUserPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id='AddUser:textFirstName']")
	WebElement firstname;

	@FindBy(xpath="//*[@id='AddUser:textFamilyName']")
	WebElement lastname;

	@FindBy(xpath="//*[@id='AddUser:textUserName']")
	WebElement username ;

	@FindBy(xpath="//*[@id='AddUser:textEmail']")
	WebElement email;

	@FindBy(xpath="//*[@id='AddUser:textJobTitle']")
	WebElement jobtitle;

	@FindBy(xpath="//*[@id='AddUser:textGrade']")
	WebElement Grade;

	@FindBy(xpath="//*[@id='AddUser:textTelephoneExt']")
	WebElement TelephoneExt;

	@FindBy(xpath="//*[@id='AddUser:textMobileNum']")
	WebElement MobileNum;

	@FindBy(xpath="//*[@id='AddUser:departmentList']")
	WebElement department;


	@FindBy(id="AddUser:userTypeList")
	WebElement usertype;

	@FindBy(xpath="//*[@id='AddUser:textSearchToDate1']")
	WebElement Date;

	@FindBy(id="AddUser:forceSelect")
	WebElement enforce;

	@FindBy(id="AddUser:AllCspsList")
	WebElement cspList;

	@FindBy(xpath="//*[@id='AddUser:moveCSpToUser']/img")
	WebElement nextcspbtn;

	@FindBy(xpath="//*[@id='AddUser:userOneGroup']")
	WebElement UserGroup;

	@FindBy(id="AddUser:save")
	WebElement save;

	@FindBy(id="AddUser:resetData")
	WebElement reset;

	@FindBy(className="fieldError")
	List<WebElement> ErrorMsgsList;

	@FindBy(id="AddUser:menuStatus")
	WebElement status;

	@FindBy(id="AddUser:secretPassword")
	WebElement password;
	@FindBy(id="AddUser:secretConfirmPassword")
	WebElement confirmPassword;

	String timestamp = new SimpleDateFormat("Mdyyyyssmmm").format(Calendar.getInstance().getTime());



	public String  AddUser(UserPojo AddUserObject) throws InterruptedException
	{

		reset.click();
		//Select First Name
		firstname.sendKeys(AddUserObject.GetFirstName());

		//Select User Last Name
		lastname.sendKeys(AddUserObject.GetLastName());

		//Select User Name
		username.sendKeys(AddUserObject.GetUserName());

		AddUserObject.setUserName(username.getAttribute("value"));
		password.sendKeys(AddUserObject.getPassword());
		confirmPassword.sendKeys(AddUserObject.getConfirmPassword());
		//Select User email
		email.sendKeys(AddUserObject.getEmail());

		//Select User job title
		jobtitle.sendKeys(AddUserObject.GetJobTitle());


		//Select User Grade
		Grade.sendKeys(AddUserObject.GetJobTitle());

		//Select User telephone
		TelephoneExt.sendKeys(AddUserObject.GetTelephoneExt());

		//Select User mobile num
		MobileNum.sendKeys(AddUserObject.GetMobileNum());

		//Select User department
		department.sendKeys(AddUserObject.GetDepartment());

		//Select User date
		Date.sendKeys(AddUserObject.GetUserExpirationDate());

		usertype.sendKeys(AddUserObject.getUserType());

		AddUserObject.setStutas(status.getText());
		//check user enforce change password
		if(AddUserObject.Getenforce().equalsIgnoreCase("check"))
		{
			enforce.click();

		}

		// select from CPS List
		Select select = new Select( cspList);
		Actions builder = new Actions(driver);
		for(int j=0; j<AddUserObject.getCsp().size();j++)
		{
			for(int i=0 ; i<select.getOptions().size();i++)
			{

				if(select.getOptions().get(i).getText().equalsIgnoreCase(AddUserObject.getCsp().get(j)))
				{
					builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i)).keyUp(Keys.CONTROL);
					builder.build().perform(); 
					nextcspbtn.click();
					Thread.sleep(2000);
					break;
				}

			}

		}
		//Select User Group
		UserGroup.sendKeys(AddUserObject.GetUserGroup());
		if(AddUserObject.Getflag().toString().contains("add"))
		{
			//click save 
			save.click();
			Thread.sleep(5000);
			String Msgs = "";
			if (AddUserObject.Getflag().toString().contains("Error")) {
				Msgs = ErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<ErrorMsgsList.size();i++)
				{
					Msgs=Msgs+"/"+ErrorMsgsList.get(i).getText().toString();
				}
			}
			else{
				//correct msg
				WebElement succssefully=driver.findElement(By.className("alert"));

				if(succssefully.isDisplayed() )
				{
					Msgs=succssefully.getText().toString();
				}


			}
			return Msgs;
		}
		else if (AddUserObject.Getflag().toString().contains("reset"))
		{

			reset.click();
			return "reset";
		}

		return "fail";

	}

	public boolean isreset()
	{


		if(username.getAttribute("value").isEmpty()
				&&firstname.getAttribute("value").isEmpty()
				&&lastname.getAttribute("value").isEmpty()
				&&password.getAttribute("value").isEmpty()
				&&confirmPassword.getAttribute("value").isEmpty()
				&&email.getAttribute("value").isEmpty()
				&&jobtitle.getAttribute("value").isEmpty()
				&&Grade.getAttribute("value").isEmpty()
				&&TelephoneExt.getAttribute("value").isEmpty()
				&&MobileNum.getAttribute("value").isEmpty()
				&&Date.getAttribute("value").isEmpty()
				&&!enforce.isSelected())
		{
			return true;
		}

		return false;
	}
}

