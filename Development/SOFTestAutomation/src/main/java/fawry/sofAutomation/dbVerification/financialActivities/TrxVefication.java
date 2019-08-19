package fawry.sofAutomation.dbVerification.financialActivities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.financialActivities.AccountTrxPojo;
import fawry.sofAutomation.pojos.financialActivities.SearchPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class TrxVefication {

	// SENDING SEARCH POJO
	public ArrayList<AccountTrxPojo> searchAccountTrx(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountTrxPojo> accountTrx = new ArrayList<AccountTrxPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"a.AMOUNT as amount\r\n" + 
					",at.NAME_PRIMARY_LANG as trxtype\r\n" + 
					",at.ACTION_NATURE as actionnature\r\n" + 
					",TO_CHAR(ACTION_DATE, 'dd') as creationdate\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ats.NAME_PRIMARY_LANG as status\r\n" + 
					",a.DESCRIPTION as description\r\n" + 
					",a.OLD_VALUE as oldvalue\r\n" + 
					",a.NEW_VALUE as newvalue\r\n" + 
					",u.USER_NAME as actor\r\n" + 
					",ac.BALANCE as accountbalance\r\n" + 
					",atl.NAME_PRIMARY_LANG as accounttype\r\n" + 
					",a.AMOUNT_IN_ACCT_CURRENCY as amountinaccountcurrency\r\n" + 
					"from POS_SOF.account_actions a\r\n" + 
					"left JOIN POS_SOF.action_types_lookup at on a.ACTION_TYPE_ID = at.id\r\n" + 
					"left JOIN POS_SOF.actions_status_lookup ats on a.ACTION_STATUS_ID = ats.id\r\n" + 
					"left JOIN POS_SOF.currency c on a.TRX_CURRENCY = c.id\r\n" + 
					"left JOIN POS_SOF.users u on a.ACTOR_ID = u.id\r\n" + 
					"left JOIN POS_SOF.Accounts ac on a.TERMINAL_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_types_lookup atl on ac.ACCOUNT_TYPE_ID = atl.id\r\n" + 
					"where a.TERMINAL_ID is not null \r\n" + 
					" AND  a.TERMINAL_ID = '"+srchacctrx.getAccountcode()+"' \r\n"
							+ " AND  rownum = 1 \r\n" + 
							"Order by a.ACTION_DATE DESC ");

			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				AccountTrxPojo addaccountstrx = new AccountTrxPojo();
				addaccountstrx.setAmount(resultOfSearchaccount.getString("amount")==null?"":resultOfSearchaccount.getString("amount"));
				addaccountstrx.setTransactiontype(resultOfSearchaccount.getString("TRXTYPE")==null?"":resultOfSearchaccount.getString("TRXTYPE"));
				addaccountstrx.setCreationDate(resultOfSearchaccount.getString("CREATIONDATE")==null?"":resultOfSearchaccount.getString("CREATIONDATE"));
				addaccountstrx.setCurrency(resultOfSearchaccount.getString("CURRENCY")==null?"":resultOfSearchaccount.getString("CURRENCY"));
				addaccountstrx.setTrxStatus(resultOfSearchaccount.getString("STATUS")==null?"":resultOfSearchaccount.getString("STATUS"));
				addaccountstrx.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				addaccountstrx.setBalance(resultOfSearchaccount.getString("ACCOUNTBALANCE")==null?"":resultOfSearchaccount.getString("ACCOUNTBALANCE"));
				addaccountstrx.setValuebefore(resultOfSearchaccount.getString("OLDVALUE")==null?"":resultOfSearchaccount.getString("OLDVALUE"));
				addaccountstrx.setValueafter(resultOfSearchaccount.getString("NEWVALUE")==null?"":resultOfSearchaccount.getString("NEWVALUE"));
				addaccountstrx.setActor(resultOfSearchaccount.getString("actor")==null?"":resultOfSearchaccount.getString("actor"));


				accountTrx.add(addaccountstrx);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountTrx;
	}
	
	public ArrayList<AccountTrxPojo> TrxCalcs(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountTrxPojo> accountTrx = new ArrayList<AccountTrxPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"a.AMOUNT as amount\r\n" + 
					",at.ACTION_NATURE as actionnature\r\n" + 
					",a.OLD_VALUE as oldvalue\r\n" + 
					",atl.NAME_PRIMARY_LANG as accounttype\r\n" + 
					"from POS_SOF.account_actions a\r\n" + 
					"left JOIN POS_SOF.action_types_lookup at on a.ACTION_TYPE_ID = at.id\r\n" + 
					"left JOIN POS_SOF.Accounts ac on a.TERMINAL_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_types_lookup atl on ac.ACCOUNT_TYPE_ID = atl.id\r\n" + 
					"where a.TERMINAL_ID is not null \r\n" + 
					" AND  a.TERMINAL_ID = '"+srchacctrx.getAccountcode()+"' \r\n"
							+ " AND  rownum = 1 \r\n" + 
							"Order by a.ACTION_DATE DESC ");

	
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				AccountTrxPojo addaccountstrx = new AccountTrxPojo();
				addaccountstrx.setAccountNature(resultOfSearchaccount.getString("accounttype")==null?"":resultOfSearchaccount.getString("accounttype"));
				addaccountstrx.setAmount(resultOfSearchaccount.getString("amount")==null?"":resultOfSearchaccount.getString("amount"));
				addaccountstrx.setActionNature(resultOfSearchaccount.getString("ACTIONNATURE")==null?"":resultOfSearchaccount.getString("ACTIONNATURE"));
				addaccountstrx.setValuebefore(resultOfSearchaccount.getString("OLDVALUE")==null?"":resultOfSearchaccount.getString("OLDVALUE"));

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


