package com.sean.leetcode.LeetCode1187;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-20 08:22
 * @Description: https://leetcode.cn/problems/make-array-strictly-increasing/
 * 1187. 使数组严格递增
 * 给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
 * 每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，
 * 分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，然后进行赋值运算 arr1[i] = arr2[j]。
 * 如果无法让 arr1 严格递增，请返回 -1。
 */
public class Solution {

    private static final int INF = 0x3f3f3f3f;

    public int makeArrayIncreasing1(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for (int num : arr2) {
            if (num != prev) {
                list.add(num);
                prev = num;
            }
        }
        int n = arr1.length;
        int m = list.size();
        //dp[i][j]表示数组arr1的前i个元素进行了j次替换后组成严格递增子数组末尾元素的最小值
        int[][] dp = new int[n + 1][Math.min(m, n) + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, m); j++) {
                //如果当前元素大于序列的最后一个元素
                if (arr1[i - 1] > dp[i - 1][j]) {
                    dp[i][j] = arr1[i - 1];
                }
                if (j > 0 && dp[i - 1][j - 1] != INF) {
                    //查找严格大于dp[i-1][j-1]的最小元素
                    int index = binarySearch(list, j - 1, dp[i - 1][j - 1]);
                    if (index != list.size()) {
                        dp[i][j] = Math.min(dp[i][j], list.get(index));
                    }
                }

                if (i == n && dp[i][j] != INF) {
                    return j;
                }
            }
        }
        return -1;
    }

    private int binarySearch(List<Integer> list, int low, int target) {
        int high = list.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int makeArrayIncreasing2(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for (int num : arr2) {
            if (num != prev) {
                list.add(num);
                prev = num;
            }
        }
        int[] temp = new int[arr1.length + 2];
        //左侧哨兵
        temp[0] = -1;
        for (int i = 1; i <= arr1.length; i++) {
            temp[i] = arr1[i - 1];
        }
        //右侧哨兵
        temp[arr1.length + 1] = INF;
        arr1 = temp;
        int n = arr1.length;
        int m = list.size();
        //dp[i]为使数组arr1的前i项递增，且保留arr1[i]的最小替换次数
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            //arr1[i]左侧的元素不进行替换
            if (arr1[i - 1] < arr1[i]) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }
            //替换arr1[i]左边的连续j个元素
            for (int j = 1; j < i; j++) {
                //arr2的最优替换的起点为大于arr1[i-j-1]的最小元素
                int k = binarySearch(list, arr1[i - j - 1]);
                //arr2的最终替换终点必须满足小于arr1[i]
                if (k + j - 1 < m && list.get(k + j - 1) < arr1[i]) {
                    dp[i] = Math.min(dp[i], dp[i - j - 1] + j);
                }
            }
        }
        return dp[n - 1] == INF ? -1 : dp[n - 1];
    }

    private int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for (int num : arr2) {
            if (num != prev) {
                list.add(num);
                prev = num;
            }
        }
        int[] temp = new int[arr1.length + 2];
        //左侧哨兵
        temp[0] = -1;
        for (int i = 1; i <= arr1.length; i++) {
            temp[i] = arr1[i - 1];
        }
        //右侧哨兵
        temp[arr1.length + 1] = INF;
        arr1 = temp;
        int n = arr1.length;
        int m = list.size();
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            //arr1[i]左侧的元素不进行替换
            if (arr1[i - 1] < arr1[i]) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }
            //固定替换元素的右侧终点
            int k = binarySearch1(list, arr1[i]);
            //枚举从i左侧连续替换元素的个数
            for (int j = 1; j <= Math.min(i - 1, k); j++) {
                //替换的连续j个元素的左侧起点需满足大于arr1[i-j-1]
                if (arr1[i - j - 1] < list.get(k - j)) {
                    dp[i] = Math.min(dp[i], dp[i - j - 1] + j);
                }
            }
        }
        return dp[n - 1] == INF ? -1 : dp[n - 1];
    }

    private int binarySearch1(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (list.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
