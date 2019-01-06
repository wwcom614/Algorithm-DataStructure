package com.ww.algorithm.Map;

//注意二分搜索树的Key要能可比较大小
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    //内部定义二分搜索树的节点，对外不可见
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        //构造方法
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    //平均时间复杂度O(logn)
    //对外可见：向二分搜索树中增加新元素(key, value)
    @Override
    public void add(K key, V value) {
        //调用内部私有的add方法
        root = add(root, key, value);
    }

    //平均时间复杂度O(logn)
    //内部方法，对外屏蔽内部节点细节：递归方法向node为根节点的二分搜索树中新插入Node(key, value)或更新已有node.key的value
    //返回插入新节点后的二分搜索树的根节点
    private Node add(Node node, K key, V value) {
        //递归终止条件，找到最后也没找到，直接新建一个节点
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else {//key.equals(node.key)，如果key相同，更新该key的value
            node.value = value;
        }
        return node;
    }

    //平均时间复杂度O(logn)
    //封装内部公用方法，对外屏蔽内部节点细节：递归方法查询node为根节点的二分搜索树中元素
    //返回插入新节点后的二分搜索树的根节点
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

    //内部方法：找以node为根节点的二分搜索树的最小值所在节点
    // 找有值的最左分支元素，类似找链表的尾节点
    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return getMin(node.left);
        }
    }

    //内部方法：删除以node为根的二分搜索树中的最小值节点，
    // 返回删除后的新二分搜索树的根节点
    private Node removeMin(Node node) {
        //递归终止条件。注意删除左子树时，如果有右子树，需要存下来，然后去除node的右子树指针
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        //递归向最左侧子树移动
        node.left = removeMin(node.left);
        return node;
    }

    //内部方法：递归删除以node为根节点的二分搜索树中键为key的节点
    //返回删除节点后新的二分搜索树的根节点
    private Node remove(Node node, K key){
        //递归终止条件。找到最后也没找到键为key的节点，不用删除
        if(node == null){
            return null;
        }

        //递归
        if(key.compareTo(node.key) < 0 ){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else {//e.equals(node.e),这就是要删除的node节点了
            if(node.left == null){//如果要删除的node节点没有左子树
                Node rightNode = node.right;//把右子树保存下来返回即可
                node.right = null;//删除node的右子树
                size--;
                return rightNode;
            }else if(node.right == null){//如果要删除的node节点没有右子树
                Node leftNode = node.left;//把左子树保存下来返回即可
                node.left = null;//删除node的左子树
                size--;
                return leftNode;
            }else {//如果要删除的node节点既有左子树，又有右子树
                //找到比待删除node节点大的最小的节点：也就是node节点右子树中的最小节点，作为后继successor节点
                Node successor = getMin(node.right);
                //将后继successor替代node节点，替代后：
                //1.后继successor的右子树为原node节点的右子树去除最小节点
                successor.right = removeMin(node.right);//注意：removeMin中有size--了
                //2.后继successor的左子树为原node节点的左子树
                successor.left = node.left;
                //3.标记JVM释放删除的node节点
                node.left = node.right = null;
                return successor;
            }
        }

    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){//找到key了
            root = remove(root, key);
            return node.value;
        }else {//没找到key
            return null;
        }
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
