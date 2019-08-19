package fawry.sofAutomation.pojos.financialActivities;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class SearchPojo extends MainPOJO {

	private String csp;
	private String accountcode;
	private String transactiontype;
	private String amount;
	private String currency;
	private String date;
	private String description;
	private String fromdate;
	private String todate;
	private String softrxrefnum;
	private String fcrn;
	
	
	
	public String getFcrn() {
		return fcrn;
	}
	public void setFcrn(String fcrn) {
		this.fcrn = fcrn;
	}
	public String getSoftrxrefnum() {
		return softrxrefnum;
	}
	public void setSoftrxrefnum(String softrxrefnum) {
		this.softrxrefnum = softrxrefnum;
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
}
