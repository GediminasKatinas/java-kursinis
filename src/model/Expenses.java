package model;

import java.io.Serializable;

public class Expenses implements Serializable {
    String expensesDescription;
    int expensesAmount;


    public Expenses(String expensesDescription, int expensesAmount) {
        this.expensesDescription = expensesDescription;
        this.expensesAmount = expensesAmount;

    }
    public Expenses(){

    }

    public String getExpensesDescription() {
        return expensesDescription;
    }

    public void setExpensesDescription(String expensesDescription) {
        this.expensesDescription = expensesDescription;
    }

    public int getExpensesAmount() {
        return expensesAmount;
    }

    public void setExpensesAmount(int expensesAmount) {
        this.expensesAmount = expensesAmount;
    }
    public String toString() {
        return
                "expense=" + expensesAmount+ '\'' +
                ", description='" + expensesDescription + '\''
                ;
    }
}
