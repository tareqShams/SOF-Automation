package fawry.sofAutomation.pages.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.admin.RolePojo;

public class SearchRolePage {

	WebDriver driver;

	public SearchRolePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 

	@FindBy(id="searchRole:exportBtn")
	WebElement downloadbtn;
	
	@FindBy(id="searchRole:textRoleCode")
	WebElement code;
	@FindBy(id="searchRole:cspList")
	WebElement csp;
	@FindBy(id="searchRole:menu1")
	WebElement status;
	@FindBy(id="searchRole:roleType")
	WebElement type;
	@FindBy(id="searchRole:SearchBts")
	WebElement search;
	@FindBy(id="searchRole:resetBtn")
	WebElement reset;
	
	
	private static String downloadpath = System.getProperty("user.dir")+"\\downloads";
	String timestamp = new SimpleDateFormat("M_d_yyyy").format(Calendar.getInstance().getTime());
	
	public void navigateToSearchRoleTab()
	{
		WebElement element = driver.findElement(By.linkText("Admin"));

		Actions action = new Actions(driver);

		action.moveToElement(element).perform();

		WebElement subElement = driver.findElement(By.linkText("Search Role"));

		action.moveToElement(subElement);

		action.click();

		action.perform();

	}
	
	public String  SearchRolefun(RolePojo SearchRoleObject) 
	
	{
		csp.sendKeys(SearchRoleObject.getCsp());
		type.sendKeys(SearchRoleObject.getRoleType());
		status.sendKeys(SearchRoleObject.getStatus());
		code.sendKeys(SearchRoleObject.getRoleCode());
		if(SearchRoleObject.getFlag().contains("search"))
		{
			search.click();
			return "search";
		}
		else if (SearchRoleObject.getFlag().contains("reset"))
		{
			reset.click();
			return "reset";
		}
		return "fail";

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
		System.out.println( "Search_Users_Result"+timestamp+".csv");
		boolean isDownloaded= isFileDownloaded(downloadpath , "Search_Roles_Result"+timestamp+".csv");
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
System.out.println(	csp.getAttribute("value").toString());
			if (code.getAttribute("value").isEmpty()
					&& csp.getAttribute("value").toString().equalsIgnoreCase("all")
					&& status.getAttribute("value").toString().equalsIgnoreCase("all")
					&&type.getAttribute("value").toString().equalsIgnoreCase("all"))
	{
		return true;
	}
	return false;
}
}