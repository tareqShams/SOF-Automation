package fawry.sofAutomation.pages.admin;


import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.admin.RolePojo;


public class AddRolePage extends MainPage {


	WebDriver driver;

	public AddRolePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 

	@FindBy(xpath="//*[@id='AddRole:textRoleCode']")
	WebElement RoleCode;

	@FindBy(xpath="//*[@id='AddRole:textRoleName']")
	WebElement RoleName;

	@FindBy(xpath="//*[@id='AddRole:textRoleDescription']")
	WebElement Description;

	@FindBy(xpath="//*[@id='AddRole:roleType']")
	WebElement RoleType;

	@FindBy(xpath="//*[@id='AddRole:cspList']")
	WebElement CSP;

	@FindBy(xpath="//*[@id='AddRole:AllGroupsList']")
	WebElement GroupsData;

	@FindBy(xpath="//*[@id='AddRole:moveGroupsToRole']")
	WebElement GroupsDataNxtBtn;

	@FindBy(xpath="//*[@id='AddRole:AllPermissionsList']")
	WebElement PermissionsData;

	@FindBy(xpath="//*[@id='AddRole:movePermissionToRole']")
	WebElement PermissionsDataNxtBtn;

	@FindBy(xpath="//*[@id='AddRole:saveRole']")
	WebElement Save;

	@FindBy(xpath="//*[@id='AddRole:resetData']")
	WebElement Reset;

	@FindBy(className="fieldError")
	List<WebElement> errormassages;

	@FindBy(className="alert")
	WebElement correctmassage;




	public String  AddRolefun(RolePojo AddRoleObject) throws InterruptedException 
	{

		RoleCode.sendKeys(AddRoleObject.getRoleCode());
		RoleName.sendKeys(AddRoleObject.getRoleName());
		Description.sendKeys(AddRoleObject.getDescription());
		RoleType.sendKeys(AddRoleObject.getRoleType());
		if(CSP.isEnabled())
		{
			CSP.sendKeys(AddRoleObject.getCsp());
		}
		Actions builder = new Actions(driver);
		Select select = new Select( GroupsData );
		Select select2 = new Select( PermissionsData );
		if(!AddRoleObject.getGroupsData().isEmpty())
		{

			for(int j=0; j<AddRoleObject.getGroupsData().size();j++)
			{


				for(int i=0 ; i<select.getOptions().size();i++)
				{

					if(select.getOptions().get(i).getText().equalsIgnoreCase(AddRoleObject.getGroupsData().get(j)))
					{
						System.out.println(select.getOptions().get(i).getText());
						builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i)).keyUp(Keys.CONTROL);
						builder.build().perform(); 
						GroupsDataNxtBtn.click();				
						Thread.sleep(2000);

					}
					break;
				}
			}

		}
		if(!AddRoleObject.getPermissionsData().isEmpty() )
		{
			for(int j=0; j<AddRoleObject.getPermissionsData().size();j++)
			{

				for(int i=0 ; i<select2.getOptions().size();i++)
				{

					if(select2.getOptions().get(i).getText().equalsIgnoreCase(AddRoleObject.getPermissionsData().get(j)))
					{
						System.out.println(select2.getOptions().get(i).getText());

						builder.keyDown(Keys.CONTROL).click(select2.getOptions().get(i)).keyUp(Keys.CONTROL);
						builder.build().perform(); 
						PermissionsDataNxtBtn.click();				
						Thread.sleep(2000);
						break;
					}


				}
			}
			Thread.sleep(2000);
		}
		String msg = "fail in steps";
		if(AddRoleObject.getFlag().contains("add"))
		{
			Save.click();
			if (!errormassages.isEmpty()) {
				msg = errormassages.get(0).getText().toString();
				for(int i=1;i<errormassages.size();i++)
				{
					msg=msg+"/"+errormassages.get(i).getText().toString();
				}
				System.out.println(msg);

			}
			else{
				while (correctmassage.isDisplayed() )
				{

					msg=correctmassage.getText().toString();
					break;


				}
			}
			return msg;
		}
		else if(AddRoleObject.getFlag().equalsIgnoreCase("reset"))
		{
			Reset.click();
			return "reset";
		}


		return "fail";


	}


	public boolean isreset()
	{


		if(RoleName.getAttribute("value").isEmpty()
				&&RoleCode.getAttribute("value").isEmpty()
				&&Description.getAttribute("value").isEmpty())
		{
			return true;
		}

		return false;
	}
}

