package com.sean.course03.lesson04;

/**
 * @Author xionghaiyang
 * @Date 2025-04-04 21:03
 * @Description KMP算法
 * 假设字符串str长度为N，字符串match长度为M，M <= N
 * 想确定str中是否有某个子串是等于match的。
 * 时间复杂度O(N)
 */
public class Code01_KMP {

    //O(N)
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        //strt中当前比对到的位置
        int x = 0;
        //match中当前比对到的位置
        int y = 0;
        //M  M<=N  O(M)
        //next[i] match中i之前的字符串[0..i-1]
        int[] next = getNextArray(match);
        //O(N)
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {// y==0
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }

    //M   O(M)
    private static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        //cn位置的字符，是当前和i-1位置比较的字符
        int cn = 0;
        while (i < next.length) {
            if (match[i - 1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    private static String getRandomString(int possibilities, int size) {
        char[] res = new char[(int) (size * Math.random()) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (char) ((int) (possibilities * Math.random()) + 'a');
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
