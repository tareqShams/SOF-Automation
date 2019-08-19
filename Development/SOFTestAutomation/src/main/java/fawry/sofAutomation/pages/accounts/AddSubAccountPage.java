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
import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;

public class AddSubAccountPage {
	WebDriver driver;
	public AddSubAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchPOS:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchPOS:tableEx1:0:textSearchResults1")
	public WebElement accountcodelink;

	@FindBy(id="searchPOS:textSearchPOSID1")
	public WebElement accountidtxt;

	@FindBy(xpath="/html/body/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/form/table/tbody/tr[4]/td/span[2]")
	public WebElement primaryaccountidspan;

	@FindBy(linkText="Sub Accounts")
	public WebElement subacclnk;

	@FindBy(id="subAccounts:newsubacct")
	public WebElement newsubaccbtn;

	@FindBy(id="addSubAccount:statusList")
	public WebElement subaccstatuslist;

	@FindBy(id="addSubAccount:description")
	public WebElement subaccdescriptiontxt;

	@FindBy(id="addSubAccount:alias")
	public WebElement subaccaliastxt;

	@FindBy(id="addSubAccount:pspFunding")
	public WebElement subaccfinanceprogbtn;

	@FindBy(id="addPOS:addAcctTerminal")
	public WebElement addterminalbtn;

	@FindBy(id="addSubAccount:currency")
	public WebElement currencylist;

	@FindBy(id="addSubAccount:dailyLimit")
	public WebElement subaccdailylimittxt;

	@FindBy(id="addSubAccount:creditBalance")
	public WebElement subacccreditlimittxt;

	@FindBy(id="addSubAccount:pspList")
	public WebElement subaccfinanceprogramlist;

	@FindBy(id="addSubAccount:usageList")
	public WebElement subaccusagelist;

	@FindBy(id="addSubAccount:acountIdentifier")
	public WebElement subaccaccountidentifiertxt;

	@FindBy(id="addSubAccount:accountTypeListPSP")
	public WebElement subaccaccountpsptypelist;

	@FindBy(id="addSubAccount:accountTypeList")
	public WebElement subaccaccounttypelist;

	@FindBy(id="addSubAccount:bankTerminalNo")
	public WebElement subaccbankterminaltxt;

	@FindBy(id="addSubAccount:acquirerBank")
	public WebElement subaccaquirebanklist;

	@FindBy(id="addSubAccount:merchantlissst")
	public WebElement subaccmerchantlist;

	@FindBy(id="addSubAccount:accountNatureList")
	public WebElement subaccaccountnaturelist;

	@FindBy(id="addSubAccount:bankAccount")
	public WebElement bankaccnumbertxt;

	@FindBy(id="addSubAccount:bankBranch")
	public WebElement bankbranchtxt;

	@FindBy(id="addSubAccount:controlAcctsList")
	public WebElement controlacclist;

	@FindBy(id="addSubAccount:saveRole")
	public WebElement subaccsavebtn;

	@FindBy(id="addSubAccount:resetData")
	public WebElement subaccresetbtn;

	@FindBy(id="addSubAccount:CorrectMessage")
	public WebElement subaccsuccessmsg;

	@FindBy(id="subAccounts:subAccountsResultsTable:0:textSearchResults1")
	public WebElement subacccodelink;

	@FindBy(xpath="//*[@id=\"subAccounts:subAccountsResultsTable\"]/tbody")
	WebElement table;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String errorMsgs;

	public String beforeid = "subAccounts:subAccountsResultsTable:";
	public String afterid = ":textSearchResults1";
	int tablesize;

	public String newsubaccountcode;
	public String primaryaccountcode;
	public String successmessage;



	public void  AddSubAccountcommon(AccountPojo addsubaccountobj)
	{
		navigatetoSubAccount(addsubaccountobj);
		//Add New SubAccount btn
		newsubaccbtn.click();

		// Insert SubAccount Data
		if (!addsubaccountobj.getAccountStatus().isEmpty())
		{
			new Select(subaccstatuslist).selectByVisibleText(addsubaccountobj.getAccountStatus());
		}
		subaccdescriptiontxt.sendKeys(addsubaccountobj.getDescription());
		subaccaliastxt.sendKeys(addsubaccountobj.getAccountAlias());

	}

	public void  financecommon(AccountPojo addsubaccountobj)
	{
		subaccfinanceprogbtn.click();
		if (!addsubaccountobj.getCurrency().isEmpty())
		{
			new Select(currencylist).selectByVisibleText(addsubaccountobj.getCurrency());
		}
		subaccdailylimittxt.sendKeys(addsubaccountobj.getDailyLimit());
		subacccreditlimittxt.sendKeys(addsubaccountobj.getCreditLimit());
		if(!addsubaccountobj.getFinanceProgram().isEmpty())
		{
			new Select(subaccfinanceprogramlist).selectByVisibleText(addsubaccountobj.getFinanceProgram());
		}
		if(!addsubaccountobj.getUsage().isEmpty())
		{
			new Select(subaccusagelist).selectByVisibleText(addsubaccountobj.getUsage());
		}
		subaccaccountidentifiertxt.sendKeys(addsubaccountobj.getAccountIdentifier());	
		if(!addsubaccountobj.getAccountType().isEmpty())
		{
			new Select(subaccaccountpsptypelist).selectByVisibleText(addsubaccountobj.getAccountType());
		}
		bankaccnumbertxt.clear();
		bankaccnumbertxt.sendKeys(addsubaccountobj.getSparefield0());

		bankbranchtxt.clear();
		bankbranchtxt.sendKeys(addsubaccountobj.getSparefield1());

		if(!addsubaccountobj.getSparefield2().isEmpty()) 
		{
			new Select(controlacclist).selectByVisibleText(addsubaccountobj.getSparefield2());
		}

	}

	public void  nonFinancecommon(AccountPojo addsubaccountobj)
	{
		if (!addsubaccountobj.getCurrency().isEmpty())
		{
			new Select(currencylist).selectByVisibleText(addsubaccountobj.getCurrency());
		}
		subaccdailylimittxt.sendKeys(addsubaccountobj.getDailyLimit());
		subacccreditlimittxt.sendKeys(addsubaccountobj.getCreditLimit());
		subaccbankterminaltxt.sendKeys(addsubaccountobj.getBankTerminal());

		if(!addsubaccountobj.getAquireBank().isEmpty())
		{
			new Select(subaccaquirebanklist).selectByVisibleText(addsubaccountobj.getAquireBank());
		}
		if (!addsubaccountobj.getMerchantName().isEmpty())
		{
			new Select(subaccmerchantlist).selectByVisibleText(addsubaccountobj.getMerchantName());
		}
		if(!addsubaccountobj.getAccountType().isEmpty())
		{
			new Select(subaccaccounttypelist).selectByVisibleText(addsubaccountobj.getAccountType());
		}
		if(!addsubaccountobj.getAccountNature().isEmpty())
		{
			new Select(subaccaccountnaturelist).selectByVisibleText(addsubaccountobj.getAccountNature());
		}
	}
	public void saveOrReset(AccountPojo addsubaccountobj) 
	{
		if (addsubaccountobj.getAction().contains("Reset")) 
		{
			subaccresetbtn.click();
		}
		else if (addsubaccountobj.getAction().contains("Save"))
		{
			subaccsavebtn.click();
		}
	}

	public String ErrorMessagesAndSuccessMessage(AccountPojo addsubaccountobj) 
	{
		actual = "";

		//when added transaction table shows on web app it means that transaction is successfull
		if (driver.findElements(By.id("addSubAccount:CorrectMessage")).size() != 0){
			actual = subaccsuccessmsg.getText();
			subacclnk.click();
			// Get Latest Added Account
			List<WebElement> row_table = table.findElements(By.tagName("tr"));
			System.out.println(row_table.size());
			tablesize = row_table.size()-1;
			// Get Latest Added Sub Account Code
			WebElement latestaddedsubaccount = driver.findElement(By.id(beforeid+tablesize+afterid));
			newsubaccountcode = latestaddedsubaccount.getText();
			// Primary Account Code
			primaryaccountcode = primaryaccountidspan.getText();
		}
		// collecting error messages in a list 
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

	public void  navigatetoSubAccount(AccountPojo addsubaccountobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(addsubaccountobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();

		//Navigate To SubAccount Menu
		subacclnk.click();
	}

	public void addFinanceSubAccountForedititing(AccountPojo addsubaccountobj)
	{
		navigatetoSubAccount(addsubaccountobj);
		//Add New SubAccount btn
		newsubaccbtn.click();

		// Insert SubAccount Data
		new Select(subaccstatuslist).selectByVisibleText("Active");
		subaccdescriptiontxt.sendKeys("Description");
		subaccaliastxt.sendKeys("Sub accounts alias");

		subaccfinanceprogbtn.click();


		new Select(currencylist).selectByVisibleText("Egypt Pound");

		subaccdailylimittxt.sendKeys("1000");
		subacccreditlimittxt.sendKeys("10000");

		new Select(subaccfinanceprogramlist).selectByVisibleText("TR1 - 11");


		new Select(subaccusagelist).selectByVisibleText("Pharmacy");

		subaccaccountidentifiertxt.sendKeys("5010");	

		new Select(subaccaccountpsptypelist).selectByVisibleText("SDA");

		subaccsavebtn.click();

	}
	public void addnonfinanceSubAccountForedititing(AccountPojo addsubaccountobj)
	{
		navigatetoSubAccount(addsubaccountobj);
		//Add New SubAccount btn
		newsubaccbtn.click();

		// Insert SubAccount Data
		new Select(subaccstatuslist).selectByVisibleText("Active");
		subaccdescriptiontxt.sendKeys("Description");
		subaccaliastxt.sendKeys("Sub accounts alias");

		new Select(currencylist).selectByVisibleText("Egypt Pound");

		subaccdailylimittxt.sendKeys("1000");
		subacccreditlimittxt.sendKeys("10000");
		subaccbankterminaltxt.sendKeys("5");


		new Select(subaccaquirebanklist).selectByVisibleText("QNB");


		new Select(subaccmerchantlist).selectByVisibleText("BANK - BANK");

		new Select(subaccaccounttypelist).selectByVisibleText("Master Card");


		new Select(subaccaccountnaturelist).selectByVisibleText("Purchase account");

		subaccsavebtn.click();

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
    }/ */
}
