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
public class ArrayQueueTest {
    //泛型
    private static ArrayQueue<Integer> arrayQueue;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        arrayQueue = new ArrayQueue<>(5);
        for(int i=0; i<6; i++){
            arrayQueue.enqueue(i);
        }
        log.info("【arrayQueue】:" + arrayQueue);
    }//【arrayQueue】:Queue: size=6, capacity=10, Front[0, 1, 2, 3, 4, 5]Tail

    @Test
    public void b_getSize() throws Exception {
        log.info("【getSize】:" + arrayQueue.getSize());
    }//【getSize】:6

    @Test
    public void c_isEmpty() throws Exception {
        log.info("【isEmpty】:" + arrayQueue.isEmpty());
    }//【isEmpty】:false

    @Test
    public void d_getCapacity() throws Exception {
        log.info("【getCapacity】:" + arrayQueue.getCapacity());
    }//【getCapacity】:10

    @Test
    public void e_getFront() throws Exception {
        log.info("【getFront】:" + arrayQueue.getFront());
    }//【getFront】:0

    @Test
    public void f_dequeue() throws Exception {
        log.info("【dequeue】:" + arrayQueue.dequeue());
        log.info("【dequeue】:" + arrayQueue.dequeue());
        log.info("【dequeue】:" + arrayQueue.dequeue());
        log.info("【dequeue】:" + arrayQueue.dequeue());
        log.info("【dequeue】:" + arrayQueue);
    }//【dequeue】:0
    //【dequeue】:1
    //【dequeue】:2
    //【dequeue】:3
    //【dequeue】:Queue: size=2, capacity=5, Front[4, 5]Tail

    @Test
    public void g_getFront() throws Exception {
        log.info("【getFront】:" + arrayQueue.getFront());
    }//【getFront】:4

}