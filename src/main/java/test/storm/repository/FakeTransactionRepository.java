package test.storm.repository;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import test.storm.entity.Transaction;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by v.kapustin on 7/31/15.
 */
@Service
public class FakeTransactionRepository implements Serializable {

    public List<Transaction> all() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Customer_1", "Account_1", "Bank_2", 5.0, DateTime.now().minusDays(1).toDate()));
        transactions.add(new Transaction("Customer_1", "Account_1", "Bank_1", 15.0, DateTime.now().minusDays(2).toDate()));
        transactions.add(new Transaction("Customer_2", "Account_2", "Bank_1", 70.0, DateTime.now().minusDays(3).toDate()));
        transactions.add(new Transaction("Customer_1", "Account_3", "Bank_3", 10.0, DateTime.now().minusDays(5).toDate()));
        transactions.add(new Transaction("Customer_1", "Account_4", "Bank_2", 90.0, DateTime.now().minusDays(8).toDate()));
        transactions.add(new Transaction("Customer_3", "Account_5", "Bank_3", 90.0, DateTime.now().minusDays(9).toDate()));
        transactions.add(new Transaction("Customer_4", "Account_5", "Bank_2", 20.0, DateTime.now().minusDays(9).toDate()));
        transactions.add(new Transaction("Customer_5", "Account_3", "Bank_2", 100.0, DateTime.now().minusDays(10).toDate()));
        transactions.add(new Transaction("Customer_2", "Account_2", "Bank_1", 20.0, DateTime.now().minusDays(11).toDate()));
        transactions.add(new Transaction("Customer_1", "Account_1", "Bank_1", 60.0, DateTime.now().minusDays(11).toDate()));
        return transactions;
    }

    public List<Transaction> listByDatePeriod(Date fromDate, Date toDate) {
        return all().parallelStream().filter(p -> p.getDate().after(fromDate) && p.getDate().before(new DateTime(toDate).plusDays(1).toDate())).collect(Collectors.toList());
    }
}
