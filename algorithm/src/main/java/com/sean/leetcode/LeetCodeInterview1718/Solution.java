package com.sean.leetcode.LeetCodeInterview1718;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-03-26 20:51
 * @Description https://leetcode.cn/problems/shortest-supersequence-lcci
 * 面试题 17.18. 最短超串
 * 假设你有两个数组，一个长一个短，短的元素均不相同。
 * 找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。
 * 若不存在，返回空数组。
 * big.length <= 100000
 * 1 <= small.length <= 100000
 */
public class Solution {

    public int[] shortestSeq(int[] big, int[] small) {
        int m = big.length, n = small.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : small) {
            map.merge(num, -1, Integer::sum);
        }
        int start = -1, len = Integer.MAX_VALUE;
        int count = 0;
        int left = 0, right = 0;
        while (right < m) {
            int cur = big[right];
            if (map.containsKey(cur)) {
                map.merge(cur, 1, Integer::sum);
                if (map.get(cur) == 0) {
                    count++;
                }
            }
            while (count == n) {
                if (right - left + 1 < len) {
                    start = left;
                    len = right - left + 1;
                }
                int pre = big[left];
                if (map.containsKey(pre)) {
                    map.merge(pre, -1, Integer::sum);
                    if (map.get(pre) < 0) {
                        count--;
                    }
                }
                left++;
            }
            right++;
        }
        return start == -1 ? new int[0] : new int[]{start, start + len - 1};
    }

}
