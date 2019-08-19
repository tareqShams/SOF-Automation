package fawry.sofAutomation.pages.basicDefinitions;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;


public class SearchCspBillTypesPoolAccountPage  extends MainPage{
	WebDriver driver;

	public SearchCspBillTypesPoolAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="searchBiller:cspList")
	WebElement csp;

	@FindBy(id="searchBiller:btcList")
	WebElement btc;
	
	@FindBy(id="searchBiller:code")
	WebElement code; 
	@FindBy(id="searchBiller:searchBtn")
	WebElement search;

	@FindBy(id="searchBiller:resetBtn")
	WebElement reset;
	
	@FindBy(xpath="//table[@id='searchBiller:profilesResultsTable']/tbody[1]")
	WebElement table;

	
	public String  SearchCspBillTypesPoolAccount(CspBtcPoolAccountPojo CspBtcPoolAccountObj)	
	{
		csp.sendKeys(CspBtcPoolAccountObj.getCsp());
		btc.sendKeys(CspBtcPoolAccountObj.getBillTypeCode());
		code.sendKeys(CspBtcPoolAccountObj.getPoolAccount());

	if(CspBtcPoolAccountObj.getFlag().contains("search"))
	{
			search.click();
			return "search";

	}
	else if (CspBtcPoolAccountObj.getFlag().contains("reset"))
	{
		reset.click();
		return "reset";
	}
	return "fail";
	}
	
	public void  SearchCspBillTypesPoolAccountForEditPage(CspBtcPoolAccountPojo CspBtcPoolAccountObj)	
	{
		csp.sendKeys(CspBtcPoolAccountObj.getCsp());
		btc.sendKeys(CspBtcPoolAccountObj.getBillTypeCode());
		code.sendKeys(CspBtcPoolAccountObj.getPoolAccount());
		search.click();
		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		List<WebElement> Columns_row = rows_table.get(0).findElements(By.tagName("td"));
		Columns_row.get(0).click();
	

	
	}
	public boolean reset()
	{
		
		Select csp1=new Select(csp);
		Select btc1=new Select(btc);
		if(code.getAttribute("value").isEmpty() 
				&& csp1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& btc1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -"))
		{
			return true;
		 
		}
		return false;
		
		
		
	}
}
