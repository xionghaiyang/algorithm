package com.sean.leetcode.LeetCode2109;

/**
 * @Author xionghaiyang
 * @Date 2025-03-30 08:02
 * @Description https://leetcode.cn/problems/adding-spaces-to-a-string
 * 2109. 向字符串添加空格
 * 给你一个下标从 0 开始的字符串 s ，以及一个下标从 0 开始的整数数组 spaces 。
 * 数组 spaces 描述原字符串中需要添加空格的下标。
 * 每个空格都应该插入到给定索引处的字符值 之前 。
 * 例如，s = "EnjoyYourCoffee" 且 spaces = [5, 9] ，那么我们需要在 'Y' 和 'C' 之前添加空格，这两个字符分别位于下标 5 和下标 9 。
 * 因此，最终得到 "Enjoy Your Coffee" 。
 * 请你添加空格，并返回修改后的字符串。
 * 1 <= s.length <= 3 * 10^5
 * s 仅由大小写英文字母组成
 * 1 <= spaces.length <= 3 * 10^5
 * 0 <= spaces[i] <= s.length - 1
 * spaces 中的所有值 严格递增
 */
public class Solution {

    public String addSpaces(String s, int[] spaces) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int space : spaces) {
            while (index != space) {
                sb.append(s.charAt(index++));
            }
            sb.append(" ");
        }
        while (index < n) {
            sb.append(s.charAt(index++));
        }
        return sb.toString();
    }

}
