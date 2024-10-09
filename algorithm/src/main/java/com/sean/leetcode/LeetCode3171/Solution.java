package com.sean.leetcode.LeetCode3171;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-10-09 23:06
 * @Description https://leetcode.cn/problems/find-subarray-with-bitwise-or-closest-to-k/
 * 3171. 找到按位或最接近 K 的子数组
 * 给你一个数组 nums 和一个整数 k 。
 * 你需要找到 nums 的一个子数组，满足子数组中所有元素按位或运算 OR 的值与 k 的 绝对差 尽可能 小 。
 * 换言之，你需要选择一个子数组 nums[l..r] 满足 |k - (nums[l] OR nums[l + 1] ... OR nums[r])| 最小。
 * 请你返回 最小 的绝对差值。
 * 子数组 是数组中连续的 非空 元素序列。
 */
public class Solution {

    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int[] bitsMaxPos = new int[31];
        Arrays.fill(bitsMaxPos, -1);
        List<int[]> posToBit = new ArrayList<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 30; j++) {
                if ((nums[i] >> j & 1) != 0) {
                    bitsMaxPos[j] = i;
                }
            }
            posToBit.clear();
            for (int j = 0; j <= 30; j++) {
                if (bitsMaxPos[j] != -1) {
                    posToBit.add(new int[]{bitsMaxPos[j], j});
                }
            }
            Collections.sort(posToBit, (a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
            int val = 0;
            for (int j = 0, p = 0; j < posToBit.size(); ) {
                while (j < posToBit.size() && posToBit.get(j)[0] == posToBit.get(p)[0]) {
                    val |= 1 << posToBit.get(j)[1];
                    j++;
                }
                res = Math.min(res, Math.abs(val - k));
                p = j;
            }
        }
        return res;
    }

}
