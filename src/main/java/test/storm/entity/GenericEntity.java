package test.storm.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by VLKxCOOL on 8/1/15.
 */
public class GenericEntity implements Serializable {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
