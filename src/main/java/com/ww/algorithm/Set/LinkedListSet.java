package com.ww.algorithm.Set;

import com.ww.algorithm.LinkedList.LinkedList;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList linkedList;

    //构造方法
    public LinkedListSet(){
        linkedList = new LinkedList();
    }

    //时间复杂度O(n)：addFirst时间复杂度O(1)，但contains时间复杂度O(n)
    @Override
    public void add(E e) {
        //集合元素排重
        if(!linkedList.contains(e)){
            linkedList.addFirst(e);
        }
    }

    //时间复杂度O(n)：要先遍历在链表中找到这个元素
    @Override
    public void remove(E e) {
        linkedList.removeFirstElement(e);
    }

    //时间复杂度O(n)
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
