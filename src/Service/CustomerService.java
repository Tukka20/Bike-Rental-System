package Service;

import dao.BikeDao;
import dao.CustomerDao;
import dao.RentDao;
import entity.Bike;
import entity.Customer;
import entity.Rent;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    public CustomerService()
    {
        CustomerDao customerDao= new CustomerDao();
        BikeDao bikeDao=new BikeDao();
        RentDao rentDao=new RentDao();

    }

    public static boolean customerRegistration(Customer customer)
    {

        try {
            return CustomerDao.registration(customer);

        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static boolean customerLogin(Customer customer)
    {

        try
        {

            return CustomerDao.login(customer);

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

            return false;
        }


    }

    public  void showAvailableBike()throws SQLException
    {
        BikeDao bikeDao=new BikeDao();
        List<Bike> bikeList=bikeDao.showAllBike();

        if (bikeList.isEmpty())
        {

            System.out.println("-------------------------");
            System.out.println("No bike record is found");
            System.out.println("-------------------------");
        }
        else
        {
            for(Bike bike: bikeList)
            {

                if(bike.isAvailability())
                {
                    System.out.println("_________________________________________________");
                    System.out.println(bike);
                    System.out.println("_________________________________________________");

                }
            }


        }


    }

    public  void rentBike(int customerId, int bikeId, double totalPrice) throws SQLException

    {
        BikeDao bikeDao=new BikeDao();
        RentDao rentDao=new RentDao();

        Bike bike=bikeDao.getBikeById(bikeId);

        if (bike==null)
        {
            System.out.println("Bike not found");
        }

        if (!bike.isAvailability())
        {
            System.out.println("Bike is rented by some other customer");
        }


        Rent rent= new Rent(0,customerId,bikeId,  null,null,totalPrice);

        rentDao.rentBike(rent);

        bikeDao.updateBikeAvailability(bikeId,false);

        System.out.println("---------------------------");
        System.out.println("Bike rented successfully");
        System.out.println("---------------------------");


    }

    public static void returnBike(int customerId,int bikeId)throws SQLException
    {

        RentDao rentDao=new RentDao();
        BikeDao bikeDao=new BikeDao();

        boolean returned=RentDao.returnBike(customerId,bikeId);

        if (returned)
        {
            bikeDao.updateBikeAvailability(bikeId, true);
            System.out.println("---------------------------");
            System.out.println("Bike returned Successfully");
            System.out.println("---------------------------");
        } else {
            System.out.println(" No active rental found for this bike and customer.");
        }



    }


    public static void viewCustRentals(int custId) throws SQLException
    {
        List<Rent> custRentals = RentDao.getRentalRecordByCustId(custId);
        if (custRentals.isEmpty()) {
            System.out.println("--------------------------");
            System.out.println("No rental records found by this customer id");
            System.out.println("--------------------------");
        } else {
            viewCustRent(custRentals);
        }
    }

    public static void viewCustRent(List<Rent> rentals) {
        System.out.println("-----Rental Records-----");
        for (Rent rent : rentals) {
            System.out.println("Rental ID: " + rent.getId() +
                    " | Customer ID: " + rent.getCustomer_id() +
                    " | Bike ID: " + rent.getBike_id() +
                    " | Rent Date: " + rent.getRent_date() +
                    " | Total Cost: " + rent.getTotal_cost());
        }
    }




}
