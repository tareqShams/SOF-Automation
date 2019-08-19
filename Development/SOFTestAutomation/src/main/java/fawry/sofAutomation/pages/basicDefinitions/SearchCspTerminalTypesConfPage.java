package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.CspAccountTerminalConfPojo;


public class SearchCspTerminalTypesConfPage extends MainPage{
	WebDriver driver;

	public SearchCspTerminalTypesConfPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchCSPAccountTypes:cspList")
	WebElement csp;

	@FindBy(id="searchCSPAccountTypes:terminalTypeList")
	WebElement terminalType; 

	@FindBy(id="searchCSPAccountTypes:searchBtn")
	WebElement search;

	@FindBy(id="searchCSPAccountTypes:resetBtn")
	WebElement reset;

	@FindBy(className="error")
	List<WebElement>  errorMassage;

	@FindBy(xpath="//table[@id='searchCSPAccountTypes:profilesResultsTable']//tbody[1]")
	WebElement table;


	public String  SearchCspTerminalTypesConf(CspAccountTerminalConfPojo CspAccountTerminalConfObj)	
	{
		csp.sendKeys(CspAccountTerminalConfObj.getCspCode());
		terminalType.sendKeys(CspAccountTerminalConfObj.getTerminalType());
		if(CspAccountTerminalConfObj.getFlag().equalsIgnoreCase("search"))
		{
			search.click();
			while(errorMassage.size()!=0)
			{
				return "No Results Found";
			}
			return "search";
		}
		else if (CspAccountTerminalConfObj.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			return "reset";
		}
		return "Failed";
	}
	public void  SearchCspTerminalTypesConfForEdit(CspAccountTerminalConfPojo CspAccountTerminalConfObj)	
	{
		csp.sendKeys(CspAccountTerminalConfObj.getCspCode());
		terminalType.sendKeys(CspAccountTerminalConfObj.getTerminalType());
		
			search.click();
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			List<WebElement> Columns_row = rows_table.get(0).findElements(By.tagName("td"));
			Columns_row.get(0).click();
		
			
	}
	
	
	
	public boolean reset()
	{
		Select csp1=new Select(csp);
		Select terminalType2=new Select(terminalType);
		if (csp1.getFirstSelectedOption().getText().equalsIgnoreCase("- All -")
				&&terminalType2.getFirstSelectedOption().getText().equalsIgnoreCase("- All -") )
		{
			return true;
		}
		return false;
	}

}
