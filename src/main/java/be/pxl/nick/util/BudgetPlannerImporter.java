package be.pxl.nick.util;

import be.pxl.nick.entity.Account;
import be.pxl.nick.entity.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private String fileName;
    private Account account = null;

    public BudgetPlannerImporter(String fileName) {
        this.fileName = fileName;
    }

    public Account readFile() {
        List<Payment> payments = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {

                String[] splitedString = line.split(",");
                if (account == null) {
                    account = createAccount(splitedString);
                }
                Payment payment = createPayment(splitedString);

                payments.add(payment);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        account.setPayments(payments);
        return account;
    }

    private Account createAccount(String[] lines) {
        Account account = new Account();
        account.setName(lines[0]);
        account.setIBAN(lines[1]);
        return account;
    }

    private Payment createPayment(String[] lines) {
//Account name,Account IBAN,Counteraccount IBAN,Transaction date,Amount,Currency,Detail

        float amount = Float.parseFloat(lines[4]);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        LocalDateTime date = LocalDateTime.parse(lines[3],formatter);
        String currency = lines[5];
        String detail = lines[6];
        return new Payment(date,amount,currency,detail);
    }
}
