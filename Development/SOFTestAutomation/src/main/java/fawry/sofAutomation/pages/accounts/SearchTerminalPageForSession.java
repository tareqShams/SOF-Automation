package fawry.sofAutomation.pages.accounts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.accounts.TerminalPojo;

public class SearchTerminalPageForSession {

	WebDriver driver;

	public SearchTerminalPageForSession(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="searchTerminals:TerminalCodeLabeld")
	WebElement terminalCodeTxt;
	
	@FindBy(id="searchTerminals:terminalType9")
	WebElement terminalTypeList;
	
	@FindBy(id="searchTerminals:serialNumberLabel")
	WebElement serialNumberTxt;
	
	@FindBy(xpath="//input[@name='searchTerminals:spareTer']")
	List<WebElement> optionsBtns;
	
	@FindBy(id="searchTerminals:button1")
	WebElement searchBtn;
	
	@FindBy(id="searchTerminals:button2")
	WebElement resetBtn;

	public void mainFields(TerminalPojo termObj) {
		
		terminalCodeTxt.sendKeys(termObj.getTerminalCode());
		
		new Select(terminalTypeList).selectByVisibleText(termObj.getTerminalType());
		
		if(termObj.getAction().contains("Spare"))
		{
			optionsBtns.get(0).click();
		}
		else if (termObj.getAction().contains("Normal"))
		{
			optionsBtns.get(1).click();
		}
	}
}
