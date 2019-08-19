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


import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;



public class SearchVelocityCriteriaPage {
	WebDriver driver;

	public SearchVelocityCriteriaPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="searchVelocity:csp")
	WebElement cspList;

	@FindBy(id="searchVelocity:velocityPeriodUnit")
	WebElement periodUnitList;

	@FindBy(id="searchVelocity:velocityMeasureType")
	WebElement measureTypeList;

	@FindBy(id="searchVelocity:btcType")
	WebElement billTypeList;
	
	@FindBy(id="searchVelocity:velocityAction")
	WebElement velocityActionList;
	
	@FindBy(id="searchVelocity:accountType")
	WebElement accountTypeList;

	@FindBy(id="searchVelocity:profilesResultsTable:0:textSearchResults1")
	WebElement firstaccountinsearchlink;
	
	@FindBy(id="searchVelocity:searchBtn")
	WebElement searchbtn;

	@FindBy(id="searchVelocity:resetBtn")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  CommonFields(VelocityPojo velocityobj)
	{

		resetbtn.click();

		if(!velocityobj.getCsp().isEmpty())
		{
			new Select(cspList).selectByVisibleText(velocityobj.getCsp());
		}
		if(!velocityobj.getPeriodUnit().isEmpty())
		{
			new Select(periodUnitList).selectByVisibleText(velocityobj.getPeriodUnit());
		}
		if(!velocityobj.getMeasureType().isEmpty())
		{
			new Select(measureTypeList).selectByVisibleText(velocityobj.getMeasureType());
		}
		if(!velocityobj.getBillType().isEmpty())
		{
			new Select(billTypeList).selectByVisibleText(velocityobj.getBillType());
		}
		if(!velocityobj.getVelocityAction().isEmpty())
		{
			new Select(velocityActionList).selectByVisibleText(velocityobj.getVelocityAction());
		}
		if(!velocityobj.getAccountType().isEmpty())
		{
			new Select(accountTypeList).selectByVisibleText(velocityobj.getAccountType());
		}
		

	}

	//Saving or Resetting Data
	public void saveOrResetData(VelocityPojo velocityobj) {

		if (velocityobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (velocityobj.getAction().contains("Search"))
		{
			searchbtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(VelocityPojo velocityobj) 
	{
		actual = "";
		if (driver.findElements(By.xpath("//*[@id=\"searchVelocity:profilesResultsTable\"]/tbody")).size() != 0  )
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
		WebElement selecthover = driver.findElement(By.linkText("Search Velocity Criteria"));
		action.moveToElement(selecthover);
		action.click();
		action.perform();
	}
	public void  moveToAccountToBeEdited(VelocityPojo velocityobj)
	{

		resetbtn.click();

		if(!velocityobj.getCsp().isEmpty())
		{
			new Select(cspList).selectByVisibleText(velocityobj.getCsp());
		}
		
		searchbtn.click();
		
		firstaccountinsearchlink.click();
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

