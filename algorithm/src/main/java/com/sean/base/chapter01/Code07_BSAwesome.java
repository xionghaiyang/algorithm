package com.sean.base.chapter01;

/**
 * @Author xionghaiyang
 * @Date 2022-08-06 08:46
 * @Description 局部最小值问题
 */
public class Code07_BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (maxSize * Math.random()) + 1];
        arr[0] = (int) (maxValue * Math.random()) - (int) (maxValue * Math.random());
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (maxValue * Math.random()) - (int) (maxValue * Math.random());
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    public static boolean isRight(int[] arr, int index) {
        if (arr.length <= 1) {
            return true;
        }
        if (index == 0) {
            return arr[index] < arr[index + 1];
        }
        if (index == arr.length - 1) {
            return arr[index] < arr[index - 1];
        }
        return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + " ");
            } else {
                System.out.println(arr[i]);
            }
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = getLessIndex(arr);
            if (!isRight(arr, res)) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Terrible");
    }

}
