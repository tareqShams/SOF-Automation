package fawry.sofAutomation.pages.admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fawry.sofAutomation.pojos.admin.GroupPojo;

public class EditGroupPage extends MainPage {
	
	WebDriver driver;
	public EditGroupPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 

	@FindBy(id="searchGroup:textGroupCode")
	WebElement groupcode;


	@FindBy(id="searchGroup:button1")
	WebElement search;

	@FindBy(id="EditGroup:statusList")
	WebElement groupstatus;

	@FindBy(id="EditGroup:textGroupName")
	WebElement groupname;

	@FindBy(id="EditGroup:textGroupDescription")
	WebElement description;

	@FindBy(id="EditGroup:groupType")
	WebElement grouptype;

	@FindBy(id="EditGroup:cspList")
	WebElement csp;

	@FindBy(id="EditGroup:GroupOneRole")
	WebElement grouprole;

	@FindBy(id="EditGroup:AllUsersList")
	WebElement allusers;

	@FindBy(id="EditGroup:selectAllGroupUsers")
	WebElement selectAllGroupUsers;

	@FindBy(id="EditGroup:moveUsersToGroup")
	WebElement moveUsersToGroup;

	@FindBy(id="EditGroup:moveUsersFromGroup")
	WebElement moveUsersFromGroup;

	@FindBy(id="EditGroup:saveGroup")
	WebElement save;

	@FindBy(id="EditGroup:deleteUser")
	WebElement delete;

	@FindBy(id="EditGroup:resetData")
	WebElement reset;

	@FindBy(id="EditGroup:backToSearch")
	WebElement back;

	@FindBy(className="alert")
	WebElement correctmsg;
	
	@FindBy(id="EditGroup:GroupUsersList")
	WebElement GroupUsersList;
	

	public void search(GroupPojo GroupObject) throws InterruptedException
	{

		groupcode.sendKeys(GroupObject.GetGroupCode());
		search.click();
		driver.findElement(By.linkText(GroupObject.GetGroupCode())).click();
		Thread.sleep(2000);
	}

	public String  editgroupfun(GroupPojo groupObject) throws InterruptedException  
	{


		if(!groupObject.GetGroupStatus().isEmpty())
		{

			groupstatus.sendKeys(groupObject.GetGroupStatus());
		}
		if(!groupObject.GetGroupName().isEmpty())
		{
			groupname.clear();
			groupname.sendKeys(groupObject.GetGroupName());
		}
		if(!groupObject.GetDescription().isEmpty())
		{
			description.clear();
			description.sendKeys(groupObject.GetDescription());
		}
		if(!groupObject.GetGroupType().isEmpty())
		{

			grouptype.sendKeys(groupObject.GetGroupType());
		}
		if(!groupObject.GetCSP().isEmpty())
		{

			csp.sendKeys(groupObject.GetCSP());
		}
		if(!groupObject.GetGroupRole().isEmpty())
		{

			grouprole.sendKeys(groupObject.GetGroupRole());
		}
		/*if(!groupObject.GetUsersData().isEmpty())
		{
			groupusers.clear();
			groupusers.sendKeys(groupObject.GetUsersData());
		}
		 */
		getGroupUsersList(groupObject);
		if(groupObject.getFlag().equalsIgnoreCase("edit"))
		{
			save.click();
			return correctmsg.getText().toString();

		}
		else if (groupObject.getFlag().equalsIgnoreCase("delete"))
		{
			delete(groupObject);
			Alert alert=driver.switchTo().alert();
			alert.accept();
			return correctmsg.getText().toString();
		}
		else if(groupObject.getFlag().equalsIgnoreCase("reset"))
		{
			reset.click();
			save.click();
			return "reset";
		}
		else if(groupObject.getFlag().equalsIgnoreCase("back"))
		{
			back.click();

			return "back";
		}
		return "fail";
	}
	public void delete(GroupPojo groupObject) throws InterruptedException
	{
		/*Select selectz=new Select(grouprole);
		System.out.println(selectz.getFirstSelectedOption().getText());
		if(!selectz.getFirstSelectedOption().getText().contains("- Please select -"))
		{
		EditRolePage role=new EditRolePage(driver);
		RolePojo searchpojo=new RolePojo();
		Select select = new Select(grouprole);
		String option = select.getFirstSelectedOption().getText();
		String part = option.split("\\s+")[0];
		searchpojo.setRoleCode(part);*/
		grouprole.sendKeys("- Please select -");
		save.click();
		/*role.navigateToSearchRoleTab();
		role.search(searchpojo);
		role.delete();
		navigateToSearchGroupTab();
		search(groupObject);
		}*/
		while(selectAllGroupUsers.isEnabled())
		{
		selectAllGroupUsers.click();
		moveUsersFromGroup.click();
		break;
		
		}
		delete.click();

	}


	public void getGroupUsersList(GroupPojo GroupObject)
	{
		Select select=new Select(GroupUsersList);
		if(select.getOptions().size()!=0)
		{
			List<WebElement> groupUsers=select.getOptions();
		ArrayList<String> arr=new ArrayList<>();
		for(int i=0 ; i<groupUsers.size();i++)
		{
			arr.add(groupUsers.get(i).getText().toString());
		}
		GroupObject.setUsersData(arr);}
		
	}
}
