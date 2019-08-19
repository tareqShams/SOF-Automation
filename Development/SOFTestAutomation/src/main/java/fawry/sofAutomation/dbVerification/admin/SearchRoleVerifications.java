package fawry.sofAutomation.dbVerification.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import fawry.sofAutomation.pojos.admin.RolePojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class SearchRoleVerifications {

	private static Logger log = Logger.getLogger(SearchRoleVerifications.class.getName());



	public ArrayList<RolePojo> addRole(RolePojo role_pojo ) throws SQLException 
	{

		DOMConfigurator.configure("log4j.xml");

		ArrayList<RolePojo> roles = new ArrayList<RolePojo>();

		try{	
			// open connection 
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			//append basic query for add-edit group functionality
			queryCond.append("SELECT r.code as code, r.NAME_PRIMARY_LANG as name , r.DESCRIPTION_PRIMARY_LANG as description ,"
					+ " r.SERVICE_LVL as \"type\" , c.CODE as csp ,g.NAME_PRIMARY_LANG as \"group\",p.NAME_PRIMARY_LANG as \"permission\" ,us.CODE as status"
					+ " FROM POS_SOF.ROLE r "
					+ " left JOIN POS_SOF.CSP c on c.id=r.CSP_ID "
					+ " left join POS_SOF.GROUP_ROLES gr on gr.ROLE_ID=r.id "
					+ " LEFT JOIN POS_SOF.GROUPS g on g.id=gr.GROUP_ID "
					+ " left join POS_SOF.ROLE_PERMISSIONS rp on rp.ROLE_ID=r.id "
					+ " left join POS_SOF.STATUS_LOOKUP us on us.ID=r.STATUS_ID"
					+ " LEFT JOIN POS_SOF.PERMISSION p on p.id=rp.PERMISSION_ID"
					+ " where r.STATUS_ID <>3 and r.CODE = '"+role_pojo.getRoleCode()+"'  ");


			//execute query in database 
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			ArrayList<String> arr=new ArrayList<>();
			ArrayList<String> arr1=new ArrayList<>();
			//loop on returned results from dataBase and set it into Pojos
			RolePojo role = new RolePojo();
			resultOfSearchaccount.next();
			role.setRoleCode(resultOfSearchaccount.getString("code")==null?"":resultOfSearchaccount.getString("code"));
			role.setRoleName(resultOfSearchaccount.getString("name")==null?"":resultOfSearchaccount.getString("name"));
			role.setDescription(resultOfSearchaccount.getString("description")==null?"":resultOfSearchaccount.getString("description"));
			role.setRoleType(resultOfSearchaccount.getString("type")==null?"":resultOfSearchaccount.getString("type"));
			role.setCsp(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
			role.setStatus(resultOfSearchaccount.getString("status")==null?"":resultOfSearchaccount.getString("status"));
			arr.add(resultOfSearchaccount.getString("group")==null?"":resultOfSearchaccount.getString("group"));
			arr1.add(resultOfSearchaccount.getString("permission")==null?"":resultOfSearchaccount.getString("permission"));

			while(resultOfSearchaccount.next())
			{		
				if(resultOfSearchaccount.getString("group")!=null)
				{
					arr.add(resultOfSearchaccount.getString("group"));
				}

				if(resultOfSearchaccount.getString("permission")!=null)
				{
					arr1.add(resultOfSearchaccount.getString("permission"));
				}
			}
			role.setGroupsData(arr);
			role.setPermissionsData(arr1);
			roles.add(role);



			// return array of pojos that contains results of database Query
		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	
		}

		catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}

		return roles;
	}


	public ArrayList<RolePojo> searchRole(RolePojo role_pojo   ) throws SQLException 
	{
		ArrayList<RolePojo> roles = new ArrayList<RolePojo>();

		try{	
			// open connection 
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			//append basic query for search Role functionality 

			queryCond.append("SELECT DISTINCT r.code as code"
					+ " FROM POS_SOF.ROLE r "
					+ " left JOIN POS_SOF.CSP c on c.id=r.CSP_ID "
					+ " left join POS_SOF.GROUP_ROLES gr on gr.ROLE_ID=r.id "
					+ " LEFT JOIN POS_SOF.GROUPS g on g.id=gr.GROUP_ID "
					+ " left join POS_SOF.ROLE_PERMISSIONS rp on rp.ROLE_ID=r.id "
					+ " left join POS_SOF.STATUS_LOOKUP us on us.ID=r.STATUS_ID"
					+ " LEFT JOIN POS_SOF.PERMISSION p on p.id=rp.PERMISSION_ID"
					+ " where r.STATUS_ID <>3 ");

			ArrayList<String> myQuary= new ArrayList<String>(); 
			//add WHERE conditions to ArrayList

			if(!role_pojo.getRoleCode().isEmpty())
			{
				myQuary.add("   r.CODE = '"+role_pojo.getRoleCode()+"' ");
			}
			else
			{
				if(!role_pojo.getCsp().isEmpty())	
				{
					myQuary.add(" C.CODE = '"+role_pojo.getCsp()+"'");
				}
				if(!role_pojo.getStatus().isEmpty())
				{
					myQuary.add(" US.CODE = '"+role_pojo.getStatus()+"'");
				}
				if(!role_pojo.getRoleType().isEmpty())
				{
					myQuary.add("r.SERVICE_LVL = '"+role_pojo.getRoleType()+"'");
				}
			}


			// call method that add and between conditions 
			String conditions=add_and(myQuary);
			// append condition statement to search query
			queryCond.append(conditions);
			//execute query in database 
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());

			//loop on returned results from dataBase and set it into Pojos
			RolePojo role;
			while(resultOfSearchaccount.next())
			{	
				role = new RolePojo();	
				role.setRoleCode(resultOfSearchaccount.getString("code")==null?"":resultOfSearchaccount.getString("code"));
				roles.add(role);

			}

			// return array of pojos that contains results of database Query
		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	
		}

		catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}

		return roles;
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

	String timestamp = new SimpleDateFormat("Mdyyyyssmmm").format(Calendar.getInstance().getTime());


	public void updaterole(String code) throws SQLException
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that update code by add timestamp to it 
		queryCond.append("UPDATE  POS_SOF.ROLE r SET r.CODE = '"+code+timestamp+"' where r.CODE='"+code+"'");
		//execute query 
		conn.executeVerificationQuery1(myconection, queryCond.toString());


	}
}
