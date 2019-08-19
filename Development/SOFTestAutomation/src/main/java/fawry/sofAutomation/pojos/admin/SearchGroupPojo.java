package fawry.sofAutomation.pojos.admin;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class SearchGroupPojo extends MainPOJO{

	private String  GroupCode;
	private String 	CSP;
	private String  Status;
	private String  GroupType;
	private String  Flag;


	public String GetGroupCode() {
		return GroupCode;
	}

	public void setGroupCode(String GroupCode) {
		this.GroupCode = GroupCode;
	} 


	public String GetCSP() {
		return CSP;
	}

	public void setCSP(String CSP) {
		this.CSP = CSP;
	} 


	public String GetStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	} 


	public String GetGroupType() {
		return GroupType;
	}

	public void setGroupType(String GroupType) {
		this.GroupType = GroupType;
	} 

	public String GetFlag() {
		return Flag;
	}

	public void setFlag(String Flag) {
		this.Flag = Flag;
	} 


}
