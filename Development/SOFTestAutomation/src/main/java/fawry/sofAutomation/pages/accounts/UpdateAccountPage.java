package fawry.sofAutomation.pages.accounts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.RegionPojo;

public class UpdateAccountPage {
	WebDriver driver;

	public UpdateAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="editPOS:locSelect")
	WebElement bylocationidbtn;

	@FindBy(id="searchPOS:textSearchPOSID1")
	WebElement accountidtxt;

	@FindBy(id="editPOS:locationID")
	WebElement locationidtxt;

	@FindBy(id="editPOS:regSelect")
	WebElement byregiondetailsbtn;

	@FindBy(id="editPOS:governorate")
	WebElement governoratelist;

	@FindBy(id="editPOS:district")
	WebElement districtlist;

	@FindBy(id="editPOS:region")
	WebElement regionlist;

	@FindBy(id="editPOS:editAccountStatus")
	WebElement accountstatuslist;

	@FindBy(id="editPOS:editUsage")
	WebElement usagelist;

	@FindBy(id="editPOS:editDescription")
	WebElement descriptiontxt;

	@FindBy(id="editPOS:cashCollectorCode")
	WebElement salesreptxt;

	@FindBy(id="editPOS:cspMainAccount")
	WebElement cspmainbtn;

	@FindBy(id="editPOS:internalAccount")
	WebElement internalbtn;

	@FindBy(id="cspMainAccount")
	WebElement mainbtn;

	@FindBy(id="editPOS:editCurrencyList")
	WebElement currencylist;

	@FindBy(id="editPOS:textDailyLimit1")
	WebElement dailylimittxt;

	@FindBy(id="editPOS:textCreditLimit1")
	WebElement creditlimittxt;

	@FindBy(id="editPOS:Mobile")
	WebElement mobilenumbertxt;

	@FindBy(id="editPOS:profileDataList")
	WebElement profileidlist;

	@FindBy(id="searchPOS:button1")
	WebElement searchtbtn;

	@FindBy(id="searchPOS:tableEx1:0:textSearchResults1")
	WebElement accountcodelink;

	@FindBy(id="editPOS:accountClassList")
	WebElement accountclasslist;

	@FindBy(id="editPOS:accountGroupList")
	WebElement accountgrouplist;

	@FindBy(id="editPOS:officialTypeList")
	WebElement officialtypelist;

	@FindBy(id="editPOS:officialValue")
	WebElement officialnumber;

	@FindBy(id="editPOS:susReason")
	WebElement suspintionreasonlist;
	
	@FindBy(id="editPOS:saveButton")
	WebElement savebtn;

	@FindBy(id="editPOS:backButton")
	WebElement backbtn;

	@FindBy(id="editPOS:link56")
	WebElement addregionlink;

	@FindBy(id="editPOS:button21")
	WebElement resetbtn;

	@FindBy(id="correctMessage")
	WebElement successmsg;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	WebElement headerErrorMsgs;

	String actual;
	String databeforeupdate;
	String dataafterreset;
	String errorMsgs;

	/*
	 * using sparefield1 for expected of adding a new region
	 * using sparefield5 for data before resetting
	 * using sparefield6 for data after resetting
	 */
	//Navigate to update account and collect data before update
	public void updateAccountMain(AccountPojo updateaccountobj)
	{
		// Search with account id
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		accountidtxt.clear();
		accountidtxt.sendKeys(updateaccountobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();

		// Collecting Data For checking when resetting
		Select selectstatus = new Select(accountstatuslist);
		Select selectusage = new Select(usagelist);
		Select selectclass = new Select(accountclasslist);
		Select selectgroup = new Select(accountgrouplist);
		databeforeupdate = selectstatus.getOptions().get(0).toString()+selectusage.getOptions().get(0).toString()+
				selectclass.getOptions().get(0).toString()+selectgroup.getOptions().get(0).toString()+
				dailylimittxt.getText()+creditlimittxt.getText();
		updateaccountobj.setSparefield5(databeforeupdate);
	}

	//updating account Location using location id
	public void  updateAccountUsingLocationId(RegionPojo updateaccountobj)
	{	
		bylocationidbtn.click();
		locationidtxt.clear();
		locationidtxt.sendKeys(updateaccountobj.getLocationId());
	}
	//updating account Location using region details with an already added region
	public void  updateAccountUsingRegionDetails(RegionPojo updateaccountobj)
	{	
		// region elements are nested and shown only when choosing by region details
		byregiondetailsbtn.click();
		if (!updateaccountobj.getGovernorate().isEmpty())
		{
			new Select(governoratelist).selectByVisibleText(updateaccountobj.getGovernorate());

			if(!updateaccountobj.getDistrict().isEmpty())
			{
				new Select(districtlist).selectByVisibleText(updateaccountobj.getDistrict());
				if(!updateaccountobj.getName().isEmpty())
				{
					new Select(regionlist).selectByVisibleText(updateaccountobj.getName());
				}
			}
		}
	}
	//updating account Location By adding a new region
	public void  updateAccountWthAddingNewRegion(AccountPojo updateaccountobj)
	{	
		addregionlink.click();
		AddRegionPage addregion = new AddRegionPage(driver);
		updateaccountobj.setSparefield1(addregion.addNewRegion(updateaccountobj.getRegions().get(0)));
		bylocationidbtn.click();
		locationidtxt.clear();
		locationidtxt.sendKeys(updateaccountobj.getRegions().get(0).getLocationId());
	}
	//All Common fields method
	public void  updateAccountCommonFields(AccountPojo updateaccountobj)
	{
		if (!updateaccountobj.getAccountStatus().isEmpty())
		{
			new Select(accountstatuslist).selectByVisibleText(updateaccountobj.getAccountStatus());
			
			if (!updateaccountobj.getSuspentionReason().isEmpty())
			{
				new Select(suspintionreasonlist).selectByVisibleText(updateaccountobj.getSuspentionReason());
			}
		}
		if(!updateaccountobj.getUsage().isEmpty())
		{
			new Select(usagelist).selectByVisibleText(updateaccountobj.getUsage());
		}
		if (!updateaccountobj.getDescription().isEmpty())
		{
			descriptiontxt.clear();
			descriptiontxt.sendKeys(updateaccountobj.getDescription());
		}
		if(!updateaccountobj.getAccountClass().isEmpty())
		{
			new Select(accountclasslist).selectByVisibleText(updateaccountobj.getAccountClass());
		}
		if(!updateaccountobj.getAccountGroup().isEmpty())
		{
			new Select(accountgrouplist).selectByVisibleText(updateaccountobj.getAccountClass());
		}
		/*
		if(!updateaccountobj.getSalesRep.isEmpty())
		{
			salesreptxt.clear();
			salesreptxt.sendKeys(updateaccountobj.getSalesRep());
		}
		 */
		if (!updateaccountobj.getCurrency().isEmpty())
		{
			new Select(currencylist).selectByVisibleText(updateaccountobj.getCurrency());
		}
		if (!updateaccountobj.getDailyLimit().isEmpty())
		{
			dailylimittxt.clear();
			dailylimittxt.sendKeys(updateaccountobj.getDailyLimit());
		}
		if(!updateaccountobj.getCreditLimit().isEmpty())
		{
			creditlimittxt.clear();
			creditlimittxt.sendKeys(updateaccountobj.getCreditLimit());
		}
		if(!updateaccountobj.getMobileNumber().isEmpty())
		{
			mobilenumbertxt.clear();
			mobilenumbertxt.sendKeys(updateaccountobj.getMobileNumber());
		}
		if(!updateaccountobj.getOfficialType().isEmpty())
		{
			new Select(officialtypelist).selectByVisibleText(updateaccountobj.getOfficialType());
		}
		if (!updateaccountobj.getOfficialnumber().isEmpty())
		{
			officialnumber.clear();
			officialnumber.sendKeys(updateaccountobj.getOfficialnumber());
		}
		/*		if(!updateaccountobj.getProfileid().isEmpty()) 
		{
			new Select(profileidlist).selectByVisibleText(updateaccountobj.getProfileid());
		}*/
	}

	//Method for choosing internal account
	public void  internalAccount(AccountPojo updateaccountobj)
	{
		internalbtn.click();
	}
	//Method for choosing internal account
	public void  cspMainAccount(AccountPojo updateaccountobj)
	{
		mainbtn.click();
	}
	//Save Or Reset Inserted Data
	public void  saveOrResetInsertedData(AccountPojo updateaccountobj)
	{
		if (updateaccountobj.getAction().contains("Reset"))
		{
			resetbtn.click();
			// Collecting data after reset
			Select selectstatus = new Select(accountstatuslist);
			Select selectusage = new Select(usagelist);
			Select selectclass = new Select(accountclasslist);
			Select selectgroup = new Select(accountgrouplist);
			dataafterreset =  selectstatus.getOptions().get(0).toString()+selectusage.getOptions().get(0).toString()+
					selectclass.getOptions().get(0).toString()+selectgroup.getOptions().get(0).toString()+
					dailylimittxt.getText()+creditlimittxt.getText();
			updateaccountobj.setSparefield6(dataafterreset);
			//Setting actual to success to be asserted with expected in excel
			actual="";
		}
		else if (updateaccountobj.getAction().contains("Save"))
		{
			savebtn.click();
		}
		else if (updateaccountobj.getAction().contains("Back"))
		{
			backbtn.click();
		}
	}
	public String collectingActualMessages(AccountPojo updateaccountobj)
	{
		actual = "";
		if (driver.findElements(By.className("fieldError")).size() != 0 )
		{
			errorMsgs = pageErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<pageErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;
		}
		else if (driver.findElements(By.className("error")).size() != 0 )
		{
			actual = headerErrorMsgs.getText();
		}
		else if (driver.findElements(By.id("correctMessage")).size() != 0 )
		{
			actual = successmsg.getText();
		}
		else if (updateaccountobj.getAction().contains("Back"))
		{
			actual = driver.getCurrentUrl();	
		}

		return actual;
	}

}

