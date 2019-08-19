package fawry.sofAutomation.pages.basicDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pages.admin.MainPage;
import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramSetupPojo;

public class AddFinanceProgramSetupPage extends MainPage{
	WebDriver driver;

	public AddFinanceProgramSetupPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="overDraft:finance")
	WebElement financeProgram;

	@FindBy(id="overDraft:billingAccount")
	WebElement mainAccount;

	@FindBy(id="overDraft:subAcct")
	WebElement subAccount;

	@FindBy(id="overDraft:classCode")
	WebElement accountClassification;

	@FindBy(id="overDraft:textAmount")
	WebElement maximumAmount;

	@FindBy(id="overDraft:openDate")
	WebElement openDate;

	@FindBy(id="overDraft:closeDate")
	WebElement closeDate;

	@FindBy(id="overDraft:overDraftEnabledTime")
	WebElement enableTime;

	@FindBy(id="overDraft:overDraftTimeClosed")
	WebElement disableTime;

	@FindBy(id="overDraft:minfaults")
	WebElement numOfMinFaultPayments;

	@FindBy(id="overDraft:fullfaults")
	WebElement numOfFullFaultPayments;

	@FindBy(id="overDraft:AllBillTypesList")
	WebElement billTypes;

	@FindBy(id="overDraft:moveBillTypesToSelected")
	WebElement moveBillTypesToSelected;

	@FindBy(id="overDraft:AllBillTypesProfilesList")
	WebElement billTypeProfiles;

	@FindBy(id="overDraft:moveBillTypesToSelected2")
	WebElement moveBillTypesToSelected2;


	@FindBy(id="overDraft:rollOver")
	WebElement rollOver; 

	@FindBy(id="overDraft:saveBtn")
	WebElement save;

	@FindBy(id="overDraft:resetBtn")
	WebElement reset;

	@FindBy(xpath="//*[@class='error' or @class='fieldError'or @class='alert']")
	List<WebElement> Massage;



	@FindBy(id="editCSPAccTypeConf:delete")
	WebElement delete;

	@FindBy(id="editCSPAccTypeConf:backBtn")
	WebElement back;
public ArrayList<String> billType=new ArrayList<>();
public ArrayList<String> billTypeProfile=new ArrayList<>();

	public String  FinanceProgramSetup(FinanceProgramSetupPojo financeProgramSetupObj) throws InterruptedException	
	{
		List<WebElement> options=new Select(financeProgram).getOptions();
		for (WebElement option : options) {
		    if (option.getText().contains(financeProgramSetupObj.getFinanceProgram())) {
		        option.click();
		        break;
		    }
		}
		mainAccount.sendKeys(financeProgramSetupObj.getMainAccount());
		accountClassification.sendKeys(financeProgramSetupObj.getAccountClassification());

		if(financeProgramSetupObj.getFlag().contains("subAccount"))
		{
			subAccount.sendKeys(financeProgramSetupObj.getSubAccount());
		}
		maximumAmount.sendKeys(financeProgramSetupObj.getMaximumAmount());
		openDate.sendKeys(financeProgramSetupObj.getOpenDate());
		closeDate.sendKeys(financeProgramSetupObj.getCloseDate());
		enableTime.sendKeys(financeProgramSetupObj.getEnableTime());
		disableTime.sendKeys(financeProgramSetupObj.getDisableTime());
		numOfMinFaultPayments.sendKeys(financeProgramSetupObj.getNumOfMinFaultPayments());
		numOfFullFaultPayments.sendKeys(financeProgramSetupObj.getNumOfFullFaultPayments());

		if(!financeProgramSetupObj.getRollOver().isEmpty())
		{
			rollOver.click();
		}

		Select select = new Select( billTypes);
		Actions builder = new Actions(driver);
		if(select.getOptions().size()!=0)
		{
			for(int i=1 ; i<2;i++)
			{
					builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i)).keyUp(Keys.CONTROL);
					builder.build().perform(); 
					billType.add(select.getOptions().get(i).getText());
					moveBillTypesToSelected.click();
					Thread.sleep(2000);
				}
		}
		Select select1 = new Select( billTypeProfiles);
		Actions builder1 = new Actions(driver);
		if(select1.getOptions().size()!=0)
		{
			for(int i=1 ; i<3;i++)
			{
					builder1.keyDown(Keys.CONTROL).click(select1.getOptions().get(i)).keyUp(Keys.CONTROL);
					builder1.build().perform(); 
					billTypeProfile.add(select1.getOptions().get(i).getText());
					moveBillTypesToSelected2.click();
					Thread.sleep(2000);
			}	
		}
			
			

		


		if(financeProgramSetupObj.getFlag().contains("save"))
		{
			save.click();
			String msg;
			if (Massage.size()!=0) {
				msg = Massage.get(0).getText().toString();
				for(int i=1;i<Massage.size();i++)
				{
					msg=msg+"/"+Massage.get(i).getText().toString();
				}
				System.out.println(msg);
				return msg;
			}
		}
		else if(financeProgramSetupObj.getFlag().contains("reset"))
		{
			reset.click();
			return "reset";
		}
		return "fail"; 
	}

	public boolean reset()
	{
		Select financeProgramSelect=new Select(financeProgram);
		Select accountClassificationSelect=new Select(accountClassification);


		if(financeProgramSelect.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& mainAccount.getAttribute("value").isEmpty()
				&& subAccount.getAttribute("value").isEmpty()
				&& accountClassificationSelect.getFirstSelectedOption().getText().equalsIgnoreCase("- Please Select -")
				&& maximumAmount.getAttribute("value").isEmpty()
				&& openDate.getAttribute("value").isEmpty()
				&& closeDate.getAttribute("value").isEmpty()
				&& enableTime.getAttribute("value").equalsIgnoreCase("00:00")
				&& disableTime.getAttribute("value").equalsIgnoreCase("23:59")
				&& numOfMinFaultPayments.getAttribute("value").isEmpty()
				&& numOfFullFaultPayments.getAttribute("value").isEmpty())
		{
			return true;
		}
		return false;

	}
}
