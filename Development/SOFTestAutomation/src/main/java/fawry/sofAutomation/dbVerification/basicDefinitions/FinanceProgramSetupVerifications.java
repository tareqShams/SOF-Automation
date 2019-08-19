package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramSetupPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class FinanceProgramSetupVerifications {
	
	public String id;
	public String btc_id;
	public FinanceProgramSetupPojo financeProgramSetupVerify(FinanceProgramSetupPojo FinanceProgramSetupObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		FinanceProgramSetupPojo  resultsInDB= null;
		ArrayList<String> arr=new ArrayList<>();
		ArrayList<String> arr1=new ArrayList<>();
		try {
			queryCond.append("select fps.PROGRAM_ID as ID , fpsb.BTC_ID as btc_id , fp.NAME_PRIMARY_LANG as financeProgram,"
					+ " fps.PRIMARY_ACCOUNT_ID as mainAccount, fps.SUB_ACCOUNT_ID as subAccount , "
					+ " fps.ACCT_CLASSIFICATION_ID as accountClassification ,fps.MAXIMUM_AMOUNT  as maximumAmount , "
					+ " fps.OPEN_DATE as openDate, fps.CLOSE_DATE as closeDate , fps.ENABLED_TIME as enableTime ,"
					+ " fps.DISABLED_TIME as disableTime, fps.NUM_OF_FAULTS_MIN_PAYMENTS as  numOfMinFaultPayments,"
					+ " fps.NUM_OF_FAULTS_FULL_PAYMENTS as numOfFullFaultPayments, fps.ROLLOVER as rollOver,"
					+ " btc.NAME_PRIMARY_LANG as billTypes, btp.NAME_PRIMARY_LANG as billTypeProfiles "
					+ " from POS_SOF.finance_programs_setup fps "
					+ " left JOIN POS_SOF.finance_programs fp on fps.PROGRAM_ID = fp.id "
					+ " left JOIN POS_SOF.overdraft_setup_status_lkp odssl on fps.STATUS_ID = odssl.id "
					+ " LEFT JOIN POS_SOF.FINANCE_PROGRAMS_SETUP_BTCS fpsb on fps.id=fpsb.FINANCE_PROGRAMS_SETUP_ID "
					+ " LEFT JOIN POS_SOF.BTC btc on btc.id= fpsb.BTC_ID "
					+ " LEFT JOIN POS_SOF.BILL_TYPE_PROFILE btp on btp.id=fpsb.BTC_PROFILE_ID "
					+ " where fp.NAME_PRIMARY_LANG='"+FinanceProgramSetupObj.getFinanceProgram()+"'");


			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			if(resultOfSearchaccount.next())
			{
				resultsInDB=new FinanceProgramSetupPojo();
				id=resultOfSearchaccount.getString("ID")==null?"":resultOfSearchaccount.getString("ID");
				btc_id=resultOfSearchaccount.getString("btc_id")==null?"":resultOfSearchaccount.getString("btc_id");

				resultsInDB.setFinanceProgram(resultOfSearchaccount.getString("financeProgram")==null?"":resultOfSearchaccount.getString("financeProgram"));
				resultsInDB.setMainAccount(resultOfSearchaccount.getString("mainAccount")==null?"":resultOfSearchaccount.getString("mainAccount"));
				resultsInDB.setSubAccount(resultOfSearchaccount.getString("subAccount")==null?"":resultOfSearchaccount.getString("subAccount"));
				resultsInDB.setAccountClassification(resultOfSearchaccount.getString("accountClassification")==null?"":resultOfSearchaccount.getString("accountClassification"));
				resultsInDB.setMaximumAmount(resultOfSearchaccount.getString("maximumAmount")==null?"":resultOfSearchaccount.getString("maximumAmount"));
				resultsInDB.setOpenDate(resultOfSearchaccount.getString("openDate")==null?"":resultOfSearchaccount.getString("openDate"));
				resultsInDB.setCloseDate(resultOfSearchaccount.getString("closeDate")==null?"":resultOfSearchaccount.getString("closeDate"));
				resultsInDB.setEnableTime(resultOfSearchaccount.getString("enableTime")==null?"":resultOfSearchaccount.getString("enableTime"));
				resultsInDB.setDisableTime(resultOfSearchaccount.getString("disableTime")==null?"":resultOfSearchaccount.getString("disableTime"));
				resultsInDB.setNumOfMinFaultPayments(resultOfSearchaccount.getString("numOfMinFaultPayments")==null?"":resultOfSearchaccount.getString("numOfMinFaultPayments"));
				resultsInDB.setNumOfFullFaultPayments(resultOfSearchaccount.getString("numOfFullFaultPayments")==null?"":resultOfSearchaccount.getString("numOfFullFaultPayments"));
				resultsInDB.setRollOver(resultOfSearchaccount.getString("rollOver")==null?"":resultOfSearchaccount.getString("rollOver"));
				arr.add(resultOfSearchaccount.getString("billTypes")==null?"":resultOfSearchaccount.getString("billTypes"));
				arr1.add(resultOfSearchaccount.getString("billTypeProfiles")==null?"":resultOfSearchaccount.getString("billTypeProfiles"));
				while(resultOfSearchaccount.next())
				{
					if(resultOfSearchaccount.getString("billTypes")!=null)
					{
						arr.add(resultOfSearchaccount.getString("billTypes"));
					}

					if(resultOfSearchaccount.getString("billTypeProfiles")!=null)
					{
						arr1.add(resultOfSearchaccount.getString("billTypeProfiles"));
					}
				}


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

		return resultsInDB;

	}
	
	public String getFinanceProgram(String flag) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		String financeProgram = null;
		try {
			if(flag.contains("btc"))
			{
			queryCond.append("select f.name_primary_lang  as FinanceProgram  , f.code as code "
					+ " from POS_SOF.FINANCE_PROGRAMS f"
					+ " LEFT JOIN POS_SOF.FINANCE_PROGRAM_TYPES ft on f.PROGRAM_TYPE_ID=FT.ID "
					+ " WHERE f.ID not in (select distinct fs.PROGRAM_ID from  POS_SOF.FINANCE_PROGRAMS_SETUP fs) "
					+ " and ft.SUB_ACCT_REQUIRED like 'N' "
					+ " and ft.SUB_ACCT_SUPPORTED like 'N' "
					+ " and ft.BTC_REQUIRED like 'N'"
					+ " and ft.ACCT_SETUP_REQUIRED='Y' "
					+ " and ft.ACCEPTS_ZERO_LIMIT = 'N' "
					+ " and ft.ACCT_NATURE_ID='1' ");
			}
			else if(flag.contains("subaccount")){
				queryCond.append("select f.name_primary_lang  as FinanceProgram , f.code as code "
						+ " from POS_SOF.FINANCE_PROGRAMS f"
						+ " LEFT JOIN POS_SOF.FINANCE_PROGRAM_TYPES ft on f.PROGRAM_TYPE_ID=FT.ID "
						+ " WHERE f.ID not in (select distinct fs.PROGRAM_ID from  POS_SOF.FINANCE_PROGRAMS_SETUP fs) "
						+ " and ft.SUB_ACCT_REQUIRED like 'Y' "
						+ " and ft.SUB_ACCT_SUPPORTED like 'Y' "
						+ " and ft.BTC_REQUIRED like 'Y' "
						+ " and ft.ACCT_SETUP_REQUIRED='Y'"
						+ " and ft.ACCEPTS_ZERO_LIMIT = 'N'"
						+ " and ft.ACCT_NATURE_ID='1'");
			}

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			resultOfSearchaccount.next();
			resultOfSearchaccount.next();
				financeProgram=resultOfSearchaccount.getString("FinanceProgram")==null?"":resultOfSearchaccount.getString("FinanceProgram")+
						" - "+resultOfSearchaccount.getString("code")==null?"":resultOfSearchaccount.getString("code");
		}catch (SQLException e)
		{

			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return financeProgram;

	}
	
	public String getAccount(String flag) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		String financeProgram = null;
		try {
			
			queryCond.append("SELECT a.CODE as SUB_ACCOUNT "
					+ " from POS_SOF.ACCOUNTS a  "
					+ " where a.PRIMARY_ACCT_ID is "+flag+" null "
					+ " AND a.id NOT IN"
					+ " (select distinct (fs.SUB_ACCOUNT_ID ) AS SUB_ACCOUNT "
					+ "  from  POS_SOF.FINANCE_PROGRAMS_SETUP fs WHERE fs.sub_account_id IS NOT NULL )"
					+ " and a.account_type_id='1' ");
			
			

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			resultOfSearchaccount.next();
				financeProgram=resultOfSearchaccount.getString("SUB_ACCOUNT")==null?"":resultOfSearchaccount.getString("SUB_ACCOUNT");
		}catch (SQLException e)
		{

			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return financeProgram;

	}
	
	
	public void update() 
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that update username by add timestamp to it 
		queryCond.append("UPDATE POS_SOF.finance_programs_setup fps	 SET fps.PROGRAM_ID = '5' WHERE fps.PROGRAM_ID = '"+id+"'");
		//execute query 
		conn.executeVerificationQuery1(myconection, queryCond.toString());
		queryCond.append("UPDATE POS_SOF.finance_programs_setup_btcs f	 SET f.btc_id = '13005' WHERE f.btc_id ='"+btc_id+"'");
		conn.executeVerificationQuery1(myconection, queryCond.toString());


	}

}