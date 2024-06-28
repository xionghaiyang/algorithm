package com.sean.leetcode.LeetCode881;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-10 07:44
 * @Description https://leetcode.cn/problems/boats-to-save-people/
 * 881. 救生艇
 * 给定数组 people 。
 * people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回 承载所有人所需的最小船数 。
 */
public class Solution {

    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        int res = 0;
        int left = 0, right = n - 1;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            res++;
        }
        return res;
    }

}
