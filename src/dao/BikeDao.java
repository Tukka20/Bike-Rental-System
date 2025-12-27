package dao;

import config.DbConfig;
import entity.Bike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BikeDao {

    public static boolean addBike(Bike bike) throws SQLException
    {

        Connection connection= DbConfig.getconnection();

        PreparedStatement ps=connection.prepareStatement("insert into bike(bike,model,price,availability) values(?,?,?,?)");

        ps.setString(1, bike.getBike());
        ps.setString(2,bike.getModel());
        ps.setDouble(3,bike.getPrice());
        ps.setBoolean(4,bike.isAvailability());
        ps.executeUpdate();
        ps.close();
        connection.close();
        return true;
    }

    public void deleteBike (int bikeId)throws SQLException
    {

        Connection connection= DbConfig.getconnection();
        PreparedStatement ps =connection.prepareStatement("delete from bike where id =?");

        ps.setInt(1,bikeId);
        ps.executeUpdate();
        ps.close();
        connection.close();


    }

    public List<Bike> showAllBike() throws SQLException
    {
        List<Bike> bikeList=new ArrayList<>();
        Connection connection=DbConfig.getconnection();
        Statement statement=connection.createStatement();
        ResultSet rs= statement.executeQuery("select* from bike");
        while (rs.next())
        {
            bikeList.add(new Bike(rs.getInt("id"),rs.getString("bike"),
                    rs.getString("model"),rs.getDouble("price"),
                    rs.getBoolean("availability")));
        }

        return bikeList;
    }

    public Bike getBikeById(int id) throws SQLException {
        Connection connection = DbConfig.getconnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM bike WHERE id = ?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Bike bike = null;
        if (rs.next()) {
            bike = new Bike(
                    rs.getInt("id"),
                    rs.getString("bike"),
                    rs.getString("model"),
                    rs.getDouble("price"),
                    rs.getBoolean("availability")
            );
        }

        rs.close();
        ps.close();
        connection.close();

        return bike;
    }


    public void updateBikeAvailability(int id, boolean availability ) throws SQLException {
        Connection connection = DbConfig.getconnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE bike SET availability = ? WHERE id = ?");
        ps.setBoolean(1,availability);
        ps.setInt(2,id);
        ps.executeUpdate();
        ps.close();
        connection.close();


    }
}

