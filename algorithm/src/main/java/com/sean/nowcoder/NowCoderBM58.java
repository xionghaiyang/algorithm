package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class NowCoderBM58 {

    public static void process(ArrayList<String> res, char[] charStr, StringBuffer buffer, boolean[] visit) {
        if (buffer.length() == charStr.length) {
            res.add(new String(buffer));
            return;
        }
        for (int i = 0; i < charStr.length; i++) {
            if (visit[i]) {
                continue;
            }
            if (i > 0 && charStr[i - 1] == charStr[i] && visit[i - 1]) {
                continue;
            }
            visit[i] = true;
            buffer.append(charStr[i]);
            process(res, charStr, buffer, visit);
            visit[i] = false;
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }

    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] charStr = str.toCharArray();
        Arrays.sort(charStr);
        boolean[] visit = new boolean[str.length()];
        Arrays.fill(visit, false);
        StringBuffer buffer = new StringBuffer();
        process(res, charStr, buffer, visit);
        return res;
    }

}
