package com.aman.journalApp.service;

import com.aman.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // otherwise give exception
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd(){
//        assertEquals(4,2+2);
//        assertEquals("kumar" , userRepository.findByUserName("kumar"));
//        assertTrue(5>3);
        assertNotNull(userRepository.findByUserName("kumar"));
    }



    @Disabled
    @ParameterizedTest
    @CsvFileSource(nullValues = {
            "1,1,2",
            "1,1,2"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
