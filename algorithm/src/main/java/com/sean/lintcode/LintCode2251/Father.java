package com.sean.lintcode.LintCode2251;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-26 08:55
 */
public class Father {

    static {
        System.out.println("Father's message:");
    }

    private String name = "Liu Bei";
    private int id = 1;

    public Father() {
    }

    public Father(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void introduction() {
        System.out.println("name: " + name + ", id: " + id + ".");
    }

}
