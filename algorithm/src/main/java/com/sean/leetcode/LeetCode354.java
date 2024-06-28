package com.sean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 俄罗斯套娃信封问题
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 */
public class LeetCode354 {

    //最长上升子序列
    private static int lengthOfLIS(int[][] nums) {
        //list中保存的是构成的上升子序列
        ArrayList<Integer> list = new ArrayList<>(nums.length);
        for (int[] num : nums) {
            //如果list为空，我们直接把num加进去。
            //如果list的最后一个元素小于num,
            //说明num加入到list的末尾可以构成一个更长的上升子序列，
            //我们就把num加入到list中
            if (list.size() == 0 || list.get(list.size() - 1) < num[1]) {
                list.add(num[1]);
            } else {
                //如果num不小于list的最后一个元素，我们就用num把list中第一个大于他的值给替换掉
                //这样我们才能保证list中元素在长度不变的情况下,元素尽可能的小
                int i = Collections.binarySearch(list, num[1]);
                //因为list是从小到大排序的，并且上面使用的是二分查找。
                //当i > 0的时候，说明出现了重复，我们直接把他替换即可，
                //如果i小于0,我们对i取反，他就是list中第一个大于num值的位置，
                //我们把他替换即可。
                list.set((i < 0) ? -i - 1 : i, num[1]);
            }

        }
        return list.size();
    }

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (int[] arr1, int[] arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });
        return lengthOfLIS(envelopes);
    }

    private static int lengthOfLIS1(int[][] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //如果当前值num[i]大于num[j],
                //说明nums[i]可以和num[j]结尾的上升序列构成的一个新的上升子序列
                if (nums[i][1] > nums[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //记录构成的最大值
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
