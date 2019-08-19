package fawry.sofAutomation.tablesVerification.basicDefinitions;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;

public class SearchVelocityTableVerifications {
	WebDriver driver;




	public SearchVelocityTableVerifications(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@id=\"searchVelocity:profilesResultsTable\"]/tbody")
	WebElement table;
	@FindBy(xpath="//input[@title='Next']")
	//input[@title='Next']
	WebElement nexttablebtn;

	public ArrayList<VelocityPojo>  searchVelocity()
	{
		ArrayList<VelocityPojo> velocityInWebTable=new ArrayList<>();
		VelocityPojo velocity;

		while(true)
		{
			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			for (int row = 0; row < rows_table.size(); row++) {
				velocity=new VelocityPojo();
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				velocity.setCsp( Columns_row.get(0).getText());

				System.out.println(velocity.getCsp());
				velocityInWebTable.add(velocity);

			}
			if( !nexttablebtn.isEnabled())
				break;

			nexttablebtn.click();
		}

		System.out.println(velocityInWebTable.size());

		return velocityInWebTable;
	}


}
