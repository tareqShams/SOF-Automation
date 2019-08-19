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

import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;



public class EditVelocityCriteriaPage {
	WebDriver driver;

	public EditVelocityCriteriaPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="editVelocityCriteria:velocityPeriod")
	WebElement velocityPeriodTxt;

	@FindBy(id="searchVelocity:csp")
	WebElement cspList;

	@FindBy(id="editVelocityCriteria:measureValue")
	WebElement measureValueTxt;

	@FindBy(id="searchVelocity:profilesResultsTable:0:textSearchResults1")
	WebElement firstaccountinsearchlink;

	@FindBy(id="searchVelocity:searchBtn")
	WebElement searchbtn;

	@FindBy(id="editVelocityCriteria:saveBtn")
	WebElement savebtn;
	
	@FindBy(id="editVelocityCriteria:resetBtn")
	WebElement editresetbtn;
	
	@FindBy(id="searchVelocity:resetBtn")
	WebElement searchresetbtn;
	
	@FindBy(id="editVelocityCriteria:delete")
	WebElement deletebtn;
	
	@FindBy(id="editVelocityCriteria:backBtn")
	WebElement backbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(id="correctMessage")
	WebElement successMsg;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  CommonFields(VelocityPojo velocityobj)
	{
		velocityobj.setResetbefore(velocityPeriodTxt.getText()+measureValueTxt.getText());

		velocityPeriodTxt.clear();
		velocityPeriodTxt.sendKeys(velocityobj.getPeriod());

		measureValueTxt.clear();
		measureValueTxt.sendKeys(velocityobj.getMeasureValue());

	}

	//Saving or Resetting Data
	public void saveOrResetData(VelocityPojo velocityobj) {

		if (velocityobj.getAction().contains("Reset")) 
		{
			editresetbtn.click();
			velocityobj.setResetafter(velocityPeriodTxt.getText()+measureValueTxt.getText());
		}
		else if (velocityobj.getAction().contains("Save"))
		{
			savebtn.click();
		}
		else if (velocityobj.getAction().contains("Back"))
		{
			backbtn.click();
		}
		else if (velocityobj.getAction().contains("Save"))
		{
			deletebtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(VelocityPojo velocityobj) 
	{
		actual = "";
		if (driver.findElements(By.id("correctMessage")).size() != 0  )
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
		else if (driver.getCurrentUrl().contains("SearchVelocityCriteria.faces") )
		{
			actual = "BackSuccess";
		}
		else if (velocityobj.getResetbefore().equals(velocityobj.getResetafter()) )
		{
			actual = "ResetSuccess";
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

		driver.navigate().to(Constants.SEARCH_VELOCITY_CRITERIA_URL);
		if(!velocityobj.getCsp().isEmpty())
		{
			System.out.println(velocityobj.getCsp());
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

