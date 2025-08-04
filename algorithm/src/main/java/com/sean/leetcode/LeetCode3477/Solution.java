package com.sean.leetcode.LeetCode3477;

/**
 * @Author xionghaiyang
 * @Date 2025-08-05 06:27
 * @Description https://leetcode.cn/problems/fruits-into-baskets-ii
 * 3477. 水果成篮 II
 * 给你两个长度为 n 的整数数组，fruits 和 baskets，其中 fruits[i] 表示第 i 种水果的 数量，baskets[j] 表示第 j 个篮子的 容量。
 * 你需要对 fruits 数组从左到右按照以下规则放置水果：
 * 每种水果必须放入第一个 容量大于等于 该水果数量的 最左侧可用篮子 中。
 * 每个篮子只能装 一种 水果。
 * 如果一种水果 无法放入 任何篮子，它将保持 未放置。
 * 返回所有可能分配完成后，剩余未放置的水果种类的数量。
 * n == fruits.length == baskets.length
 * 1 <= n <= 100
 * 1 <= fruits[i], baskets[i] <= 1000
 */
public class Solution {

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] hasFruit = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (baskets[j] >= fruits[i] && !hasFruit[j]) {
                    hasFruit[j] = true;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                res++;
            }
        }
        return res;
    }

}
