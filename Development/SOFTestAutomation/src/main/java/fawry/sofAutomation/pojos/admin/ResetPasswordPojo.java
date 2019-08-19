package fawry.sofAutomation.pojos.admin;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class ResetPasswordPojo extends MainPOJO{

	
	private String username;
	private String newpassword;
	private String oldpassword;
	private String enforce;
	private String flag;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getEnforce() {
		return enforce;
	}
	public void setEnforce(String enforce) {
		this.enforce = enforce;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	

}
