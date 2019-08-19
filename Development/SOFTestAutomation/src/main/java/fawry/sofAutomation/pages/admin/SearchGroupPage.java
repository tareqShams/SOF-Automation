package fawry.sofAutomation.pages.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.admin.SearchGroupPojo;

public class SearchGroupPage extends MainPage{


	WebDriver driver;

	public SearchGroupPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 

	@FindBy(xpath="//*[@id='searchGroup:textGroupCode']")
	WebElement GroupCode;
	@FindBy(xpath="//*[@id='searchGroup:cspList']")
	WebElement CSP;
	@FindBy(xpath="//*[@id='searchGroup:menu1']")
	WebElement Status;
	@FindBy(xpath="//*[@id='searchGroup:groupType']")
	WebElement GroupType;
	@FindBy(xpath="//*[@id='searchGroup:button1']")
	WebElement Search;
	@FindBy(xpath="//*[@id='searchGroup:resetBtn']")
	WebElement Reset;

	@FindBy(id="searchGroup:exportBtn")
	WebElement downloadbtn;
	private static String downloadpath = System.getProperty("user.dir")+"\\downloads";
	String timestamp = new SimpleDateFormat("M_d_yyyy").format(Calendar.getInstance().getTime());
	
		public String  SearchGroupfun(SearchGroupPojo SearchGroupObject) 
	{

		CSP.sendKeys(SearchGroupObject.GetCSP());
		Status.sendKeys(SearchGroupObject.GetStatus());
		GroupType.sendKeys(SearchGroupObject.GetGroupType());
		GroupCode.sendKeys(SearchGroupObject.GetGroupCode());

		if(SearchGroupObject.GetFlag().equals("search"))
		{
			Search.click();
			return "search";
		}
		else if (SearchGroupObject.GetFlag().equals("reset"))
		{
			Reset.click();
			
			return "reset";
		}
		return "false";


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
		boolean isDownloaded= isFileDownloaded(downloadpath , "Search_Groups_Result"+timestamp+".csv");
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
	public boolean isreset()
	{
		
		if(GroupCode.getAttribute("value").isEmpty() )
		{
			return true;
		 
		}
		return false;
		
		
	}
}
