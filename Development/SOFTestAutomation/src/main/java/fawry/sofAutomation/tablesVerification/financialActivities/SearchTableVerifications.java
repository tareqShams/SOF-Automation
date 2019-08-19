package fawry.sofAutomation.tablesVerification.financialActivities;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;

public class SearchTableVerifications 
{

	WebDriver driver;

	public SearchTableVerifications(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Both accounts and credit accounts table
	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/table[2]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")
	WebElement accountstrxTable;

	@FindBy(xpath="//input[@title='Next']")
	WebElement nexttablebtn;


	public ArrayList<AccountTrxPojo>  SearchAccounttrxTable()
	{
		ArrayList<AccountTrxPojo> accounts=new ArrayList<>();
		AccountTrxPojo account=new AccountTrxPojo();

		while(true)
		{
			List<WebElement> rows_table = accountstrxTable.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {

				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				account.setAccountcode(Columns_row.get(0).getText());

				accounts.add(account);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}
		System.out.println(accounts.size());
		return accounts;
	}
}


