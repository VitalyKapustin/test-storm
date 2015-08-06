package com.insart.titanium.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import com.insart.titanium.storm.util.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.insart.titanium.storm.entity.Transaction;

import java.util.Map;

/**
 * Created by v.kapustin on 7/31/15.
 */
public class LogTransactionSumBolt extends BaseRichBolt {

    private static Logger LOG;

    private OutputCollector _collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        Transaction transaction = (Transaction) input.getValueByField("transaction");
        String byField = String.valueOf(input.getValueByField("byField"));
        double sum = (double) input.getValueByField("sum");
//        LOG.debug(new StringBuilder(transaction.toString()).append(" sum by ").append(byField).append(" = ").append(sum));
        LOG = Logger.getLogger(LogTransactionSumBolt.class);
        LOG.debug(CommonUtils.getLogMessage(LogTransactionSumBolt.class, transaction));
        _collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
