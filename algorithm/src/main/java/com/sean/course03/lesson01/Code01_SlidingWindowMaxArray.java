package com.sean.course03.lesson01;

import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-03-29 22:51
 * @Description 假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class Code01_SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int n = arr.length;
        //大 -> 小
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[n - w + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
            if (qMax.peekFirst() == i - w) {
                qMax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    private static int[] rightWay(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int n = arr.length;
        int[] res = new int[n - w + 1];
        int index = 0;
        int left = 0;
        int right = w - 1;
        while (right < n) {
            int max = arr[left];
            for (int i = left + 1; i <= right; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            left++;
            right++;
        }
        return res;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
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

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) ((arr.length + 1) * Math.random());
            int[] res1 = getMaxWindow(arr, w);
            int[] res2 = rightWay(arr, w);
            if (!isEqual(res1, res2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
