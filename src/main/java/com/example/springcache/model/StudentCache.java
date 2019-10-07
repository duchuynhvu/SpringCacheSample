package com.example.springcache.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCache implements Serializable {
    private static final long serialVersionUID = -7081460530059058527L;
    
    private Long id;
    private String firstName;
    private String lastName;

    public StudentCache(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
