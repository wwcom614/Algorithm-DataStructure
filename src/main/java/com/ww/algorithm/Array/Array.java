package com.ww.algorithm.Array;

//自定义数组类
//使用泛型，让数组类支持存储任意元素
public class Array<E> {
    //自定义数组
    private E[] data;

    //数组当前元素个数
    private int size;

    //构造方法：传入数组容量capacity构造Array
    public Array(int capacity) {
        //Java不支持new泛型，只能是先new个Object出来，然后强制类型转换为泛型类型
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //无参构造方法：调用方如果没传入数组容量capacity，将使用该方法预制初始容量
    public Array() {
        this(10);
    }

    //构造方法：传入一个数组，构造成动态数组
    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i=0; i<arr.length;i++){
            data[i] = arr[i];
        }
        size = arr.length;
    }

    //获取数组当前元素个数
    public int getSize() {
        return size;
    }

    //获取数组容量
    public int getCapacity() {
        return data.length;
    }

    //查询数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //均摊时间复杂度O(1)
    //自定义一个内部方法，用于数组动态扩容和缩容。注：该操作不涉及增删元素。
    private void resize(int newCapacity) {
        //实例化一个newCapacity的扩容后数组，并拷贝扩容前数组的数据元素到扩容后数组
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        //扩容完毕后，data引用直接指向扩容后的newData对象
        data = newData;
    }

    //时间复杂度O(n)
    //在数组索引index位置插入一个元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. Required 0<=index<=size! ");
        }
        if (size == data.length) {//数组满了要扩容啦！
            //一次性动态扩容2倍
            resize(data.length * 2);
        }

        //从最后一个元素开始，每个依次向后挪一个，一直到把index的数据也向后挪走
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //安心的在index插入新元素
        data[index] = e;
        //数组当前元素数量+1
        size++;
    }

    //时间复杂度O(1)
    //便捷扩展：在数组所有已有元素最后面插入一个元素
    public void addLast(E e) {
        add(size, e);
    }

    //时间复杂度O(n)
    //便捷扩展：在数组所有已有元素最前面插入一个元素
    public void addFirst(E e) {
        add(0, e);
    }

    //时间复杂度O(1)
    //获取index位置的元素
    public E get(int index) {
        //控制调用者无法获取未使用的空间元素
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed. Required 0<=index<size! ");
        }
        return data[index];
    }

    //时间复杂度O(1)
    //便捷扩展：获取数组最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    //时间复杂度O(1)
    //便捷扩展：获取数组首个元素
    public E getFirst() {
        return get(0);
    }

    //时间复杂度O(1)
    //修改index位置的元素
    public void set(int index, E e) {
        //控制调用者无法获取未使用的空间元素
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed. Required 0<=index<size! ");
        }
        data[index] = e;
    }

    //时间复杂度O(n)
    //查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            //注意：需使用值比较equals，不能使用引用比较==
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    //时间复杂度O(n)
    //查找数组中首个元素e所在的索引值，如果不存在，返回-1
    public int findFirstElement(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    //时间复杂度O(n)
    //在数组索引index位置删除一个元素，并返回该删除的元素
    public E remove(int index) {
        if (size <= 0) {
            throw new IllegalArgumentException("remove failed. Array is empty! ");
        } else if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. Required 0<=index<size! ");
        } else {
            E result = data[index];
            //从删除元素index开始，每个依次向前挪一个，一直到size-1末尾元素
            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            //数组当前元素数量-1
            size--;
            //清空最后一个不用的元素引用，让JVM垃圾回收
            data[size] = null;

            //为避免动态缩容与动态扩容形成计算复杂度震荡，采用更懒一点的方式解决：
            if (size <= data.length / 4 && data.length / 2 != 0) {//低于系统容量1/4时缩容；缩容不能缩成0
                //动态缩容一半，降低对象存储占用
                resize(data.length / 2);
            }
            return result;
        }
    }

    //时间复杂度O(n)
    //快捷删除数组中第1个元素，并返回该删除的元素
    public E removeFirst() {
        return remove(0);
    }

    //时间复杂度O(1)
    //快捷删除数组中最后1个元素，并返回该删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    //时间复杂度O(n)
    //从数组中删除首个值为e的元素，删除成功返回true，未找到返回false
    public boolean removeFirstElement(E e) {
        int index = findFirstElement(e);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    //重写toString方法，打印结果便于测试验证
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size=%d, capacity=%d \n [", size, data.length));
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            //如果不是最后一个元素，用逗号空格隔开
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //数组索引为i和j的两个元素交换，最大堆add元素时使用
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal!");
        } else {
            E e = data[i];
            data[i] = data[j];
            data[j] = e;
        }

    }


}
