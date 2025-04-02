package com.sean.course03.lesson01;

/**
 * @Author xionghaiyang
 * @Date 2025-04-02 11:00
 * @Description 马从(0, 0)出发，有k步要走，并且一定要走完，最终来到x，y位置的方法数是多少
 */
public class Code06_HorseJump {

    public static int ways1(int x, int y, int k) {
        return f(x, y, k);
    }

    private static int f(int x, int y, int k) {
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return f(x + 2, y - 1, k - 1)
                + f(x + 2, y + 1, k - 1)
                + f(x + 1, y + 2, k - 1)
                + f(x - 1, y + 2, k - 1)
                + f(x - 2, y + 1, k - 1)
                + f(x - 2, y - 1, k - 1)
                + f(x - 1, y - 2, k - 1)
                + f(x + 1, y - 2, k - 1);
    }

    public static int ways2(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        //dp[...][...][0] = 0;
        dp[0][0][0] = 1;
        for (int level = 1; level <= k; level++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][level] = getValue(dp, i + 2, j - 1, level - 1)
                            + getValue(dp, i + 2, j + 1, level - 1)
                            + getValue(dp, i + 1, j + 2, level - 1)
                            + getValue(dp, i - 1, j + 2, level - 1)
                            + getValue(dp, i - 2, j + 1, level - 1)
                            + getValue(dp, i - 2, j - 1, level - 1)
                            + getValue(dp, i - 1, j - 2, level - 1)
                            + getValue(dp, i + 1, j - 2, level - 1);
                }
            }
        }
        return dp[x][y][k];
    }

    private static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int x = 6;
        int y = 8;
        int k = 10;
        System.out.println(ways1(x, y, k));
        System.out.println(ways2(x, y, k));
    }

}
