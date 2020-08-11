package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;
import fawry.sofAutomation.constants.basicDefinitions.Constants;
import fawry.sofAutomation.pages.admin.MainPage;

public class AddCspBillTypePage  extends MainPage{
	WebDriver driver;

	public AddCspBillTypePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="addCSP:cspList")
	WebElement customerServiceProvider;

	@FindBy(id="addCSP:btcList")
	WebElement billTypeCode;

	@FindBy(id="addCSP:textAmount")
	WebElement amount;

	@FindBy(id="addCSP:textMonths")
	WebElement loyaltyNumOfMonths;

	@FindBy(id="addCSP:enableInadvanceCommisions")
	WebElement commissionsBtn;


	@FindBy(id="addCSP:saveBtn")
	WebElement save;

	@FindBy(id="addCSP:resetBtn")
	WebElement reset;

	@FindBy(className="fieldError")
	List<WebElement> errorMassages;

	@FindBy(id="addCSP:correctMessage")
	List<WebElement> correctMassages;

	@FindBy(id="addCSP:errorMessage")
	List<WebElement> errorMassage;

	public String  addCspBillType(CspBillTypePojo cspBillObject)	
	{
		driver.navigate().to(Constants.ADD_CSP_BILL_TYPE_URL);
		if(!cspBillObject.getCustomerServiceProvider().isEmpty())
		{
			customerServiceProvider.sendKeys(cspBillObject.getCustomerServiceProvider());
		}
		if(!cspBillObject.getBillTypeCode().isEmpty())
		{
			billTypeCode.sendKeys(cspBillObject.getBillTypeCode());
		}
		amount.sendKeys(cspBillObject.getAmount());
		loyaltyNumOfMonths.sendKeys(cspBillObject.getLoyaltyNumOfMonths());
		if(cspBillObject.getFlag().contains("Commissions"))
		{
			commissionsBtn.click();
		}
		System.out.println(cspBillObject.getFlag());
		if(cspBillObject.getFlag().contains("save"))
		{
			String msg;
			save.click();
			if (errorMassages.size()!=0) {
				msg = errorMassages.get(0).getText().toString();
				for(int i=1;i<errorMassages.size();i++)
				{
					msg=msg+"/"+errorMassages.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}

			else if(errorMassage.size()!=0)
			{
				System.out.println(errorMassage.get(0).getText().toString());
				return errorMassage.get(0).getText().toString();

			}
			else if (correctMassages.size()!=0)
			{

				System.out.println(correctMassages.get(0).getText().toString());
				return correctMassages.get(0).getText().toString();

			}	


		}
		else if (cspBillObject.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			return "reset";

		}

		return "fail";
	}

	public Boolean reset()
	{
		Select csp=new Select(customerServiceProvider);
		Select billtypecode=new Select(billTypeCode);

		if(csp.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& billtypecode.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& amount.getAttribute("value").isEmpty()
				&&loyaltyNumOfMonths.getAttribute("value").isEmpty())
		{
			return true;
		}
		return false;

	}
}
