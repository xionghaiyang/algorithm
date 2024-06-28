package com.sean.leetcode;

/**
 * 1比特与2比特字符
 * https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/
 */
public class LeetCode717 {

    public static boolean isOneBitCharacter(int[] bits) {
        int count = 0;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 1) {
                count++;
            } else {
                break;
            }
        }
        return count % 2 == 0;
    }

    public static void main(String[] args) {
        System.out.println(isOneBitCharacter(new int[]{1,0,0}));
        System.out.println(isOneBitCharacter(new int[]{1, 1, 1, 0}));
    }

}
