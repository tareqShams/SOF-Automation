package fawry.sofAutomation.pages.admin;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.admin.SearchUserPojo;


public class SearchUserPage extends MainPage{
	WebDriver driver;

	public SearchUserPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@id='searchUser:textUserName']")
	WebElement UserName;

	@FindBy(xpath="//*[@id='searchUser:textFirstName']")
	WebElement FirstName;

	@FindBy(xpath="//*[@id='searchUser:textLastName']")
	WebElement LastName;

	@FindBy(xpath="//*[@id='searchUser:textEmail']")
	WebElement Email;

	@FindBy(xpath="//*[@id='searchUser:textTitle']")
	WebElement JobTitle;

	@FindBy(xpath="//*[@id='searchUser:textTelephoneExt']")
	WebElement TelephoneExt;

	@FindBy(xpath="//*[@id='searchUser:cspList']")
	WebElement CSP;

	@FindBy(id="searchUser:departmentList")
	WebElement Department;

	@FindBy(xpath="//*[@id='searchUser:groupList']")
	WebElement Groups;

	@FindBy(xpath="//*[@id='searchUser:statusList']")
	WebElement Status;

	@FindBy(xpath="//*[@id='searchUser:textGrade']")
	WebElement Grade;

	@FindBy(xpath="//*[@id='searchUser:textMobileNum']")
	WebElement MobileNumber;

	@FindBy(id="searchUser:exportBtn")
	WebElement downloadbtn;

	@FindBy(xpath="//*[@id='searchUser:button1']")
	WebElement Search;

	@FindBy(xpath="//*[@id='searchUser:resetBtn']")
	WebElement Reset;

	@FindBy(xpath="//*[@id='EditUser:userCspList']")
	WebElement userCsp;
	private static String downloadpath = System.getProperty("user.dir")+"\\downloads";
	String timestamp = new SimpleDateFormat("M_d_yyyy").format(Calendar.getInstance().getTime());
	public String  SearchUserfun(SearchUserPojo SearchUserObject) 
	{

		//Hover On Admin Then Search User
		
		FirstName.sendKeys(SearchUserObject.getFirstName());

		LastName.sendKeys(SearchUserObject.getLastName());

		Email.sendKeys(SearchUserObject.getEmail());

		JobTitle.sendKeys(SearchUserObject.getJobTitle());

		TelephoneExt.sendKeys(SearchUserObject.getTelephoneExt());

		CSP.sendKeys(SearchUserObject.getCsp());

		Department.sendKeys(SearchUserObject.getDepartment());

		Groups.sendKeys(SearchUserObject.getGroups());

		Status.sendKeys(SearchUserObject.getStatus());

		Grade.sendKeys(SearchUserObject.getGrade());

		MobileNumber.sendKeys(SearchUserObject.getMobileNumber());

		UserName.sendKeys(SearchUserObject.getUserName());


		if(SearchUserObject.getFlag().equalsIgnoreCase("search"))
		{
			Search.click();
			return "search";
		}
		else if (SearchUserObject.getFlag().equalsIgnoreCase("reset")) {
			Reset.click();
			return "reset";

		}
		return null;




	}

	public boolean testDownloading() throws InterruptedException  {

		File directory = new File(downloadpath);
		File[] files = directory.listFiles();
		for (File file : files) 
		{
			if (!file.delete()) 
			{
				System.out.println("Faild to delete file" + file);
			}
		}
		downloadbtn.click();
		Thread.sleep(5000);
		boolean isDownloaded= isFileDownloaded(downloadpath , "Search_Users_Result"+timestamp+".csv");
		return	isDownloaded;
	}
	public boolean isFileDownloaded(String downloadpath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadpath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag=true;
		}
		return flag;
	}

	public boolean reset()
	{
				if (UserName.getAttribute("value").isEmpty()&& FirstName.getAttribute("value").isEmpty() && LastName.getAttribute("value").isEmpty()
				&&Email.getAttribute("value").isEmpty()&& JobTitle.getAttribute("value").isEmpty() && Grade.getAttribute("value").isEmpty() 
				&& TelephoneExt.getAttribute("value").isEmpty() && MobileNumber.getAttribute("value").isEmpty() )
		{
			return true;
		}
		return false;
	}

}
