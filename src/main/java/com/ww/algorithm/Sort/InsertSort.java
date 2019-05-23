package com.ww.algorithm.Sort;

import org.springframework.stereotype.Component;

/*
插入排序：插入排序类算法，算法平均复杂度O(n²)，稳定，支持原地排序
类似纸牌游戏中，抓牌理牌的思路。
1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
3.针对所有的元素重复以上的步骤，除了最后一个。
4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
*/

@Component
public class InsertSort {

    public void insertSort(int[] arr) throws Exception {

        int length = arr.length;//数组长度，将这个提取出来是为了提高速度。
        int insertNum;//待插入数
        for (int i = 1; i < length; i++) {//首先设定插入次数，即循环次数，从第2个数开始~最后一个数，逐个与左侧有序数组数值比较。
            insertNum = arr[i];//设定本次循环的待插入数
            int j = i - 1;//左侧有序数组的最后一个数的index
            while (j >= 0 && arr[j] > insertNum) {//从左侧有序数组最后一个数arr\[j]开始向前遍历j--，如果待插入数insertNum小于当前值arr[j]
                arr[j + 1] = arr[j];//将当前值arr\[j]向后移动一位
                j--;
            }
            arr[j + 1] = insertNum;//如果待插入数insertNum>=当前值arr\[j]，将当前数放到空着的位置，即j+1。
        }
    }
}
