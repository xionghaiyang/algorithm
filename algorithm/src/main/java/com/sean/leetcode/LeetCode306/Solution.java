package com.sean.leetcode.LeetCode306;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-22 17:54
 * @Description https://leetcode.cn/problems/additive-number
 * 306. 累加数
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。
 * 除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。
 * 如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 */
public class Solution {

    private String num;
    private int n;
    private List<List<Integer>> list = new ArrayList<>();

    public boolean isAdditiveNumber(String num) {
        this.num = num;
        n = num.length();
        return dfs(0);
    }

    private boolean dfs(int u) {
        int m = list.size();
        if (u == n) {
            return m >= 3;
        }
        int max = num.charAt(u) == '0' ? u + 1 : n;
        List<Integer> cur = new ArrayList<>();
        for (int i = u; i < max; i++) {
            cur.add(0, num.charAt(i) - '0');
            if (m < 2 || check(list.get(m - 2), list.get(m - 1), cur)) {
                list.add(cur);
                if (dfs(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    private boolean check(List<Integer> list1, List<Integer> list2, List<Integer> list3) {
        List<Integer> list = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < list1.size() || i < list2.size(); i++) {
            if (i < list1.size()) {
                t += list1.get(i);
            }
            if (i < list2.size()) {
                t += list2.get(i);
            }
            list.add(t % 10);
            t /= 10;
        }
        if (t > 0) {
            list.add(t);
        }
        boolean ok = list3.size() == list.size();
        for (int i = 0; i < list.size() && ok; i++) {
            if (!list3.get(i).equals(list.get(i))) {
                ok = false;
            }
        }
        return ok;
    }

}
