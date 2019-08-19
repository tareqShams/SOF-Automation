package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.CspBtcPoolAccountPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class CspBtcPoolAccountVerifications {


	public CspBtcPoolAccountPojo addCspBtcPoolAccount(CspBtcPoolAccountPojo cspBtcPoolAccountObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();

		try {
			queryCond.append("SELECT c.NAME_PRIMARY_LANG as csp ,b.NAME_PRIMARY_LANG as btc ,cb.DOUBLE_ENTRY_ACCOUNT_ID as PoolAccount"
					+ " FROM	POS_SOF.CSP_BTC_POOL_ACCOUNT cb "
					+ "left JOIN  POS_SOF.CSP C on cb.CSP_ID=C.id "
					+ "left JOIN  POS_SOF.BTC b on cb.BTC_ID=b.id  "
					+ " where cb.DOUBLE_ENTRY_ACCOUNT_ID= '"+cspBtcPoolAccountObj.getPoolAccount()+"'"
					+ " and  c.NAME_PRIMARY_LANG ='"+cspBtcPoolAccountObj.getCsp()+"' "
					+ " and b.NAME_PRIMARY_LANG = '"+cspBtcPoolAccountObj.getBillTypeCode()+"'");

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			if(resultOfSearchaccount.next())
			{
				cspBtcPoolAccountObj.setCsp(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
				cspBtcPoolAccountObj.setBillTypeCode(resultOfSearchaccount.getString("btc")==null?"":resultOfSearchaccount.getString("btc"));
				cspBtcPoolAccountObj.setPoolAccount(resultOfSearchaccount.getString("PoolAccount")==null?"":resultOfSearchaccount.getString("PoolAccount"));
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return cspBtcPoolAccountObj;


	}
	public CspBtcPoolAccountPojo addCspBtcPoolAccountForEdit(CspBtcPoolAccountPojo cspBtcPoolAccountObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();

		try {
			queryCond.append("SELECT c.NAME_PRIMARY_LANG as csp ,b.NAME_PRIMARY_LANG as btc ,cb.DOUBLE_ENTRY_ACCOUNT_ID as PoolAccount"
					+ " FROM	POS_SOF.CSP_BTC_POOL_ACCOUNT cb "
					+ "left JOIN  POS_SOF.CSP C on cb.CSP_ID=C.id "
					+ "left JOIN  POS_SOF.BTC b on cb.BTC_ID=b.id  "
					+ " where cb.DOUBLE_ENTRY_ACCOUNT_ID= '"+cspBtcPoolAccountObj.getPoolAccount()+"'"
					+ " and  c.NAME_PRIMARY_LANG ='"+cspBtcPoolAccountObj.getCsp()+"' "
					+ " and b.CODE = '"+cspBtcPoolAccountObj.getBillTypeCode()+"'");

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			if(resultOfSearchaccount.next())
			{
				cspBtcPoolAccountObj.setCsp(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
				cspBtcPoolAccountObj.setBillTypeCode(resultOfSearchaccount.getString("btc")==null?"":resultOfSearchaccount.getString("btc"));
				cspBtcPoolAccountObj.setPoolAccount(resultOfSearchaccount.getString("PoolAccount")==null?"":resultOfSearchaccount.getString("PoolAccount"));
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return cspBtcPoolAccountObj;


	}
	public ArrayList<CspBtcPoolAccountPojo> addCspBtcPoolAccountForSearch(CspBtcPoolAccountPojo cspBtcPoolAccountObj ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		ArrayList<CspBtcPoolAccountPojo> results = new ArrayList<>();
		try {
			queryCond.append("SELECT c.NAME_PRIMARY_LANG as csp ,b.NAME_PRIMARY_LANG as btc ,cb.DOUBLE_ENTRY_ACCOUNT_ID as PoolAccount"
					+ " FROM	POS_SOF.CSP_BTC_POOL_ACCOUNT cb "
					+ "left JOIN  POS_SOF.CSP C on cb.CSP_ID=C.id "
					+ "left JOIN  POS_SOF.BTC b on cb.BTC_ID=b.id  "
					+ " where");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			if(!cspBtcPoolAccountObj.getPoolAccount().isEmpty() || !cspBtcPoolAccountObj.getPoolAccount().isEmpty())
			{

				myQuary.add(" cb.DOUBLE_ENTRY_ACCOUNT_ID= '"+cspBtcPoolAccountObj.getPoolAccount()+"'");

			}
			if(!cspBtcPoolAccountObj.getCsp().isEmpty() || !cspBtcPoolAccountObj.getCsp().isEmpty())
			{

				myQuary.add("  c.NAME_PRIMARY_LANG = '"+cspBtcPoolAccountObj.getCsp()+"'");

			}
			if(!cspBtcPoolAccountObj.getBillTypeCode().isEmpty() || !cspBtcPoolAccountObj.getBillTypeCode().isEmpty())
			{

				myQuary.add(" b.NAME_PRIMARY_LANG = '"+cspBtcPoolAccountObj.getBillTypeCode()+"'");

			}

			queryCond.append(add_and(myQuary));
			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			CspBtcPoolAccountPojo cspBtcPoolAccount;
			while(resultOfSearchaccount.next())
			{
				cspBtcPoolAccount=new CspBtcPoolAccountPojo();
				cspBtcPoolAccountObj.setCsp(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
				cspBtcPoolAccountObj.setBillTypeCode(resultOfSearchaccount.getString("btc")==null?"":resultOfSearchaccount.getString("btc"));
				cspBtcPoolAccountObj.setPoolAccount(resultOfSearchaccount.getString("PoolAccount")==null?"":resultOfSearchaccount.getString("PoolAccount"));
				results.add(cspBtcPoolAccount);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return results;


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
