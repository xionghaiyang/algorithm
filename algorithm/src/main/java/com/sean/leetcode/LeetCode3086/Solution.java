package com.sean.leetcode.LeetCode3086;

/**
 * @Author xionghaiyang
 * @Date 2024-07-04 06:38
 * @Description https://leetcode.cn/problems/minimum-moves-to-pick-k-ones/
 * 3086. 拾起 K 个 1 需要的最少行动次数
 * 给你一个下标从 0 开始的二进制数组 nums，其长度为 n ；
 * 另给你一个 正整数 k 以及一个 非负整数 maxChanges 。
 * Alice 在玩一个游戏，游戏的目标是让 Alice 使用 最少 数量的 行动 次数从 nums 中拾起 k 个 1 。
 * 游戏开始时，Alice 可以选择数组 [0, n - 1] 范围内的任何索引 aliceIndex 站立。
 * 如果 nums[aliceIndex] == 1 ，Alice 会拾起一个 1 ，并且 nums[aliceIndex] 变成0（这 不算 作一次行动）。
 * 之后，Alice 可以执行 任意数量 的 行动（包括零次），在每次行动中 Alice 必须 恰好 执行以下动作之一：
 * 选择任意一个下标 j != aliceIndex 且满足 nums[j] == 0 ，然后将 nums[j] 设置为 1 。这个动作最多可以执行 maxChanges 次。
 * 选择任意两个相邻的下标 x 和 y（|x - y| == 1）且满足 nums[x] == 1, nums[y] == 0 ，然后交换它们的值（将 nums[y] = 1 和 nums[x] = 0）。
 * 如果 y == aliceIndex，在这次行动后 Alice 拾起一个 1 ，并且 nums[y] 变成 0 。
 * 返回 Alice 拾起 恰好 k 个 1 所需的 最少 行动次数
 * 2 <= n <= 10^5
 * 0 <= nums[i] <= 1
 * 1 <= k <= 10^5
 * 0 <= maxChanges <= 10^5
 * maxChanges + sum(nums) >= k
 */
public class Solution {

    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        long[] indexSum = new long[n + 1];
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            indexSum[i + 1] = indexSum[i] + nums[i] * i;
            sum[i + 1] = sum[i] + nums[i];
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (process(i, nums) + maxChanges >= k) {
                if (k <= process(i, nums)) {
                    res = Math.min(res, (long) k - nums[i]);
                } else {
                    res = Math.min(res, (long) 2 * k - process(i, nums) - nums[i]);
                }
                continue;
            }
            int left = 0, right = n;
            while (left <= right) {
                int mid = (left + right) / 2;
                int i1 = Math.max(i - mid, 0), i2 = Math.min(i + mid, n - 1);
                if (sum[i2 + 1] - sum[i1] >= k - maxChanges) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            int i1 = Math.max(i - left, 0), i2 = Math.min(i + left, n - 1);
            if (sum[i2 + 1] - sum[i1] > k - maxChanges) {
                i1++;
            }
            long count1 = sum[i + 1] - sum[i1], count2 = sum[i2 + 1] - sum[i + 1];
            res = Math.min(res, indexSum[i2 + 1] - indexSum[i + 1] - i * count2 + i * count1 - (indexSum[i + 1] - indexSum[i1]) + 2 * maxChanges);
        }
        return res;
    }

    private int process(int i, int[] nums) {
        int n = nums.length;
        int res = nums[i];
        if (i - 1 >= 0) {
            res += nums[i - 1];
        }
        if (i + 1 < n) {
            res += nums[i + 1];
        }
        return res;
    }

    public long minimumMoves1(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int left = 0, right = -1;
        long leftSum = 0, rightSum = 0;
        long leftCount = 0, rightCount = 0;
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (process(i, nums) + maxChanges >= k) {
                if (k <= process(i, nums)) {
                    res = Math.min(res, (long) k - nums[i]);
                } else {
                    res = Math.min(res, (long) 2 * k - process(i, nums) - nums[i]);
                }
            }
            if (k <= maxChanges) {
                continue;
            }
            while (right + 1 < n && (right - i < i - left || leftCount + rightCount + maxChanges < k)) {
                if (nums[right + 1] == 1) {
                    rightCount++;
                    rightSum += right + 1;
                }
                right++;
            }
            while (leftCount + rightCount + maxChanges > k) {
                if (right - i < i - left || right - i == i - left && nums[left] == 1) {
                    if (nums[left] == 1) {
                        leftCount--;
                        leftSum -= left;
                    }
                    left++;
                } else {
                    if (nums[right] == 1) {
                        rightCount--;
                        rightSum -= right;
                    }
                    right--;
                }
            }
            res = Math.min(res, leftCount * i - leftSum + rightSum - rightCount * i + 2 * maxChanges);
            if (nums[i] == 1) {
                leftCount++;
                leftSum += i;
                rightCount--;
                rightSum -= i;
            }
        }
        return res;
    }

}
