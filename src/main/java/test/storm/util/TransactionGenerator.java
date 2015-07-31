package test.storm.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import test.storm.entity.Transaction;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Created by v.kapustin on 7/30/15.
 */
@Component
public class TransactionGenerator implements Serializable {

    public Transaction generate(int uniqueCustomerQuantity, int uniqueAccountQuantity, int uniqueBankQuantity) {
        return new Transaction("Customer_" + new Random().nextInt(uniqueCustomerQuantity), "Account_" + new Random().nextInt(uniqueAccountQuantity), "Bank_" + new Random().nextInt(uniqueBankQuantity), 100.0, new Date());
    }
}
