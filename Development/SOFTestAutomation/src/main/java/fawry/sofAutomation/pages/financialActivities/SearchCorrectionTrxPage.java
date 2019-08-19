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

import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;



public class SearchCorrectionTrxPage {
	WebDriver driver;

	public SearchCorrectionTrxPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="Search Correction Transaction")
	WebElement searchcorrpagebtn;

	@FindBy(id="searchCorrectionTrx:FCRN")
	WebElement fcrntxt;
	
	@FindBy(id="searchCorrectionTrx:button1")
	WebElement searchbtn;

	@FindBy(id="searchCorrectionTrx:button2")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  searchCorrectiontrxSearchField(AccountTrxPojo searchcorrtrxobj)
	{
		
		searchcorrpagebtn.click();
		resetbtn.click();
		
		fcrntxt.clear();
		fcrntxt.sendKeys(searchcorrtrxobj.getFawyCustomerrefnum());
		}



	//Saving or Resetting Data
	public void searchOrResetData(AccountTrxPojo searchcorrtrxobj) {

		if (searchcorrtrxobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (searchcorrtrxobj.getAction().contains("Search"))
		{
			searchbtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String srchAccountTrxErrorMessagesAndSuccessMessage(AccountTrxPojo searchcorrtrxobj) 
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

