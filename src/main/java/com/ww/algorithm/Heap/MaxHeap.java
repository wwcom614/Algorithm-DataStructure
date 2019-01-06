package com.ww.algorithm.Heap;

import com.ww.algorithm.Array.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> array;

    //让调用者指定初始最大二叉堆大小的构造方法
    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    //无参构造方法
    public MaxHeap() {
        array = new Array<>();
    }

    //Heapify构造方法：将任意数组整理成最大堆，时间复杂度O(n)
    //而如果从无到有逐个add元素构造最大堆的时间复杂度O(nlogn)
    public MaxHeap(E[] arr){
        //首先将输入数组转化为动态数组
        array = new Array<>(arr);

        //找到最后一个非叶子节点--方法是找到最后一个节点的父节点，然后依次向前，直到到达root节点
        for(int i = getParent(arr.length-1); i>=0; i--){
            //逐个节点siftDown，直到满足最大堆性质
            siftDown(i);
        }
    }

    //给调用者返回堆当前元素数量
    public int getSize() {
        return array.getSize();
    }

    //给调用者返回堆是否为空
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //内部方法：底层基于动态数组实现的二叉堆，给定当前节点动态数组索引，返回其父节点动态数组索引
    private int getParent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("root node doesn't have parent!");
        } else {
            return (index - 1) / 2;
        }
    }

    //内部方法：底层基于动态数组实现的二叉堆，给定当前节点动态数组索引，返回其左子节点动态数组索引
    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    //内部方法：底层基于动态数组实现的二叉堆，给定当前节点动态数组索引，返回其右子节点动态数组索引
    private int getRightChild(int index) {
        return index * 2 + 2;
    }

    //时间复杂度O(logn)
    //内部方法：最大堆在底层(数组索引为index位置)插入元素后，需要逐步上浮到合适节点，以满足最大堆性质：父节点值比左右子节点都大
    private void siftUp(int curIndex) {
        //当索引curIndex还未到达动态数组的最头部，并且curIndex的父元素值比新插入的curIndex元素值小
        while (curIndex > 0 && array.get(getParent(curIndex)).compareTo(array.get(curIndex)) < 0) {
            //那么交换curIndex元素与父元素的值
            array.swap(curIndex, getParent(curIndex));
            //索引curIndex向前移动到父元素的索引处，以便进行下一轮与再上一层父节点值的比较和交换
            curIndex = getParent(curIndex);
        }
    }

    //向最大堆中增加元素，时间复杂度O(logn)
    public void add(E e) {
        //增加元素到最大堆的最底层末尾，也就是动态数组的末尾
        array.addLast(e);
        //调用siftUp逐步向堆上层(动态数组前端)调整新增加元素的位置，直到满足最大堆性质：父节点值比左右子节点都大
        siftUp(array.getSize() - 1);
    }

    //时间复杂度logn
    //内部方法：新插入顶层节点(数组索引为curIndex位置)元素。如何恢复最大堆性质(父节点值比左右子节点都大)：
    // 1.curIndex与其左子节点(getLeftChild)或右子节点(getRightChild)值最大的那一个交换位置
    // 2.curIndex继续步骤2的比较，直到curIndex逐步下沉到合适节点，以满足最大堆性质：父节点值比左右子节点都大
    private void siftDown(int curIndex) {
        //当curIndex节点存在左子节点(左子节点的索引在动态数组范围内)
        while (getLeftChild(curIndex) < array.getSize()) {
            //先把左子节点的索引存下来，默认curIndex节点的最大值子树maxChildIndex是左子树的值
            int maxChildIndex = getLeftChild(curIndex);
            //除非curIndex节点右子节点也存在(右子节点的索引也在动态数组范围内)，并且右子树值>左子树值
            if (maxChildIndex + 1 < array.getSize() &&
                    array.get(maxChildIndex + 1).compareTo(array.get(maxChildIndex)) > 0) {
                //此时curIndex节点的最大值子树maxChildIndex是右子树的值
                maxChildIndex = maxChildIndex + 1;
            }

            //确定好了curIndex节点的最大值子树maxChildIndex，与curIndex节点值做比较：
            //如果curIndex节点的最大值子树maxChildIndex 比 curIndex节点值小，那么已经符合最大堆性质，可以结束移动curIndex节点。
            if(array.get(maxChildIndex).compareTo(array.get(curIndex)) < 0){
                break;
            }else {//否则说明curIndex节点还没下沉到位，curIndex节点与maxChildIndex节点交换位置后继续下沉。
                array.swap(curIndex, maxChildIndex);//交换值
                curIndex = maxChildIndex; //当前索引位置下沉
            }
        }
    }

    //时间复杂度O(1)
    //查询最大堆中的最大元素，其实就是root节点(数组索引为0位置)，也就是动态数组的首个元素
    public E getMax() {
        if (array.getSize() == 0) {
            throw new IllegalArgumentException("The heap is empty so it can't get Max!");
        } else {
            return array.get(0);
        }
    }

    //时间复杂度O(logn)
    //取出最大堆的最大元素，并删除该节点--顶层root节点(数组索引为0位置)
    public E removeMax() {
        //先查询最大堆的最大值，并记录下来，最后作为结果返回
        E max = getMax();
        //删除最大堆的最大元素根节点后，如何移动节点恢复最大堆性质：
        //1.将最大堆的root节点和末尾节点交换位置
        array.swap(0, array.getSize() - 1);
        //2.交换后，删除末尾节点
        array.removeLast();
        //顶层root节点(数组索引为0位置)下沉，直到满足最大堆性质
        siftDown(0);
        return max;
    }

    //时间复杂度O(logn)
    //取出(获取并删除)最大堆中最大元素，插入元素e
    public E replace(E e){
        //先取出最大堆最大元素保存好，最后返回
        E max = getMax();

        //新插入元素直接插入到堆顶
        array.set(0, e);
        //逐步下沉交换，直到恢复最大堆性质
        siftDown(0);
        //返回之前保存的最大元素值
        return max;
    }

}
