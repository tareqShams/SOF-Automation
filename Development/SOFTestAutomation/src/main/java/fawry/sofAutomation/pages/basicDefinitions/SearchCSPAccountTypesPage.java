package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;
import fawry.sofAutomation.pages.admin.MainPage;

public class SearchCSPAccountTypesPage extends MainPage{
	WebDriver driver;

	public SearchCSPAccountTypesPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(id="searchCSPAccountTypes:cspList")
	WebElement csp;

	@FindBy(id="searchCSPAccountTypes:accTypeList")
	WebElement accountType;

	@FindBy(id="searchCSPAccountTypes:searchBtn")
	WebElement search;

	@FindBy(id="searchCSPAccountTypes:resetBtn")
	WebElement reset;
	
	@FindBy(xpath="//table[@id='searchCSPAccountTypes:profilesResultsTable']/tbody")
	WebElement table;
	@FindBy(id="searchCSPAccountTypes:profilesResultsTable:deluxe1__pagerNext")
	WebElement nexttablebtn;
	
	public void  searchCspAccountType(CSPAccountTypePojo cspAccountObject)	
	{
	csp.sendKeys(cspAccountObject.getCsp());
	accountType.sendKeys(cspAccountObject.getAccountType());
//	if(cspBillObject.getFlag().contains("search"))
//	{
		search.click();
		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		List<WebElement> Columns_row = rows_table.get(0).findElements(By.tagName("td"));
		Columns_row.get(0).click();
//
//	}
//	else if (cspBillObject.getFlag().contains("reset"))
//	{
//		reset.click();
//	}
}


}
