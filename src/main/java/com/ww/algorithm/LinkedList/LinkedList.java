package com.ww.algorithm.LinkedList;

public class LinkedList<E> {
    //链表是由节点构成的，所以首先构造节点Node内部类。
    // private对外屏蔽链表内部的节点信息和实现细节
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

    //在链表头插入元素 与 在链表其他位置插入元素逻辑上有区别。
    //在链表头部head之前设置一个虚拟头结点dummyHead来解决该问题
    private Node dummyHead;
    private int size;

    public LinkedList() {
        //即使链表为空，也存在一个节点--虚拟头结点
        dummyHead = new Node(null, null);
        size = 0;
    }

    //获取链表中元素个数
    public int getSize() {
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //时间复杂度O(n)
    //思维练习：在链表index位置增加一个元素e
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Ileagal index");
        }
        /* 技巧：使用虚拟头结点dummyHead，就可以使用同样的逻辑在任意位置插入元素了，包括头结点之前
        if(index == 0){
            addFirst(e);
        }else {*/
        //从链表头head开始移动，一直找到index前一个位置的node
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        /*  Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;*/
        //更优雅精巧的一种写法，1行相当于上面3行代码
        prev.next = new Node(e, prev.next);
        size++;
    }

    //时间复杂度O(1)
    //在链表头head添加新的元素e，在链表head添加元素方便
    public void addFirst(E e) {
/*        //将新元素加入一个node
        Node node = new Node(e);
        //新增加的node的next指向原来的head
        node.next = head;
        //新增加的node自己成为head
        head = node;*/
        //更优雅精巧的一种写法，1行相当于上面3行代码
        /*head = new Node(e, head);
        size++;*/

        //使用虚拟头结点dummyHead，就可以使用同样的逻辑在任意位置插入元素了，包括头结点之前
        add(0, e);
    }

    //未使用尾指针tail，此时时间复杂度O(n)
    //在链表末尾增加一个元素e
    public void addLast(E e) {
        add(size, e);
    }

    //时间复杂度O(n)
    //思维练习：在链表index位置删除指定索引位置的node，并返回该node的元素值e
    public E remove(int index){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. Ileagal index");
        }
        Node prev = dummyHead;
        for(int i=0; i<index; i++){
            prev = prev.next;
        }

        //将要删除的节点prev.next记录下来，方法返回该值
        Node delNode = prev.next;

        //删除节点delNode：prev节点跳过delNode，指向delNode的下个节点
        prev.next = delNode.next;
        //便于JVM回收删除的节点
        delNode.next = null;
        size--;
        return delNode.e;
    }

    //时间复杂度O(1)
    //从链表中删除首个元素，并返回首个元素
    public E removeFirst(){
        return remove(0);
    }

    //时间复杂度O(n)
    //从链表中删除首个值为e的节点
    public void removeFirstElement(E e){
        Node prev = dummyHead;
        while (prev.next != null){
            if(e.equals(prev.next.e)){
                break;//找到了，停止遍历
            }else {
                prev = prev.next;//没找到，继续遍历
            }
        }

        if(prev.next != null){
            Node delNode = prev.next;//记录被删除的节点
            prev.next = delNode.next;//链表跳过被删除的的节点
            delNode.next = null;//JVM删除被删除的节点引用
        }
    }

    //时间复杂度O(n)
    //从链表中删除末尾元素，并返回末尾元素
    public E removeLast(){
        return remove(size-1);
    }

    //时间复杂度O(n)
    //思维练习：获取链表index位置的元素e
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Ileagal index");
        }
        Node cur = dummyHead.next;
        for(int i=0; i<index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    //时间复杂度O(1)
    //获得链表首个元素
    public E getFirst(){
        return get(0);
    }

    //时间复杂度O(n)
    //获得链表末尾元素
    public E getLast(){
        return get(size-1);
    }

    //时间复杂度O(n)
    //思维练习：修改链表index位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Ileagal index");
        }
        Node cur = dummyHead.next;
        for(int i=0; i<index; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    //时间复杂度O(n)
    //查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        //while循环遍历链表
        while (cur!= null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("size=%d \n", getSize()));
        //for循环遍历链表
        for(Node cur = dummyHead.next; cur != null; cur = cur.next){
            sb.append(cur + "->");
        }
        sb.append("null");
        return sb.toString();
    }
}
