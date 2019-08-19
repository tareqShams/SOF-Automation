package fawry.sofAutomation.pages.accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class AccountVerficationPage {
	WebDriver driver;
	public AccountVerficationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchPOS:button1")
	WebElement searchtbtn;

	@FindBy(id="searchPOS:tableEx1:0:textSearchResults1")
	WebElement accountcodelink;

	@FindBy(id="searchPOS:textSearchPOSID1")
	WebElement accountidtxt;


	@FindBy(linkText="Account Verification")
	WebElement accverficationlnk;

	@FindBy(id="ContactData:generate")
	WebElement GenerateAndSendVerificationCodelnk;

	@FindBy(id="ContactData:markprc")
	WebElement MarkProfileIncompletelnk;

	@FindBy(id="ContactData:rst")
	WebElement ResetStatuslnk;

	@FindBy(id="ContactData:CorrectMessage")
	WebElement successMsg;

	@FindBy(className="error")
	WebElement headerErrorMsgs;

	String actualaccountverfication;



	public String  accountVerfication(AccountPojo accountverficationobj)
	{
		navigatoAccountverfication(accountverficationobj);
		System.out.println(accountverficationobj.getAction());
		if (accountverficationobj.getAction().contains("SendVerfication"))
		{
			GenerateAndSendVerificationCodelnk.click();
		}
		else if  (accountverficationobj.getAction().contains("ResetStatus"))
		{
			ResetStatuslnk.click();
		}
		else if  (accountverficationobj.getAction().contains("Markincomplete"))
		{
			MarkProfileIncompletelnk.click();
		}

		if (driver.findElements(By.id("ContactData:CorrectMessage")).size() != 0)
		{
			actualaccountverfication = successMsg.getText();
		}
		else if (driver.findElements(By.className("error")).size() != 0)
		{
			actualaccountverfication = headerErrorMsgs.getText();
		}

		return actualaccountverfication;

	}


	public void  navigatoAccountverfication(AccountPojo accountverobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(accountverobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();
		//Navigate To account Terminals Menu
		accverficationlnk.click();
	}

}
