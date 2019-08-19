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

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:valueInId")
	WebElement BPlusFactor;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:saturdayValueInId")
	WebElement BPlusSat;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:sundayValueInId")
	WebElement BPlusSun;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:mondayValueInId")
	WebElement BPlusMon;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:tuesdayValueInId")
	WebElement BPlusTue;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:wednesValueInId")
	WebElement BPlusWed;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:thursdayValueInId")
	WebElement BPlusThu;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:0:fridayValueInId")
	WebElement BPlusFri;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:valueInId")
	WebElement CFactor;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:saturdayValueInId")
	WebElement CSat;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:sundayValueInId")
	WebElement CSun;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:mondayValueInId")
	WebElement CMon;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:tuesdayValueInId")
	WebElement CTue;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:wednesValueInId")
	WebElement CWed;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:thursdayValueInId")
	WebElement CThu;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:1:fridayValueInId")
	WebElement CFri;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:valueInId")
	WebElement BFactor;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:saturdayValueInId")
	WebElement BSat;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:sundayValueInId")
	WebElement BSun;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:mondayValueInId")
	WebElement BMon;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:tuesdayValueInId")
	WebElement BTue;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:wednesValueInId")
	WebElement BWed;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:thursdayValueInId")
	WebElement BThu;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:2:fridayValueInId")
	WebElement BFri;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:valueInId")
	WebElement APlusFactor;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:saturdayValueInId")
	WebElement APlusSat;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:sundayValueInId")
	WebElement APlusSun;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:mondayValueInId")
	WebElement APlusMon;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:tuesdayValueInId")
	WebElement APlusTue;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:wednesValueInId")
	WebElement APlusWed;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:thursdayValueInId")
	WebElement APlusThu;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:3:fridayValueInId")
	WebElement APlusFri;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:valueInId")
	WebElement AFactor;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:saturdayValueInId")
	WebElement ASat;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:sundayValueInId")
	WebElement ASun;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:mondayValueInId")
	WebElement AMon;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:tuesdayValueInId")
	WebElement ATue;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:wednesValueInId")
	WebElement AWed;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:thursdayValueInId")
	WebElement AThu;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:4:fridayValueInId")
	WebElement AFri;


	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:valueInId")
	WebElement CPlusFactor;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:saturdayValueInId")
	WebElement CPlusSat;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:sundayValueInId")
	WebElement CPlusSun;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:mondayValueInId")
	WebElement CPlusMon;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:tuesdayValueInId")
	WebElement CPlusTue;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:wednesValueInId")
	WebElement CPlusWed;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:thursdayValueInId")
	WebElement CPlusThu;

	@FindBy(id="ConfigureCSPClassOverdraftFactor:CSPClassFactors:5:fridayValueInId")
	WebElement CPlusFri;

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

		if (!feesobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(feesobj.getCsp());
		}

		bPlusValues(feesobj);
		cValues(feesobj);
		bValues(feesobj);
		aPlusValues(feesobj);
		aValues(feesobj);
		CPlusValues(feesobj);	
	}

	public void  bPlusValues(CSPFeesPojo feesobj)
	{
		BPlusFactor.clear();
		BPlusFactor.sendKeys(feesobj.getBPlusFactor());

		BPlusSat.clear();
		BPlusSat.sendKeys(feesobj.getBPlusSat());

		BPlusSun.clear();
		BPlusSun.sendKeys(feesobj.getBPlusSun());

		BPlusMon.clear();
		BPlusMon.sendKeys(feesobj.getBPlusMon());

		BPlusTue.clear();
		BPlusTue.sendKeys(feesobj.getBPlusTue());

		BPlusWed.clear();
		BPlusWed.sendKeys(feesobj.getBPlusWed());

		BPlusThu.clear();
		BPlusThu.sendKeys(feesobj.getBPlusThu());

		BPlusFri.clear();
		BPlusFri.sendKeys(feesobj.getBPlusFri());

	}

	public void  cValues(CSPFeesPojo feesobj)
	{
		CFactor.clear();
		CFactor.sendKeys(feesobj.getCFactor());

		CSat.clear();
		CSat.sendKeys(feesobj.getCSat());

		CSun.clear();
		CSun.sendKeys(feesobj.getCSun());

		CMon.clear();
		CMon.sendKeys(feesobj.getCMon());

		CTue.clear();
		CTue.sendKeys(feesobj.getCTue());

		CWed.clear();
		CWed.sendKeys(feesobj.getCWed());

		CThu.clear();
		CThu.sendKeys(feesobj.getCThu());

		CFri.clear();
		CFri.sendKeys(feesobj.getCFri());
	}

	public void  bValues(CSPFeesPojo feesobj)
	{
		BFactor.clear();
		BFactor.sendKeys(feesobj.getBFactor());

		BSat.clear();
		BSat.sendKeys(feesobj.getBSat());

		BSun.clear();
		BSun.sendKeys(feesobj.getBSun());

		BMon.clear();
		BMon.sendKeys(feesobj.getBMon());

		BTue.clear();
		BTue.sendKeys(feesobj.getBTue());

		BWed.clear();
		BWed.sendKeys(feesobj.getBWed());

		BThu.clear();
		BThu.sendKeys(feesobj.getBThu());

		BFri.clear();
		BFri.sendKeys(feesobj.getBFri());
	}
	
	public void  aPlusValues(CSPFeesPojo feesobj)
	{
		APlusFactor.clear();
		APlusFactor.sendKeys(feesobj.getAPlusFactor());

		APlusSat.clear();
		APlusSat.sendKeys(feesobj.getAPlusSat());

		APlusSun.clear();
		APlusSun.sendKeys(feesobj.getAPlusSun());

		APlusMon.clear();
		APlusMon.sendKeys(feesobj.getAPlusMon());

		APlusTue.clear();
		APlusTue.sendKeys(feesobj.getAPlusTue());

		APlusWed.clear();
		APlusWed.sendKeys(feesobj.getAPlusWed());

		APlusThu.clear();
		APlusThu.sendKeys(feesobj.getAPlusThu());

		APlusFri.clear();
		APlusFri.sendKeys(feesobj.getAPlusFri());

	}

	public void  aValues(CSPFeesPojo feesobj)
	{
		AFactor.clear();
		AFactor.sendKeys(feesobj.getAFactor());

		ASat.clear();
		ASat.sendKeys(feesobj.getASat());

		ASun.clear();
		ASun.sendKeys(feesobj.getASun());

		AMon.clear();
		AMon.sendKeys(feesobj.getAMon());

		ATue.clear();
		ATue.sendKeys(feesobj.getATue());

		AWed.clear();
		AWed.sendKeys(feesobj.getAWed());

		AThu.clear();
		AThu.sendKeys(feesobj.getAThu());

		AFri.clear();
		AFri.sendKeys(feesobj.getAFri());
	}
	
	
	public void  CPlusValues(CSPFeesPojo feesobj)
	{
		CPlusFactor.clear();
		CPlusFactor.sendKeys(feesobj.getCPlusFactor());

		CPlusSat.clear();
		CPlusSat.sendKeys(feesobj.getCPlusSat());

		CPlusSun.clear();
		CPlusSun.sendKeys(feesobj.getCPlusSun());

		CPlusMon.clear();
		CPlusMon.sendKeys(feesobj.getCPlusMon());

		CPlusTue.clear();
		CPlusTue.sendKeys(feesobj.getCPlusTue());

		CPlusWed.clear();
		CPlusWed.sendKeys(feesobj.getCPlusWed());

		CPlusThu.clear();
		CPlusThu.sendKeys(feesobj.getCPlusThu());

		CPlusFri.clear();
		CPlusFri.sendKeys(feesobj.getCPlusFri());
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

