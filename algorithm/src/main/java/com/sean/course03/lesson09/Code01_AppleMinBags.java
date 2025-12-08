package com.sean.course03.lesson09;

/**
 * @Author xionghaiyang
 * @Date 2025-12-08 20:17
 * @Description 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数N，返回至少使用多少袋子。
 * 如果N无法让使用的每个袋子必须装满，返回-1
 */
public class Code01_AppleMinBags {

    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1, bag8 = apple / 8, rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    //如果剩余苹果rest可以被装6个苹果的袋子搞定，返回袋子数量
    //不能搞定返回-1
    private static int minBagBase6(int rest) {
        return rest % 6 == 0 ? rest / 6 : -1;
    }

    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 1; apple < 100; apple++) {
            System.out.println(apple + ":" + minBags(apple));
        }
    }

}
