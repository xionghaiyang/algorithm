package com.sean.course03.lesson01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-03-31 20:28
 * @Description 单调栈
 * 一种特别设计的栈结构，为了解决如下的问题：
 * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
 * 1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
 * 那么到底怎么设计呢？
 */
public class Code03_MonotonousStack {

    //数组arr值不重复，求i位置的数左侧离i最近的数在哪，右侧离i最近的数在哪
    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null) {
            return null;
        }
        int n = arr.length;
        int[][] res = new int[n][2];
        //头 -> 尾，小 -> 大
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    //数组arr值有重复，求i位置的数左侧离i最近的数在哪，右侧离i最近的数在哪
    public static int[][] getNearLess(int[] arr) {
        if (arr == null) {
            return null;
        }
        int n = arr.length;
        int[][] res = new int[n][2];
        //底 -> 顶，小 -> 大
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int popI : popIs) {
                    res[popI][0] = leftLessIndex;
                    res[popI][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int popI : popIs) {
                res[popI][0] = leftLessIndex;
                res[popI][1] = -1;
            }
        }

        return res;
    }

    private static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (arr.length * Math.random());
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    private static int[] getRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    private static int[][] rightWay(int[] arr) {
        if (arr == null) {
            return null;
        }
        int n = arr.length;
        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < n) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    private static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }
        return true;
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
        int testTime = 200000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = getRandomArrayNoRepeat(maxSize);
            int[] arr2 = getRandomArray(maxSize, maxValue);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("出错了1");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("出错了2");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
