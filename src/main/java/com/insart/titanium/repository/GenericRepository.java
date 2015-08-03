package com.insart.titanium.repository;

import com.couchbase.client.protocol.views.Query;
import com.insart.titanium.repository.custom.GenericRepositoryCustom;
import com.insart.titanium.storm.entity.GenericEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by v.kapustin on 8/3/15.
 */
public interface GenericRepository extends CrudRepository<GenericEntity, String>, GenericRepositoryCustom<GenericEntity, String> {

    public List<GenericEntity> findByDate(Query query);
}
