package com.sean.lintcode.LintCode2875;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-30 08:26
 */
public class TestA {

    static int num = 0;

    static {
        num++;
    }

    {
        num++;
    }

    public TestA() {
        num++;
    }

}
