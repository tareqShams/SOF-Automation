package fawry.sofAutomation.pojos.admin;

import java.util.ArrayList;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class RolePojo extends MainPOJO {


	
	private String flag;
	private String roleCode;
	private String roleName;
	private String description;
	private String roleType;
	private String csp;
	private ArrayList<String>  groupsData;
	private ArrayList<String>  permissionsData;
	public ArrayList<String> getGroupsData() {
		return groupsData;
	}
	public ArrayList<String> getPermissionsData() {
		return permissionsData;
	}
	public void setGroupsData(ArrayList<String> groupsData) {
		this.groupsData = groupsData;
	}
	public void setPermissionsData(ArrayList<String> permissionsData) {
		this.permissionsData = permissionsData;
	}


	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	
	

	@Override
	public boolean equals(Object obj)
	{
		RolePojo otherPojo=(RolePojo)obj;
		if((this.getRoleCode()).equals(otherPojo.getRoleCode())||this.getRoleCode()==(otherPojo.getRoleCode()))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	

	
	
}
