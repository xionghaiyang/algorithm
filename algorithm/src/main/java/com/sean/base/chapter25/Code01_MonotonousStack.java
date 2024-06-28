package com.sean.base.chapter25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-17 21:09
 * @Description: TODO
 */
public class Code01_MonotonousStack {

    // arr = [3 ,1 ,2 ,3]
    //        0  1  2  3
    //[
    //  0 : [-1,1]
    //  1 : [-1,-1]
    //  2 : [1,-1]
    //  3 : [2,-1]
    // ]
    public int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        //只存位置
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {//当遍历到i位置的数,arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int j = stack.pop();
                res[j][0] = stack.isEmpty() ? -1 : stack.peek();
                res[j][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            res[j][0] = stack.isEmpty() ? -1 : stack.peek();
            res[j][1] = -1;
        }
        return res;
    }

    public int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {//i->arr[i]进栈
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
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
            for (int popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    public int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
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
            while (cur < arr.length) {
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

    public int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    public int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    public boolean isEqual(int[][] res1, int[][] res2) {
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

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code01_MonotonousStack solution = new Code01_MonotonousStack();
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = solution.getRandomArrayNoRepeat(size);
            int[] arr2 = solution.getRandomArray(size, max);
            if (!solution.isEqual(solution.getNearLessNoRepeat(arr1), solution.rightWay(arr1))) {
                System.out.println("Oops!");
                solution.printArray(arr1);
                break;
            }
            if (!solution.isEqual(solution.getNearLess(arr2), solution.rightWay(arr2))) {
                System.out.println("Oops!");
                solution.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
