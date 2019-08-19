package fawry.sofAutomation.pages.basicDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramPojo;

public class AddFinanceProgramPage extends MainPage{
	WebDriver driver;

	public AddFinanceProgramPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="addFinanceProgram:programTypeList")
	WebElement programType;

	@FindBy(id="addFinanceProgram:Code")
	WebElement code;

	@FindBy(id="addFinanceProgram:name")
	WebElement name;

	@FindBy(id="addFinanceProgram:textareaDescription1")
	WebElement description;

	@FindBy(id="addFinanceProgram:maxFacilityLimit")
	WebElement maximumFacilityLimit;

	@FindBy(id="addFinanceProgram:validateSetupTimevalidateSetupTime")
	WebElement validateSetupTime;

	@FindBy(id="addFinanceProgram:disableAccountRouting")
	WebElement disableAccountRouting;

	@FindBy(id="addFinanceProgram:MainAccountReturn")
	WebElement mainAccountReturn;

	@FindBy(id="addFinanceProgram:enableEstatment")
	WebElement enableEStatment;

	@FindBy(id="addFinanceProgram:saveBtn")
	WebElement save;

	@FindBy(id="addFinanceProgram:resetBtn")
	WebElement reset;

	@FindBy(id="addFinanceProgram:cancelBtn")
	WebElement cancel;

	@FindBy(xpath="//*[@class='error' or @class='alert']")
	List<WebElement> Massage;

	@FindBy(className="fieldError" )
	List<WebElement> fieldError;

	@FindBy(id="addFinanceProgram:textNumberOfDays")
	WebElement textNumberOfDays;

	@FindBy(id="addFinanceProgram:estatmentPerVisits")
	WebElement estatmentPerVisits;


	@FindBy(id="addFinanceProgram:estatmentBlockInDebit")
	WebElement estatmentBlockInDebit;

	@FindBy(id="addFinanceProgram:estatmentActivateInNotify")
	WebElement estatmentActivateInNotify;

	public String  AddFinanceProgram(FinanceProgramPojo FinanceProgramObj) throws InterruptedException	
	{	Select programType1=new Select(programType);
	programType1.selectByVisibleText(FinanceProgramObj.getProgramType());
	Thread.sleep(2000);
	code.clear();
	code.sendKeys(FinanceProgramObj.getCode());
	name.clear();
	name.sendKeys(FinanceProgramObj.getName());
	description.clear();
	description.sendKeys(FinanceProgramObj.getDescription());
	maximumFacilityLimit.clear();
	maximumFacilityLimit.sendKeys(FinanceProgramObj.getMaximumFacilityLimit());


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
		enableEStatment.click();
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


	if(FinanceProgramObj.getFlag().equals("save"))
	{
		save.click();
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
	else if(FinanceProgramObj.getFlag().equals("reset"))
	{
		reset.click();
		return "reset";
	}
	else if(FinanceProgramObj.getFlag().equals("cancle"))
	{
		cancel.click();
		return "cancle";
	}

	return "fail";
	}
	public boolean reset()
	{
		Select programType1=new Select(programType);
		if(programType1.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& code.getText().isEmpty()
				&& name.getText().isEmpty()
				&& description.getText().isEmpty()
				&& maximumFacilityLimit.getText().isEmpty())
		{

			return false;
		}

		return false;

	}


}
