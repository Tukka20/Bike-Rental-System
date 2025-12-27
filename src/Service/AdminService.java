package Service;

import dao.BikeDao;
import dao.RentDao;
import entity.Bike;
import entity.Rent;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private BikeDao bikeDao;
    private RentDao rentDao;

    public  AdminService()
    {

        bikeDao=new BikeDao();
        rentDao=new RentDao();

    }

    public static boolean addBike(Bike bike)throws SQLException
    {
        return BikeDao.addBike(bike);

    }

    public void deleteBike(int bikeId)throws SQLException
    {

        bikeDao.deleteBike(bikeId);

        System.out.println("--------------------------");
        System.out.println("Bike removed successfully");
        System.out.println("--------------------------");


    }

    public void showAllBike()throws SQLException
    {
        List<Bike> bikes=bikeDao.showAllBike();
        if (bikes.isEmpty())
        {

            System.out.println("-------------------------");
            System.out.println("No bike record is found");
            System.out.println("-------------------------");
        }
        else
        {
            for(Bike bike:bikes)
            {
                System.out.println(bike);
            }


        }

    }

    public void viewAllRentals() throws SQLException {
        List<Rent> rentals = RentDao.getAllRents();
        if (rentals.isEmpty()) {
            System.out.println("--------------------------");
            System.out.println("No rental records found.");
            System.out.println("--------------------------");
        } else {
            viewAllRent(rentals);
        }
    }

    public void viewAllRent(List<Rent> rentals) {
        System.out.println("-----Rental Records-----");
        for (Rent rent : rentals) {
            System.out.println("Rental ID: " + rent.getId() +
                    " | Customer ID: " + rent.getCustomer_id() +
                    " | Bike ID: " + rent.getBike_id() +
                    " | Rent Date: " + rent.getRent_date() +
                    " | Return Date: " + rent.getReturn_date() +
                    " | Total Cost: " + rent.getTotal_cost());
        }
    }



}
