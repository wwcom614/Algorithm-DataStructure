package com.ww.algorithm.HashTable;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstUniqCharTest {
    @Test
    public void testFirstUniqChar() throws Exception {
        String s1 = "leetcode";
        String s2 = "loveleetcode";

        FirstUniqChar firstUniqChar = new FirstUniqChar();
        log.info("【firstUniqChar(s1)】" + firstUniqChar.firstUniqChar(s1));
        //【firstUniqChar(s1)】0

        log.info("【firstUniqChar(s2)】" + firstUniqChar.firstUniqChar(s2));
        //【firstUniqChar(s2)】2
    }

}