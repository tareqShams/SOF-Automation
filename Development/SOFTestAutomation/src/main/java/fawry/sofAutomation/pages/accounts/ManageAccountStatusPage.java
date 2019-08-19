package fawry.sofAutomation.pages.accounts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class ManageAccountStatusPage {
	WebDriver driver;

	public ManageAccountStatusPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="manageAcctStatus:acctCode")
	WebElement accountcodetxt;

	//The Code shown when account is found successfully
	@FindBy(id="manageAcctStatus:acctCodeTxt")
	WebElement accountshowncode;

	@FindBy(id="manageAcctStatus:acctStatusTxt")
	WebElement accountshowntatus;

	@FindBy(id="manageAcctStatus:acttTerminalsTable:0:trmnlCode")
	WebElement terminalcodetxt;

	@FindBy(id="manageAcctStatus:acttTerminalsTable:0:trmnlStatusTxt")
	WebElement terminalstatustxt;

	@FindBy(id="manageAcctStatus:susReason")
	WebElement suspentionreason;

	@FindBy(id="manageAcctStatus:updateAcctStatus")
	WebElement suspendaccountbtn;

	@FindBy(id="manageAcctStatus:acttTerminalsTable:0:j_id_jsp_572340282_161")
	WebElement activateterminalbtn;

	@FindBy(id="manageAcctStatus:reset")
	WebElement resetbtn;

	@FindBy(id="manageAcctStatus:getTerminal")
	WebElement getterminalsbtn;

	@FindBy(id="manageAcctStatus:CorrectMessage")
	public WebElement successmsg;

	@FindBy(className="fieldError")
	WebElement pageErrorMsgsList;

	@FindBy(className="error")
	WebElement headerErrorMsgs;

	String actual;
	String databeforeupdate;
	String dataafterreset;
	String errorMsgs;

	public String  manageAccountStatus(AccountPojo manageaccountobj)
	{
		//Navigate To url and reset data to make sure Fields are clear
		driver.navigate().to(Constants.MANAGE_ACCOUNT_STATUS_URL);
		//Reset Button only appears if account is found therefore checking on presence of account
		if (driver.findElements(By.id("manageAcctStatus:reset")).size() != 0 )
		{
		resetbtn.click();
		}
		//Insert account credintials 
		accountcodetxt.clear();
		accountcodetxt.sendKeys(manageaccountobj.getAccountCode());
		//Click on get terminals btn
		getterminalsbtn.click();
		//Check for error messages or success to get data
		if (driver.findElements(By.className("fieldError")).size() != 0 )
		{
			actual = pageErrorMsgsList.getText();
		}
		// If account code is shown then the account exist and Starting to execute test scenarios on accounts
		else if (driver.findElements(By.id("manageAcctStatus:acctCodeTxt")).size() != 0 )
		{
			if (manageaccountobj.getAction().contains("Suspend")) 
			{
				//Selecting Suspension Reason
				if (!manageaccountobj.getSuspentionReason().isEmpty())
				{
					new Select(suspentionreason).selectByVisibleText(manageaccountobj.getSuspentionReason());
				}
				// Check for presence of suspend account btn and click if present
				if (driver.findElements(By.id("manageAcctStatus:updateAcctStatus")).size() != 0 )
				{
					//Click on Suspend account button presence
					suspendaccountbtn.click();
					//Check for error messages
					if (driver.findElements(By.className("fieldError")).size() != 0 )
					{
						actual = pageErrorMsgsList.getText();
					}
					//Check for correct message presence
					else if (driver.findElements(By.id("manageAcctStatus:CorrectMessage")).size() != 0 )
					{
					actual = successmsg.getText();
					}
				}
			}
			//Terminal Activation for a blocked Terminal
			if (manageaccountobj.getAction().contains("Activate"))
			{
				if (driver.findElements(By.id("manageAcctStatus:acttTerminalsTable:0:j_id_jsp_572340282_161")).size() != 0)
				{
					activateterminalbtn.click();
					// Accept Activation message
					Alert alert = driver.switchTo().alert();
					System.out.println(alert.getText());
					alert.accept();
				}
				actual = terminalstatustxt.getText();
			}	
		}
		return actual;
	}

	public void movetomanageaccountstatuspage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Manage Account/Terminal Status"));
		action.moveToElement(selecthover);
		action.click();
		action.perform();

	}

}

