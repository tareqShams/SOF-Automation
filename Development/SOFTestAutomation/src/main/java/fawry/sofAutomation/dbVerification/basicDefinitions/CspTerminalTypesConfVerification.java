package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.CspAccountTerminalConfPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class CspTerminalTypesConfVerification {


	
	
	public CspAccountTerminalConfPojo CspAccountTerminalConfForEdit(CspAccountTerminalConfPojo CspAccountTerminalConfObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		CspAccountTerminalConfPojo  CspAccountTerminal = null;

		try {
			queryCond.append("select c.CODE as csp ,tl.NAME_PRIMARY_LANG as Terminal_Type , tt.MAX_SEQUENCIAL_NUMBER as MAX_SEQUENCIAL_NUMBER,"
					+ "tt.PIN_REGULAR_EXPRESSION as PIN_REGULAR_EXPRESSION , tt.CHANGE_PIN as Force_CHANGE_PIN "
					+ ",tt.AUTO_GENERATE_PIN_IN_CUST_REG as AUTO_GENERATE_PIN, tt.VALIDATE_PIN as VALIDATE_PIN "
					+ ",tt.PIN_ENCRYPTION_ENABLED as PIN_ENCRYPTION_ENABLED, tt.ALLOW_ANONYMOUS_ACCOUNTS as ALLOW_ANONYMOUS_ACCOUNTS"
					+ ",tt.REG_TERMINALS_IN_CUST_REG as REG_TERMINALS_IN_CUST_REG , tt.VALIDATE_DEVICE_ID as VALIDATE_DEVICE_ID ,"
					+ "tt.CHECK_PIN_HISTORY as CHECK_PIN_HISTORY  "
					+ "from POS_SOF.CSP_TERMINAL_TYPES tt   "
					+ "left join POS_SOF.CSP c on tt.CSP_ID=c.ID  "
					+ " LEFT JOIN  POS_SOF.TERMINAL_TYPES_LOOKUP tl on tt.TERM_TYPE_ID=tl.ID " 
					+ " where  c.NAME_PRIMARY_LANG = '"+CspAccountTerminalConfObj.getCspCode()+"'"
					+ " and tl.NAME_PRIMARY_LANG like '%"+CspAccountTerminalConfObj.getTerminalType()+"%'");
						

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			if(resultOfSearchaccount.next())
			{
				CspAccountTerminal=new CspAccountTerminalConfPojo();
				CspAccountTerminal.setCspCode(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
				CspAccountTerminal.setTerminalType(resultOfSearchaccount.getString("Terminal_Type")==null?"":resultOfSearchaccount.getString("Terminal_Type"));
				CspAccountTerminal.setMaxSequentialNumber(resultOfSearchaccount.getString("MAX_SEQUENCIAL_NUMBER")==null?"":resultOfSearchaccount.getString("MAX_SEQUENCIAL_NUMBER"));
				CspAccountTerminal.setPinRegularExperssion(resultOfSearchaccount.getString("PIN_REGULAR_EXPRESSION")==null?"":resultOfSearchaccount.getString("PIN_REGULAR_EXPRESSION"));
				CspAccountTerminal.setForcePinChange(resultOfSearchaccount.getString("Force_CHANGE_PIN")==null?"":resultOfSearchaccount.getString("Force_CHANGE_PIN"));
				CspAccountTerminal.setAutoGenPin(resultOfSearchaccount.getString("AUTO_GENERATE_PIN")==null?"":resultOfSearchaccount.getString("AUTO_GENERATE_PIN"));
				CspAccountTerminal.setValidatePIN(resultOfSearchaccount.getString("VALIDATE_PIN")==null?"":resultOfSearchaccount.getString("VALIDATE_PIN"));
				CspAccountTerminal.setPinEncryptionEnabled(resultOfSearchaccount.getString("PIN_ENCRYPTION_ENABLED")==null?"":resultOfSearchaccount.getString("PIN_ENCRYPTION_ENABLED"));
				CspAccountTerminal.setAllowAnonymousAccounts(resultOfSearchaccount.getString("ALLOW_ANONYMOUS_ACCOUNTS")==null?"":resultOfSearchaccount.getString("ALLOW_ANONYMOUS_ACCOUNTS"));
				CspAccountTerminal.setRegTerminalsInCustReg(resultOfSearchaccount.getString("REG_TERMINALS_IN_CUST_REG")==null?"":resultOfSearchaccount.getString("REG_TERMINALS_IN_CUST_REG"));
				CspAccountTerminal.setValidateDeviceId(resultOfSearchaccount.getString("VALIDATE_DEVICE_ID")==null?"":resultOfSearchaccount.getString("VALIDATE_DEVICE_ID"));
				CspAccountTerminal.setCheckPinHistory(resultOfSearchaccount.getString("CHECK_PIN_HISTORY")==null?"":resultOfSearchaccount.getString("CHECK_PIN_HISTORY"));
				

			}
			else
			{
				return null;
			}
		} 
		catch (SQLException e)
		{

			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return CspAccountTerminal;


}
	
	
	
	public ArrayList<CspAccountTerminalConfPojo> CspAccountTerminalConf(CspAccountTerminalConfPojo CspAccountTerminalConfObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		ArrayList<CspAccountTerminalConfPojo> results=new ArrayList<>();
		try {
			queryCond.append("select c.CODE as csp ,tl.CODE as Terminal_Type  "
							+ " from POS_SOF.CSP_TERMINAL_TYPES tt "
							+ " left join POS_SOF.CSP c on tt.CSP_ID=c.ID "
							+ " LEFT JOIN  POS_SOF.TERMINAL_TYPES_LOOKUP tl on tt.TERM_TYPE_ID=tl.ID "
							+ " where ");
			
			ArrayList<String> myQuary= new ArrayList<String>(); 
			
			if(!CspAccountTerminalConfObj.getCspCode().isEmpty())
			{
				myQuary.add(" c.NAME_PRIMARY_LANG = '"+CspAccountTerminalConfObj.getCspCode()+"'");
			}
			if(!CspAccountTerminalConfObj.getTerminalType().isEmpty())
			{
				myQuary.add(" tl.NAME_PRIMARY_LANG = '"+CspAccountTerminalConfObj.getTerminalType()+"'");
			}
			queryCond.append(add_and(myQuary));

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			CspAccountTerminalConfPojo  CspAccountTerminal;
			if(!resultOfSearchaccount.next())
			{
				return null;
			}
			CspAccountTerminal=new CspAccountTerminalConfPojo();
			CspAccountTerminal.setCspCode(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
			CspAccountTerminal.setTerminalType(resultOfSearchaccount.getString("Terminal_Type")==null?"":resultOfSearchaccount.getString("Terminal_Type"));
			results.add(CspAccountTerminal);
			while(resultOfSearchaccount.next())
			{
				CspAccountTerminal=new CspAccountTerminalConfPojo();
				CspAccountTerminal.setCspCode(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
				CspAccountTerminal.setTerminalType(resultOfSearchaccount.getString("Terminal_Type")==null?"":resultOfSearchaccount.getString("Terminal_Type"));
				results.add(CspAccountTerminal);
			}
			
		} 
		catch (SQLException e)
		{

			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return results;


}
	
	public String add_and(ArrayList<String> myquary)
	{
		String newstring="";
		for(int i=0;i<myquary.size();i++)
		{
			if(i==myquary.size()-1)
			{
				newstring+=myquary.get(i);

			}
			else
			{
				newstring+=myquary.get(i)+" AND ";

			}
		}
		return newstring;

	}
}