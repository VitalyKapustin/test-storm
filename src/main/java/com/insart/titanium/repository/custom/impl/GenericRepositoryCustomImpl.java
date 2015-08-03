package com.insart.titanium.repository.custom.impl;

import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.insart.titanium.repository.custom.GenericRepositoryCustom;
import com.insart.titanium.storm.entity.GenericEntity;
import com.insart.titanium.storm.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
@Repository
public class GenericRepositoryCustomImpl implements GenericRepositoryCustom<GenericEntity, String> {

    @Autowired
    private CouchbaseTemplate template;

    private final static String KEY_ID = "EVENT_ID";

    @Override
    public GenericEntity saveEntity(GenericEntity entity) {
        entity.setId(String.valueOf(template.getCouchbaseClient().incr(KEY_ID, 1, 1)));
        template.save(entity);
        return entity;
    }
}
