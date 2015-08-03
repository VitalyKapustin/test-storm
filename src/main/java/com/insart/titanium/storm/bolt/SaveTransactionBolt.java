package com.insart.titanium.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import com.insart.titanium.storm.entity.Transaction;
import com.insart.titanium.storm.util.ApplicationContextUtils;
import com.insart.titanium.storm.util.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
@Component
public class SaveTransactionBolt extends BaseRichBolt {

    private static final Logger LOG = Logger.getLogger(PeriodTransactionsBolt.class);

    private OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Transaction transaction = (Transaction) tuple.getValueByField("transaction");
        ApplicationContextUtils.getGenericRepository().saveEntity(transaction);
        LOG.debug(CommonUtils.getLogMessage(SaveTransactionBolt.class, transaction));
        _collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
