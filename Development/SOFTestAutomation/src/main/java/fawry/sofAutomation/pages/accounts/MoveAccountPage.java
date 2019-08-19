package fawry.sofAutomation.pages.accounts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.AccountPojo;;

public class MoveAccountPage {
	WebDriver driver;

	public MoveAccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="form1:textPosid1")
	public WebElement accountcodetxt;

	@FindBy(id="form1:checkAccount")
	public WebElement checkaccountbtn;

	@FindBy(id="form1:resetAccount")
	public WebElement resetaccountbtn;

	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[2]/td[2]")
	WebElement merchantname;

	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[2]")
	WebElement merchantcode;

	@FindBy(xpath="/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[5]/td[2]")
	WebElement csp;

	@FindBy(id="form1:corporate")
	WebElement destinationcsplist;

	@FindBy(id="form1:moveAccount")
	WebElement moveaccountbtn;

	@FindBy(className="errorWarning")
	public List<WebElement> HeaderErrorMsgsList;

	@FindBy(className="fieldError")
	WebElement pageerrors;

	@FindBy(className="alert")
	WebElement successmsg;

	String actualmoveaccount;
	String errorMsgs;
	public String newCsp;
	public String newMercahnt;


	public String  moveAccount(AccountPojo moveaccountobj) throws InterruptedException
	{
		driver.navigate().to(Constants.MOVE_ACCOUNT_URL);
		resetaccountbtn.click();
		accountcodetxt.clear();
		accountcodetxt.sendKeys(moveaccountobj.getAccountCode());
		checkaccountbtn.click();

		// Check For absence of Error messages and read messages
		if (driver.findElements(By.className("errorWarning")).size() != 0 )
		{
			errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<HeaderErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
			}
			actualmoveaccount = errorMsgs;
		}
		else if (driver.findElements(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[3]/td[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[1]/td[2]")).size() != 0 )
		{
			System.out.println(csp.getText());
			System.out.println(merchantname.getText());
			new Select(destinationcsplist).selectByVisibleText(moveaccountobj.getCsp());
			moveaccountbtn.click();
			if (moveaccountobj.getAction().contains("Fail"))
			{
				errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<HeaderErrorMsgsList.size();i++)
				{
					errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
				}
				actualmoveaccount = errorMsgs;
			}
			else if (moveaccountobj.getAction().contains("Success"))
			{
				actualmoveaccount = successmsg.getText();

				// Resends Account Info to check Success of editing
				accountcodetxt.clear();
				accountcodetxt.sendKeys(moveaccountobj.getAccountCode());
				checkaccountbtn.click();
				// Assign new Values into Variables
				newMercahnt = merchantname.getText();
				moveaccountobj.setMerchantName(newMercahnt);
				newCsp = csp.getText();
			}
		}
		else if (driver.findElements(By.className("fieldError")).size() != 0 ) 
		{
			actualmoveaccount = pageerrors.getText();
		}


		return actualmoveaccount;

	}


	public void movetoMoveAccountpage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Move Account"));
		action.moveToElement(selecthover);
		action.click();
		action.perform();

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

