package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;

public class EditCspBillTypePage extends MainPage {

	WebDriver driver;
	public EditCspBillTypePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="editPSP:editCode")
	WebElement customerServiceProvidercode;

	@FindBy(id="editPSP:btc")
	WebElement billTypeCode;

	@FindBy(id="editPSP:textAmount")
	WebElement amount;

	@FindBy(id="editPSP:textMonths")
	WebElement loyaltyNumOfMonths;

	@FindBy(id="editPSP:saveBtn")
	WebElement save;

	@FindBy(id="editPSP:resetBtn")
	WebElement reset;
	
	@FindBy(id="editPSP:delete")
	WebElement delete;

	@FindBy(id="editPSP:backBtn")
	WebElement back;
	
	@FindBy(className="fieldError")
	List<WebElement> fieldError;
	
	@FindBy(className="alert")
	List<WebElement> correctMassage;
	
	@FindBy(id="errorMessage")
	List<WebElement> errorMassage;
	
	public String CustomerServiceProvider =null;
	public String  updateCspBillType(CspBillTypePojo cspBillObject)	
	{
	    CustomerServiceProvider = customerServiceProvidercode.getText();
		billTypeCode.sendKeys(cspBillObject.getUpdatebillTypeCode());
		amount.clear();
		amount.sendKeys(cspBillObject.getAmount());
		loyaltyNumOfMonths.clear();
		loyaltyNumOfMonths.sendKeys(cspBillObject.getLoyaltyNumOfMonths());
		String msg;
		if(cspBillObject.getFlag().contains("save"))
		{
			save.click();
			if (errorMassage.size()!=0) {
				msg = errorMassage.get(0).getText().toString();
				for(int i=1;i<errorMassage.size();i++)
				{
					msg=msg+"/"+errorMassage.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}
			
			else if(errorMassage.size()!=0)
			{
				System.out.println(errorMassage.get(0).getText().toString());
				return errorMassage.get(0).getText().toString();
			}
			else if (correctMassage.size()!=0)
			{
				System.out.println(correctMassage.get(0).getText().toString());
				return correctMassage.get(0).getText().toString();
			}	
		}
		else if(cspBillObject.getFlag().contains("reset"))
		{
			reset.click();
			return "reset";
		}
		else if(cspBillObject.getFlag().contains("delete"))
		{
			delete.click();
			Alert alert=driver.switchTo().alert();
			alert.accept();
			if (correctMassage.size()!=0)
			{
				System.out.println(correctMassage.get(0).getText().toString());
				return correctMassage.get(0).getText().toString();
			}	
		return "delete Massage Failed";
		}
		else if(cspBillObject.getFlag().contains("back"))
		{
			back.click();
			return "back";
		}
		return "No Action Done";
	}
}
