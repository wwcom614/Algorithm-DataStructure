package com.ww.algorithm.Stack;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class StackPerfTest {
    //封装一个方法：测试使用某种Queue，进行opCount次enqueue和dequeue操作所需时间，单位秒
    private static double testPushPopPerf(Stack<Integer> stack, int opCount){
        //记录测试开始时间
        long startTime = System.nanoTime();
        //入队出队测试开始
        for (int i=0; i<opCount;i++){
            stack.push(i);
        }
        for (int i=0; i<opCount;i++){
            stack.pop();
        }
        //记录测试结束时间
        long endTime = System.nanoTime();
        //纳秒转换为秒、long转double
        return (endTime - startTime) / 1000000000.0;
    }

    @Test
    public void testStackPerf(){
        int opCount = 10000000;

        //基于动态数组实现的ArrayStack的入栈均摊复杂度为O(1),出栈均摊复杂度为O(1),
        //该测试本身是个循环，时间复杂度为O(n)
        //所以整体时间复杂度为O(n)
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double timeArrayStack = testPushPopPerf(arrayStack, opCount);
        log.info("【arrayStack】："+ timeArrayStack);
        //【arrayStack】：7.587812

        //基于链表实现的LinkedListStack的入栈均摊复杂度为O(1),出栈均摊复杂度为O(1),
        //该测试本身是个循环，时间复杂度为O(n)
        //所以整体时间复杂度为O(n)
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double timeLinkedListStack = testPushPopPerf(linkedListStack, opCount);
        log.info("【linkedListStack】："+ timeLinkedListStack);
        //【linkedListStack】：19.4924735

        //arrayStack 和 LinkedListStack的时间复杂度都是O(1)级别，性能没有巨大差异
        // 因为LinkedListStack包含更多的new node的操作，数据量大时可能会慢些
    }
}