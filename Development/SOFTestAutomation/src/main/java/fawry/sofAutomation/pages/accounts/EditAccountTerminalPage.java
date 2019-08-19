package fawry.sofAutomation.pages.accounts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class EditAccountTerminalPage {
	WebDriver driver;
	public EditAccountTerminalPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchPOS:button1")
	WebElement searchtbtn;

	@FindBy(id="searchPOS:tableEx1:0:textSearchResults1")
	WebElement accountcodelink;

	@FindBy(id="searchPOS:textSearchPOSID1")
	WebElement accountidtxt;

	@FindBy(xpath="/html/body/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/form/table/tbody/tr[6]/td/table/tbody/tr/td/table/tbody")
	WebElement table;

	@FindBy(linkText="Account Terminals")
	WebElement accountterminallnk;

	@FindBy(id="editAccountTerminal:saveData")
	WebElement savebtn;

	@FindBy(id="editAccountTerminal:backToSearch")
	WebElement backbtn;
	
	@FindBy(id="editAccountTerminal:resetData")
	WebElement resetbtn;
	
	@FindBy(id="editAccountTerminal:deleteSubAccount")
	WebElement deletebtn;

	@FindBy(id="editAccountTerminal:statusList")
	WebElement terminalstatuslist;
	
	@FindBy(id="editAccountTerminal:TermName")
	WebElement terminalname;
	
	@FindBy(id="editAccountTerminal:TermList")
	WebElement terminaltypelist;
	
	@FindBy(id="editAccountTerminal:ProfileCode")
	WebElement profilecodetxt;
	
	@FindBy(id="editAccountTerminal:textSN1")
	WebElement serialnumbertxt;
	
	@FindBy(id="editAccountTerminal:textareaDescription1")
	WebElement descriptiontxt;

	@FindBy(id="editAccountTerminal:TermCode")
	WebElement terminalcodetxt;
	
	@FindBy(id="editAccountTerminal:CorrectMessage")
	public WebElement successmsg;
	
	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	WebElement headerErrorMsgsList;


	String beforeid = "accountTerminals:accountsResultsTable:";
	String afterid = ":textSearchResults1";
	int tablesize;
	String actualeditterm;
	String databeforeupdate;
	String dataafterupdate;
	String errorMsgs;
	public 	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());
	/*
	 * using sparefield5 for data before resetting
	 * using sparefield6 for data after resetting
	 */


	public String  editAccountTerminal(AccountPojo editaccountterminalobj)
	{
		actualeditterm = "";
		navigatetoeditAccountterminal(editaccountterminalobj);
		tablesize();
		//Click On The latest Added terminal
		WebElement latestaddedterminal = driver.findElement(By.id(beforeid+tablesize+afterid));
		latestaddedterminal.click();

		// Collecting Data For checking when resetting
		Select selectstatus = new Select(terminalstatuslist);
		Select selecttype = new Select(terminaltypelist);
		databeforeupdate = selectstatus.getOptions().get(0).toString()+selecttype.getOptions().get(0).toString()
				+profilecodetxt.getText()+serialnumbertxt.getText()+descriptiontxt.getText();
		editaccountterminalobj.setSparefield5(databeforeupdate);
		editaccountterminalobj.setTerminalCode(terminalcodetxt.getText());
		if(!editaccountterminalobj.getTerminalStatus().isEmpty())
		{
			new Select(terminalstatuslist).selectByVisibleText(editaccountterminalobj.getTerminalStatus());
		}
		if(!editaccountterminalobj.getTerminalName().isEmpty())
		{
			terminalname.clear();
			terminalname.sendKeys(editaccountterminalobj.getTerminalName());
		}
		if(!editaccountterminalobj.getTerminalType().isEmpty())
		{
			new Select(terminaltypelist).selectByVisibleText(editaccountterminalobj.getTerminalType());
		}
		if(!editaccountterminalobj.getProfileid().isEmpty())
		{
			profilecodetxt.clear();
			profilecodetxt.sendKeys(editaccountterminalobj.getProfileid());
		}
		if (!editaccountterminalobj.getSerialNumber().isEmpty())
		{
			serialnumbertxt.clear();
			serialnumbertxt.sendKeys(editaccountterminalobj.getSerialNumber());
		}
		if(!editaccountterminalobj.getDescription().isEmpty())
		{
			descriptiontxt.clear();
			descriptiontxt.sendKeys(editaccountterminalobj.getDescription());
		}
		if(editaccountterminalobj.getAction().contains("Save"))
		{
			savebtn.click();
			actualeditterm = "";
			if (driver.findElements(By.className("fieldError")).size() != 0 )
			{
				errorMsgs = pageErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<pageErrorMsgsList.size();i++)
				{
					errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
				}
				actualeditterm = errorMsgs;
			}
			else if (driver.findElements(By.className("error")).size() != 0 )
			{
				actualeditterm = headerErrorMsgsList.getText();
			}
			else if (driver.findElements(By.id("editAccountTerminal:CorrectMessage")).size() != 0 )
			{
				actualeditterm = successmsg.getText();
			}
		}
		else if (editaccountterminalobj.getAction().contains("Reset"))
		{
			resetbtn.click();
			Select selectstatusafter = new Select(terminalstatuslist);
			Select selecttypeafter = new Select(terminaltypelist);
			databeforeupdate = selectstatusafter.getOptions().get(0).toString()+selecttypeafter.getOptions().get(0).toString()
					+profilecodetxt.getText()+serialnumbertxt.getText()+descriptiontxt.getText();
			editaccountterminalobj.setSparefield6(dataafterupdate);
		}
		else if (editaccountterminalobj.getAction().contains("Back"))
		{
			backbtn.click();
			actualeditterm = driver.getCurrentUrl();
		}
		else if (editaccountterminalobj.getAction().contains("Delete"))
		{
			deletebtn.click();
			Alert alert;
			driver.switchTo().alert().accept();
		}
		
		
			
		return actualeditterm;

	}


	public void  navigatetoeditAccountterminal(AccountPojo editsubaccountobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(editsubaccountobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();

		//Navigate To SubAccount Menu
		accountterminallnk.click();
	}

	public void tablesize() 
	{
		List<WebElement> row_table = table.findElements(By.tagName("tr"));
		System.out.println(row_table.size());
		tablesize = row_table.size()-1;

	}
}

