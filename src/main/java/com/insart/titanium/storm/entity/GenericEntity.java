package com.insart.titanium.storm.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by VLKxCOOL on 8/1/15.
 */
public class GenericEntity implements Serializable {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
