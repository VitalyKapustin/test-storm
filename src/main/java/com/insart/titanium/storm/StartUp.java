package com.insart.titanium.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.insart.titanium.configuration.ApplicationConfig;
import com.insart.titanium.storm.bolt.*;
import com.insart.titanium.storm.spout.TransactionSpout;

/**
 * Created by v.kapustin on 7/30/15.
 */
@Component
public class StartUp {

    private static final Logger LOG = Logger.getLogger(StartUp.class);

    @Autowired
    private TransactionSpout transactionSpout;

    @Autowired
    private SaveTransactionBolt saveTransactionBolt;

    @Autowired
    private PeriodTransactionsBolt periodTransactionsBolt;

    @Autowired
    private TransactionAmountByCustomerBolt transactionAmountByCustomerBolt;

    @Autowired
    private TransactionAmountByBankBolt transactionAmountByBankBolt;

    @Autowired
    private TransactionAmountByAccountBolt transactionAmountByAccountBolt;

    @Autowired
    private LogTransactionSumBolt logTransactionSumBolt;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        StartUp startUp = ctx.getBean(StartUp.class);
        startUp.start();
    }

    public void start() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("transactions", transactionSpout);
        builder.setBolt("saveTransaction", saveTransactionBolt).shuffleGrouping("transactions");
        builder.setBolt("periodTransactions", periodTransactionsBolt, 3).shuffleGrouping("transactions");
        builder.setBolt("transactionAmountByCustomer", transactionAmountByCustomerBolt, 7).shuffleGrouping("periodTransactions");
        builder.setBolt("transactionAmountByBank", transactionAmountByBankBolt, 7).shuffleGrouping("periodTransactions");
        builder.setBolt("transactionAmountByAccount", transactionAmountByAccountBolt, 7).shuffleGrouping("periodTransactions");
        builder.setBolt("logTransactionSum", logTransactionSumBolt, 10)
                .shuffleGrouping("transactionAmountByCustomer")
                .shuffleGrouping("transactionAmountByBank")
                .shuffleGrouping("transactionAmountByAccount");

        Config conf = new Config();
        conf.setDebug(false);
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("com", conf, builder.createTopology());
        Utils.sleep(1000 * 60 * 10);
        localCluster.killTopology("com");
        localCluster.shutdown();
    }
}
