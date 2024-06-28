package com.sean.base.chapter38;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-08 19:19
 * @Description: TODO
 */
public class Code01_AppleMinBags {

    public int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag8 = apple >> 3;
        int rest = apple - (bag8 << 3);
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + rest / 6;
            } else {
                bag8--;
                rest += 8;
            }
        }
        return -1;
    }

    public int minBagAwesome(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0
                    : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2
                    : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        Code01_AppleMinBags solution = new Code01_AppleMinBags();
        for (int apple = 1; apple < 200; apple++) {
            System.out.println(apple + ":" + solution.minBags(apple));
        }
        System.out.println("测试开始");
        for (int i = 0; i < 100000; i++) {
            if (solution.minBags(i) != solution.minBagAwesome(i)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
