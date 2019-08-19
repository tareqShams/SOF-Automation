package fawry.sofAutomation.pages.basicDefinitions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;

public class AddActivationMethodParameterPage extends MainPage{
	WebDriver driver;

	public AddActivationMethodParameterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="addCSP_ACCType:ampCode")
	WebElement code;
	
	@FindBy(id="addCSP_ACCType:activationMethodList")
	WebElement activationMethod;
	
	@FindBy(id="addCSP_ACCType:merchantList")
	WebElement merchant;
	
	@FindBy(id="addCSP_ACCType:btcList")
	WebElement btc;
	
	@FindBy(id="addCSP_ACCType:textActivationAuthorizerSystemCode")
	WebElement activationAuthorizerSystemCode;
	
	@FindBy(id="addCSP_ACCType:saveBtn")
	WebElement save;
	
	@FindBy(id="addCSP_ACCType:resetBtn")
	WebElement reset;

	@FindBy(id="addCSP_ACCType:backBtn")
	WebElement back;
	String timestamp = new SimpleDateFormat("ssmmm").format(Calendar.getInstance().getTime());

	public void  addActivationMethodParameter(CSPAccountTypePojo addActivationMethodParameterobj)	
	{
		
		code.sendKeys(timestamp);
		addActivationMethodParameterobj.setCode(code.getAttribute("value"));
		activationMethod.sendKeys(addActivationMethodParameterobj.getActivationMethod());
		merchant.sendKeys(addActivationMethodParameterobj.getMerchant());
		btc.sendKeys(addActivationMethodParameterobj.getBtc());
		activationAuthorizerSystemCode.sendKeys(addActivationMethodParameterobj.getActivationAuthorizerSystemCode());
		/*if(addActivationMethodParameterobj.getFlag().contains("saveActivationMethod"))
		{
			save.click();
		}
		else if (addActivationMethodParameterobj.getFlag().contains("resetActivationMethod"))
		{
			reset.click();
		}
		else if (addActivationMethodParameterobj.getFlag().contains("backActivationMethod"))
		{
			back.click();
		}*/

	save.click();
	back.click();
	}
	
	
}
