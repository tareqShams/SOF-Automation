package fawry.sofAutomation.pojos.basicDefinitions;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class FinanceProgramPojo extends MainPOJO{
	
	private String  programType;
	private String  code;
	private String	name;
	private String	description;
	private String  maximumFacilityLimit;
	private String	validateSetupTime;
	private String 	disableAccountRouting;
	private String  mainAccountReturn;
	private String 	enableEStatment ;
	private String  numOfDays;
	private String  dueDatePerVisits;
	private String 	blockSetupInDebit;
	private String  reactivateSetupInCredit ;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNumOfDays() {
		return numOfDays;
	}
	public void setNumOfDays(String numOfDays) {
		this.numOfDays = numOfDays;
	}
	public String getDueDatePerVisits() {
		return dueDatePerVisits;
	}
	public void setDueDatePerVisits(String dueDatePerVisits) {
		this.dueDatePerVisits = dueDatePerVisits;
	}
	public String getBlockSetupInDebit() {
		return blockSetupInDebit;
	}
	public void setBlockSetupInDebit(String blockSetupInDebit) {
		this.blockSetupInDebit = blockSetupInDebit;
	}
	public String getReactivateSetupInCredit() {
		return reactivateSetupInCredit;
	}
	public void setReactivateSetupInCredit(String reactivateSetupInCredit) {
		this.reactivateSetupInCredit = reactivateSetupInCredit;
	}
	private String  flag;

	public String getProgramType() {
		return programType;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMaximumFacilityLimit() {
		return maximumFacilityLimit;
	}
	public void setMaximumFacilityLimit(String maximumFacilityLimit) {
		this.maximumFacilityLimit = maximumFacilityLimit;
	}
	public String getValidateSetupTime() {
		return validateSetupTime;
	}
	public void setValidateSetupTime(String validateSetupTime) {
		this.validateSetupTime = validateSetupTime;
	}
	public String getDisableAccountRouting() {
		return disableAccountRouting;
	}
	public void setDisableAccountRouting(String disableAccountRouting) {
		this.disableAccountRouting = disableAccountRouting;
	}
	public String getMainAccountReturn() {
		return mainAccountReturn;
	}
	public void setMainAccountReturn(String mainAccountReturn) {
		this.mainAccountReturn = mainAccountReturn;
	}
	public String getEnableEStatment() {
		return enableEStatment;
	}
	public void setEnableEStatment(String enableEStatment) {
		this.enableEStatment = enableEStatment;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
