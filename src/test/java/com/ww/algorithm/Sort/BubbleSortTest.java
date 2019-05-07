package com.ww.algorithm.Sort;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class BubbleSortTest {
    @Test
    public void testBubbleSort() throws Exception {
        int[] sourceArray = new int[]{5, 7, 2, 9, 4, 1, 0, 6, 7};
        log.info("【sourceArray】" + Arrays.toString(sourceArray));
        //【sourceArray】[5, 7, 2, 9, 4, 1, 0, 6, 7]
        log.info("【BubbleSortArray】" + Arrays.toString(BubbleSort.bubbleSort(sourceArray)));
        //【BubbleSortArray】[0, 1, 2, 4, 5, 6, 7, 7, 9]
    }
}