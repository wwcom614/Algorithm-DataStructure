package com.ww.algorithm.Heap;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//https://leetcode-cn.com/problems/top-k-frequent-elements/
public class TopKFrequency {

    public List<Integer> getTopKFrequent(int[] nums, int k){

        //将数组转化为元素为key，出现次数为value的Map
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int key : nums){
            if(treeMap.containsKey(key)){
                treeMap.put(key, treeMap.get(key)+1);
            }else {
                treeMap.put(key, 1);
            }
        }

        //基于自己写的最大堆实现的优先队列
        PriortyQueue<Freq> priortyQueue = new PriortyQueue<>();
        //遍历上述构造好的元素为key，出现次数为value的Map的元素们keys
        for (int key: treeMap.keySet()){
            //优先队列中如果少于k个元素，元素直接入队
            if(priortyQueue.getSize() < k){
                priortyQueue.enqueue(new Freq(key, treeMap.get(key)));
            //优先队列中已有k个元素，又来一个新的元素，那么用优先队列头部(最大堆root节点元素)--优先队列中目前优先级最低的元素，
            //与新来的元素对比，如果新来的元素优先级更高(出现次数freq值更大)
            }else if(treeMap.get(key) > priortyQueue.getFront().freq){
                //优先队列头部元素出队--removeMax方法删除最大堆root节点元素
                priortyQueue.dequeue();
                //新来的元素入队--add(e)方法最大堆插入元素
                priortyQueue.enqueue(new Freq(key ,treeMap.get((key))));
            }
        }

        //遍历完成后，优先队列中就是所需的TopK元素，存储到链表中返回
        LinkedList<Integer> list = new LinkedList<>();
        while (! priortyQueue.isEmpty()){
            list.add(priortyQueue.dequeue().key);
        }
        return list;
    }

    //优先队列中的元素需要有可比性，将做好元素频率统计的Map转化为Freq对象，并重写其compareTo方法：自定义比较大小的方法
    private class Freq implements Comparable<Freq>{
        private int key;
        private int freq;

        //构造方法
        public Freq(int key, int freq){
            this.key = key;
            this.freq = freq;
        }

        //因为引入的优先队列是基于自己写的最大堆实现的，最终前K个元素组成的最大堆中优先级最高
        // 但优先队列每次堆中最先被比较替换的是堆顶元素--本堆中最大值，而堆中其他元素优先级要高于该元素。
        // 所以要自定义堆顶最大值元素其优先级在最大堆中最低才行：最大堆中可比较值越大优先级越低，最大堆中可比较值越小优先级越高
        @Override
        public int compareTo(Freq another) {
            //freq频次低，优先级高
            if(this.freq < another.freq){
                return 1;
            //freq频次高，优先级低
            }else if(this.freq > another.freq){
                return -1;
            }else {
                return 0;
            }
        }
    }
}
