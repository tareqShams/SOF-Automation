package fawry.sofAutomation.pojos.admin;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class LoginPojo extends MainPOJO{
	
	public String userName;
	public String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
