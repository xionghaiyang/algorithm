package com.sean.lintcode.LintCode2313;

/**
 * @Author xionghaiyang
 * @Date 2022-08-01 21:28
 * @Description https://www.lintcode.com/problem/2313/?showListFe=true&page=1&pageSize=50
 * 请在类名为 Solution 的 textCharacter 方法中 // write your code here 下面编写你的代码。
 * 本题我们在 Main.java 中已经写好了对 Solution 对象的创建和 textCharacter 方法的调用。
 * 在 Solution 类中已经定义好带有枚举类型引用参数的 printCharacter 方法，该方法中通过 switch case 语句实现对梅兰竹菊代表品性的打印。
 * 在 PropertyEnum 文件中定义了枚举类 PropertyEnum，以及列出了所有的枚举值。
 */
public class Solution {

    public void printCharacter(PropertyEnum s) {
        switch (s) {
            case PlumBlossoms:
                System.out.println(
                        "PlumBlossoms: A noble man who explores the waves and the snow");
                break;

            case Orchid:
                System.out.println(
                        "Orchid: A sage of the world, the fragrance of the deep valley");
                break;

            case Bamboo:
                System.out.println("Bamboo: A gentleman of modesty and elegance");
                break;

            case Chrysanthemum:
                System.out.println("Chrysanthemum: A hermit of the world");
                break;
        }
    }

    public void textCharacter() {
        printCharacter(PropertyEnum.PlumBlossoms);
        printCharacter(PropertyEnum.Orchid);
        printCharacter(PropertyEnum.Bamboo);
        printCharacter(PropertyEnum.Chrysanthemum);
    }

}
