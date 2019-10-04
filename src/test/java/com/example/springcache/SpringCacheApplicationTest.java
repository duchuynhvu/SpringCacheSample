package com.example.springcache;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springcache.model.User;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCacheApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringCacheApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                getRootUrl() + "/users", HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
        System.out.println(user.getFirstName());
        Assert.assertNotNull(user);
    }
}
