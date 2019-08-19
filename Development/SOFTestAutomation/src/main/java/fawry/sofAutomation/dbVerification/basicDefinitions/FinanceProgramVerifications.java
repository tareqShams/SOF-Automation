package fawry.sofAutomation.dbVerification.basicDefinitions;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.FinanceProgramPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class FinanceProgramVerifications {

	public FinanceProgramPojo FinanceProgramVerify(FinanceProgramPojo FinanceProgramObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		FinanceProgramPojo  resultsInDB= null;

		try {
			queryCond.append("select fpt.NAME_PRIMARY_LANG as PROGRAM_TYPE ,"
					+ "fp.CODE as CODE ,fp.NAME_PRIMARY_LANG as NAME ,"
					+ " fp.DESCRIPTION_PRIMARY_LANG as DESCRIPTION ,"
					+ "fp.MAXIMUM_FACILITY_LIMIT as MAXIMUM_FACILITY_LIMIT ,"
					+ " fp.VALIDATE_SETUP_TIME as VALIDATE_SETUP_TIME ,"
					+ " fp.DISABLE_ACCOUNT_ROUTING as  DISABLE_ACCOUNT_ROUTING ,"
					+ "fp.IS_MAIN_ACCOUNT_RETURN as MAIN_ACCOUNT_RETURN ,"
					+ " fp.ENABLE_ESTATMENT as ENABLE_ESTATMENT,"
					+ "fp.ESTATMENT_NUMBER_OF_DAYS as NUMBER_OF_DAYS , "
					+ "fp.ESTATMENT_DUE_DATE_PER_VISITS as DUE_DATE_PER_VISITS ,"
					+ " fp.ESTATMENT_SETUP_BLOCK_IN_DEBIT as SETUP_BLOCK_IN_DEBIT  ,"
					+ "fp.ESTATMENT_SETUP_ACTIV_IN_NOTFY as SETUP_ACTIV_IN_NOTFY  "
					+ "from POS_SOF.FINANCE_PROGRAMS fp "
					+ " LEFT JOIN POS_SOF.FINANCE_PROGRAM_TYPES fpt on fpt.ID=fp.PROGRAM_TYPE_ID "
					+ "WHERE fp.CODE='"+FinanceProgramObj.getCode()+"'");


			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			if(resultOfSearchaccount.next())
			{
				resultsInDB=new FinanceProgramPojo();
				resultsInDB.setProgramType(resultOfSearchaccount.getString("PROGRAM_TYPE")==null?"":resultOfSearchaccount.getString("PROGRAM_TYPE"));
				resultsInDB.setCode(resultOfSearchaccount.getString("CODE")==null?"":resultOfSearchaccount.getString("CODE"));
				resultsInDB.setName(resultOfSearchaccount.getString("NAME")==null?"":resultOfSearchaccount.getString("NAME"));
				resultsInDB.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				resultsInDB.setMaximumFacilityLimit(resultOfSearchaccount.getString("MAXIMUM_FACILITY_LIMIT")==null?"":resultOfSearchaccount.getString("MAXIMUM_FACILITY_LIMIT"));
				resultsInDB.setValidateSetupTime((resultOfSearchaccount.getString("VALIDATE_SETUP_TIME")=="N"||resultOfSearchaccount.getString("VALIDATE_SETUP_TIME")==null)?"":resultOfSearchaccount.getString("VALIDATE_SETUP_TIME"));
				resultsInDB.setDisableAccountRouting(resultOfSearchaccount.getString("DISABLE_ACCOUNT_ROUTING")=="N"||resultOfSearchaccount.getString("DISABLE_ACCOUNT_ROUTING")==null?"":resultOfSearchaccount.getString("DISABLE_ACCOUNT_ROUTING"));
				resultsInDB.setMainAccountReturn(resultOfSearchaccount.getString("MAIN_ACCOUNT_RETURN")=="N"||resultOfSearchaccount.getString("MAIN_ACCOUNT_RETURN")==null?"":resultOfSearchaccount.getString("MAIN_ACCOUNT_RETURN"));
				resultsInDB.setEnableEStatment(resultOfSearchaccount.getString("ENABLE_ESTATMENT")=="N"||resultOfSearchaccount.getString("ENABLE_ESTATMENT")==null?"":resultOfSearchaccount.getString("ENABLE_ESTATMENT"));
				resultsInDB.setNumOfDays(resultOfSearchaccount.getString("NUMBER_OF_DAYS")=="N"||resultOfSearchaccount.getString("NUMBER_OF_DAYS")==null?"":resultOfSearchaccount.getString("NUMBER_OF_DAYS"));
				resultsInDB.setDueDatePerVisits(resultOfSearchaccount.getString("DUE_DATE_PER_VISITS")=="N"||resultOfSearchaccount.getString("DUE_DATE_PER_VISITS")==null?"":resultOfSearchaccount.getString("DUE_DATE_PER_VISITS"));
				resultsInDB.setBlockSetupInDebit(resultOfSearchaccount.getString("SETUP_BLOCK_IN_DEBIT")=="N"||resultOfSearchaccount.getString("SETUP_BLOCK_IN_DEBIT")==null?"":resultOfSearchaccount.getString("SETUP_BLOCK_IN_DEBIT"));
				resultsInDB.setReactivateSetupInCredit(resultOfSearchaccount.getString("SETUP_ACTIV_IN_NOTFY")=="N"||resultOfSearchaccount.getString("SETUP_ACTIV_IN_NOTFY")==null?"":resultOfSearchaccount.getString("SETUP_ACTIV_IN_NOTFY"));


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
	public FinanceProgramPojo FinanceProgramVerifyForEdit(FinanceProgramPojo FinanceProgramObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		FinanceProgramPojo  resultsInDB= null;

		try {
			queryCond.append("select fpt.NAME_PRIMARY_LANG as PROGRAM_TYPE "
					+ ",fp.STATUS_ID as Status,"
					+ "fp.CODE as CODE ,fp.NAME_PRIMARY_LANG as NAME ,"
					+ " fp.DESCRIPTION_PRIMARY_LANG as DESCRIPTION ,"
					+ "fp.MAXIMUM_FACILITY_LIMIT as MAXIMUM_FACILITY_LIMIT ,"
					+ " fp.VALIDATE_SETUP_TIME as VALIDATE_SETUP_TIME ,"
					+ " fp.DISABLE_ACCOUNT_ROUTING as  DISABLE_ACCOUNT_ROUTING ,"
					+ "fp.IS_MAIN_ACCOUNT_RETURN as MAIN_ACCOUNT_RETURN ,"
					+ " fp.ENABLE_ESTATMENT as ENABLE_ESTATMENT,"
					+ "fp.ESTATMENT_NUMBER_OF_DAYS as NUMBER_OF_DAYS , "
					+ "fp.ESTATMENT_DUE_DATE_PER_VISITS as DUE_DATE_PER_VISITS ,"
					+ " fp.ESTATMENT_SETUP_BLOCK_IN_DEBIT as SETUP_BLOCK_IN_DEBIT  ,"
					+ "fp.ESTATMENT_SETUP_ACTIV_IN_NOTFY as SETUP_ACTIV_IN_NOTFY  "
					+ "from POS_SOF.FINANCE_PROGRAMS fp "
					+ " LEFT JOIN POS_SOF.FINANCE_PROGRAM_TYPES fpt on fpt.ID=fp.PROGRAM_TYPE_ID "
					+ "WHERE ");
			ArrayList<String> myQuary= new ArrayList<String>(); 

			if(!FinanceProgramObj.getCode().isEmpty())
			{
				myQuary.add(" fp.CODE='"+FinanceProgramObj.getCode()+"'");
			}
			if(!FinanceProgramObj.getProgramType().isEmpty())
			{
				myQuary.add(" fpt.name_primary_lang='"+FinanceProgramObj.getProgramType()+"'");
			}
			queryCond.append(add_and(myQuary));	

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			if(resultOfSearchaccount.next())
			{
				resultsInDB=new FinanceProgramPojo();
				resultsInDB.setProgramType(resultOfSearchaccount.getString("PROGRAM_TYPE")==null?"":resultOfSearchaccount.getString("PROGRAM_TYPE"));
				resultsInDB.setCode(resultOfSearchaccount.getString("CODE")==null?"":resultOfSearchaccount.getString("CODE"));
				resultsInDB.setName(resultOfSearchaccount.getString("NAME")==null?"":resultOfSearchaccount.getString("NAME"));
				resultsInDB.setDescription(resultOfSearchaccount.getString("DESCRIPTION")==null?"":resultOfSearchaccount.getString("DESCRIPTION"));
				resultsInDB.setMaximumFacilityLimit(resultOfSearchaccount.getString("MAXIMUM_FACILITY_LIMIT")==null?"":resultOfSearchaccount.getString("MAXIMUM_FACILITY_LIMIT"));
				resultsInDB.setValidateSetupTime((resultOfSearchaccount.getString("VALIDATE_SETUP_TIME")=="N"||resultOfSearchaccount.getString("VALIDATE_SETUP_TIME")==null)?"":resultOfSearchaccount.getString("VALIDATE_SETUP_TIME"));
				resultsInDB.setDisableAccountRouting(resultOfSearchaccount.getString("DISABLE_ACCOUNT_ROUTING")=="N"||resultOfSearchaccount.getString("DISABLE_ACCOUNT_ROUTING")==null?"":resultOfSearchaccount.getString("DISABLE_ACCOUNT_ROUTING"));
				resultsInDB.setMainAccountReturn(resultOfSearchaccount.getString("MAIN_ACCOUNT_RETURN")=="N"||resultOfSearchaccount.getString("MAIN_ACCOUNT_RETURN")==null?"":resultOfSearchaccount.getString("MAIN_ACCOUNT_RETURN"));
				resultsInDB.setEnableEStatment(resultOfSearchaccount.getString("ENABLE_ESTATMENT")=="N"||resultOfSearchaccount.getString("ENABLE_ESTATMENT")==null?"":resultOfSearchaccount.getString("ENABLE_ESTATMENT"));
				resultsInDB.setNumOfDays(resultOfSearchaccount.getString("NUMBER_OF_DAYS")=="N"||resultOfSearchaccount.getString("NUMBER_OF_DAYS")==null?"":resultOfSearchaccount.getString("NUMBER_OF_DAYS"));
				resultsInDB.setDueDatePerVisits(resultOfSearchaccount.getString("DUE_DATE_PER_VISITS")=="N"||resultOfSearchaccount.getString("DUE_DATE_PER_VISITS")==null?"":resultOfSearchaccount.getString("DUE_DATE_PER_VISITS"));
				resultsInDB.setBlockSetupInDebit(resultOfSearchaccount.getString("SETUP_BLOCK_IN_DEBIT")=="N"||resultOfSearchaccount.getString("SETUP_BLOCK_IN_DEBIT")==null?"":resultOfSearchaccount.getString("SETUP_BLOCK_IN_DEBIT"));
				resultsInDB.setReactivateSetupInCredit(resultOfSearchaccount.getString("SETUP_ACTIV_IN_NOTFY")=="N"||resultOfSearchaccount.getString("SETUP_ACTIV_IN_NOTFY")==null?"":resultOfSearchaccount.getString("SETUP_ACTIV_IN_NOTFY"));

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
	
	
	public void update(FinanceProgramPojo FinanceProgramObj)
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that delete row using id
		queryCond.append("UPDATE POS_SOF.finance_programs  SET CODE = '14' WHERE CODE ='"+FinanceProgramObj.getCode()+"'");
		//execute query 
		conn.executeVerificationQuery1(myconection, queryCond.toString());

	}
}
