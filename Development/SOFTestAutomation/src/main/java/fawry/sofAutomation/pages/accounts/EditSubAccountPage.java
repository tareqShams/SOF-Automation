package fawry.sofAutomation.pages.accounts;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class EditSubAccountPage {
	WebDriver driver;
	public EditSubAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchPOS:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchPOS:tableEx1:0:textSearchResults1")
	public WebElement accountcodelink;

	@FindBy(id="searchPOS:textSearchPOSID1")
	public WebElement accountidtxt;

	@FindBy(xpath="//*[@id=\"subAccounts:subAccountsResultsTable\"]/tbody")
	WebElement table;

	@FindBy(linkText="Sub Accounts")
	public WebElement subacclnk;


	@FindBy(id="editCreditAccount:saveButton")
	public WebElement editcreditaccsavebtn;

	@FindBy(id="EditSubAccount:deleteSubAccount")
	public WebElement editsubaccdeletebtn;

	@FindBy(id="editCreditAccount:deleteButton")
	public WebElement editcreditaccdeletebtn;


	@FindBy(id="editCreditAccount:backButton")
	public WebElement editcreditaccbackbtn;

	@FindBy(id="editCreditAccount:profileStatus")
	public WebElement editcreditaccaccountstatuslist;
	//---------------
	@FindBy(id="EditSubAccount:saveRole")
	public WebElement editsubaccsavebtn;

	@FindBy(id="EditSubAccount:backToSearch")
	public WebElement editsubaccbackbtn;

	@FindBy(id="EditSubAccount:statusList")
	public WebElement editsubaccaccountstatuslist;

	@FindBy(id="correctMessage")
	public WebElement editcreditaccaccountsuccessmsg;

	@FindBy(id="EditSubAccount:CorrectMessage")
	public WebElement editsubaccaccountsuccessmsg;

	@FindBy(id="EditSubAccount:SubAcctCode")
	WebElement editedsubaccountcode;

	@FindBy(id="addSubAccount:POSCode")
	WebElement editedcreditaccountcode;



	public String beforeid = "subAccounts:subAccountsResultsTable:";
	public String afterid = ":textSearchResults1";
	int tablesize;
	public String successmessage;
	public String subaccountcodeforDBcheck;



	public String  editSubAccount(AccountPojo editsubaccountobj)
	{
		navigatetoSubAccount(editsubaccountobj);
		tablesize();
		//Click On The latest Added SubAccount
		WebElement latestaddedsubaccount = driver.findElement(By.id(beforeid+tablesize+afterid));
		latestaddedsubaccount.click();
		System.out.println(editsubaccountobj.getAction());
		successmessage = "";

		//
			if (driver.findElements(By.id("EditSubAccount:alias")).size() != 0) {

				if(editsubaccountobj.getAction().contains("Back")) 
				{
					editsubaccbackbtn.click();
					successmessage = driver.getCurrentUrl();
				}
				if(editsubaccountobj.getAction().contains("Save")) 
				{
					new Select(editsubaccaccountstatuslist).selectByVisibleText(editsubaccountobj.getAccountStatus());
					editsubaccsavebtn.click();
					if (driver.findElements(By.id("EditSubAccount:CorrectMessage")).size() != 0){
					successmessage = editsubaccaccountsuccessmsg.getText();
					}
					editsubaccountobj.setAccountCode(editedsubaccountcode.getText());
				}		
				if(editsubaccountobj.getAction().contains("Delete") && driver.findElements(By.id("EditSubAccount:deleteSubAccount")).size() != 0) 
				{
					editsubaccdeletebtn.click();
					Alert alert = driver.switchTo().alert();
					System.out.println(alert.getText());
					alert.accept();
					if (driver.findElements(By.id("EditSubAccount:CorrectMessage")).size() != 0){
					successmessage = editsubaccaccountsuccessmsg.getText();
					}
				}
			}
			else if(driver.findElements(By.id("editCreditAccount:alias")).size() != 0) 
			{

				if(editsubaccountobj.getAction().contains("Back")) 
				{
					editcreditaccbackbtn.click();
					successmessage = driver.getCurrentUrl();
				}
				if(editsubaccountobj.getAction().contains("Save")) 
				{
					new Select(editcreditaccaccountstatuslist).selectByVisibleText(editsubaccountobj.getAccountStatus());
					editcreditaccsavebtn.click();
					if (driver.findElements(By.id("correctMessage")).size() != 0)
					{
					successmessage = editcreditaccaccountsuccessmsg.getText();
					}
					//subaccountcodeforDBcheck = editedcreditaccountcode.getText();
				}		
				if(editsubaccountobj.getAction().contains("Delete") && driver.findElements(By.id("editCreditAccount:deleteButton")).size() != 0) 
				{
					editcreditaccdeletebtn.click();
					Alert alert = driver.switchTo().alert();
					System.out.println(alert.getText());
					alert.accept();
				
					if (driver.findElements(By.id("correctMessage")).size() != 0)
					{
					successmessage = editcreditaccaccountsuccessmsg.getText();
					}
				}
			}
		return successmessage;

	}


	public void  navigatetoSubAccount(AccountPojo editsubaccountobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(editsubaccountobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();

		//Navigate To SubAccount Menu
		subacclnk.click();
	}

	public void tablesize() 
	{
		List<WebElement> row_table = table.findElements(By.tagName("tr"));
		System.out.println(row_table.size());
		tablesize = row_table.size()-1;

	}
	/*
	 *  //change value of a cell
    public String isnullvalue(String value)
    {
           if(value.equalsIgnoreCase("*"))
           {
                  value="";
           }
           return value;
    }
	 */
}

