package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.CSPFeesPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;

import fawry.sofAutomation.utils.DatabaseConnection;

public class SearchVefication {

	// SENDING SEARCH POJO
	public ArrayList<CSPFeesPojo> searchFees(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<CSPFeesPojo> fees = new ArrayList<CSPFeesPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"c.name_primary_lang cspname\r\n" + 
					",ac.NAME_PRIMARY_LANG accountclass\r\n" + 
					",f.OVERDRAFT_FACTOR as facvalue\r\n" + 
					",f.SATURDAY_FACTOR as satvalue\r\n" + 
					",f.SUNDAY_FACTOR as sunvalue\r\n" + 
					",f.MONDAY_FACTOR as monvalue \r\n" + 
					",f.TUESDAY_FACTOR as tuevalue \r\n" + 
					",f.WEDNESDAY_FACTOR as wedvalue \r\n" + 
					",f.THURSDAY_FACTOR as thuvalue \r\n" + 
					",f.FRIDAY_FACTOR as frivalue \r\n" + 
					"from POS_SOF.CSP_CLASS_OVERDRAFTFACTORSETUP f\r\n" + 
					"left JOIN POS_SOF.csp c on f.CSP_ID = c.id\r\n" + 
					"left JOIN POS_SOF.ACCOUNT_CLASS_LOOKUP ac on f.CLASS_ID = ac.id\r\n" + 
					"where f.id is not null");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacctrx.getCsp());
			if(srchacctrx.getCsp() != "" && srchacctrx.getCsp() != null)
			{
				myQuary.add(" c.NAME_PRIMARY_LANG = '" + srchacctrx.getCsp() + "'");
			}
			
			System.out.println(srchacctrx.getAccountClass());
			if(srchacctrx.getAccountClass() != "" && srchacctrx.getAccountClass() != null)
			{
				myQuary.add(" ac.name_primary_lang = '" + srchacctrx.getAccountClass() + "'");
			}
			
			/*System.out.println(flag);
			if(flag.equalsIgnoreCase("Add"))
			{
			myQuary.add(" rownum = 1 \r\n" + 
					"Order by a.ACTION_DATE DESC ");
			}*/
			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				CSPFeesPojo addfees = new CSPFeesPojo();
				
				addfees.setFactorValue(resultOfSearchaccount.getString("FACVALUE")==null?"":resultOfSearchaccount.getString("FACVALUE"));
				addfees.setSaturdayValue(resultOfSearchaccount.getString("SATVALUE")==null?"":resultOfSearchaccount.getString("SATVALUE"));
				addfees.setSundayValue(resultOfSearchaccount.getString("SUNVALUE")==null?"":resultOfSearchaccount.getString("SUNVALUE"));
				addfees.setMondayValue(resultOfSearchaccount.getString("MONVALUE")==null?"":resultOfSearchaccount.getString("MONVALUE"));
				addfees.setTuesdayValue(resultOfSearchaccount.getString("TUEVALUE")==null?"":resultOfSearchaccount.getString("TUEVALUE"));
				addfees.setWedensdayValue(resultOfSearchaccount.getString("WEDVALUE")==null?"":resultOfSearchaccount.getString("WEDVALUE"));
				addfees.setThursdayValue(resultOfSearchaccount.getString("THUVALUE")==null?"":resultOfSearchaccount.getString("THUVALUE"));
				addfees.setFridayValue(resultOfSearchaccount.getString("FRIVALUE")==null?"":resultOfSearchaccount.getString("FRIVALUE"));

				fees.add(addfees);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fees;
	}
	
	public ArrayList<CSPFeesPojo> searchCspDebitCreditSettings(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<CSPFeesPojo> fees = new ArrayList<CSPFeesPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"cs.NAME_PRIMARY_LANG as cspname\r\n" + 
					",ul.NAME_PRIMARY_LANG as debitcreditnature\r\n" + 
					"from POS_SOF.csp_usage cu\r\n" + 
					"left JOIN POS_SOF.csp cs on cu.CSP_ID = cs.id\r\n" + 
					"left JOIN POS_SOF.usage_lookup ul on cu.USAGE_ID = ul.id\r\n" + 
					"where cs.id is not null\r\n");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacctrx.getCsp());
			if(srchacctrx.getCsp() != "" && srchacctrx.getCsp() != null)
			{
				myQuary.add(" cs.NAME_PRIMARY_LANG = '" + srchacctrx.getCsp() + "'\r\n");
			}
			System.out.println(srchacctrx.getAccountType());
			if(srchacctrx.getAccountType() != "" && srchacctrx.getAccountType() != null)
			{
				myQuary.add(" ul.ACCOUNT_TYPE_ID ='" + srchacctrx.getAccountType() + "'");
			}
			
			/*System.out.println(flag);
			if(flag.equalsIgnoreCase("Add"))
			{
			myQuary.add(" rownum = 1 \r\n" + 
					"Order by a.ACTION_DATE DESC ");
			}*/
			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				CSPFeesPojo addfees = new CSPFeesPojo();
				
				addfees.setDebitNature(resultOfSearchaccount.getString("debitcreditnature")==null?"":resultOfSearchaccount.getString("debitcreditnature"));
				
				fees.add(addfees);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fees;
	}
	public ArrayList<CSPFeesPojo> searchCspTerminalTypesSettings(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<CSPFeesPojo> fees = new ArrayList<CSPFeesPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"cs.NAME_PRIMARY_LANG as cspname\r\n" + 
					",tl.NAME_PRIMARY_LANG as termtype\r\n" + 
					"from POS_SOF.CSP_TERMINAL_TYPES ct\r\n" + 
					"left JOIN POS_SOF.csp cs on ct.CSP_ID = cs.id\r\n" + 
					"left JOIN POS_SOF.TERMINAL_TYPES_LOOKUP tl on ct.TERM_TYPE_ID = tl.id\r\n" + 
					"where ct.id is not null");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacctrx.getCsp());
			if(srchacctrx.getCsp() != "" && srchacctrx.getCsp() != null)
			{
				myQuary.add(" cs.NAME_PRIMARY_LANG = '" + srchacctrx.getCsp() + "'");
			}	
			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				CSPFeesPojo addfees = new CSPFeesPojo();
				
				addfees.setTerminalTypes(resultOfSearchaccount.getString("TERMTYPE")==null?"":resultOfSearchaccount.getString("TERMTYPE"));
				
				fees.add(addfees);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fees;
	}
/*
	//Update account Status to be later used in account/terminal status page
	public void updateTerminalStatus(SearchPojo srchacc, String flag) throws SQLException
	{

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();
			System.out.println(srchacc.getTerminalstatusid()+"/"+srchacc.getTerminalCode());
			queryCond.append("UPDATE POS_SOF.terminals t\r\n" + 
					"SET t.TERMINAL_STATUS_ID = "+srchacc.getTerminalstatusid()+" \r\n" + 
					"WHERE t.code = '"+srchacc.getTerminalCode()+"'");

			System.out.println(queryCond.toString());
			conn.executeVerificationQuery(myconection, queryCond.toString());
			conn.closeDBConnection(myconection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	public String add_and(ArrayList<String> myquary)
	{
		String newstring="";
		for(int i=0;i<myquary.size();i++)
		{
			newstring+="	AND "+myquary.get(i);
		}
		return newstring;
	}

}


