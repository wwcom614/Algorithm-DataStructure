package com.ww.algorithm.Trie;

import java.util.TreeMap;

//leetcode题目677键值映射：https://leetcode-cn.com/problems/map-sum-pairs/description/
//实现一个 MapSum 类里的两个方法，insert 和 sum。
//对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
//对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
public class MapSum {
    //定义一个内部类Node，这是Trie的一个元素
    private class Node {
        //value值不为0表示即使这不是叶子节点，到该节点也是个单词，例如panda中，走到pan没到叶子节点也是一个单词
        public int value;
        public TreeMap<Character, Node> next;

        //Node构造方法
        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        //Node无参构造方法
        public Node() {
            this(0);
        }
    }

    private Node root;

    //构造方法
    public MapSum(){
        root = new Node();
    }

    public void insert(String word, int val){
        Node cur = root;
        //遍历新单词word的每个字符
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //如果Trie中没有该字符，需要先创建节点
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            //将当前节点cur移动到下个节点
            cur = cur.next.get(c);
        }
        //移动到最后位置，新创建或在原有节点赋值新的val值
        cur.value = val;
    }

    //前缀匹配权重求和方法
    public int sum(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            //如果前缀prefix挨字母在Trie中找，到中间哪个字母已经找不到了，该单词肯定不在Trie中
            if (cur.next.get(c) == null) {
                //返回前缀匹配求和权重值为0
                return 0;
            }
            //前缀prefix该字母在Trie中找得到，那就移动到下一个Trie的Node节点继续匹配下个字母
            cur = cur.next.get(c);
        }

        //此时已经到达prefix的最后一个字母对应的节点，其本身以及其所有的next节点都匹配，value求和
        return sum(cur);
    }

    private int sum(Node node){

        //递归结束条件：找到最终的叶子节点，直接返回自己的value值
        if(node.next.size() == 0){
            return node.value;
        }

        //node本身命中，其value值肯定是求和的一部分
        int sumResult = node.value;

        //遍历node的next节点，它们的value值也要加进来
        for (char nextChar: node.next.keySet()){
            sumResult += sum(node.next.get(nextChar));
        }

        return sumResult;
    }


}
