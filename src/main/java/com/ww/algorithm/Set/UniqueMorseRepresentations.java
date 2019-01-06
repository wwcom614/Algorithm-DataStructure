package com.ww.algorithm.Set;

//leetcode第804道题目
//https://leetcode-cn.com/problems/unique-morse-code-words/

import java.util.TreeSet;

public class UniqueMorseRepresentations {

    public static int uniqueMorseRepresentations(String[] words) {
        //将leetcode上摩斯码与26个小写英文字母的映射定义，存放到一个字符串数组中
        String[] morseCodes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<String> treeSet = new TreeSet<>();
        for(String word : words){
            word = word.toLowerCase();
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<word.length(); i++){
                sb.append(morseCodes[word.charAt(i)-'a']);
            }
            treeSet.add(sb.toString());
        }
        return treeSet.size();


    }

}
