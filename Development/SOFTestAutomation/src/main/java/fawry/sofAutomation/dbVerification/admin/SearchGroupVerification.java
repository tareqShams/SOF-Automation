package fawry.sofAutomation.dbVerification.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import fawry.sofAutomation.pojos.admin.GroupPojo;
import fawry.sofAutomation.pojos.admin.SearchGroupPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class SearchGroupVerification {
	private static Logger log = Logger.getLogger(SearchGroupVerification.class.getName());

	public GroupPojo addgroup(SearchGroupPojo group_pojo ) throws SQLException
	{		
		DOMConfigurator.configure("log4j.xml");
		GroupPojo group = new GroupPojo() ;
		try { 
			//open database connection
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

				//append basic query for add-edit group functionality
				queryCond.append("SELECT us.CODE as status, g.CODE as code, g.NAME_PRIMARY_LANG as name,"
						+ " g.DESCRIPTION_PRIMARY_LANG as description,g.SERVICE_LVL as type,"
						+ " c.CODE as csp, R.NAME_PRIMARY_LANG as role ,u.USER_NAME as username"
						+ " from POS_SOF.GROUPS g "
						+ "LEFT OUTER JOIN POS_SOF.GROUP_USERS gu On gu.GROUP_ID = g.ID "
						+ "left join \"POS_SOF\".\"USERS\" u on Gu.\"USER_ID\" = u.\"ID\""
						+ " left join \"POS_SOF\".\"USER_STATUS_LOOKUP\"  us  on g.\"STATUS_ID\" = us.\"ID\""
						+ "left join \"POS_SOF\".\"CSP\" c on g.\"CSP_ID\" = c.\"ID\" "
						+ "left join POS_SOF.GROUP_ROLES gr on gr.GROUP_ID=g.ID "
						+ "left join POS_SOF.ROLE r on r.ID=gr.ROLE_ID "
						+ "where G.STATUS_ID <>3 and g.CODE = '"+group_pojo.GetGroupCode()+"' ");
			System.out.println(queryCond.toString());
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			
			//loop on returned results from dataBase and set it into Pojos
			ArrayList<String> arr=new ArrayList<>();
		
				resultOfSearchaccount.next();
				group.setGroupStatus(resultOfSearchaccount.getString("status")==null?"":resultOfSearchaccount.getString("status"));
				group.setGroupCode(resultOfSearchaccount.getString("code")==null?"":resultOfSearchaccount.getString("code"));
				group.setGroupName(resultOfSearchaccount.getString("name")==null?"":resultOfSearchaccount.getString("name"));
				group.setDescription(resultOfSearchaccount.getString("description")==null?"":resultOfSearchaccount.getString("description"));
				group.setGroupType(resultOfSearchaccount.getString("type")==null?"":resultOfSearchaccount.getString("type"));
				group.setCSP(resultOfSearchaccount.getString("csp")==null?"":resultOfSearchaccount.getString("csp"));
				group.setGroupRole(resultOfSearchaccount.getString("role")==null?"":resultOfSearchaccount.getString("role"));
				arr.add((resultOfSearchaccount.getString("username")==null?"":resultOfSearchaccount.getString("username")));

				while(resultOfSearchaccount.next())
				{					
					group = new GroupPojo();
					arr.add((resultOfSearchaccount.getString("username")==null?"":resultOfSearchaccount.getString("username")));

				}
				group.setUsersData(arr);
			

			
		
	} catch (SQLException e) {
		log.error(e.getClass().getSimpleName());	
	}

	catch (Exception e) {
		log.error(e.getClass().getSimpleName());	

	}
		// return array of pojos that contains results of database Query
		return group;



	}
	public ArrayList<GroupPojo> searchgroup(SearchGroupPojo group_pojo  ) throws SQLException
	{		
		DOMConfigurator.configure("log4j.xml");


		//
		ArrayList<GroupPojo> groups = new ArrayList<GroupPojo>();
		try { 
			//open database connection
			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();

			StringBuilder queryCond = new StringBuilder();

				//append basic query for search group functionality 
				queryCond.append("SELECT  g.CODE code from POS_SOF.GROUPS g "
						+ "left join \"POS_SOF\".\"USER_STATUS_LOOKUP\"  us  on g.\"STATUS_ID\" = us.\"ID\" "
						+ "left join \"POS_SOF\".\"CSP\" c on g.CSP_ID = c.\"ID\" "
						+ " WHERE G.STATUS_ID <>3  ");
			

			ArrayList<String> myQuary= new ArrayList<String>(); 
			//add WHERE conditions to ArrayList
			if(!group_pojo.GetGroupCode().isEmpty())
			{
				myQuary.add("  g.CODE = '"+group_pojo.GetGroupCode()+"' ");
			}
			else{
				if(!group_pojo.GetCSP().isEmpty())	
				{
					myQuary.add(" C.CODE = '"+group_pojo.GetCSP()+"'");
				}
				if(!group_pojo.GetStatus().isEmpty())
				{
					myQuary.add(" US.CODE = '"+group_pojo.GetStatus()+"'");
				}
				if(!group_pojo.GetGroupType().isEmpty())
				{
					myQuary.add("g.SERVICE_LVL = '"+group_pojo.GetGroupType()+"'");
				}

			}


			// call method that add and between conditions 
			String conditions=add_and(myQuary);
			// append condition statement to search query
			queryCond.append(conditions);
			//execute query in database 
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			System.out.println(queryCond.toString());
			GroupPojo group ;
			//loop on returned results from dataBase and set it into Pojos
			
				while(resultOfSearchaccount.next())
				{
					group = new GroupPojo();
					group.setGroupCode(resultOfSearchaccount.getString("code")==null?"":resultOfSearchaccount.getString("code"));
					groups.add(group);

				}

			


		
	} catch (SQLException e) {
		log.error(e.getClass().getSimpleName());	
	}

	catch (Exception e) {
		log.error(e.getClass().getSimpleName());	

	}
		// return array of pojos that contains results of database Query
		return groups;



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


	public void updategroup(String code) throws SQLException
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that update code by add timestamp to it 
		queryCond.append("UPDATE POS_SOF.GROUPS g SET g.CODE = '"+code+timestamp+"'WHERE g.CODE ='"+code+"'");
		//execute query 
		conn.executeVerificationQuery1(myconection, queryCond.toString());


	}
}
