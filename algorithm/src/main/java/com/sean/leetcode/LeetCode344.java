package com.sean.leetcode;

/**
 * 反转字符串
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class LeetCode344 {

    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int length = s.length;
        for (int i = 0; i < (length >> 1); i++) {
            char temp = s[i];
            s[i] = s[length - 1 - i];
            s[length - 1 - i] = temp;
        }
    }

    private static void printArr(char[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        char[] chars1 = {'h', 'e', 'l', 'l', 'o'};
        reverseString(chars1);
        printArr(chars1);

        char[] chars2 = {'H','a','n','n','a','h'};
        reverseString(chars2);
        printArr(chars2);
    }

}
