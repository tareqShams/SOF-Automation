package fawry.sofAutomation.tablesVerification.basicDefinitions;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;

public class SearchCspTableVerifications {
	WebDriver driver;




	public SearchCspTableVerifications(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//table[@id='searchCSPCashCollection:profilesResultsTable']//tbody[1]")
	WebElement table;
	@FindBy(id="searchCSPCashCollection:profilesResultsTable:deluxe1__pagerNext")
	WebElement nexttablebtn;

	public ArrayList<CspBillTypePojo>  searchCsp() throws InterruptedException
	{
		ArrayList<CspBillTypePojo> allCspInWebTable=new ArrayList<>();
		CspBillTypePojo csp;

		while(true)
		{
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {
				csp=new CspBillTypePojo();
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				csp.setCustomerServiceProvider( Columns_row.get(1).getText());
				csp.setBillTypeCode( Columns_row.get(2).getText());

				allCspInWebTable.add(csp);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}

		System.out.println(allCspInWebTable.size());

		return allCspInWebTable;
	}


}
