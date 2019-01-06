package com.ww.algorithm.Heap;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopKFrequencyTest {

    int[] nums = {1,1,1,2,2,3};

    @Test
    public void testGetTopKFrequent() throws Exception {
        TopKFrequency topKFrequency = new TopKFrequency();
        log.info("【Top2】："+ topKFrequency.getTopKFrequent(nums, 2));
        log.info("【Top1】："+ topKFrequency.getTopKFrequent(nums, 1));
    }

    @Test
    public void testGetTopKFrequentImprove() throws Exception {
        TopKFrequencyImprove topKFrequencyImprove = new TopKFrequencyImprove();
        log.info("【Top2】："+ topKFrequencyImprove.getTopKFrequentImprove(nums, 2));
        log.info("【Top1】："+ topKFrequencyImprove.getTopKFrequentImprove(nums, 1));
    }

}