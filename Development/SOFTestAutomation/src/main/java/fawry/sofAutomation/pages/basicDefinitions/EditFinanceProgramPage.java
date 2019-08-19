package fawry.sofAutomation.pages.basicDefinitions;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramPojo;

public class EditFinanceProgramPage extends MainPage{
	WebDriver driver;

	public EditFinanceProgramPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="editFinanceProgram:name")
	WebElement name;

	@FindBy(id="editFinanceProgram:textareaDescription1")
	WebElement description; 

	@FindBy(id="editFinanceProgram:maxFacilityLimit")
	WebElement maxFacilityLimit;

	@FindBy(id="editFinanceProgram:validateSetupTime")
	WebElement validateSetupTime;

	@FindBy(id="editFinanceProgram:disableAccountRouting")
	WebElement disableAccountRouting;

	@FindBy(id="editFinanceProgram:MainAccountReturn")
	WebElement mainAccountReturn;


	@FindBy(id="editFinanceProgram:enableEstatment")
	WebElement enableEstatment;


	@FindBy(id="editFinanceProgram:textNumberOfDays")
	WebElement textNumberOfDays;


	@FindBy(id="editFinanceProgram:estatmentPerVisits")
	WebElement estatmentPerVisits;


	@FindBy(id="editFinanceProgram:estatmentBlockInDebit")
	WebElement estatmentBlockInDebit;

	@FindBy(id="editFinanceProgram:estatmentActivateInNotify")
	WebElement estatmentActivateInNotify;

	@FindBy(id="editFinanceProgram:saveBtn")
	WebElement saveBtn;

	@FindBy(id="editFinanceProgram:resetBtn")
	WebElement resetBtn;

	@FindBy(id="editFinanceProgram:delete")
	WebElement delete;

	@FindBy(id="editFinanceProgram:backBtn")
	WebElement backBtn;

	@FindBy(xpath="//*[@class='error' or @class='alert']")
	List<WebElement> Massage;

	@FindBy(className="fieldError" )
	List<WebElement> fieldError;


	public String  EditFinanceProgram(FinanceProgramPojo FinanceProgramObj)	
	{
		if(!FinanceProgramObj.getName().isEmpty())
		{
			name.clear();
			name.sendKeys(FinanceProgramObj.getName());
		}
		if(!FinanceProgramObj.getDescription().isEmpty())
		{
			description.clear();
			description.sendKeys(FinanceProgramObj.getDescription());
		} 
		if(!FinanceProgramObj.getMaximumFacilityLimit().isEmpty())
		{
			maxFacilityLimit.clear();
			maxFacilityLimit.sendKeys(FinanceProgramObj.getMaximumFacilityLimit());
		}
		if(!FinanceProgramObj.getValidateSetupTime().isEmpty())
		{
			validateSetupTime.click();
		}
		if(!FinanceProgramObj.getDisableAccountRouting().isEmpty())
		{
			disableAccountRouting.click();
		}
		if(!FinanceProgramObj.getMainAccountReturn().isEmpty())
		{
			mainAccountReturn.click();
		}
		if(!FinanceProgramObj.getEnableEStatment().isEmpty())
		{
			enableEstatment.click();
			textNumberOfDays.clear();
			textNumberOfDays.sendKeys(FinanceProgramObj.getNumOfDays());
			if(!FinanceProgramObj.getDueDatePerVisits().isEmpty())
			{
				estatmentPerVisits.click();
			}
			if(!FinanceProgramObj.getBlockSetupInDebit().isEmpty())
			{
				estatmentBlockInDebit.click();
			}
			if(!FinanceProgramObj.getReactivateSetupInCredit().isEmpty())
			{
				estatmentActivateInNotify.click();
			}

		}
		
		if(FinanceProgramObj.getFlag().equalsIgnoreCase("save"))
		{ 
			saveBtn.click();
			String msg;
			if (fieldError.size()!=0) {
				msg = fieldError.get(0).getText().toString();
				for(int i=1;i<fieldError.size();i++)
				{
					msg=msg+"/"+fieldError.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}

			else if(Massage.size()!=0)
			{
				System.out.println(Massage.get(0).getText().toString());
				return Massage.get(0).getText().toString();
			}
		
		}
		else if(FinanceProgramObj.getFlag().equalsIgnoreCase("reset"))
		{
			resetBtn.click();
			return "reset";
		}
		else if(FinanceProgramObj.getFlag().equalsIgnoreCase("delete"))
		{
			delete.click();
			return Massage.get(0).getText().toString();
		}
		else if(FinanceProgramObj.getFlag().equalsIgnoreCase("back"))
		{
			backBtn.click();
			return "back";
		}
		return "fail";
	}
}
