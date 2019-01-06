package com.ww.algorithm.Map;

public class LinkedListMap<K, V> implements Map<K, V> {

    //链表是由节点构成的，所以首先构造节点Node内部类。
    // private对外屏蔽链表内部的节点信息和实现细节
    private class Node {
        //public本类中可直接使用，省的写get/set方法了
        public K key;
        public V value;
        public Node next;

        //全参构造方法
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //1个参数构造方法
        public Node(K key) {
            this(key, null, null);
        }

        //无参构造方法
        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }

    }

    //在链表头插入元素 与 在链表其他位置插入元素逻辑上有区别。
    //在链表头部head之前设置一个虚拟头结点dummyHead来解决该问题
    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    //时间复杂度O(n)
    //定义封装一个内部方法：输入key，找到该key所在的节点
    private Node getNode(K key) {
        Node cur = dummyHead;
        while (cur != null) {
            if (key.equals(cur.key)) {
                return cur;
            } else {
                cur = cur.next;
            }
        }
        //如果一直找到链表最后也没找到该key值，返回null
        return null;
    }

    //时间复杂度O(n)
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    //时间复杂度O(n)
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    //时间复杂度O(n)
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {//如果Map中不存在该key，创建node节点，在链表头部直接增加
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }else {//如果Map中存在该key，更新其value值
            node.value = value;
        }
    }

    //时间复杂度O(n)
    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {//如果Map中不存在该key，无法set报错
            throw new IllegalArgumentException(key + " doesn't exist in Map!");
        }else {//如果Map中存在该key，更新其value值
            node.value = value;
        }
    }


    //时间复杂度O(n)
    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null){
            if(key.equals(prev.next.key)){
                break;//找到了，停止遍历
            }else {
                prev = prev.next;//没找到，继续遍历
            }
        }

        if(prev.next != null){
            Node delNode = prev.next;//记录被删除的节点
            prev.next = delNode.next;//链表跳过被删除的的节点
            delNode.next = null;//JVM删除被删除的节点引用
            size--;
            return delNode.value;
        }
        return null;
    }



    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
