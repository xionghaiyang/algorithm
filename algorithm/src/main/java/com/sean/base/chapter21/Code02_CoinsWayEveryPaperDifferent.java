package com.sean.base.chapter21;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-08 19:55
 * @Description: TODO
 */
public class Code02_CoinsWayEveryPaperDifferent {

    public int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    //arr[index...]组成正好rest这么多的钱，有几种方法
    private int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {//没钱了
            return rest == 0 ? 1 : 0;
        } else {
            return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
        }
    }

    public int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    public int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code02_CoinsWayEveryPaperDifferent solution = new Code02_CoinsWayEveryPaperDifferent();
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 100000;
        System.out.println("测试开始!");
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int res1 = solution.coinWays(arr, aim);
            int res2 = solution.dp(arr, aim);
            if (res1 != res2) {
                System.out.println("Oops!");
                solution.printArray(arr);
                System.out.println(aim);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("测试结束!");
    }

}
