package com.sean.leetcode.LeetCode3362;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-05-22 05:39
 * @Description https://leetcode.cn/problems/zero-array-transformation-iii
 * 3362. 零数组变换 III
 * 给你一个长度为 n 的整数数组 nums 和一个二维数组 queries ，其中 queries[i] = [li, ri] 。
 * 每一个 queries[i] 表示对于 nums 的以下操作：
 * 将 nums 中下标在范围 [li, ri] 之间的每一个元素 最多 减少 1 。
 * 坐标范围内每一个元素减少的值相互 独立 。
 * 零数组 指的是一个数组里所有元素都等于 0 。
 * 请你返回 最多 可以从 queries 中删除多少个元素，使得 queries 中剩下的元素仍然能将 nums 变为一个 零数组 。
 * 如果无法将 nums 变为一个 零数组 ，返回 -1 。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= li <= ri < nums.length
 */
public class Solution {

    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int n = nums.length;
        int m = queries.length;
        int[] diff = new int[n + 1];
        int sum = 0, j = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            while (j < m && queries[j][0] <= i) {
                heap.offer(queries[j][1]);
                j++;
            }
            while (sum < nums[i] && !heap.isEmpty() && heap.peek() >= i) {
                sum++;
                diff[heap.poll() + 1]--;
            }
            if (sum < nums[i]) {
                return -1;
            }
        }
        return heap.size();
    }

}
