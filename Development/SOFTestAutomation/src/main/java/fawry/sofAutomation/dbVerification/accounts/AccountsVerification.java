package fawry.sofAutomation.dbVerification.accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.synth.Region;

import fawry.sofAutomation.pojos.accounts.AccountPojo;
import fawry.sofAutomation.pojos.accounts.CustomerPojo;
import fawry.sofAutomation.pojos.accounts.RegionPojo;
import fawry.sofAutomation.pojos.accounts.SearchPojo;
import fawry.sofAutomation.pojos.accounts.TerminalPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class AccountsVerification {

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
				myQuary.add(" actt.NAME_PRIMARY_LANG like '%" + srchacc.getAccountType() + "%' \r\n");
			}
			System.out.println(srchacc.getTerminalCode());
			if (srchacc.getTerminalCode() != "" && srchacc.getTerminalCode() != null)
			{
				myQuary.add(" t.code = '" + srchacc.getTerminalCode() + "' \r\n");
			}
			//
			if (srchacc.getUsage() != "" && srchacc.getUsage() != null)
			{
				myQuary.add(" u.NAME_PRIMARY_LANG = '" + srchacc.getUsage() + "' \r\n");
			}
			if (srchacc.getName() != "" && srchacc.getName() != null)
			{
				myQuary.add(" a.NAME_PRIMARY_LANG ='" + srchacc.getName() + "' \r\n");
			}
			if (srchacc.getDescription() != "" && srchacc.getDescription() != null)
			{
				myQuary.add(" a.DESCRIPTION_PRIMARY_LANG like '%" + srchacc.getDescription() + "%' \r\n");
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
					",r.code as regioncode\r\n" + 
					",d.NAME_PRIMARY_LANG as district\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ac.NAME_PRIMARY_LANG as accountclass\r\n" + 
					",ag.NAME_PRIMARY_LANG as accountgroup\r\n" + 
					",ot.NAME_PRIMARY_LANG as officialtype\r\n" + 
					",a.DESCRIPTION_PRIMARY_LANG as description\r\n" + 
					",a.balance as balance\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",a.CREATION_DATE as creationdate\r\n" + 
					",a.code as accountcode\r\n" + 
					",a.OFFICIAL_TYPE_NUMBER as officialtypenumber\r\n" + 
					",ms.NAME_PRIMARY_LANG as MERCHANTNAME\r\n" + 
					",ms.code as customercode\r\n" + 
					",msc.NAME_PRIMARY_LANG as customercatagory\r\n" + 
					",mscsp.NAME_PRIMARY_LANG as customercsp\r\n" + 
					",actt.NAME_PRIMARY_LANG as accounttype\r\n" + 
					",tr.DEVICE_ID as terminalserial\r\n" + 
					",tr.TERMINAL_DESC_SECONDRY_LANG as terminaldescription\r\n" + 
					",tr.TERMINAL_NAME_PRIMARY_LANG as terminalname\r\n" + 
					",ttl.NAME_PRIMARY_LANG as terminaltype\r\n" + 
					",tsl.name_primary_lang as terminalstatus\r\n" + 
					",msc.name_primary_lang as customercategory\r\n" + 
					",mspt.name_primary_lang as customerprofiletype\r\n" + 
					",tr.PIN as terminalpin\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on a.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.usage_lookup u on a.USAGE_ID = u.ID\r\n" + 
					"left JOIN POS_SOF.regions r on a.REGION_ID = r.ID\r\n" + 
					"left JOIN POS_SOF.districts d on r.DSTR_ID = d.ID\r\n" + 
					"left JOIN POS_SOF.currency c on a.CURRENCY_ID = c.ID\r\n" + 
					"left JOIN POS_SOF.ACCOUNT_CLASS_LOOKUP ac on a.ACCOUNT_CLASS_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_group_lookup ag on a.GROUP_ID = ag.id\r\n" + 
					"left JOIN POS_SOF.official_types_lookup ot on a.OFFICIAL_TYPE_ID = ot.id \r\n" + 
					"left JOIN POS_SOF.customer_profile ms on a.CUSTOMER_PROFILE_ID = ms.id\r\n" + 
					"left JOIN POS_SOF.CSP mscsp on ms.CSP_ID = mscsp.id\r\n" + 
					"left JOIN POS_SOF.CUSTOMER_PROFILE_TYPES_LOOKUP mspt on ms.CUSTOMER_PROFILE_TYPES_ID = mspt.id\r\n" + 
					"left JOIN POS_SOF.customer_categories msc on ms.CUST_CATEGORY_ID = msc.id\r\n" + 
					"left Join POS_SOF.account_types actt on a.ACCOUNT_TYPE_ID = actt.id\r\n" + 
					"left Join POS_SOF.terminals tr on a.id = tr.POS_TERMINAL_ID\r\n" + 
					"left Join POS_SOF.terminal_types_lookup ttl on tr.TERMINAL_TYPE_ID = ttl.ID\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup tsl on tr.TERMINAL_STATUS_ID = tsl.id \r\n" + 
					"where a.code is not null \r\n" + 
					" AND  a.PRIMARY_ACCT_ID is null \r\n" + 
					"AND  a.code = '"+srchacc.getAccountCode()+"'\r\n"+
					"Order by tr.TERMINAL_NAME_PRIMARY_LANG DESC ");


			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			resultOfSearchaccount.next();

			AccountPojo addaccounts = new AccountPojo();
			addaccounts.setAccountCode(resultOfSearchaccount.getString("ACCOUNTCODE")==null?"":resultOfSearchaccount.getString("ACCOUNTCODE"));
			addaccounts.setAccountStatus(resultOfSearchaccount.getString("accountstatus")==null?"":resultOfSearchaccount.getString("accountstatus"));
			addaccounts.setUsage(resultOfSearchaccount.getString("USAGE")==null?"":resultOfSearchaccount.getString("USAGE"));
			addaccounts.setCurrency(resultOfSearchaccount.getString("CURRENCY")==null?"":resultOfSearchaccount.getString("CURRENCY"));
			addaccounts.setAccountClass(resultOfSearchaccount.getString("ACCOUNTCLASS")==null?"":resultOfSearchaccount.getString("ACCOUNTCLASS"));
			addaccounts.setAccountGroup(resultOfSearchaccount.getString("ACCOUNTGROUP")==null?"":resultOfSearchaccount.getString("ACCOUNTGROUP"));
			addaccounts.setOfficialType(resultOfSearchaccount.getString("OFFICIALTYPE")==null?"":resultOfSearchaccount.getString("OFFICIALTYPE"));
			addaccounts.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
			addaccounts.setBalance(resultOfSearchaccount.getString("BALANCE")==null?"":resultOfSearchaccount.getString("BALANCE"));
			addaccounts.setCreditLimit(resultOfSearchaccount.getString("CREDITLIMIT")==null?"":resultOfSearchaccount.getString("CREDITLIMIT"));
			addaccounts.setDailyLimit(resultOfSearchaccount.getString("DAILYLIMIT")==null?"":resultOfSearchaccount.getString("DAILYLIMIT"));
			addaccounts.setOfficialnumber(resultOfSearchaccount.getString("OFFICIALTYPENUMBER")==null?"":resultOfSearchaccount.getString("OFFICIALTYPENUMBER"));
			addaccounts.setCsp(resultOfSearchaccount.getString("CUSTOMERCSP")==null?"":resultOfSearchaccount.getString("CUSTOMERCSP"));
			addaccounts.setAccountType(resultOfSearchaccount.getString("ACCOUNTTYPE")==null?"":resultOfSearchaccount.getString("ACCOUNTTYPE"));

			ArrayList<CustomerPojo> customers =  new ArrayList<CustomerPojo>();
			CustomerPojo customer = new CustomerPojo();
			customer.setCustomercatagory(resultOfSearchaccount.getString("CUSTOMERCATEGORY")==null?"":resultOfSearchaccount.getString("CUSTOMERCATEGORY"));
			customer.setCustomerprofilecode(resultOfSearchaccount.getString("CUSTOMERCODE")==null?"":resultOfSearchaccount.getString("CUSTOMERCODE"));
			customer.setCustomerprofiletype(resultOfSearchaccount.getString("CUSTOMERPROFILETYPE")==null?"":resultOfSearchaccount.getString("CUSTOMERPROFILETYPE"));
			customer.setName(resultOfSearchaccount.getString("MERCHANTNAME")==null?"":resultOfSearchaccount.getString("MERCHANTNAME"));
			customers.add(customer);
			addaccounts.setCustomer(customers);

			ArrayList<RegionPojo> regions =  new ArrayList<RegionPojo>();
			RegionPojo region = new RegionPojo();
			region.setCode(resultOfSearchaccount.getString("REGIONCODE")==null?"":resultOfSearchaccount.getString("REGIONCODE"));
			region.setDistrict(resultOfSearchaccount.getString("DISTRICT")==null?"":resultOfSearchaccount.getString("DISTRICT"));
			region.setName(resultOfSearchaccount.getString("REGIONNAME")==null?"":resultOfSearchaccount.getString("REGIONNAME"));
			regions.add(region);
			addaccounts.setRegions(regions);

			ArrayList<TerminalPojo> accountTerminals =  new ArrayList<TerminalPojo>();

			TerminalPojo terminal =  new TerminalPojo();

			terminal.setTerminalstatus(resultOfSearchaccount.getString("TERMINALSTATUS")==null?"":resultOfSearchaccount.getString("TERMINALSTATUS"));
			terminal.setTerminalType(resultOfSearchaccount.getString("TERMINALTYPE")==null?"":resultOfSearchaccount.getString("TERMINALTYPE"));
			terminal.setSerialNumber(resultOfSearchaccount.getString("TERMINALSERIAL")==null?"":resultOfSearchaccount.getString("TERMINALSERIAL"));
			terminal.setName(resultOfSearchaccount.getString("TERMINALNAME")==null?"":resultOfSearchaccount.getString("TERMINALNAME"));
			terminal.setDescription(resultOfSearchaccount.getString("TERMINALDESCRIPTION")==null?"":resultOfSearchaccount.getString("TERMINALDESCRIPTION"));
			terminal.setHashedPin(resultOfSearchaccount.getString("TERMINALPIN")==null?"":resultOfSearchaccount.getString("TERMINALPIN"));

			accountTerminals.add(terminal); 

			while(resultOfSearchaccount.next())
			{
				terminal.setTerminalstatus(resultOfSearchaccount.getString("TERMINALSTATUS")==null?"":resultOfSearchaccount.getString("TERMINALSTATUS"));
				terminal.setTerminalType(resultOfSearchaccount.getString("TERMINALTYPE")==null?"":resultOfSearchaccount.getString("TERMINALTYPE"));
				terminal.setSerialNumber(resultOfSearchaccount.getString("TERMINALSERIAL")==null?"":resultOfSearchaccount.getString("TERMINALSERIAL"));
				terminal.setName(resultOfSearchaccount.getString("TERMINALNAME")==null?"":resultOfSearchaccount.getString("TERMINALNAME"));
				terminal.setDescription(resultOfSearchaccount.getString("TERMINALDESCRIPTION")==null?"":resultOfSearchaccount.getString("TERMINALDESCRIPTION"));
				terminal.setHashedPin(resultOfSearchaccount.getString("TERMINALPIN")==null?"":resultOfSearchaccount.getString("TERMINALPIN"));

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

	public ArrayList<AccountPojo> accountProfile(SearchPojo srchacc)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("SELECT  \r\n" + 
					"a.id \r\n" + 
					",a.code as AccountCode \r\n" + 
					",aps.NAME_PRIMARY_LANG as profileId \r\n" + 
					"from POS_SOF.ACCOUNTS a \r\n" + 
					"left JOIN ACCOUNT_PROFILES_LOOKUP aps on a.PROFILE_ID = aps.id  \r\n" + 
					"where a.code ='"+srchacc.getAccountCode()+"'\r\n");
			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			resultOfSearchaccount.next();

			AccountPojo addaccounts = new AccountPojo();
			addaccounts.setAccountCode(resultOfSearchaccount.getString("ACCOUNTCODE")==null?"":resultOfSearchaccount.getString("ACCOUNTCODE"));
			addaccounts.setProfileid(resultOfSearchaccount.getString("profileId")==null?"":resultOfSearchaccount.getString("profileId"));
			
			addAccount.add(addaccounts);
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
	}
	
	
	public ArrayList<AccountPojo> cspProgramData(SearchPojo srchacc)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("SELECT  \r\n" + 
					"cp.FINANCE_PROGRAM_ID as fnID \r\n" + 
					",act.NAME_PRIMARY_LANG as acctType \r\n" + 
					",ul.NAME_PRIMARY_LANG as acctUsage\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",cp.SUB_ACCOUNT_DAILY_LIMIT as dailyLimit\r\n" + 
					",cp.SUB_ACCOUNT_CREDIT_LIMIT as creditLimit\r\n" + 
					"from CSP_PROGRAM cp\r\n" + 
					"left JOIN ACCOUNT_TYPES act on cp.SUB_ACCOUNT_TYPE_ID = act.id  \r\n" + 
					"left JOIN csp cs on cp.CSP_ID = cs.id \r\n" + 
					"left JOIN USAGE_LOOKUP ul on cp.SUB_ACCOUNT_USAGE_ID = ul.id  \r\n" + 
					"left JOIN CURRENCY c on cp.SUB_ACCOUNT_CURRENCY_ID = c.id  \r\n" + 
					"where cs.NAME_PRIMARY_LANG = '"+srchacc.getCsp()+"'\r\n");
			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			resultOfSearchaccount.next();

			AccountPojo addaccounts = new AccountPojo();
			addaccounts.setFinanceProgram(resultOfSearchaccount.getString("fnID")==null?"":resultOfSearchaccount.getString("fnID"));
			addaccounts.setAccountType(resultOfSearchaccount.getString("acctType")==null?"":resultOfSearchaccount.getString("acctType"));
			addaccounts.setUsage(resultOfSearchaccount.getString("acctUsage")==null?"":resultOfSearchaccount.getString("acctUsage"));
			addaccounts.setCurrency(resultOfSearchaccount.getString("currency")==null?"":resultOfSearchaccount.getString("currency"));
			addaccounts.setDailyLimit(resultOfSearchaccount.getString("dailyLimit")==null?"":resultOfSearchaccount.getString("dailyLimit"));
			addaccounts.setCreditLimit(resultOfSearchaccount.getString("creditLimit")==null?"":resultOfSearchaccount.getString("creditLimit"));

			addAccount.add(addaccounts);
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
	}
	
	public ArrayList<AccountPojo> megaAccountData(SearchPojo srchacc)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<AccountPojo> addAccount = new ArrayList<AccountPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("SELECT  \r\n" + 
					"fps.SUB_ACCOUNT_ID as subId \r\n" + 
					",a.code as acctCode \r\n" +
					",fps.MAXIMUM_AMOUNT as dailyLimit\r\n" + 
					",fp.NAME_PRIMARY_LANG as financeProg\r\n" + 
					"from FINANCE_PROGRAMS_SETUP fps\r\n" + 
					"left JOIN ACCOUNTS a on fps.PRIMARY_ACCOUNT_ID = a.id  \r\n" + 
					"left JOIN FINANCE_PROGRAMS fp on fps.PROGRAM_ID = fp.id\r\n" + 
					"where\r\n" + 
					"a.code ='"+srchacc.getAccountCode()+"'\r\n" + 
					"AND fps.PROGRAM_ID ='"+srchacc.getFinanceProgram()+"'\r\n");
			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			resultOfSearchaccount.next();

			AccountPojo addaccounts = new AccountPojo();
			addaccounts.setPrimaryAccountCode(resultOfSearchaccount.getString("acctCode")==null?"":resultOfSearchaccount.getString("acctCode"));
			addaccounts.setDailyLimit(resultOfSearchaccount.getString("dailyLimit")==null?"":resultOfSearchaccount.getString("dailyLimit"));
			addaccounts.setFinanceProgram(resultOfSearchaccount.getString("financeProg")==null?"":resultOfSearchaccount.getString("financeProg"));

			addAccount.add(addaccounts);
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addAccount;
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

	public ArrayList<AccountPojo> updateAccount(SearchPojo srchacc, String flag)
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
					",r.code as regioncode\r\n" + 
					",d.NAME_PRIMARY_LANG as district\r\n" + 
					",c.NAME_PRIMARY_LANG as currency\r\n" + 
					",ac.NAME_PRIMARY_LANG as accountclass\r\n" + 
					",ag.NAME_PRIMARY_LANG as accountgroup\r\n" + 
					",ot.NAME_PRIMARY_LANG as officialtype\r\n" + 
					",a.DESCRIPTION_PRIMARY_LANG as description\r\n" + 
					",a.CREDIT_LIMIT as creditlimit\r\n" + 
					",a.DAILY_LIMIT as dailylimit\r\n" + 
					",a.OFFICIAL_TYPE_NUMBER as officialtypenumber\r\n" + 
					"from POS_SOF.ACCOUNTS a\r\n" + 
					"left JOIN POS_SOF.terminal_status_lookup ts on a.TERMINAL_STATUS_ID = ts.id \r\n" + 
					"left JOIN POS_SOF.usage_lookup u on a.USAGE_ID = u.ID\r\n" + 
					"left JOIN POS_SOF.regions r on a.REGION_ID = r.ID\r\n" + 
					"left JOIN POS_SOF.districts d on r.DSTR_ID = d.ID\r\n" + 
					"left JOIN POS_SOF.currency c on a.CURRENCY_ID = c.ID\r\n" + 
					"left JOIN POS_SOF.ACCOUNT_CLASS_LOOKUP ac on a.ACCOUNT_CLASS_ID = ac.id\r\n" + 
					"left JOIN POS_SOF.account_group_lookup ag on a.GROUP_ID = ag.id\r\n" + 
					"left JOIN POS_SOF.official_types_lookup ot on a.OFFICIAL_TYPE_ID = ot.id \r\n" + 
					"where a.code is not null \r\n" + 
					" AND  a.PRIMARY_ACCT_ID is null \r\n" + 
					"AND  a.code = '"+srchacc.getAccountCode()+"'");


			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());



			while(resultOfSearchaccount.next())
			{
				AccountPojo addaccounts = new AccountPojo();
				addaccounts.setAccountStatus(resultOfSearchaccount.getString("accountstatus")==null?"":resultOfSearchaccount.getString("accountstatus"));
				addaccounts.setUsage(resultOfSearchaccount.getString("USAGE")==null?"":resultOfSearchaccount.getString("USAGE"));
				addaccounts.setDescription(resultOfSearchaccount.getString("description")==null?"":resultOfSearchaccount.getString("description"));

				ArrayList<RegionPojo> regions = new ArrayList<RegionPojo>();
				RegionPojo reg = new RegionPojo();

				reg.setCode(resultOfSearchaccount.getString("regioncode")==null?"":resultOfSearchaccount.getString("regioncode"));
				reg.setName(resultOfSearchaccount.getString("regionname")==null?"":resultOfSearchaccount.getString("REGIONNAME"));
				reg.setDistrict(resultOfSearchaccount.getString("district")==null?"":resultOfSearchaccount.getString("district"));

				regions.add(reg);
				addaccounts.setRegions(regions);

				addaccounts.setCurrency(resultOfSearchaccount.getString("CURRENCY")==null?"":resultOfSearchaccount.getString("CURRENCY"));
				addaccounts.setAccountClass(resultOfSearchaccount.getString("ACCOUNTCLASS")==null?"":resultOfSearchaccount.getString("ACCOUNTCLASS"));
				addaccounts.setAccountGroup(resultOfSearchaccount.getString("ACCOUNTGROUP")==null?"":resultOfSearchaccount.getString("ACCOUNTGROUP"));
				addaccounts.setOfficialType(resultOfSearchaccount.getString("OFFICIALTYPE")==null?"":resultOfSearchaccount.getString("OFFICIALTYPE"));
				addaccounts.setCreditLimit(resultOfSearchaccount.getString("CREDITLIMIT")==null?"":resultOfSearchaccount.getString("CREDITLIMIT"));
				addaccounts.setDailyLimit(resultOfSearchaccount.getString("DAILYLIMIT")==null?"":resultOfSearchaccount.getString("DAILYLIMIT"));
				addaccounts.setOfficialnumber(resultOfSearchaccount.getString("officialtypenumber")==null?"":resultOfSearchaccount.getString("officialtypenumber"));


				addAccount.add(addaccounts);
			}
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


