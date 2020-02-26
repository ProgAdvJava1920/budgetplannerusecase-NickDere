package be.pxl.nick;

import be.pxl.nick.entity.Account;
import be.pxl.nick.util.BudgetPlannerImporter;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertEquals;

public class BudgetPlannerImporterTest {

    private Account account;

    @BeforeEach
    public void init() {
        BudgetPlannerImporter importer = new BudgetPlannerImporter();
        account = importer.readFile("src\\main\\resources\\account_payments.csv");
    }

    @Test
    public void testIfReadFileGivesCorrectAmountOfPayments() {
        assertEquals(account.getPayments().size(), 100);
    }
}
