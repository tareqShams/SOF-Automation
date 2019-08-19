package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;

public class EditCspBtcPoolAccountPage extends MainPage{
	WebDriver driver;

	public EditCspBtcPoolAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="addCSP:textPoolAccount")
	WebElement poolAccount;


	@FindBy(id="addCSP:cspList")
	WebElement csp;

	@FindBy(id="addCSP:btcList")
	WebElement btc;

	@FindBy(id="addCSP:saveBtn")
	WebElement save;

	@FindBy(id="addCSP:backBtn")
	WebElement back;

	@FindBy(id="addCSP:resetBtn")
	WebElement reset;

	@FindBy(id="addCSP:deletesingleItem")
	WebElement delete;

	@FindBy(className="fieldError")
	List<WebElement>  fieldError;

	@FindBy(id="addCSP:errorMessage")
	List<WebElement> errorMassage;

	@FindBy(className="alert")
	List<WebElement> correctMessage;

	public String  setBillTypeCode;
	public String setCsp;
	public String code;

	public String  editCspBtcPoolAccount(CspBtcPoolAccountPojo CspBtcPoolAccountObj)	
	{
		code=poolAccount.getText();
		if(!CspBtcPoolAccountObj.getNewCode().isEmpty())
		{
			poolAccount.clear();
			poolAccount.sendKeys(CspBtcPoolAccountObj.getNewCode());
		}
		  setBillTypeCode =btc.getText();
		 setCsp= csp.getText();

		if(CspBtcPoolAccountObj.getFlag().equalsIgnoreCase("save"))
		{
			save.click();
			return correctMessage.get(0).getText();


		}
		else if(CspBtcPoolAccountObj.getFlag().equalsIgnoreCase("delete"))
		{
			delete.click();
			return correctMessage.get(0).getText();

		}
		else if(CspBtcPoolAccountObj.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();	
			return "reset";
		}
		else if(CspBtcPoolAccountObj.getFlag().equalsIgnoreCase("back"))
		{
			back.click();
			return  "back";
		}
		return null;


	}

	public boolean reset()
	{
		if(poolAccount.getText().equalsIgnoreCase(code))
		{
			return true;
		}
		return false;

	}

}
