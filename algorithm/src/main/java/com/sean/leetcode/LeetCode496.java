package com.sean.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 下一个更大元素 I
 * https://leetcode-cn.com/problems/next-greater-element-i/
 */
public class LeetCode496 {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < length2; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                hashMap.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] res = new int[length1];
        for (int i = 0; i < length1; i++) {
            res[i] = hashMap.getOrDefault(nums1[i], -1);
        }
        return res;
    }

}
