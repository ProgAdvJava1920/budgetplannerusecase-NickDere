package be.pxl.nick.dao;

import be.pxl.nick.entity.Account;
import be.pxl.nick.entity.Label;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class LabelDAOTest {
    @Test
    public void testLabelInsert() {
        Label label = new Label();
        label.setDescription("testDescription");
        label.setName("testName");
        LabelDAO labelDAO = new LabelDAO("jdbc:mariadb://localhost:3306/budgetplanner?useSSL=false", "root", "admin");

        Label labelInserted = labelDAO.createLabel(label);
        //assertEquals(account,accountInserted);
        assertNotEquals(0, labelInserted.getId());
        System.out.println(labelInserted.getId());
    }
}
