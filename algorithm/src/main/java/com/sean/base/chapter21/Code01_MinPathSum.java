package com.sean.base.chapter21;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-08 19:40
 * @Description: TODO
 */
public class Code01_MinPathSum {

    public int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }

    public int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != res.length; i++) {
            for (int j = 0; j != res[0].length; j++) {
                res[i][j] = (int) (Math.random() * 100);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Code01_MinPathSum solution = new Code01_MinPathSum();
        int rowSize = 10;
        int colSize = 10;
        int testTime = 100000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            int[][] m = solution.generateRandomMatrix(rowSize, colSize);
            if (solution.minPathSum1(m) != solution.minPathSum2(m)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

}
