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

public class InternalTransferPage {
	WebDriver driver;
	public InternalTransferPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="transfer:corporate")
	WebElement csplist;

	@FindBy(id="transfer:fromAcctCode")
	WebElement fromaccountcodetxt;

	@FindBy(id="transfer:toAcctCode")
	WebElement toaccountcodetxt;

	@FindBy(id="transfer:trxAmount")
	WebElement amounttxt;

	@FindBy(id="transfer:description")
	WebElement descriptiontxt;

	@FindBy(id="transfer:transferBtn")
	WebElement transferbtn;

	@FindBy(id="transfer:resetBtn")
	WebElement resetbtn;

	@FindBy(id="confirmTransfer:confirmBtn")
	WebElement confirmbtn;

	@FindBy(id="confirmTransfer:backBtn")
	WebElement backbtn;

	@FindBy(id="confirmTransfer:cancelBtn")
	WebElement cancelbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;
	
	@FindBy(id="confirmTransfer:correctMessage")
	WebElement successmsg;
	

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	//All Common fields method
	public void  CommonFields(AccountTrxPojo internaltransferobj)
	{

		resetbtn.click();
		
		if (!internaltransferobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(internaltransferobj.getCsp());
		}

		fromaccountcodetxt.clear();
		fromaccountcodetxt.sendKeys(internaltransferobj.getAccountcode());
		
		toaccountcodetxt.clear();
		toaccountcodetxt.sendKeys(internaltransferobj.getToaccountCode());
		
		amounttxt.clear();
		amounttxt.sendKeys(internaltransferobj.getAmount());
		
		descriptiontxt.clear();
		descriptiontxt.sendKeys(internaltransferobj.getDescription());

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(AccountTrxPojo internaltransferobj) 
	{
		actual = "";
		// Collecting success message
		if (driver.findElements(By.id("confirmTransfer:correctMessage")).size() != 0  )
		{
			actual = successmsg.getText();
		}
		// Collecting error messages in a list 
		else if (driver.findElements(By.className("fieldError")).size() != 0  )
		{
			errorMsgs = pageErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<pageErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;
		}		
		else if ( driver.findElements(By.className("error")).size() != 0)
		{
			errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<HeaderErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;	
		}
		return actual;
	}

	//Saving or resetting Data
	public void saveOrResetData(AccountTrxPojo internaltransferobj) {

		if (internaltransferobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (internaltransferobj.getAction().contains("Transfer"))
		{
			transferbtn.click();
			// Some Cases need confirmation and it can only happen after confirmation page shows
			if ( driver.getCurrentUrl().contains("ConfirmTransfer.faces"))
			{
				if (internaltransferobj.getAction().contains("Confirm")){
					confirmbtn.click();
				}
				else if (internaltransferobj.getAction().contains("Back")){
					backbtn.click();
				}
				else if (internaltransferobj.getAction().contains("Cancel")){
					cancelbtn.click();
				}
			}
		}
	}





	public void movetointernaltransferpage () {

		WebElement hover = driver.findElement(By.linkText("Financial Activities"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Internal Transfer"));
		action.moveToElement(selecthover);
		action.click();
		action.perform();

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

