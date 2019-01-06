package com.ww.algorithm.Queue;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueuePerfTest {
    //封装一个方法：测试使用某种Queue，进行opCount次enqueue和dequeue操作所需时间，单位秒
    private static double testEnqueueDequeuePerf(Queue<Integer> q, int opCount){
        //记录测试开始时间
        long startTime = System.nanoTime();
        //入队出队测试开始
        for (int i=0; i<opCount;i++){
            q.enqueue(i);
        }
        for (int i=0; i<opCount;i++){
            q.dequeue();
        }
        //记录测试结束时间
        long endTime = System.nanoTime();
        //纳秒转换为秒、long转double
        return (endTime - startTime) / 1000000000.0;
    }

    @Test
    public void testQueuePerf(){
        int opCount = 100000;

        //基于动态数组实现的ArrayQueue的入队均摊复杂度为O(1),出队均摊复杂度为O(n),
        //该测试本身是个循环，时间复杂度为O(n)
        //所以整体时间复杂度为O(n²)
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double timeArrayQueue = testEnqueueDequeuePerf(arrayQueue, opCount);
        log.info("【arrayQueue】："+ timeArrayQueue);
        //【arrayQueue】：9.2937447

        //基于循环数组实现的LoopQueue的入队均摊复杂度为O(1),出队均摊复杂度为O(1),
        //该测试本身是个循环，时间复杂度为O(n)
        //所以整体时间复杂度为O(n)
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double timeLoopQueue = testEnqueueDequeuePerf(loopQueue, opCount);
        log.info("【loopQueue】："+ timeLoopQueue);
        //【loopQueue】：0.0360282

        //基于链表实现的LinkedListQueue的入队均摊复杂度为O(1),出队均摊复杂度为O(1),
        //该测试本身是个循环，时间复杂度为O(n)
        //所以整体时间复杂度为O(n)
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double timeLinkedListQueue = testEnqueueDequeuePerf(linkedListQueue, opCount);
        log.info("【linkedListQueue】："+ timeLinkedListQueue);
        //【linkedListQueue】：0.0311501

        //loopQueue 和 LinkedListQueue 的时间复杂度都是O(1)级别，性能没有巨大差异
    }
}