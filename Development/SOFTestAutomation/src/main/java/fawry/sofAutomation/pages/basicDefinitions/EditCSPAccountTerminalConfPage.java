package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspAccountTerminalConfPojo;


public class EditCSPAccountTerminalConfPage extends MainPage{
	WebDriver driver;

	public EditCSPAccountTerminalConfPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="editCSPAccTypeConf:editCode")
	WebElement cspCode;

	@FindBy(id="editCSPAccTypeConf:menuTerminalType1")
	WebElement terminalType;

	@FindBy(id="editCSPAccTypeConf:textmaxSequentialNumber")
	WebElement maxSequentialNumber;

	@FindBy(id="editCSPAccTypeConf:textpinRegularExperssion")
	WebElement pinRegularExperssion;

	@FindBy(id="editCSPAccTypeConf:forcePinChange")
	WebElement forcePinChange;

	@FindBy(id="editCSPAccTypeConf:autoGenPin")
	WebElement autoGenPin;

	@FindBy(id="editCSPAccTypeConf:validatePin")
	WebElement validatePin;

	@FindBy(id="editCSPAccTypeConf:pinEncryptionEnabled")
	WebElement pinEncryptionEnabled;

	@FindBy(id="editCSPAccTypeConf:allowAnonymousAccounts")
	WebElement allowAnonymousAccounts;

	@FindBy(id="editCSPAccTypeConf:regTerminalsInCustReg")
	WebElement regTerminalsInCustReg;

	@FindBy(id="editCSPAccTypeConf:validateDeviceId")
	WebElement validateDeviceId;

	@FindBy(id="editCSPAccTypeConf:checkPinHistory")
	WebElement checkPinHistory;

	@FindBy(id="editCSPAccTypeConf:saveBtn")
	WebElement save;

	@FindBy(id="editCSPAccTypeConf:resetBtn")
	WebElement reset;

	@FindBy(id="editCSPAccTypeConf:delete")
	WebElement delete;

	@FindBy(id="editCSPAccTypeConf:backBtn")
	WebElement back;

	@FindBy(className="alert")
	List<WebElement>  massage;

	@FindBy(className="fieldError")
	List<WebElement>  error;

	

	public String  EditCSPAccountTerminalConf(CspAccountTerminalConfPojo editCSPAccountTerminalConfObj)	
	{

		if(!editCSPAccountTerminalConfObj.getMaxSequentialNumber().isEmpty())
		{
			maxSequentialNumber.sendKeys(editCSPAccountTerminalConfObj.getMaxSequentialNumber());
		}
		if(!editCSPAccountTerminalConfObj.getPinEncryptionEnabled().isEmpty())
		{
			pinEncryptionEnabled.sendKeys(editCSPAccountTerminalConfObj.getPinEncryptionEnabled());
		}
		if(!editCSPAccountTerminalConfObj.getForcePinChange().isEmpty())
		{
			forcePinChange.click();
		}
		if(!editCSPAccountTerminalConfObj.getAutoGenPin().isEmpty())
		{
			autoGenPin.click();
		}
		if(!editCSPAccountTerminalConfObj.getValidatePIN().isEmpty())
		{
			validatePin.click();
		}
		if(!editCSPAccountTerminalConfObj.getPinEncryptionEnabled().isEmpty())
		{
			pinEncryptionEnabled.click();
		}
		if(!editCSPAccountTerminalConfObj.getAllowAnonymousAccounts().isEmpty())
		{
			allowAnonymousAccounts.click();
		}

		if(!editCSPAccountTerminalConfObj.getRegTerminalsInCustReg().isEmpty())
		{
			regTerminalsInCustReg.click();
		}
		if(!editCSPAccountTerminalConfObj.getValidateDeviceId().isEmpty())
		{
			validateDeviceId.click();
		}
		if(!editCSPAccountTerminalConfObj.getCheckPinHistory().isEmpty())
		{
			checkPinHistory.click();
		}


		if(editCSPAccountTerminalConfObj.getFlag().equalsIgnoreCase("save"))
		{
			save.click();
			String msg;
			if (error.size()!=0) {
				msg = error.get(0).getText().toString();
				for(int i=1;i<error.size();i++)
				{
					msg=msg+"/"+error.get(i).getText().toString();
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
		else if(editCSPAccountTerminalConfObj.getFlag().equalsIgnoreCase("delete"))
		{
			delete.click();
			Alert alert=driver.switchTo().alert();
			alert.accept();
			return massage.get(0).getText().toString();
		}
		else if(editCSPAccountTerminalConfObj.getFlag().equalsIgnoreCase("back"))
		{
			back.click();
			return "back";
		}
		else if(editCSPAccountTerminalConfObj.getFlag().equalsIgnoreCase("reset")){
			reset.click();
			return "reset";
		}
		return "fail";
	}




}
