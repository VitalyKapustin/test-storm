package com.insart.titanium.repository.custom;

import com.insart.titanium.storm.entity.GenericEntity;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
public interface GenericRepositoryCustom<T extends GenericEntity, ID> {

    public T saveEntity(T entity);
}
