package com.sean.nowcoder;

public class NowCoderBM86 {

    public static String solve(String s, String t) {
        if (s == null || s.length() == 0) {
            return t;
        }
        if (t == null || t.length() == 0) {
            return s;
        }
        int index1 = s.length() - 1;
        int index2 = t.length() - 1;
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (index1 >= 0 && index2 >= 0) {
            int temp = (s.charAt(index1--) - '0') + (t.charAt(index2--) - '0') + carry;
            carry = temp / 10;
            stringBuilder.append(temp % 10);
        }
        while (index1 >= 0) {
            int temp = (s.charAt(index1--) - '0') + carry;
            carry = temp / 10;
            stringBuilder.append(temp % 10);
        }
        while (index2 >= 0) {
            int temp = (t.charAt(index2--) - '0') + carry;
            carry = temp / 10;
            stringBuilder.append(temp % 10);
        }
        if (carry == 1) {
            stringBuilder.append("1");
        }
        return stringBuilder.reverse().toString();
    }

}
