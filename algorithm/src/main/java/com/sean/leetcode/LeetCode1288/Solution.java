package com.sean.leetcode.LeetCode1288;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-06 09:07
 * @Description: https://leetcode.cn/problems/remove-covered-intervals
 * 1288. 删除被覆盖区间
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * 对于所有的 i != j：intervals[i] != intervals[j]
 */
public class Solution {

    public int removeCoveredIntervals(int[][] intervals) {
        //按区间左端点从小到大排序
        //区间左端点相同时，按区间右端点从大到小排序
        //这样就会先遍历大区间，再遍历被大区间覆盖的小区间
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int res = 0, maxRight = 0;
        for (int[] interval : intervals) {
            if (interval[1] > maxRight) {
                maxRight = interval[1];
                res++;
            }
        }
        return res;
    }

}
