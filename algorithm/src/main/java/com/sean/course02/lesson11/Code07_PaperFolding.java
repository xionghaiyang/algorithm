package com.sean.course02.lesson11;

/**
 * @Author xionghaiyang
 * @Date 2025-05-10 13:21
 * @Description 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。
 * 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 */
public class Code07_PaperFolding {

    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    //当你来到一个脑海中想象的节点
    //这个节点在第i层，一个有N层，N固定不变的
    //这个节点如果是凹的话,down = T
    //这个节点如果是凸的话，down = F
    //函数的功能:中序打印以你想象的节点为头的整棵树
    private static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? "凹" : "凸");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }

}
