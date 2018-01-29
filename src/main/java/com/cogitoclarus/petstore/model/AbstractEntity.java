package com.cogitoclarus.petstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
/**
 *
 * some useful boilerplate columns applicable to all our database table objects
 * auditing/schema versioning
 **/

@Data
@MappedSuperclass
public abstract class AbstractEntity {

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "create_ts")
    private Date createTimestamp;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "last_update_ts")
    private Date lastUpdateTimestamp;

    @JsonIgnore
    @Version
    @Column(name = "version", nullable = false)
    private int version = 0;

}