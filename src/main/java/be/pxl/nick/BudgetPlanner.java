package be.pxl.nick;

import be.pxl.nick.entity.Account;
import be.pxl.nick.util.BudgetPlannerImporter;

import java.util.logging.Logger;

public class BudgetPlanner {
    public static void main(String[] args) {
        BudgetPlannerImporter importer=new BudgetPlannerImporter();
        Account account= importer.readFile("src\\main\\resources\\account_payments.csv");
        System.out.println(account);
    }

}
