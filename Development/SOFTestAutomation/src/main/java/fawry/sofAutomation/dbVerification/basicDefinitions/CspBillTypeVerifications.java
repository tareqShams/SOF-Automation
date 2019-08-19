package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fawry.sofAutomation.pojos.basicDefinitions.CspBillTypePojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class CspBillTypeVerifications {
	

	public CspBillTypePojo addCspBillType(CspBillTypePojo addCspBillTypeObject ) 
	{
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();

		try {
			queryCond.append("select cb.id as id,C.CODE AS CSP,B.NAME_PRIMARY_LANG AS BTC,CB.AMOUNT AS AMOUNT "
					+ " ,CB.LOYALTY_NUM_OF_MONTHS  AS LOYALTY_NUM_OF_MONTHS  "
					+ "from POS_SOF.CSP_BTC cb left join POS_SOF.CSP c on cb.CSP_ID= c.ID "
					+ "left join POS_SOF.BTC b on b.ID=cb.BTC_ID"
					+ " WHERE c.CODE='"+addCspBillTypeObject.getCustomerServiceProvider()+"' AND B.NAME_PRIMARY_LANG='"+addCspBillTypeObject.getBillTypeCode()+"'");

			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			if(resultOfSearchaccount.next())
			{
			addCspBillTypeObject.setCustomerServiceProvider(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));
			addCspBillTypeObject.setBillTypeCode(resultOfSearchaccount.getString("BTC")==null?"":resultOfSearchaccount.getString("BTC"));
			addCspBillTypeObject.setAmount(resultOfSearchaccount.getString("AMOUNT")==null?"":resultOfSearchaccount.getString("AMOUNT"));
			addCspBillTypeObject.setLoyaltyNumOfMonths(resultOfSearchaccount.getString("LOYALTY_NUM_OF_MONTHS")==null?"":resultOfSearchaccount.getString("LOYALTY_NUM_OF_MONTHS"));
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addCspBillTypeObject;
	}

	/*public void delete()
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that delete row using id
		System.out.println(id);
		queryCond.append("DELETE FROM POS_SOF.CSP_BTC where id="+id);
		//execute query 
		conn.executeVerificationQuery1(myconection, queryCond.toString());

	}*/
}