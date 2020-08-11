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



public class ConfigreCspOverDraftClassFactorsPage {
	WebDriver driver;

	public ConfigreCspOverDraftClassFactorsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="ConfigureCSPClassOverdraftFactor:SearchCSPBranchList")
	WebElement csplist;
	@FindBy(xpath="//td[@class='grid']//span")
	List<WebElement> classesLst;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:savebtn")
	WebElement savebtn;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:resetBtn")
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
		feesobj.setNumberOfClasses(classesLst.size());
		if (!feesobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(feesobj.getCsp());
		}

		for (int i =0 ; i < classesLst.size(); i++)
		{
			if(i == 0)
			{
				feesobj.setAccountclass(feesobj.getFees().get(0).getAccountclass());
				feesobj.setFactor(feesobj.getFees().get(0).getFactor());
				feesobj.setSat(feesobj.getFees().get(0).getSat());
				feesobj.setSun(feesobj.getFees().get(0).getSun());
				feesobj.setMon(feesobj.getFees().get(0).getMon());
				feesobj.setTue(feesobj.getFees().get(0).getTue());
				feesobj.setWed(feesobj.getFees().get(0).getWed());
				feesobj.setThu(feesobj.getFees().get(0).getThu());
				feesobj.setFri(feesobj.getFees().get(0).getFri());
			}
			else if(i == 1)
			{
				feesobj.setAccountclass(feesobj.getFeesA().get(0).getAccountclass());
				feesobj.setFactor(feesobj.getFeesA().get(0).getFactor());
				feesobj.setSat(feesobj.getFeesA().get(0).getSat());
				feesobj.setSun(feesobj.getFeesA().get(0).getSun());
				feesobj.setMon(feesobj.getFeesA().get(0).getMon());
				feesobj.setTue(feesobj.getFeesA().get(0).getTue());
				feesobj.setWed(feesobj.getFeesA().get(0).getWed());
				feesobj.setThu(feesobj.getFeesA().get(0).getThu());
				feesobj.setFri(feesobj.getFeesA().get(0).getFri());
			}
			else if(i == 2)
			{
				feesobj.setAccountclass(feesobj.getFeesB().get(0).getAccountclass());
				feesobj.setFactor(feesobj.getFeesB().get(0).getFactor());
				feesobj.setSat(feesobj.getFeesB().get(0).getSat());
				feesobj.setSun(feesobj.getFeesB().get(0).getSun());
				feesobj.setMon(feesobj.getFeesB().get(0).getMon());
				feesobj.setTue(feesobj.getFeesB().get(0).getTue());
				feesobj.setWed(feesobj.getFeesB().get(0).getWed());
				feesobj.setThu(feesobj.getFeesB().get(0).getThu());
				feesobj.setFri(feesobj.getFeesB().get(0).getFri());
			}

			WebElement factorTxt = driver.findElement
			(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":valueInId"));
			factorTxt.clear();
			factorTxt.sendKeys(feesobj.getFactor());
			
			WebElement satTxt = driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":saturdayValueInId"));
			satTxt.clear();
			satTxt.sendKeys(feesobj.getSat());
			
			WebElement sunTxt = 
					driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":sundayValueInId"));
			sunTxt.clear();
			sunTxt.sendKeys(feesobj.getSun());
			
			WebElement monTxt = 
					driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":mondayValueInId"));
			monTxt.clear();
			monTxt.sendKeys(feesobj.getMon());
			
			WebElement tueTxt = 
					driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":tuesdayValueInId"));
			tueTxt.clear();
			tueTxt.sendKeys(feesobj.getTue());
			
			WebElement wedTxt = 
					driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":wednesValueInId"));
			wedTxt.clear();	
			wedTxt.sendKeys(feesobj.getWed());
			
			WebElement thuTxt = 
					driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":thursdayValueInId"));
			thuTxt.clear();
			thuTxt.sendKeys(feesobj.getThu());
			
			WebElement friTxt = 
					driver.findElement(By.id("ConfigureCSPClassOverdraftFactor:CSPClassFactors:"+i+":fridayValueInId"));
			friTxt.clear();
			friTxt.sendKeys(feesobj.getFri());
		}
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
		else if ( driver.findElements(By.className("alert")).size() != 0)
		{

			actual = driver.findElement(By.className("alert")).getText();	
		}
		return actual;
	}

	public void hovertopage () 
	{
		WebElement hover = driver.findElement(By.linkText("Basic Definitions"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Configure CSP Overdraft Factor"));
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

