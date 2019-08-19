package fawry.sofAutomation.dbVerification.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import fawry.sofAutomation.pojos.admin.SearchUserPojo;
import fawry.sofAutomation.pojos.admin.UserPojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class SearchUserVerifications {
	private static Logger log = Logger.getLogger(SearchUserVerifications.class.getName());

	public UserPojo SearchUserVerificationsForAdd(SearchUserPojo userpojo   )
	{		 
		DOMConfigurator.configure("log4j.xml");
		UserPojo  user = new UserPojo();
		ArrayList<String> arr=new ArrayList<>();
		try {
		//open database connection
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();

		//append basic query for add-edit user functionality
		queryCond.append("SELECT  u.USER_NAME as username, u.FIRST_NAME AS FIRSTNAME ,u.FAMILY_NAME as lastname, d.NAME_PRIMARY_LANG as department,u.JOB_TITLE as title,"
				+ "u.GRADE as grade,u.TELEPHONE_EXT AS TelephoneExt,u.MOBILE_NUM as mobileNUM, u.EMAIL as email,US.CODE AS Status ,g.NAME_PRIMARY_LANG as GroupName,UT.NAME_PRIMARY_LANG as UserType,c.NAME_PRIMARY_LANG as CSP_NAME"
				+ ",to_char(u.USER_EXPIRATION_DATE , 'DY, DD MON YYYY') as \"date\""
				+", u.ENFORCE_CHANGE_PASSWORD as enforce"
				+ " FROM \"POS_SOF\".\"USERS\" u "
				+ "LEFT OUTER JOIN POS_SOF.CSP_USERS cu On cu.USER_ID = u.ID "
				+ "left join \"POS_SOF\".\"CSP\" c on cu.\"CSP_ID\" = c.\"ID\""
				+ "left join \"POS_SOF\".\"DEPARTMENT\" d  on u.\"DEPARTMENT_ID\" = d.\"ID\""
				+ "left join \"POS_SOF\".\"GROUP_USERS\"  gu  on gu.\"USER_ID\" = u.\"GROUP_ID\""
				+ "left join \"POS_SOF\".\"GROUPS\"  g  on gu.\"GROUP_ID\" = g.\"ID\" "
				+ "left join \"POS_SOF\".\"USER_TYPES_LOOKUPS\"  ut  on u.\"USER_TYPE_ID\" = ut.\"ID\""
				+ "left join \"POS_SOF\".\"USER_STATUS_LOOKUP\"  us  on u.\"STATUS_ID\" = us.\"ID\""
				+ "where  u.USER_NAME = '"+userpojo.getUserName()+"' ");

		System.out.println(queryCond.toString());
		//execute query in database 
		ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
		//Get returned results from dataBase and set it into UserPojo			
		resultOfSearchaccount.next();
		user.setFirstName(resultOfSearchaccount.getString("firstname")==null?"":resultOfSearchaccount.getString("firstname"));
		user.setLastName(resultOfSearchaccount.getString("lastname")==null?"":resultOfSearchaccount.getString("lastname"));
		user.setUserName(resultOfSearchaccount.getString("username")==null?"":resultOfSearchaccount.getString("username"));
		user.setEmail(resultOfSearchaccount.getString("email")==null?"":resultOfSearchaccount.getString("email"));
		user.setJobTitle(resultOfSearchaccount.getString("title")==null?"":resultOfSearchaccount.getString("title"));
		user.setGrade(resultOfSearchaccount.getString("grade")==null?"":resultOfSearchaccount.getString("grade"));
		user.setTelephoneExt(resultOfSearchaccount.getString("TelephoneExt")==null?"":resultOfSearchaccount.getString("TelephoneExt"));
		user.setMobileNum(resultOfSearchaccount.getString("mobileNUM")==null?"":resultOfSearchaccount.getString("mobileNUM"));
		user.setDepartment(resultOfSearchaccount.getString("department")==null?"":resultOfSearchaccount.getString("department"));
		user.setUserExpirationDate(resultOfSearchaccount.getString("date")==null?"":resultOfSearchaccount.getString("date"));
		user.setUserType(resultOfSearchaccount.getString("UserType")==null?"":resultOfSearchaccount.getString("UserType"));
		user.setUserGroup(resultOfSearchaccount.getString("GroupName")==null?"":resultOfSearchaccount.getString("GroupName"));
		user.setStutas(resultOfSearchaccount.getString("Status")==null?"":resultOfSearchaccount.getString("Status"));
		user.SetEnforce(resultOfSearchaccount.getString("enforce")==null?"":resultOfSearchaccount.getString("enforce"));

		arr.add((resultOfSearchaccount.getString("CSP_NAME")==null?"":resultOfSearchaccount.getString("CSP_NAME")));

		
		while(resultOfSearchaccount.next())
		{
			arr.add((resultOfSearchaccount.getString("CSP_NAME")==null?"":resultOfSearchaccount.getString("CSP_NAME")));
		}
		user.setCsp(arr);

		}
		catch (SQLException e) 
		{
			log.error(e.getClass().getSimpleName());	

		}
		catch (Exception e) 
		{
			log.error(e.getClass().getSimpleName());	

		}
		return user;

	}

	

	public ArrayList<UserPojo> SearchUserVerificationsForSearch (SearchUserPojo userpojo )
	{
		ArrayList<UserPojo> users = new ArrayList<UserPojo>();

		try {
			//open database connection

			DatabaseConnection conn= new DatabaseConnection();
			Connection myconection=conn.openConnection();
			StringBuilder queryCond = new StringBuilder();

			//append basic query for search group functionality Using (Group Or Csp) in Search Criteria
				if(!userpojo.getGroups().isEmpty() || !userpojo.getCsp().isEmpty())
				{

					queryCond.append("SELECT  u.USER_NAME as username "
							+ "FROM \"POS_SOF\".\"USERS\" u");

				}
				//append basic query for search group functionality For the Other Search Criteria
				else
				{
					queryCond.append("SELECT u.USER_NAME as username"
							+ " FROM \"POS_SOF\".\"USERS\" u "
							+ "left join \"POS_SOF\".\"DEPARTMENT\" d  on u.\"DEPARTMENT_ID\" = d.\"ID\""
							+ "left join \"POS_SOF\".\"USER_STATUS_LOOKUP\"  us  on u.\"STATUS_ID\" = us.\"ID\""
							+ "left join \"POS_SOF\".\"USER_TYPES_LOOKUPS\"  ut  on u.\"USER_TYPE_ID\" = ut.\"ID\""
							+ "WHERE  u.STATUS_ID NOT IN ('5', '3')"
							+ " AND ");
				}

				ArrayList<String> myQuary= new ArrayList<String>(); 

				//add WHERE conditions to ArrayList

				if(!userpojo.getUserName().isEmpty())
				{
					queryCond.append("UPPER( u.USER_NAME) LIKE UPPER('%"+userpojo.getUserName()+"%')");


				}
				else{
					if (!userpojo.getFirstName().isEmpty())
					{
						myQuary.add("UPPER( u.FIRST_NAME )LIKE UPPER('%"+userpojo.getFirstName()+"%')");

					} 
					if(!userpojo.getLastName().isEmpty())
					{

						myQuary.add("UPPER(U.FAMILY_NAME )LIKE UPPER('%"+userpojo.getLastName()+"%')");

					}
					if(!userpojo.getEmail().isEmpty())
					{

						myQuary.add("UPPER(U.EMAIL )LIKE UPPER('%"+userpojo.getEmail()+"%')");

					}
					if(!userpojo.getJobTitle().isEmpty())
					{

						myQuary.add("UPPER(U.JOB_TITLE )LIKE UPPER('%"+userpojo.getJobTitle()+"%')");

					}	
					if(!userpojo.getTelephoneExt().isEmpty())
					{

						myQuary.add("UPPER(U.TELEPHONE_EXT) LIKE UPPER('%"+userpojo.getTelephoneExt()+"%')");

					}
					if(!userpojo.getStatus().isEmpty())
					{

						myQuary.add(" US.CODE = '"+userpojo.getStatus()+"'");

					}
					if(!userpojo.getCsp().isEmpty())
					{
						queryCond.append(" WHERE ID IN (SELECT USER_ID FROM \"POS_SOF\".\"CSP_USERS\" WHERE CSP_ID =(SELECT ID FROM \"POS_SOF\".\"CSP\" WHERE ");
						myQuary.add("CODE = '"+userpojo.getCsp()+"'))"+" AND   u.STATUS_ID NOT IN ('5', '3')");

					}
					if(!userpojo.getDepartment().isEmpty())
					{
						myQuary.add("UPPER(D.NAME_PRIMARY_LANG ) LIKE UPPER('"+userpojo.getDepartment()+"')");

					}
					if(!userpojo.getGroups().isEmpty())
					{
						queryCond.append(" left join \"POS_SOF\".\"GROUP_USERS\"  gu  on gu.\"USER_ID\" = u.\"ID\""
								+ "left join \"POS_SOF\".\"GROUPS\"  g  on gu.\"GROUP_ID\" = g.\"ID\" where  ");
						myQuary.add("g.CODE = '"+userpojo.getGroups()+"' AND   u.STATUS_ID NOT IN ('5', '3')");

					}
					if(!userpojo.getGrade().isEmpty())
					{

						myQuary.add("UPPER(u.GRADE) LIKE UPPER('%"+userpojo.getGrade()+"%')");

					}		

					if(!userpojo.getMobileNumber().isEmpty())
					{

						myQuary.add("u.MOBILE_NUM LIKE UPPER('%"+userpojo.getMobileNumber()+"')");

					}
				}
				// call method that add and between conditions then  append condition statement to search query
				queryCond.append(add_and(myQuary));
			
			System.out.println(queryCond.toString());

			//execute query in database 
			ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());
			UserPojo user ;
			
			//loop on returned results from dataBase and set it into Pojos
				while(resultOfSearchaccount.next())
				{
					user = new UserPojo();
					user.setUserName(resultOfSearchaccount.getString("username")==null?"":resultOfSearchaccount.getString("username"));
					users.add(user);

				}

		} catch (SQLException e) {
			log.error(e.getClass().getSimpleName());	
		}

		catch (Exception e) {
			log.error(e.getClass().getSimpleName());	

		}
		return users;
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

	String timestamp = new SimpleDateFormat("Mdyyyyssmmm").format(Calendar.getInstance().getTime());
	public void updateuser(String username) 
	{
		// open connection 
		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		// append query that update username by add timestamp to it 
		queryCond.append("UPDATE  POS_SOF.USERS u SET u.USER_NAME = '"+username+timestamp+"' where u.USER_NAME='"+username+"'");
		//execute query 
		conn.executeVerificationQuery1(myconection, queryCond.toString());


	}

	}

