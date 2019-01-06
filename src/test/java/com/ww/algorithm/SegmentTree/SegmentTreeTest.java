package com.ww.algorithm.SegmentTree;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class SegmentTreeTest {


    @Test
    public void testSegmentTree() throws Exception {
        Integer[] array = {-2, 0, 3, -5, 2, -1};
        //调用者实现融合器业务逻辑：两个子节点数据之和作为父节点数据
        SegmentTree<Integer> segmentTree = new SegmentTree<>(array, (a, b) -> a + b);
        log.info("【segmentTree】:" + segmentTree);
        //【segmentTree】:[-3,1,-4,-2,3,-3,-1,-2,0,null,null,-5,2,null,null,null,null,null,null,null,null,null,null,null]

        log.info("【querySeg(0,2)】:" + segmentTree.querySeg(0, 2));
        //【querySeg(0,2)】:1

        log.info("【querySeg(2,5)】:" + segmentTree.querySeg(2, 5));
        //【querySeg(2,5)】:-1

        log.info("【querySeg(0,5)】:" + segmentTree.querySeg(0, 5));
        //【querySeg(0,5)】:-3
    }
}