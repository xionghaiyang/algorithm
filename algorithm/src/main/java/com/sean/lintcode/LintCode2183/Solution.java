package com.sean.lintcode.LintCode2183;

/**
 * @Author xionghaiyang
 * @Date 2022-08-13 13:50
 * @Description https://www.lintcode.com/problem/2183/?showListFe=true&page=1&pageSize=50
 * 2183 · 根据学生的成绩等级，判断学生的成绩范围
 * 本题要根据学生的成绩等级，判断学生的成绩范围。
 * 我们规定 A 等级 代表 90~100 分，
 * B 等级代表 80~89 分，
 * C 等级代表 70~79 分，
 * D 等级代表 60~69 分，
 * E 等级代表 0~59 分，
 * 如果是 A~E 以外的字母，用 Unknown level 表示。
 * 我们推荐使用 switch case 语句来实现
 * 本题提供了 Solution 类 。
 * Solution 类中有一个 getRange 方法，该方法的返回值类型是 String 类型，
 * 该方法传递了一个 String 类型的参数 level，方法中定义了一个 String 类型的 str 并赋值为空。
 * 你需要在 // write your code here 下面写出你的代码。
 */
public class Solution {

    public String getRange(String level) {
        String str = "";
        switch (level) {
            case "A":
                str = "90~100";
                break;
            case "B":
                str = "80~89";
                break;
            case "C":
                str = "70~79";
                System.out.println();
                break;
            case "D":
                str = "60~69";
                break;
            case "E":
                str = "0~59";
                break;
            default:
                str = "Unknown level";
                break;
        }
        return str;
    }

}
