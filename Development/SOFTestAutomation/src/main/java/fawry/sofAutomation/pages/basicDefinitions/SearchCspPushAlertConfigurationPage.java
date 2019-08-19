package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;

public class SearchCspPushAlertConfigurationPage extends MainPage{
	WebDriver driver;

	public SearchCspPushAlertConfigurationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="searchBiller:csp")
	WebElement csp;

	@FindBy(id="searchBiller:accountType")
	WebElement accountType;

	@FindBy(id="searchBiller:searchBtn")
	WebElement search; 

	@FindBy(id="searchBiller:resetBtn")
	WebElement reset;

	@FindBy(id="searchBiller:errorMessage")
	List<WebElement>  errorMessage;


	@FindBy(xpath="//table[@id='searchBiller:profilesResultsTable']//tbody[1]")
	WebElement table;

	public String  SearchCspPushAlertConfig(CspPushAlertPojo CspPushAlertObj)	
	{
		csp.sendKeys(CspPushAlertObj.getCsp());
		accountType.sendKeys(CspPushAlertObj.getAccountType());

		if(CspPushAlertObj.getFlag().equalsIgnoreCase("search"))
		{ 
			search.click();
			if(errorMessage.size()!=0)
			{
				System.out.println(errorMessage.get(0).getText().toString());
				return errorMessage.get(0).getText().toString();
			}
			return "search";
		}
		else if(CspPushAlertObj.getFlag().equalsIgnoreCase("reset"))
		{ 
			reset.click();
			return "reset";
		}
		return "fail";
	}
	public boolean reset()
	{
		Select csp1=new Select(csp);
		Select accountType1=new Select(accountType);

		if(csp1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& accountType1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -"))
		{
			return true;
		}
		return false;

	}
	public void  SearchCspPushAlertConfForEdit(CspPushAlertPojo CspPushAlertObj)	
	{
		csp.sendKeys(CspPushAlertObj.getCsp());
		accountType.sendKeys(CspPushAlertObj.getAccountType());
		search.click();

		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		List<WebElement> Columns_row = rows_table.get(0).findElements(By.tagName("td"));
		Columns_row.get(0).click();

	}

}
