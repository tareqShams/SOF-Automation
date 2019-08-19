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
import fawry.sofAutomation.pojos.accounts.TerminalPojo;;

public class SearchTerminalPage {
	WebDriver driver;

	public SearchTerminalPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchTerminals:TerminalCodeLabeld")
	public WebElement terminalcodetxt;

	@FindBy(id="searchTerminals:terminalType9")
	public WebElement terminaltypelist;

	@FindBy(id="searchTerminals:serialNumberLabel")
	public WebElement serialnumbertxt;

	@FindBy(id="searchTerminals:spareTer:0")
	public WebElement spareterminalbtn;

	@FindBy(id="searchTerminals:spareTer:1")
	public WebElement normalterminalbtn;
	
	@FindBy(id="searchTerminals:accountsResultsTable:0:textSearchResults11")
	public WebElement terminalcodelink;

	@FindBy(id="searchTerminals:button1")
	public WebElement searchtbtn;

	@FindBy(id="searchTerminals:button2")
	public WebElement resetbtn;

	@FindBy(className="fieldError")
	public List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	public List<WebElement> HeaderErrorMsgsList;

	String actualsearchterminal;
	String errorMsgs;

	public String  SearchTerminal(TerminalPojo searchTermobj) throws InterruptedException
	{
		driver.navigate().to(Constants.SEARCH_TERMINAL_URL);
		resetbtn.click();
		if (searchTermobj.getTerminalCode() != "" )
		{
			terminalcodetxt.clear();
			terminalcodetxt.sendKeys(searchTermobj.getTerminalCode());
		}
		else {
			if (searchTermobj.getTerminalType() != "" )
			{
				new Select(terminaltypelist).selectByVisibleText(searchTermobj.getTerminalType());
			}
			serialnumbertxt.clear();
			serialnumbertxt.sendKeys(searchTermobj.getSeriallNumber());

			if (searchTermobj.getSapreNormalType().equalsIgnoreCase("Spare")) {
				spareterminalbtn.click();		
			}
			if (searchTermobj.getSapreNormalType().equalsIgnoreCase("Normal"))
			{
				normalterminalbtn.click();
			}
		}
		if(searchTermobj.getAction().contains("Reset"))
		{
			resetbtn.click();
			String emptycode = terminalcodetxt.getText();
			String emptyserial = serialnumbertxt.getText();
			actualsearchterminal = emptycode+emptyserial;
		}

		if(searchTermobj.getAction().contains("Search")) 
		{
			searchtbtn.click();
			// Check For absence of Error messages and read messages
			if (driver.findElements(By.className("fieldError")).size() != 0  )
			{
				errorMsgs = pageErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<pageErrorMsgsList.size();i++)
				{
					errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
				}
				actualsearchterminal = errorMsgs;
			}
			else if ( driver.findElements(By.className("error")).size() != 0)
			{
				errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
				for(int i=1;i<HeaderErrorMsgsList.size();i++)
				{
					errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
				}
				actualsearchterminal = errorMsgs;
			}
			else if (driver.findElements(By.xpath("//*[@id=\"searchTerminals:accountsResultsTable\"]/tbody")).size() != 0  )
			{
				if (searchTermobj.getAction().contains("ClickOnCode"))
				{
					terminalcodelink.click();
					actualsearchterminal = driver.getCurrentUrl();
				}
				if (searchTermobj.getAction().equalsIgnoreCase("SearchSuccess"))
				{
				actualsearchterminal = "Success";
				}
			}
		}

		return actualsearchterminal;

	}


	public void movetoSearchTerminalpage () {

		WebElement hover = driver.findElement(By.linkText("Accounts"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Search Terminals"));
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

