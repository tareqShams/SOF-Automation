package fawry.sofAutomation.pojos.admin;

import java.util.ArrayList;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class GroupPojo  extends MainPOJO{

	private ArrayList<String>  UsersData;
	private  String groupStatus;
	private String	groupCode;
	private String	GroupName;
	private String	Description;
	private String	GroupType;
	private String  CSP;
	private String GroupRole;
	private String flag;

	
	public ArrayList<String> getUsersData() {
		return UsersData;
	}

	public void setUsersData(ArrayList<String> usersData) {
		UsersData = usersData;
	}

	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String GetGroupStatus() {
		return groupStatus;
	}
	
	public void setGroupStatus(String GroupStatus) {
		this.groupStatus = GroupStatus;
	} 
	
	public String GetGroupCode() {
		return groupCode;
	}
	
	public void setGroupCode(String GroupCode) {
		this.groupCode = GroupCode;
	} 
	
	public String GetGroupName() {
		return GroupName;
	}
	
	public void setGroupName(String GroupName) {
		this.GroupName = GroupName;
	} 
	
	public String GetDescription() {
		return Description;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	} 
	
	public String GetGroupType() {
		return GroupType;
	}
	
	public void setGroupType(String GroupType) {
		this.GroupType = GroupType;
	} 
	
	public String GetCSP() {
		return CSP;
	}
	
	public void setCSP(String CSP) {
		this.CSP = CSP;
	} 
	
	public String GetGroupRole() {
		return GroupRole;
	}
	
	public void setGroupRole(String GroupRole) {
		this.GroupRole = GroupRole;
	} 
	
	@Override
	public boolean equals(Object obj)
	{
		GroupPojo otherPojo=(GroupPojo)obj;
		if(this.GetGroupCode().equals(otherPojo.GetGroupCode()))
		{
			return true;
		}
		
		else
		{
			return false;
		}

	}

}
