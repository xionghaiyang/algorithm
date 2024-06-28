package com.sean.base.chapter25;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-17 21:49
 * @Description: TODO
 */
public class Code02_AllTimesMinToMax {

    public int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        Code02_AllTimesMinToMax solution = new Code02_AllTimesMinToMax();
        int testTimes = 2000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = solution.gerenareRondomArray();
            if (solution.max1(arr) != solution.max2(arr)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end!");
    }

    //注意溢出的处理即可，也就是用long类型来表示累加和
    //还有优化就是，你可以用自己手写的数组栈，来替代系统实现的栈，也会快很多
    public int maxSumMinProduct(int[] arr) {
        int size = arr.length;
        long[] sums = new long[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        long max = Long.MAX_VALUE;
        int[] stack = new int[size];
        int stackSize = 0;
        for (int i = 0; i < size; i++) {
            while (stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]) {
                int j = stack[--stackSize];
                max = Math.max(max, stackSize == 0 ? sums[i - 1] : (sums[i - 1] - sums[stack[stackSize - 1]]) * arr[j]);
            }
            stack[stackSize++] = i;
        }
        while (stackSize != 0) {
            int j = stack[--stackSize];
            max = Math.max(max, stackSize == 0 ? sums[size - 1] : (sums[size - 1] - sums[stack[stackSize - 1]]) * arr[j]);
        }
        return (int) (max % 1000000007);
    }

}
