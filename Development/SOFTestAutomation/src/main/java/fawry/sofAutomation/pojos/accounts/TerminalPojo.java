package fawry.sofAutomation.pojos.accounts;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class TerminalPojo extends MainPOJO {

	public String terminalcode;
	public String name;
	public String terminaltype;
	public String serialnumber;
	public String saprenormaltype;
	public String terminalstatus;
	public String accountcode;
	public String accountstatus;
	public String terminalpin;
	public String terminalconfirmpin;
	public String terminalcsp;
	public String hashedpin;
	public String action;
	public String description;
	public String profilecode;

	
	public String getProfilecode() {
		return profilecode;
	}
	public void setProfilecode(String profilecode) {
		this.profilecode = profilecode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTerminalCode() {
		return terminalcode;
	}
	public void SetTerminalCode(String terminalcode) {
		this.terminalcode = terminalcode;
	}
	public String getTerminalType() {
		return terminaltype;
	}
	public void setTerminalType(String terminaltype) {
		this.terminaltype = terminaltype;
	}
	public String getSeriallNumber() {
		return serialnumber;
	}
	public void setSerialNumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getSapreNormalType() {
		return saprenormaltype;
	}
	public void setSapreNormalType(String saprenormaltype) {
		this.saprenormaltype = saprenormaltype;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTerminalstatus() {
		return terminalstatus;
	}
	public void setTerminalstatus(String terminalstatus) {
		this.terminalstatus = terminalstatus;
	}
	public String getAccountcode() {
		return accountcode;
	}
	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}
	public String getAccountstatus() {
		return accountstatus;
	}
	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}
	public String getTerminalPin() {
		return terminalpin;
	}
	public void setTerminalPin(String terminalpin) {
		this.terminalpin = terminalpin;
	}
	public String getTerminalConfirmPin() {
		return terminalconfirmpin;
	}
	public void setTerminalConfirmPin(String terminalconfirmpin) {
		this.terminalconfirmpin = terminalconfirmpin;
	}
	public String getTerminalCsp() {
		return terminalcsp;
	}
	public void setTerminalCsp(String terminalcsp) {
		this.terminalcsp = terminalcsp;
	}
	public String getHashedPin() {
		return hashedpin;
	}
	public void setHashedPin(String hashedpin) {
		this.hashedpin = hashedpin;
	}
	
	
	
	@Override 
	public boolean equals(Object obj) 
	{
		TerminalPojo otherterm = (TerminalPojo)obj;
		if(otherterm.getTerminalCode().equalsIgnoreCase(this.getTerminalCode()) || otherterm.getTerminalCode() == this.getTerminalCode())
		{
			return true;
		}

		else 
		{
			return false;
		}
	}
	
}
