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

public class AddAccountTrxPage {
	WebDriver driver;
	public AddAccountTrxPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="addTrx:corporate")
	WebElement csplist;

	@FindBy(id="addTrx:POSIDInput")
	WebElement accountcodetxt;

	@FindBy(id="addTrx:menu1")
	WebElement transactiontypelist;

	@FindBy(id="addTrx:trxclassType")
	WebElement trxclassificationlist;

	@FindBy(id="addTrx:amountInput")
	WebElement transactionamounttxt;

	@FindBy(id="addTrx:trxCurr")
	WebElement transactioncurrencylist;

	@FindBy(id="addTrx:trxDate")
	WebElement exchangeratedatetxt;

	@FindBy(id="addTrx:description")
	WebElement descriptiontxt;

	@FindBy(id="addTrx:add")
	WebElement addbtn;

	@FindBy(id="addTrx:reset")
	WebElement resetbtn;

	@FindBy(id="confirmTransaction:confirmBtn")
	WebElement confirmbtn;

	@FindBy(id="confirmTransaction:backBtn")
	WebElement backbtn;

	@FindBy(id="confirmTransaction:cancelBtn")
	WebElement cancelbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	//All Common fields method
	public void  AddAccountTrxCommonFields(AccountTrxPojo addaccounttrxobj)
	{
		System.out.println(addaccounttrxobj.getCsp());
		if (!addaccounttrxobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(addaccounttrxobj.getCsp());
		}
		System.out.println(addaccounttrxobj.getAccountcode());
		accountcodetxt.clear();
		accountcodetxt.sendKeys(addaccounttrxobj.getAccountcode());

		System.out.println(addaccounttrxobj.getTransactiontype());
		if (!addaccounttrxobj.getTransactiontype().isEmpty())
		{
			new Select(transactiontypelist).selectByVisibleText(addaccounttrxobj.getTransactiontype());
			if (!addaccounttrxobj.getClassification().isEmpty())
			{	
				new Select(trxclassificationlist).selectByVisibleText(addaccounttrxobj.getClassification());
			}

		}

		System.out.println(addaccounttrxobj.getAmount());
		transactionamounttxt.clear();
		transactionamounttxt.sendKeys(addaccounttrxobj.getAmount());

		System.out.println(addaccounttrxobj.getCurrency());
		if (!addaccounttrxobj.getCurrency().isEmpty())
		{
			new Select(transactioncurrencylist).selectByVisibleText(addaccounttrxobj.getCurrency());
		}

		System.out.println(addaccounttrxobj.getDate());
		if (!addaccounttrxobj.getDate().isEmpty())
		{
			exchangeratedatetxt.clear();
			exchangeratedatetxt.sendKeys(addaccounttrxobj.getDate());
		}
		descriptiontxt.clear();
		descriptiontxt.sendKeys(addaccounttrxobj.getDescription());
	}

	// Collecting Success messages and error Messages
	public String addAccountTrxErrorMessagesAndSuccessMessage(AccountTrxPojo addaccounttrxobj) 
	{
		actual = "";
		// Collecting Data after reset to be asserted later
		if (addaccounttrxobj.getAction().contains("Reset")) 
		{
			actual = accountcodetxt.getText()+transactionamounttxt.getText()+descriptiontxt.getText();
		}
		//when added transaction table shows on web app it means that transaction is successfull
		else if (driver.findElements(By.id("addTrx:tableEx1:0:textSearchResults1")).size() != 0){
			actual = "Success";
		}
		// collecting error messages in a list 
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
	public void saveOrResetData(AccountTrxPojo addaccounttrxobj) {

		if (addaccounttrxobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (addaccounttrxobj.getAction().contains("Add")){
			addbtn.click();
			// Some Cases need confirmation and it can only happen after confirmation page shows
			if ( driver.getCurrentUrl().contains("ConfirmTransaction.faces"))
			{
				if (addaccounttrxobj.getAction().contains("Confirm")){
					confirmbtn.click();
				}
				else if (addaccounttrxobj.getAction().contains("Back")){
					backbtn.click();
				}
				else if (addaccounttrxobj.getAction().contains("Cancel")){
					cancelbtn.click();
				}
			}
		}

	}



	public void movetoaddaccounttrxpage () {

		WebElement hover = driver.findElement(By.linkText("Financial Activities"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Add Account Trx"));
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

