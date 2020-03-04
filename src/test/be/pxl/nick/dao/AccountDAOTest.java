package be.pxl.nick.dao;

import be.pxl.nick.entity.Account;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountDAOTest {

    @Test
    public void testAccountInsert(){
        Account account = new Account();
        account.setIBAN("testIBAN");
        account.setName("testName");

        AccountDAO accountDAO=new AccountDAO("jdbc:mariadb://localhost:3306/budgetplanner?useSSL=false", "root", "admin");

        Account accountInserted = accountDAO.createAccount(account);
        //assertEquals(account,accountInserted);
        assertNotEquals(0,accountInserted.getId());
        System.out.println(accountInserted.getId());
    }
}
