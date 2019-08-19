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



public class AddCreditTrxPage {
	WebDriver driver;

	public AddCreditTrxPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}




	@FindBy(id="addCreditTrx:accountCode")
	WebElement accountcodetxt;

	@FindBy(id="addCreditTrx:corporate")
	WebElement csplist;

	@FindBy(id="addCreditTrx:bankTerminalNo")
	WebElement bankterminalnotxt;

	@FindBy(id="addCreditTrx:lastFourDisgits")
	WebElement lastfourdigitstxt;

	@FindBy(id="addCreditTrx:issuerBankId")
	WebElement issueridtxt;

	@FindBy(id="addCreditTrx:expiryDate")
	WebElement expirydatetxt;

	@FindBy(id="addCreditTrx:alias")
	WebElement aliastxt;

	@FindBy(id="addCreditTrx:transactionType")
	WebElement transactiontypelist;

	@FindBy(id="addCreditTrx:transactionAmount")
	WebElement transactionamounttxt;

	@FindBy(id="addCreditTrx:accountType")
	WebElement accounttypelist;

	@FindBy(id="addCreditTrx:trxclassType")
	WebElement transactionclassificationlist;

	@FindBy(id="addCreditTrx:description")
	WebElement descriptiontxt;
	
	
	@FindBy(id="addCreditTrx:add")
	WebElement addbtn;

	@FindBy(id="addCreditTrx:reset")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  addCreditTrxCommonFields(AccountTrxPojo searchaccounttrxobj)
	{
		accountcodetxt.clear();
		accountcodetxt.sendKeys(searchaccounttrxobj.getAccountcode());

		if (!searchaccounttrxobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(searchaccounttrxobj.getCsp());
		}
		
		bankterminalnotxt.clear();
		bankterminalnotxt.sendKeys(searchaccounttrxobj.getBankterminalnum());
	
	lastfourdigitstxt.clear();
	lastfourdigitstxt.sendKeys(searchaccounttrxobj.getLastfourdigits());

	issueridtxt.clear();
	issueridtxt.sendKeys(searchaccounttrxobj.getIssuerid());
	
	expirydatetxt.clear();
	expirydatetxt.sendKeys(searchaccounttrxobj.getDate());

	aliastxt.clear();
	aliastxt.sendKeys(searchaccounttrxobj.getAlias());

	if (!searchaccounttrxobj.getTransactiontype().isEmpty())
	{
		new Select(transactiontypelist).selectByVisibleText(searchaccounttrxobj.getTransactiontype());
	}
	
	transactionamounttxt.clear();
	transactionamounttxt.sendKeys(searchaccounttrxobj.getAmount());

	if(!searchaccounttrxobj.getAccounttype().isEmpty())
	{
		new Select(accounttypelist).selectByVisibleText(searchaccounttrxobj.getAccounttype());
	}
	
	if(!searchaccounttrxobj.getClassification().isEmpty())
	{
		new Select(transactionclassificationlist).selectByVisibleText(searchaccounttrxobj.getClassification());
	}
	
	descriptiontxt.clear();
	descriptiontxt.sendKeys(searchaccounttrxobj.getDescription());
	
	}



	//Saving or Resetting Data
	public void saveOrResetData(AccountTrxPojo srchaccounttrxobj) {

		if (srchaccounttrxobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (srchaccounttrxobj.getAction().contains("Add"))
		{
			addbtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String addCreditTrxErrorMessagesAndSuccessMessage(AccountTrxPojo srchaccounttrxobj) 
	{
		actual = "";
		if (driver.findElements(By.className("fieldError")).size() != 0  )
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

	public void movetoaddcredittrxpage () {

		WebElement hover = driver.findElement(By.linkText("Financial Activities"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Add Credit Card Trx"));
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

