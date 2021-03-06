package test.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import test.storm.entity.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by v.kapustin on 7/31/15.
 */
@Component
public class TransactionAmountByCustomerBolt extends BaseRichBolt {

    private static final Logger LOG = Logger.getLogger(PeriodTransactionsBolt.class);

    private OutputCollector _collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        Transaction transaction = (Transaction) input.getValueByField("transaction");
        List<Transaction> transactions = (List<Transaction>) input.getValueByField("periodTransactions");
        double sum = transactions.parallelStream().filter(p -> p.getCustomer().equals(transaction.getCustomer())).mapToDouble(p -> p.getAmount()).sum();
        _collector.emit(input, new Values(transaction, "customer", sum));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("transaction", "byField", "sum"));
    }
}
