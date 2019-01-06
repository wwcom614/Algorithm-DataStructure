package com.ww.algorithm.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//二分搜索树的元素必须要有可比性，自身元素比左子树元素大，比右子树元素小。
public class BSTTree<E extends Comparable<E>> {
    //内部定义二分搜索树的节点，对外不可见
    private class Node {
        public E e;
        public Node left, right;

        //构造方法
        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //平均时间复杂度O(logn)
    //对外可见：向二分搜索树中增加新元素e
    public void add(E e) {
        //调用内部私有的add方法
        root = add(root, e);
    }

    //平均时间复杂度O(logn)
    //内部方法，对外屏蔽内部节点细节：递归方法向node为根节点的二分搜索树中插入元素e
    //返回插入新节点后的二分搜索树的根节点
    private Node add(Node node, E e) {
        //递归终止条件，找到最后也没找到，直接新建一个节点
        if (node == null) {
            size++;
            return new Node(e);
        }
        //未考虑重复元素。
        // 如果要考虑重复元素，下述使用<=归到左子树 或 使用>=归到右子树
        if (e.compareTo(node.e) < 0) {//要增加的元素 < 当前节点node的元素，向左节点递归继续寻找
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {//要增加的元素 > 当前节点node的元素，向右节点递归继续寻找
            node.right = add(node.right, e);
        }
        return node;
    }

    //平均时间复杂度O(logn)
    //对外可见：二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //平均时间复杂度O(logn)
    //内部方法，对外屏蔽内部节点细节：递归方法查询node为根节点的二分搜索树中是否含有元素e
    private boolean contains(Node node, E e) {
        //递归终止条件，找到最后也没找到，返回null
        if (node == null) {
            return false;
        }
        //递归
        if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {//e.compareTo(node.e) > 0
            return contains(node.right, e);
        }
    }

    //深度优先遍历：前序遍历、中序遍历、后序遍历

    //平均时间复杂度O(logn)
    //二叉树的前序遍历--非递归，基于自定义stack模拟系统栈实现
    public void preOrderTraverseByStack() {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            //先出栈当前节点
            Node cur = stack.pop();
            System.out.println(String.format("【preOrderTraverseByStack】：%d", cur.e));

            //注意压栈顺序。因为我们想先看左子树，所以要先将非空右子节点压入栈底，非空左子节点后压入在栈顶
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //平均时间复杂度O(logn)
    //二叉树的前序遍历--基于递归实现
    //父节点->左叶子节点->右叶子节点，应用：二叉树状toString打印
    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    //平均时间复杂度O(logn)
    //内部方法，对外屏蔽前序遍历的内部节点细节：递归方法遍历node为根的二叉树
    private void preOrderTraverse(Node node) {
        //递归终止条件
        if (node == null) {
            return;
        }
        //递归
        System.out.println(String.format("【preOrderTraverse】：%d", node.e));
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    //内部方法，前序遍历应用：生成以node为根节点，深度为depth描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }
        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);
    }

    //内部方法：用于字符串体现递归深度
    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    //二叉树的中序遍历--基于递归实现
    //左叶子节点->父节点->右叶子节点，应用：二分搜索树元素从小到大顺序
    public void midOrderTraverse() {
        midOrderTraverse(root);
    }

    //内部方法，对外屏蔽中序遍历的内部节点细节：递归方法遍历node为根的二叉树
    private void midOrderTraverse(Node node) {
        //递归终止条件
        if (node == null) {
            return;
        }
        //递归
        midOrderTraverse(node.left);
        System.out.println(String.format("【midOrderTraverse】：%d", node.e));
        midOrderTraverse(node.right);
    }

    //二叉树的后序遍历--基于递归实现
    //左叶子节点->右叶子节点->父节点，最后才是根节点，应用：其他语言中可用于内存释放
    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    //内部方法，对外屏蔽后序遍历的内部节点细节：递归方法遍历node为根的二叉树
    private void postOrderTraverse(Node node) {
        //递归终止条件
        if (node == null) {
            return;
        }
        //递归
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.println(String.format("【postOrderTraverse】：%d", node.e));
    }

    //广度优先遍历：层序遍历--基于队列实现
    //常用于算法设计找二分搜索树中的特定元素，例如最短路径
    public void levelOrderTraverse() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(String.format("【levelOrderTraverse】：%d", cur.e));

            //使用队列先进先出，想先看左子节点，所以左子节点先入队
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    //查找二分搜索树的最小元素--最左有值的分支元素
    public E getMin() {
        if (size == 0) {
            throw new IllegalArgumentException("BSTTree is empty!");
        }
        return getMin(root).e;
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

    //查找二分搜索树的最大元素--最右有值的分支元素
    public E getMax() {
        if (size == 0) {
            throw new IllegalArgumentException("BSTTree is empty!");
        }
        return getMax(root).e;
    }

    //内部方法：找有值的最右分支元素，类似找链表的尾节点
    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return getMax(node.right);
        }
    }

    //二分搜索树删除最小值节点，并返回最小值
    public E removeMin() {
        //删除之前，先把最小值保存好
        E min = getMin();
        //删除二分搜索树中的最小值节点后，root变更为新二分搜索树的根节点
        root = removeMin(root);
        return min;
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

    //二分搜索树删除最大值节点，并返回最大值
    public E removeMax() {
        //删除之前，先把最大值保存好
        E max = getMax();
        //删除二分搜索树中的最大值节点后，root变更为新二分搜索树的根节点
        root = removeMax(root);
        return max;
    }

    //内部方法：删除以node为根的二分搜索树中的最大值节点，
    // 返回删除后的新二分搜索树的根节点
    private Node removeMax(Node node) {
        //递归终止条件。注意删除右子树时，如果有左子树，需要存下来，然后去除node的左子树指针
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        //递归向最右侧子树移动
        node.right = removeMax(node.right);
        return node;
    }

    //从二分搜索树中，删除元素值为e的节点
    public void remove(E e){
        remove(root, e);
    }

    //内部方法：递归删除以node为根节点的二分搜索树中值为e的节点
    //返回删除节点后新的二分搜索树的根节点
    private Node remove(Node node, E e){
        //递归终止条件。找到最后也没找到值为e的节点，不用删除
        if(node == null){
            return null;
        }

        //递归
        if(e.compareTo(node.e) < 0 ){
            node.left = remove(node.left, e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
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

}
