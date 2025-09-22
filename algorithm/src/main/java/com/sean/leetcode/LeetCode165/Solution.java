package com.sean.leetcode.LeetCode165;

/**
 * @Author xionghaiyang
 * @Date 2025-09-23 07:25
 * @Description https://leetcode.cn/problems/compare-version-numbers
 * 165. 比较版本号
 * 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。
 * 版本号由被点 '.' 分开的修订号组成。
 * 修订号的值 是它 转换为整数 并忽略前导零。
 * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。
 * 如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
 * 返回规则如下：
 * 如果 version1 < version2 返回 -1，
 * 如果 version1 > version2 返回 1，
 * 除此之外返回 0。
 * 1 <= version1.length, version2.length <= 500
 * version1 和 version2 仅包含数字和 '.'
 * version1 和 version2 都是 有效版本号
 * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 */
public class Solution {

    public int compareVersion(String version1, String version2) {
        int m = version1.length(), n = version2.length();
        int i = 0, j = 0;
        while (i < m || j < n) {
            int v1 = 0;
            while (i < m && version1.charAt(i) != '.') {
                v1 = v1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            int v2 = 0;
            while (j < n && version2.charAt(j) != '.') {
                v2 = v2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
            i++;
            j++;
        }
        return 0;
    }

}
