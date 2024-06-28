package com.sean.base.chapter19;

import java.util.HashMap;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-07 19:50
 * @Description: TODO
 */
public class Code03_StickersToSpellWord {

    public int minStickers1(String[] strickers, String target) {
        int res = process1(strickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //所有贴纸strickers,每一种贴纸都有无穷张
    //target
    //最少张数
    private int process1(String[] strickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : strickers) {
            String rest = minus(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(strickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char c : str1) {
            count[c - 'a']++;
        }
        for (char c : str2) {
            count[c - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public int minStickers2(String[] strickers, String target) {
        int N = strickers.length;
        //关键优化（用词频标替代贴纸数组）
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = strickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        int res = process2(counts, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //strickers[i]数组,当初i号贴纸的字符统计int[][] strickers->所有的贴纸
    //每一种贴纸都有无穷张
    //返回搞定target的最少张数
    private int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            //尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            //最关键的优化（重要的剪枝！这一步也是贪心）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public int minStickers3(String[] strickers, String target) {
        int N = strickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = strickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int res = process3(counts, target, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process3(int[][] strckers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = strckers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] strcker = strckers[i];
            if (strcker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - strcker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process3(strckers, builder.toString(), dp));
            }
        }
        int res = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, res);
        return res;
    }

}
