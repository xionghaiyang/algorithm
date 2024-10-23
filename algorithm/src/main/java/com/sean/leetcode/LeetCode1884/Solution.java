package com.sean.leetcode.LeetCode1884;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-10-23 18:51
 * @Description https://leetcode.cn/problems/egg-drop-with-2-eggs-and-n-floors
 * 1884. 鸡蛋掉落-两枚鸡蛋
 * 给你 2 枚相同 的鸡蛋，和一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都 会碎 ，从 f 楼层或比它低 的楼层落下的鸡蛋都 不会碎 。
 * 每次操作，你可以取一枚 没有碎 的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
 * 如果鸡蛋碎了，你就不能再次使用它。
 * 如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 */
public class Solution {

    public int twoEggDrop(int n) {
        //记dp[i]表示i层楼的建筑需要的最小的操作次数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= i; k++) {
                dp[i] = Math.min(dp[i], Math.max(k - 1, dp[i - k]) + 1);
            }
        }
        return dp[n];
    }

}
