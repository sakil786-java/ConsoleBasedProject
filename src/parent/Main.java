package parent;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class Main {
	static Logger log = LogManager.getLogger(Main.class.getName());
	static String email="";
	public static void main(String[] args)  {
		DbConnection d=new DbConnection();
		DbConnection.connection();
		Scanner sc=new Scanner(System.in);
		String check="";
		
		int choice=0;
		do {
			mainMenu();
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				check = existingUserCaseOne(d, sc);
				break;
			case 2:
				check = enterAsAdminCaseTwo(d, sc);
				break;
			case 3:
				check = createNewAccountCaseThree(d, sc);
				break;
			default:
				log.info("Choose Between 1 and 2");
			}
			if(check!=null)//changes
			{switch(check)
				{case "Admin":
				caseOfAdmin(d, sc);
						break;
					case "Customer":
						log.info("Welcome user");
						Customer c=new Customer();
						int choi=0;
						do {
							choi = insideCustomerDoLoop(d, sc, c);
						}while(choi!=0);
						break;
					default:
						log.info("");
				}
			}
		}while(choice!=0);
	}
	private static void caseOfAdmin(DbConnection d, Scanner sc) {
		Admin a=new Admin();
			int addChoice=0;
			log.info("Welcome Admin");
			do {
			menuOfAdmin();
			addChoice=sc.nextInt();
			wholeSwitchCaseOfAdmin(d, sc, a, addChoice);
			}while(addChoice!=0);
	}
	private static void mainMenu() {
		log.info("Press 1 for Existing User");
		log.info("Press 2 to login as Admin");
		log.info("Press 3 to Create a new Account");
	}
	private static void menuOfAdmin() {
		log.info("1 -> Add");
		log.info("2 -> Update");
		log.info("3 -> Remove");
		log.info("4 -> View");
		log.info("0 -> Exit");
	}
	private static void wholeSwitchCaseOfAdmin(DbConnection d, Scanner sc, Admin a, int addChoice) {
		switch (addChoice) {
		case 1:
			log.info("Enter the product name: ");
			String s1=sc.next();
			log.info("Enter the product price: ");
			int p=sc.nextInt();
			a.addProd(s1,p);
			break;
		case 2:
			d.view();
			log.info("Enter product id: ");
			int id=sc.nextInt();
			log.info("Enter the price to be updated: ");
			int price=sc.nextInt();
			if(price!=0)
				a.updateProd(id,price);
			else
				log.info("Price should be greater than zero");
			break;
		case 3:
			d.view();
			log.info("Enter the product id: ");
			int id2=sc.nextInt();
			a.deleteProd(id2);
			break;
		case 4:
			d.view();
			break;
		case 5:
			log.info("Enter Your Choice Between 1,2 and 3");	
			break;
		case 0:
			break;
		default:
			log.info("Wrong choice!!!");
		}
	}
	private static String existingUserCaseOne(DbConnection d, Scanner sc) {
		String check;
		log.info("Enter Email: ");
		sc.nextLine();
		String user=sc.nextLine();
		log.info("Password: ");
		String pass=sc.nextLine();
		check=d.checkUser(user,pass);
		if(check.equals("Customer"))
			email=user;
		return check;
	}
	private static String enterAsAdminCaseTwo(DbConnection d, Scanner sc) {
		String check;
		log.info("Enter Your Security Key: ");
		String securityNumber=sc.next();
		check=d.checkUser(securityNumber);
		if(check.equals(""))
			log.info("Enter valid security number");
		return check;
	}
	private static String createNewAccountCaseThree(DbConnection d, Scanner sc) {
		String check;
		log.info("Name: ");
		sc.nextLine();
		String userNames=sc.nextLine();
		log.info("Email Id: ");
		String email=sc.nextLine();
		log.info("Addresss: ");
		String address=sc.nextLine();
		log.info("Phone Number Should be equalto 10 ");
		log.info("Phone Number: ");
		String phno=sc.nextLine();
		
		log.info("Password Should be greater than equalto 8 ");
		log.info("Password: ");
		String pass=sc.nextLine();
		log.info("Confirm Password: ");
		String confirmPasswords=sc.nextLine();
		
		log.info("customer spell should be match Like This-> Customer : ");
		log.info("Enter Role as Customer: ");
		String role=sc.nextLine();
		if(role.equals("Admin"))
		{
			log.info("Looks Like!!You enter role as Admin or try to access The Admin module  thats why app is terminated");
			System.exit(0);
		}
		d.createAccount(userNames,email,address,phno,pass,confirmPasswords,role);
		check="";
		return check;
	}
	private static int insideCustomerDoLoop(DbConnection d, Scanner sc, Customer c) {
		int choi;
		log.info("1 -> Add to Cart");
		log.info("2 -> View Cart");
		log.info("3 -> Remove From Cart");
		log.info("4 -> Bill");
		log.info("5 -> Place Order");
		log.info("0 -> Exit!");
		choi=sc.nextInt();
		switch(choi)
		{
			case 1: 
				d.view();
				log.info("Enter Product Id to add to cart: ");
				int productsId=sc.nextInt();
				log.info("Quantiy in Kg: ");
				int quan=sc.nextInt();
				c.addToCart(productsId,quan);
				break;
			case 2:
				c.viewCart();
				break;
			case 3:
				c.viewCart();
				log.info("Enter the Prod_id from the cart to remove: ");
				int p=sc.nextInt();
				c.removeCart(p);
				break;
			case 4:
				c.bill();
				break;
			case 5:
				c.placeOrder(email);
				break;
			case 0:
				break;
			default:
					log.info("Wrong choice!!!");
		}
		return choi;
	}
}