package fawry.sofAutomation.dbVerification.accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class SubAccountsVefication {


	public ArrayList<AccountPojo> addSubAccount(SearchPojo srchacc, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();
			
			ArrayList<String> myQuary= new ArrayList<String>(); 
			
			queryCond.append("SELECT \r\n" + 
					"a.id\r\n" + 
					",ts.NAME_PRIMARY_LANG as accountstatus\r\n" + 
					",a.ALIAS as accountalias\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",a.BANK_TERMINAL_ID as bankterminalid\r\n" + 
					",a.PRIMARY_ACCT_ID primaryaccountid\r\n" + 
					",actt.NAME_PRIMARY_LANG as accounttype\r\n" + 
					",acn.NAME_PRIMARY_LANG as accountnature\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on a.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.currency c on a.CURRENCY_ID = c.ID\r\n" + 
					"left Join POS_SOF.account_types actt on a.ACCOUNT_TYPE_ID = actt.id\r\n" + 
					"left Join POS_SOF.account_nature_lookup acn on a.ACCOUNT_NATURE_ID = acn.id\r\n" + 
					"where a.code is not null \r\n" +  
					"AND a.code = '"+srchacc.getAccountCode()+"'\r\n");
			if(flag.equalsIgnoreCase("Add"))
			{
				myQuary.add("ts.NAME_PRIMARY_LANG != 'Deleted'\r\n");
			}
			else if(flag.equalsIgnoreCase("Edit"))
			{
				myQuary.add("ts.NAME_PRIMARY_LANG = 'Deleted'\r\n");
			}
			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());


			resultOfSearchaccount.next();
			
				AccountPojo addaccounts = new AccountPojo();
				addaccounts.setAccountStatus(resultOfSearchaccount.getString("accountstatus")==null?"":resultOfSearchaccount.getString("accountstatus"));
				addaccounts.setAccountAlias(resultOfSearchaccount.getString("accountalias")==null?"":resultOfSearchaccount.getString("accountalias"));
				addaccounts.setCurrency(resultOfSearchaccount.getString("currency")==null?"":resultOfSearchaccount.getString("currency"));
				addaccounts.setCreditLimit(resultOfSearchaccount.getString("creditlimit")==null?"":resultOfSearchaccount.getString("creditlimit"));
				addaccounts.setDailyLimit(resultOfSearchaccount.getString("dailylimit")==null?"":resultOfSearchaccount.getString("dailylimit"));
				addaccounts.setBankTerminal(resultOfSearchaccount.getString("bankterminalid")==null?"":resultOfSearchaccount.getString("bankterminalid"));
				addaccounts.setPrimaryAccountCode(resultOfSearchaccount.getString("primaryaccountid")==null?"":resultOfSearchaccount.getString("primaryaccountid"));
				addaccounts.setAccountType(resultOfSearchaccount.getString("accounttype")==null?"":resultOfSearchaccount.getString("accounttype"));
				addaccounts.setAccountNature(resultOfSearchaccount.getString("accountnature")==null?"":resultOfSearchaccount.getString("accountnature"));


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


