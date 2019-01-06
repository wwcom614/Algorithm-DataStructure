package com.ww.algorithm.RBTree;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class RBTreeTest {
    @Test
    public void testAVLTree() throws Exception {
        String[] strings = {"a", "b", "c", "d", "c", "b", "c"};
        RBTree<String, Integer> rbTree = new RBTree<>();
        for (String word : strings) {
            if (rbTree.contains(word)) {
                rbTree.set(word, rbTree.get(word) + 1);
            } else {
                rbTree.add(word, 1);
            }
        }
        for (String word : strings) {
            log.info("【rbTree】：" + word + "--" + rbTree.get(word));
        }
        //【rbTree】：a--1
        //【rbTree】：b--2
        //【rbTree】：c--3
        //【rbTree】：d--1
        log.info("【Different K-V】："+ rbTree.getSize());
        //【Different K-V】：4


    }
}