package com.ww.algorithm.Queue;

import lombok.extern.java.Log;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoopQueueTest {
    //泛型
    private static LoopQueue<Integer> loopQueue;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        loopQueue = new LoopQueue<>(5);
        for(int i=0; i<6; i++){
            loopQueue.enqueue(i);
        }
        log.info("【loopQueue】:" + loopQueue);
    }//【loopQueue】:Queue: size=6, capacity=10, Front[0, 1, 2, 3, 4, 5]Tail

    @Test
    public void b_getSize() throws Exception {
        log.info("【getSize】:" + loopQueue.getSize());
    }//【getSize】:6

    @Test
    public void c_isEmpty() throws Exception {
        log.info("【isEmpty】:" + loopQueue.isEmpty());
    }//【isEmpty】:false

    @Test
    public void d_getCapacity() throws Exception {
        log.info("【getCapacity】:" + loopQueue.getCapacity());
    }//【getCapacity】:10

    @Test
    public void e_getFront() throws Exception {
        log.info("【getFront】:" + loopQueue.getFront());
    }//【getFront】:0

    @Test
    public void f_dequeue() throws Exception {
        log.info("【dequeue】:" + loopQueue.dequeue());
        log.info("【dequeue】:" + loopQueue.dequeue());
        log.info("【dequeue】:" + loopQueue.dequeue());
        log.info("【dequeue】:" + loopQueue.dequeue());
        log.info("【dequeue】:" + loopQueue);
    }//【dequeue】:0
    //【dequeue】:1
    //【dequeue】:2
    //【dequeue】:3
    //【dequeue】:Queue: size=2, capacity=5, Front[4, 5]Tail

    @Test
    public void g_getFront() throws Exception {
        log.info("【getFront】:" + loopQueue.getFront());
    }//【getFront】:4

}