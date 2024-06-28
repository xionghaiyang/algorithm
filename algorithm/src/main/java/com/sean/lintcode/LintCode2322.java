package com.sean.lintcode;

public class LintCode2322 {

    public StringBuffer deleteString(String str, int indexStart, int indexEnding) {
        StringBuffer res = new StringBuffer(str);
        res.delete(indexStart, indexEnding);
        return res;
    }

}
