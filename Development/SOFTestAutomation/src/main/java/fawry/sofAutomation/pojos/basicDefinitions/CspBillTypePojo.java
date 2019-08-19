package fawry.sofAutomation.pojos.basicDefinitions;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class CspBillTypePojo extends MainPOJO {

	private String customerServiceProvider;
	private String billTypeCode;
	private String updatebillTypeCode;

	public String getUpdatebillTypeCode() {
		return updatebillTypeCode;
	}
	public void setUpdatebillTypeCode(String updatebillTypeCode) {
		this.updatebillTypeCode = updatebillTypeCode;
	}
	private String amount;
	private String loyaltyNumOfMonths;
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCustomerServiceProvider() {
		return customerServiceProvider;
	}
	public void setCustomerServiceProvider(String customerServiceProvider) {
		this.customerServiceProvider = customerServiceProvider;
	}
	public String getBillTypeCode() {
		return billTypeCode;
	}
	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getLoyaltyNumOfMonths() {
		return loyaltyNumOfMonths;
	}
	public void setLoyaltyNumOfMonths(String loyaltyNumOfMonths) {
		this.loyaltyNumOfMonths = loyaltyNumOfMonths;
	}
}
