package test.storm.bolt;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.storm.entity.Transaction;
import test.storm.repository.FakeTransactionRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by v.kapustin on 7/31/15.
 */
@Component
public class PeriodTransactionsBolt extends BaseRichBolt {

    private static final Logger LOG = Logger.getLogger(PeriodTransactionsBolt.class);

    private OutputCollector _collector;

    @Autowired
    private FakeTransactionRepository repository;


    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        List<Transaction> periodTransactions = repository.listByDatePeriod(DateTime.now().minusDays(9).toDate(), new Date());
        _collector.emit(input, new Values(input.getValues().get(0), periodTransactions));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("transaction", "periodTransactions"));
    }
}
