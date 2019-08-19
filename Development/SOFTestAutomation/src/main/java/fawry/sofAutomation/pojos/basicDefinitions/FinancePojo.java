package fawry.sofAutomation.pojos.basicDefinitions;


import fawry.sofAutomation.pojos.main.MainPOJO;


public class FinancePojo extends MainPOJO{

	private String financeprogid;
	private String financeprogname;
	private String financeProgCode;
	private String btc;
	private String accountNumber;
	private String subAccount;
	private String status;


	public String getSubAccount() {
		return subAccount;
	}
	public void setSubAccount(String subAccount) {
		this.subAccount = subAccount;
	}	
	public String getFinanceprogId() {
		return financeprogid;
	}
	public void setFinanceprogId(String financeprogid) {
		this.financeprogid = financeprogid;
	}
	public String getFinanceprogname() {
		return financeprogname;
	}
	public void setFinanceprogname(String financeprogname) {
		this.financeprogname = financeprogname;
	}
	public String getFinanceProgCode() {
		return financeProgCode;
	}
	public void setFinanceProgCode(String financeProgCode) {
		this.financeProgCode = financeProgCode;
	}
	public String getBtc() {
		return btc;
	}
	public void setBtc(String btc) {
		this.btc = btc;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	@Override 
	public boolean equals(Object obj) 
	{
		FinancePojo otheracc = (FinancePojo)obj;
		
		if(otheracc.getFinanceprogId().equalsIgnoreCase(this.getFinanceprogId()) || otheracc.getFinanceprogId() == this.getFinanceprogId())
		{
			return true;
		}
		else 
		{
			return false;
		}

	}


}
