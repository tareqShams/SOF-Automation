package fawry.sofAutomation.pages.accounts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pages.main.MainPage;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class AddAccountPage extends MainPage{
	WebDriver driver;
	public AddAccountPage(WebDriver driver)	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*
	  sparefield5 is for -- adding new region output
	  sparefield6 is for -- adding new customer
	  sparefield7 is for -- adding new Terminal
	 */

	public String newacccode;

	@FindBy(id="addPOS:locationID")
	WebElement locationidtxt;

	@FindBy(id="addPOS:cspList")
	WebElement csplist;

	@FindBy(id="addPOS:menu1")
	WebElement customerprofiletxt;

	@FindBy(id="addPOS:usageList")
	WebElement usagelist;

	@FindBy(id="addPOS:textareaDescription1")
	WebElement descriptiontxt;

	@FindBy(id="addPOS:addAcctTerminal")
	WebElement addterminalbtn;

	@FindBy(id="addPOS:textDailyLimit1")
	WebElement dailylimittxt;

	@FindBy(id="addPOS:textCreditLimit1")
	WebElement creditlimittxt;

	@FindBy(id="addPOS:button1")
	WebElement addaccountsavebtn;

	@FindBy(id="AddAccountSuccess:CorrectMessage")
	WebElement addaccountsuccessmsg;

	@FindBy(id="AddAccountSuccess:okBtn")
	WebElement addaccountsuccesokbtn;

	@FindBy(id="addPOS:regSelect")
	WebElement selectbyregiondetailsdbtn;

	@FindBy(id="addPOS:locSelect")
	WebElement selectbylocationidbtn;

	@FindBy(id="addPOS:governorate")
	WebElement addaccgovernorateselect;

	@FindBy(id="addPOS:district")
	WebElement addaccdistrictselect;

	@FindBy(id="addPOS:region")
	WebElement addaccregionselect;

	@FindBy(id="addPOS:link55")
	WebElement byregiondetailsaddregionlink;

	@FindBy(id="addPOS:link56")
	WebElement bylocationidaddregionlink;

	@FindBy(id="addPOS:link44")
	WebElement addcustomerprofilelink;

	@FindBy(id="addPOS:accountClassList")
	WebElement accountclassslist;

	@FindBy(id="addPOS:accountGroupList")
	WebElement accountgrouplist;

	@FindBy(id="addPOS:textDailyCreditLimit")
	WebElement dailycredditlimittxt;

	@FindBy(id="addPOS:Mobile")
	WebElement addaccmobilenumber;

	@FindBy(id="addPOS:officialTypeList")
	WebElement officialtypelist;

	@FindBy(id="addPOS:currencyList")
	WebElement currencyLst;

	@FindBy(id="addPOS:officialValue")
	WebElement officialnumbertxt;

	@FindBy(id="addPOS:profileDataList")
	WebElement profileidlist;

	@FindBy(id="addPOS:megaAccount234")
	WebElement setupProgramAccountBtn;
	
	@FindBy(id="addPOS:button2")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	WebElement csperrormsg;

	@FindBy(id="addPOS:RegionCode")
	WebElement existingregioncodemsg;

	@FindBy(id="AddAccountSuccess:AccountCode")
	WebElement newaccountcode;

	@FindBy(id="addPOS:link56")
	WebElement addaccaddregionlink;

	@FindBy(id="addPOS:link44")
	WebElement addcustomerfromaddaccountlink;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String actualregion;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());

	//Add account using region details with an already added Region
	public void AddAccountUsingRegionDetails(AccountPojo addaccountobj)
	{
		selectbyregiondetailsdbtn.click();

		// Selection Order is governorate , District Then Region
		if(!addaccountobj.getRegions().get(0).getGovernorate().isEmpty()) {
			new Select(addaccgovernorateselect).selectByVisibleText(addaccountobj.getRegions().get(0).getGovernorate());
			if (!addaccountobj.getRegions().get(0).getDistrict().isEmpty()){
				new Select(addaccdistrictselect).selectByVisibleText(addaccountobj.getRegions().get(0).getDistrict());
				if (!addaccountobj.getRegions().get(0).getName().isEmpty()) {
					new Select(addaccregionselect).selectByVisibleText(addaccountobj.getRegions().get(0).getName());
				}
			}
		}
	}

	public void  addAccountUsingLocationId(AccountPojo addaccountobj)

	{
		selectbylocationidbtn.click();

		if (!addaccountobj.getLocationId().isEmpty())
		{
			locationidtxt.sendKeys(addaccountobj.getLocationId());
		}
	}
	//All Common fields methhod
	public void  AddAccountCommonFields(AccountPojo addaccountobj)
	{
		//Selection order is Csp, Customer Profile Then Usage
		if (!addaccountobj.getCsp().isEmpty())
		{
			csplist.click();
			new Select(csplist).selectByVisibleText(addaccountobj.getCsp());
			if (!addaccountobj.getCustomerProfile().isEmpty())
			{

				customerprofiletxt.clear();
				customerprofiletxt.sendKeys(addaccountobj.getCustomerProfile());
				addaccountobj.getCustomer().get(0).setCustomerprofilecode(addaccountobj.getCustomerProfile());

				if (!addaccountobj.getUsage().isEmpty())
				{
					usagelist.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					new Select(usagelist).selectByVisibleText(addaccountobj.getUsage());
				}
			}
		}

		descriptiontxt.clear();
		descriptiontxt.sendKeys(addaccountobj.getDescription());
		if (addaccountobj.getAddAccountMethod().contains("AddTerminal"))
		{
			for(int i=0; i< addaccountobj.getTerminals().size(); i++)
			{
				// use already created add terminal Method
				addterminalbtn.click();
				AddTerminalPage addterminal = new AddTerminalPage(driver);
				// set the output of adding new Terminal into spare variable to be used in validation
				addaccountobj.setSparefield7(addterminal.addNewTerminalNewAccount(addaccountobj.getTerminals().get(i)));
			}

		}
		if(!addaccountobj.getCurrency().isEmpty())
		{
			new Select(currencyLst).selectByVisibleText(addaccountobj.getCurrency());

		}
		dailylimittxt.clear();
		dailylimittxt.sendKeys(addaccountobj.getDailyLimit());
		creditlimittxt.clear();
		creditlimittxt.sendKeys(addaccountobj.getCreditLimit());

		if(!addaccountobj.getOfficialType().isEmpty())
		{
			new Select(officialtypelist).selectByVisibleText(addaccountobj.getOfficialType());
		}
		if (!addaccountobj.getOfficialnumber().isEmpty())
		{
			officialnumbertxt.sendKeys(addaccountobj.getOfficialnumber()+timestamp);
			addaccountobj.setOfficialnumber(addaccountobj.getOfficialnumber()+timestamp);

		}
		if (!addaccountobj.getProfileid().isEmpty())
		{			
			new Select(profileidlist).selectByVisibleText(addaccountobj.getProfileid());
		}
		if(addaccountobj.getAddAccountMethod().contains("Mega"))
		{
			setupProgramAccountBtn.click();
		}
	}

	// Collecting Success messages and error Messages
	public String addAccountErrorMessagesAndSuccessMessage(AccountPojo addaccountobj) 
	{
		actual = "";
		if (addaccountobj.getAddAccountMethod().contains("Reset")) 
		{
			actual = locationidtxt.getText();
		}
		else if (driver.findElements(By.id("AddAccountSuccess:CorrectMessage")).size() != 0)
		{
			actual =  addaccountsuccessmsg.getText();
			newacccode = newaccountcode.getText();
			System.out.println(newacccode);
			addaccountobj.setAccountCode(newacccode);
			addaccountsuccesokbtn.click();
		}
		else if (driver.findElements(By.className("fieldError")).size() != 0  )
		{
			errorMsgs = pageErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<pageErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;
		}
		else if ( driver.findElements(By.className("error")).size() != 0)
		{
			errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<HeaderErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;	
		}
		return actual;
	}
	//Adding New Customer Using Add customer Page
	public void AddAccountAddNewCustomer(AccountPojo addaccountobj) {

		AddCustomerPage addCustomer = new AddCustomerPage(driver);

		addcustomerfromaddaccountlink.click();
		// Setting the output from adding new customer into spare field to be validated with expected
		addaccountobj.setSparefield6(addCustomer.AddNewCustomer(addaccountobj.getCustomer().get(0)));

		if (!addaccountobj.getCustomer().get(0).getCustomerprofilecode().isEmpty() || !addaccountobj.getCustomer().get(0).getCustomerprofilecode().equalsIgnoreCase("1"))
		{
			addaccountobj.setCutomerProfile(addaccountobj.getCustomer().get(0).getCustomerprofilecode());
		}
	}

	//Adding New Region Using add region Page
	public void AddAccountAddNewRegion(AccountPojo addaccountobj) {
		AddRegionPage addregion = new AddRegionPage(driver);
		addaccaddregionlink.click();
		// Setting the output from adding new region into spare field to be validated with expected
		addaccountobj.setSparefield5(addregion.addNewRegion(addaccountobj.getRegions().get(0)));

		locationidtxt.clear();
		locationidtxt.sendKeys(addaccountobj.getRegions().get(0).getLocationId());
	}
	//Saving or resetting Data
	public void saveOrResetData(AccountPojo addaccountobj) {
		System.out.println(addaccountobj.getAddAccountMethod());
		if (addaccountobj.getAddAccountMethod().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (addaccountobj.getAddAccountMethod().contains("Save")){
			addaccountsavebtn.click();
		}
	}



	public void addStaticAccount(AccountPojo addaccountobj)
	{
		driver.navigate().to(Constants.ADD_ACCOUNT_URL);
		selectbylocationidbtn.click();
		locationidtxt.sendKeys(Constants.STATIC_LOCATION_ID);

		csplist.click();
		new Select(csplist).selectByVisibleText("FAWRYRTL");

		customerprofiletxt.clear();
		customerprofiletxt.sendKeys(Constants.STATIC_CUSTOMER_PROFILE);


		usagelist.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new Select(usagelist).selectByVisibleText(Constants.STATIC_USAGE);


		descriptiontxt.clear();
		descriptiontxt.sendKeys("Test with static account");
		// use already created add terminal Method
		addterminalbtn.click();
		AddTerminalPage addterminal = new AddTerminalPage(driver);
		addaccountobj.setTerminalStatus("Active");
		addaccountobj.setTerminalType("Point of Sale");
		addaccountobj.setSerialNumber("1");
		addaccountobj.setPin("1234");
		//addterminal.addNewTerminalNewAccount(addaccountobj);

		dailylimittxt.clear();
		dailylimittxt.sendKeys("1000");
		creditlimittxt.clear();
		creditlimittxt.sendKeys("10000");

		addaccountsavebtn.click();

		addaccountobj.setAccountCode(newacccode);


	}
	/*
	 *  //change value of a cell
    public String isnullvalue(String value)
    {
           if(value.equalsIgnoreCase("*"))
           {
                  value="";
           }
           return value;
    }
	 */
}

