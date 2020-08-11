package fawry.sofAutomation.pages.accounts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.main.MainPage;
import fawry.sofAutomation.pojos.accounts.SearchPojo;;

public class SearchAccountPage extends MainPage {
	
	WebDriver driver;
	public SearchAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	ArrayList<String> resetActual;

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

	@FindBy(id="searchPOS:textSearchName")
	public WebElement nameTxt;

	@FindBy(id="searchPOS:textSearchDescription")
	public WebElement descTxt;

	@FindBy(id="searchPOS:currList")
	public WebElement currencyLst;

	@FindBy(id="searchPOS:usageList")
	public WebElement usageLst;

	@FindBy(id="searchPOS:cspList")
	public WebElement cspLst;

	@FindBy(id="searchPOS:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchPOS:resetBtn")
	public WebElement resetbtn;

	@FindBy(className="pagerDeluxe")
	public WebElement resultscounter;

	@FindBy(id="searchPOS:errorMessage")
	public WebElement errormsg;

	@FindBy(xpath="//input[@type='text']")
	ArrayList<WebElement> allTxtFields;

	public String allTxtFieldsData;

	public void  SearchAccount(SearchPojo searchaccountobj) throws InterruptedException
	{
		resetbtn.click();
		if (searchaccountobj.getAccountCode() != "") {
			accountcodetxt.sendKeys(searchaccountobj.getAccountCode());
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
		if (searchaccountobj.getName() != "") {
			nameTxt.sendKeys(searchaccountobj.getName());
		}
		if (searchaccountobj.getDescription() != "") {
			descTxt.sendKeys(searchaccountobj.getDescription());
		}
		if (searchaccountobj.getCsp() != "") {
			new Select(cspLst).selectByVisibleText(searchaccountobj.getCsp());
		}
		if (searchaccountobj.getCurrency() != "") {
			new Select(currencyLst).selectByVisibleText(searchaccountobj.getCurrency());
		}
		if (searchaccountobj.getUsage() != "") {
			new Select(usageLst).selectByVisibleText(searchaccountobj.getUsage());
		}
	}


	public void saveOrReset (SearchPojo searchaccountobj) {

		if(searchaccountobj.getAction().contains("Search"))
		{
			searchtbtn.click();
		}
		if(searchaccountobj.getAction().contains("Reset"))
		{
			resetbtn.click();
			
			allTxtFieldsData = allTxtFields.get(0).getText().toString();
			for(int i=1;i<allTxtFields.size();i++)
			{
				allTxtFieldsData=allTxtFieldsData+allTxtFields.get(i).getText().toString();
			}
		}

	}
}

