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

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class AddControlAccountPage {
	WebDriver driver;
	public AddControlAccountPage(WebDriver driver)
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

	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[4]/td[1]/span[2]")
	public WebElement primaryaccountidspan;

	@FindBy(linkText="Control Accounts")
	public WebElement controlacclnk;

	@FindBy(id="subAccounts:newsubacct")
	public WebElement newControlaccbtn;

	@FindBy(id="addSubAccount:statusList")
	public WebElement controlaccstatuslist;

	@FindBy(id="addSubAccount:description")
	public WebElement controlaccdescriptiontxt;

	@FindBy(id="addSubAccount:alias")
	public WebElement controlaccaliastxt;

	@FindBy(id="addSubAccount:orgList")
	public WebElement psplist;

	@FindBy(id="addSubAccount:creditBalance")
	public WebElement controlacccreditlimittxt;

	@FindBy(id="addSubAccount:bankBranch")
	public WebElement pspbranchcodetxt;

	@FindBy(id="addSubAccount:bankAccount")
	public WebElement controlaccbanknotxt;

	@FindBy(id="addSubAccount:textExpiryDate")
	public WebElement controlaccountexpirationdate;

	@FindBy(id="addSubAccount:accountTypeListPSP")
	public WebElement controlaccaccounttypelist;

	@FindBy(id="addSubAccount:acountIdentifier")
	public WebElement controlaccaccountidentifiertxt;

	@FindBy(id="addSubAccount:saveRole")
	public WebElement contaccsavebtn;

	@FindBy(id="addSubAccount:resetData")
	public WebElement subaccresetbtn;

	@FindBy(id="addSubAccount:CorrectMessage")
	public WebElement controlaccsuccessmsg;
	
	@FindBy(xpath="//input[@title='Next']")
	WebElement nexttablebtn;

	@FindBy(id="subAccounts:subAccountsResultsTable:0:textSearchResults1")
	public WebElement subacccodelink;

	@FindBy(xpath="//*[@id=\"subAccounts:subAccountsResultsTable\"]/tbody")
	WebElement table;

	@FindBy(className="fieldError")
	public List<WebElement> pageErrorMsgsList;

	public 	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());
	public String beforeid = "subAccounts:subAccountsResultsTable:";
	public String afterid = ":textSearchResults1";
	int tablesize;

	public String newcontrolaccountcode;
	public String primaryaccountcode;
	public String actualaddcontrolaccount = "";
	public String errorMsgs;



	public String  AddControlAccount(AccountPojo addcontrolaccountobj)
	{
		navigatetocontrolAccount(addcontrolaccountobj);
		//Add New Control Account btn
		newControlaccbtn.click();

		// Insert SubAccount Data
		if (!addcontrolaccountobj.getTerminalStatus().isEmpty())
		{
			new Select(controlaccstatuslist).selectByVisibleText(addcontrolaccountobj.getTerminalStatus());
		}
		controlaccdescriptiontxt.clear();
		controlaccdescriptiontxt.sendKeys(addcontrolaccountobj.getDescription()+timestamp);
		addcontrolaccountobj.setDescription(addcontrolaccountobj.getDescription()+timestamp);
		controlaccaliastxt.clear();
		controlaccaliastxt.sendKeys(addcontrolaccountobj.getAccountAlias());
		controlacccreditlimittxt.clear();
		controlacccreditlimittxt.sendKeys(addcontrolaccountobj.getCreditLimit());
		if (!addcontrolaccountobj.getPsp().isEmpty())
		{
			new Select(psplist).selectByVisibleText(addcontrolaccountobj.getPsp());
		}
		pspbranchcodetxt.clear();
		pspbranchcodetxt.sendKeys(addcontrolaccountobj.getPspCode());
		//using bank Terminal as bank no instead of creating extra variable in accounts pojo
		controlaccbanknotxt.clear();
		controlaccbanknotxt.sendKeys(addcontrolaccountobj.getBankTerminal());
		if (!addcontrolaccountobj.getAccountType().isEmpty())
		{
			new Select(controlaccaccounttypelist).selectByVisibleText(addcontrolaccountobj.getAccountType());
		}
		controlaccountexpirationdate.clear();
		controlaccountexpirationdate.sendKeys(addcontrolaccountobj.getExpirationDate());
		controlaccaccountidentifiertxt.clear();
		controlaccaccountidentifiertxt.sendKeys(addcontrolaccountobj.getAccountIdentifier()+timestamp);

		if (addcontrolaccountobj.getAction().contains("Reset")) 
		{
			subaccresetbtn.click();
			actualaddcontrolaccount = controlaccdescriptiontxt.getText()+controlaccaliastxt.getText()+controlacccreditlimittxt.getText()+pspbranchcodetxt.getText()+controlaccaccountidentifiertxt.getText();
		}
		else 
		{
			contaccsavebtn.click();

			// CHECK FOR ERROR MESSAGES
			if (driver.findElements(By.className("fieldError")).size() != 0  )
			{
				errorMsgs = pageErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<pageErrorMsgsList.size();i++)
				{
					errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
				}
				actualaddcontrolaccount = errorMsgs;
			}
			else if(driver.findElements(By.id("addSubAccount:CorrectMessage")).size() != 0)
			{
				// Get Success Message And SubAccount ID
				actualaddcontrolaccount = controlaccsuccessmsg.getText();
				controlacclnk.click();
				
				// Get Latest Added Account Code
				List<WebElement> row_table = table.findElements(By.tagName("tr"));
				System.out.println(row_table.size());
				tablesize = row_table.size()-1;
				// Get Latest Added Sub Account Code
				WebElement latestaddedcontrolaccount = driver.findElement(By.id(beforeid+tablesize+afterid));
				newcontrolaccountcode = latestaddedcontrolaccount.getText();
				// Primary Account Code
				primaryaccountcode = primaryaccountidspan.getText();
			}
		}


		return actualaddcontrolaccount;

	}


	public void  navigatetocontrolAccount(AccountPojo addsubaccountobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(addsubaccountobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();

		//Navigate To SubAccount Menu
		controlacclnk.click();
	}

}
