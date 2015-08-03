package com.insart.titanium.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.insart.titanium.storm.entity.Transaction;
import com.insart.titanium.storm.util.ApplicationContextUtils;
import com.insart.titanium.storm.util.CommonUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by v.kapustin on 7/31/15.
 */
@Component
public class PeriodTransactionsBolt extends BaseRichBolt {

    private static final Logger LOG = Logger.getLogger(PeriodTransactionsBolt.class);

    private OutputCollector _collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        Query query = new Query();
        query.setRangeStart(ComplexKey.of(DateTime.now().minusDays(9).getMillis()));
        query.setRangeEnd(ComplexKey.of(DateTime.now().getMillis()));
        LOG.debug(CommonUtils.getLogMessage(PeriodTransactionsBolt.class, (Transaction) input.getValues().get(0)));
        _collector.emit(input, new Values(input.getValues().get(0), ApplicationContextUtils.getGenericRepository().findByDate(query)));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("transaction", "periodTransactions"));
    }
}
