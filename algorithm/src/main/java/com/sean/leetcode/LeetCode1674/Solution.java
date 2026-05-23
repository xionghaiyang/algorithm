package com.sean.leetcode.LeetCode1674;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-13 06:25
 * @Description: https://leetcode.cn/problems/minimum-moves-to-make-array-complementary
 * 1674. 使数组互补的最少操作次数
 * 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。
 * 每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。
 * 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。
 * 例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。
 * 返回使数组 互补 的 最少 操作次数。
 * n == nums.length
 * 2 <= n <= 10^5
 * 1 <= nums[i] <= limit <= 10^5
 * n 是偶数。
 */
public class Solution {

    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];
        for (int i = 0; i < n / 2; i++) {
            int x = Math.min(nums[i], nums[n - 1 - i]);
            int y = Math.max(nums[i], nums[n - 1 - i]);
            //[2,x+1) += 2
            //[x+1,x+y) += 1
            //x+y 无需修改
            //(x+y,y + limit] += 1
            //(y + limit,2 * limit] += 2
            diff[2] += 2;
            diff[x + 1]--;
            diff[x + y]--;
            diff[x + y + 1]++;
            diff[y + limit + 1]++;
        }
        int res = n;
        for (int z = 2, sum = 0; z <= limit * 2; z++) {
            sum += diff[z];
            res = Math.min(res, sum);
        }
        return res;
    }

}
