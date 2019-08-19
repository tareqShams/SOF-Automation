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


import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;



public class AddVelocityCriteriaPage {
	WebDriver driver;

	public AddVelocityCriteriaPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="addVelocityCriteria:velocityPeriod")
	WebElement velocityPeriodTxt;

	@FindBy(id="addVelocityCriteria:velocityPeriodUnit")
	WebElement periodUnitList;

	@FindBy(id="addVelocityCriteria:velocityMeasureType")
	WebElement measureTypeList;

	@FindBy(id="addVelocityCriteria:btcType")
	WebElement billTypeList;

	@FindBy(id="addVelocityCriteria:csp")
	WebElement cspList;

	@FindBy(id="addVelocityCriteria:velocityAction")
	WebElement velocityActionList;

	@FindBy(id="addVelocityCriteria:accountType")
	WebElement accountTypeList;

	@FindBy(id="addVelocityCriteria:measureValue")
	WebElement measureValueTxt;

	@FindBy(id="addVelocityCriteria:errorCode")
	WebElement errorCodeList;

	@FindBy(id="addVelocityCriteria:selectedCustomerCat")
	WebElement customerCategoryList;

	@FindBy(id="addVelocityCriteria:saveBtn")
	WebElement savebtn;

	@FindBy(id="addVelocityCriteria:resetBtn")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(id="addVelocityCriteria:correctMessage")
	WebElement successMsg;
	
	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  CommonFields(VelocityPojo velocityobj)
	{

		resetbtn.click();

		velocityPeriodTxt.clear();
		velocityPeriodTxt.sendKeys(velocityobj.getPeriod());
		
		if (!velocityobj.getPeriodUnit().isEmpty())
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
	
	if(!velocityobj.getCsp().isEmpty())
	{
		new Select(cspList).selectByVisibleText(velocityobj.getCsp());
	}
	
	if(!velocityobj.getVelocityAction().isEmpty())
	{
		new Select(velocityActionList).selectByVisibleText(velocityobj.getVelocityAction());
	}
	
	if(!velocityobj.getAccountType().isEmpty())
	{
		new Select(accountTypeList).selectByVisibleText(velocityobj.getAccountType());
	}
	
	measureValueTxt.clear();
	measureValueTxt.sendKeys(velocityobj.getMeasureValue());
	
	if(!velocityobj.getErrorCode().isEmpty())
	{
		new Select(errorCodeList).selectByVisibleText(velocityobj.getErrorCode());
	}
	
	if(!velocityobj.getCustomerCategory().isEmpty())
	{
		new Select(customerCategoryList).selectByVisibleText(velocityobj.getCustomerCategory());
	}
	
	}

	//Saving or Resetting Data
	public void saveOrResetData(VelocityPojo velocityobj) {

		if (velocityobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (velocityobj.getAction().contains("Save"))
		{
			savebtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(VelocityPojo velocityobj) 
	{
		actual = "";
		if (driver.findElements(By.id("addVelocityCriteria:correctMessage")).size() != 0  )
		{
			actual = successMsg.getText();
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
		WebElement selecthover = driver.findElement(By.linkText("Add Velocity Criteria"));
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

