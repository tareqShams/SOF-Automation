package fawry.sofAutomation.pages.accounts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;

public class AddTerminalPage {

	WebDriver driver;

	public AddTerminalPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="AddAcctTerminalData:statusList")
	public WebElement newaccstatuslist;
	
	@FindBy(id="addAccountTerminal:statusList")
	public WebElement oldaccstatuslist;

	@FindBy(id="AddAcctTerminalData:TermList")
	public WebElement newacctypelist;
	
	@FindBy(id="addAccountTerminal:TermList")
	public WebElement oldacctypelist;

	@FindBy(id="AddAcctTerminalData:textSN1")
	public WebElement newaccserialnumbertxt;
	
	@FindBy(id="addAccountTerminal:textSN1")
	public WebElement oldaccserialnumbertxt;

	@FindBy(id="AddAcctTerminalData:textPin1")
	public WebElement newaccpintxt;
	
	@FindBy(id="addAccountTerminal:textPin1")
	public WebElement oldaccpintxt;

	@FindBy(id="AddAcctTerminalData:TermName")
	WebElement newaccnametxt;
	
	@FindBy(id="AddAcctTerminalData:textareaDescription1")
	WebElement newaccdescriptiontxt;
	
	@FindBy(id="AddAcctTerminalData:profileCode")
	WebElement newaccprofilecodetxt;
	
	@FindBy(id="addAccountTerminal:CorrectMessage")
	public WebElement terminalSuccessMsg;

	@FindBy(className="fieldError")
	public List<WebElement> ErrorMsgsList;

	@FindBy(id="AddAcctTerminalData:add")
	public WebElement newaccsavebtn;
	
	@FindBy(id="addAccountTerminal:button1")
	public WebElement oldaccsavebtn;

	@FindBy(id="AddAcctTerminalData:cancel")
	public WebElement newaccbackbtn;
	
	@FindBy(id="addAccountTerminal:button2")
	public WebElement oldaccresetbtn;

	public String errorMsgs;
	public String returnAddedTerminal;
	public 	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());

	public String  addNewTerminalNewAccount(TerminalPojo terminalPojo)
	{

		if(!terminalPojo.getTerminalstatus().isEmpty()) 
		{
			new Select(newaccstatuslist).selectByVisibleText(terminalPojo.getTerminalstatus());
		}
		
		newaccnametxt.clear();
		newaccnametxt.sendKeys(terminalPojo.getName());
		
		if(!terminalPojo.getTerminalType().isEmpty() ) 
		{
			new Select(newacctypelist).selectByVisibleText(terminalPojo.getTerminalType());
		}
		
		newaccprofilecodetxt.clear();
		newaccprofilecodetxt.sendKeys(terminalPojo.getProfilecode());
		
		newaccserialnumbertxt.clear();
		newaccserialnumbertxt.sendKeys(terminalPojo.getSeriallNumber()+ timestamp);
		terminalPojo.setSerialNumber(terminalPojo.getSeriallNumber()+ timestamp);
		
		newaccpintxt.clear();
		newaccpintxt.sendKeys(terminalPojo.getTerminalPin());
		
		newaccdescriptiontxt.clear();
		newaccdescriptiontxt.sendKeys(terminalPojo.getDescription());
		
		newaccsavebtn.click();
		
		if (driver.findElements(By.className("fieldError")).size() != 0)
		{
			// Read All Error messages in a List
			errorMsgs = ErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<ErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+ErrorMsgsList.get(i).getText().toString();
			}
			returnAddedTerminal = errorMsgs;
			newaccbackbtn.click();
		}
		else if (driver.getCurrentUrl().contains("AddPOS.faces"))
		{
			returnAddedTerminal = "success";
		}
		else if (driver.findElements(By.id("addAccountTerminal:CorrectMessage")).size() != 0)
		{
			returnAddedTerminal = terminalSuccessMsg.getText();
		}

		return returnAddedTerminal;
	}
	
	public String  addNewTerminalAlreadyCreatedAccount(AccountPojo accountobj)
	{

		if(!accountobj.getTerminalStatus().isEmpty()) 
		{
			new Select(oldaccstatuslist).selectByVisibleText(accountobj.getTerminalStatus());
		}
		if(!accountobj.getTerminalType().isEmpty() ) 
		{
			new Select(oldacctypelist).selectByVisibleText(accountobj.getTerminalType());
		}

		oldaccserialnumbertxt.clear();
		if (accountobj.getSerialNumber().equalsIgnoreCase("5"))
		{
		oldaccserialnumbertxt.sendKeys(accountobj.getSerialNumber()+ timestamp);
		}
		else if (!accountobj.getSerialNumber().equalsIgnoreCase("5"))
		{
		oldaccserialnumbertxt.sendKeys(accountobj.getSerialNumber());
		}
		oldaccpintxt.clear();
		oldaccpintxt.sendKeys(accountobj.getPin());
		System.out.println(accountobj.getAction());
		if(accountobj.getAction().contains("Save"))
		{
		oldaccsavebtn.click();
		}
		else if (accountobj.getAction().contains("Reset"))
		{
			oldaccresetbtn.click();
			returnAddedTerminal = oldaccserialnumbertxt.getText()+oldaccpintxt.getText();
		}
		if (driver.findElements(By.className("fieldError")).size() != 0)
		{
			// Read All Error messages in a List
			errorMsgs = ErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<ErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+ErrorMsgsList.get(i).getText().toString();
			}
			returnAddedTerminal = errorMsgs;
		}
		else if (driver.findElements(By.id("addAccountTerminal:CorrectMessage")).size() != 0)
		{
			returnAddedTerminal = terminalSuccessMsg.getText();
		}

		return returnAddedTerminal;
	}


}
