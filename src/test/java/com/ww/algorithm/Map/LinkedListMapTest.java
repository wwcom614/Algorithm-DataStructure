package com.ww.algorithm.Map;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkedListMapTest {

    //基于以自己用链表实现的Map，实现一个词频统计功能。
    @Test
    public void testLinkedListMap() throws Exception {
        String[] strings = {"a", "b", "c", "d", "c", "b", "c"};
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();

        for (String string : strings) {
            if (linkedListMap.contains(string)) {
                linkedListMap.set(string, linkedListMap.get(string) + 1);
            } else {
                linkedListMap.add(string, 1);
            }
        }
        for (String string : strings) {
            log.info("【linkedListMap】：" + string + "--" + linkedListMap.get(string));
        }
        //【linkedListMap】：a--1
        //【linkedListMap】：b--2
        //【linkedListMap】：c--3
        //【linkedListMap】：d--1
        log.info("【Different K-V】："+ linkedListMap.getSize());
        //【Different K-V】：4


    }

}