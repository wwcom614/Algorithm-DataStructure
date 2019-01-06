package com.ww.algorithm.SegmentTree;

//给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
//https://leetcode-cn.com/problems/range-sum-query-immutable/
public class NumsBySumArray {
    //2.元素和数组方案：因元素不可变，考虑提前计算好数组和的方案，性能更好
    //sum[i]存储前i个元素和--[0~i-1]：sum[0]=0,sum[1]=nums[0],sum[2]=sum[1]+nums[1]...
    //为求区间和操作提前准备个求和数组
    private int[] sum;
    //为更新操作提前准备个新数组
    private int[] updateArr;

    //初始化建立时间复杂度O(n)
    NumsBySumArray(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 0; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        //为更新操作提前准备个新数组
        updateArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            updateArr[i] = nums[i];
        }

    }

    //查询的时间复杂度O(1)
    int sumRange2(int i, int j) {
        //sum[j+1]存储的是nums[0~j]的和
        //sum[i]存储的是nums[0~i-1]的和
        return sum[j + 1] - sum[i];
    }

    //更新的时间复杂度O(n)
    public void update(int index, int value) {
        updateArr[index] = value;
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + updateArr[i - 1];
        }
    }
}
