package com.example.springcache.model.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class ModelAudit implements Serializable {
    private static final long serialVersionUID = 2121120110467539510L;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public ModelAudit() {}
}
