package fawry.sofAutomation.pages.basicDefinitions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.FinancePojo;



public class SearchFinanceProgramSetupPage {
	WebDriver driver;

	public SearchFinanceProgramSetupPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="searchTrx:finance")
	WebElement financeProglist;

	@FindBy(id="searchTrx:billingAccount")
	WebElement accountNumbertxt;

	@FindBy(id="searchTrx:btcList")
	WebElement btclist;

	@FindBy(id="searchTrx:financeSts")
	WebElement programStatuslist;

	@FindBy(xpath="//table[@id='ConfigureCSPSettingsForm:CreditNatureList']//tbody//tr")
	List<WebElement> creditnaturetable;

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


	public void  CommonFields(FinancePojo settingsobj)
	{

		resetbtn.click();

		if (!settingsobj.getFinanceprogname().isEmpty() && !settingsobj.getFinanceProgCode().isEmpty())
		{
			new Select(financeProglist).selectByVisibleText(settingsobj.getFinanceprogname() +" - "+settingsobj.getFinanceProgCode());
		}
		
		accountNumbertxt.clear();
		accountNumbertxt.sendKeys(settingsobj.getAccountNumber());

		if(!settingsobj.getBtc().isEmpty())
		{
			new Select(btclist).selectByVisibleText(settingsobj.getBtc());
		}
		if (!settingsobj.getStatus().isEmpty())
		{
			new Select(programStatuslist).selectByVisibleText(settingsobj.getStatus()); 
		}

	}

	//Saving or Resetting Data
	public void saveOrResetData(FinancePojo settingsobj) {

		if (settingsobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (settingsobj.getAction().contains("Search"))
		{
			searchbtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(FinancePojo settingsobj) 
	{
		actual = "";
		if (driver.findElements(By.xpath("//*[@id=\"searchTrx:tableEx1\"]/tbody")).size() != 0  )
		{
			actual = "Success";
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

	public void hovertopage () 
	{
		WebElement hover = driver.findElement(By.linkText("Basic Definitions"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Finance Program Setup"));
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

