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
public class ArrayStackTest {
    //泛型
    private static ArrayStack<Integer> arrayStack;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        arrayStack = new ArrayStack<>(5);
        for(int i=0; i<6; i++){
            arrayStack.push(i);
        }
        log.info("【arrayStack】:" + arrayStack);
    }//【arrayStack】:Queue: size=6, capacity=10, Bottom[0, 1, 2, 3, 4, 5]Top

    @Test
    public void b_getSize() throws Exception {
        log.info("【getSize】:" + arrayStack.getSize());
    }//【getSize】:6

    @Test
    public void c_isEmpty() throws Exception {
        log.info("【isEmpty】:" + arrayStack.isEmpty());
    }//【isEmpty】:false

    @Test
    public void d_getCapacity() throws Exception {
        log.info("【getCapacity】:" + arrayStack.getCapacity());
    }//【getCapacity】:10

    @Test
    public void e_peek() throws Exception {
        log.info("【peek】:" + arrayStack.peek());
    }//【peek】:5

    @Test
    public void f_pop() throws Exception {
        log.info("【pop】:" + arrayStack.pop());
        log.info("【pop】:" + arrayStack.pop());
        log.info("【pop】:" + arrayStack.pop());
        log.info("【pop】:" + arrayStack.pop());
        log.info("【pop】:" + arrayStack);
    }//【pop】:5
    //【pop】:4
    //【pop】:3
    //【pop】:2
    //【pop】:Queue: size=2, capacity=5, Bottom[0, 1]Top

    @Test
    public void g_peek() throws Exception {
        log.info("【peek】:" + arrayStack.peek());
    }//【peek】:1

}