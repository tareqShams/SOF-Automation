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



public class SearchCreditTrxPage {
	WebDriver driver;

	public SearchCreditTrxPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchCreditTrx:bankTerminalNo")
	WebElement bankterminalnotxt;

	@FindBy(id="searchCreditTrx:fawryCustRefNum")
	WebElement fcrntxt;

	@FindBy(id="searchCreditTrx:fromDate")
	WebElement fromdatetxt;

	@FindBy(id="searchCreditTrx:toDate")
	WebElement todatetxt;

	@FindBy(id="searchCreditTrx:authenticationId")
	WebElement authenticationidtxt;

	@FindBy(id="searchCreditTrx:reconciliationStatus")
	WebElement reconciliationstatuslist;

	@FindBy(id="searchCreditTrx:actionCommand")
	WebElement actioncommandlist;

	@FindBy(id="searchCreditTrx:TrxTypes")
	WebElement transactiontypelist;

	@FindBy(id="searchCreditTrx:POSCode")
	WebElement accountcodetxt;

	@FindBy(id="searchCreditTrx:BillingAcct")
	WebElement billingaccounttxt;

	@FindBy(id="searchCreditTrx:corporate")
	WebElement csplist;

	@FindBy(id="searchCreditTrx:UserName")
	WebElement usernametxt;

	@FindBy(id="searchCreditTrx:CustomerIP")
	WebElement customeriptxt;

	@FindBy(id="searchCreditTrx:IssuerID")
	WebElement issueridtxt;

	@FindBy(id="searchCreditTrx:searchBtn")
	WebElement searchbtn;

	@FindBy(id="searchCreditTrx:reset")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());

	//Insert Data into Fields
	public void  searchCredittrxMainFields(AccountTrxPojo searchcredittrxobj)
	{
		resetbtn.click();

		bankterminalnotxt.clear();
		bankterminalnotxt.sendKeys(searchcredittrxobj.getBankterminalnum());

		fcrntxt.clear();
		fcrntxt.sendKeys(searchcredittrxobj.getFawyCustomerrefnum());

		fromdatetxt.clear();
		fromdatetxt.sendKeys(searchcredittrxobj.getFromdate());

		todatetxt.clear();
		todatetxt.sendKeys(searchcredittrxobj.getTodate());

		authenticationidtxt.clear();
		authenticationidtxt.sendKeys(searchcredittrxobj.getAuthenticationid());

		if (!searchcredittrxobj.getReconciliation().isEmpty())
		{
			new Select(reconciliationstatuslist).selectByVisibleText(searchcredittrxobj.getReconciliation());
		}

		if (!searchcredittrxobj.getActioncommand().isEmpty())
		{
			new Select(actioncommandlist).selectByVisibleText(searchcredittrxobj.getActioncommand());
		}

		if (!searchcredittrxobj.getTransactiontype().isEmpty())
		{
			new Select(transactiontypelist).selectByVisibleText(searchcredittrxobj.getTransactiontype());
		}

		accountcodetxt.clear();
		accountcodetxt.sendKeys(searchcredittrxobj.getAccountcode());

		billingaccounttxt.clear();
		billingaccounttxt.sendKeys(searchcredittrxobj.getBillingaccount());

		if (!searchcredittrxobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(searchcredittrxobj.getCsp());
		}

		usernametxt.clear();
		usernametxt.sendKeys(searchcredittrxobj.getUserName());

		customeriptxt.clear();
		customeriptxt.sendKeys(searchcredittrxobj.getCustomerip());

		issueridtxt.clear();
		issueridtxt.sendKeys(searchcredittrxobj.getIssuerid());

	}



	//Saving or Resetting Data
	public void searchOrResetData(AccountTrxPojo searchcredittrxobj) {

		if (searchcredittrxobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (searchcredittrxobj.getAction().contains("Search"))
		{
			searchbtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(AccountTrxPojo searchcredittrxobj) 
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

	public void movetoSearchcredittrxpage () {

		WebElement hover = driver.findElement(By.linkText("Financial Activities"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Credit Card Trx"));
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

