package fawry.sofAutomation.pages.admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.admin.UserPojo;

public class EditUserPage extends MainPage {


	WebDriver driver;

	public EditUserPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	@FindBy(xpath="//*[@id='searchUser:textUserName']")
	WebElement userNameSearch;

	@FindBy(xpath="//*[@id='searchUser:button1']")
	WebElement Search;


	@FindBy(id="EditUser:statusList")
	WebElement userStatus;
	@FindBy(id="EditUser:textFirstName")
	WebElement firstName;
	@FindBy(id="EditUser:textFamilyName")
	WebElement lastName;
	@FindBy(id="EditUser:textUserName")
	WebElement username;
	@FindBy(id="EditUser:textEmail")
	WebElement email;
	@FindBy(id="EditUser:textJobTitle")
	WebElement jobTitle;
	@FindBy(id="EditUser:textGrade")
	WebElement grade;
	@FindBy(id="EditUser:textTelephoneExt")
	WebElement telephoneExt;
	@FindBy(id="EditUser:textMobileNum")
	WebElement mobileNum;
	@FindBy(id="EditUser:departmentList")
	WebElement department;
	@FindBy(id="EditUser:userExpiryDate")
	WebElement userExpireDate;
	@FindBy(id="EditUser:userTypeList")
	WebElement userType;
	@FindBy(id="EditUser:blockUser")
	WebElement block;
	@FindBy(id="EditUser:moveCSpToUser")
	WebElement nextcspbtn;
	@FindBy(id="EditUser:OneUserGroup")
	WebElement userGroup;
	@FindBy(id="EditUser:saveUser")
	WebElement save;
	@FindBy(id="EditUser:deleteUser")
	WebElement delete;
	@FindBy(id="EditUser:resetData")
	WebElement reset;
	@FindBy(id="EditUser:backToSearch")
	WebElement back;
	@FindBy(xpath="/html/body/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody")
	WebElement tableBody;
	@FindBy(id="EditUser:userCspList")
	WebElement usercsp;

	@FindBy(className="alert")
	WebElement correctmsg;

	
	public void search(UserPojo EditUserObject) throws InterruptedException
	{
		
		userNameSearch.sendKeys(EditUserObject.GetUserName());
		Search.click();
		driver.findElement(By.linkText(EditUserObject.GetUserName())).click();
		Thread.sleep(2000);
	}
	public String  EditUserfun(UserPojo EditUserObject) throws InterruptedException 
	{

		if(!EditUserObject.getBlock().isEmpty())
		{
			block.click();
			search(EditUserObject);
		}

		if(!EditUserObject.getStatus().isEmpty())
		{
			userStatus.clear();
			userStatus.sendKeys(EditUserObject.getStatus());
		}

		if(!EditUserObject.GetFirstName().isEmpty())
		{
			firstName.clear();
			firstName.sendKeys(EditUserObject.GetFirstName());
		}

		if(!EditUserObject.GetLastName().isEmpty())
		{
			lastName.clear();
			lastName.sendKeys(EditUserObject.GetLastName());
		}

		if(!EditUserObject.getEmail().isEmpty())
		{
			email.clear();
			email.sendKeys(EditUserObject.getEmail());
		}

		if(!EditUserObject.GetJobTitle().isEmpty())
		{
			jobTitle.clear();
			jobTitle.sendKeys(EditUserObject.GetJobTitle());

		}

		if(!EditUserObject.GetGrade().isEmpty())
		{
			grade.clear();
			grade.sendKeys(EditUserObject.GetGrade());

		}

		if(!EditUserObject.GetTelephoneExt().isEmpty())
		{
			telephoneExt.clear();
			telephoneExt.sendKeys(EditUserObject.GetTelephoneExt());

		}

		if(!EditUserObject.GetMobileNum().isEmpty())
		{
			mobileNum.clear();
			mobileNum.sendKeys(EditUserObject.GetMobileNum());

		}

		if(!EditUserObject.GetDepartment().isEmpty())
		{
			department.clear();
			department.sendKeys(EditUserObject.GetDepartment());

		} 


		if(!EditUserObject.GetUserExpirationDate().isEmpty())
		{
			userExpireDate.clear();
			userExpireDate.sendKeys(EditUserObject.GetUserExpirationDate());

		}


		if(!EditUserObject.getUserType().isEmpty())
		{
			userType.clear();
			userType.sendKeys(EditUserObject.getUserType());

		}
		getUsersCspList(EditUserObject);

		if(EditUserObject.Getflag().contains("save"))
		{
			save.click();
			while(correctmsg.isDisplayed())
			{
				return correctmsg.getText();
			}
		}


		else if (EditUserObject.Getflag().contains("reset"))
		{
			reset.click();
			save.click();
			return "reset";
		}
		else if (EditUserObject.Getflag().contains("delete"))
		{
			delete.click();
			Alert alert=driver.switchTo().alert();
			alert.accept();
			while(correctmsg.isDisplayed())
			{
				return correctmsg.getText();
			}

		}
		else if (EditUserObject.Getflag().contains("back"))
		{
			back.click();
			return "back";
		}
		return "fail";
	}
	
	public void getUsersCspList(UserPojo userObject)
	{
		Select select=new Select(usercsp);
		if(select.getOptions().size()!=0)
		{
			List<WebElement> UsersCsp=select.getOptions();
		ArrayList<String> arr=new ArrayList<>();
		for(int i=0 ; i<UsersCsp.size();i++)
		{
			arr.add(UsersCsp.get(i).getText().toString());
		}
		userObject.setCsp(arr);
		}
		
	} 

}
