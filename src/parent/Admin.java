package parent;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Admin {
	static Logger log = LogManager.getLogger(Admin.class.getName());
	public void addProd(String s1,int p) {
		
		Statement s=null;
		try {
			DbConnection.connection();
			String sql="Insert into products(prod_name,price) values('"+s1+"','"+p+"')";
			 s= DbConnection.con.createStatement();
			int conf=s.executeUpdate(sql);
			
			if(conf==1)
				log.info("Product Added Successfully");
			
			else
				log.info("Something went wrong try again");
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
	public void deleteProd(int id) {
		Statement s=null;
		try {
			DbConnection.connection();
			String sql="Delete from products where prod_id='"+id+"'";
			 s=DbConnection.con.createStatement();
			int conf=s.executeUpdate(sql);
			if(conf==1)
				log.info("Deleted Successfully");
			else
				log.info("Enter valid product id");
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
	public void updateProd(int id,int price){
		Statement s=null;
		try {
			DbConnection.connection();
			String sql="update products set price='"+price+"' where prod_id='"+id+"'";
			s= DbConnection.con.createStatement();
			int conf=s.executeUpdate(sql);
			if(conf==1)
				log.info("Updated Successfully");
			else
				log.info("Enter valid product id");
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
}