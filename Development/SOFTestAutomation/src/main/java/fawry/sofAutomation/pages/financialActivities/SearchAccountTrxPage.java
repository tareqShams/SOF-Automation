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



public class SearchAccountTrxPage {
	WebDriver driver;

	public SearchAccountTrxPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="Search Transaction")
	WebElement searchtrxpagebtn;

	@FindBy(id="searchTrx:btcList")
	WebElement billtypelist;

	@FindBy(id="searchTrx:primaryAcctSelect")
	WebElement primaryaccbtn;

	@FindBy(id="searchTrx:subAcctSelect")
	WebElement subaccbtn;

	@FindBy(id="searchTrx:textSearchPOSID1")
	WebElement accountcodetxt;

	@FindBy(id="searchTrx:corporate")
	WebElement csplist;

	@FindBy(id="searchTrx:textSearchFromDate1")
	WebElement fromdatetxt;

	@FindBy(id="searchTrx:textSearchToDate1")
	WebElement todate;

	@FindBy(id="searchTrx:merchantCode")
	WebElement merchantcode;

	@FindBy(id="searchTrx:sofTrxRefNum")
	WebElement softrxrefnumtxt;

	@FindBy(id="searchTrx:FCRN")
	WebElement fawrycustomerrefnumtxt;

	@FindBy(id="searchTrx:status")
	WebElement terminalstatuslist;

	@FindBy(id="searchTrx:listbox1")
	WebElement transactiontypelist;

	@FindBy(id="searchTrx:ReversalFCRN")
	WebElement reversalcustomerrefnumtxt;

	@FindBy(id="searchTrx:usage")
	WebElement usagelist;

	@FindBy(id="searchTrx:button1")
	WebElement searchbtn;

	@FindBy(id="searchTrx:button2")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  SearchAccounttrxSearchFields(AccountTrxPojo searchaccounttrxobj)
	{
		
		searchtrxpagebtn.click();
		resetbtn.click();
		
		if (!searchaccounttrxobj.getBilltypecode().isEmpty())
		{
			new Select(billtypelist).selectByVisibleText(searchaccounttrxobj.getBilltypecode());
		}
		if (searchaccounttrxobj.getAccounttype().equalsIgnoreCase("Primary"))
		{
			primaryaccbtn.click();
		}
		if (searchaccounttrxobj.getAccounttype().equalsIgnoreCase("Sub"))
		{
			subaccbtn.click();
		}
	
		accountcodetxt.clear();
		accountcodetxt.sendKeys(searchaccounttrxobj.getAccountcode());
		
		if (!searchaccounttrxobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(searchaccounttrxobj.getCsp());
		}
		
		fromdatetxt.clear();
		fromdatetxt.sendKeys(searchaccounttrxobj.getFromdate());

		todate.clear();
		todate.sendKeys(searchaccounttrxobj.getTodate());

		merchantcode.clear();
		merchantcode.sendKeys(searchaccounttrxobj.getMerchant());

		softrxrefnumtxt.clear();
		softrxrefnumtxt.sendKeys(searchaccounttrxobj.getSoftrxrefnum());

		fawrycustomerrefnumtxt.clear();
		fawrycustomerrefnumtxt.sendKeys(searchaccounttrxobj.getFawyCustomerrefnum());

		if(!searchaccounttrxobj.getTerminalstatus().isEmpty())
		{
			new Select(terminalstatuslist).selectByVisibleText(searchaccounttrxobj.getTerminalstatus());
		}
		if(!searchaccounttrxobj.getTransactiontype().isEmpty())
		{
			new Select(transactiontypelist).selectByVisibleText(searchaccounttrxobj.getTransactiontype());
		}

		reversalcustomerrefnumtxt.clear();
		reversalcustomerrefnumtxt.sendKeys(searchaccounttrxobj.getRevcustomerrefnum());

		if(!searchaccounttrxobj.getUsage().isEmpty())
		{
			new Select(usagelist).selectByVisibleText(searchaccounttrxobj.getUsage());
		}
	}



	//Saving or Resetting Data
	public void searchOrResetData(AccountTrxPojo srchaccounttrxobj) {

		if (srchaccounttrxobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (srchaccounttrxobj.getAction().contains("Search"))
		{
			searchbtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String srchAccountTrxErrorMessagesAndSuccessMessage(AccountTrxPojo srchaccounttrxobj) 
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

	public void movetoSearchaccounttrxpage () {

		WebElement hover = driver.findElement(By.linkText("Financial Activities"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Account Trx"));
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

