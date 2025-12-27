package dao;

import config.DbConfig;
import entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    public static boolean login(Admin admin)throws SQLException
    {

        Connection connection= DbConfig.getconnection();
        PreparedStatement ps=connection.prepareStatement("select * from admin where username=? and pass=?");

        ps.setString(1,admin.getUserName());
        ps.setString(2,admin.getPass());

        ResultSet rs=ps.executeQuery();
        if (rs.next())
        {

            return true;
        }

        rs.close();
        ps.close();
        connection.close();
        return false;


    }


}
