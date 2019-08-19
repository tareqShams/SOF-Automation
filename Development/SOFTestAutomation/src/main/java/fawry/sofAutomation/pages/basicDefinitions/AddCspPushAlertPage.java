package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;

public class AddCspPushAlertPage extends MainPage {

	
	WebDriver driver;

	public AddCspPushAlertPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="addCspPushAlertConfig:csp")
	WebElement csp;

	@FindBy(id="addCspPushAlertConfig:accountType")
	WebElement accountType;

	@FindBy(id="addCspPushAlertConfig:pushAlert")
	WebElement allowPushAlert;

	@FindBy(id="addCspPushAlertConfig:saveBtn")
	WebElement save;

	@FindBy(id="addCspPushAlertConfig:resetBtn")
	WebElement reset;
	
	
	@FindBy(id="addCspPushAlertConfig:cancelBtn")
	WebElement cancel;
	
	
	@FindBy(className="fieldError")
	List<WebElement> fieldError;
	
	
	@FindBy(xpath="//*[@class='alert' or @class='error']")
	List<WebElement> massage;
	
	public String  AddCspPushAlert(CspPushAlertPojo addCspPushAlertObj)	
	{
		
		csp.sendKeys(addCspPushAlertObj.getCsp());
		accountType.sendKeys(addCspPushAlertObj.getAccountType());
		if(addCspPushAlertObj.getAllowPushAlert().equalsIgnoreCase("Y"))
		{
			allowPushAlert.click();
		}
		
		if(addCspPushAlertObj.getFlag().equalsIgnoreCase("save"))
		{
			save.click();
			String msg;
			if (fieldError.size()!=0) {
				msg = fieldError.get(0).getText().toString();
				for(int i=1;i<fieldError.size();i++)
				{
					msg=msg+"/"+fieldError.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}
			
			else if(massage.size()!=0)
			{
				System.out.println(massage.get(0).getText().toString());
				return massage.get(0).getText().toString();
			}
		}
		else if (addCspPushAlertObj.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			return "reset";
		}
		else if (addCspPushAlertObj.getFlag().equalsIgnoreCase("cancel"))
		{
			cancel.click();
			return "cancel";

		}
	
		
		return "fail";
	}
	
	
	public boolean reset()
	{
		Select csp1=new Select(csp);
		Select accountType1=new Select(accountType);
		
		if(csp1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
		&& accountType1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -"))
		{
			return true;
		}
		return false;
		
		
		
		
	}
	
	
	
}
