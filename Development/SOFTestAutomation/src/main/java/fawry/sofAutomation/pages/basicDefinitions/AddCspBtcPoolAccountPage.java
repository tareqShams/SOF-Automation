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

	@FindBy(id="addCSP:cspList")
	WebElement csp;

	@FindBy(id="addCSP:btcList")
	WebElement btc;

	@FindBy(id="addCSP:textPoolAccount")
	WebElement poolAccount;

	@FindBy(id="addCSP:saveBtn")
	WebElement save;

	@FindBy(id="addCSP:resetBtn")
	WebElement reset;

	@FindBy(className="alert")
	List<WebElement>  correctMassage;

	@FindBy(id="addCSP:errorMessage")
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
		poolAccount.sendKeys(CspBtcPoolAccountObj.getPoolAccount());

		if(CspBtcPoolAccountObj.getFlag().equalsIgnoreCase("save"))
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
		else if (CspBtcPoolAccountObj.getFlag().equalsIgnoreCase("reset")) {
			reset.click();
			return "reset";
		}
		else  {
			return "false";
		}
		return null;


	}

}
