package fawry.sofAutomation.dbVerification.basicDefinitions;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.CspPushAlertPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class CspPushAlertConfigVerifications {


	public  String id;
	public CspPushAlertPojo CspPushAlertConfig(CspPushAlertPojo CspPushAlertObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		CspPushAlertPojo  CspPushAlertInDB = null;

		try {
			queryCond.append("select  pac.CSP_ID as ID, C.NAME_PRIMARY_LANG AS CSP, at.NAME_PRIMARY_LANG as account_type, pac.IS_PUSH_ALERT as IS_PUSH_ALERT "
					+ " from POS_SOF.CSP_PUSH_ALERT_CONFIG pac "
					+ " left join POS_SOF.CSP c on pac.CSP_ID=c.ID "
					+ " LEFT JOIN POS_SOF.ACCOUNT_TYPES at on at.ID=pac.ACCOUNT_TYPE_ID "
					+ " WHERE c.NAME_PRIMARY_LANG='"+CspPushAlertObj.getCsp()+"' "
					+ " and at.NAME_PRIMARY_LANG='"+CspPushAlertObj.getAccountType()+"'");


			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			if(resultOfSearchaccount.next())
			{
				CspPushAlertInDB=new CspPushAlertPojo();
				CspPushAlertInDB.setCsp(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));
				CspPushAlertInDB.setAccountType(resultOfSearchaccount.getString("account_type")==null?"":resultOfSearchaccount.getString("account_type"));
				CspPushAlertInDB.setAllowPushAlert(resultOfSearchaccount.getString("IS_PUSH_ALERT")==null?"":resultOfSearchaccount.getString("IS_PUSH_ALERT"));
				id=resultOfSearchaccount.getString("ID")==null?"":resultOfSearchaccount.getString("ID");

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
		conn.closeDBConnection(myconection);
		return CspPushAlertInDB;
	}


	public ArrayList<CspPushAlertPojo> CspPushAlertConfigForSearch(CspPushAlertPojo CspPushAlertObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		CspPushAlertPojo  CspPushAlertInDB = null;
		ArrayList<CspPushAlertPojo> results=new ArrayList<>();
		try {
			queryCond.append("select  pac.CSP_ID as ID, C.CODE AS CSP, at.CODE as account_type, pac.IS_PUSH_ALERT as IS_PUSH_ALERT "
					+ " from POS_SOF.CSP_PUSH_ALERT_CONFIG pac "
					+ " left join POS_SOF.CSP c on pac.CSP_ID=c.ID "
					+ " LEFT JOIN POS_SOF.ACCOUNT_TYPES at on at.ID=pac.ACCOUNT_TYPE_ID "
					+ " WHERE  ");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			if(!CspPushAlertObj.getCsp().isEmpty() || !CspPushAlertObj.getCsp().isEmpty())
			{

				myQuary.add("c.NAME_PRIMARY_LANG='"+CspPushAlertObj.getCsp()+"'");

			}
			if(!CspPushAlertObj.getAccountType().isEmpty() || !CspPushAlertObj.getAccountType().isEmpty())
			{

				myQuary.add(" at.NAME_PRIMARY_LANG='"+CspPushAlertObj.getAccountType()+"'");

			}

			queryCond.append(add_and(myQuary));
			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			while(resultOfSearchaccount.next())
			{
				CspPushAlertInDB=new CspPushAlertPojo();
				CspPushAlertInDB.setCsp(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));
				CspPushAlertInDB.setAccountType(resultOfSearchaccount.getString("account_type")==null?"":resultOfSearchaccount.getString("account_type"));
				CspPushAlertInDB.setAllowPushAlert(resultOfSearchaccount.getString("IS_PUSH_ALERT")==null?"":resultOfSearchaccount.getString("IS_PUSH_ALERT"));
				results.add(CspPushAlertInDB);
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
		conn.closeDBConnection(myconection);
		return results;
	}

	public void update(String id) 
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that update username by add timestamp to it 
		queryCond.append("UPDATE POS_SOF.CSP_PUSH_ALERT_CONFIG cpac SET cpac.CSP_ID = '684'  WHERE cpac.CSP_ID= '"+id+"'");
		//execute query 
		System.out.println(queryCond);
		conn.executeVerificationQuery1(myconection, queryCond.toString());


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
}