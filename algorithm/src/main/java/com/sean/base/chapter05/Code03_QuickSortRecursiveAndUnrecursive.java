package com.sean.base.chapter05;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-18 21:21
 * @Description: 快排的非递归版本
 */
public class Code03_QuickSortRecursiveAndUnrecursive {

    //荷兰国旗问题
    public int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //快排递归版本
    public void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    //要处理的是什么范围上的排序
    private class Op {
        public int left;
        public int right;

        public Op(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    //快排3.0非递归版本 用栈来执行
    public void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        swap(arr, (int) (Math.random() * n), n - 1);
        int[] equalArea = netherlandsFlag(arr, 0, n - 1);
        int eleft = equalArea[0];
        int eright = equalArea[1];
        Deque<Op> stack = new ArrayDeque<>();
        stack.push(new Op(0, eleft - 1));
        stack.push(new Op(eright + 1, n - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.left < op.right) {
                swap(arr, op.left + (int) (Math.random() * (op.right - op.left + 1)), op.right);
                equalArea = netherlandsFlag(arr, op.left, op.right);
                eleft = equalArea[0];
                eright = equalArea[1];
                stack.push(new Op(op.left, eleft - 1));
                stack.push(new Op(eright + 1, op.right));
            }
        }
    }

    //快排3.0非递归版本用队列来执行
    public void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        swap(arr, (int) (Math.random() * n), n - 1);
        int[] equalArea = netherlandsFlag(arr, 0, n - 1);
        int eleft = equalArea[0];
        int eright = equalArea[1];
        Queue<Op> queue = new LinkedList<>();
        queue.offer(new Op(0, eleft - 1));
        queue.offer(new Op(eright + 1, n - 1));
        while (!queue.isEmpty()) {
            Op op = queue.poll();
            if (op.left < op.right) {
                swap(arr, op.left + (int) (Math.random() * (op.right - op.left + 1)), op.right);
                equalArea = netherlandsFlag(arr, op.left, op.right);
                eleft = equalArea[0];
                eright = equalArea[1];
                queue.offer(new Op(op.left, eleft - 1));
                queue.offer(new Op(eright + 1, op.right));
            }
        }
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        Code03_QuickSortRecursiveAndUnrecursive solution = new Code03_QuickSortRecursiveAndUnrecursive();
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
            int[] arr2 = solution.copyArray(arr1);
            int[] arr3 = solution.copyArray(arr1);
            solution.quickSort1(arr1);
            solution.quickSort2(arr2);
            solution.quickSort3(arr3);
            if (!solution.isEqual(arr1, arr2) || !solution.isEqual(arr1, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过:" + (succeed ? "是" : "否"));
    }

}
