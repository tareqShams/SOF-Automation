package fawry.sofAutomation.dbVerification.accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class SearchVefication {

	// SENDING SEARCH POJO
	public ArrayList<AccountPojo> searchAccount(SearchPojo srchacc, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("SELECT \r\n" + 
					"a.id\r\n" + 
					",ts.NAME_PRIMARY_LANG as terminalStatus\r\n" + 
					",u.NAME_PRIMARY_LANG as usage\r\n" + 
					",r.NAME_PRIMARY_LANG as regionname\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ac.NAME_PRIMARY_LANG as accountclass\r\n" + 
					",ag.NAME_PRIMARY_LANG as accountgroup\r\n" + 
					",ot.NAME_PRIMARY_LANG as officialtype\r\n" + 
					",a.DESCRIPTION_PRIMARY_LANG as description\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",a.PRIMARY_ACCT_ID as primaryaccount\r\n" + 
					",a.ALIAS as subalias\r\n" + 
					",a.BANK_TERMINAL_ID as banktermid\r\n" + 
					",a.CREATION_DATE as creationdate\r\n" + 
					",a.code as accountcode\r\n" + 
					",ms.NAME_PRIMARY_LANG as MERCHANTNAME\r\n" + 
					",csp.NAME_PRIMARY_LANG as csp\r\n" + 
					",actt.NAME_PRIMARY_LANG as accounttype\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on a.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.usage_lookup u on a.USAGE_ID = u.ID\r\n" + 
					"left JOIN POS_SOF.regions r on a.REGION_ID = r.ID\r\n" + 
					"left JOIN POS_SOF.currency c on a.CURRENCY_ID = c.ID\r\n" + 
					"left JOIN POS_SOF.ACCOUNT_CLASS_LOOKUP ac on a.ACCOUNT_CLASS_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_group_lookup ag on a.GROUP_ID = ag.id\r\n" + 
					"left JOIN POS_SOF.official_types_lookup ot on a.OFFICIAL_TYPE_ID = ot.id \r\n" + 
					"left JOIN POS_SOF.customer_profile ms on a.CUSTOMER_PROFILE_ID = ms.id\r\n" + 
					"left JOIN POS_SOF.CSP csp on ms.CSP_ID = csp.id\r\n" + 
					"left Join POS_SOF.account_types actt on a.ACCOUNT_TYPE_ID = actt.id\r\n" + 
					"where a.code is not null \r\n");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacc.getAccountCode());
			if(srchacc.getAccountCode() != "" && srchacc.getAccountCode() != null)
			{
				myQuary.add(" a.code = '" + srchacc.getAccountCode() + "' \r\n");
			}
			System.out.println(srchacc.getOfficialType());
			if (srchacc.getOfficialType() != "" && srchacc.getOfficialType() != null)
			{
				myQuary.add(" ot.NAME_PRIMARY_LANG = '" + srchacc.getOfficialType() + "' \r\n");
			} 
			System.out.println(srchacc.getStatus());
			if (srchacc.getStatus() != "" && srchacc.getStatus() != null)
			{
				myQuary.add(" ts.NAME_PRIMARY_LANG = '" + srchacc.getStatus() + "' \r\n");
			}
			System.out.println(srchacc.getAccountGroup());
			if (srchacc.getAccountGroup() != "" && srchacc.getAccountGroup() != null)
			{
				myQuary.add(" ag.NAME_PRIMARY_LANG = '" + srchacc.getAccountGroup() + "' \r\n");
			}
			System.out.println(srchacc.getOfficialNumber());
			if (srchacc.getOfficialNumber() != "" && srchacc.getOfficialNumber() != null)
			{
				myQuary.add(" a.OFFICIAL_TYPE_NUMBER = '" + srchacc.getOfficialNumber() + "' \r\n");
			}
			System.out.println(srchacc.getMerchantCode());
			if (srchacc.getMerchantCode() != "" && srchacc.getMerchantCode() != null)
			{
				myQuary.add(" ms.code = '" + srchacc.getMerchantCode() + "' \r\n");
			}
			System.out.println(srchacc.getCsp());
			if (srchacc.getCsp() != "" && srchacc.getCsp() != null)
			{
				myQuary.add(" csp.NAME_PRIMARY_LANG = '" + srchacc.getCsp() + "' \r\n");
			}
			System.out.println(srchacc.getBankTerminal());
			if (srchacc.getBankTerminal() != "" && srchacc.getBankTerminal() != null)
			{
				myQuary.add(" a.BANK_TERMINAL_ID = '" + srchacc.getBankTerminal() + "' \r\n");
			}
			System.out.println(srchacc.getAccountType());
			if (srchacc.getAccountType() != "" && srchacc.getAccountType() != null)
			{
				myQuary.add(" actt.NAME_PRIMARY_LANG like '" + srchacc.getAccountType() + "%' \r\n");
			}
			System.out.println(srchacc.getTerminalCode());
			if (srchacc.getTerminalCode() != "" && srchacc.getTerminalCode() != null)
			{
				myQuary.add(" t.code = '" + srchacc.getTerminalCode() + "' \r\n");
			}
			System.out.println(srchacc.getIsCredit());
			if (srchacc.getIsCredit() == null)
			{
				myQuary.add(" a.PRIMARY_ACCT_ID is null \r\n ");
			}

			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				AccountPojo addaccounts = new AccountPojo();
				addaccounts.setAccountCode(resultOfSearchaccount.getString("ACCOUNTCODE")==null?"":resultOfSearchaccount.getString("ACCOUNTCODE"));
				addaccounts.setTerminalStatus(resultOfSearchaccount.getString("TERMINALSTATUS")==null?"":resultOfSearchaccount.getString("TERMINALSTATUS"));
				addaccounts.setUsage(resultOfSearchaccount.getString("USAGE")==null?"":resultOfSearchaccount.getString("USAGE"));
				addaccounts.setRegion(resultOfSearchaccount.getString("REGIONNAME")==null?"":resultOfSearchaccount.getString("REGIONNAME"));
				addaccounts.setCurrency(resultOfSearchaccount.getString("CURRENCY")==null?"":resultOfSearchaccount.getString("CURRENCY"));
				addaccounts.setAccountClass(resultOfSearchaccount.getString("ACCOUNTCLASS")==null?"":resultOfSearchaccount.getString("ACCOUNTCLASS"));
				addaccounts.setAccountGroup(resultOfSearchaccount.getString("ACCOUNTGROUP")==null?"":resultOfSearchaccount.getString("ACCOUNTGROUP"));
				addaccounts.setOfficialType(resultOfSearchaccount.getString("OFFICIALTYPE")==null?"":resultOfSearchaccount.getString("OFFICIALTYPE"));
				addaccounts.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				addaccounts.setCreditLimit(resultOfSearchaccount.getString("CREDITLIMIT")==null?"":resultOfSearchaccount.getString("CREDITLIMIT"));
				addaccounts.setDailyLimit(resultOfSearchaccount.getString("DAILYLIMIT")==null?"":resultOfSearchaccount.getString("DAILYLIMIT"));
				addaccounts.setMerchantName(resultOfSearchaccount.getString("MERCHANTNAME")==null?"":resultOfSearchaccount.getString("MERCHANTNAME"));
				addaccounts.setCsp(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));


				addAccount.add(addaccounts);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
	}

	public ArrayList<AccountPojo> addPrimaryAccount(SearchPojo srchacc, String flag)
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
					",u.NAME_PRIMARY_LANG as usage\r\n" + 
					",r.NAME_PRIMARY_LANG as regionname\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ac.NAME_PRIMARY_LANG as accountclass\r\n" + 
					",ag.NAME_PRIMARY_LANG as accountgroup\r\n" + 
					",ot.NAME_PRIMARY_LANG as officialtype\r\n" + 
					",a.DESCRIPTION_PRIMARY_LANG as description\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",a.PRIMARY_ACCT_ID as primaryaccount\r\n" + 
					",a.ALIAS as subalias\r\n" + 
					",a.BANK_TERMINAL_ID as banktermid\r\n" + 
					",a.CREATION_DATE as creationdate\r\n" + 
					",a.code as accountcode\r\n" + 
					",ms.NAME_PRIMARY_LANG as MERCHANTNAME\r\n" + 
					",csp.NAME_PRIMARY_LANG as csp\r\n" + 
					",actt.NAME_PRIMARY_LANG as accounttype\r\n" + 
					",tr.DEVICE_ID as terminalserial\r\n" + 
					",tr.TERMINAL_DESC_SECONDRY_LANG as terminaldescription\r\n" + 
					",tr.TERMINAL_NAME_PRIMARY_LANG as terminalname\r\n" + 
					",ttl.NAME_PRIMARY_LANG as terminaltype\r\n" + 
					",tsl.name_primary_lang as terminalstatus\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on a.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.usage_lookup u on a.USAGE_ID = u.ID\r\n" + 
					"left JOIN POS_SOF.regions r on a.REGION_ID = r.ID\r\n" + 
					"left JOIN POS_SOF.currency c on a.CURRENCY_ID = c.ID\r\n" + 
					"left JOIN POS_SOF.ACCOUNT_CLASS_LOOKUP ac on a.ACCOUNT_CLASS_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_group_lookup ag on a.GROUP_ID = ag.id\r\n" + 
					"left JOIN POS_SOF.official_types_lookup ot on a.OFFICIAL_TYPE_ID = ot.id \r\n" + 
					"left JOIN POS_SOF.customer_profile ms on a.CUSTOMER_PROFILE_ID = ms.id\r\n" + 
					"left JOIN POS_SOF.CSP csp on ms.CSP_ID = csp.id\r\n" + 
					"left Join POS_SOF.account_types actt on a.ACCOUNT_TYPE_ID = actt.id\r\n" + 
					"left Join POS_SOF.terminals tr on a.id = tr.POS_TERMINAL_ID\r\n" + 
					"left Join POS_SOF.terminal_types_lookup ttl on tr.TERMINAL_TYPE_ID = ttl.ID\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup tsl on tr.TERMINAL_STATUS_ID = tsl.id \r\n" + 
					"where a.code is not null \r\n" + 
					" AND  a.PRIMARY_ACCT_ID is null \r\n"+
					"AND  a.code = '"+srchacc.getAccountCode()+"'");


			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			resultOfSearchaccount.next();
			
				AccountPojo addaccounts = new AccountPojo();
				addaccounts.setAccountCode(resultOfSearchaccount.getString("ACCOUNTCODE")==null?"":resultOfSearchaccount.getString("ACCOUNTCODE"));
				addaccounts.setAccountStatus(resultOfSearchaccount.getString("accountstatus")==null?"":resultOfSearchaccount.getString("accountstatus"));
				addaccounts.setUsage(resultOfSearchaccount.getString("USAGE")==null?"":resultOfSearchaccount.getString("USAGE"));
				addaccounts.setRegion(resultOfSearchaccount.getString("REGIONNAME")==null?"":resultOfSearchaccount.getString("REGIONNAME"));
				addaccounts.setCurrency(resultOfSearchaccount.getString("CURRENCY")==null?"":resultOfSearchaccount.getString("CURRENCY"));
				addaccounts.setAccountClass(resultOfSearchaccount.getString("ACCOUNTCLASS")==null?"":resultOfSearchaccount.getString("ACCOUNTCLASS"));
				addaccounts.setAccountGroup(resultOfSearchaccount.getString("ACCOUNTGROUP")==null?"":resultOfSearchaccount.getString("ACCOUNTGROUP"));
				addaccounts.setOfficialType(resultOfSearchaccount.getString("OFFICIALTYPE")==null?"":resultOfSearchaccount.getString("OFFICIALTYPE"));
				addaccounts.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				addaccounts.setCreditLimit(resultOfSearchaccount.getString("CREDITLIMIT")==null?"":resultOfSearchaccount.getString("CREDITLIMIT"));
				addaccounts.setDailyLimit(resultOfSearchaccount.getString("DAILYLIMIT")==null?"":resultOfSearchaccount.getString("DAILYLIMIT"));
				addaccounts.setMerchantName(resultOfSearchaccount.getString("MERCHANTNAME")==null?"":resultOfSearchaccount.getString("MERCHANTNAME"));
				addaccounts.setCsp(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));
				
				ArrayList<TerminalPojo> accountTerminals =  new ArrayList<TerminalPojo>();
				while(resultOfSearchaccount.next())
	              {
					TerminalPojo terminal =  new TerminalPojo();
					
					terminal.setTerminalstatus(resultOfSearchaccount.getString("TERMINALSTATUS")==null?"":resultOfSearchaccount.getString("TERMINALSTATUS"));
					terminal.setTerminalType(resultOfSearchaccount.getString("TERMINALTYPE")==null?"":resultOfSearchaccount.getString("TERMINALTYPE"));
					terminal.setSerialNumber(resultOfSearchaccount.getString("TERMINALSERIAL")==null?"":resultOfSearchaccount.getString("TERMINALSERIAL"));
					terminal.setName(resultOfSearchaccount.getString("TERMINALNAME")==null?"":resultOfSearchaccount.getString("TERMINALNAME"));
					terminal.setDescription(resultOfSearchaccount.getString("TERMINALDESCRIPTION")==null?"":resultOfSearchaccount.getString("TERMINALDESCRIPTION"));

					accountTerminals.add(terminal);  
	              }
				addaccounts.setTerminals(accountTerminals);

				addAccount.add(addaccounts);
			
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
	}

	
	public ArrayList<AccountPojo> searchAccountTerminals(SearchPojo srchacc, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("SELECT \r\n" + 
					"a.id\r\n" + 
					",tsa.NAME_PRIMARY_LANG as accountstatus\r\n" + 
					",ts.NAME_PRIMARY_LANG as terminalStatus\r\n" + 
					",u.NAME_PRIMARY_LANG as usage\r\n" + 
					",r.NAME_PRIMARY_LANG as regionname\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ac.NAME_PRIMARY_LANG as accountclass\r\n" + 
					",ag.NAME_PRIMARY_LANG as accountgroup\r\n" + 
					",ot.NAME_PRIMARY_LANG as officialtype\r\n" + 
					",a.DESCRIPTION_PRIMARY_LANG as description\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",a.PRIMARY_ACCT_ID as primaryaccount\r\n" + 
					",a.ALIAS as subalias\r\n" + 
					",a.BANK_TERMINAL_ID as banktermid\r\n" + 
					",a.CREATION_DATE as creationdate\r\n" + 
					",a.code as accountcode\r\n" + 
					",ms.NAME_PRIMARY_LANG as MERCHANTNAME\r\n" + 
					",csp.NAME_PRIMARY_LANG as csp\r\n" + 
					",actt.NAME_PRIMARY_LANG as accounttype\r\n" + 
					",t.code as terminalcode\r\n" + 
					",tt.NAME_PRIMARY_LANG as terminaltype\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup tsa on a.TERMINAL_STATUS_ID = tsa.id \r\n" + 
					"left JOIN POS_SOF.terminals t on a.id = t.POS_TERMINAL_ID\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on t.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.terminal_types_lookup tt on t.TERMINAL_TYPE_ID = tt.id\r\n" + 
					"left JOIN POS_SOF.usage_lookup u on a.USAGE_ID = u.ID\r\n" + 
					"left JOIN POS_SOF.regions r on a.REGION_ID = r.ID\r\n" + 
					"left JOIN POS_SOF.currency c on a.CURRENCY_ID = c.ID\r\n" + 
					"left JOIN POS_SOF.ACCOUNT_CLASS_LOOKUP ac on a.ACCOUNT_CLASS_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_group_lookup ag on a.GROUP_ID = ag.id\r\n" + 
					"left JOIN POS_SOF.official_types_lookup ot on a.OFFICIAL_TYPE_ID = ot.id \r\n" + 
					"left JOIN POS_SOF.customer_profile ms on a.CUSTOMER_PROFILE_ID = ms.id\r\n" + 
					"left JOIN POS_SOF.CSP csp on ms.CSP_ID = csp.id\r\n" + 
					"left Join POS_SOF.account_types actt on a.ACCOUNT_TYPE_ID = actt.id\r\n" + 
					"where a.code is not null ");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacc.getAccountCode());
			if(srchacc.getAccountCode() != "" && srchacc.getAccountCode() != null)
			{
				myQuary.add(" a.code = '" + srchacc.getAccountCode() + "' ");
			}
			System.out.println(srchacc.getOfficialType());
			if (srchacc.getOfficialType() != "" && srchacc.getOfficialType() != null)
			{
				myQuary.add(" ot.NAME_PRIMARY_LANG = '" + srchacc.getOfficialType() + "'");
			} 
			System.out.println(srchacc.getStatus());
			if (srchacc.getStatus() != "" && srchacc.getStatus() != null)
			{
				myQuary.add(" ts.NAME_PRIMARY_LANG = '" + srchacc.getStatus() + "'");
			}
			System.out.println(srchacc.getAccountGroup());
			if (srchacc.getAccountGroup() != "" && srchacc.getAccountGroup() != null)
			{
				myQuary.add(" ag.NAME_PRIMARY_LANG = '" + srchacc.getAccountGroup() + "'");
			}
			System.out.println(srchacc.getOfficialNumber());
			if (srchacc.getOfficialNumber() != "" && srchacc.getOfficialNumber() != null)
			{
				myQuary.add(" a.OFFICIAL_TYPE_NUMBER = '" + srchacc.getOfficialNumber() + "'");
			}
			System.out.println(srchacc.getMerchantCode());
			if (srchacc.getMerchantCode() != "" && srchacc.getMerchantCode() != null)
			{
				myQuary.add(" ms.code = '" + srchacc.getMerchantCode() + "'");
			}
			System.out.println(srchacc.getCsp());
			if (srchacc.getCsp() != "" && srchacc.getCsp() != null)
			{
				myQuary.add(" csp.NAME_PRIMARY_LANG = '" + srchacc.getCsp() + "'");
			}
			System.out.println(srchacc.getBankTerminal());
			if (srchacc.getBankTerminal() != "" && srchacc.getBankTerminal() != null)
			{
				myQuary.add(" a.BANK_TERMINAL_ID = '" + srchacc.getBankTerminal() + "'");
			}
			System.out.println(srchacc.getAccountType());
			if (srchacc.getAccountType() != "" && srchacc.getAccountType() != null)
			{
				myQuary.add(" actt.NAME_PRIMARY_LANG like '" + srchacc.getAccountType() + "%'");
			}
			System.out.println(srchacc.getTerminalCode());
			if (srchacc.getTerminalCode() != "" && srchacc.getTerminalCode() != null)
			{
				myQuary.add(" t.code = '" + srchacc.getTerminalCode() + "'");
			}
			System.out.println(srchacc.getIsCredit());
			if (srchacc.getIsCredit() == null)
			{
				myQuary.add(" a.PRIMARY_ACCT_ID is null ");
			}
			System.out.println(flag);
			if(flag.equalsIgnoreCase("Sub"))
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
				AccountPojo addaccounts = new AccountPojo();
				addaccounts.setAccountCode(resultOfSearchaccount.getString("ACCOUNTCODE")==null?"":resultOfSearchaccount.getString("ACCOUNTCODE"));
				addaccounts.setAccountStatus(resultOfSearchaccount.getString("ACCOUNTSTATUS")==null?"":resultOfSearchaccount.getString("ACCOUNTSTATUS"));
				addaccounts.setTerminalStatus(resultOfSearchaccount.getString("TERMINALSTATUS")==null?"":resultOfSearchaccount.getString("TERMINALSTATUS"));
				addaccounts.setUsage(resultOfSearchaccount.getString("USAGE")==null?"":resultOfSearchaccount.getString("USAGE"));
				addaccounts.setRegion(resultOfSearchaccount.getString("REGIONNAME")==null?"":resultOfSearchaccount.getString("REGIONNAME"));
				addaccounts.setCurrency(resultOfSearchaccount.getString("CURRENCY")==null?"":resultOfSearchaccount.getString("CURRENCY"));
				addaccounts.setAccountClass(resultOfSearchaccount.getString("ACCOUNTCLASS")==null?"":resultOfSearchaccount.getString("ACCOUNTCLASS"));
				addaccounts.setAccountGroup(resultOfSearchaccount.getString("ACCOUNTGROUP")==null?"":resultOfSearchaccount.getString("ACCOUNTGROUP"));
				addaccounts.setOfficialType(resultOfSearchaccount.getString("OFFICIALTYPE")==null?"":resultOfSearchaccount.getString("OFFICIALTYPE"));
				addaccounts.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				addaccounts.setCreditLimit(resultOfSearchaccount.getString("CREDITLIMIT")==null?"":resultOfSearchaccount.getString("CREDITLIMIT"));
				addaccounts.setDailyLimit(resultOfSearchaccount.getString("DAILYLIMIT")==null?"":resultOfSearchaccount.getString("DAILYLIMIT"));
				//addaccounts.set(resultOfSearchaccount.getString("MERCHANTNUMBER")==null?"":resultOfSearchaccount.getString("MERCHANTNUMBER"));
				addaccounts.setMerchantName(resultOfSearchaccount.getString("MERCHANTNAME")==null?"":resultOfSearchaccount.getString("MERCHANTNAME"));
				addaccounts.setCsp(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));
				addaccounts.setTerminalType(resultOfSearchaccount.getString("TERMINALTYPE")==null?"":resultOfSearchaccount.getString("TERMINALTYPE"));


				//addaccounts.setPrimaryAccountCode(resultOfSearchaccount.getString("PRIMARYACCOUNT")==null?"":resultOfSearchaccount.getString("primaryaccount"));
				//addaccounts.setAccountAlias(resultOfSearchaccount.getString("SUBALIAS")==null?"":resultOfSearchaccount.getString("SUBALIAS"));
				//addaccounts.setBankTerminal(resultOfSearchaccount.getString("BANKTERMID")==null?"":resultOfSearchaccount.getString("BANKTERMID"));
				//addaccounts.setBankTerminal(resultOfSearchaccount.getString("MERCHANT")==null?"":resultOfSearchaccount.getString("MERCHANTNAME"));


				addAccount.add(addaccounts);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
	}



	public ArrayList<TerminalPojo> searchTerminal(SearchPojo srchtrm, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<TerminalPojo> terminal = new ArrayList<TerminalPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("Select \r\n" + 
					"t.CODE as terminalcode, \r\n" + 
					"t.DEVICE_ID as serialnumber,\r\n" + 
					"t.IS_SPARE_TERMINAL as isspare,\r\n" + 
					"t.Pin as terminalpin,\r\n" + 
					"ts.NAME_PRIMARY_LANG as terminalstatus, \r\n" + 
					"tt.NAME_PRIMARY_LANG as terminaltype, \r\n" + 
					"a.code as accountcode\r\n" + 
					"from POS_SOF.TERMINALS t\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on t.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.terminal_types_lookup tt on t.TERMINAL_TYPE_ID = tt.id \r\n" + 
					"left JOIN POS_SOF.ACCOUNTS a on t.POS_TERMINAL_ID = a.id  where  t.code is not null");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchtrm.getTerminalCode());
			if(srchtrm.getTerminalCode() != "" && srchtrm.getTerminalCode() != null)
			{
				myQuary.add(" t.code = '" + srchtrm.getTerminalCode() + "'");
			}
			System.out.println(srchtrm.getAccountCode());
			if(srchtrm.getAccountCode() != "" && srchtrm.getAccountCode() != null)
			{
				myQuary.add(" a.code = '" + srchtrm.getAccountCode() + "'");
			}
			System.out.println(srchtrm.getSeriallNumber());
			if (srchtrm.getSeriallNumber() != "" && srchtrm.getSeriallNumber() != null)
			{
				myQuary.add(" t.DEVICE_ID = '" + srchtrm.getSeriallNumber() + "'");
				//COUNT++;
			} 
			System.out.println(srchtrm.getTerminalType());
			if (srchtrm.getTerminalType() != "" && srchtrm.getTerminalType() != null)
			{
				myQuary.add(" tt.NAME_PRIMARY_LANG = '" + srchtrm.getTerminalType() + "'-- and  a.PRIMARY_ACCT_ID is null");

			}
			System.out.println(srchtrm.getSapreNormalType());
			if (srchtrm.getSapreNormalType() != "" && srchtrm.getSapreNormalType() != null)
			{
				if (srchtrm.getSapreNormalType().equalsIgnoreCase("Normal"))
				{
					srchtrm.setSapreNormalType("N");
					System.out.println(srchtrm.getSapreNormalType());
					myQuary.add(" t.IS_SPARE_TERMINAL = '" + srchtrm.getSapreNormalType() + "'");
				}
				else if (srchtrm.getSapreNormalType().equalsIgnoreCase("Spare"))
				{myQuary.add(" t.IS_SPARE_TERMINAL is null");}

			}

			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());




			while(resultOfSearchaccount.next())
			{
				TerminalPojo searchterm = new TerminalPojo();

				searchterm.SetTerminalCode(resultOfSearchaccount.getString("terminalcode")==null?"":resultOfSearchaccount.getString("terminalcode"));
				searchterm.setSerialNumber(resultOfSearchaccount.getString("serialnumber")==null?"":resultOfSearchaccount.getString("serialnumber"));
				if (resultOfSearchaccount.getString("isspare") == "N") {
					searchterm.setSapreNormalType("Normal");}
				else if (resultOfSearchaccount.getString("isspare") == null) {
					searchterm.setSapreNormalType("Spare");}
				System.out.println(searchterm.getSapreNormalType());
				searchterm.setTerminalstatus(resultOfSearchaccount.getString("terminalstatus")==null?"":resultOfSearchaccount.getString("terminalstatus"));
				searchterm.setTerminalType(resultOfSearchaccount.getString("terminaltype")==null?"":resultOfSearchaccount.getString("terminaltype"));
				searchterm.setAccountcode(resultOfSearchaccount.getString("accountcode")==null?"":resultOfSearchaccount.getString("accountcode"));
				searchterm.setTerminalPin(resultOfSearchaccount.getString("terminalpin")==null?"":resultOfSearchaccount.getString("terminalpin"));


				terminal.add(searchterm);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return terminal;
	}

	//Update account Status to be later used in account/terminal status page
	public void updateAccountStatus(SearchPojo srchacc, String flag) throws SQLException
	{

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();
			System.out.println(srchacc.getAccountstatusid()+"/"+srchacc.getAccountCode());
			queryCond.append("UPDATE POS_SOF.accounts a \r\n" + 
					"SET a.TERMINAL_STATUS_ID = "+srchacc.getAccountstatusid()+ " \r\n" + 
					"WHERE a.code = '"+ srchacc.getAccountCode()+"'");

			System.out.println(queryCond.toString());
			conn.executeVerificationQuery(myconection, queryCond.toString());
			conn.closeDBConnection(myconection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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


