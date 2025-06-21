package com.sean.leetcode.LeetCode2138;

/**
 * @Author xionghaiyang
 * @Date 2025-06-22 05:58
 * @Description https://leetcode.cn/problems/divide-a-string-into-groups-of-size-k
 * 2138. 将字符串拆分为若干长度为 k 的组
 * 字符串 s 可以按下述步骤划分为若干长度为 k 的组：
 * 第一组由字符串中的前 k 个字符组成，第二组由接下来的 k 个字符串组成，依此类推。
 * 每个字符都能够成为 某一个 组的一部分。
 * 对于最后一组，如果字符串剩下的字符 不足 k 个，需使用字符 fill 来补全这一组字符。
 * 注意，在去除最后一个组的填充字符 fill（如果存在的话）并按顺序连接所有的组后，所得到的字符串应该是 s 。
 * 给你一个字符串 s ，以及每组的长度 k 和一个用于填充的字符 fill ，按上述步骤处理之后，返回一个字符串数组，
 * 该数组表示 s 分组后 每个组的组成情况 。
 * 1 <= s.length <= 100
 * s 仅由小写英文字母组成
 * 1 <= k <= 100
 * fill 是一个小写英文字母
 */
public class Solution {

    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int m = n / k;
        m += (n % k) != 0 ? 1 : 0;
        String[] res = new String[m];
        for (int i = 0, j = 0; j < m; i += k, j++) {
            if (i + k < n) {
                res[j] = s.substring(i, i + k);
            } else {
                StringBuilder str = new StringBuilder(s.substring(i));
                while (str.length() < k) {
                    str.append(fill);
                }
                res[j] = str.toString();
            }
        }
        return res;
    }

}
