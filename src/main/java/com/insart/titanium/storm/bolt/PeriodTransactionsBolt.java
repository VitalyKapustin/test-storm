package com.insart.titanium.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.couchbase.client.java.query.Query;
import com.insart.titanium.configuration.ApplicationConfig;
import com.insart.titanium.repository.GenericRepository;
import com.insart.titanium.storm.entity.Transaction;
import com.insart.titanium.storm.util.CommonUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.Map;

/**
 * Created by v.kapustin on 7/31/15.
 */
public class PeriodTransactionsBolt extends BaseRichBolt {

    private OutputCollector _collector;

    @Autowired
    private GenericRepository genericRepository;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ctx.getAutowireCapableBeanFactory().autowireBean(this);

//        Query query = new Query();
//        query.setRangeStart(ComplexKey.of(DateTime.now().minusDays(9).getMillis()));
//        query.setRangeEnd(ComplexKey.of(DateTime.now().getMillis()));
        Logger LOG = Logger.getLogger(PeriodTransactionsBolt.class);
//        if (LOG == null) {
//            throw new RuntimeException("LOG is null!");
//        } else if (LOG != null) {
//            StringBuilder sb = new StringBuilder();
//            Enumeration e = LOG.getRootLogger().getAllAppenders();
//            while (e.hasMoreElements()) {
//                Appender app = (Appender) e.nextElement();
//                if (app instanceof FileAppender) {
//                    sb.append("!!!!!!!!!!!!!!!File: ").append(((FileAppender) app).getFile());
//                    sb.append("\n");
//                }
//            }
//            throw new RuntimeException(sb.toString());
//        }
        LOG.debug(CommonUtils.getLogMessage(PeriodTransactionsBolt.class, (Transaction) input.getValues().get(0)));
//        _collector.emit(input, new Values(input.getValues().get(0), genericRepository.findByDate(query)));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("transaction", "periodTransactions"));
    }
}
