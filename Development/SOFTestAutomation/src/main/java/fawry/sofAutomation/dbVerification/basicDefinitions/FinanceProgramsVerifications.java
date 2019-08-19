package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.FinancePojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;

import fawry.sofAutomation.utils.DatabaseConnection;

public class FinanceProgramsVerifications {

	// SENDING SEARCH POJO
	public ArrayList<FinancePojo> searchFinancesSetup(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<FinancePojo> setup = new ArrayList<FinancePojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"fps.id as fpid\r\n" + 
					",fp.NAME_PRIMARY_LANG as fpname\r\n" + 
					",odssl.NAME_PRIMARY_LANG as status\r\n" + 
					"from POS_SOF.finance_programs_setup fps\r\n" + 
					"left JOIN POS_SOF.finance_programs fp on fps.PROGRAM_ID = fp.id\r\n" + 
					"left JOIN POS_SOF.overdraft_setup_status_lkp odssl on fps.STATUS_ID = odssl.id\r\n" + 
					"where fps.id is not null\r\n");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacctrx.getFinanceProgCode());
			if(srchacctrx.getFinanceProgCode() != "" && srchacctrx.getFinanceProgCode() != null)
			{
				myQuary.add(" fp.code = '" + srchacctrx.getFinanceProgCode() + "'\r\n");
			}
			System.out.println(srchacctrx.getAccountNumber());
			if(srchacctrx.getAccountNumber() != "" && srchacctrx.getAccountNumber() != null)
			{
				myQuary.add(" fps.PRIMARY_ACCOUNT_ID ='" + srchacctrx.getAccountNumber() + "'\r\n");
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
				FinancePojo settings = new FinancePojo();
				
				settings.setFinanceprogId(resultOfSearchaccount.getString("fpid")==null?"":resultOfSearchaccount.getString("fpid"));

				setup.add(settings);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return setup;
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


