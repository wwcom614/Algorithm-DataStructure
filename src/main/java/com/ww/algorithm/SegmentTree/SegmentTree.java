package com.ww.algorithm.SegmentTree;

//线段树(区间树)SegmentTree
//区间数据在动态更新变化，不断查询区间数据。
//区间本身固定，仅仅是数据更新，不插入新数据
public class SegmentTree<E> {
    // 将平衡二叉树结构的线段树，底层叶子节点null补全为满二叉树
    // 而且因区间固定，所以可以使用普通的静态数组做底层数据结构
    private E[] array;//数据元素底层数据结构存储在数组中
    private E[] tree;//更新和查询时间复杂度O(logn)级别的线段树数据结构
    private Merger<E> merger;//线段树的对两个子节点数据如何融合成父节点数据的业务逻辑融合器

    //线段树构造方法，时间复杂度O(n)
    public SegmentTree(E[] arr, Merger<E> merger) {
        //获取调用者的对两个子节点数据如何融合成父节点数据的业务逻辑，例如求和、取最大最小值等
        this.merger = merger;

        array = (E[]) new Object[arr.length];
        //输入Integer数组不能直接转换为int数组array，需要逐个元素封装转换
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        //底层数组开辟4*n空间，确保能容纳底层叶子节点null补全为满二叉树的线段树所有元素
        tree = (E[]) new Object[arr.length * 4];
        //输入一个数组，自顶向下创建线段树
        buildSegmentTree(0, 0, array.length - 1);
    }

    //内部私有方法：在curIndex位置创建[left...right]的线段树
    private void buildSegmentTree(int curIndex, int left, int right) {
        //递归结束条件，是最后的叶子节点
        if (left == right) {
            tree[curIndex] = array[left];
            return;
        }

        int leftChildIndex = getLeftChild(curIndex);
        int rightChildIndex = getRightChild(curIndex);
        //注意二分节点写成这种形式可避免整数之和超出整数范围
        int middle = left + (right - left) / 2;
        //左子树的范围是前半段
        buildSegmentTree(leftChildIndex, left, middle);
        //右子树的范围是后半段
        buildSegmentTree(rightChildIndex, middle + 1, right);

        //引入融合器，让调用者来实现：对两个子节点数据如何融合成父节点数据的业务逻辑
        tree[curIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }


    public int getSize() {
        return array.length;
    }

    public E get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Illeagal index!");
        } else {
            return array[index];
        }
    }

    //内部方法：底层基于数组实现的满二叉树，给定当前节点数组索引，返回其左子节点数组索引
    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    //内部方法：底层基于数组实现的满二叉树，给定当前节点数组索引，返回其右子节点数组索引
    private int getRightChild(int index) {
        return index * 2 + 2;
    }

    //查询并返回区间范围[queryLeft,queryRight]内的值，时间复杂度O(logn)
    public E querySeg(int queryLeft, int queryRight) {
        if (queryLeft < 0 || queryLeft >= array.length || queryRight < 0 || queryRight >= array.length) {
            throw new IllegalArgumentException("Illegal left or right index!");
        }
        return querySeg(0, 0, array.length - 1, queryLeft, queryRight);
    }

    //内部私有方法：在curIndex为根节点的线段树[left,right]的范围里，查询[queryLeft，queryRight]的值，时间复杂度O(logn)
    private E querySeg(int curIndex, int left, int right, int queryLeft, int queryRight) {
        //递归结束条件
        if (queryLeft == left && queryRight == right) {
            return tree[curIndex];
        }

        int leftChildIndex = getLeftChild(curIndex);
        int rightChildIndex = getRightChild(curIndex);
        //注意二分节点写成这种形式可避免整数之和超出整数范围
        int middle = left + (right - left) / 2;

        if (queryLeft >= middle + 1) {//如果要查询的左边界位于middle右侧，直接查右子树
            return querySeg(rightChildIndex, middle + 1, right, queryLeft, queryRight);
        } else if (queryRight <= middle) {//如果要查询的右边界位于middle左侧，直接查左子树
            return querySeg(leftChildIndex, left, middle, queryLeft, queryRight);
        } else {//如果要查询的结果两边子树都有，那就两边都查一下，然后放入调用者自定义的融合器计算结果返回
            E leftResult = querySeg(leftChildIndex, left, middle, queryLeft, middle);
            E rightResult = querySeg(rightChildIndex, middle + 1, right, middle + 1, queryRight);
            return merger.merge(leftResult, rightResult);
        }
    }

    //将index位置的元素更新为e，时间复杂度O(logn)
    public void set(int index, E e) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        array[index] = e;
        set(0, 0, array.length - 1, index, e);
    }

    //内部私有方法：在curIndex为根节点的线段树[left,right]的范围里，将index位置的元素更新为e，时间复杂度O(logn)
    private void set(int curIndex, int left, int right, int index, E e) {
        //递归结束条件，是最后的叶子节点
        if (left == right) {
            tree[curIndex] = e;
            return;
        }

        int leftChildIndex = getLeftChild(curIndex);
        int rightChildIndex = getRightChild(curIndex);
        //注意二分节点写成这种形式可避免整数之和超出整数范围
        int middle = left + (right - left) / 2;
        if (curIndex >= middle + 1) {//如果要更新的节点位于middle右侧，直接去右子树寻找
            set(rightChildIndex, middle + 1, right, index, e);
        } else {//curIndex<=middle 如果要更新的节点位于middle左侧，直接去左子树寻找
            set(leftChildIndex, left, middle, index, e);
        }
        //更新index元素值e后，需要调用融合器，重新计算下线段树的值
        tree[curIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }

            if (i != tree.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
