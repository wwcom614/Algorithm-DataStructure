package com.ww.algorithm.Heap;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//https://leetcode-cn.com/problems/top-k-frequent-elements/
public class TopKFrequencyImprove {

    public List<Integer> getTopKFrequentImprove(int[] nums, int k) {

        //将数组转化为元素为key，出现次数为value的Map
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int key : nums) {
            if (treeMap.containsKey(key)) {
                treeMap.put(key, treeMap.get(key) + 1);
            } else {
                treeMap.put(key, 1);
            }
        }

        //使用java.util.PriorityQueue，默认使用最小堆实现(前K最终个元素组成的最小堆中优先级最高)，且支持用户自定义Comparator比较器作为入参
        // 优先队列每次堆中最先被比较替换的是堆顶元素--本堆中最小值，而堆中其他元素优先级要高于该元素。
        // 所以Comparator比较器(内部类使用Lambda表达式)中需重写Integer大小定义：Map中key对应的value(出现次数)越大优先级越高，key对应的value越小优先级越低
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (a, b) -> treeMap.get(a) - treeMap.get(b)
        );

        //遍历上述构造好的元素为key，出现次数为value的Map的元素们keys
        for (int key : treeMap.keySet()) {
            //优先队列中如果少于k个元素，元素直接入队
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
                //优先队列中已有k个元素，又来一个新的元素，那么用优先队列头部(最小堆root节点元素)--优先队列中目前优先级最低的元素，
                //与新来的元素对比，如果新来的元素优先级更高(出现次数值更大)
            } else if (treeMap.get(key) > treeMap.get(priorityQueue.peek())) {
                //优先队列头部元素出队--remove方法删除最小堆root节点元素
                priorityQueue.remove();
                //新来的元素入队--add(e)方法最小堆插入元素
                priorityQueue.add(key);
            }
        }

        //遍历完成后，优先队列中就是所需的TopK元素，存储到链表中返回
        LinkedList<Integer> list = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            list.add(priorityQueue.remove());
        }
        return list;
    }
}
