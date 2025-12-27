package dao;

import config.DbConfig;
import entity.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentDao {

    public  void rentBike(Rent rent) throws SQLException
    {

        Connection connection = DbConfig.getconnection();
        PreparedStatement ps=connection.prepareStatement("insert into rental(customer_id,bike_id,rent_date,return_date,total_cost) values (?,?,now(),?,?)");

        ps.setInt(1,rent.getCustomer_id());
        ps.setInt(2,rent.getBike_id());
        ps.setTimestamp(3,rent.getReturn_date());
        ps.setDouble(4,rent.getTotal_cost());

        ps.executeUpdate();
        ps.close();

        PreparedStatement updateBike = connection.prepareStatement(
                "UPDATE bike SET availability = FALSE WHERE id = ?"
        );
        updateBike.setInt(1, rent.getBike_id());
        updateBike.executeUpdate();
        updateBike.close();

        connection.close();

    }

    public static boolean returnBike(int customerId, int bikeId) throws SQLException {

        Connection connection = DbConfig.getconnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE rental SET return_date = NOW() WHERE customer_id = ? AND bike_id = ? AND return_date IS NULL");

        ps.setInt(1, customerId);
        ps.setInt(2,bikeId);

        ps.executeUpdate();
        ps.close();
        connection.close();
        return true;

    }


        public static List<Rent> getAllRents() throws SQLException {
            List<Rent> rentList = new ArrayList<>();
            Connection connection = DbConfig.getconnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rental where return_date is null");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rent rent = new Rent(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("bike_id"),
                        rs.getTimestamp("rent_date"),
                        rs.getTimestamp("return_date"),
                        rs.getDouble("total_cost")
                );
                rentList.add(rent);
            }

            rs.close();
            ps.close();
            connection.close();

            return rentList;
        }

        public static List <Rent>getRentalRecordByCustId(int custId)throws SQLException
        {
            List<Rent> rentList = new ArrayList<>();
         Connection connection=DbConfig.getconnection();
         PreparedStatement ps= connection.prepareStatement("SELECT * FROM rental WHERE customer_id = ? AND return_date IS NULL");
         ps.setInt(1,custId);
         ResultSet rs= ps.executeQuery();
         while (rs.next())
         {
             Rent rent=new Rent(
                     rs.getInt("id"),
                     rs.getInt("customer_id"),
                     rs.getInt("bike_id"),
                     rs.getTimestamp("rent_date"),
                     rs.getTimestamp("return_date"),
                     rs.getDouble("total_cost")
             );
             rentList.add(rent);
         }
         rs.close();
         ps.close();
         connection.close();
         return rentList;

        }



}







