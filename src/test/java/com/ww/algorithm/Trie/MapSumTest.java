package com.ww.algorithm.Trie;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapSumTest {
    @Test
    public void testMapSum() throws Exception {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        log.info("【sum(ap)-1】："+ mapSum.sum("ap"));
        //【sum(ap)-1】：3

        mapSum.insert("app", 2);
        log.info("【sum(ap)-2】："+ mapSum.sum("ap"));
        //【sum(ap)-2】：5
    }


}