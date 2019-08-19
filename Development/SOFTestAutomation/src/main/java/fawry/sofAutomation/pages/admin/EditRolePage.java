package fawry.sofAutomation.pages.admin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.admin.RolePojo;

public class EditRolePage  extends MainPage
{

	WebDriver driver;

	public EditRolePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	@FindBy(id="EditRole:statusList")
	WebElement status;
	@FindBy(id="EditRole:textRoleName")
	WebElement name;
	@FindBy(id="EditRole:textRoleDescription")
	WebElement description;
	@FindBy(id="EditRole:roleType")
	WebElement type;
	@FindBy(id="EditRole:cspList")
	WebElement csp;
	@FindBy(id="EditRole:AllGroupsList")
	WebElement allgroups;
	@FindBy(id="EditRole:roleGroupList")
	WebElement rolegroups;
	@FindBy(id="EditRole:moveGroupsToRole")
	WebElement moveGroupsToRole;
	@FindBy(id="EditRole:moveGroupFromRole")
	WebElement moveGroupFromRole;
	@FindBy(id="EditRole:AllPermissionsList")
	WebElement allPermissions;
	@FindBy(id="EditRole:rolePermissionsList")
	WebElement rolePermissions;
	@FindBy(id="EditRole:movePermissionToRole")
	WebElement movePermissionToRole;
	@FindBy(id="EditRole:movePermissionsFromRole")
	WebElement movePermissionsFromRole;
	@FindBy(id="EditRole:saveRole")
	WebElement save;
	@FindBy(id="EditRole:deleteUser")
	WebElement delete;
	@FindBy(id="EditRole:resetData")
	WebElement reset;
	@FindBy(id="EditRole:backToSearch")
	WebElement back;
	@FindBy(xpath="/html/body/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody")
	WebElement tableBody;
	@FindBy(id="searchRole:textRoleCode")
	WebElement searchcode;
	@FindBy(id="searchRole:SearchBts")
	WebElement search;
	@FindBy(className="alert")
	WebElement correctmsg;
	@FindBy(id="EditRole:selectAllRolesGroups")
	WebElement selectAllRolesGroups;
	@FindBy(id="EditRole:selectAllRolesPermissions")
	WebElement selectAllRolesPermissions;


	
	public void search(RolePojo roleObject) throws InterruptedException
	{

		searchcode.sendKeys(roleObject.getRoleCode());
		search.click();
		driver.findElement(By.partialLinkText(roleObject.getRoleCode())).click();
		Thread.sleep(2000);
	}

	public String  EditRolefun(RolePojo RoleObject) throws InterruptedException 
	{
		Actions builder = new Actions(driver);
		Select select ;

		if(!RoleObject.getStatus().isEmpty())
		{

			status.sendKeys(RoleObject.getStatus());
		}

		if(!RoleObject.getRoleName().isEmpty())
		{
			name.clear();
			name.sendKeys(RoleObject.getRoleName());
		}

		if(!RoleObject.getDescription().isEmpty())
		{
			description.clear();
			description.sendKeys(RoleObject.getDescription());
		}

		if(!RoleObject.getRoleType().isEmpty())
		{
			type.sendKeys(RoleObject.getRoleType());
		}

		if(!RoleObject.getCsp().isEmpty())
		{

			csp.sendKeys(RoleObject.getCsp());
		}
		System.out.println(RoleObject.getGroupsData());
		for(int j=0; j<RoleObject.getGroupsData().size();j++)
		{
		if(!RoleObject.getGroupsData().isEmpty())
		{
			select = new Select(allgroups);

			for(int i=0 ; i<select.getOptions().size();i++)
			{

				if(select.getOptions().get(i).getText().equalsIgnoreCase(RoleObject.getGroupsData().get(j)))
				{

					builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i)).keyUp(Keys.CONTROL);
					builder.build().perform(); 
					moveGroupsToRole.click();				
					Thread.sleep(2000);
					break;
				}
			}
		}
		}
		System.out.println(RoleObject.getPermissionsData());
		for(int j=0; j<RoleObject.getPermissionsData().size();j++)
		{
		if(!RoleObject.getPermissionsData().isEmpty())
		{
			select = new Select(allPermissions);

			for(int i=0 ; i<select.getOptions().size();i++)
			{

				if(select.getOptions().get(i).getText().equalsIgnoreCase(RoleObject.getPermissionsData().get(j)))
				{

					builder.keyDown(Keys.CONTROL).click(select.getOptions().get(i)).keyUp(Keys.CONTROL);
					builder.build().perform(); 
					movePermissionToRole.click();				
					Thread.sleep(2000);
					break;
				}
			}
		}
		}
		if(RoleObject.getFlag().equalsIgnoreCase("edit"))
		{
			save.click();
			//String CorrectMsg=correctmsg.getText().toString();
			return correctmsg.getText().toString();

		}
		else if (RoleObject.getFlag().equalsIgnoreCase("delete"))
		{
			delete();
			return correctmsg.getText().toString();
		}
		else if(RoleObject.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			return "reset";
		}
		else if(RoleObject.getFlag().equalsIgnoreCase("back"))
		{
			back.click();

			return "back";
		}
		return "fail";
	}

	public boolean navigateback()
	{
		if(driver.getCurrentUrl().contains("SearchRole"))
		{
			return true;
		}

		return false;

	}

	public void delete()
	{
		selectAllRolesGroups.click();
		moveGroupFromRole.click();
		selectAllRolesPermissions.click();
		movePermissionsFromRole.click();
		save.click();
		delete.click();
		Alert alert=driver.switchTo().alert();
		alert.accept();
	}
}
