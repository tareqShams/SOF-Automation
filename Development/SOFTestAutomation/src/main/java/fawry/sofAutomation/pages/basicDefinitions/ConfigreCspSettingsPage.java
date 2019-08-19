package fawry.sofAutomation.pages.basicDefinitions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;



public class ConfigreCspSettingsPage {
	WebDriver driver;

	public ConfigreCspSettingsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="ConfigureCSPSettingsForm:CSPsList")
	WebElement csplist;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:0']")
	WebElement debitpharmbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:1']")
	WebElement debitcustomerbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:2']")
	WebElement debitdistibuterbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:3']")
	WebElement debitlibrarybtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:4']")
	WebElement debitkioskbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:5']")
	WebElement debitcentralbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:6']")
	WebElement debitmobilebtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:7']")
	WebElement debitotherbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:DebitNatureList:8']")
	WebElement debitsalesbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:CreditNatureList:0']")
	WebElement creditcollectionbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:CreditNatureList:1']")
	WebElement creditmarketbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:CreditNatureList:2']")
	WebElement creditcommercialbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:CreditNatureList:3']")
	WebElement creditcardbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:CreditNatureList:4']")
	WebElement creditcontrolbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:TermTypesList:0']")
	WebElement terminalmobilebtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:TermTypesList:1']")
	WebElement terminalinternetbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:TermTypesList:2']")
	WebElement terminalatmbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:TermTypesList:3']")
	WebElement terminalposbtn;

	@FindBy(xpath="//label[@for='ConfigureCSPSettingsForm:TermTypesList:4']")
	WebElement terminalsuperfawrybtn;

	@FindBy(xpath="//table[@id='ConfigureCSPSettingsForm:TermTypesList']//tbody//tr")
	List<WebElement> terminaltypestable;

	@FindBy(xpath="//table[@id='ConfigureCSPSettingsForm:DebitNatureList']//tbody//tr")
	List<WebElement> debitnaturetable;

	@FindBy(xpath="//table[@id='ConfigureCSPSettingsForm:CreditNatureList']//tbody//tr")
	List<WebElement> creditnaturetable;

	@FindBy(id="ConfigureCSPSettingsForm:saveCSPSettings")
	WebElement savebtn;

	@FindBy(id="ConfigureCSPSettingsForm:resetData")
	WebElement resetbtn;

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	@FindBy(id="ConfigureCSPSettingsForm:CorrectMessage")
	WebElement successMsg;

	String actual;
	String errorMsgs;
	String timestamp = new SimpleDateFormat("ssmm").format(Calendar.getInstance().getTime());


	public void  CommonFields(CSPFeesPojo settingsobj)
	{

		resetbtn.click();

		if (!settingsobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(settingsobj.getCsp());
		}

		debitNatureValues(settingsobj);
		creditNatureValues(settingsobj);
		terminalTypesValues(settingsobj);


	}

	public void  debitNatureValues(CSPFeesPojo settingsobj)
	{
		if (!settingsobj.getDebitNature().isEmpty())
		{
			for (int data = 0; data < debitnaturetable.size(); data++) 
			{
				driver.findElement(By.id("ConfigureCSPSettingsForm:DebitNatureList:"+data)).click();
			}
		}
		else {
			System.out.println("Not Updating Any Debit Data");
		}
	}

	public void  creditNatureValues(CSPFeesPojo settingsobj)
	{
		if(!settingsobj.getCreditNature().isEmpty())
		{
			for (int data = 0; data < creditnaturetable.size(); data++) 
			{
				driver.findElement(By.id("ConfigureCSPSettingsForm:CreditNatureList:"+data)).click();
			}
		}
		else {
			System.out.println("Not Updating Any Credit Data");
		}
	}

	public void  terminalTypesValues(CSPFeesPojo settingsobj)
	{
		if (!settingsobj.getTerminalTypes().isEmpty()) {
			for (int data = 0; data < terminaltypestable.size(); data++) 
			{
				driver.findElement(By.id("ConfigureCSPSettingsForm:TermTypesList:"+data)).click();
			}
		}
		else
		{
			System.out.println("Not Updating Any Terminal Types Data");
		}
	}



	//Saving or Resetting Data
	public void saveOrResetData(CSPFeesPojo settingsobj) {

		if (settingsobj.getAction().contains("Reset")) 
		{
			resetbtn.click();
		}
		else if (settingsobj.getAction().contains("Save"))
		{
			savebtn.click();
		}

	}

	// Collecting Success messages and error Messages
	public String ErrorMessagesAndSuccessMessage(CSPFeesPojo settingsobj) 
	{
		actual = "";
		if (driver.findElements(By.id("ConfigureCSPSettingsForm:CorrectMessage")).size() != 0  )
		{
			actual = successMsg.getText();
		}
		else if (driver.findElements(By.className("fieldError")).size() != 0  )
		{
			errorMsgs = pageErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<pageErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;
		}		
		else if ( driver.findElements(By.className("error")).size() != 0)
		{
			errorMsgs = HeaderErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<HeaderErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText().toString();
			}
			actual = errorMsgs;	
		}
		return actual;
	}

	public ArrayList<CSPFeesPojo> checkClickedDebitButtons(CSPFeesPojo settingsobj)
	{
		if (!settingsobj.getCsp().isEmpty())
		{
			new Select(csplist).selectByVisibleText(settingsobj.getCsp());
		}

		ArrayList<CSPFeesPojo> debitCreditValues = new  ArrayList<>();
		CSPFeesPojo csp;

		for (int data = 0; data < debitnaturetable.size(); data++) {
			csp = new CSPFeesPojo();

			System.out.println(debitnaturetable.size());
			if (driver.findElement(By.id("ConfigureCSPSettingsForm:DebitNatureList:"+data)).isSelected())
			{
				csp.setDebitNature(driver.findElement(By.xpath("//label[@for='ConfigureCSPSettingsForm:DebitNatureList:"+data+"']")).getText());
				System.out.println(csp.getDebitNature());
			}
			debitCreditValues.add(csp);
		}
		return debitCreditValues;	
	}

	public ArrayList<CSPFeesPojo> checkClickedCredititButtons()
	{

		ArrayList<CSPFeesPojo> debitCreditValues = new  ArrayList<>();
		CSPFeesPojo csp;

		for (int data = 0; data < creditnaturetable.size(); data++) {
			csp = new CSPFeesPojo();

			System.out.println(creditnaturetable.size());

			if (driver.findElement(By.id("ConfigureCSPSettingsForm:DebitNatureList:"+data)).isSelected())
			{
				csp.setCreditNature(driver.findElement(By.xpath("//label[@for='ConfigureCSPSettingsForm:CreditNatureList:"+data+"']")).getText());
				System.out.println(csp.getCreditNature());
			}
			debitCreditValues.add(csp);
		}
		return debitCreditValues;	
	}

	public ArrayList<CSPFeesPojo> checkClickedTerminalButtons()
	{

		ArrayList<CSPFeesPojo> debitCreditValues = new  ArrayList<>();
		CSPFeesPojo csp;

		for (int data = 0; data < terminaltypestable.size(); data++) {

			csp = new CSPFeesPojo();

			System.out.println(terminaltypestable.size());
			if (driver.findElement(By.id("ConfigureCSPSettingsForm:TermTypesList:"+data)).isSelected())
			{
				csp.setTerminalTypes(driver.findElement(By.xpath("//label[@for='ConfigureCSPSettingsForm:TermTypesList:"+data+"']")).getText());
				System.out.println(csp.getTerminalTypes());
			}
			debitCreditValues.add(csp);
		}
		return debitCreditValues;	
	}

	public void hovertopage () 
	{
		WebElement hover = driver.findElement(By.linkText("Basic Definitions"));
		Actions action = new Actions(driver);
		action.moveToElement(hover).perform();
		WebElement selecthover = driver.findElement(By.linkText("Configure CSP Settings"));
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

