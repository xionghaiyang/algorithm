package com.sean.course03.lesson01;

import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-03-31 20:00
 * @Description 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        //[left..right)
        int left = 0, right = 0;
        int res = 0;
        while (left < n) {
            while (right < n) {
                //小 -> 大
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[right]) {
                    qMin.pollLast();
                }
                qMin.addLast(right);
                //大 -> 小
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[right]) {
                    qMax.pollLast();
                }
                qMax.addLast(right);
                if (arr[qMax.getFirst()] - arr[qMin.getFirst()] > num) {
                    break;
                }
                right++;
            }
            res += right - left;
            if (qMin.peekFirst() == left) {
                qMin.pollFirst();
            }
            if (qMax.peekFirst() == left) {
                qMax.pollFirst();
            }
            left++;
        }
        return res;
    }

    private static int rightWay(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int max = arr[i];
            int min = arr[i];
            if (max - min <= num) {
                res++;
            }
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (max - min <= num) {
                    res++;
                }
            }
        }
        return res;
    }

    private static int[] getRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    private static void printArray(int[] arr) {
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
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = getRandomArray(maxSize, maxValue);
            int num = (int) (maxValue * Math.random());
            int res1 = getNum(arr, num);
            int res2 = rightWay(arr, num);
            if (res1 != res2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "ERROR!");
    }

}
