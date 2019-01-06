package com.ww.algorithm.RBTree;

import com.ww.algorithm.Map.Map;

//注意红黑树的Key要能可比较大小
public class RBTree<K extends Comparable<K>, V> implements Map<K, V> {

    //红黑树节点颜色常量定义
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //内部定义红黑树的节点，对外不可见
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        //红黑树节点颜色标识：RED = true， BLACK = false
        public boolean color;

        //构造方法
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            //默认构造方法创建的节点颜色初始为红色，默认向上融合
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    // 判断节点node的颜色
    private boolean isRed(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1    x   --------->   node   T3
    //      / \              /   \
    //     T2 T3            T1   T2
    private Node leftRotate(Node node){
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        //旋转后的颜色标识并不能保证旋转完后还是红黑树，只是一个子步骤。
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x     T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){

        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        //旋转后的颜色标识并不能保证旋转完后还是红黑树，只是一个子步骤。
        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //平均时间复杂度O(logn)
    //对外可见：向红黑树中增加新元素(key, value)
    @Override
    public void add(K key, V value) {
        //调用内部私有的add方法
        root = add(root, key, value);
        //最终根节点为黑节点
        root.color = BLACK;
    }

    //平均时间复杂度O(logn)
    //内部方法，对外屏蔽内部节点细节：递归方法向node为根节点的红黑树中新插入Node(key, value)或更新已有node.key的value
    //返回插入新节点后的红黑树的根节点
    private Node add(Node node, K key, V value) {
        //递归终止条件，找到最后也没找到，直接新建一个节点
        if (node == null) {
            size++;
            //默认插入红色节点
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else {//key.equals(node.key)，如果key相同，更新该key的value
            node.value = value;
        }

        //黑节点的右子节点是红色，左子节点是黑色，此时需要做左旋转
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        //黑节点左侧连续有2个红子节点，此时需要右旋转
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        //黑节点的左右子节点都是红色，需要做颜色翻转
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    //平均时间复杂度O(logn)
    //封装内部公用方法，对外屏蔽内部节点细节：递归方法查询node为根节点的红黑树中元素
    //返回插入新节点后的红黑树的根节点
    private Node getNode(Node node, K key){
        //递归终止条件，找到最后也没找到，返回null
        if (node == null) {
            return null;
        }
        //递归
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {//e.compareTo(node.e) > 0
            return getNode(node.right, key);
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root ,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException(key + " doesn't exist in Map!");
        }else {
            node.value = value;
        }
    }

    //内部方法：找以node为根节点的红黑树的最小值所在节点
    // 找有值的最左分支元素，类似找链表的尾节点
    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return getMin(node.left);
        }
    }

    //有空再研究RBTree的删除
    public V remove(K key){
        return null;
    };

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
