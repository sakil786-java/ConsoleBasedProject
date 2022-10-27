package parent;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DbConnection {
	static Logger log = LogManager.getLogger(DbConnection.class.getName());
	static Connection con=null;
	public static Connection connection() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/consolebasedshopping","root","root");
			return con;
	} catch (Exception e) {
		log.info("");
	}
		return con;
				
	}
	void createAccount(String name,String mail,String address,String phno,String pass,String confirmPassword,String role) 
	{
		if(pass.length()>=8 && phno.length()==10 && pass.equals(confirmPassword))
		{Statement s=null;
			try {
				String sql="Insert into customer(Name,Email,Address,Phone_No,Password,Confirm_Password,role) values('"+name+"','"+mail+"','"+address+"','"+phno+"','"+pass+"','"+confirmPassword+"','"+role+"')";
				s= con.createStatement();
				int conf=s.executeUpdate(sql);
				
				if(conf==1)
				{
					log.info("Account Created Successfully");
					log.info("____________________________");
				}	
				else
					log.info("Something went wrong try again after some time");
			} catch (Exception e) {
				log.info("");
			}finally {
		     	   try {
		         	   if (s!=null) {
		         		   s.close();    
		         		    }}
		         	   catch (Exception e) {
		                	log.info("");   
		                	  }
			}
			
		}
		else
			log.info("Bad Credentials!!!! Password Should be greater than equalto 8 and mobile number Should be equal to 10");
	}
	String checkUser(String user,String pass) 
	{
		Statement s=null;
		ResultSet conf=null;
		boolean flag=false;
		String role="";
		try{
			String sql="Select * from customer where email='"+user+"' And Password='"+pass+"'";
			s= con.createStatement();
			conf=s.executeQuery(sql);
			
			while(conf.next())
			{ flag=true;
				role=conf.getString(7);//here i change 7 to 6
			}
			if(!flag)
			{
				log.info("\n\n\tWrong Credentials\n\tPlease enter correct Email and passWord\n");
			}
			return role;
		}catch (Exception e) {
			log.info("");
		}finally {
	     	   try {
	         	   if (s!=null) {
	         		   s.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info("");   
	                	  }
	     	  try {
	         	   if (conf!=null) {
	         		   conf.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info("");   
	                	  }
	     	   
		}
		return role;
		
		
	}
	
	
	
	
	
	
	
	String checkUser(String secretKey) 
	{
		Statement s=null;
		ResultSet conf=null;
		String role="";
		try{
			String sql="Select * from Admin_details where Ad_Security_Key='"+secretKey+"'";
			s=con.createStatement();
			conf=s.executeQuery(sql);
			
			while(conf.next())
			{
				role=conf.getString(2);
			}
			return role;
		}catch (Exception e) {
			log.info("");
		}finally {
	     	   try {
	         	   if (s!=null) {
	         		   s.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info("");   
	                	  }
	     	  try {
	         	   if (conf!=null) {
	         		   conf.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info("");   
	                	  }
	     	   
		}
		return role;
		
	}
	void view(){
		Statement s=null;
		ResultSet conf=null;
		try{
			String sql="Select * from products";
			s= con.createStatement();
			conf=s.executeQuery(sql);
			log.info("----------------------------------------------------------------");
			log.info("\t\t Products\n\n");
			while(conf.next())			
			{
				log.info(" Product ID: {} \t\tProduct Name: {} \t\tPrice:  {}",conf.getInt("prod_id"),conf.getString("prod_name"),conf.getInt("price"));

			}
			log.info("----------------------------------------------------------------");
			
		}catch (Exception e) {
			log.info("");
		}finally {
	     	   try {
	         	   if (s!=null) {
	         		   s.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info("");   
	                	  }
	     	  try {
	         	   if (conf!=null) {
	         		   conf.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info("");   
	                	  }
	     	   
		}
		

		
	}
}