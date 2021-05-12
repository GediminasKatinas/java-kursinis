package jdbc;

import java.sql.*;

public class catJDBC {
    public static void createCat(String name,String email,String user) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO category (name, email, user) "
                + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, user);

        ps.executeUpdate();
        ResultSet ids = ps.getGeneratedKeys();
        ids.close();
        ps.close();
    }
    public static void removeCat(String name) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement prep = con.prepareStatement("DELETE FROM category WHERE name = ?");
        prep.setString(1, name);
        prep.executeUpdate();
        prep.close();
    }
    public static void createsubCat(String name,String email,String user) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO subcategory (name, email, parentcat) "
                + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, name.toString());
        ps.setString(2, email.toString());
        ps.setString(3, user);

        ps.executeUpdate();
        ResultSet ids = ps.getGeneratedKeys();
        ids.close();
    }
    public static void removesubCat(String name) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement prep = con.prepareStatement("DELETE FROM subcategory WHERE name = ?");
        prep.setString(1, name);
        prep.executeUpdate();

        prep.close();
    }


}
