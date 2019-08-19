package fawry.sofAutomation.pojos.admin;

import java.util.ArrayList;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class UserPojo  extends MainPOJO{
	private String firstName;
	private String LastName;
	private String userName;
	private String email;
	private String jobTitle;
	private String grade;
	private String telephoneExt;
	private String mobileNum;
	private String department;
	private String userExpirationDate;
	private String userType;
	private ArrayList<String> csp;
	private String userGroup;
	private String enforce;
	private String flag;
	private String stutas;
	private String password;
	private String confirmPassword;
	private String block;
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public ArrayList<String> getCsp() {
		return csp;
	}

	public void setCsp(ArrayList<String> csp) {
		this.csp = csp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getStutas() {
		return stutas;
	}

	public void setStutas(String stutas) {
		this.stutas = stutas;
	}

	public String GetFirstName() {
		return firstName;
	}

	public void setFirstName(String FirstName) {
		this.firstName = FirstName;
	}

	public String GetLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public String GetUserName() {
		return userName;
	}

	public void setUserName(String UserName) {
		this.userName = UserName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	public String GetJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String JobTitle) {
		this.jobTitle = JobTitle;
	}

	public String GetGrade() {
		return grade;
	}

	public void setGrade(String Grade) {
		this.grade = Grade;
	}

	public String GetTelephoneExt() {
		return telephoneExt;
	}

	public void setTelephoneExt(String TelephoneExt) {
		this.telephoneExt = TelephoneExt;
	}

	public String GetMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String MobileNum) {
		this.mobileNum = MobileNum;
	}

	public String GetDepartment() {
		return department;
	}

	public void setDepartment(String Department) {
		this.department = Department;
	}

	public String GetUserExpirationDate() {
		return userExpirationDate;
	}

	public void setUserExpirationDate(String UserExpirationDate) {
		this.userExpirationDate = UserExpirationDate;
	}



	public String GetUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String UserGroup) {
		this.userGroup = UserGroup;
	}
	public String Getenforce() {
		return enforce;
	}

	public void SetEnforce(String enforce) {
		this.enforce = enforce;
	}
	public String Getflag() {
		return flag;
	}

	public void Setflag(String flag) {
		this.flag = flag;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	@Override
	public boolean equals(Object obj)
	{
		UserPojo otherPojo=(UserPojo)obj;
		if(otherPojo.GetUserName().equals(this.GetUserName())||otherPojo.GetUserName()==(this.GetUserName()))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
}

