package com.sean.leetcode.LeetCode1105;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2023-04-23 08:08
 * @Description: https://leetcode.cn/problems/filling-bookcase-shelves
 * 1105. 填充书架
 * 给定一个数组 books ，其中 books[i] = [thicknessi, heighti] 表示第 i 本书的厚度和高度。
 * 你也会得到一个整数 shelfWidth 。
 * 按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。
 * 重复这个过程，直到把所有的书都放在书架上。
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
 * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 * 1 <= books.length <= 1000
 * 1 <= thicknessi <= shelfWidth <= 1000
 * 1 <= heighti <= 1000
 */
public class Solution {

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return process(books, shelfWidth, memo, n - 1);
    }

    private int process(int[][] books, int shelfWidth, int[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = Integer.MAX_VALUE, maxH = 0, leftW = shelfWidth;
        for (int j = i; j >= 0; j--) {
            leftW -= books[j][0];
            if (leftW < 0) {
                break;
            }
            maxH = Math.max(maxH, books[j][1]);
            res = Math.min(res, process(books, shelfWidth, memo, j - 1) + maxH);
        }
        return memo[i] = res;
    }

    public int minHeightShelves1(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int maxH = 0, leftW = shelfWidth;
            for (int j = i; j > 0; j--) {
                leftW -= books[j - 1][0];
                if (leftW < 0) {
                    break;
                }
                maxH = Math.max(maxH, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + maxH);
            }
        }
        return dp[n];
    }

}
