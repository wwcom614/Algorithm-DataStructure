package com.ww.algorithm.Map;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class BSTMapTest {

    //基于以自己用BST实现的Map，实现一个词频统计功能。
    @Test
    public void testBSTMap() throws Exception {
        String[] strings = {"a", "b", "c", "d", "c", "b", "c"};
        BSTMap<String, Integer> bstMap = new BSTMap<>();

        for (String word : strings) {
            if (bstMap.contains(word)) {
                bstMap.set(word, bstMap.get(word) + 1);
            } else {
                bstMap.add(word, 1);
            }
        }
        for (String word : strings) {
            log.info("【bstMap】：" + word + "--" + bstMap.get(word));
        }
        //【bstMap】：a--1
        //【bstMap】：b--2
        //【bstMap】：c--3
        //【bstMap】：d--1
        log.info("【Different K-V】："+ bstMap.getSize());
        //【Different K-V】：4


    }

}