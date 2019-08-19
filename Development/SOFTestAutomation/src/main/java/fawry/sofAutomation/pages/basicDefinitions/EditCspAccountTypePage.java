package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;

public class EditCspAccountTypePage {
	WebDriver driver;

	public EditCspAccountTypePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="editCSPAccTypeConf:editCode")
	WebElement csp;

	@FindBy(id="editCSPAccTypeConf:menuTerminalType1")
	WebElement accountType;

	@FindBy(id="editCSPAccTypeConf:CustomerProfileType")
	WebElement customerProfileType;

	@FindBy(id="editCSPAccTypeConf:balanceBtc")
	WebElement balanceBTC;

	@FindBy(id="editCSPAccTypeConf:paymentbtc")
	WebElement paymentBTC;

	@FindBy(id="editCSPAccTypeConf:aList")
	WebElement activationMethodParameter;

	@FindBy(id="editCSPAccTypeConf:textAccountCodePrefix")
	WebElement accountCodePrefix;

	@FindBy(id="editCSPAccTypeConf:textCancelExtremeBalanceAmount")
	WebElement cancelExtremeBalanceAmount;

	@FindBy(id="editCSPAccTypeConf:textMaxAcctNumber")
	WebElement maxNumberOfAccounts;

	@FindBy(id="editCSPAccTypeConf:textExcludedBtcs")
	WebElement excludedBTCS;

	@FindBy(id="editCSPAccTypeConf:addEnabled")
	WebElement addEnabled;

	@FindBy(id="editCSPAccTypeConf:deleteEnabled")
	WebElement deleteEnabled;

	@FindBy(id="editCSPAccTypeConf:fundLoadEnabled")
	WebElement fundLoadEnabled;

	@FindBy(id="editCSPAccTypeConf:balanceInqEnabled")
	WebElement balanceINQEnabled;

	@FindBy(id="editCSPAccTypeConf:pinRequired")
	WebElement pinRequired;

	@FindBy(id="editCSPAccTypeConf:allowAnonymousAccounts")
	WebElement allowAnonymousAccounts;

	@FindBy(id="editCSPAccTypeConf:updateEnabled")
	WebElement threeDSecuredEnabled;

	@FindBy(id="editCSPAccTypeConf:updateEnabled")
	WebElement updateEnabled;

	@FindBy(id="editCSPAccTypeConf:cashOutEnabled")
	WebElement cashOutEnabled;

	@FindBy(id="editCSPAccTypeConf:createEnabled")
	WebElement createEnabled;

	@FindBy(id="editCSPAccTypeConf:pmtEnabled")
	WebElement pmtEnabled;

	@FindBy(id="editCSPAccTypeConf:kycRequired")
	WebElement kycRequired;

	@FindBy(id="editCSPAccTypeConf:purchaseEnable")
	WebElement purchaseEnable;

	@FindBy(id="editCSPAccTypeConf:decryptAcctNumParameter")
	WebElement decryptAccountNumberParameter;

	@FindBy(id="editCSPAccTypeConf:saveBtn")
	WebElement save;

	@FindBy(id="editCSPAccTypeConf:resetBtn")
	WebElement reset;

	@FindBy(id="editCSPAccTypeConf:delete")
	WebElement delete;

	@FindBy(id="editCSPAccTypeConf:backBtn")
	WebElement back;

	@FindBy(className="fieldError")
	List<WebElement> fieldError;

	@FindBy(className="alert")
	List<WebElement> correctMessage;

	@FindBy(linkText="Add Activation Method Parameter")
	WebElement AddActivationMethodParameter;


	public String  editCspAccountType(CSPAccountTypePojo addCspAccountTypeObj)	
	{

		if(!addCspAccountTypeObj.getBalanceBTC().isEmpty())
			balanceBTC.sendKeys(addCspAccountTypeObj.getBalanceBTC());

		if(!addCspAccountTypeObj.getPaymentBTC().isEmpty())
			paymentBTC.sendKeys(addCspAccountTypeObj.getPaymentBTC());

		if(!addCspAccountTypeObj.getActivationMethodParameter().isEmpty())
		{
			if(addCspAccountTypeObj.getActivationMethodParameter().equalsIgnoreCase("new"))
			{
				AddActivationMethodParameter.click();
				AddActivationMethodParameterPage newActivationMethod=new AddActivationMethodParameterPage(driver);
				newActivationMethod.addActivationMethodParameter(addCspAccountTypeObj);
				activationMethodParameter.sendKeys(addCspAccountTypeObj.getCode());
			}
			else 
			{
				activationMethodParameter.sendKeys(addCspAccountTypeObj.getActivationMethodParameter());

			}
		}
		if(!addCspAccountTypeObj.getActivationMethodParameter().isEmpty())
			activationMethodParameter.sendKeys(addCspAccountTypeObj.getActivationMethodParameter());

		if(!addCspAccountTypeObj.getAccountCodePrefix().isEmpty())
		{
			accountCodePrefix.clear();
			accountCodePrefix.sendKeys(addCspAccountTypeObj.getAccountCodePrefix());
		}		
		if(!addCspAccountTypeObj.getCancelExtremeBalanceAmount().isEmpty())
		{
			cancelExtremeBalanceAmount.clear();
			cancelExtremeBalanceAmount.sendKeys(addCspAccountTypeObj.getCancelExtremeBalanceAmount());
		}
		if(!addCspAccountTypeObj.getMaxNumberOfAccounts().isEmpty())
		{
			maxNumberOfAccounts.clear();
			maxNumberOfAccounts.sendKeys(addCspAccountTypeObj.getMaxNumberOfAccounts());
		}
		if(!addCspAccountTypeObj.getExcludedBTCS().isEmpty())
		{
			excludedBTCS.clear();
			excludedBTCS.sendKeys(addCspAccountTypeObj.getExcludedBTCS());
		}
		if(!addCspAccountTypeObj.getAddEnabled().isEmpty())
		{
			addEnabled.click();
		}
		if(!addCspAccountTypeObj.getDeleteEnabled().isEmpty())
		{
			deleteEnabled.click();
		}
		if(!addCspAccountTypeObj.getFundLoadEnabled().isEmpty())
		{
			fundLoadEnabled.click();

		}
		if(!addCspAccountTypeObj.getBalanceINQEnabled().isEmpty())
		{
			balanceINQEnabled.click();
		}
		if(!addCspAccountTypeObj.getPinRequired().isEmpty())
		{
			pinRequired.click();
		}
		if(!addCspAccountTypeObj.getAllowAnonymousAccounts().isEmpty())
		{
			allowAnonymousAccounts.click();
		}

		if(!addCspAccountTypeObj.getThreeDSecuredEnabled().isEmpty())
		{
			threeDSecuredEnabled .click();
		}
		if(!addCspAccountTypeObj.getUpdateEnabled().isEmpty())
		{
			updateEnabled.click();
		}
		if(!addCspAccountTypeObj.getCashOutEnabled().isEmpty())
		{
			cashOutEnabled.click();
		}
		if(!addCspAccountTypeObj.getCreateEnabled().isEmpty())
		{
			createEnabled.click();
		}
		if(!addCspAccountTypeObj.getKycRequired().isEmpty())
		{
			kycRequired.click();
		}
		if(!addCspAccountTypeObj.getDecryptAccountNumberParameter().isEmpty())
		{
			decryptAccountNumberParameter.click();
		}
		if(!addCspAccountTypeObj.getPurchaseEnable().isEmpty())
		{
			purchaseEnable.click();
		}
		if(addCspAccountTypeObj.getFlag().contains("edit"))
		{
			save.click();
			String msg;

			if (correctMessage.size()!=0)
			{
				System.out.println(correctMessage.get(0).getText().toString());
				return correctMessage.get(0).getText().toString();
			}	
			else if (fieldError.size()!=0) {
				msg = fieldError.get(0).getText().toString();
				for(int i=1;i<fieldError.size();i++)
				{
					msg=msg+"/"+fieldError.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}
		}
		else if(addCspAccountTypeObj.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			return "reset";
		}
		else if(addCspAccountTypeObj.getFlag().equalsIgnoreCase("delete"))
		{
			delete.click();
			Alert alert=driver.switchTo().alert();
			alert.accept();
			System.out.println(correctMessage.get(0).getText().toString());

			return correctMessage.get(0).getText().toString();
		}
		else if(addCspAccountTypeObj.getFlag().equalsIgnoreCase("back"))
		{
			back.click();
			return "back";
		}

		return "fail";

	}
	
	
}
