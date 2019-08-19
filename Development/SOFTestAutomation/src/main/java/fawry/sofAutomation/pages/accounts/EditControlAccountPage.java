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

public class EditControlAccountPage {
	WebDriver driver;
	public EditControlAccountPage(WebDriver driver)
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

	@FindBy(xpath="//*[@id=\"subAccounts:subAccountsResultsTable\"]/tbody")
	WebElement table;

	@FindBy(linkText="Control Accounts")
	WebElement controlacclnk;


	@FindBy(id="EditControlAccount:bankLoanAcctNoIn")
	WebElement bankaccountnotxt;

	@FindBy(id="EditControlAccount:statusList")
	WebElement statuslist;

	@FindBy(id="EditControlAccount:creditLimitIn")
	WebElement creditlimittxt;


	@FindBy(id="EditControlAccount:dailyCreditLimitIn")
	WebElement dailylimittxt;

	@FindBy(id="EditControlAccount:acctExpiryDateIN")
	WebElement expirationdate;
	
	@FindBy(id="EditControlAccount:saveBtn")
	WebElement savebtn;

	@FindBy(id="EditControlAccount:resetBtn")
	WebElement resetbtn;

	@FindBy(id="EditControlAccount:backBtn")
	WebElement backbtn;

	@FindBy(id="EditControlAccount:correctMessage")
	WebElement successmsg;

	@FindBy(id="EditSubAccount:CorrectMessage")
	WebElement editsubaccaccountsuccessmsg;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	WebElement headerErrorMsgsList;


	String beforeid = "subAccounts:subAccountsResultsTable:";
	String afterid = ":textSearchResults1";
	int tablesize;
	String actualeditcontrolaccount;
	public String controlaccountcodeforDBcheck;
	String errorMsgs;


	public String  editControlAccount(AccountPojo editcontrolaccountobj)
	{
		navigatetoControlAccount(editcontrolaccountobj);
		tablesize();
		//Click On The latest Added SubAccount
		WebElement latestaddedcontrolaccount = driver.findElement(By.id(beforeid+tablesize+afterid));
		controlaccountcodeforDBcheck = latestaddedcontrolaccount.getText();
		latestaddedcontrolaccount.click();

		// Update Data
		if(!editcontrolaccountobj.getBankTerminal().isEmpty()) 
		{
			bankaccountnotxt.clear();
			bankaccountnotxt.sendKeys(editcontrolaccountobj.getBankTerminal());
		}
		if (!editcontrolaccountobj.getTerminalStatus().isEmpty())
		{
			new Select(statuslist).selectByVisibleText(editcontrolaccountobj.getTerminalStatus());
		}
		if (!editcontrolaccountobj.getCreditLimit().isEmpty())
		{
			creditlimittxt.clear();
			creditlimittxt.sendKeys(editcontrolaccountobj.getCreditLimit());
		}
		if (!editcontrolaccountobj.getDailyLimit().isEmpty())
		{
			dailylimittxt.clear();
			dailylimittxt.sendKeys(editcontrolaccountobj.getDailyLimit());
		}
		if (!editcontrolaccountobj.getExpirationDate().isEmpty())
		{
			expirationdate.clear();
			expirationdate.sendKeys(editcontrolaccountobj.getExpirationDate());
		}
		if(editcontrolaccountobj.getAction().contains("Back"))
		{
			backbtn.click();
			actualeditcontrolaccount = driver.getCurrentUrl();
		}
		else if (editcontrolaccountobj.getAction().contains("Reset"))
		{
			resetbtn.click();
			actualeditcontrolaccount = "";
		}
		else if (editcontrolaccountobj.getAction().contains("Save"))
		{
			savebtn.click();
			if (driver.findElements(By.className("fieldError")).size() != 0 )
			{
				errorMsgs = pageErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<pageErrorMsgsList.size();i++)
				{
					errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
				}
				actualeditcontrolaccount = errorMsgs;
			}
			else if (driver.findElements(By.className("error")).size() != 0 )
			{
				actualeditcontrolaccount = headerErrorMsgsList.getText();
			}
			else if (driver.findElements(By.id("EditControlAccount:correctMessage")).size() != 0 )
			{
				actualeditcontrolaccount = successmsg.getText();
			}

		}
		return actualeditcontrolaccount;

	}


	public void  navigatetoControlAccount(AccountPojo editsubaccountobj)
	{
		//Navigate To SearchPage
		driver.navigate().to(Constants.SEARCH_ACCOUNT_URL);
		// Search with account id
		accountidtxt.clear();
		accountidtxt.sendKeys(editsubaccountobj.getAccountCode());
		searchtbtn.click();
		accountcodelink.click();

		//Navigate To SubAccount Menu
		controlacclnk.click();
	}

	public void tablesize() 
	{
		List<WebElement> row_table = table.findElements(By.tagName("tr"));
		System.out.println(row_table.size());
		tablesize = row_table.size()-1;

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

