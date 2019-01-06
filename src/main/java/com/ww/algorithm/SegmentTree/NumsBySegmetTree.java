package com.ww.algorithm.SegmentTree;

//给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
//303题，数组不修改：https://leetcode-cn.com/problems/range-sum-query-immutable/
//307题，数组支持修改：https://leetcode-cn.com/problems/range-sum-query-mutable/
public class NumsBySegmetTree {
    //1.线段树方案，如果数组不修改仅查询时，非时间复杂度最优；数据可修改可查询时，修改和查询的时间复杂度都是O(logn)，OK
    private SegmentTree<Integer> segmentTree;

    //初始化建立时间复杂度O(n)
    NumsBySegmetTree(int[] nums) {
        if (nums.length > 0) {
            //int数组没法直接包装成Integer数组，只能逐个赋值
            Integer[] array = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                array[i] = nums[i];
            }
            //构建线段树
            segmentTree = new SegmentTree<>(array, (a, b) -> a + b);
        }
    }

    //查询的时间复杂度O(logn)
    int sumRange(int i, int j) {
        if(segmentTree == null){
            throw new IllegalArgumentException("SegmentTree is null!");
        }
        return segmentTree.querySeg(i, j);
    }

    //更新的时间复杂度O(logn)
    public void update(int index, int value) {
        if(segmentTree == null){
            throw new IllegalArgumentException("SegmentTree is null!");
        }
        segmentTree.set(index, value);
    }
}
