package fawry.sofAutomation.pages.admin;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.admin.GroupPojo;


public class AddGroupPage  extends MainPage{
	WebDriver driver;

	public AddGroupPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="AddGroup:textGroupCode")
	WebElement GroupCode;

	@FindBy(id="AddGroup:textGroupName")
	WebElement GroupName;

	@FindBy(id="AddGroup:textGroupDescription")
	WebElement Description;

	@FindBy(id="AddGroup:groupType")
	WebElement GroupType;

	@FindBy(id="AddGroup:cspList")
	WebElement CSP;

	@FindBy(id="AddGroup:GroupOneRole")
	WebElement GroupRole;

	@FindBy(id="AddGroup:AllUsersList")
	WebElement UsersData;

	@FindBy(id="AddGroup:moveUsersToGroup")
	WebElement nextcspbtn;

	@FindBy(id="AddGroup:saveGroup")
	WebElement Save;

	@FindBy(id="AddGroup:resetData")
	WebElement Reset;
	@FindBy(className="fieldError")
	List<WebElement>  errors ;
	@FindBy(className="alert")
	WebElement correctmsg;
	String timestamp = new SimpleDateFormat("mmm").format(Calendar.getInstance().getTime());

	
	public String  AddGroup(GroupPojo AddGroupoObject) throws InterruptedException
	{
		GroupCode.sendKeys(AddGroupoObject.GetGroupCode());
		GroupName.sendKeys(AddGroupoObject.GetGroupName());
		Description.sendKeys(AddGroupoObject.GetDescription());
		GroupType.sendKeys(AddGroupoObject.GetGroupType());
		if(CSP.isEnabled())
		{
			CSP.sendKeys(AddGroupoObject.GetCSP());

		}
		GroupRole.sendKeys(AddGroupoObject.GetGroupRole());

		Select select = new Select( UsersData );
		Actions builder = new Actions(driver);
		//need to fix
		for(int j=0; j<AddGroupoObject.getUsersData().size();j++)
		{
		for(int i=0 ; i<select.getOptions().size();i++)
		{

			if(select.getOptions().get(i).getText().equalsIgnoreCase(AddGroupoObject.getUsersData().get(j)))
			{
				builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i)).keyUp(Keys.CONTROL);
				builder.build().perform(); 
				nextcspbtn.click();
				Thread.sleep(2000);
				break;
			}

		}
		}
		if(AddGroupoObject.getFlag().contains("add"))
		{
			Save.click();
			String Msgs;
			if(!errors.isEmpty())
			{
				Msgs = errors.get(0).getText().toString();
				for(int i=1;i<errors.size();i++)
				{
					Msgs=Msgs+"/"+errors.get(i).getText().toString();
				}

				return Msgs;
			}
			else if (correctmsg.isDisplayed())
			{
				return correctmsg.getText();
			}
		}
		else if(AddGroupoObject.getFlag().contains("reset"))
		{
			Reset.click();
			return "reset";
		}

		
		return "Faild";
	}



	public boolean isreset()
	{
		if(GroupName.getAttribute("value").isEmpty()
				&&GroupCode.getAttribute("value").isEmpty()
				&&Description.getAttribute("value").isEmpty())
		{
			return true;
		}

		return false;
	}
}



