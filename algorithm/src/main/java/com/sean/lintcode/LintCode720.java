package com.sean.lintcode;

import java.util.Arrays;

public class LintCode720 {

    public String rearrange(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        int res = 0, index = -1;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= '0' && ch <= '9') {
                res += ch - '0';
                index++;
            } else {
                break;
            }
        }
        if (index == -1) {
            return new String(chars);
        } else {
            StringBuilder ret = new StringBuilder();
            for (int i = index + 1; i < chars.length; i++) {
                ret.append(chars[i]);
            }
            ret.append(res);
            return ret.toString();
        }
    }

}
