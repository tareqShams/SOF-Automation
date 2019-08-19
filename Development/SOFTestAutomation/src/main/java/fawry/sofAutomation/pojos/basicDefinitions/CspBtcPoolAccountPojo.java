package fawry.sofAutomation.pojos.basicDefinitions;



import fawry.sofAutomation.pojos.main.MainPOJO;

public class CspBtcPoolAccountPojo extends MainPOJO {
	private String csp;
	private String newCode;
	public String getNewCode() {
		return newCode;
	}
	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}
	private String	billTypeCode;
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getBillTypeCode() {
		return billTypeCode;
	}
	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}
	private String poolAccount;
	private String flag;
	
	public String getPoolAccount() {
		return poolAccount;
	}
	public void setPoolAccount(String poolAccount) {
		this.poolAccount = poolAccount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
