package fawry.sofAutomation.tablesVerification.basicDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;

public class SearchCspBillTypesPoolAccountTableVerification {
	WebDriver driver;




	public SearchCspBillTypesPoolAccountTableVerification(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//table[@id='searchBiller:profilesResultsTable']//tbody[1]")
	WebElement table;
	
	@FindBy(id="searchBiller:profilesResultsTable:deluxe1__pagerNext")
	WebElement nexttablebtn;
	
	
	public ArrayList<CspBtcPoolAccountPojo>  searchCspBtcPoolAccount() throws InterruptedException
	{
		ArrayList<CspBtcPoolAccountPojo> allCspInWebTable=new ArrayList<>();
		CspBtcPoolAccountPojo csp;

		while(true)
		{
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {
				csp=new CspBtcPoolAccountPojo();
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				csp.setPoolAccount( Columns_row.get(0).getText());
				csp.setCsp( Columns_row.get(1).getText());
				csp.setBillTypeCode( Columns_row.get(2).getText());
				
				System.out.println(csp.getPoolAccount());
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
