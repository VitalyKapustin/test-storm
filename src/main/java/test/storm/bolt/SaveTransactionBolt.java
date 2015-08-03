package test.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.repository.TransactionRepository;
import test.storm.entity.Transaction;

import java.util.Map;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
@Component
public class SaveTransactionBolt extends BaseRichBolt {

    private static final Logger LOG = Logger.getLogger(PeriodTransactionsBolt.class);

    private OutputCollector _collector;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Transaction transaction = (Transaction) tuple.getValueByField("transaction");
        transactionRepository.save(transaction);
        _collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
