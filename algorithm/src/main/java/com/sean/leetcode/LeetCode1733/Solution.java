package com.sean.leetcode.LeetCode1733;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-09-10 08:03
 * @Description https://leetcode.cn/problems/minimum-number-of-people-to-teach
 * 1733. 需要教语言的最少人数
 * 在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。
 * 两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
 * 给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
 * 总共有 n 种语言，编号从 1 到 n 。
 * languages[i] 是第 i 位用户掌握的语言集合。
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] 表示 u​​​​​​​​​​​i​​​​​ 和 vi 为好友关系。
 * 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。
 * 请返回你 最少 需要教会多少名用户。
 * 请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
 * 2 <= n <= 500
 * languages.length == m
 * 1 <= m <= 500
 * 1 <= languages[i].length <= n
 * 1 <= languages[i][j] <= n
 * 1 <= u​​​​​​i < v​​​​​​i <= languages.length
 * 1 <= friendships.length <= 500
 * 所有的好友关系 (u​​​​​i, v​​​​​​i) 都是唯一的。
 * languages[i] 中包含的值互不相同。
 */
public class Solution {

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] persons = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int x : languages[i]) {
                persons[i][x - 1] = true;
            }
        }
        List<int[]> fs = new ArrayList<>();
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1, v = friendship[1] - 1;
            boolean flag = true;
            for (int x : languages[u]) {
                if (persons[v][x - 1]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                fs.add(new int[]{u, v});
            }
        }
        int res = m;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int[] f : fs) {
                int u = f[0], v = f[1];
                if (!persons[u][i]) {
                    set.add(u);
                }
                if (!persons[v][i]) {
                    set.add(v);
                }
            }
            res = Math.min(res, set.size());
        }
        return res;
    }

}
