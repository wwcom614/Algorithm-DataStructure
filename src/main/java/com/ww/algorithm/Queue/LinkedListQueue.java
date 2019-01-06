package com.ww.algorithm.Queue;

public class LinkedListQueue<E> implements Queue<E> {

    //构造节点Node内部类：private对外屏蔽链表内部的节点信息和实现细节
    private class Node {
        //public本类中可直接使用，省的写get/set方法了
        public E e;
        public Node next;

        //全参构造方法
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        //1个参数构造方法
        public Node(E e) {
            this(e, null);
        }

        //无参构造方法
        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    //无参构造方法
    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*链表的head端插入、head端删除元素都容易。
    如果基于链表实现队列，需要增加尾指针tail。
    tail端插入元素容易，但tail端删除元素不容易。
    所以：
    从tail端插入元素--队尾enqueue。
    从head端删除元素--队首dequeue。
    因为不涉及在中间插入node，所以不引入dummyHead。
    要注意链表为空的情况。*/
    @Override
    public void enqueue(E e) {
        //单独考虑链表为空情况
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }else {
            //入队增加：当前tailnext赋值为新加入的节点
            tail.next = new Node(e);
            //tail向后移动一个节点
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        //单独考虑链表为空情况
        if(isEmpty()){
            throw new IllegalArgumentException("Dequeue failed, because the queue is empty!");
        }
        //把head节点记录下来
        Node delNode = head;
        //出队删除：head节点向后移动一个节点
        head = head.next;
        //便于JVM回收删除的节点
        delNode.next = null;

        //如果链表只有1个元素，出队后链表为空，此时head和tail都要为null才行
        if(head == null){
            tail = null;
        }
        size--;

        return delNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("getFront failed, because the queue is empty!");
        }
        return head.e;
    }

    //重写toString方法，打印结果便于测试验证
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //标注队首负责入队
        sb.append(String.format("LinkedListQueue: size=%d, Front[", getSize()));
        //for循环遍历链表
        for(Node cur = head; cur != null; cur = cur.next){
            sb.append(cur + "->");
        }
        //标注队尾负责入队
        sb.append("null]Tail");
        return sb.toString();
    }
}
