package fawry.sofAutomation.pojos.basicDefinitions;


import fawry.sofAutomation.pojos.main.MainPOJO;


public class VelocityPojo extends MainPOJO{

	private String period;
	private String periodUnit;
	private String measureType;
	private String billType;
	private String csp;
	private String velocityAction;
	private String accountType;
	private String measureValue;
	private String errorCode;
	private String customerCategory;
	private String resetbefore;
	private String resetafter;
	
	public String getResetbefore() {
		return resetbefore;
	}
	public void setResetbefore(String resetbefore) {
		this.resetbefore = resetbefore;
	}
	public String getResetafter() {
		return resetafter;
	}
	public void setResetafter(String resetafter) {
		this.resetafter = resetafter;
	}	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPeriodUnit() {
		return periodUnit;
	}
	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getVelocityAction() {
		return velocityAction;
	}
	public void setVelocityAction(String velocityAction) {
		this.velocityAction = velocityAction;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getMeasureValue() {
		return measureValue;
	}
	public void setMeasureValue(String measureValue) {
		this.measureValue = measureValue;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getCustomerCategory() {
		return customerCategory;
	}
	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}




	@Override 
	public boolean equals(Object obj) 
	{
		VelocityPojo otheracc = (VelocityPojo)obj;
		
		if(otheracc.getCsp().equalsIgnoreCase(this.getCsp()) || otheracc.getCsp() == this.getCsp())
		{
			return true;
		}
		else 
		{
			return false;
		}

	}


}
