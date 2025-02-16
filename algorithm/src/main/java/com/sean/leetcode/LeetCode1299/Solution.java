package com.sean.leetcode.LeetCode1299;

/**
 * @Author xionghaiyang
 * @Date 2025-02-16 09:40
 * @Description https://leetcode.cn/problems/replace-elements-with-greatest-element-on-right-side/
 * 1299. 将每个元素替换为右侧最大元素
 * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
 * 完成所有替换操作后，请你返回这个数组。
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i] <= 10^5
 */
public class Solution {

    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        res[n-1] = -1;
        for(int i = n-2;i>=0;i--){
            res[i] = Math.max(res[i+1],arr[i+1]);
        }
        return res;
    }

}
