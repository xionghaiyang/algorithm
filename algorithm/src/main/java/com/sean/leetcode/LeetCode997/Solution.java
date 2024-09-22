package com.sean.leetcode.LeetCode997;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-09-22 13:18
 * @Description https://leetcode.cn/problems/find-the-town-judge/
 * 997. 找到小镇的法官
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。
 * 传言称，这些人中有一个暗地里是小镇法官。
 * 如果小镇法官真的存在，那么：
 * 1:小镇法官不会信任任何人。
 * 2:每个人（除了小镇法官）都信任这位小镇法官。
 * 3:只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 * 1 <= n <= 1000
 * 0 <= trust.length <= 10^4
 * trust[i].length == 2
 * trust 中的所有trust[i] = [ai, bi] 互不相同
 * ai != bi
 * 1 <= ai, bi <=n
 */
public class Solution {

    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n + 1];
        boolean[] flag = new boolean[n + 1];
        Arrays.fill(flag, true);
        for (int[] arr : trust) {
            flag[arr[0]] = false;
            count[arr[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (flag[i] && count[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

}
