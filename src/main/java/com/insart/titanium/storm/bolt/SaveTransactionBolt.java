package com.insart.titanium.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import com.insart.titanium.configuration.ApplicationContextProvider;
import com.insart.titanium.repository.GenericRepository;
import com.insart.titanium.storm.entity.Transaction;
import com.insart.titanium.storm.util.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
public class SaveTransactionBolt extends BaseRichBolt {

    private final static Logger LOG = Logger.getLogger(SaveTransactionBolt.class);

    private OutputCollector _collector;

    @Autowired
    private GenericRepository genericRepository;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        ApplicationContextProvider.autowireObject(this);

        Transaction transaction = (Transaction) tuple.getValueByField("transaction");
        genericRepository.saveEntity(transaction);
        LOG.info(CommonUtils.getLogMessage(SaveTransactionBolt.class, transaction));
        _collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
