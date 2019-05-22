package com.ww.algorithm.Sort;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
冒泡排序：交换排序类算法，算法平均复杂度O(n²)，稳定，支持原地排序
1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
3.针对所有的元素重复以上的步骤，除了最后一个。
4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
*/

@Component
public class BubbleSort {

    public  int[] bubbleSort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        //前一个元素与后一个元素比较，直到倒数第2个元素
        for (int i = 0; i < arr.length - 1; i++) {
            // 设定一个标记bSwap，若为false，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean bSwap = false;
            //只比较交换i之前的元素即可
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前一个元素比后一个元素大
                if (arr[j] > arr[j + 1]) {
                    //交换2个元素
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    bSwap = true;
                    if (!bSwap) {
                        break;
                    }
                }
            }
        }
        return arr;
    }
}
