package fawry.sofAutomation.pages.basicDefinitions;

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

import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;



public class ConfigreCspOverDraftFeesPage {
	WebDriver driver;

	public ConfigreCspOverDraftFeesPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="addCSPClassFees:cspList")
	WebElement csplist;
	
	@FindBy(id="addCSPClassFees:cspAccClassList")
	WebElement accountclasslist;
	
	@FindBy(id="addCSPClassFees:textFromValue")
	WebElement fromvaluetxt;
	
	@FindBy(id="addCSPClassFees:textToValue")
	WebElement tovaluetxt;
	
	@FindBy(id="addCSPClassFees:textFixesValue")
	WebElement fixedvaluetxt;
	
	@FindBy(id="addCSPClassFees:saveBtn")
	WebElement savebtn;

	@FindBy(id="addCSPClassFees:resetBtn")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  CommonFields(CSPFeesPojo feesobj)
	{

		resetbtn.click();
		
		if (!feesobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(feesobj.getCsp());
		}
		if (!feesobj.getAccountclass().isEmpty())
		{
			new Select(accountclasslist).selectByVisibleText(feesobj.getAccountclass());
		}
		
		fromvaluetxt.clear();
		fromvaluetxt.sendKeys(feesobj.getFromvalue());
		
		tovaluetxt.clear();
		tovaluetxt.sendKeys(feesobj.getTovalue());
		
		fixedvaluetxt.clear();
		fixedvaluetxt.sendKeys(feesobj.getFixedfees());
		
	}



	//Saving or Resetting Data
	public void saveOrResetData(CSPFeesPojo feesobj) {

		if (feesobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (feesobj.getAction().contains("Save"))
		{
			savebtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(CSPFeesPojo feesobj) 
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

	public void hovertopage () {

		WebElement hover = driver.findElement(By.linkText("Basic Definitions"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Configure CSP Overdraft Fees"));
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

