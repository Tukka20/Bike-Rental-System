package dao;

import config.DbConfig;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    public static boolean registration(Customer customer) throws SQLException
    {

        Connection connection= DbConfig.getconnection();

        PreparedStatement ps=connection.prepareStatement("insert into customer (name,username,pass) values (?,?,?)");
        PreparedStatement checkUser = connection.prepareStatement("SELECT username FROM customer WHERE username = ?");

        ps.setString(1,customer.getName());

        checkUser.setString(1,customer.getUserName());
        ResultSet rs=checkUser.executeQuery();
        if (rs.next())
        {
            System.out.println("Error :- User Name is already taken");
            return false;
        }
        ps.setString(2,customer.getUserName());

        ps.setString(3,customer.getPass());

        ps.executeUpdate();
        checkUser.close();
        ps.close();
        connection.close();

        return true;

    }

    public static Boolean login(Customer customer)throws SQLException
    {

        Connection connection=DbConfig.getconnection();
        PreparedStatement ps=connection.prepareStatement("select * from customer where username=? and pass=?");

        ps.setString(1,customer.getUserName());
        ps.setString(2,customer.getPass());

        ResultSet rs=ps.executeQuery();
        if (rs.next())
        {

            return true;
        }
        else
            return false;


    }


}
