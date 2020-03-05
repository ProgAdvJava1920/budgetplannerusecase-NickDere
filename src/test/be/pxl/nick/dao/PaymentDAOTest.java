package be.pxl.nick.dao;

import be.pxl.nick.entity.Account;
import be.pxl.nick.entity.Payment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.assertNotEquals;

public class PaymentDAOTest {
    @Test
    public void testPaymentInsert() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse("Sun Feb 16 05:21:40 CET 2020", formatter);
        Payment payment = new Payment(localDateTime, 885.08F, "EUR", "Quas accusantium quia sed id repudiandae tempora.");
        payment.setAccountId(1);
        payment.setCounterAccountId(2);
        payment.setLabelId(1);


        PaymentDAO paymentDAO = new PaymentDAO("jdbc:mariadb://localhost:3306/budgetplanner?useSSL=false", "root", "admin");

        Payment paymentInserted = paymentDAO.createPayment(payment);
        //assertEquals(account,accountInserted);
        assertNotEquals(0, paymentInserted.getId());
        System.out.println(paymentInserted.getId());
    }
}
