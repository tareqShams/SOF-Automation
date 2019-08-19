package fawry.sofAutomation.pojos.accounts;

import fawry.sofAutomation.pojos.main.MainPOJO;

public class RegionPojo extends MainPOJO {
	
	public String governorate;
	public String district;
	public String code;
	public String name;
	public String locationId;

	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getGovernorate() {
		return governorate;
	}
	public void setGovernorate(String governorate) {
		this.governorate = governorate;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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


	

	
	@Override 
	public boolean equals(Object obj) 
	{
		RegionPojo other = (RegionPojo)obj;
		if(other.getCode().equalsIgnoreCase(this.getCode()) || other.getCode() == this.getCode())
		{
			return true;
		}

		else 
		{
			return false;
		}
	}
	
}
