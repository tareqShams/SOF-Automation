package fawry.sofAutomation.pojos.basicDefinitions;

import java.util.ArrayList;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.main.MainPOJO;


public class CSPFeesPojo extends MainPOJO{

	private String csp;
	private String accountclass;
	private String fromvalue;
	private String tovalue;
	private String fixedfees;
	private String factor;
	private String sat;
	private String sun;
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;	
	private String debitNature;
	private String creditNature;
	private String terminalTypes;
	private int numberOfClasses;
	ArrayList<CSPFeesPojo> fees;
	ArrayList<CSPFeesPojo> feesA;
	ArrayList<CSPFeesPojo> feesB;

	public ArrayList<CSPFeesPojo> getFeesA() {
		return feesA;
	}
	public void setFeesA(ArrayList<CSPFeesPojo> feesA) {
		this.feesA = feesA;
	}
	public ArrayList<CSPFeesPojo> getFeesB() {
		return feesB;
	}
	public void setFeesB(ArrayList<CSPFeesPojo> feesB) {
		this.feesB = feesB;
	}
	public ArrayList<CSPFeesPojo> getFees() {
		return fees;
	}
	public void setFees(ArrayList<CSPFeesPojo> fees) {
		this.fees = fees;
	}
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}
	public String getDebitNature() {
		return debitNature;
	}
	public void setDebitNature(String depiNature) {
		this.debitNature = depiNature;
	}
	public String getCreditNature() {
		return creditNature;
	}
	public void setCreditNature(String creditNature) {
		this.creditNature = creditNature;
	}
	public String getTerminalTypes() {
		return terminalTypes;
	}
	public void setTerminalTypes(String terminalTypes) {
		this.terminalTypes = terminalTypes;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	public String getSat() {
		return sat;
	}
	public void setSat(String sat) {
		this.sat = sat;
	}
	public String getSun() {
		return sun;
	}
	public void setSun(String sun) {
		this.sun = sun;
	}
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getTue() {
		return tue;
	}
	public void setTue(String tue) {
		this.tue = tue;
	}
	public String getWed() {
		return wed;
	}
	public void setWed(String wed) {
		this.wed = wed;
	}
	public String getThu() {
		return thu;
	}
	public void setThu(String thu) {
		this.thu = thu;
	}
	public String getFri() {
		return fri;
	}
	public void setFri(String fri) {
		this.fri = fri;
	}
	public String getCsp() {
		return csp;
	}
	public void setCsp(String csp) {
		this.csp = csp;
	}
	public String getAccountclass() {
		return accountclass;
	}
	public void setAccountclass(String accountclass) {
		this.accountclass = accountclass;
	}
	public String getFromvalue() {
		return fromvalue;
	}
	public void setFromvalue(String fromvalue) {
		this.fromvalue = fromvalue;
	}
	public String getTovalue() {
		return tovalue;
	}
	public void setTovalue(String tovalue) {
		this.tovalue = tovalue;
	}
	public String getFixedfees() {
		return fixedfees;
	}
	public void setFixedfees(String fixedfees) {
		this.fixedfees = fixedfees;
	}


	@Override 
	public boolean equals(Object obj) 
	{
		CSPFeesPojo otheracc = (CSPFeesPojo)obj;
		boolean x = false;
		if (otheracc.getDebitNature() != null && this.getDebitNature() != null )
		{
			if(otheracc.getDebitNature().equalsIgnoreCase(this.getDebitNature()) || otheracc.getDebitNature() == this.getDebitNature())
			{
				x= true;
			}
		}
		if(otheracc.getTerminalTypes() != null && otheracc.getTerminalTypes() != null)
		{
			if(otheracc.getTerminalTypes().equalsIgnoreCase(this.getTerminalTypes()) || otheracc.getTerminalTypes() == this.getTerminalTypes())
			{
				x = true;
			}
		}
		else 
		{
			x = false;
		}
		return x;
	}




}
