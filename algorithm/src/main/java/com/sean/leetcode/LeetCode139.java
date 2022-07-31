package com.sean.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 单词拆分
 * https://leetcode-cn.com/problems/word-break/
 */
public class LeetCode139 {

    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    private static List<String> getList(String[] list) {
        List<String> res = new ArrayList<>();
        for (String s : list) {
            res.add(s);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] wordDict1 = {"leet","code"};
        System.out.println(wordBreak("leetcode",getList(wordDict1)));

        String[] wordDict2 = {"apple","pen"};
        System.out.println(wordBreak("applepenapple",getList(wordDict2)));

        String[] wordDict3 = {"cats","dog","sand","and","cat"};
        System.out.println(wordBreak("catsandog",getList(wordDict3)));
    }

}
