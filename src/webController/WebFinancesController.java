package webController;

import com.google.gson.Gson;
import jdbc.catJDBC;
import jdbc.financesJDBC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.Properties;
@Controller
public class WebFinancesController {
    @RequestMapping(value = "/finances/createProfit", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createProfit(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String desc = data.getProperty("desc");
        String amount = data.getProperty("amount");
        String cat = data.getProperty("category");
        String subcat = data.getProperty("subcategory");
        financesJDBC.createProfit(desc,amount,cat,subcat);
        return "profit added";
    }
    @RequestMapping(value = "/finances/createExpense", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createExpense(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String desc = data.getProperty("desc");
        String amount = data.getProperty("amount");
        String cat = data.getProperty("category");
        String subcat = data.getProperty("subcategory");
        financesJDBC.createExpense(desc,amount,cat,subcat);
        return "profit added";
    }
    @RequestMapping(value = "/finances/deleteExpense", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteExpense(@RequestParam("description") String name) throws SQLException, ClassNotFoundException { //url/categoryInfo?name=Pilies
       financesJDBC.removeExpense(name);
        return "deleted";
    }
    @RequestMapping(value = "/finances/deleteProfit", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteProfit(@RequestParam("description") String name) throws SQLException, ClassNotFoundException { //url/categoryInfo?name=Pilies
        financesJDBC.removeProfit(name);
        return "deleted";
    }
}
