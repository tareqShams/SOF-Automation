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

public class SearchDormantAccountPage {
	WebDriver driver;

	public SearchDormantAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchDormantAccounts:textDormantCode")
	public WebElement dormantaccountcodetxt;

	@FindBy(id="searchDormantAccounts:textActiveCode")
	public WebElement activeaccountcodetxt;

	@FindBy(id="searchDormantAccounts:dormantCspList")
	public WebElement oldcsplist;

	@FindBy(id="searchDormantAccounts:activeCspList")
	public WebElement newcsplist;

	@FindBy(id="searchDormantAccounts:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchDormantAccounts:resetBtn")
	public WebElement resetbtn;

	@FindBy(className="pagerDeluxe")
	public WebElement resultscounter;
	
	@FindBy(id="searchPOS:errorMessage")
	public WebElement errormsg;

	String actualsearchdormantaccount;


	public String  SearchDormantAccount(AccountPojo searchdormantaccountobj) throws InterruptedException
	{
		driver.navigate().to(Constants.SEARCH_DORMANT_ACCOUNT_URL);
		resetbtn.click();
		if (searchdormantaccountobj.getTerminalStatus().equalsIgnoreCase("Dormant")) {
			dormantaccountcodetxt.clear();
			dormantaccountcodetxt.sendKeys(searchdormantaccountobj.getAccountCode());
		}
		if (searchdormantaccountobj.getTerminalStatus().equalsIgnoreCase("Active")) {
			activeaccountcodetxt.clear();
			activeaccountcodetxt.sendKeys(searchdormantaccountobj.getAccountCode());
		}
		if (!searchdormantaccountobj.getCsp().isEmpty()) 
		{
			System.out.println(searchdormantaccountobj.getCsp());
			new Select(oldcsplist).selectByVisibleText(searchdormantaccountobj.getCsp());
		}
		if (!searchdormantaccountobj.getNewCsp().isEmpty()) 
		{
			System.out.println(searchdormantaccountobj.getNewCsp());
			new Select(newcsplist).selectByVisibleText(searchdormantaccountobj.getNewCsp());
		}
		if (searchdormantaccountobj.getAction().equalsIgnoreCase("reset"))
		{
			resetbtn.click();
			actualsearchdormantaccount = dormantaccountcodetxt.getText()+activeaccountcodetxt.getText();
		}
		else if (searchdormantaccountobj.getAction().equalsIgnoreCase("search"))
		{
			searchtbtn.click();
			if (driver.findElements(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")).size() != 0)
			{
				actualsearchdormantaccount = "table is shown successfully";
			}
		}

		return actualsearchdormantaccount;

	}


	public void movetoSearchDormantAccountpage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Dormant Account"));
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

