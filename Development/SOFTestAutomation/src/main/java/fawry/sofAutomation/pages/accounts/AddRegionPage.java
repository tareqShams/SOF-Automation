package fawry.sofAutomation.pages.accounts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.RegionPojo;


public class AddRegionPage {

	WebDriver driver;

	public AddRegionPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="addRegionForm:governorate")
	public WebElement regiongovernoratelist;

	@FindBy(id="addRegionForm:district")
	public WebElement regiondistrictlist;

	@FindBy(id="addRegionForm:regionCode")
	public WebElement regioncodetxt;

	@FindBy(id="addRegionForm:regionName")
	public WebElement regionnametxt;

	@FindBy(id="addRegionForm:saveBtn")
	public WebElement addregionsavebtn;

	@FindBy(id="addRegionForm:resetBtn")
	public WebElement addregionresetbtn;

	@FindBy(id="addRegionForm:backBtn")
	public WebElement addregionbackbtn;

	@FindBy(id="addPOS:link56")
	public WebElement addaccaddregionlink;

	@FindBy(id="editPOS:link56")
	public WebElement updateaccaddregionlink;

	@FindBy(id="addRegionForm:correctMessage")
	public WebElement addregionsuccessmsg;
	
	@FindBy(className="fieldError")
	public List<WebElement> ErrorMsgsList;

	public String actual;
	public 	String timestamp1 = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());
	public 	String timestamp2 = new SimpleDateFormat("ddhhmmssmm").format(Calendar.getInstance().getTime());
	public String errorMsgs;


	public String  addNewRegion(RegionPojo regobj)
	{
		if(!regobj.getGovernorate().isEmpty() && !regobj.getDistrict().isEmpty() && !regobj.getCode().isEmpty() && regobj.getCode().equalsIgnoreCase("1") && !regobj.getName().isEmpty() )
		{
			new Select(regiongovernoratelist).selectByVisibleText(regobj.getGovernorate());
			new Select(regiondistrictlist).selectByVisibleText(regobj.getDistrict());

			regioncodetxt.clear();
			regioncodetxt.sendKeys(regobj.getCode() + timestamp1);
			
			regobj.setLocationId(regobj.getCode() + timestamp1);
			
			if (regobj.getName().equalsIgnoreCase("ResetRegion"))
			{
				addregionresetbtn.click();
				regobj.setLocationId(Constants.STATIC_LOCATION_ID);
				actual = driver.getCurrentUrl();
				addregionbackbtn.click();
			}
			if (!regobj.getName().equalsIgnoreCase("ResetRegion")) 
			{
				regionnametxt.clear();
				regionnametxt.sendKeys(regobj.getName()+timestamp2);
				regobj.setName(regobj.getName()+timestamp2);
				addregionsavebtn.click();
				actual = addregionsuccessmsg.getText();
				addregionbackbtn.click();
			}
		}
		else
		{
			if(!regobj.getGovernorate().isEmpty())
			{
				new Select(regiongovernoratelist).selectByVisibleText(regobj.getGovernorate());
			}
			if(!regobj.getDistrict().isEmpty())
			{
				new Select(regiondistrictlist).selectByVisibleText(regobj.getDistrict());	
			}

			regioncodetxt.clear();
			regioncodetxt.sendKeys(regobj.getCode());
			regionnametxt.clear();
			regionnametxt.sendKeys(regobj.getName());
			
			//Save Credentials
			addregionsavebtn.click();
			
			// Read All Error messages in a List
			errorMsgs = ErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<ErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+ErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;
			addregionbackbtn.click();

		}
		return actual;

	}

}
