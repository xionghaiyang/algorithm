package com.sean.base.chapter40;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-19 22:21
 * @Description: TODO
 */
public class Code01_LongestSumSubArrayLengthInPositiveArray {

    public int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == K) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < K) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    private boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    public int[] generatePositiveArray(int size, int value) {
        int[] res = new int[size];
        for (int i = 0; i != size; i++) {
            res[i] = (int) (Math.random() * value) + 1;
        }
        return res;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code01_LongestSumSubArrayLengthInPositiveArray solution = new Code01_LongestSumSubArrayLengthInPositiveArray();
        int len = 50;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int res1 = solution.getMaxLength(arr, K);
            int res2 = solution.right(arr, K);
            if (res1 != res2) {
                System.out.println("Oops!");
                solution.printArray(arr);
                System.out.println("K: " + K);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("test end");
    }

}
