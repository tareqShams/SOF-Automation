package fawry.sofAutomation.pages.accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;;

public class SearchCreditCardAccountPage {
	WebDriver driver;

	public SearchCreditCardAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchCreditAccounts:BankTerminalNo")
	public WebElement bankterminalnotxt;

	@FindBy(id="searchCreditAccounts:POSCode")
	public WebElement accountcodetxt;

	@FindBy(id="searchCreditAccounts:accountTypeList")
	public WebElement accounttypelist;

	@FindBy(id="searchCreditAccounts:profileStatus")
	public WebElement statuslist;

	@FindBy(id="searchCreditAccounts:acquierBank")
	public WebElement acquirerbanklist;

	@FindBy(id="searchCreditAccounts:accountnatureList")
	public WebElement accountnaturelist;

	@FindBy(id="searchCreditAccounts:MerchantID")
	WebElement acquirermerchantid;

	@FindBy(id="searchCreditAccounts:searchBtn")
	public WebElement searchtbtn;

	@FindBy(id="searchCreditAccounts:resetBtn")
	public WebElement resetbtn;


	
	@FindBy(id="searchCreditAccounts:profilesResultsTable:0:textSearchResults1")
	WebElement firstaccountintable;


	@FindBy(className="error")
	public WebElement errormsg;

	String actualcreditaccount;

	public String  SearchCreditAccount(AccountPojo searchcreditaccountobj) throws InterruptedException
	{
		driver.navigate().to(Constants.SEARCH_CREDIT_ACCOUNT_URL);
		resetbtn.click();
		if (searchcreditaccountobj.getAccountCode() != "") {
			accountcodetxt.clear();
			accountcodetxt.sendKeys(searchcreditaccountobj.getAccountCode());
		}
		bankterminalnotxt.clear();
		bankterminalnotxt.sendKeys(searchcreditaccountobj.getBankTerminal());
		acquirermerchantid.clear();
		acquirermerchantid.sendKeys(searchcreditaccountobj.getMerchantName());
		if (searchcreditaccountobj.getAccountType() != "") {
			new Select(accounttypelist).selectByVisibleText(searchcreditaccountobj.getAccountType());
		}
		if (searchcreditaccountobj.getAquireBank() != "") {
			new Select(acquirerbanklist).selectByVisibleText(searchcreditaccountobj.getAquireBank());
		}
		if (searchcreditaccountobj.getTerminalStatus() != "") {
			new Select(statuslist).selectByVisibleText(searchcreditaccountobj.getTerminalStatus());
		}
		if (searchcreditaccountobj.getAccountNature() != "") {
			new Select(accountnaturelist).selectByVisibleText(searchcreditaccountobj.getAccountNature());
		}
		if (searchcreditaccountobj.getAction().equalsIgnoreCase("reset"))
		{
			resetbtn.click();
			actualcreditaccount = accountcodetxt.getText()+bankterminalnotxt.getText()+acquirermerchantid.getText();
		}
		else if (searchcreditaccountobj.getAction().contains("Search"))
		{
			searchtbtn.click();
			if (driver.findElements(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")).size() != 0)
			{
				actualcreditaccount = "Table is Shown Successfully";
				if (searchcreditaccountobj.getAction().contains("Click"))
				{
					firstaccountintable.click();
					actualcreditaccount = driver.getCurrentUrl();
				}
			}
			else if (driver.findElements(By.className("error")).size() != 0)
			{
				actualcreditaccount = errormsg.getText();
			}
		}

		return actualcreditaccount;

	}


	public void movetoSearchcreditaccountpage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Credit Card Account"));
		action.moveToElement(selecthover);
		action.click();
		action.perform();

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

