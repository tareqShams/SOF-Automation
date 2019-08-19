package fawry.sofAutomation.pages.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.admin.ResetPasswordPojo;

public class ResetPasswordPage extends MainPage{
	
	WebDriver driver;

	public ResetPasswordPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="resetPassword:UserNameLabel")
	WebElement username;
	
	@FindBy(id="resetPassword:newPasswordLabel")
	WebElement newpassword;
	
	@FindBy(id="resetPassword:confirmPasswordLabel")
	WebElement confirmnewpassword;
	
	@FindBy(id="resetPassword:forceSelect")
	WebElement enforce;
	
	@FindBy(id="resetPassword:button1")
	WebElement save;
	
	@FindBy(id="resetPassword:button2")
	WebElement reset;
	
	@FindBy(className="alert")
	WebElement correctmassage;
	
	@FindBy(className="fieldError")
	List<WebElement>  errors ;
	
	
	static public String msg= "" ;

	public String  resetPassword(ResetPasswordPojo resetObject)
	{
		
		
		username.sendKeys(resetObject.getUsername());
		newpassword.sendKeys(resetObject.getNewpassword());
		confirmnewpassword.sendKeys(resetObject.getNewpassword());
		if(resetObject.getEnforce().equalsIgnoreCase("uncheck"))
		{
			enforce.click();
			
		}
		
		if(resetObject.getFlag().equalsIgnoreCase("save"))
		{
			save.click();
			
			while(!errors.isEmpty())
			{

				msg = errors.get(0).getText().toString();
				for(int i=1;i<errors.size();i++)
				{
					msg=msg+"/"+errors.get(i).getText().toString();
				}
				break;

				
			}
			
			if(correctmassage.isDisplayed())
			{
				msg=correctmassage.getText();
				
			}
		
			return msg;
		}
		if(resetObject.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			return "reset";
		}
	
			
			return "fail";
		

		
	}
	
	public boolean isreset()
	{
		if(username.getAttribute("value").isEmpty()
				&&newpassword.getAttribute("value").isEmpty()
				&&confirmnewpassword.getAttribute("value").isEmpty())
		{
			return true;
		}

		return false;
	}
		
	}

