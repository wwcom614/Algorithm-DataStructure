package com.ww.algorithm.Stack;

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
public class LinkedListStackTest {

    private static LinkedListStack<Integer> linkedListStack;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        linkedListStack = new LinkedListStack<>();
        for(int i=0; i<6; i++){
            linkedListStack.push(i);
        }
        log.info("【linkedListStack】:" + linkedListStack);
    }//【linkedListStack】:size=6
    // 5->4->3->2->1->0->null



    @Test
    public void b_getSize() throws Exception {
        log.info("【getSize】:" + linkedListStack.getSize());
    }//【getSize】:6

    @Test
    public void c_isEmpty() throws Exception {
        log.info("【isEmpty】:" + linkedListStack.isEmpty());
    }//【isEmpty】:false

    @Test
    public void d_peek() throws Exception {
        log.info("【peek】:" + linkedListStack.peek());
    }//【peek】:5

    @Test
    public void e_pop() throws Exception {
        log.info("【pop】:" + linkedListStack.pop());
        log.info("【pop】:" + linkedListStack.pop());
        log.info("【pop】:" + linkedListStack.pop());
        log.info("【pop】:" + linkedListStack.pop());
        log.info("【pop】:" + linkedListStack);
    }//【pop】:5
    //【pop】:4
    //【pop】:3
    //【pop】:2
    //pop】:size=2 1->0->null

    @Test
    public void f_peek() throws Exception {
        log.info("【peek】:" + linkedListStack.peek());
    }//【peek】:1

}