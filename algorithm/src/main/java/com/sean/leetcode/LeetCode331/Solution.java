package com.sean.leetcode.LeetCode331;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-31 19:31
 * @Description: https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/
 * 331. 验证二叉树的前序序列化
 * 序列化二叉树的一种方法是使用 前序遍历 。
 * 当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 */
public class Solution {

    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots--;
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++;
            }
        }
        return slots == 0;
    }

}
