package fawry.sofAutomation.pojos.accounts;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class CustomerPojo extends MainPOJO {

	public String customerprofilecode;
	public String name;
	public String cspname;
	public String cutomerdescription;
	public String customercatagory;
	public String customerprofiletype;
	public String mobilenumber;
	
	public String getCustomerprofilecode() {
		return customerprofilecode;
	}
	public void setCustomerprofilecode(String customerprofilecode) {
		this.customerprofilecode = customerprofilecode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCspname() {
		return cspname;
	}
	public void setCspname(String cspname) {
		this.cspname = cspname;
	}
	public String getCutomerdescription() {
		return cutomerdescription;
	}
	public void setCutomerdescription(String cutomerdescription) {
		this.cutomerdescription = cutomerdescription;
	}
	public String getCustomercatagory() {
		return customercatagory;
	}
	public void setCustomercatagory(String customercatagory) {
		this.customercatagory = customercatagory;
	}
	public String getCustomerprofiletype() {
		return customerprofiletype;
	}
	public void setCustomerprofiletype(String customerprofiletype) {
		this.customerprofiletype = customerprofiletype;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	

	
	
	@Override 
	public boolean equals(Object obj) 
	{
		CustomerPojo other = (CustomerPojo)obj;
		if(other.getCustomerprofilecode().equalsIgnoreCase(this.getCustomerprofilecode()) || other.getCustomerprofilecode() == this.getCustomerprofilecode())
		{
			return true;
		}

		else 
		{
			return false;
		}
	}
	
}
