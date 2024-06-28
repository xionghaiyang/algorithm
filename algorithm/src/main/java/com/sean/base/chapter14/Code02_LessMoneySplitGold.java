package com.sean.base.chapter14;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-31 20:29
 * @Description: TODO
 */
public class Code02_LessMoneySplitGold {

    //纯暴力
    public int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    //等待合并的数都在arr里,pre之前的合并行为产生了多少总代价
    //arr中只剩一个数字的时候，停止合并，返回最小的总代价
    private int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                res = Math.min(res, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return res;
    }

    private int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] res = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                res[ansi++] = arr[arri];
            }
        }
        res[ansi] = arr[i] + arr[j];
        return res;
    }

    public int lessMoney2(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            sum += cur;
            heap.add(cur);
        }
        return sum;
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 4;
        int maxValue = 1000;
        Code02_LessMoneySplitGold solution = new Code02_LessMoneySplitGold();
        boolean succeed = true;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.generateRandomArray(maxSize, maxValue);
            if (solution.lessMoney1(arr) != solution.lessMoney2(arr)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
