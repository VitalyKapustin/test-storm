package com.insart.titanium.storm.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import com.insart.titanium.storm.entity.Transaction;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Created by v.kapustin on 7/30/15.
 */
@Component
public class TransactionGenerator implements Serializable {

    public Transaction generate(int uniqueCustomerQuantity, int uniqueAccountQuantity, int uniqueBankQuantity, Date fromDate, Date toDate) {
        int dateRangeLength = Days.daysBetween(new DateTime(fromDate), new DateTime(toDate)).getDays();
        return new Transaction("Customer_" + new Random().nextInt(uniqueCustomerQuantity),
                "Account_" + new Random().nextInt(uniqueAccountQuantity),
                "Bank_" + new Random().nextInt(uniqueBankQuantity),
                100.0,
                new DateTime(fromDate).plusDays(new Random().nextInt(dateRangeLength)).toDate());
    }
}
