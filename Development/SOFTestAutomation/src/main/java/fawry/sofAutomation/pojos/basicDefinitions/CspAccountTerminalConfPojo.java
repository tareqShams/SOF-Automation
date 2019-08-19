package fawry.sofAutomation.pojos.basicDefinitions;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class CspAccountTerminalConfPojo extends MainPOJO{

	private String	cspCode;
	private String	terminalType;
	private String	maxSequentialNumber;
	private String	pinRegularExperssion;
	private String	forcePinChange;
	private String	autoGenPin;
	private String	validatePIN;
	private String	pinEncryptionEnabled ;
	private String	allowAnonymousAccounts;
	private String	regTerminalsInCustReg;
	private String	validateDeviceId;
	private String	checkPinHistory ;
	private String	flag ;
	

	public String getCspCode() {
		return cspCode;
	}
	public void setCspCode(String cspCode) {
		this.cspCode = cspCode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getMaxSequentialNumber() {
		return maxSequentialNumber;
	}
	public void setMaxSequentialNumber(String maxSequentialNumber) {
		this.maxSequentialNumber = maxSequentialNumber;
	}
	public String getPinRegularExperssion() {
		return pinRegularExperssion;
	}
	public void setPinRegularExperssion(String pinRegularExperssion) {
		this.pinRegularExperssion = pinRegularExperssion;
	}
	public String getForcePinChange() {
		return forcePinChange;
	}
	public void setForcePinChange(String forcePinChange) {
		this.forcePinChange = forcePinChange;
	}
	public String getAutoGenPin() {
		return autoGenPin;
	}
	public void setAutoGenPin(String autoGenPin) {
		this.autoGenPin = autoGenPin;
	}
	public String getValidatePIN() {
		return validatePIN;
	}
	public void setValidatePIN(String validatePIN) {
		this.validatePIN = validatePIN;
	}
	public String getPinEncryptionEnabled() {
		return pinEncryptionEnabled;
	}
	public void setPinEncryptionEnabled(String pinEncryptionEnabled) {
		this.pinEncryptionEnabled = pinEncryptionEnabled;
	}
	public String getAllowAnonymousAccounts() {
		return allowAnonymousAccounts;
	}
	public void setAllowAnonymousAccounts(String allowAnonymousAccounts) {
		this.allowAnonymousAccounts = allowAnonymousAccounts;
	}
	public String getRegTerminalsInCustReg() {
		return regTerminalsInCustReg;
	}
	public void setRegTerminalsInCustReg(String regTerminalsInCustReg) {
		this.regTerminalsInCustReg = regTerminalsInCustReg;
	}
	public String getValidateDeviceId() {
		return validateDeviceId;
	}
	public void setValidateDeviceId(String validateDeviceId) {
		this.validateDeviceId = validateDeviceId;
	}
	public String getCheckPinHistory() {
		return checkPinHistory;
	}
	public void setCheckPinHistory(String checkPinHistory) {
		this.checkPinHistory = checkPinHistory;
	}

	
	
	@Override
	public boolean equals(Object obj)
	{
		CspAccountTerminalConfPojo otherPojo=(CspAccountTerminalConfPojo)obj;
		if((otherPojo.getCspCode().equals(this.getCspCode()) && otherPojo.getTerminalType().equals(this.getTerminalType()))
				||(otherPojo.getCspCode()==(this.getCspCode())&& otherPojo.getTerminalType()==(this.getTerminalType())))
		{
			return true;
		}
		else
		{
			return false;
		}

	}  
	
	
}
