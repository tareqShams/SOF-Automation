package fawry.sofAutomation.pojos.admin;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class EditUserPojo extends MainPOJO{


	private  String userStatus;
	private  String	firstName;
	private  String	lastName;
	private  String	username;
	private  String	email;
	private  String	jobTitle;
	private  String	grade;
	private  String	telephoneExt;
	private  String	mobileNum;
	private  String	department;
	private  String	userExpireDate;
	private  String	userType;
	private  String	block;
	private  String	csps;
	private  String	userGroup;
	private  String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTelephoneExt() {
		return telephoneExt;
	}
	public void setTelephoneExt(String telephoneExt) {
		this.telephoneExt = telephoneExt;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUserExpireDate() {
		return userExpireDate;
	}
	public void setUserExpireDate(String userExpireDate) {
		this.userExpireDate = userExpireDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String bserType) {
		this.userType = bserType;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getCsps() {
		return csps;
	}
	public void setCsps(String csps) {
		this.csps = csps;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
}
