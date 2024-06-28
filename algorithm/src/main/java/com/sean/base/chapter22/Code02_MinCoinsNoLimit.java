package com.sean.base.chapter22;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-10 19:23
 * @Description: TODO
 */
public class Code02_MinCoinsNoLimit {

    public int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    //arr[index..]面值，每种面值张数自由选择
    //高出rest正好这么多钱，返回最小樟树
    //拿Integer.MAX_VALUE标记怎么都搞定不了
    private int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int res = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                int next = process(arr, index + 1, rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    res = Math.min(res, zhang + next);
                }
            }
            return res;
        }
    }

    public int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        res = Math.min(res, zhang + next);
                    }
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][aim];
    }

    public int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
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
        Code02_MinCoinsNoLimit solution = new Code02_MinCoinsNoLimit();
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = solution.randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int res1 = solution.minCoins(arr, aim);
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
