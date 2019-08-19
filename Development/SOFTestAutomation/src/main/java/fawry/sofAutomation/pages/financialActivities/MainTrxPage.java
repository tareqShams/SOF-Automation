package fawry.sofAutomation.pages.financialActivities;

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

import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;

public class MainTrxPage {
	WebDriver driver;
	public MainTrxPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String timestamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());

	int balance;
	//All Common fields method
	public int  trxCalculations(String accountNature, String trxNature,float oldValue, int amount)
	{
		System.out.println(timestamp);
		int currentValue = Math.round(oldValue);
		
		if (trxNature.equalsIgnoreCase("Debit"))
		{
			if(accountNature.equalsIgnoreCase("Debit"))
			{
				balance = currentValue - amount;
			}
			else if (accountNature.equalsIgnoreCase("Credit"))
			{
				balance = currentValue + amount;
			}
		}
		if (trxNature.equalsIgnoreCase("Credit"))
		{
			if(accountNature.equalsIgnoreCase("Debit"))
			{
				balance = currentValue + amount;
			}
			else if (accountNature.equalsIgnoreCase("Credit"))
			{
				balance = currentValue - amount;
			}
		}
		return balance;
	}

}

