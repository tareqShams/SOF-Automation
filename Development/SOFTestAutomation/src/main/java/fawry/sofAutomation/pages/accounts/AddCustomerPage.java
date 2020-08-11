package fawry.sofAutomation.pages.accounts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.accounts.CustomerPojo;

public class AddCustomerPage {

	WebDriver driver;

	public AddCustomerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="addMerchant:textMerchantID1")
	public WebElement customerprofilecodetxt;

	@FindBy(id="addMerchant:textPosid1")
	public WebElement customerprofilenametxt;

	@FindBy(id="addMerchant:corporate")
	public WebElement cspnamelist;

	@FindBy(id="addMerchant:textareaDescription1")
	public WebElement descriptiontxt;

	@FindBy(id="addMerchant:customerCategoryList")
	public WebElement customercatagorylist;

	@FindBy(id="addMerchant:customerTypeList")
	public WebElement customerprofiletype;

	@FindBy(id="addMerchant:textCustContactVal")
	public WebElement customermobilenumbertxt;

	@FindBy(id="addMerchant:CorrectMessage")
	public WebElement customerSuccessMsg;

	@FindBy(className="fieldError")
	public List<WebElement> ErrorMsgsList;

	@FindBy(id="addMerchant:button1")
	public WebElement savebtn;

	@FindBy(id="addMerchant:backBtn")
	public WebElement backbtn;

	public String errorMsgs;
	public String returnAddedCustomer;
	public 	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());

	public String  AddNewCustomer(CustomerPojo obj)
	{
		if (obj.getName().equalsIgnoreCase("ClickBack")) 
		{
			//Check Functionality of back Button
			backbtn.click();
			returnAddedCustomer = driver.getCurrentUrl();
		}
		else 
		{

			if(!obj.getCustomerprofilecode().contains("1")) 
			{
				customerprofilecodetxt.clear();
				customerprofilecodetxt.sendKeys(obj.getCustomerprofilecode());
			}
			if(obj.getCustomerprofilecode().contains("1")) 
			{
				customerprofilecodetxt.clear();
				customerprofilecodetxt.sendKeys(obj.getCustomerprofilecode()+timestamp);
				obj.setCustomerprofilecode(obj.getCustomerprofilecode()+timestamp);
			}
			if(!obj.getName().isEmpty() ) 
			{
				customerprofilenametxt.clear();
				customerprofilenametxt.sendKeys(obj.getName());
			}
			if(obj.getName().equalsIgnoreCase("Customer")) 
			{
				customerprofilenametxt.clear();
				customerprofilenametxt.sendKeys(obj.getName() + timestamp);
				obj.setName(obj.getName() + timestamp);
			}
			if(!obj.getCspname().isEmpty() ) 
			{
				new Select(cspnamelist).selectByVisibleText(obj.getCspname());
			}
			descriptiontxt.clear();
			descriptiontxt.sendKeys(obj.getCutomerdescription());
			if(!obj.getCustomercatagory().isEmpty() ) 
			{
				new Select(customercatagorylist).selectByVisibleText(obj.getCustomercatagory());
			}
			if(!obj.getCustomerprofiletype().isEmpty() ) 
			{
				new Select(customerprofiletype).selectByVisibleText(obj.getCustomerprofiletype());
			}
			customermobilenumbertxt.clear();
			customermobilenumbertxt.sendKeys(obj.getMobilenumber());

			savebtn.click();

			if (driver.findElements(By.id("addMerchant:CorrectMessage")).size() != 0)
			{
				returnAddedCustomer = customerSuccessMsg.getText();
				backbtn.click();
			}
			else if (driver.findElements(By.className("fieldError")).size() != 0)
			{
			// Read All Error messages in a List
			errorMsgs = ErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<ErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+ErrorMsgsList.get(i).getText().toString();
			}
			returnAddedCustomer = errorMsgs;
			backbtn.click();
			}
			else {
				backbtn.click();
				returnAddedCustomer = driver.getCurrentUrl();
			}

		}
		return returnAddedCustomer;
	}


}
