package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;


public class EditCspPushAlertConfigPage extends MainPage{

	WebDriver driver;
	public EditCspPushAlertConfigPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

/*	@FindBy(id="editPSP:editCode")
	WebElement CSP;

	@FindBy(id="editPSP:btc")
	WebElement accountType;*/

	@FindBy(id="editCspPushAlertConfig:pushAlert")
	WebElement allowPushAlert;

	@FindBy(id="editCspPushAlertConfig:saveBtn")
	WebElement save;

	@FindBy(id="editCspPushAlertConfig:resetBtn")
	WebElement reset;

	@FindBy(id="editCspPushAlertConfig:delete")
	WebElement delete;

	@FindBy(id="editCspPushAlertConfig:backBtn")
	WebElement back;

	@FindBy(xpath="//*[@class='alert' or @class='error']")
	List<WebElement> Massage;

	
	public String  EditCspPushAlertConfig(CspPushAlertPojo CspPushAlertObj)	
	{
		if(!CspPushAlertObj.getAllowPushAlert().isEmpty())
		{
			allowPushAlert.click();
		}
		if (CspPushAlertObj.getFlag().equalsIgnoreCase("save"))
		{ 
			save.click();
			return Massage.get(0).getText();
		}
		else if (CspPushAlertObj.getFlag().equalsIgnoreCase("reset"))
		{ 
			reset.click();
			return "reset";
		}
		else if (CspPushAlertObj.getFlag().equalsIgnoreCase("delete"))
		{ 
			delete.click();
			Alert alert=driver.switchTo().alert();
			alert.accept();
			return Massage.get(0).getText();
		}
		else if (CspPushAlertObj.getFlag().equalsIgnoreCase("back"))
		{ 
			back.click();
			return "back";
		}
		return "fail";
	}
}
