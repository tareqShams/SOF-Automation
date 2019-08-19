package fawry.sofAutomation.pages.accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.accounts.SearchPojo;;

public class SearchAccountPage {
	WebDriver driver;

	public SearchAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchPOS:textSearchPOSID1")
	public WebElement accountcodetxt;

	@FindBy(id="searchPOS:textSearchMerchantID1")
	public WebElement merchantcodetxt;

	@FindBy(id="searchPOS:textSearchOfficialNum1")
	public WebElement officialnumbertxt;

	@FindBy(id="searchPOS:officialTypeList")
	public WebElement officialtypelist;

	@FindBy(id="searchPOS:acctGroupList")
	public WebElement accountgrouplist;

	@FindBy(id="searchPOS:menu1")
	public WebElement statuslist;

	@FindBy(id="searchPOS:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchPOS:resetBtn")
	public WebElement resetbtn;

	@FindBy(className="pagerDeluxe")
	public WebElement resultscounter;
	
	@FindBy(id="searchPOS:errorMessage")
	public WebElement errormsg;



	public String  SearchAccount(SearchPojo searchaccountobj) throws InterruptedException
	{
		resetbtn.click();
		if (searchaccountobj.getAccountCode() != "") {
			accountcodetxt.sendKeys(searchaccountobj.accountcode);
		}
		if (searchaccountobj.getMerchantCode() != "") {
			merchantcodetxt.sendKeys(searchaccountobj.getMerchantCode());
		}
		if (searchaccountobj.getOfficialNumber() != "") {
			officialnumbertxt.sendKeys(searchaccountobj.getOfficialNumber());
		}
		if (searchaccountobj.getOfficialType() != "") {
			new Select(officialtypelist).selectByVisibleText(searchaccountobj.getOfficialType());
		}
		if (searchaccountobj.getAccountGroup() != "") {
			new Select(accountgrouplist).selectByVisibleText(searchaccountobj.getAccountGroup());
		}
		if (searchaccountobj.getStatus() != "") {
			new Select(statuslist).selectByVisibleText(searchaccountobj.getStatus());
		}
		searchtbtn.click();
		return "Success";

	}


	public void movetoSearchaccountpage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Account"));
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

