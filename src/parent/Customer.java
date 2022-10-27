package parent;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Customer {
	static Logger log = LogManager.getLogger(Customer.class.getName());
	static String priceString="price";
	static String productId="prod_id";
	static String productName="prod_name";
	public void addToCart(int p, int q) 
	{
		Statement s2=null;
		ResultSet rs=null;
		
		try {
			DbConnection.connection();
			String s1="select * from products where prod_id=('"+p+"')";
			s2=DbConnection.con.createStatement();
			rs=s2.executeQuery(s1);
			int productId1=0;
			int price=0;
			String s3="";
			
			while(rs.next())
			{
				productId1=rs.getInt(productId);
				s3=rs.getString(productName);
				price=rs.getInt(priceString)*q;
				String sql="Insert into cart values('"+productId1+"', '"+s3+"', '"+price+"', '"+q+"')";
				
				int conf=s2.executeUpdate(sql);
				if(conf==1)
					log.info("Product Added to Cart Successfully");
				
				else
					log.info("Something went wrong try again");
			}
		}catch (Exception e) {
			log.info("");
		}finally {
			try {
		      	   if (s2!=null) {
		     		   s2.close();    
		     		    }}
		     	   catch (Exception e) {
		            	log.info(e);   
		            	  }
		     	   try {
		         	   if (rs!=null) {
		         		   rs.close();    
		         		    }}
		         	   catch (Exception e) {
		                	log.info(e);   
		                	  }	
		}
	}
	public void viewCart() {
		
		Statement s=null;
		ResultSet conf=null;
		try {
			DbConnection.connection();
			String sql="Select * from cart";
			s=DbConnection.con.createStatement();
			conf=s.executeQuery(sql);
			log.info("-------------------------------------------------------------------------");
			log.info("\t\tCart\n\n");
			
			while(conf.next())			
			{
				log.info(" Product ID: {} \t\tProduct Name: {} \t\tQuantity(kg): {} \t\tPrice:  {}",conf.getInt(productId),conf.getString(productName),conf.getInt("quantity"),conf.getInt(priceString));		}
			log.info("--------------------------------------------------------------------------");
		} catch (Exception e) {
			log.info("");
		}finally {
			try {
		      	   if (conf!=null) {
		     		   conf.close();    
		     		    }}
		     	   catch (Exception e) {
		            	log.info(e);   
		            	  }
		     	   try {
		         	   if (s!=null) {
		         		   s.close();    
		         		    }}
		         	   catch (Exception e) {
		                	log.info(e);   
		                	  }	
		}
	}
	public void removeCart(int p) {
		Statement s=null;
		try {
			DbConnection.connection();
			
			String sql="Delete from cart where prod_id='"+p+"'";
			s= DbConnection.con.createStatement();
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
		            	log.info(e);   
		            	  }
		}
		
	}
	public void bill() {
		ResultSet conf=null;
		Statement s=null;
		ResultSet rs=null;
		try {
			DbConnection.connection();
			String sql="Select * from cart";
			s= DbConnection.con.createStatement();
			conf=s.executeQuery(sql);
			log.info("-------------------------------------------------------------------------");
			log.info("\t\tTotal Bill\n\n");
			
			int f=0;
			while(conf.next())			
			{
				f=1;
				log.info(" Product ID: {} \t\tProduct Name: {} \t\tQuantity(kg): {} \t\tPrice:  {}",conf.getInt(productId),conf.getString(productName),conf.getInt("quantity"),conf.getInt(priceString));
				
			}
			if(f!=0)
			{
				String sql2="Select sum(price) as sum from cart";
				rs=s.executeQuery(sql2);
				log.info("\n");
				while(rs.next())
					log.info("\t\t\tTotal Amount \t {}",rs.getInt("sum"));
			}
			log.info("--------------------------------------------------------------------------");
			
		} catch (Exception e) {
			log.info("");
		}finally {
			try {
		      	   if (conf!=null) {
		     		   conf.close();    
		     		    }}
		     	   catch (Exception e) {
		            	log.info(e);   
		            	  }
		     	   try {
		         	   if (rs!=null) {
		         		   rs.close();    
		         		    }}
		         	   catch (Exception e) {
		                	log.info(e);   
		                	  }	
		     	  try {
		        	   if (s!=null) {
		        		   s.close();    
		        		    }}
		        	   catch (Exception e) {
		               	log.info(e);   
		               	  }	
		}
		}
		
	


	public void placeOrder(String email){
		Statement s=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		ResultSet rs1=null;
		try {
			String sql="select * from customer where email='"+email+"'";
			s= DbConnection.con.createStatement();
			rs=s.executeQuery(sql);
			while(rs.next())
			{
				String name=rs.getString("name");
				String address=rs.getString("address");
				String phno=rs.getString("phone_no");
				String sql1="select prod_name from cart";
				String con="";
				
				 rs2=s.executeQuery(sql1);
				con = insiders2WhileLoop(rs2, con);
				String sql2="Select sum(price) as sum from cart";
				 rs1=s.executeQuery(sql2);
				int sum=0;
				while(rs1.next())
				{
					sum=rs1.getInt("sum");
				}
				String sql3="Insert into placeorder(name, phno, address, products , total_amount) values('"+name+"','"+phno+"','"+address+"','"+con+"','"+sum+"')";
				
				int conf=s.executeUpdate(sql3);
				if(conf==1)
					log.info("Thank You For Ordering Products From Our Store :)");
				else
					log.info(".....");
				break;
			}

			
		} catch (Exception e) {
			log.info("");
		}finally {
	     	   try {
	         	   if (s!=null) {
	         		   s.close();    
	         		    }}
	         	   catch (Exception e) {
	                	log.info(e);   
	                	  }
	         	   try {
	             	   if (rs!=null) {
	             		   rs.close();    
	             		    }}
	             	   catch (Exception e) {
	                    	log.info(e);   
	                    	  }
	         	  try {
	             	   if (rs1!=null) {
	             		   rs1.close();    
	             		    }}
	             	   catch (Exception e) {
	                    	log.info(e);   
	                    	  }
	         	 try {
	             	   if (rs2!=null) {
	             		   rs2.close();    
	             		    }}
	             	   catch (Exception e) {
	                    	log.info(e);   
	                    	  }
	         	   
	            }
			}
	private String insiders2WhileLoop(ResultSet rs2, String con) throws SQLException {
		while(rs2.next())
		{
			con=con+" "+rs2.getString(productName);
		}
		return con;
	}
}