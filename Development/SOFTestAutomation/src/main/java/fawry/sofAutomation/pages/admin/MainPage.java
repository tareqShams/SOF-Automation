package fawry.sofAutomation.pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage {

	
	
	public void navigateToTab(String tab , String field , WebDriver driver)
	{
		WebElement element = driver.findElement(By.linkText(tab));

		Actions action = new Actions(driver);

		action.moveToElement(element).perform();

		WebElement subElement = driver.findElement(By.linkText(field));

		action.moveToElement(subElement);

		action.click();

		action.perform();

	}
	
}
