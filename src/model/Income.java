package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Income  implements Serializable {
    String incomeDescription;
    int incomeAmount;


    public Income(String incomeDescription, int incomeAmount) {
        this.incomeDescription = incomeDescription;
        this.incomeAmount = incomeAmount;

    }

    public Income() {

    }

    public String getIncomeDescription() {
        return incomeDescription;
    }

    public void setIncomeDescription(String incomeDescription) {
        this.incomeDescription += incomeDescription;
    }

    public int getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(int incomeAmount) {
        this.incomeAmount += incomeAmount;
    }

    public String toString() {
        return
                "income='" + incomeAmount+ '\'' +
                ", description='" + incomeDescription + '\''
                ;
    }

}


