package com.sean.leetcode.LeetCode2500;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 20:42
 * @Description: https://leetcode.cn/problems/delete-greatest-value-in-each-row/
 * 2500. 删除每行中的最大值
 * 给你一个 m x n 大小的矩阵 grid ，由若干正整数组成。
 * 执行下述操作，直到 grid 变为空矩阵：
 * 从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。
 * 将删除元素中的最大值与答案相加。
 * 注意 每执行一次操作，矩阵中列的数据就会减 1 。
 * 返回执行上述操作后的答案。
 */
public class Solution {

    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int[] arr : grid) {
            Arrays.sort(arr);
        }
        int res = 0;
        int max = 0;
        for (int j = 0; j < n; j++) {
            max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, grid[i][j]);
            }
            res += max;
        }
        return res;
    }

}
