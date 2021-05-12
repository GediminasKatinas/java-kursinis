package GUI;

import controller.CategoryManagement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jdbc.financesJDBC;
import model.*;
import utils.databaseUtils;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class financialMenu {
    public Button addExpenseBtn;
    public Button remExpBtn;
    public Button addProfitBtn;
    public Button remProfitBtn;
    public Button backBtn;
    public TextField textField;
    public MenuItem showInfo;
    public TreeView selectionTreeView;
    FinanceManagementSystem fmis = dataManagement.fmis;
    int pavad = manageCat.skaicius;
    Connection con = Main.con;

    public void addExpense(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        Object toAddSubComp = JOptionPane.showInputDialog(frame, "Enter subcats name:");
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("subcat doesnt exist");
            alert.showAndWait();
        }


        Object result = JOptionPane.showInputDialog(frame, "Enter the description");
        Object reisult = JOptionPane.showInputDialog(frame, "Enter the amount:");
        fmis.getCategories().get(pavad).getSubCategories().get(index).getExpenses().add(new Expenses((String) result, Integer.parseInt((String) reisult)));
        financesJDBC.createExpense(result.toString(),reisult.toString(), fmis.getCategories().get(pavad).getName(),toAddSubComp.toString());

    }

    public void remExpense(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        Object toAddSubComp = JOptionPane.showInputDialog(frame, "Enter subcats name:");
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("subcat doesnt exist");
            alert.showAndWait();
        }

        Object removeexp = JOptionPane.showInputDialog(frame, "Enter expense description info to remove that expense:");
        fmis.getCategories().get(pavad).getSubCategories().get(index).getExpenses().removeIf(Expenses -> Expenses.getExpensesDescription().equals(removeexp));
        financesJDBC.removeExpense(removeexp.toString());
        System.out.println("expense removed");
    }

    public void addProfit(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        Object toAddSubComp = JOptionPane.showInputDialog(frame, "Enter subcats name:");
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("subcat doesnt exist");
            alert.showAndWait();
        }
        Object result = JOptionPane.showInputDialog(frame, "Enter the description:");
        Object reisult = JOptionPane.showInputDialog(frame, "Enter the amount:");

        fmis.getCategories().get(pavad).getSubCategories().get(index).getIncome().add(new Income((String) result, Integer.parseInt((String) reisult)));
        financesJDBC.createProfit(result.toString(),reisult.toString(), fmis.getCategories().get(pavad).getName(),toAddSubComp.toString());

    }

    public void removeProfit(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame();
        Object toAddSubComp = JOptionPane.showInputDialog(frame, "Enter subcats name:");
        int index = 0;

        for (int i = 0; i < fmis.getCategories().get(pavad).getSubCategories().size(); i++) {
            if (fmis.getCategories().get(pavad).getSubCategories().get(i).getName().equals(toAddSubComp)) {
                index = i;
                break;
            } else {
                index = -1;
                continue;
            }
        }
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("subcat doesnt exist");
            alert.showAndWait();
        }
        Object removeinc = JOptionPane.showInputDialog(frame, "Enter expense description info to remove that expense:");
        fmis.getCategories().get(pavad).getSubCategories().get(index).getIncome().removeIf(Expenses -> Expenses.getIncomeDescription().equals(removeinc));
        financesJDBC.removeProfit(removeinc.toString());
        System.out.println("expense removed");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {

        FXMLLoader load = new FXMLLoader(getClass().getResource("manageCat.fxml"));
        Parent root = load.load();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void showInfo(ActionEvent actionEvent) {

        createTree();

    }

    public void createTree() {


        selectionTreeView.setRoot(new TreeItem<String>("neveikia tree:("));
        fmis.getCategories().get(pavad).getSubCategories().forEach(subCategory -> addTreeItems(subCategory, selectionTreeView.getRoot()));
        selectionTreeView.setShowRoot(false);


    }

    private void addTreeItems(Category section, TreeItem parentItem) {

        TreeItem<String> sectionTreeItem1 = new TreeItem<String>(section.getName());
        TreeItem<ArrayList> sectionTreeItem = new TreeItem<ArrayList>(section.getIncome());
        TreeItem<ArrayList> sectionTreeItem2 = new TreeItem<ArrayList>(section.getExpenses());
        parentItem.getChildren().add(sectionTreeItem1);
        parentItem.getChildren().add(sectionTreeItem);
        parentItem.getChildren().add(sectionTreeItem2);
        section.getSubCategories().forEach(subsection -> addTreeItems(subsection, sectionTreeItem));

    }
}
