package com.sean.base.chapter27;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-23 19:44
 * @Description: TODO
 */
public class Code01_KMP {

    public int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        //O(M) m<=n
        int[] next = getNextArray(str2);
        //O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { //y == 0
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;//目前在哪个位置上求next数组的值
        int cn = 0;//当前是哪个位置的值再和i-1位置的字符比较
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {//配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public String getRandomString(int possibilities, int size) {
        char[] res = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Code01_KMP solution = new Code01_KMP();
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            String str = solution.getRandomString(possibilities, strSize);
            String match = solution.getRandomString(possibilities, matchSize);
            if (solution.getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish!");
    }

}
