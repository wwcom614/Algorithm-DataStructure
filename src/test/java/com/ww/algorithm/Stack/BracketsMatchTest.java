package com.ww.algorithm.Stack;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class BracketsMatchTest {

    BracketsMatch bracketsMatch = new BracketsMatch();

    @Test
    public void isValid() throws Exception {

        String s1 = "3*(5+6)-3";
        boolean result1 = bracketsMatch.isValid(s1);
        log.info("3*(5+6)-3："+ result1);
        //  3*(5+6)-3：true

        String s2 = "3*(5+6]-3";
        boolean result2 = bracketsMatch.isValid(s2);
        log.info("3*(5+6]-3："+ result2);
        //  3*(5+6]-3：false

        String s3 = "{3*[5+6]-3";
        boolean result3 = bracketsMatch.isValid(s3);
        log.info("{3*[5+6]-3："+ result3);
        //  {3*[5+6]-3：false
    }

}