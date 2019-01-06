package com.ww.algorithm.Queue;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    //循环队列的头索引和尾索引
    private int front,tail;
    //队列中元素数量
    private int size;

    //有参构造方法(capacity是调用者设定的容量，循环队列要多预留一个元素，所以实际大小要+1)
    public LoopQueue(int capacity){
        //循环队列的tail==front重合时表示队列为空，
        // (tail+1)%capacity==front时表示队列满，需要预留(浪费)一个元素空间
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    //无参构造方法
    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        //调用者设定的容量，比实际占用的容量少1
        return data.length - 1;
    }

    //循环队列的tail==front重合时表示队列为空
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    ////均摊时间复杂度O(1)
    //操作仅仅是扩容或缩容，不涉及出队和入队
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i=0; i<size; i++){
            //重要，原循环队列数组，从front开始，逐个复制到新数组中。考虑队尾循环，要取模。
            newData[i] = data[(i+front) % data.length];
        }

        //拷贝完成后，data直接指向拷贝好的新数组
        data = newData;
        //队首已重置为0
        front = 0;
        //队尾已重置为队列大小--末尾元素索引值+1
        tail = size;
    }

    //均摊时间复杂度O(1)
    @Override
    public void enqueue(E e) {
        if((tail+1) % data.length == front){//循环队列满，要扩容啦！
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail+1) % data.length;
        size++;
    }

    //均摊时间复杂度O(1)
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Can't dequeue from an empty Queue!");
        }

        E result = data[front];
        //出队的队首元素JVM内存回收
        data[front] = null;
        front = (front+1) % data.length;
        size--;

        //为避免动态缩容与动态扩容形成计算复杂度震荡，采用更懒一点的方式解决：
        if(size <= getCapacity() / 4 && getCapacity()/2 != 0){//低于系统容量1/4时缩容；缩容不能缩成0
            resize(getCapacity()/2);
        }
        return result;
    }

    //时间复杂度O(1)
    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("The Queue is empty!");
        }
        return data[front];
    }

    //重写toString方法，打印结果便于测试验证
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue: size=%d, capacity=%d, Front[", size, getCapacity()));
        //注意：遍历一个循环队列，想清楚，方法如下
        for (int i=front; i!=tail; i=(i+1)%data.length) {
            sb.append(data[i]);
            //如果不是最后一个元素，用逗号空格隔开
            if(tail != (i+1)%data.length){
                sb.append(", ");
            }
        }
        //标识这端是栈顶
        sb.append("]Tail");
        return sb.toString();
    }
}

