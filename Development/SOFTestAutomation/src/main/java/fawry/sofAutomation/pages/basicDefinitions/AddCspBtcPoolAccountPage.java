package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;
import fawry.sofAutomation.pages.admin.MainPage;

public class AddCspBtcPoolAccountPage  extends MainPage{
	WebDriver driver;

	public AddCspBtcPoolAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:cspList")
	WebElement csp;

	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:btcList")
	WebElement btc;

	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:textPoolAccount")
	WebElement poolAccount;
	
	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:oneAction")
	WebElement oneActionBtn;
	
	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:actionSynchronized")
	WebElement actionSynchBtn;

	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:sameAction")
	WebElement sameActionBtn;
	
	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:debitOrCredit:0")
	WebElement poolActionBothBtn;
	
	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:debitOrCredit:1")
	WebElement poolActionDebitBtn;
	
	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:debitOrCredit:2")
	WebElement poolActionCreditBtn;
	
	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:saveBtn")
	WebElement save;

	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:resetBtn")
	WebElement reset;

	@FindBy(className="alert")
	List<WebElement>  correctMassage;

	@FindBy(id="AddCSP_BTC_POOL_ACCOUNT:errorMessage")
	List<WebElement> errorMassage;

	@FindBy(className="fieldError")
	List<WebElement> fieldError;

	
	public String  addCspBtcPoolAccount(CspBtcPoolAccountPojo CspBtcPoolAccountObj) throws InterruptedException	
	{
		Select select = new Select( csp);
		Select select1 = new Select( btc);
		
		Actions builder = new Actions(driver);
		Actions builder2 = new Actions(driver);
		
		for(int i=0 ; i<select.getOptions().size();i++)
		{
			if(select.getOptions().get(i).getText().contains(CspBtcPoolAccountObj.getCsp()))
			{
				builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i));
				builder.build().perform(); 
				break;
			}
		}
		for(int i=0 ; i<select1.getOptions().size();i++)
		{
			if(select1.getOptions().get(i).getText().contains(CspBtcPoolAccountObj.getBillTypeCode()))
			{
				builder2.keyDown(Keys.CONTROL).click(select1.getOptions().get(i)).keyUp(Keys.CONTROL);
				builder2.build().perform(); 
				break;
			}
		}
		Thread.sleep(2000);
		System.out.println(CspBtcPoolAccountObj.getPoolAccount());
		poolAccount.clear();
		poolAccount.sendKeys(CspBtcPoolAccountObj.getPoolAccount());
		if(poolAccount.getText().isEmpty())
		{
		poolAccount.sendKeys(CspBtcPoolAccountObj.getPoolAccount());
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
		
		if(CspBtcPoolAccountObj.getFlag().contains("Save"))
		{
			save.click();
			String msg;
			if (errorMassage.size()!=0) {
				msg = errorMassage.get(0).getText().toString();
				for(int i=1;i<errorMassage.size();i++)
				{
					msg=msg+"/"+errorMassage.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}
			else if(fieldError.size()!=0)
			{
				System.out.println(fieldError.get(0).getText().toString());
				return fieldError.get(0).getText().toString();

			}

			else if (correctMassage.size()!=0)
			{
				System.out.println(correctMassage.get(0).getText().toString());
				return correctMassage.get(0).getText().toString();
			}	
		}
		else if (CspBtcPoolAccountObj.getFlag().contains("Reset")) {
			reset.click();
			return "reset";
		}
		else  {
			return "false";
		}
		return null;


	}

}
