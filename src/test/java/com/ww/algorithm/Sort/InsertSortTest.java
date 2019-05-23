package com.ww.algorithm.Sort;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertSortTest {

    @Resource
    InsertSort insertSort;

    @Test
    public void testBubbleSort() throws Exception {
        int[] array = new int[]{5, 7, 2, 9, 4, 1, 0, 6, 7};
        log.info("【SourceArray】" + Arrays.toString(array));
        //【SourceArray】[5, 7, 2, 9, 4, 1, 0, 6, 7]

        insertSort.insertSort(array);

        log.info("【InsertSortArray】" + Arrays.toString(array));
        //【InsertSortArray】[0, 1, 2, 4, 5, 6, 7, 7, 9]
    }
}