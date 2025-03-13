package com.sean.course01.lesson03;

/**
 * @Author xionghaiyang
 * @Date 2025-03-13 20:47
 * @Description 局部最小值问题
 */
public class Code04_BSAwesome {

    //arr整体无序
    //arr相邻的数不相等！
    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[n - 1] < arr[n - 2]) {
            return n - 1;
        }
        int left = 0, right = n - 1;
        while (left < right - 1) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid] > arr[mid - 1]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return arr[left] < arr[right] ? left : right;
    }

    //生成随机数组，且相邻数不相等
    private static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (maxLen * Math.random());
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (maxValue * Math.random());
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (maxValue * Math.random());
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    private static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        //boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean leftBigger = left < 0 || arr[left] > arr[minIndex];
        //boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        boolean rightBigger = right >= arr.length || arr[right] > arr[minIndex];
        return leftBigger && rightBigger;
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int res = oneMinIndex(arr);
            if (!check(arr, res)) {
                printArray(arr);
                System.out.println(res);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
