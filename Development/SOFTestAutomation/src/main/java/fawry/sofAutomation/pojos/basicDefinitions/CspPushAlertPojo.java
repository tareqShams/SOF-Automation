package fawry.sofAutomation.pojos.basicDefinitions;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class CspPushAlertPojo extends MainPOJO{
	private String csp;
	private String 	accountType;
	private String  allowPushAlert;
	private String flag;
	
	
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAllowPushAlert() {
		return allowPushAlert;
	}
	public void setAllowPushAlert(String allowPushAlert) {
		this.allowPushAlert = allowPushAlert;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	
	@Override
	public boolean equals(Object obj)
	{
		CspPushAlertPojo otherPojo=(CspPushAlertPojo)obj;
		if((otherPojo.getCsp().equals(this.getCsp()) && otherPojo.getAccountType().equals(this.getAccountType()))
				||(otherPojo.getCsp()==(this.getCsp())&& otherPojo.getAccountType()==(this.getAccountType())))
		{
			return true;
		}
		else
		{
			return false;
		}

	}  
	
	
}
