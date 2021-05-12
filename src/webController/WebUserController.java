package webController;

import com.google.gson.Gson;
import jdbc.catJDBC;
import jdbc.userJDBC;
import model.FinanceManagementSystem;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import utils.databaseUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
@Controller
public class WebUserController {
    public static FinanceManagementSystem fmis = new FinanceManagementSystem();

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers() throws SQLException, ClassNotFoundException {
        Gson gson = new Gson();
        utils.databaseUtils.DataIn();
        List<User> users=User.getUsers();
        return gson.toJson(users);
    }
    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteCat(@RequestParam("login") String name) throws SQLException, ClassNotFoundException { //url/categoryInfo?name=Pilies
        Gson gson = new Gson();
        utils.databaseUtils.DataIn();
        if (name.equals("")) return "No  name provided";
        User.getUsers().removeIf(user -> user.getLogin().equals(name));
        PreparedStatement prep = databaseUtils.connectDB().prepareStatement("DELETE FROM user WHERE login = ?");
        prep.setString(1, name);
        prep.executeUpdate();
        prep.close();
        return gson.toJson("deleted "+name+ "user.");
    }
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createUser(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String user = data.getProperty("username");
        String pass = data.getProperty("password");
        userJDBC.create(user,pass);
        return parser.toJson("user added");
    }

}
