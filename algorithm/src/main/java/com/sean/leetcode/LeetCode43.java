package com.sean.leetcode;

public class LeetCode43 {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String res = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int carry = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int t = x * y + carry;
                curr.append(t % 10);
                carry = t / 10;
            }
            if (carry != 0) {
                curr.append(carry % 10);
            }
            res = add(res, curr.reverse().toString());
        }
        return res;
    }

    private String add(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuffer res = new StringBuffer();
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int t = x + y + carry;
            res.append(t % 10);
            carry = t / 10;
            i--;
            j--;
        }
        return res.reverse().toString();
    }

}
