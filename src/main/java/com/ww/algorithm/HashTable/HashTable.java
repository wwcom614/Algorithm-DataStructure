package com.ww.algorithm.HashTable;

import java.util.TreeMap;

public class HashTable<K, V> {
    //哈希冲突程度N/M值的上限，到达该值，动态数组扩容
    private static final int EXTEND_THREAD = 10;

    //哈希冲突程度N/M值的下限，到达该值，动态数组缩容
    private static final int SHRINK_THREAD = 2;

    //数学家给出的：多大数据量下，采用哪个素数M，会让哈希冲突问题更少。https://planetmath.org/goodhashtableprimes
    //哈希冲突程度N/M值的上限，到达该值，从如下数组中依次选取素数M扩容
    private final int[] capacity = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317,
            196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843,
            50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    //哈希表容量capacity的索引指示
    private int capacityIndex = 0;


    //哈希冲突的解决方法使用封闭地址的链地址法Seperate Chaining
    //简单起见，直接使用TreeMap数组做自定义哈希表每个元素的底层数据结构
    private TreeMap<K, V>[] hashTable;
    //取模素数
    private int M;
    //hashTable的大小
    private int size;

    //构造方法
    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    //自定义哈希方法：java的hashCode结果是有符号int类型(支持负数)，为了做数组索引，需要取绝对值(& 0x7fffffff)，然后素数取模
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    //向哈希表中增加元素(key,value)
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        //如果hashTable中数组索引已存在key的hash值，且其chain(TreeMap)中的确存在该key值，修改value
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {//如果hashTable中不存在该key，添加到hashTable中，size+1
            map.put(key, value);
            size++;
        }

        //平均哈希冲突N/M大于EXTEND_THREAD时，哈希表扩容到capacity数组中下一个更大的素数值，注意防数组越界
        if (size >= EXTEND_THREAD * M && capacityIndex + 1 < capacity.length) {
            capacityIndex++;
            resize(capacity[capacityIndex]);
        }
    }

    //从哈希表中删除键为key的元素，返回其value
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        //如果hashTable中数组索引已存在key的hash值，且其chain(TreeMap)中的确存在该key值，删除该元素并记录value返回
        V result = null;
        if (map.containsKey(key)) {
            result = map.remove(key);
            size--;
        }

        //平均哈希冲突N/M小于SHRINK_THREAD时，哈希表缩容到capacity数组中上一个更小的素数值，注意防数组越界
        if (size < SHRINK_THREAD * M && capacityIndex - 1 >= 0) {
            capacityIndex--;
            resize(capacity[capacityIndex]);
        }
        return result;
    }

    //改变哈希表大小
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        //先建立一个大小为newM，元素为TreeMap的数组
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        //遍历原先大小为M的哈希表
        for (int i = 0; i < oldM; i++) {
            //遍历记录原先哈希表中的元素--获取到每个TreeMap
            TreeMap<K, V> map = hashTable[i];
            //遍历该TreeMap，获取并记录每个值到新哈希表中，注意，此时哈希的M是newM，而不是oldM
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }

            //完成老哈希表到新哈希表的赋值后，将hashTable引用指向新哈希表
            this.hashTable = newHashTable;
        }
    }

    //修改哈希表中键为key的元素的值为value
    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        //如果hashTable中不存在key，报错无法set
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        } else {//如果hashTable中存在key，修改该元素的值为value
            map.put(key, value);
        }
    }

    //判断哈希表中是否存在key
    public boolean contains(K key) {
        //如果hashTable中数组索引已存在key的hash值，且其chain(TreeMap)中的确存在该key值，返回true
        //如果hashTable中不存在该key，返回false
        return hashTable[hash(key)].containsKey(key);
    }

    //获取哈希表中指定key的value
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }
}
