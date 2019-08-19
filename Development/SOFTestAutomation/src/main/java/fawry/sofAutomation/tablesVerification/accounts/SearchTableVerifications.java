package fawry.sofAutomation.tablesVerification.accounts;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;

public class SearchTableVerifications 
{

	WebDriver driver;

	public SearchTableVerifications(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Both accounts and credit accounts table
	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")
	WebElement accountsTable;

	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[2]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")
	WebElement terminaltable;

	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")
	WebElement dormantaccounttable;
	
	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]")
	WebElement credittable;	

	@FindBy(xpath="//input[@title='Next']")
	WebElement nexttablebtn;


	public ArrayList<AccountPojo>  SearchAccountTable() throws InterruptedException
	{
		ArrayList<AccountPojo> accounts=new ArrayList<>();
		AccountPojo account=new AccountPojo();

		while(true)
		{
			List<WebElement> rows_table = accountsTable.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {

				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				account.setAccountCode( Columns_row.get(0).getText());
				account.setAccountStatus( Columns_row.get(1).getText());
				account.setMerchantName( Columns_row.get(2).getText());
				account.setCsp( Columns_row.get(3).getText());
				//           account.setBalance( Columns_row.get(4).getText());
				account.setCurrency( Columns_row.get(5).getText());
				account.setDailyLimit( Columns_row.get(6).getText());
				account.setCreditLimit( Columns_row.get(7).getText());
				//             account.setCreationDate( Columns_row.get(8).getText());
				//           account.setPsp( Columns_row.get(9).getText());
				account.setDescription( Columns_row.get(10).getText());
				account.setOfficialType( Columns_row.get(11).getText());
				account.setOfficialnumber( Columns_row.get(12).getText());
				//             account.setPendingDate( Columns_row.get(13).getText());
				//            account.setPendingAmount( Columns_row.get(14).getText());



				accounts.add(account);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}
		System.out.println(accounts.size());
		return accounts;
	}

	public ArrayList<TerminalPojo>  SearchTerminalTable() throws InterruptedException
	{
		ArrayList<TerminalPojo> terminals=new ArrayList<>();
		TerminalPojo terminal=new TerminalPojo();

		while(true)
		{
			List<WebElement> rows_table = terminaltable.findElements(By.tagName("tr"));

			for (int row = 0; row < rows_table.size(); row++) {

				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				terminal.SetTerminalCode( Columns_row.get(0).getText());
				terminal.setTerminalType( Columns_row.get(1).getText());
				terminal.setTerminalstatus( Columns_row.get(2).getText());
				terminal.setSerialNumber( Columns_row.get(3).getText());
				terminal.setAccountcode( Columns_row.get(4).getText());
				terminal.setAccountstatus( Columns_row.get(5).getText());
				//terminal.SetProfileCode( Columns_row.get(6).getText());
				terminal.setSapreNormalType( Columns_row.get(7).getText());

				terminals.add(terminal);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}
		System.out.println(terminals.size());
		return terminals;
	}

	public ArrayList<AccountPojo>  SearchDormanAccountTable() throws InterruptedException
	{
		ArrayList<AccountPojo> accounts=new ArrayList<>();
		AccountPojo account=new AccountPojo();

		while(true)
		{
			List<WebElement> rows_table = dormantaccounttable.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {

				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				account.setAccountCode( Columns_row.get(0).getText());
				account.setCsp( Columns_row.get(1).getText());
				//account.setMerchantName( Columns_row.get(2).getText());
				account.setNewCsp( Columns_row.get(3).getText());

				accounts.add(account);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}
		System.out.println(accounts.size());
		return accounts;
	}

	public ArrayList<AccountPojo>  SearchCreditAccountTable() throws InterruptedException
	{
		ArrayList<AccountPojo> accounts=new ArrayList<>();
		AccountPojo account=new AccountPojo();

		while(true)
		{
			List<WebElement> rows_table = credittable.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {

				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				//account.setBankTerminal( Columns_row.get(0).getText());
				account.setAccountCode( Columns_row.get(1).getText());
				//account.setTerminalStatus( Columns_row.get(2).getText());
				

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


