package fawry.sofAutomation.tablesVerification.basicDefinitions;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;
import fawry.sofAutomation.pojos.basicDefinitions.FinancePojo;

public class SearchFinanceTableVerifications {
	WebDriver driver;




	public SearchFinanceTableVerifications(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@id=\"searchTrx:tableEx1\"]/tbody")
	WebElement table;
	@FindBy(xpath="//input[@title='Next']")
	//input[@title='Next']
	WebElement nexttablebtn;

	public ArrayList<FinancePojo>  searchFinanceSetup()
	{
		ArrayList<FinancePojo> allFinanceProgInWebTable=new ArrayList<>();
		FinancePojo finance;

		while(true)
		{
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {
				finance=new FinancePojo();
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				finance.setFinanceprogId( Columns_row.get(1).getText());
				finance.setAccountNumber( Columns_row.get(2).getText());
				finance.setSubAccount( Columns_row.get(3).getText());

				allFinanceProgInWebTable.add(finance);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}

		System.out.println(allFinanceProgInWebTable.size());

		return allFinanceProgInWebTable;
	}


}
