package com.ww.algorithm.Stack;


/*
https://leetcode-cn.com/problems/valid-parentheses/
给定一个有 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。*/

import java.util.Stack;

public class BracketsMatch {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        //依次遍历字符串String的每个字符，提取括号，按顺序存储到StringBuilder中。
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //依次遍历字符串String的每个字符
            if (c == '(' || c == '[' || c == '{' || c == ')' || c == ']' || c == '}') {
                sb.append(c);
            }
        }

        //此时，StringBuilder sb中只有括号，且出现顺序与输入String中一致。
        for (int i = 0; i < sb.length(); i++) {
            //挨个看看这些括号
            char c = sb.charAt(i);
            //如果字符是左括号，入栈stack。注意，该栈只入这3种左括号字符
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                //如果开头不是左括号的，肯定不合法
                if (stack.isEmpty()) {
                    return false;
                }
                //如果栈内的左括号 和 下一个字符中的括号不匹配，肯定不合法
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        //都匹配完成后，栈内不能还有左括号，如果栈不为空，肯定不合法
        return stack.isEmpty();
    }
}
