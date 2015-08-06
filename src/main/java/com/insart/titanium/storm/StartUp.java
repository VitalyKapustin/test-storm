package com.insart.titanium.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import com.insart.titanium.storm.bolt.SaveTransactionBolt;
import com.insart.titanium.storm.spout.TransactionSpout;

/**
 * Created by v.kapustin on 7/30/15.
 */
public class StartUp {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("transactions", new TransactionSpout(), 4);
        builder.setBolt("saveTransaction", new SaveTransactionBolt(), 6).shuffleGrouping("transactions");
//        builder.setBolt("periodTransactions", new PeriodTransactionsBolt(), 3).shuffleGrouping("transactions");
//        builder.setBolt("transactionAmountByCustomer", new TransactionAmountByCustomerBolt(), 7).shuffleGrouping("periodTransactions");
//        builder.setBolt("transactionAmountByBank", new TransactionAmountByBankBolt(), 7).shuffleGrouping("periodTransactions");
//        builder.setBolt("transactionAmountByAccount", new TransactionAmountByAccountBolt(), 7).shuffleGrouping("periodTransactions");
//        builder.setBolt("logTransactionSum", new LogTransactionSumBolt(), 10)
//                .shuffleGrouping("transactionAmountByCustomer")
//                .shuffleGrouping("transactionAmountByBank")
//                .shuffleGrouping("transactionAmountByAccount");

        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(2);

//        try {
//            StormSubmitter.submitTopology("com", conf, builder.createTopology());
//        } catch (AlreadyAliveException e) {
//            e.printStackTrace();
//        } catch (InvalidTopologyException e) {
//            e.printStackTrace();
//        }

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("com", conf, builder.createTopology());
        Utils.sleep(1000 * 60 * 10);
        localCluster.killTopology("com");
        localCluster.shutdown();
    }
}
