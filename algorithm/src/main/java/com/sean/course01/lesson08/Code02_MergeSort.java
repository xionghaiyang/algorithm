package com.sean.course01.lesson08;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-26 20:09
 * @Description 归并排序
 */
public class Code02_MergeSort {

    //递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //请让arr[left...right]范围上的数有序
    private static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界，不可能同时越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int n = arr.length;
        while (step < n) {
            int left = 0;
            while (left < n) {
                int mid = 0;
                if (n - left >= step) {
                    mid = left + step - 1;
                } else {
                    mid = n - 1;
                }
                if (mid == n - 1) {
                    break;
                }
                int right = 0;
                if (n - 1 - mid >= step) {
                    right = mid + step;
                } else {
                    right = n - 1;
                }
                merge(arr, left, mid, right);
                if (right == n - 1) {
                    break;
                } else {
                    left = right + 1;
                }
            }
            if (step > n / 2) {
                break;
            }
            step *= 2;
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null ^ arr2 == null) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            Arrays.sort(arr3);
            if (!isEqual(arr1, arr3) || !isEqual(arr2, arr3)) {
                System.out.println("出错了");
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
            }
        }
        System.out.println("测试结束");
    }

}
