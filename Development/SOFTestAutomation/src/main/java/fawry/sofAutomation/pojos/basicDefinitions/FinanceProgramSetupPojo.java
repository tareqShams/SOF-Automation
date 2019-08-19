package fawry.sofAutomation.pojos.basicDefinitions;

import java.util.ArrayList;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class FinanceProgramSetupPojo extends MainPOJO  {
	private String				financeProgram;
	private String				mainAccount;
	private String				subAccount;
	private String				accountClassification;
	private String				maximumAmount;
	private String				openDate;
	private String		   		closeDate;
	private String				enableTime ;
	private String				disableTime ;
	private String				numOfMinFaultPayments;
	private String				numOfFullFaultPayments;
	private ArrayList<String>	billTypes;
	private ArrayList<String>	billTypeProfiles;
	private String 				flag;
	private String  			rollOver;
	public String getRollOver() {
		return rollOver;
	}
	public void setRollOver(String rollOver) {
		this.rollOver = rollOver;
	}
	public String getFinanceProgram() {
		return financeProgram;
	}
	public void setFinanceProgram(String financeProgram) {
		this.financeProgram = financeProgram;
	}
	public String getMainAccount() {
		return mainAccount;
	}
	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}
	public String getSubAccount() {
		return subAccount;
	}
	public void setSubAccount(String subAccount) {
		this.subAccount = subAccount;
	}
	public String getAccountClassification() {
		return accountClassification;
	}
	public void setAccountClassification(String accountClassification) {
		this.accountClassification = accountClassification;
	}
	public String getMaximumAmount() {
		return maximumAmount;
	}
	public void setMaximumAmount(String maximumAmount) {
		this.maximumAmount = maximumAmount;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getEnableTime() {
		return enableTime;
	}
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}
	public String getDisableTime() {
		return disableTime;
	}
	public void setDisableTime(String disableTime) {
		this.disableTime = disableTime;
	}
	public String getNumOfMinFaultPayments() {
		return numOfMinFaultPayments;
	}
	public void setNumOfMinFaultPayments(String numOfMinFaultPayments) {
		this.numOfMinFaultPayments = numOfMinFaultPayments;
	}
	public String getNumOfFullFaultPayments() {
		return numOfFullFaultPayments;
	}
	public void setNumOfFullFaultPayments(String numOfFullFaultPayments) {
		this.numOfFullFaultPayments = numOfFullFaultPayments;
	}
	
	public ArrayList<String> getBillTypes() {
		return billTypes;
	}
	public void setBillTypes(ArrayList<String> billTypes) {
		this.billTypes = billTypes;
	}
	public ArrayList<String> getBillTypeProfiles() {
		return billTypeProfiles;
	}
	public void setBillTypeProfiles(ArrayList<String> billTypeProfiles) {
		this.billTypeProfiles = billTypeProfiles;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
