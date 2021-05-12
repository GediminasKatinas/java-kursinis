package jdbc;

import GUI.Main;
import model.User;

import java.sql.*;

public class userJDBC {
    public static void create(String user,String psw) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO user (login, psw) "
                + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user);
        ps.setString(2, psw);


        ps.executeUpdate();
        ResultSet ids = ps.getGeneratedKeys();
        ids.close();
        ps.close();
    }

}
