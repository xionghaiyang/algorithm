package com.sean.leetcode.LeetCode3876;

/**
 * @Author xionghaiyang
 * @Date 2026-03-28 19:28
 * @Description https://leetcode.cn/problems/construct-uniform-parity-array-ii
 * 3876. 构造奇偶一致的数组 II
 * 给你一个长度为 n 的数组 nums1，其中包含 互不相同 的整数。
 * 你需要构造另一个长度为 n 的数组 nums2，使得 nums2 中的元素要么全部为 奇数，要么全部为 偶数。
 * 对于每个下标 i，你必须从以下两种选择中 任选其一（顺序不限）：
 * nums2[i] = nums1[i]​​​​​​​
 * nums2[i] = nums1[i] - nums1[j]，其中 j != i，且满足 nums1[i] - nums1[j] >= 1
 * 如果能够构造出满足条件的数组，则返回 true；否则，返回 false。
 * 1 <= n == nums1.length <= 10^5
 * 1 <= nums1[i] <= 10^9
 * nums1 中的所有整数互不相同。
 */
public class Solution {

    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int odd = 0, even = 0, minOdd = Integer.MAX_VALUE, minEven = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((nums1[i] & 1) == 1) {
                odd++;
                minOdd = Math.min(minOdd, nums1[i]);
            } else {
                even++;
                minEven = Math.min(minEven, nums1[i]);
            }
        }
        return odd == 0 || even == 0 || minEven > minOdd;
    }

}
