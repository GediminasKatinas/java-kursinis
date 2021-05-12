package webController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Alert;
import jdbc.catJDBC;
import model.Category;
import model.FinanceManagementSystem;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.databaseUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
@Controller
public class WebCategoriesController {
    public FinanceManagementSystem fmis = new FinanceManagementSystem();
    Gson gson = new Gson();

    public WebCategoriesController() throws SQLException, ClassNotFoundException {
    }

    @RequestMapping(value = "/categories/categoryList", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCategories() throws SQLException, ClassNotFoundException {
        utils.databaseUtils.DataIn();
        List<Category> categories=fmis.getCategories();

        return gson.toJson(categories);
    }
    @RequestMapping(value = "/categories/categoryInfo", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getInfoByName(@RequestParam("name") String name) throws SQLException, ClassNotFoundException { //url/categoryInfo?name=Pilies
        utils.databaseUtils.DataIn();
        if (name.equals("")) return "No  name provided";
        int index = 0;
        for (int i = 0; i < fmis.getCategories().size(); i++) {
            if (fmis.getCategories().get(i).getName().equals(name)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }}
            Category specat = fmis.getCategories().get(index);
        return gson.toJson(specat.getEmail()+" "+specat.getName()+" "+specat.getOwner());

    }
    @RequestMapping(value = "/categories/categoryDel", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteCat(@RequestBody String request) throws SQLException, ClassNotFoundException { //url/categoryInfo?name=Pilies
        utils.databaseUtils.DataIn();
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("name");
        catJDBC.removeCat(name);
        return gson.toJson("deleted "+name+ "category.");
    }
    @RequestMapping(value = "/categories/createCat", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createCategory(@RequestBody String request) throws SQLException, ClassNotFoundException {
Gson parser = new Gson();
Properties data = parser.fromJson(request, Properties.class);
String name = data.getProperty("name");
        String email = data.getProperty("email");
        String user = data.getProperty("user");
        catJDBC.createCat(name,email,user);
return "cat added";
    }
    @RequestMapping(value = "/categories/createSubCat", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createSubCategory(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("name");
        String email = data.getProperty("email");
        String parentcat = data.getProperty("parentcat");
        catJDBC.createsubCat(name,email,parentcat);
        return "cat added";
    }
    @RequestMapping(value = "/categories/SubcategoryDel", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteSubCat(@RequestParam("SubCatsName") String name) throws SQLException, ClassNotFoundException { //url/categoryInfo?name=Pilies
        utils.databaseUtils.DataIn();
        if (name.equals("")) return "No  name provided";
        catJDBC.removesubCat(name);
        return gson.toJson("deleted "+name+ "subcategory.");
    }
}

