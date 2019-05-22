package com.ww.algorithm.Sort;

import org.springframework.stereotype.Component;

/*
快速排序：交换排序类算法，算法平均复杂度O(nlogn)，不稳定，支持原地排序。
1.从数组中挑出一个元素，例如第一个元素，作为 “基准值std”。
2.挖坑分治法：比基准值std小的向左移动，比基准值std大的向右移动,一直到左右index重合(此位置leftIndex==rightIndex)，把标准值std放到该index上。
3.基准值左右两侧继续递归重复上述过程，一直到左右两侧index重合。
*/
@Component
public class QuickSort {

    public void quickSort(int[] arr, int startIndex, int endIndex) {

        if (startIndex < endIndex) {//递归结束条件
            //把数组中第0个数值作为标准数
            int std = arr[startIndex];
            //排序过程中需要移动下标leftIndex和rightIndex
            int leftIndex = startIndex;
            int rightIndex = endIndex;

            //循环遍历数组左右两侧数值，找出比标准值std小的数移动到左边，比标准值std大的数移动到右边，
            // 一直到左右index重合(此位置leftIndex==rightIndex)，把标准值std放到该index上。
            while (leftIndex < rightIndex) {
                //因为标准值std选取的左边第1个，所以从最右边rightIndex指向元素开始比较：
                //1.如果右边的数值比标准值std大，rightIndex向前偏移看前一个数值
                while (leftIndex < rightIndex && std <= arr[rightIndex]) {
                    rightIndex--;
                }
                //2.如果右边的数值比标准值std小，右侧数值移动到左侧leftIndex位置
                arr[leftIndex] = arr[rightIndex];

                //接下来看左边
                //3.如果左边的数值比标准值std小，leftIndex向后偏移看后一个数值
                while (leftIndex < rightIndex && arr[leftIndex] <= std) {
                    leftIndex++;
                }
                //4.如果左边的数值比标准值std大，左侧数值移动到右侧
                arr[rightIndex] = arr[leftIndex];
            }

            //完成全部元素移动后，左右index重合(此位置leftIndex==rightIndex)，把标准值std放到该index上。
            arr[leftIndex] = std;

            //首次快速排序完成后，左右两边分别再递归同样的过程快速排序
            //std左侧递归快速排序
            quickSort(arr, startIndex, leftIndex);

            //std右侧递归快速排序
            quickSort(arr, leftIndex + 1, endIndex);
        }
    }
}
