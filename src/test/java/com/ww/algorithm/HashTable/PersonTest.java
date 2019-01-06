package com.ww.algorithm.HashTable;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Test
    public void testFirstUniqChar() throws Exception {
        //Java中默认使用对象的引用地址做hashcode，比较的是引用地址，new出来2个对象，hashCode一定不同也就一定不等。
        //自定义重写Person对象的hashCode方法，忽略字符大小写，对值进行哈希。
        Person WW = new Person("WANGWEI", 38, 43210.56);
        Person ww = new Person("wangwei", 38, 43210.56);
        log.info("【WW.hashCode】：" + WW.hashCode());
        //【WW.hashCode】：2048140999
        log.info("【ww.hashCode】：" + ww.hashCode());
        //【ww.hashCode】：2048140999

        HashSet<Person> personHashSet = new HashSet<>();
        personHashSet.add(WW);
        personHashSet.add(ww);
        log.info("【personHashSet.size】：" + personHashSet.size());
        //【personHashSet.size】：1

        HashMap<Person, Integer> personIntegerHashMap = new HashMap<>();
        personIntegerHashMap.put(WW, 1);
        personIntegerHashMap.put(ww, 1);
        log.info("【personIntegerHashMap.size】：" + personIntegerHashMap.size());
        //【personIntegerHashMap.size】：1
    }


}