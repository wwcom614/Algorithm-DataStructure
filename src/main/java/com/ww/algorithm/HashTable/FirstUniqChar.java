package com.ww.algorithm.HashTable;


//leetcode上的题目387
//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//注意事项：您可以假定该字符串只包含小写字母。
//https://leetcode-cn.com/problems/first-unique-character-in-a-string/


public class FirstUniqChar {
    public int firstUniqChar(String s) {
        //因为字符串限定只包含小写字母，考虑使用26个元素的数组构建Hash表实现
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++)
            //将字符串的每个字符映射到数组中，遇到重复字母，对应索引的数组数值+1
            freq[s.charAt(i) - 'a']++;

        for (int i = 0; i < s.length(); i++)
            //再次依次遍历字符串的每个字符，看哪个字符数组中数值先出现1，返回索引；遍历到最后一个字符也没找到，返回-1
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;

        return -1;
    }
}
