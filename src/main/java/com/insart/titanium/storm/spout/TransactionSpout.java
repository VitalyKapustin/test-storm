package com.insart.titanium.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import com.insart.titanium.storm.entity.Transaction;
import com.insart.titanium.storm.util.CommonUtils;
import com.insart.titanium.storm.util.TransactionGenerator;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * Created by v.kapustin on 7/30/15.
 */
public class TransactionSpout extends BaseRichSpout {

    private final static Logger LOG = Logger.getLogger(TransactionSpout.class);

    private static int UNIQUE_CUSTOMER_QUANTITY = 5;
    private static int UNIQUE_ACCOUNT_QUANTITY = 5;
    private static int UNIQUE_BANK_QUANTITY = 3;

    private SpoutOutputCollector _collector;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("transaction"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        _collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        Utils.sleep(5000);
        Transaction transaction = new TransactionGenerator().generate(UNIQUE_CUSTOMER_QUANTITY, UNIQUE_ACCOUNT_QUANTITY, UNIQUE_BANK_QUANTITY, DateTime.now().minusDays(9).toDate(), DateTime.now().toDate());
        LOG.info(CommonUtils.getLogMessage(TransactionSpout.class, new TransactionGenerator().generate(UNIQUE_CUSTOMER_QUANTITY, UNIQUE_ACCOUNT_QUANTITY, UNIQUE_BANK_QUANTITY, DateTime.now().minusDays(9).toDate(), DateTime.now().toDate())));
        _collector.emit(new Values(transaction));
    }
}
