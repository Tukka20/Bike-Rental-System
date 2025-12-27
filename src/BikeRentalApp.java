import Service.AdminService;
import Service.CustomerService;
import dao.AdminDao;
import entity.Admin;
import entity.Bike;
import entity.Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class BikeRentalApp {
    public static void main(String[] args) throws SQLException {

        Scanner sc=new Scanner(System.in);
        AdminService adminService=new AdminService();
        CustomerService customerService=new CustomerService();
        while (true)
        {
            System.out.println("-----WELCOME TO BIKE RENTAL SERVICE-----");

            System.out.println("1. --> ADMIN PANEL ");
            System.out.println("2. --> CUSTOMER PANEL ");
            System.out.println("3. --> CLOSE ");

            System.out.println("------ENTER YOUR CHOICE------");
            int ch =sc.nextInt();

            switch (ch)
            {
                case 1:
                    System.out.print("ENTER THE USERNAME : ");
                    String aUser=sc.next();
                    System.out.print("ENTER THE PASSWORD : ");
                    String aPass=sc.next();

                    Admin admin = new Admin(0, aUser, aPass);
                    boolean isLoggedIn = AdminDao.login(admin);

                    if(isLoggedIn) {
                        System.out.println("------------------------------");
                        System.out.println("  -------WELCOME " + admin.getUserName() + "-------");
                        System.out.println("------------------------------");
                        boolean adminRunning = true;
                        while (adminRunning)
                        {
                            System.out.println("1. ----> SHOW ALL THE BIKE");
                            System.out.println("2. ----> ADD BIKE");
                            System.out.println("3. ----> DELETE BIKE");
                            System.out.println("4. ----> SHOW ALL RENTED BIKE");
                            System.out.println("5. ----> LOGOUT");
                            System.out.println("------ENTER YOUR CHOICE------");
                            int choice = sc.nextInt();
                            sc.nextLine();
                            switch (choice) {
                                case 1:
                                    adminService.showAllBike();
                                    break;
                                case 2:
                                    System.out.print("ENTER THE BIKE NAME : ");
                                    String bname = sc.next();
                                    sc.nextLine();
                                    System.out.print("ENTER THE BIKE MODEL : ");

                                    String bmodel = sc.next();
                                    System.out.print("ENTER THE PRICE OF THE BIKE RENT : ");
                                    double bprice = sc.nextDouble();
                                    System.out.print("BIKE IS AVAILABLE (True/False) : ");
                                    boolean bavailable = sc.nextBoolean();
                                    Bike bike = new Bike();
                                    bike.setBike(bname);
                                    bike.setModel(bmodel);
                                    bike.setPrice(bprice);
                                    bike.setAvailability(bavailable);
                                    boolean isAdd = AdminService.addBike(bike);
                                    if (isAdd) {
                                        System.out.println();
                                        System.out.println("-------BIKE ADDED SUCCESSFULLY-------");
                                        System.out.println();
                                    } else
                                        System.out.println("-------SOMETHING WENT WRONG-------");

                                    break;

                                case 3:
                                    adminService.showAllBike();
                                    System.out.print("ENTER THE BIKE ID YOU WANT TO REMOVE : ");
                                    int id = sc.nextInt();
                                    adminService.deleteBike(id);
                                    break;

                                case 4:
                                    adminService.viewAllRentals();
                                    break;
                                case 5:
                                    adminRunning = false;
                                    break;

                                default:
                                    System.out.println("---------INVALID CHOICE---------");


                            }
                        }
                    }
                    else {
                            System.out.println("------------------------------");
                            System.out.println("ERROR :- INVALID USERNAME OR PASSWORD");
                            System.out.println("------------------------------");
                        }

                        break;



                case 2:
                    Customer customer=new Customer();
                    boolean customerPanelRunning =true;
                    while (customerPanelRunning) {
                        System.out.println("1. --> REGISTER");
                        System.out.println("2. --> LOGIN ");
                        System.out.println("3. --> BACK TO MAIN MENU ");
                        System.out.println("------ENTER YOUR CHOICE------");
                        int choice = sc.nextInt();
                        switch (choice) {

                            case 1:
                                System.out.println("------NEW REGISTRATION------");
                                System.out.print("ENTER YOUR NAME : ");
                                String name = sc.next();
                                System.out.print("ENTER YOUR USERNAME : ");
                                sc.nextLine();
                                String uname = sc.next();
                                System.out.print("ENTER YOUR PASSWORD :");
                                String pass = sc.next();
                                customer.setName(name);
                                customer.setUserName(uname);
                                customer.setPass(pass);
                                boolean isRegister = CustomerService.customerRegistration(customer);
                                if (isRegister) {
                                    System.out.println();
                                    System.out.println("-------REGISTRATION SUCCESSFULLY-------");
                                    System.out.println();
                                } else
                                    System.out.println("-------SOMETHING WENT WRONG-------");

                                break;

                            case 2:
                                System.out.print("ENTER YOUR USERNAME : ");
                                String cuname = sc.next();
                                System.out.print("ENTER YOUR PASSWORD : ");
                                String cpass = sc.next();

                                Customer customer1 = new Customer(0, cuname, cpass);
                                boolean loggedIn = CustomerService.customerLogin(customer1);

                                if (loggedIn) {
                                    System.out.println("------------------------------");
                                    System.out.println("  -------WELCOME " + customer1.getUserName() + "-------");
                                    System.out.println("------------------------------");
                                    boolean customerLoginRunning = true;
                                    while (customerLoginRunning) {
                                        System.out.println("1.---> SHOW ALL AVAILABLE BIKES");
                                        System.out.println("2.---> RENT A BIKE");
                                        System.out.println("3.---> SHOW RENTED BIKE");
                                        System.out.println("4.---> RETURN BIKE");
                                        System.out.println("5.---> LOGOUT");
                                        System.out.println("------ENTER YOUR CHOICE------");
                                        int ch1 = sc.nextInt();
                                        switch (ch1) {

                                            case 1:
                                                System.out.println("----AVAILABLE BIKES----");
                                                customerService.showAvailableBike();

                                                break;
                                            case 2:
                                                System.out.println("-------AVAILABLE FOR RENT------- ");
                                                customerService.showAvailableBike();
                                                System.out.print("ENTER YOUR CUSTOMER ID: ");
                                                int cId = sc.nextInt();
                                                System.out.print("ENTER THE BIKE ID : ");
                                                int bId = sc.nextInt();
//                                                System.out.print("ENTER THE RETURN DATE (dd-mm-yyyy) : ");
//                                                String returnDate = sc.next();
                                                System.out.print("ENTER THE PRICE : ");
                                                double price = sc.nextDouble();
                                                customerService.rentBike(cId, bId, price);
                                                break;
                                            case 3:

                                                System.out.print("ENTER YOUR CUSTOMER ID : ");
                                                int custId = sc.nextInt();
                                                CustomerService.viewCustRentals(custId);
                                                break;
                                            case 4:
                                                System.out.print("ENTER YOUR CUSTOMER ID : ");
                                                int cuId = sc.nextInt();
                                                System.out.print("ENTER THE BIKE ID FOR RETURN : ");
                                                int biId = sc.nextInt();
                                                CustomerService.returnBike(cuId, biId);
                                                break;
                                            case 5:
                                                customerLoginRunning = false;
                                                break;


                                            default:
                                                System.out.println("---------INVALID CHOICE---------");


                                        }

                                    }

                                } else {
                                    System.out.println("-------------------------------------");
                                    System.out.println("ERROR :- INVALID USERNAME OR PASSWORD");
                                    System.out.println("-------------------------------------");

                                }
                                break;
                            case 3:
                                customerPanelRunning = false;
                                break;

                            default:
                                System.out.println("---------INVALID CHOICE---------");
                        }
                    }
                    break;



                case 3:
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println("THANKS FOR USING BIKE RENTAL SYSTEM");
                    System.out.println("-------------------------------------");
                    System.exit(0);
                    break;



                default:
                    System.out.println("---------INVALID CHOICE---------");




            }



        }


    }
}
