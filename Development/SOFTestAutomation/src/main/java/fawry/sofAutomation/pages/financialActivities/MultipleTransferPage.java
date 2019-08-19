package fawry.sofAutomation.pages.financialActivities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;

public class MultipleTransferPage {
	WebDriver driver;
	public MultipleTransferPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="TransferToBulkAccounts:cspList")
	WebElement csplist;

	@FindBy(id="TransferToBulkAccounts:tableEx1:0:amountInput")
	WebElement firstaccounttramounttxt;

	@FindBy(id="TransferToBulkAccounts:tableEx1:1:amountInput")
	WebElement secondaccounttramounttxt;

	
	@FindBy(id="TransferToBulkAccounts:tableEx1:2:amountInput")
	WebElement thirdaccounttramounttxt;

	@FindBy(id="TransferToBulkAccounts:tableEx1:0:AcctCodeTxt")
	WebElement firstaccountcodemsg;
	
	@FindBy(id="TransferToBulkAccounts:tableEx1:1:AcctCodeTxt")
	WebElement secondaccountcodemsg;
	
	@FindBy(id="TransferToBulkAccounts:tableEx1:2:AcctCodeTxt")
	WebElement thirdaccountcodemsg;
	
	@FindBy(id="TransferToBulkAccounts:tableEx1:0:DescInput")
	WebElement firstaccountdescriptiontxt;
	
	@FindBy(id="TransferToBulkAccounts:tableEx1:0:BalanceAfterTxt")
	WebElement faccbalanceaftertransfermsg;
	
	@FindBy(id="TransferToBulkAccounts:tableEx1:1:BalanceAfterTxt")
	WebElement saccbalanceaftertransfermsg;
	
	@FindBy(id="TransferToBulkAccounts:transferBtn")
	WebElement transferbtn;

	@FindBy(id="TransferToBulkAccounts:resetBtn")
	WebElement resetbtn;

	@FindBy(id="TransferToBulkAccounts:confirmBtn")
	WebElement confirmbtn;

	@FindBy(id="TransferToBulkAccounts:backBtn")
	WebElement backbtn;

	@FindBy(id="TransferToBulkAccounts:cancelBtn")
	WebElement cancelbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;
	
	@FindBy(id="TransferToBulkAccounts:CorrectMessage")
	WebElement successmsg;
	

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("hmssmmm").format(Calendar.getInstance().getTime());


	//All Common fields method
	public void  CommonFields(AccountTrxPojo multipletransferobj)
	{

		resetbtn.click();
		//When csp is empty all other fields will not appear
		if (!multipletransferobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(multipletransferobj.getCsp());
		
			// Collecting account codes to be used in DB verification
			multipletransferobj.setAccountcode(firstaccountcodemsg.getText());
			multipletransferobj.setSecondaccount(secondaccountcodemsg.getText());
			multipletransferobj.setThirdaccount(thirdaccountcodemsg.getText());

			firstaccounttramounttxt.clear();
			firstaccounttramounttxt.sendKeys(multipletransferobj.getAmount());
			
			if (!multipletransferobj.getDescription().isEmpty()) 
			{
			firstaccountdescriptiontxt.clear();
			firstaccountdescriptiontxt.sendKeys(multipletransferobj.getDescription()+ timestamp);
			}
			
			secondaccounttramounttxt.clear();
			secondaccounttramounttxt.sendKeys(multipletransferobj.getSecondamount());
			
			thirdaccounttramounttxt.clear();
			thirdaccounttramounttxt.sendKeys(multipletransferobj.getThirdamount());
		}
		
	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(AccountTrxPojo multipletransferobj) 
	{
		actual = "";
		// Collecting success message
		if (driver.findElements(By.id("TransferToBulkAccounts:CorrectMessage")).size() != 0  )
		{
			actual = successmsg.getText();
		}
		// Collecting error messages in a list 
		else if ( driver.findElements(By.className("error")).size() != 0)
		{
			errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<HeaderErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;	
		}
		else if (driver.findElements(By.className("fieldError")).size() != 0  )
		{
			errorMsgs = pageErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<pageErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;
		}		

		return actual;
	}

	//Saving or resetting Data
	public void saveOrResetData(AccountTrxPojo multipletransferobj) {

		if (multipletransferobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (multipletransferobj.getAction().contains("Transtwicefer"))
		{
			transferbtn.click();
			transferbtn.click();
		}
		else if (multipletransferobj.getAction().contains("Transfer"))
		{
			transferbtn.click();
			// Some Cases need confirmation and it can only happen after confirmation page shows
			if ( driver.getCurrentUrl().contains("ConfirmMultiAcctsMoneyTransfer.faces"))
			{
				if (multipletransferobj.getAction().contains("Confirm")){
					confirmbtn.click();
				}
				else if (multipletransferobj.getAction().contains("Back")){
					backbtn.click();
				}
				else if (multipletransferobj.getAction().contains("Cancel")){
					cancelbtn.click();
				}
			}
		}
	}





	public void movetomultipletransferpage () {

		WebElement hover = driver.findElement(By.linkText("Financial Activities"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		//WebElement selecthover = driver.findElement(By.linkText("Transfer money to multiple accounts"));
		//action.moveToElement(selecthover);
		//action.click();
		//action.perform();

	}
	/*
	 *  //change value of a cell
    public String isnullvalue(String value)
    {
           if(value.equalsIgnoreCase("*"))
           {
                  value="";
           }
           return value;
    }
	 */
}

