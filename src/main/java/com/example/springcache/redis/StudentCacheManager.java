package com.example.springcache.redis;

import com.example.springcache.model.Student;

public interface StudentCacheManager {
    void cacheStudentDetails(Student student);
    boolean checkEmpty();
}
