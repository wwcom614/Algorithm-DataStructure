package com.ww.algorithm.Recursion;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArraySumTest {
    @Test
    public void arraySumTest() throws Exception {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        log.info("【arraySum】：" + ArraySum.ArraySum(array));
    }//【arraySum】：55


}