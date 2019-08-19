package fawry.sofAutomation.pojos.financialActivities;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class AccountTrxPojo extends MainPOJO {

	private String csp;
	private String accountcode;
	private String transactiontype;
	private String amount;
	private String currency;
	private String date;
	private String description;
	private String balance;
	private String valuebefore;
	private String valueafter;
	private String classification;
	private String billtypecode;
	private String accounttype;
	private String fromdate;
	private String todate;
	private String merchant;
	private String softrxrefnum;
	private String fawrycustomerrefnum;
	private String terminalstatus;
	private String revcustomerrefnum;
	private String usage;
	private String bankterminalnum;
	private String lastfourdigits;
	private String issuerid;	
	private String authenticationid;
	private String actioncommand;
	private String username;
	private String reconciliation;
	private String billingaccount;
	private String customerip;
	private String alias;
	private String toaccount;
	private String secondaccount;
	private String thirdaccount;
	private String secondamount;
	private String thirdamount;
	private String accountNature;
	private String actionNature;
	private String creationDate;
	private String trxStatus;
	private String actor;



	
	
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getTrxStatus() {
		return trxStatus;
	}
	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getActionNature() {
		return actionNature;
	}
	public void setActionNature(String actionNature) {
		this.actionNature = actionNature;
	}
	public String getAccountNature() {
		return accountNature;
	}
	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}
	public String getSecondamount() {
		return secondamount;
	}
	public void setSecondamount(String secondamount) {
		this.secondamount = secondamount;
	}
	public String getThirdamount() {
		return thirdamount;
	}
	public void setThirdamount(String thirdamount) {
		this.thirdamount = thirdamount;
	}
	public String getSecondaccount() {
		return secondaccount;
	}
	public void setSecondaccount(String secondaccount) {
		this.secondaccount = secondaccount;
	}
	public String getThirdaccount() {
		return thirdaccount;
	}
	public void setThirdaccount(String thirdaccount) {
		this.thirdaccount = thirdaccount;
	}

	public String getToaccountCode() {
		return toaccount;
	}
	public void setToaccountCode(String toaccount) {
		this.toaccount = toaccount;
	}
	public String getAuthenticationid() {
		return authenticationid;
	}
	public void setAuthenticationid(String authenticationid) {
		this.authenticationid = authenticationid;
	}
	public String getActioncommand() {
		return actioncommand;
	}
	public void setActioncommand(String actioncommand) {
		this.actioncommand = actioncommand;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getReconciliation() {
		return reconciliation;
	}
	public void setReconciliation(String reconciliation) {
		this.reconciliation = reconciliation;
	}
	public String getBillingaccount() {
		return billingaccount;
	}
	public void setBillingaccount(String billingaccount) {
		this.billingaccount = billingaccount;
	}
	public String getCustomerip() {
		return customerip;
	}
	public void setCustomerip(String customerip) {
		this.customerip = customerip;
	}
	public String getBankterminalnum() {
		return bankterminalnum;
	}
	public void setBankterminalnum(String bankterminalnum) {
		this.bankterminalnum = bankterminalnum;
	}
	public String getLastfourdigits() {
		return lastfourdigits;
	}
	public void setLastfourdigits(String lastfourdigits) {
		this.lastfourdigits = lastfourdigits;
	}
	public String getIssuerid() {
		return issuerid;
	}
	public void setIssuerid(String issuerid) {
		this.issuerid = issuerid;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getBilltypecode() {
		return billtypecode;
	}
	public void setBilltypecode(String billtypecode) {
		this.billtypecode = billtypecode;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getSoftrxrefnum() {
		return softrxrefnum;
	}
	public void setSoftrxrefnum(String softrxrefnum) {
		this.softrxrefnum = softrxrefnum;
	}
	public String getFawyCustomerrefnum() {
		return fawrycustomerrefnum;
	}
	public void setFawryCustomerrefnum(String customerrefnum) {
		this.fawrycustomerrefnum = customerrefnum;
	}
	public String getTerminalstatus() {
		return terminalstatus;
	}
	public void setTerminalstatus(String terminalstatus) {
		this.terminalstatus = terminalstatus;
	}
	public String getRevcustomerrefnum() {
		return revcustomerrefnum;
	}
	public void setRevcustomerrefnum(String revcustomerrefnum) {
		this.revcustomerrefnum = revcustomerrefnum;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getValuebefore() {
		return valuebefore;
	}
	public void setValuebefore(String valuebefore) {
		this.valuebefore = valuebefore;
	}
	public String getValueafter() {
		return valueafter;
	}
	public void setValueafter(String valueafter) {
		this.valueafter = valueafter;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getAccountcode() {
		return accountcode;
	}
	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override 
	public boolean equals(Object obj) 
	{
		AccountTrxPojo otheracc = (AccountTrxPojo)obj;
		if(this.getAccountcode().equalsIgnoreCase(otheracc.getAccountcode()) || this.getAccountcode() == otheracc.getAccountcode())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
