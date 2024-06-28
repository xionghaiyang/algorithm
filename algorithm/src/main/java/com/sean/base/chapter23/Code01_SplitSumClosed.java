package com.sean.base.chapter23;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-15 19:55
 * @Description: TODO
 */
public class Code01_SplitSumClosed {

    public int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(arr, 0, sum / 2);
    }

    //arr[i...]可以自由选择，请返回累加和尽量接近rest，但不能超过rest的情况下,最接近的累加和是多少
    private int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        } else {//还有数,arr[i]这个数
            //可能性1，不使用arr[i]
            int p1 = process(arr, i + 1, rest);
            //可能性2,要使用arr[i]
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
            }
            return Math.max(p1, p2);
        }
    }

    public int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                //可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                //可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code01_SplitSumClosed solution = new Code01_SplitSumClosed();
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = solution.randomArray(len, maxValue);
            int res1 = solution.right(arr);
            int res2 = solution.dp(arr);
            if (res1 != res2) {
                solution.printArray(arr);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
