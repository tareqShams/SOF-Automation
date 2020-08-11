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
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:textPoolAccount")
	WebElement poolAccount;


	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:cspList")
	WebElement csp;

	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:btcList")
	WebElement btc;
	
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:oneAction")
	WebElement oneActionBtn;
	
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:sameAction")
	WebElement actionSynchBtn;

	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:sameAction")
	WebElement sameActionBtn;
	
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:debitOrCredit:0")
	WebElement poolActionBothBtn;
	
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:debitOrCredit:1")
	WebElement poolActionDebitBtn;
	
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:debitOrCredit:2")
	WebElement poolActionCreditBtn;
	
	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:saveBtn")
	WebElement save;

	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:backBtn")
	WebElement back;

	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:resetBtn")
	WebElement reset;

	@FindBy(id="EditCSP_BTC_POOL_ACCOUNT:deletesingleItem")
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
		if(CspBtcPoolAccountObj.getFlag().contains("One"))
		{
			oneActionBtn.click();
		}
		if(CspBtcPoolAccountObj.getFlag().contains("Synch"))
		{
			actionSynchBtn.click();
		}
		if(CspBtcPoolAccountObj.getFlag().contains("Same"))
		{
			sameActionBtn.click();
		}
		if(CspBtcPoolAccountObj.getFlag().contains("Both"))
		{
			poolActionBothBtn.click();
		}
		else if(CspBtcPoolAccountObj.getFlag().contains("Debit"))
		{
			poolActionDebitBtn.click();
		}
		else if(CspBtcPoolAccountObj.getFlag().contains("Credit"))
		{
			poolActionCreditBtn.click();
		}

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
