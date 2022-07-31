package com.sean.leetcode;

import java.util.HashSet;

/**
 * 宝石与石头
 * https://leetcode-cn.com/problems/jewels-and-stones/
 */
public class LeetCode771 {

    public static int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> hashSet = new HashSet<>(jewels.length());
        for (int i = 0; i < jewels.length(); i++) {
            hashSet.add(jewels.charAt(i));
        }
        int res = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (hashSet.contains(stones.charAt(i))) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(numJewelsInStones("z", "ZZ"));
    }

}
