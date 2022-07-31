package com.sean.lintcode;

import java.util.ArrayList;
import java.util.List;

public class LintCode914 {

    public static List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; ) {
            res.add(s.substring(0, i) + "--" + s.substring(i + 2));
        }
        return res;
    }

}
