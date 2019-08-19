package fawry.sofAutomation.tablesVerification.basicDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;

public class SearchCspPushAlertConfigurationTableVerification {

	WebDriver driver;

	public SearchCspPushAlertConfigurationTableVerification(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//table[@id='searchBiller:profilesResultsTable']//tbody[1]")
	WebElement table;

	@FindBy(id="searchBiller:profilesResultsTable:deluxe1__pagerNext")
	WebElement nexttablebtn;


	public ArrayList<CspPushAlertPojo>  SearchCspPushAlertConfigurationTable() throws InterruptedException
	{
		ArrayList<CspPushAlertPojo> results=new ArrayList<>();
		CspPushAlertPojo result;

		while(true)
		{
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {
				result=new CspPushAlertPojo();
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
				result.setCsp( Columns_row.get(0).getText());
				result.setAccountType( Columns_row.get(1).getText());
				System.out.println(result.getCsp());
				System.out.println(result.getAccountType());
				results.add(result);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}

		System.out.println(results.size());

		return results;
	}
	
}
