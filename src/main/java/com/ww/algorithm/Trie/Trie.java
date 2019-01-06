package com.ww.algorithm.Trie;

import java.util.TreeMap;

//字典树(前缀树)Trie
//场景：专门为处理字符串设计的数据结构/集合/映射，可做到查询每个条目的时间复杂度和字典中有多少个条目无关，只和查询字符长度w相关，而大多数单词长度<10。
//可以作为字符串集合set的底层数据结构，也可以作为字符串+value的映射map的底层数据结构。
//典型以空间换时间的数据结构，缺点是空间占用过大，为解决该问题，在此基础上有Compressed Trie、Ternary Search Trie。
public class Trie {

    //定义一个内部类Node，这是Trie的一个元素
    private class Node {
        //isWord是解决非叶子节点也可能是单词的问题，例如panda中，走到pan没到叶子节点也是一个单词
        public boolean isWord;
        public TreeMap<Character, Node> next;

        //Node构造方法
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        //Node无参构造方法
        public Node() {
            this(false);
        }
    }

    private Node root;
    //记录Trie中单词word数量
    private int size;

    //Trie构造方法
    public Trie() {
        root = new Node();
        size = 0;
    }

    //获取Trie中单词word数量
    public int getSize() {
        return size;
    }

    //向Trie中增加一个单词word的方法，时间复杂度O(w)，w是要增加的字符串长度，和待查询数据集大小无关
    public void addWord(String word) {
        Node cur = root;
        //遍历要增加的单词word的每个字符
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //如果Trie中没有该字符，创建字符+节点的映射Map
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            //将当前节点cur移动到Trie中下个字符节点
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {//遍历完成后到达要插入单词的最后一个字符，做排重判断，如果这个单词之前不在Trie中
            cur.isWord = true;
            size++;
        }
    }

    //精确匹配：查询Trie中是否包含某个单词word，时间复杂度O(w)，w是查询字符串长度，和待查询数据集无关
    public boolean contains(String word) {
        Node cur = root;
        //遍历要精确匹配单词word的每个字符
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //如果到中间哪个字母已经找不到了，该单词肯定不在Trie中
            if (cur.next.get(c) == null) {
                return false;
            }
            //单词该字母在Trie中找得到，那就移动到下一个Trie的Node节点继续匹配下个字母
            cur = cur.next.get(c);
        }
        //如果单词到最后一个字母都匹配到了Trie树中的元素，
        // 且最后一个字母所在Trie树中的Node节点标识了有单词，才返回true，否则返回false
        return cur.isWord;
    }

    //前缀匹配：查询Trie中是否包含某个单词以prefix为前缀，时间复杂度O(w)，w是查询字符串长度，和待查询数据集无关
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            //如果前缀prefix挨字母在Trie中找，到中间哪个字母已经找不到了，该单词肯定不在Trie中
            if (cur.next.get(c) == null) {
                return false;
            }
            //前缀prefix该字母在Trie中找得到，那就移动到下一个Trie的Node节点继续匹配下个字母
            cur = cur.next.get(c);
        }
        //如果前缀prefix到最后一个字母都匹配到了Trie，前缀匹配完毕，Trie肯定存在单词以prefix为前缀
        return true;
    }

    //leetcode题目211：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
    //设计一个支持以下两种操作的数据结构：void addWord(word)  bool search(word)
    //search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
    //模式匹配
    public boolean search(String word) {
        return match(root, word, 0);
    }

    //index是单词word中的字母索引
    private boolean match(Node node, String word, int index) {
        //递归结束条件，已经到达word的最后一个字母
        if (index == word.length()) {
            return node.isWord;
        }

        //递归开始
        char c = word.charAt(index);
        if (c != '.') {//如果要到trie树中查找的字母c是字母a~z
            if (node.next.get(c) == null) {//如果单词挨字母在Trie树中找，到中间哪个字母已经找不到了，该单词肯定不在Trie中
                return false;
            }
            //本次字母匹配成功，递归下个节点的值与word的下个字符继续看是否匹配
            return match(node.next.get(c), word, index + 1);
        } else {//如果要到trie树中查找的字母c是"."
            for (char nextChar : node.next.keySet()) {//遍历trie树中当前节点的所有下个节点
                if (match(node.next.get(nextChar), word, index + 1)) {//每个下个节点的值与word的下个字符匹配，匹配成功返回true
                    return true;
                }
            }
            //遍历trie树中当前节点的所有下个节点，与word的下个字符都没匹配成功，返回false
            return false;
        }
    }
}
