package com.ww.algorithm.Recursion;

public class ArraySum {
    public static int ArraySum(int[] array){
        return sum(array, 0);
    }

    //递归练习：使用递归方法求数组元素的和。
    //计算array[leftIndex.....n]范围内元素的和
    //sum(array[0]~array[n-1]) = array[0] + sum(array[1]~array[n-1])
    private static int sum(int[] array, int leftIndex){
        //求解最基本的问题
        if(leftIndex == array.length){
            return 0;
        }else {
            //把原问题转化为更小的问题，还是方法调用，不是A调用B，是A调用A自己而已
            return array[leftIndex] + sum(array, leftIndex+1);
        }

    }
}
