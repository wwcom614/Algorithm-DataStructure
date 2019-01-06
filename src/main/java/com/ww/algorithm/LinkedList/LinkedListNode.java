package com.ww.algorithm.LinkedList;

public class LinkedListNode {
    public int val;
    public LinkedListNode next;

    //待查找的值findValue
    public LinkedListNode(int findValue){
        this.val = findValue;
    }

    //为方便测试，写一个构造函数，将传入的数组创建一个链表。当前的ListNode作为链表的头节点返回
    public LinkedListNode(int[] array){
        if(array == null || array.length ==0){
            throw new IllegalArgumentException("Array can't be empty!");
        }
        //ListNode其实是一个节点，构造方法完成后，ListNode成为进入和访问链表的头节点
        this.val = array[0];
        LinkedListNode cur = this;
        for(int i=1; i<array.length; i++){
            cur.next = new LinkedListNode(array[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Front[");
        LinkedListNode cur = this;
        while (cur != null){
            sb.append(cur.val + "->");
            cur = cur.next;
        }
        sb.append("null]Tail");
        return sb.toString();
    }
}
