package com.sean.leetcode;

import java.util.Stack;

public class LeetCode591 {

    public static boolean isValid(String code) {
        int N = code.length();
        Stack<String> tagStack = new Stack<>();
        int i = 0;
        while (i < N) {
            if (code.charAt(i) == '<') {
                if (i == N - 1) {
                    return false;
                }
                if (code.charAt(i + 1) == '/') {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }
                    String tagname = code.substring(i + 2, j);
                    if (tagStack.isEmpty() || !tagStack.peek().equals(tagname)) {
                        return false;
                    }
                    tagStack.pop();
                    i = j + 1;
                    if (tagStack.isEmpty() && i != N) {
                        return false;
                    }
                } else if (code.charAt(i + 1) == '!') {
                    if (tagStack.isEmpty()) {
                        return false;
                    }
                    if (i + 9 > N) {
                        return false;
                    }
                    String cdata = code.substring(i + 2, i + 9);
                    if (!"[CDATA[".equals(cdata)) {
                        return false;
                    }
                    int j = code.indexOf("]]>", i);
                    if (j < 0) {
                        return false;
                    }
                    i = j + 1;
                } else {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }
                    String tagname = code.substring(i + 1,j);
                    if (tagname.length() < 1 || tagname.length() > 9) {
                        return false;
                    }
                    for (int k = 0; k < tagname.length(); k++) {
                        if (!Character.isUpperCase(tagname.charAt(k))) {
                            return false;
                        }
                    }
                    tagStack.push(tagname);
                    i = j + 1;
                }
            } else {
                if (tagStack.isEmpty()) {
                    return false;
                }
                i++;
            }
        }
        return tagStack.isEmpty();
    }

}
