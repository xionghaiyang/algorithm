package com.sean.lintcode;

public class LintCode2256 {

    public StringBuffer updateString(String str1, String str2) {
        StringBuffer stringBuffer = new StringBuffer(str1);
        stringBuffer.replace(4, 7, str2);
        return stringBuffer;
    }

}
