package com.sean.course01.lesson08;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-03-26 20:58
 * @Description 随机快速排序
 */
public class Code03_PartitionAndQuickSort {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //以arr[n-1]为划分值，将arr分为两个部分
    //左边小于等于区域，右边大于区域
    public static void splitNum1(int[] arr) {
        //小于等于区域的右边界
        int lessEqualR = -1;
        int index = 0;
        int n = arr.length;
        while (index < n) {
            if (arr[index] <= arr[n - 1]) {
                //与小于等于区域下一个数交换
                swap(arr, ++lessEqualR, index++);
            } else {
                index++;
            }
        }
    }

    //以arr[n-1]为划分值，将arr分为三个部分
    //左边为小于区域，中间为等于区域，右边为大于区域
    public static void splitNum2(int[] arr) {
        int n = arr.length;
        //小于区域右边界
        int lessR = -1;
        //大于区域左边界
        int moreL = n - 1;
        int index = 0;
        while (index < moreL) {
            if (arr[index] < arr[n - 1]) {
                //与小于等于区域右边界下一个数交换
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[n - 1]) {
                //与大于区域左边界下一个数交换
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, n - 1);
    }

    //在arr[left...right]范围上，拿arr[right]做划分值
    //小于区域，等于区域和大于区域
    //返回等于区域的范围
    public static int[] partition(int[] arr, int left, int right) {
        int lessR = left - 1;
        int moreL = right;
        int index = left;
        while (index < moreL) {
            if (arr[index] < arr[right]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[right]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, right);
        return new int[]{lessR + 1, moreL};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equalE = partition(arr, left, right);
        process(arr, left, equalE[0] - 1);
        process(arr, equalE[1] + 1, right);
    }

    public static class Job {
        public int left;
        public int right;

        public Job(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Deque<Job> stack = new ArrayDeque<Job>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job cur = stack.pop();
            int[] equals = partition(arr, cur.left, cur.right);
            //有小于区域
            if (equals[0] > cur.left) {
                stack.push(new Job(cur.left, equals[0] - 1));
            }
            //有大于区域
            if (equals[1] < cur.right) {
                stack.push(new Job(equals[1] + 1, cur.right));
            }
        }
    }

    //荷兰国旗问题
    public static int[] netherlandsFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int less = left - 1;
        int more = right;
        int index = left;
        while (index < more) {
            if (arr[index] == arr[right]) {
                index++;
            } else if (arr[index] < arr[right]) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, --more, index);
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int) ((right - left + 1) * Math.random()), right);
        int[] equalArea = netherlandsFlag(arr, left, right);
        process3(arr, left, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, right);
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
        int[] arr = {7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 4};
        splitNum2(arr);
        printArray(arr);
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("测试结束");
    }

}
