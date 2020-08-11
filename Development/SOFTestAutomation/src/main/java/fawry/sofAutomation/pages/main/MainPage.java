package fawry.sofAutomation.pages.main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import fawry.sofAutomation.pojos.accounts.AccountPojo;

public class MainPage {

	@FindBy(className="fieldError")
	List<WebElement> pageErrorMsgsList;

	@FindBy(className="error")
	List<WebElement> HeaderErrorMsgsList;

	String actual;
	String actualregion;
	String errorMsgs;
	
	public void moveToPage(String tab , String field , WebDriver driver)
	{
		WebElement element = driver.findElement(By.linkText(tab));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		WebElement subElement = driver.findElement(By.linkText(field));
		action.moveToElement(subElement);
		action.click();
		action.perform();

	}
	
	public String errorMessagesAndSuccessMessage(WebDriver driver ) 
	{
		actual = "";

		if (driver.findElements(By.className("alert")).size() != 0)
		{
			actual =  driver.findElement(By.className("alert")).getText();
		}
		else if (driver.findElements(By.className("fieldError")).size() != 0  )
		{
			errorMsgs = pageErrorMsgsList.get(0).getText().toString();
			for(int i=1;i<pageErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+pageErrorMsgsList.get(i).getText();
			}
			actual = errorMsgs;
		}
		else if ( driver.findElements(By.className("error")).size() != 0)
		{
			errorMsgs = HeaderErrorMsgsList.get(0).getText();
			for(int i=1;i<HeaderErrorMsgsList.size();i++)
			{
				errorMsgs=errorMsgs+"/"+HeaderErrorMsgsList.get(i).getText();
			}
			actual = errorMsgs;	
		}
		return actual;
	}
}
