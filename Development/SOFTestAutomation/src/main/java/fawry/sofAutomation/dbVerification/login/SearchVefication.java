package fawry.sofAutomation.dbVerification.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class SearchVefication {

	// SENDING SEARCH POJO
	public ArrayList<AccountTrxPojo> searchAccountTrx(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountTrxPojo> accountTrx = new ArrayList<AccountTrxPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n"
					+ "a.TERMINAL_ID as accountcode\r\n" + 
					",a.AMOUNT as amount\r\n" + 
					",at.NAME_PRIMARY_LANG as trxtype\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ats.NAME_PRIMARY_LANG as status\r\n" + 
					",a.DESCRIPTION as description\r\n" + 
					",a.OLD_VALUE as oldvalue\r\n" + 
					",a.NEW_VALUE as newvalue\r\n" + 
					"from POS_SOF.account_actions a\r\n" + 
					"left JOIN POS_SOF.action_types_lookup at on a.ACTION_TYPE_ID = at.id\r\n" + 
					"left JOIN POS_SOF.actions_status_lookup ats on a.ACTION_STATUS_ID = ats.id\r\n" + 
					"left JOIN POS_SOF.currency c on a.TRX_CURRENCY = c.id\r\n" + 
					"where	a.TERMINAL_ID is not null \r\n");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacctrx.getAccountcode());
			if(srchacctrx.getAccountcode() != "" && srchacctrx.getAccountcode() != null)
			{
				myQuary.add(" a.TERMINAL_ID = '" + srchacctrx.getAccountcode() + "' \r\n");
			}
			System.out.println(srchacctrx.getTransactiontype());
			if(srchacctrx.getTransactiontype() != "" && srchacctrx.getTransactiontype() != null)
			{
				myQuary.add(" at.NAME_PRIMARY_LANG = '" + srchacctrx.getTransactiontype() + "' \r\n");
			}
			System.out.println(srchacctrx.getSoftrxrefnum());
			if(srchacctrx.getSoftrxrefnum() != "" && srchacctrx.getSoftrxrefnum() != null)
			{
				myQuary.add(" a.GROUP_REF_NUM = '" + srchacctrx.getSoftrxrefnum() + "'");
			}
			System.out.println(srchacctrx.getFromdate()+srchacctrx.getTodate());
			if(srchacctrx.getFromdate() != "" && srchacctrx.getFromdate() != null)
			{
				myQuary.add(" a.ACTION_DATE between TO_DATE('" + srchacctrx.getFromdate() + "','Dy, DD Mon yyyy') and TO_DATE('" + srchacctrx.getTodate() + "','Dy, DD Mon yyyy')");
			}
			System.out.println(srchacctrx.getFcrn());
			if(srchacctrx.getFcrn() != "" && srchacctrx.getFcrn() != null)
			{
				myQuary.add(" FW_CUST_REF_NUM = '" + srchacctrx.getFcrn() + "'");
			}
			
			System.out.println(flag);
			if(flag.equalsIgnoreCase("Add"))
			{
			myQuary.add(" rownum = 1 \r\n" + 
					"Order by a.ACTION_DATE DESC ");
			}
			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				AccountTrxPojo addaccountstrx = new AccountTrxPojo();
				addaccountstrx.setAccountcode(resultOfSearchaccount.getString("accountcode")==null?"":resultOfSearchaccount.getString("accountcode"));
				addaccountstrx.setAmount(resultOfSearchaccount.getString("amount")==null?"":resultOfSearchaccount.getString("amount"));
				addaccountstrx.setTransactiontype(resultOfSearchaccount.getString("TRXTYPE")==null?"":resultOfSearchaccount.getString("TRXTYPE"));
				addaccountstrx.setCurrency(resultOfSearchaccount.getString("currency")==null?"":resultOfSearchaccount.getString("currency"));
				addaccountstrx.setDescription(resultOfSearchaccount.getString("description")==null?"":resultOfSearchaccount.getString("description"));
				addaccountstrx.setValueafter(resultOfSearchaccount.getString("newvalue")==null?"":resultOfSearchaccount.getString("newvalue"));
				addaccountstrx.setValuebefore(resultOfSearchaccount.getString("oldvalue")==null?"":resultOfSearchaccount.getString("oldvalue"));


				accountTrx.add(addaccountstrx);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountTrx;
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


