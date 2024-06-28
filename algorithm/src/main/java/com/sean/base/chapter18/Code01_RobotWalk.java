package com.sean.base.chapter18;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-03 19:25
 * @Description: TODO
 */
public class Code01_RobotWalk {

    public int ways1(int n, int start, int aim, int k) {
        if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
            return -1;
        }
        return process1(start, k, aim, n);
    }

    //机器人当前来到的位置是cur
    //机器人还有rest步需要去走
    //最终的目标是aim
    //有哪些位置?1-N
    //返回:机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少?
    private int process1(int cur, int rest, int aim, int n) {
        if (rest == 0) {//如果已经不需要走了，走完了
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(2, rest - 1, aim, n);
        }
        if (cur == n) {
            return process1(n - 1, rest - 1, aim, n);
        }
        return process1(cur - 1, rest - 1, aim, n) + process1(cur + 1, rest - 1, aim, n);
    }

    public int ways2(int n, int start, int aim, int k) {
        if (n < 2 || start < 1 || start > n || aim < 2 || aim > n || k < 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, k, aim, n, dp);
    }

    private int process2(int cur, int rest, int aim, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int res = 0;
        if (rest == 0) {
            res = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            res = process2(2, rest - 1, aim, n, dp);
        } else if (cur == n) {
            res = process2(n - 1, rest - 1, aim, n, dp);
        } else {
            res = process2(cur - 1, rest - 1, aim, n, dp) + process2(cur + 1, rest - 1, aim, n, dp);
        }
        dp[cur][rest] = res;
        return res;
    }

    public int ways3(int n, int start, int aim, int k) {
        if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][k + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[n][rest] = dp[n - 1][rest - 1];
        }
        return dp[start][k];
    }

    public static void main(String[] args) {
        Code01_RobotWalk solution = new Code01_RobotWalk();
        System.out.println(solution.ways1(5, 2, 4, 6));
        System.out.println(solution.ways2(5, 2, 4, 6));
        System.out.println(solution.ways3(5, 2, 4, 6));
    }

}
