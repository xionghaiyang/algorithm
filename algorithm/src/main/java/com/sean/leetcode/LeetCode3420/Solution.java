package com.sean.leetcode.LeetCode3420;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-13 06:18
 * @Description: https://leetcode.cn/problems/count-non-decreasing-subarrays-after-k-operations
 * 3420. 统计 K 次操作以内得到非递减子数组的数目
 * 给你一个长度为 n 的数组 nums 和一个整数 k 。
 * 对于 nums 中的每一个子数组，你可以对它进行 至多 k 次操作。
 * 每次操作中，你可以将子数组中的任意一个元素增加 1 。
 * 注意 ，每个子数组都是独立的，也就是说你对一个子数组的修改不会保留到另一个子数组中。
 * 请你返回最多 k 次操作以内，有多少个子数组可以变成 非递减 的。
 * 如果一个数组中的每一个元素都大于等于前一个元素（如果前一个元素存在），那么我们称这个数组是 非递减 的。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 */
public class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long res = 0, cnt = 0;
        //(根节点的值，树的大小)
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (int left = n - 1, right = n - 1; left >= 0; left--) {
            int x = nums[left];
            //停机以x为根的树的大小
            int size = 1;
            while (!linkedList.isEmpty() && x >= linkedList.peekFirst()[0]) {
                int[] tree = linkedList.pollFirst();
                int v = tree[0], sz = tree[1];
                size += sz;
                cnt += (long) (x - v) * sz;
            }
            linkedList.addFirst(new int[]{x, size});
            while (cnt > k) {
                int[] tree = linkedList.peekLast();
                cnt -= tree[0] - nums[right];
                right--;
                tree[1]--;
                if (tree[1] == 0) {
                    linkedList.pollLast();
                }
            }
            res += right - left + 1;
        }
        return res;
    }

}
