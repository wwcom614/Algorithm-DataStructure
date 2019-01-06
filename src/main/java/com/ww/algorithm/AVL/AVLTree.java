package com.ww.algorithm.AVL;

import com.ww.algorithm.Map.Map;

import java.util.ArrayList;

//注意AVL树的Key要能可比较大小
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {
    //内部定义AVL树的节点，对外不可见
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        //增加一个变量height，进行二叉树各节点树高度维护
        public int height;

        //构造方法
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;//初始化时只有一个节点，树高度为1
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    //内部私有方法：获取节点node树的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    //内部私有方法，获取节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {//空节点的平衡因子是0
            return 0;
        } else {//非空节点的平衡因子是其左子树和右子树的高度差，>1表示左子树不平衡，<-1表示右子树不平衡
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    //判断该二叉树是否是二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        //方法是调用中二叉树的递归中序遍历，从最左下角叶子节点开始，左叶子节点->父节点->右叶子节点，逐个将节点的key压入ArrayList
        midOrderTraverse(root, keys);
        //从ArrayList依次取出之前压入的key，获取value输出应该是从小到大顺序排列的
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    //中序遍历二叉树节点，逐个将节点的key压入ArrayList
    private void midOrderTraverse(Node node, ArrayList<K> keys) {
        //递归终止条件
        if (node == null) {
            return;
        }
        //递归到最左下角叶子节点开始，左叶子节点->父节点->右叶子节点，逐个将节点的key压入ArrayList
        midOrderTraverse(node.left, keys);
        keys.add(node.key);
        midOrderTraverse(node.right, keys);
    }

    //判断二叉树是否是平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //内部私有方法，递归判断以node为根节点的二叉树是否是平衡二叉树
    private boolean isBalanced(Node node) {
        if (node == null) {//递归结束条件：node节点是空节点，是平衡二叉树
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {//递归结束条件：node节点左子树和右子树的高度差balanceFactor的绝对值超过1，不是平衡二叉树
            return false;
        }

        //递归左子树和右子树，两者都是平衡二叉树node才可能是平衡二叉树
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //维护二叉树平衡且保持二分搜索树特性的关键方法，右旋和左旋方法
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        //指定要旋转的x节点
        Node x = y.left;
        //提前保存x节点的右子树，右旋完成后作为y节点左子树
        Node T3 = x.right;

        // x节点右旋过程
        x.right = y;
        y.left = T3;

        //旋转完成后，更新节点x和y的height，其树的高度=其左右子树更高的那个高度+1
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        //指定要旋转的x节点
        Node x = y.right;
        //提前保存x节点的左子树，右旋完成后作为y节点右子树
        Node T2 = x.left;

        //x节点左旋过程
        x.left = y;
        y.right = T2;

        //旋转完成后，更新参与旋转的节点x和y的height，其树的高度=其左右子树更高的那个高度+1
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    //平均时间复杂度O(logn)
    //对外可见：向AVL树中增加新元素(key, value)
    @Override
    public void add(K key, V value) {
        //调用内部私有的add方法
        root = add(root, key, value);
    }

    //平均时间复杂度O(logn)
    //内部方法，对外屏蔽内部节点细节：递归方法向node为根节点的AVL树中新插入Node(key, value)或更新已有node.key的value
    //返回插入新节点后的AVL树的根节点
    private Node add(Node node, K key, V value) {
        //递归终止条件，找到最后也没找到，直接新建一个节点
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {//要增加元素的key < 当前节点node的key，向左节点递归继续寻找
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {//要增加元素的key > 当前节点node的key，向右节点递归继续寻找
            node.right = add(node.right, key, value);
        } else {//key.equals(node.key)，//要增加元素的key == 当前节点node的key，更新该节点key的value
            node.value = value;
        }

        //更新height：当前node增加新节点后，其树的高度=其左右子树更高的那个高度+1
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算node节点的平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护：
        //1.插入的元素在不平衡节点左侧的左侧--LL
        //不平衡节点在node左子树 && 到node左子树节点看下，左子树节点的左子树高度更高
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            //node节点，右旋转可恢复树的平衡性，且保持二分搜索树特性
            return rightRotate(node);

        //2.插入的元素在不平衡节点右侧的右侧--RR
        //不平衡节点在node右子树 && 到node右子树节点看下，右子树节点的右子树高度更高
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            //node节点，左旋转可恢复树的平衡性，且保持二分搜索树特性
            return leftRotate(node);

        //3.插入的元素在不平衡节点左侧的右侧--LR
        //不平衡节点在node左子树 && 到node左子树节点看下，左子树节点的右子树高度更高
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            //先对node节点左子树节点左旋转，旋转后变成LL，再进行一次右旋转，可恢复树的平衡性，且保持二分搜索树特性
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //4.插入的元素在不平衡节点右侧的左侧--RL
        //不平衡节点在node右子树 && 到node右子树节点看下，右子树节点的左子树高度更高
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            //先对node节点右子树节点右旋转，旋转后变成RR，再进行一次左旋转，可恢复树的平衡性，且保持二分搜索树特性
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        //如果不需要平衡性维护，直接返回node节点
        return node;
    }

    //平均时间复杂度O(logn)
    //封装内部公用方法，对外屏蔽内部节点细节：递归方法查询node为根节点的二分搜索树中插入元素e
    //返回插入新节点后的二分搜索树的根节点
    private Node getNode(Node node, K key) {
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
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist in Map!");
        } else {
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


    //内部方法：递归删除以node为根节点的二分搜索树中键为key的节点
    //返回删除节点后新的二分搜索树的根节点
    private Node remove(Node node, K key) {
        //递归终止条件。找到最后也没找到键为key的节点，不用删除
        if (node == null) {
            return null;
        }

        //定义一个返回的节点变量，经过平衡处理后再最终返回
        Node returnNode;
        //递归
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            returnNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            returnNode = node;
        } else {//e.equals(node.e),这就是要删除的node节点了
            if (node.left == null) {//如果要删除的node节点没有左子树
                Node rightNode = node.right;//把右子树保存下来返回即可
                node.right = null;//删除node的右子树
                size--;
                returnNode = rightNode;
            } else if (node.right == null) {//如果要删除的node节点没有右子树
                Node leftNode = node.left;//把左子树保存下来返回即可
                node.left = null;//删除node的左子树
                size--;
                returnNode = leftNode;
            } else {//如果要删除的node节点既有左子树，又有右子树
                //找到比待删除node节点大的最小的节点：也就是node节点右子树中的最小节点，作为后继successor节点
                Node successor = getMin(node.right);
                //将后继successor替代node节点，替代后：
                //1.后继successor的右子树为原node节点的右子树去除最小节点
                successor.right = remove(node.right, successor.key);
                //2.后继successor的左子树为原node节点的左子树
                successor.left = node.left;
                //3.标记JVM释放删除的node节点
                node.left = node.right = null;
                size--;
                returnNode = successor;
            }
        }

        //只有一个节点，被删除后为空，防止空指针异常保护
        if(returnNode == null){
            return null;
        }

        //更新height：returnNode其树的高度=其左右子树更高的那个高度+1
        returnNode.height = Math.max(getHeight(returnNode.left), getHeight(returnNode.right)) + 1;

        // 计算returnNode节点的平衡因子
        int balanceFactor = getBalanceFactor(returnNode);

        // 平衡维护：
        //1.returnNode不平衡节点左侧的左侧--LL
        //不平衡节点在returnNode左子树 && 到returnNode左子树节点看下，左子树节点的左子树高度更高
        if (balanceFactor > 1 && getBalanceFactor(returnNode.left) >= 0)
            //returnNode节点，右旋转可恢复树的平衡性，且保持二分搜索树特性
            return rightRotate(returnNode);

        //2.returnNode在不平衡节点右侧的右侧--RR
        //不平衡节点在returnNode右子树 && 到returnNode右子树节点看下，右子树节点的右子树高度更高
        if (balanceFactor < -1 && getBalanceFactor(returnNode.right) <= 0)
            //returnNode节点，左旋转可恢复树的平衡性，且保持二分搜索树特性
            return leftRotate(returnNode);

        //3.returnNode在不平衡节点左侧的右侧--LR
        //不平衡节点在returnNode左子树 && 到returnNode左子树节点看下，左子树节点的右子树高度更高
        if (balanceFactor > 1 && getBalanceFactor(returnNode.left) < 0) {
            //先对returnNode节点左子树节点左旋转，旋转后变成LL，再进行一次右旋转，可恢复树的平衡性，且保持二分搜索树特性
            returnNode.left = leftRotate(returnNode.left);
            return rightRotate(returnNode);
        }

        //4.returnNode在不平衡节点右侧的左侧--RL
        //不平衡节点在returnNode右子树 && 到node右子树节点看下，右子树节点的左子树高度更高
        if (balanceFactor < -1 && getBalanceFactor(returnNode.right) > 0) {
            //先对returnNode节点右子树节点右旋转，旋转后变成RR，再进行一次左旋转，可恢复树的平衡性，且保持二分搜索树特性
            returnNode.right = rightRotate(returnNode.right);
            return leftRotate(returnNode);
        }

        //如果不需要平衡性维护，直接返回returnNode节点
        return returnNode;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {//找到key了
            root = remove(root, key);
            return node.value;
        } else {//没找到key
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
