package com.example.springcache.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = 4737929151590000928L;
    
    String id;
    String name;
    String clz;

    public Student(String id, String name, String clz) {
        this.id = id;
        this.name = name;
        this.clz = clz;
    }
}
