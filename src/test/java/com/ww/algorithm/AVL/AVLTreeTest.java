package com.ww.algorithm.AVL;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class AVLTreeTest {
    @Test
    public void testAVLTree() throws Exception {
        String[] strings = {"a", "b", "c", "d", "c", "b", "c"};
        AVLTree<String, Integer> avlTree = new AVLTree<>();
        for (String word : strings) {
            if (avlTree.contains(word)) {
                avlTree.set(word, avlTree.get(word) + 1);
            } else {
                avlTree.add(word, 1);
            }
        }
        for (String word : strings) {
            log.info("【avlTree】：" + word + "--" + avlTree.get(word));
        }
        //【avlTree】：a--1
        //【avlTree】：b--2
        //【avlTree】：c--3
        //【avlTree】：d--1
        log.info("【Different K-V】："+ avlTree.getSize());
        //【Different K-V】：4

        log.info("【add-isBST】："+ avlTree.isBST());
        //【add-isBST】：true
        log.info("【add-isBalanced】："+ avlTree.isBalanced());
        //【add-isBalanced】：true

        avlTree.remove("b");
        log.info("【remove-isBST】："+ avlTree.isBST());
        //【remove-isBST】：true
        log.info("【remove-isBalanced】："+ avlTree.isBalanced());
        //【remove-isBalanced】：true
    }

}