package fawry.sofAutomation.pages.accounts;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.constants.accounts.Constants;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;

public class ChangeTerminalPinPage 
{

	WebDriver driver;

	public ChangeTerminalPinPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="changePin:code1")
	WebElement accountcodetxt;

	@FindBy(id="changePin:corporate")
	WebElement csplist;

	@FindBy(id="changePin:termType")
	WebElement terminaltypelist;

	@FindBy(id="changePin:sn")
	WebElement deviceidtxt;

	@FindBy(id="changePin:newPin1")
	WebElement newpintxt;

	@FindBy(id="changePin:confirmPin1")
	WebElement confirmpintxt;

	@FindBy(className="fieldError")
	public List<WebElement> pageerrormsgs;

	@FindBy(className="error")
	public List<WebElement> HeaderErrorMsgs;

	@FindBy(className="alert")
	WebElement successmsg;

	@FindBy(id="changePin:button1")
	public WebElement terminalsavebtn;

	@FindBy(id="changePin:button2")
	public WebElement terminalResetbtn;

	public String errorMsgs;
	public String returnChangedTerminalPin;
	public static String HashReturn;
	public 	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());

	public String  ChangeTerminalPin(TerminalPojo terminalobj)
	{
		driver.navigate().to(Constants.CHANGE_TERMINAL_PIN_URL);
		terminalResetbtn.click();

		accountcodetxt.clear();
		accountcodetxt.sendKeys(terminalobj.getAccountcode());

		if(!terminalobj.getTerminalCsp().isEmpty() ) 
		{
			new Select(csplist).selectByVisibleText(terminalobj.getTerminalCsp());
		}
		if (!terminalobj.getTerminalType().isEmpty())
		{
			new Select(terminaltypelist).selectByVisibleText(terminalobj.getTerminalType());
		}
		deviceidtxt.clear();
		deviceidtxt.sendKeys(terminalobj.getSeriallNumber());

		newpintxt.clear();
		newpintxt.sendKeys(terminalobj.getTerminalPin());

		confirmpintxt.clear();
		confirmpintxt.sendKeys(terminalobj.getTerminalConfirmPin());

		terminalsavebtn.click();

		//System.out.println(driver.findElements(By.className("fieldError")).size());
		if (driver.findElements(By.className("fieldError")).size() != 0)
		{
			// Read All Error messages in a List
			errorMsgs = pageerrormsgs.get(0).getText().toString();
			for(int i=1;i<pageerrormsgs.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageerrormsgs.get(i).getText().toString();
			}
			returnChangedTerminalPin = errorMsgs;
		}
		else if (driver.findElements(By.className("alert")).size() != 0)
		{
			returnChangedTerminalPin = successmsg.getText();
		}

		return returnChangedTerminalPin;
	}

	public static String Hashing(String input, String hashtype)
	{
		if(hashtype.equalsIgnoreCase("MD5"))
		{
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigist = md.digest(input.getBytes());
				BigInteger number = new BigInteger(1, messageDigist);
				String hashtext = number.toString(16);
				while (hashtext.length() < 32)
				{
					hashtext = "0" + hashtext;
				}
				HashReturn = hashtext;
			} catch (NoSuchAlgorithmException e) {
				HashReturn = e.getMessage();
			}
		}
		else if (hashtype.equalsIgnoreCase("SHA1"))
		{
			try {
				MessageDigest md = MessageDigest.getInstance("SHA1");
				byte[] messageDigist = md.digest(input.getBytes());
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < messageDigist.length; i++)
				{
					sb.append(Integer.toString((messageDigist[i]& 0xff)+ 0x100, 16).substring(1));	
				}
				HashReturn = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				HashReturn = e.getMessage();
			}
		}
		return HashReturn;
	}

	public void movetoChangeTerminalPinpage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Change Terminal PIN"));
		action.moveToElement(selecthover);
		action.click();
		action.perform();

	}


}
