package com.sean.base.chapter22;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-10 20:54
 * @Description: TODO
 */
public class Code03_SplitNumber {

    //n为正数
    public int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    //上一个拆出来的数是pre
    //还剩rest需要去拆
    //返回拆解的方法数
    private int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }

    public int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        Code03_SplitNumber solution = new Code03_SplitNumber();
        int n = 100;
        System.out.println("测试开始");
        for (int i = 1; i <= n; i++) {
            int res1 = solution.ways(i);
            int res2 = solution.dp1(i);
            int res3 = solution.dp2(i);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Oops!");
                System.out.println(i);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(res3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
