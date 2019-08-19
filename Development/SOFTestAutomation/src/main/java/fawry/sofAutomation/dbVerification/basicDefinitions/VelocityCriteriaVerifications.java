package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fawry.sofAutomation.pojos.basicDefinitions.VelocityPojo;
import fawry.sofAutomation.pojos.basicDefinitions.SearchPojo;


import fawry.sofAutomation.utils.DatabaseConnection;

public class VelocityCriteriaVerifications {

	// SENDING SEARCH POJO
	public ArrayList<VelocityPojo> velocityCriteriaVerification(SearchPojo srchacctrx, String flag)
	{
		// 	RETURN ACCOUNT POJO
		ArrayList<VelocityPojo> data = new ArrayList<VelocityPojo>();

		try {
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			queryCond.append("select \r\n" + 
					"vc.VELOCITY_PERIOD as velperiod\r\n" + 
					",iu.name_primary_lang as periodunit\r\n" + 
					",vmtl.name_primary_lang as velmeasuretype\r\n" + 
					",b.name_primary_lang as billtypename\r\n" + 
					",c.name_primary_lang as cspname\r\n" + 
					",val.name_primary_lang as velactionname\r\n" + 
					",atl.name_primary_lang as accounttype\r\n" + 
					",vc.measure_value as measurevalue\r\n" + 
					",ecl.name_primary_lang as errorcode\r\n" + 
					",sl.name_primary_lang as status\r\n" + 
					",c.id as cspid\r\n" + 
					"from POS_SOF.velocity_criteria vc\r\n" + 
					"left JOIN POS_SOF.interval_units_lookup iu on vc.VELOCITY_PERIOD_UNIT_ID = iu.id\r\n" + 
					"left JOIN POS_SOF.velocity_measure_type_lookup vmtl on vc.VELOCITY_MEASURE_TYPE_ID = vmtl.id\r\n" + 
					"left JOIN POS_SOF.btc b on vc.BILL_TYPE_ID = b.id\r\n" + 
					"left JOIN POS_SOF.csp c on vc.CSP_ID = c.id\r\n" + 
					"left JOIN POS_SOF.VELOCITY_ACTIONS_LOOKUP val on vc.VELOCITY_ACTION_ID = val.id\r\n" + 
					"left JOIN POS_SOF.account_types atl on vc.ACCOUNT_TYPE_ID = atl.id\r\n" + 
					"left JOIN POS_SOF.error_codes_lookup ecl on vc.ERROR_CODE_ID = ecl.id\r\n" + 
					"left JOIN POS_SOF.status_lookup sl on vc.STATUS_ID = sl.id\r\n" + 
					"where vc.id is not null\r\n");

			ArrayList<String> myQuary= new ArrayList<String>(); 

			System.out.println(srchacctrx.getPeriod());
			if(srchacctrx.getPeriod() != "" && srchacctrx.getPeriod() != null)
			{
				myQuary.add(" vc.VELOCITY_PERIOD = '" + srchacctrx.getPeriod() + "'\r\n");
			}
			System.out.println(srchacctrx.getPeriodUnit());
			if(srchacctrx.getPeriodUnit() != "" && srchacctrx.getPeriodUnit() != null)
			{
				myQuary.add(" iu.name_primary_lang ='" + srchacctrx.getPeriodUnit() + "'\r\n");
			}
			System.out.println(srchacctrx.getMeasureType());
			if(srchacctrx.getMeasureType() != "" && srchacctrx.getMeasureType() != null)
			{
				myQuary.add(" vmtl.name_primary_lang = '" + srchacctrx.getMeasureType() + "'\r\n");
			}
			System.out.println(srchacctrx.getBillType());
			if(srchacctrx.getBillType() != "" && srchacctrx.getBillType() != null)
			{
				myQuary.add(" b.name_primary_lang ='" + srchacctrx.getBillType() + "'\r\n");
			}
			System.out.println(srchacctrx.getCsp());
			if(srchacctrx.getCsp() != "" && srchacctrx.getCsp() != null)
			{
				myQuary.add(" c.name_primary_lang ='" + srchacctrx.getCsp() + "'\r\n");
			}
			System.out.println(srchacctrx.getVelocityAction());
			if(srchacctrx.getVelocityAction() != "" && srchacctrx.getVelocityAction() != null)
			{
				myQuary.add(" val.name_primary_lang ='" + srchacctrx.getVelocityAction() + "'\r\n");
			}
			System.out.println(srchacctrx.getAccountType());
			if(srchacctrx.getAccountType() != "" && srchacctrx.getAccountType() != null)
			{
				myQuary.add(" atl.name_primary_lang ='" + srchacctrx.getAccountType() + "'\r\n");
			}
			System.out.println(srchacctrx.getMeasureValue());
			if(srchacctrx.getMeasureValue() != "" && srchacctrx.getMeasureValue() != null)
			{
				myQuary.add(" vc.measure_value ='" + srchacctrx.getMeasureValue() + "'\r\n");
			}
			System.out.println(srchacctrx.getErrorCode());
			if(srchacctrx.getErrorCode() != "" && srchacctrx.getErrorCode() != null)
			{
				myQuary.add(" ecl.name_primary_lang ='" + srchacctrx.getErrorCode() + "'\r\n");
			}
			System.out.println(srchacctrx.getStatus());
			if(srchacctrx.getStatus() != "" && srchacctrx.getStatus() != null)
			{

				myQuary.add(" sl.name_primary_lang ='" + srchacctrx.getStatus() + "'\r\n");

			}
			if (flag.equalsIgnoreCase("Search"))
			{
				myQuary.add(" sl.name_primary_lang != 'DELETED'\r\n");
			}
			System.out.println(flag);
			if(flag.equalsIgnoreCase("Add"))
			{
				myQuary.add(" rownum = 1 \r\n" + 
						"Order by vc.ID DESC ");
			}
			String conditions=add_and(myQuary);

			queryCond.append(conditions);
			System.out.println(queryCond.toString());

			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			while(resultOfSearchaccount.next())
			{
				VelocityPojo velocity = new VelocityPojo();

				velocity.setPeriod(resultOfSearchaccount.getString("VELPERIOD")==null?"":resultOfSearchaccount.getString("VELPERIOD"));
				velocity.setPeriodUnit(resultOfSearchaccount.getString("PERIODUNIT")==null?"":resultOfSearchaccount.getString("PERIODUNIT"));
				velocity.setMeasureType(resultOfSearchaccount.getString("VELMEASURETYPE")==null?"":resultOfSearchaccount.getString("VELMEASURETYPE"));
				velocity.setBillType(resultOfSearchaccount.getString("BILLTYPENAME")==null?"":resultOfSearchaccount.getString("BILLTYPENAME"));
				velocity.setCsp(resultOfSearchaccount.getString("CSPNAME")==null?"":resultOfSearchaccount.getString("CSPNAME"));
				velocity.setVelocityAction(resultOfSearchaccount.getString("VELACTIONNAME")==null?"":resultOfSearchaccount.getString("VELACTIONNAME"));
				velocity.setMeasureValue(resultOfSearchaccount.getString("MEASUREVALUE")==null?"":resultOfSearchaccount.getString("MEASUREVALUE"));
				velocity.setErrorCode(resultOfSearchaccount.getString("ERRORCODE")==null?"":resultOfSearchaccount.getString("ERRORCODE"));
				// In Case of Searching Velocity Criteria the Web table shows CSP_ID not it's name
				if(flag.equals("Search"))
				{
					velocity.setCsp(resultOfSearchaccount.getString("cspid")==null?"":resultOfSearchaccount.getString("cspid"));
				}
				System.out.println(velocity.getCsp());
				data.add(velocity);
			}
			conn.closeDBConnection(myconection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
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


