package fawry.sofAutomation.dbVerification.accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class ControlAccountVefication {

	// SENDING SEARCH POJO
	public ArrayList<AccountPojo> controlAccount(SearchPojo srchacc, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();
			queryCond.append("SELECT \r\n" + 
					"a.id\r\n" + 
					",ts.NAME_PRIMARY_LANG as accountstatus\r\n" + 
					",a.DESCRIPTION_PRIMARY_LANG as description\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",actt.NAME_PRIMARY_LANG as accounttype\r\n" + 
					",u.NAME_PRIMARY_LANG as usage\r\n" + 
					",psp.NAME_PRIMARY_LANG\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on a.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.usage_lookup u on a.USAGE_ID = u.ID\r\n" + 
					"left Join POS_SOF.account_types actt on a.ACCOUNT_TYPE_ID = actt.id\r\n" + 
					"left Join POS_SOF.PSP psp on a.PSP_ID = psp.id\r\n" + 
					"where a.code is not null \r\n" + 
					"AND  a.code = '"+srchacc.getAccountCode()+"'");


			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());


			resultOfSearchaccount.next();
			
				AccountPojo addaccounts = new AccountPojo();
				addaccounts.setAccountStatus(resultOfSearchaccount.getString("accountstatus")==null?"":resultOfSearchaccount.getString("accountstatus"));
				addaccounts.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				addaccounts.setCreditLimit(resultOfSearchaccount.getString("creditlimit")==null?"":resultOfSearchaccount.getString("creditlimit"));
				addaccounts.setAccountType(resultOfSearchaccount.getString("accounttype")==null?"":resultOfSearchaccount.getString("accounttype"));
				addaccounts.setUsage(resultOfSearchaccount.getString("USAGE")==null?"":resultOfSearchaccount.getString("USAGE"));
				addaccounts.setPsp(resultOfSearchaccount.getString("pspname")==null?"":resultOfSearchaccount.getString("pspname"));


				addAccount.add(addaccounts);
			
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
	}

	public String add_and(ArrayList<String> myquary)
	{
		String newstring="";
		for(int i=0;i<myquary.size();i++)
		{
			newstring+=" AND "+myquary.get(i);
		}
		return newstring;
	}

}


