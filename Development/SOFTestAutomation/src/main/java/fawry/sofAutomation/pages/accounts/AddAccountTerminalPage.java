package fawry.sofAutomation.pages.accounts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class AddAccountTerminalPage {
	WebDriver driver;
	public AddAccountTerminalPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchPOS:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchPOS:tableEx1:0:textSearchResults1")
	public WebElement accountcodelink;

	@FindBy(id="searchPOS:textSearchPOSID1")
	public WebElement accountidtxt;

	@FindBy(linkText="Account Terminals")
	public WebElement accterminalslnk;

	@FindBy(id="accountTerminals:newAcctBtn")
	public WebElement newaccterminalbtn;
	
	@FindBy(xpath="//*[@id=\"accountTerminals:accountsResultsTable\"]/tbody")
	WebElement table;

	@FindBy(className="fieldError")
	public List<WebElement> pageErrorMsgsList;

	public 	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());
	public String beforeid = "accountTerminals:accountsResultsTable:";
	public String afterid = ":textSearchResults1";
	int tablesize;

	public String newaccountterminalcode;
	public String primaryaccountcode;
	String actualaccountterminal;




	public String  AddAccountTerminal(AccountPojo addaccountterminalobj)
	{
		navigatoAccountterminal(addaccountterminalobj);
		//Click on Add New Terminal btn
		newaccterminalbtn.click();
		// Calling already made method for adding new Terminal
		AddTerminalPage addterminal = new AddTerminalPage(driver);
		actualaccountterminal = addterminal.addNewTerminalAlreadyCreatedAccount(addaccountterminalobj);;
		// Get latest added terminal code to be used in DB check
		
		accterminalslnk.click();
		tablesize();
		//Click On The latest Added terminal
		WebElement latestaddedterminal = driver.findElement(By.id(beforeid+tablesize+afterid));
		// Set Terminal Code to accounts pojo
		addaccountterminalobj.setTerminalCode(latestaddedterminal.getText());
		
		return actualaccountterminal;

	}


	public void  navigatoAccountterminal(AccountPojo addaccounttermobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(addaccounttermobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();
		//Navigate To account Terminals Menu
		accterminalslnk.click();
	}
	public void tablesize() 
	{
		List<WebElement> row_table = table.findElements(By.tagName("tr"));
		System.out.println(row_table.size());
		tablesize = row_table.size()-1;

	}

}
