package com.ww.algorithm.Heap;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaxHeapTest {

    @Test
    public void testMaxHeapFunction() {
        //定义最大堆中元素数量
        int n = 10000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();

        //向最大堆中压入n个元素，压在末尾，再逐步siftUp恢复最大堆性质，时间复杂度O(nlogn)
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        //从最大堆中取出n个元素，时间复杂度O(nlogn)
        // 每次按照优先队列场景取最大的，也就是从堆头取出删除，堆尾节点补位后再逐步siftDown恢复最大堆性质
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = maxHeap.removeMax();
        }

        //取出的元素值存入int[] array，应该是从大到小排序的，验证是否如此
        for (int i = 0; i < n - 1; i++) {
            if ( array[i] < array[i + 1]){
                throw new IllegalArgumentException("MaxHeap has something wrong!");
            }
        }
    }

    //直接从无到有逐个添加元素构造最大堆(时间复杂度O(nlogn))
    //与
    //heapify方式基于数组构造最大堆(时间复杂度O(n))
    //对比分析性能
    @Test
    public void testMaxHeapPerf() {
        //定义待压入最大堆中元素数量
        int n = 10000000;
        //构造入参数组
        Random random = new Random();
        Integer[] testArr = new Integer[n];
        for (int i=0; i<n; i++){
            testArr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        log.info("【directAddHeapTime】：" + testAddHeapifyPerf(testArr, false));
        //【directAddHeapTime】：8.8225958

        log.info("【heapifyTime】：" + testAddHeapifyPerf(testArr, true));
        //【heapifyTime】：0.8199775
    }

    //封装一个方法：分别使用heapify方式或直接增加方式，基于testArr测试数组add元素构建最大堆
    private static double testAddHeapifyPerf(Integer[] testArr, boolean isHeapify){
        //记录测试开始时间
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify){//heapify方式
            maxHeap = new MaxHeap<>(testArr);
        }else {//直接增加方式
            maxHeap = new MaxHeap<>();
            for (int i : testArr){
                maxHeap.add(i);
            }
        }

        //记录测试结束时间
        long endTime = System.nanoTime();
        //纳秒转换为秒、long转double
        return (endTime - startTime) / 1000000000.0;
    }
}