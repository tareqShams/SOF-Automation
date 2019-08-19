package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;

public class AddCSPAccountTypePage extends MainPage {
	WebDriver driver;

	public AddCSPAccountTypePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="addCSP_ACCType:cspList")
	WebElement csp;

	@FindBy(id="addCSP_ACCType:cspAccTypesList")
	WebElement accountType;

	@FindBy(id="addCSP_ACCType:CustomerProfileTypeList")
	WebElement customerProfileType;

	@FindBy(id="addCSP_ACCType:btcBalanceList")
	WebElement balanceBTC;

	@FindBy(id="addCSP_ACCType:btcPaymentList")
	WebElement paymentBTC;

	@FindBy(id="addCSP_ACCType:aList")
	WebElement activationMethodParameter;

	@FindBy(id="addCSP_ACCType:textAccCodePrefix")
	WebElement accountCodePrefix;

	@FindBy(id="addCSP_ACCType:textCancelExtremeBalanceAmount")
	WebElement cancelExtremeBalanceAmount;

	@FindBy(id="addCSP_ACCType:maxAcctNumber")
	WebElement maxNumberOfAccounts;

	@FindBy(id="addCSP_ACCType:excludedBtcs")
	WebElement excludedBTCS;

	@FindBy(id="addCSP_ACCType:addEnabled")
	WebElement addEnabled;

	@FindBy(id="addCSP_ACCType:deleteEnabled")
	WebElement deleteEnabled;

	@FindBy(id="addCSP_ACCType:fundLoadEnabled")
	WebElement fundLoadEnabled;

	@FindBy(id="addCSP_ACCType:balanceInqEnabled")
	WebElement balanceINQEnabled;

	@FindBy(id="addCSP_ACCType:pinRequired")
	WebElement pinRequired;

	@FindBy(id="addCSP_ACCType:allowAnonymousAccounts")
	WebElement allowAnonymousAccounts;

	@FindBy(id="addCSP_ACCType:updateEnabled")
	WebElement threeDSecuredEnabled;

	@FindBy(id="addCSP_ACCType:updateEnabled")
	WebElement updateEnabled;

	@FindBy(id="addCSP_ACCType:cashOutEnabled")
	WebElement cashOutEnabled;

	@FindBy(id="addCSP_ACCType:createEnabled")
	WebElement createEnabled;

	@FindBy(id="addCSP_ACCType:pmtEnabled")
	WebElement pmtEnabled;

	@FindBy(id="addCSP_ACCType:kycRequired")
	WebElement kycRequired;

	@FindBy(id="addCSP_ACCType:purchaseEnable")
	WebElement purchaseEnable;

	@FindBy(id="addCSP_ACCType:decryptAcctNumParameter")
	WebElement decryptAccountNumberParameter;

	@FindBy(id="addCSP_ACCType:saveBtn")
	WebElement save;

	@FindBy(id="addCSP_ACCType:resetBtn")
	WebElement reset;

	@FindBy(className="fieldError")
	List<WebElement> errorMessage;

	@FindBy(id="addCSP_ACCType:errorMessage")
	List<WebElement> fieldError;

	@FindBy(id="addCSP_ACCType:correctMessage")
	List<WebElement> correctMessage;

	@FindBy(linkText="Add Activation Method Parameter")
	WebElement AddActivationMethodParameter;


	public String  addCspAccountType(CSPAccountTypePojo addCspAccountTypeObj)	
	{
		reset.click();
		csp.sendKeys(addCspAccountTypeObj.getCsp());
		accountType.sendKeys(addCspAccountTypeObj.getAccountType());
		customerProfileType.sendKeys(addCspAccountTypeObj.getCustomerProfileType());
		balanceBTC.sendKeys(addCspAccountTypeObj.getBalanceBTC());
		paymentBTC.sendKeys(addCspAccountTypeObj.getPaymentBTC());
		System.out.println(addCspAccountTypeObj.getActivationMethodParameter());
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
		activationMethodParameter.sendKeys(addCspAccountTypeObj.getActivationMethodParameter());
		accountCodePrefix.sendKeys(addCspAccountTypeObj.getAccountCodePrefix());
		cancelExtremeBalanceAmount.sendKeys(addCspAccountTypeObj.getCancelExtremeBalanceAmount());
		maxNumberOfAccounts.sendKeys(addCspAccountTypeObj.getMaxNumberOfAccounts());
		excludedBTCS.sendKeys(addCspAccountTypeObj.getExcludedBTCS());
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

		if(addCspAccountTypeObj.getFlag().contains("saveCspAccountType"))
		{
			save.click();
			String msg;
			if (errorMessage.size()!=0) {
				msg = errorMessage.get(0).getText().toString();
				for(int i=1;i<errorMessage.size();i++)
				{
					msg=msg+"/"+errorMessage.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}

			else if(fieldError.size()!=0)
			{
				System.out.println(fieldError.get(0).getText().toString());
				return fieldError.get(0).getText().toString();

			}
			else if (correctMessage.size()!=0)
			{
				System.out.println(correctMessage.get(0).getText().toString());
				return correctMessage.get(0).getText().toString();
			}	

		}
		else if(addCspAccountTypeObj.getFlag().equalsIgnoreCase("resetCspAccountType"))
		{
			reset.click();
			return "reset";
		}
		return "faild";
	}

	public boolean reset()
	{

		Select cspselect=new Select(csp);
		Select accountType1=new Select(accountType);
		Select customerProfile=new Select(customerProfileType);
		Select balance=new Select(balanceBTC);
		Select payment=new Select(paymentBTC);
		Select activationMethod=new Select(activationMethodParameter);

		if(cspselect.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& accountType1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& customerProfile.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& balance.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& payment.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& activationMethod.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& accountCodePrefix.getAttribute("value").isEmpty()
				&&cancelExtremeBalanceAmount.getAttribute("value").isEmpty()
				&&maxNumberOfAccounts.getAttribute("value").isEmpty()
				&&excludedBTCS.getAttribute("value").isEmpty()
				&&cancelExtremeBalanceAmount.getAttribute("value").isEmpty()
				&&cancelExtremeBalanceAmount.getAttribute("value").isEmpty())
		{
			return true;
		}
		return false;

	}

}
