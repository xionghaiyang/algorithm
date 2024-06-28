package com.sean.leetcode;

/**
 * 截断句子
 * https://leetcode-cn.com/problems/truncate-sentence/
 */
public class LeetCode1816 {

    public String truncateSentence1(String s, int k) {
        String[] words = s.split(" ");
        if (words.length < k) {
            return s;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < k; i++) {
            stringBuffer.append(words[i]);
            if (i < k - 1) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    public String truncateSentence(String s, int k) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
                if(count==k){
                    return s.substring(0,i);
                }
            }
        }
        return s;
    }
}
