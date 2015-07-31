package test.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.storm.entity.Transaction;
import test.storm.util.TransactionGenerator;

import java.util.Map;

/**
 * Created by v.kapustin on 7/30/15.
 */
@Component
public class TransactionSpout extends BaseRichSpout {

    private static int UNIQUE_CUSTOMER_QUANTITY = 5;
    private static int UNIQUE_ACCOUNT_QUANTITY = 5;
    private static int UNIQUE_BANK_QUANTITY = 3;

    private static final Logger LOG = Logger.getLogger(TransactionSpout.class);

    private SpoutOutputCollector _collector;

    @Autowired
    private TransactionGenerator transactionGenerator;

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
        Utils.sleep(100);
        Transaction transaction = transactionGenerator.generate(UNIQUE_CUSTOMER_QUANTITY, UNIQUE_ACCOUNT_QUANTITY, UNIQUE_BANK_QUANTITY);
        LOG.debug("Transaction has been generated: " + transaction);
        _collector.emit(new Values(transaction));
    }
}
