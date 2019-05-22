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
public class QuickSortTest {

    @Resource
    QuickSort quickSort;

    @Test
    public void testQuickSort() throws Exception {
        int[] array = new int[]{5, 7, 2, 9, 4, 1, 0, 6, 7};
        log.info("【sourceArray】" + Arrays.toString(array));
        //【sourceArray】[5, 7, 2, 9, 4, 1, 0, 6, 7]

        quickSort.quickSort(array,0, array.length-1);
        log.info("【QuickSortArray】" + Arrays.toString(array));
        //【QuickSortArray】[0, 1, 2, 4, 5, 6, 7, 7, 9]
    }

}