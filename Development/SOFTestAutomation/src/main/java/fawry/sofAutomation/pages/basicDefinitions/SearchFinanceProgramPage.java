package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramPojo;

public class SearchFinanceProgramPage extends MainPage {
	WebDriver driver;

	public SearchFinanceProgramPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="searchBiller:code")
	WebElement Code;

	@FindBy(id="searchBiller:progType")
	WebElement ProgramType; 

	@FindBy(id="searchBiller:searchBtn")
	WebElement search;

	@FindBy(id="searchBiller:resetBtn")
	WebElement reset;

	@FindBy(xpath="//table[@id='searchBiller:profilesResultsTable']//tbody[1]")
	WebElement table;

	
	public void  SearchFinanceProgram(FinanceProgramPojo FinanceProgramObj)	
	{
		Code.sendKeys(FinanceProgramObj.getCode());
		ProgramType.sendKeys(FinanceProgramObj.getProgramType());
    	search.click();
		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		for(int i=0; i<rows_table.size();i++){
		List<WebElement> Columns_row = rows_table.get(i).findElements(By.tagName("td"));
		if(!FinanceProgramObj.getCode().isEmpty())
		{
			Columns_row.get(0).click();

		}
		else{
			if(Columns_row.get(0).getText().equalsIgnoreCase(FinanceProgramObj.getCode())
					&&Columns_row.get(2).getText().equalsIgnoreCase(FinanceProgramObj.getProgramType()) )
			{
				Columns_row.get(0).click();

			}
		}
		}
		
			
	}

}
