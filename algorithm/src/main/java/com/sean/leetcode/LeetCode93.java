package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode93 {

    List<String> res = new ArrayList<>();
    int[] segments;

    public List<String> restoreIpAddresses(String s) {
        segments = new int[4];
        dfs(s, 0, 0);
        return res;
    }

    private void dfs(String s, int segId, int segStart) {
        //如果找到了4段IP地址并且遍历完了字符串，那么就是一种答案
        if (segId == 4) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < 4; i++) {
                    ipAddr.append(segments[i]);
                    if (i != 3) {
                        ipAddr.append('.');
                    }
                }
                res.add(ipAddr.toString());
            }
            return;
        }
        //如果还没有找到4段IP地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }
        //一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); segEnd++) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

}
