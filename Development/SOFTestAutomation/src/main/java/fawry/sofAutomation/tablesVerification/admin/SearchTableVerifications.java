package fawry.sofAutomation.tablesVerification.admin;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fawry.sofAutomation.pojos.admin.GroupPojo;
import fawry.sofAutomation.pojos.admin.RolePojo;
import fawry.sofAutomation.pojos.admin.UserPojo;

public class SearchTableVerifications {
	
	WebDriver driver;

	
	
	
	public SearchTableVerifications(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//td[@width='70%']//td//tr[@valign='top']//td//tbody[1]")
	WebElement table;
	@FindBy(xpath="//input[@title='Next']")
	WebElement nexttablebtn;
	
	public ArrayList<UserPojo>  SearchuserTable() throws InterruptedException
	{
		ArrayList<UserPojo> users=new ArrayList<>();
		UserPojo user;

		while(true)
		{
		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		for (int row = 0; row < rows_table.size(); row++) {
			 user=new UserPojo();
             List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

             user.setUserName( Columns_row.get(0).getText());
             user.setFirstName( Columns_row.get(1).getText());
             user.setLastName( Columns_row.get(2).getText());
             user.setDepartment( Columns_row.get(3).getText());
             user.setJobTitle( Columns_row.get(4).getText());
             user.setGrade( Columns_row.get(5).getText());
             user.setTelephoneExt( Columns_row.get(6).getText());
             user.setMobileNum( Columns_row.get(7).getText());
             user.setEmail( Columns_row.get(8).getText());
             user.setStutas( Columns_row.get(10).getText());
             user.setUserGroup( Columns_row.get(11).getText());
             user.setUserType( Columns_row.get(21).getText());
             users.add(user);
            
		 }
		if( !nexttablebtn.isEnabled())
			break;
			
		nexttablebtn.click();

		 
			 
		}
		 
		 System.out.println(users.size());
		
		return users;
	}
	public ArrayList<GroupPojo>  SearchgroupTable() 
	{
		
		ArrayList<GroupPojo> groups=new ArrayList<>();
		GroupPojo group;

		while(true)
		{
		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		for (int row = 0; row < rows_table.size(); row++) {
			group=new GroupPojo();
             List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

             group.setGroupCode( Columns_row.get(0).getText());
             group.setGroupName( Columns_row.get(1).getText());
             group.setDescription( Columns_row.get(2).getText());
             group.setCSP( Columns_row.get(3).getText());
             group.setGroupType( Columns_row.get(4).getText());
             group.setGroupStatus( Columns_row.get(5).getText());
             groups.add(group);
            
		 }
		if( !nexttablebtn.isEnabled())
			break;
			
		nexttablebtn.click();

		 
			 
		}
		 
		 System.out.println(groups.size());
		
		return groups;
		
	}

	public ArrayList<RolePojo>  SearchroleTable() 
	{
		
		ArrayList<RolePojo> roles=new ArrayList<>();
		RolePojo role;
		while(true)
		{
		List<WebElement> rows_table = table.findElements(By.tagName("tr"));
		for (int row = 0; row < rows_table.size(); row++) {
			role=new RolePojo();

           
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			
			role.setRoleCode(Columns_row.get(0).getText());
			role.setCsp(Columns_row.get(3).getText());
			role.setStatus(Columns_row.get(5).getText());
			role.setRoleType(Columns_row.get(4).getText());
			roles.add(role);

	
		}
		if( !nexttablebtn.isEnabled())
			break;
			
		nexttablebtn.click();

		 
			 
		}
		
		return roles;
		
	}
}
