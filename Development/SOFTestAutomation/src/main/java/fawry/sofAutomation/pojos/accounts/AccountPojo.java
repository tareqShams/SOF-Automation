package fawry.sofAutomation.pojos.accounts;

import java.util.ArrayList;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class AccountPojo extends MainPOJO {

	public String accountcode;
	public String primaryaccountcode;
	public String locationid;
	public String governorate;
	public String district;
	public String region;
	public String regioncode;
	public String csp;
	public String customerprofile;
	public String usage;
	public String description;
	public String terminalstatus;
	public String terminaltype;
	public String serialnumber;
	public String pin;
	public String currency;
	public String dailylimit;
	public String creditlimit;
	public String customerprofilename;
	public String customerdescription;
	public String customercatagory;
	public String customerprofiletype;
	public String customerprofilecode;
	public String mobilenumber;
	public String accountclass;
	public String accountgroup;
	public String officialtype;
	//	public String messagefieldname;
	public String messagevalue;
	public String addAccountMethod;
	public String action;
	public String officialnumber;
	public String profileid;
	public String accountstatus;	
	public String accountalias;
	public String bankterminal;
	public String aquirebank;
	public String merchantname;
	public String accountnature;
	public String financeprogram;
	public String accountidentifier;
	public String accounttype;
	public String newcsp;
	public String psp;
	public String pspcode;
	public String expirationdate;
	public String terminalname;
	public String terminalcode;
	public String suspintionreason;
	public ArrayList<TerminalPojo> terminals;
	public ArrayList<CustomerPojo> customers;
	public ArrayList<RegionPojo> regions;
	public String balance;



	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public ArrayList<RegionPojo> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<RegionPojo> regions) {
		this.regions = regions;
	}
	public ArrayList<CustomerPojo> getCustomer() {
		return customers;
	}
	public void setCustomer(ArrayList<CustomerPojo> customers) {
		this.customers = customers;
	}
	public ArrayList<TerminalPojo> getTerminals() {
		return terminals;
	}
	public void setTerminals(ArrayList<TerminalPojo> terminals) {
		this.terminals = terminals;
	}
	public String getAccountCode() {
		return accountcode;
	}
	public void setAccountCode(String accountcode) {
		this.accountcode = accountcode;
	}
	public String getPrimaryAccountCode() {
		return primaryaccountcode;
	}
	public void setPrimaryAccountCode(String primaryaccountcode) {
		this.primaryaccountcode = primaryaccountcode;
	}
	public String getLocationId() {
		return locationid;
	}
	public void setLocationId(String locationid) {
		this.locationid = locationid;
	}
	public String getGovernorate() {
		return governorate;
	}
	public void setGovernorate(String governorate) {
		this.governorate = governorate;
	}	
	public String getDisstrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}		
	public String getRegionCode() {
		return regioncode;
	}
	public void setRegionCode(String regioncode) {
		this.regioncode = regioncode;
	}	
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}	
	public String getCustomerProfile() {
		return customerprofile;
	}
	public void setCutomerProfile(String customerprofile) {
		this.customerprofile = customerprofile;
	}	
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public String getTerminalStatus() {
		return terminalstatus;
	}
	public void setTerminalStatus(String terminalstatus) {
		this.terminalstatus = terminalstatus;
	}	
	public String getTerminalType() {
		return terminaltype;
	}
	public void setTerminalType(String terminaltype) {
		this.terminaltype = terminaltype;
	}	
	public String getSerialNumber() {
		return serialnumber;
	}
	public void setSerialNumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}	
	public String getDailyLimit() {
		return dailylimit;
	}
	public void setDailyLimit(String dailylimit) {
		this.dailylimit = dailylimit;
	}	
	public String getCreditLimit() {
		return creditlimit;
	}
	public void setCreditLimit(String creditlimit) {
		this.creditlimit = creditlimit;
	}	
	public String getCustomerProfileName() {
		return customerprofilename;
	}
	public void setCustomerProfileName(String customerprfilename) {
		this.customerprofilename = customerprfilename;
	}	
	public String getCustomerDescription() {
		return customerdescription;
	}
	public void setCustomerDesccription(String customerdescription) {
		this.customerdescription = customerdescription;
	}	
	public String getCustomerCatagory() {
		return customercatagory;
	}
	public void setCustomerCatagory(String customercatagory) {
		this.customercatagory = customercatagory;
	}	
	public String getCustomerProfileType() {
		return customerprofiletype;
	}
	public void setCustomerProfileType(String customerprofiletype) {
		this.customerprofiletype = customerprofiletype;
	}	
	public String getCustomerProfileCode() {
		return customerprofilecode;
	}
	public void setCustomerProfileCode(String customerprofilecode) {
		this.customerprofilecode = customerprofilecode;
	}	
	public String getMobileNumber() {
		return mobilenumber;
	}
	public void setMobileNumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getAccountClass() {
		return accountclass;
	}
	public void setAccountClass(String accountclass) {
		this.accountclass = accountclass;
	}	
	public String getAccountGroup() {
		return accountgroup;
	}
	public void setAccountGroup(String accountgroup) {
		this.accountgroup = accountgroup;
	}	
	public String getOfficialType() {
		return officialtype;
	}
	public void setOfficialType(String officialtype) {
		this.officialtype = officialtype;
	}	
	public String getMessageValue() {
		return messagevalue;
	}
	public void setMessageValue(String messagevalue) {
		this.messagevalue = messagevalue;
	}	
	public String getAddAccountMethod() {
		return addAccountMethod;
	}
	public void setAddAccountMethod(String addAccountMethod) {
		this.addAccountMethod = addAccountMethod;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOfficialnumber() {
		return officialnumber;
	}
	public void setOfficialnumber(String officialnumber) {
		this.officialnumber = officialnumber;
	}
	public String getProfileid() {
		return profileid;
	}
	public void setProfileid(String profileid) {
		this.profileid = profileid;
	}
	public String getAccountStatus() {
		return accountstatus;
	}
	public void setAccountStatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}
	public String getAccountAlias() {
		return accountalias;
	}
	public void setAccountAlias(String accountalias) {
		this.accountalias = accountalias;
	}
	public String getBankTerminal() {
		return bankterminal;
	}
	public void setBankTerminal(String bankterminal) {
		this.bankterminal = bankterminal;
	}
	public String getAquireBank() {
		return aquirebank;
	}
	public void setAquireBank(String aquirebank) {
		this.aquirebank = aquirebank;
	}
	public String getMerchantName() {
		return merchantname;
	}
	public void setMerchantName(String merchantname) {
		this.merchantname = merchantname;
	}
	public String getAccountNature() {
		return accountnature;
	}
	public void setAccountNature(String accountnature) {
		this.accountnature = accountnature;
	}
	public String getFinanceProgram() {
		return financeprogram;
	}
	public void setFinanceProgram(String financeprogram) {
		this.financeprogram = financeprogram;
	}
	public String getAccountIdentifier() {
		return accountidentifier;
	}
	public void setAccountIdentifier(String accountidentifier) {
		this.accountidentifier = accountidentifier;
	}
	public String getAccountType() {
		return accounttype;
	}
	public void setAccountType(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getNewCsp() {
		return newcsp;
	}
	public void setNewCsp(String newcsp) {
		this.newcsp = newcsp;
	}
	public String getPsp() {
		return psp;
	}
	public void setPsp(String psp) {
		this.psp = psp;
	}
	public String getPspCode() {
		return pspcode;
	}
	public void setPspCode(String pspcode) {
		this.pspcode = pspcode;
	}
	public String getExpirationDate() {
		return expirationdate;
	}
	public void setExpirationDate(String expirationdate) {
		this.expirationdate = expirationdate;
	}
	public String getTerminalName() {
		return terminalname;
	}
	public void setTerminalName(String terminalname) {
		this.terminalname = terminalname;
	}
	public String getTerminalCode() {
		return terminalcode;
	}
	public void setTerminalCode(String terminalcode) {
		this.terminalcode = terminalcode;
	}
	public String getSuspentionReason() {
		return suspintionreason;
	}
	public void setSuspentionReason(String suspintionreason) {
		this.suspintionreason = suspintionreason;
	}

	@Override 
	public boolean equals(Object obj) 
	{
		AccountPojo otheracc = (AccountPojo)obj;
		if(otheracc.getAccountCode().equalsIgnoreCase(this.getAccountCode())|| otheracc.getAccountCode() == this.getAccountCode())
		{
			return true;
		}

		else 
		{
			return false;
		}
	}
	// Creating Extra variables to be used when needed ( in case of resetting edited fields)
	public String sparefield0;
	public String sparefield1;
	public String sparefield2;
	public String sparefield3;
	public String sparefield4;
	public String sparefield5;
	public String sparefield6;
	public String sparefield7;
	public String sparefield8;
	public String sparefield9;
	
	public String getSparefield0() {
		return sparefield0;
	}
	public void setSparefield0(String sparefield0) {
		this.sparefield0 = sparefield0;
	}
	public String getSparefield1() {
		return sparefield1;
	}
	public void setSparefield1(String sparefield1) {
		this.sparefield1 = sparefield1;
	}
	public String getSparefield2() {
		return sparefield2;
	}
	public void setSparefield2(String sparefield2) {
		this.sparefield2 = sparefield2;
	}
	public String getSparefield3() {
		return sparefield3;
	}
	public void setSparefield3(String sparefield3) {
		this.sparefield3 = sparefield3;
	}
	public String getSparefield4() {
		return sparefield4;
	}
	public void setSparefield4(String sparefield4) {
		this.sparefield4 = sparefield4;
	}
	public String getSparefield5() {
		return sparefield5;
	}
	public void setSparefield5(String sparefield5) {
		this.sparefield5 = sparefield5;
	}
	public String getSparefield6() {
		return sparefield6;
	}
	public void setSparefield6(String sparefield6) {
		this.sparefield6 = sparefield6;
	}
	public String getSparefield7() {
		return sparefield7;
	}
	public void setSparefield7(String sparefield7) {
		this.sparefield7 = sparefield7;
	}
	public String getSparefield8() {
		return sparefield8;
	}
	public void setSparefield8(String sparefield8) {
		this.sparefield8 = sparefield8;
	}
	public String getSparefield9() {
		return sparefield9;
	}
	public void setSparefield9(String sparefield9) {
		this.sparefield9 = sparefield9;
	}



}
