package com.sean.lintcode;

public class LintCode408 {

    public static String addBinary(String a, String b) {
        int indexA = a.length() - 1, indexB = b.length() - 1, numA = 0, numB = 0, sum = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (indexA >= 0 && indexB >= 0) {
            numA = a.charAt(indexA--) - '0';
            numB = b.charAt(indexB--) - '0';
            sum = numA + numB + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        while (indexA >= 0) {
            numA = a.charAt(indexA--) - '0';
            sum = numA + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        while (indexB >= 0) {
            numB = b.charAt(indexB--) - '0';
            sum = numB + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }

}
