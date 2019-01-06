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
public class LinkedListQueueTest {

    private static LinkedListQueue<Integer> linkedListQueue;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        linkedListQueue = new LinkedListQueue<>();
        for(int i=0; i<6; i++){
            linkedListQueue.enqueue(i);
        }
        log.info("【linkedListQueue】:" + linkedListQueue);
    }//【linkedListQueue】:LinkedListQueue: size=6, Front[0->1->2->3->4->5->null]Tail

    @Test
    public void b_getSize() throws Exception {
        log.info("【getSize】:" + linkedListQueue.getSize());
    }//【getSize】:6

    @Test
    public void c_isEmpty() throws Exception {
        log.info("【isEmpty】:" + linkedListQueue.isEmpty());
    }//【isEmpty】:false

    @Test
    public void e_getFront() throws Exception {
        log.info("【getFront】:" + linkedListQueue.getFront());
    }//【getFront】:0

    @Test
    public void f_dequeue() throws Exception {
        log.info("【dequeue】:" + linkedListQueue.dequeue());
        log.info("【dequeue】:" + linkedListQueue.dequeue());
        log.info("【dequeue】:" + linkedListQueue.dequeue());
        log.info("【dequeue】:" + linkedListQueue.dequeue());
        log.info("【dequeue】:" + linkedListQueue);
    }//【dequeue】:0
    //【dequeue】:1
    //【dequeue】:2
    //【dequeue】:3
    //【dequeue】:LinkedListQueue: size=2, Front[4->5->null]Tail

    @Test
    public void g_getFront() throws Exception {
        log.info("【getFront】:" + linkedListQueue.getFront());
    }//【getFront】:4

}