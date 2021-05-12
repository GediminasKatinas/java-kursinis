package jdbc;

import java.sql.*;

public class financesJDBC {
    public static void createExpense(String desc,String amount,String cat,String subcat) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO expenses (amount, description, subcategory, category) "
                + "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, amount.toString());
        ps.setString(2, desc.toString());
        ps.setString(3, subcat);
        ps.setString(4, cat);

        ps.executeUpdate();
        ResultSet ids = ps.getGeneratedKeys();
        ids.close();
        ps.close();
    }
    public static void removeExpense(String name) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement prep = con.prepareStatement("DELETE FROM expenses WHERE description = ?");
        prep.setString(1, name);
        prep.executeUpdate();

        prep.close();
    }
    public static void createProfit(String desc,String amount,String cat,String subcat) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO income (amount, description, subcategory, category) "
                + "VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, amount.toString());
        ps.setString(2, desc.toString());
        ps.setString(3, subcat);
        ps.setString(4, cat);
        ps.executeUpdate();
        ResultSet ids = ps.getGeneratedKeys();
        ids.close();
        ps.close();
    }
    public static void removeProfit(String name) throws ClassNotFoundException, SQLException {
        Connection con=utils.databaseUtils.connectDB();
        PreparedStatement prep = con.prepareStatement("DELETE FROM income WHERE description = ?");
        prep.setString(1, (String) name);
        prep.executeUpdate();

        prep.close();
    }

}
