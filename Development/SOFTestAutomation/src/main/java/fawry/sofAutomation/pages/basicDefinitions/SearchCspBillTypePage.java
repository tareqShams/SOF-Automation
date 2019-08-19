package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;

public class SearchCspBillTypePage extends MainPage {
	WebDriver driver;

	public SearchCspBillTypePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="searchCSPCashCollection:cspList")
	WebElement customerServiceProvider;

	@FindBy(id="searchCSPCashCollection:btcList")
	WebElement billTypeCode;
	
	@FindBy(id="searchCSPCashCollection:searchBtn")
	WebElement search;

	@FindBy(id="searchCSPCashCollection:resetBtn")
	WebElement reset;
	@FindBy(xpath="//table[@id='searchCSPCashCollection:profilesResultsTable']//tbody[1]")
	WebElement table;
	@FindBy(id="searchCSPCashCollection:profilesResultsTable:deluxe1__pagerNext")
	WebElement nexttablebtn;
	
	
	public void  searchCspBillType(CspBillTypePojo cspBillObject)	
	{
		customerServiceProvider.sendKeys(cspBillObject.getCustomerServiceProvider());
		billTypeCode.sendKeys(cspBillObject.getBillTypeCode());
//		if(cspBillObject.getFlag().contains("search"))
//		{
			search.click();
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			List<WebElement> Columns_row = rows_table.get(0).findElements(By.tagName("td"));
			Columns_row.get(0).click();
//
//		}
//		else if (cspBillObject.getFlag().contains("reset"))
//		{
//			reset.click();
//		}
	}
	
	public Boolean reset()
	{
		Select csp=new Select(customerServiceProvider);
		Select billtypecode=new Select(billTypeCode);

		if(csp.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& billtypecode.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -"))
		{
			return true;
		}
		return false;

	}
}
