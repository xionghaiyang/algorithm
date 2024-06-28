package com.sean.base.chapter21;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-08 20:09
 * @Description: TODO
 */
public class Code03_CoinsWayNoLimit {

    public int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    //arr[index...]所有的面值,每一个面值都可以任意选择樟树，组成正好rest这么多钱，方法数多少?
    private int process(int[] arr, int index, int rest) {
        if (index == arr.length) {//没钱了
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }

    public int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - zhang * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        Code03_CoinsWayNoLimit solution = new Code03_CoinsWayNoLimit();
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int res1 = solution.coinsWay(arr, aim);
            int res2 = solution.dp1(arr, aim);
            int res3 = solution.dp2(arr, aim);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Oops!");
                solution.printArray(arr);
                System.out.println(aim);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(res3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
