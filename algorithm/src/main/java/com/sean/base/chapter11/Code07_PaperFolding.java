package com.sean.base.chapter11;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-26 21:17
 * @Description: TODO
 */
public class Code07_PaperFolding {

    public void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    //当你来到一个节点,脑海中想象的
    //这个节点在第i层，一共有N层，N固定不变的
    //这个节点如果是凹的话,down=T
    //这个节点如果是凸的话，down=F
    //函数的功能:中序打印以你想象的节点为头的整棵树!
    private void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? "凹" : "凸");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        Code07_PaperFolding solution = new Code07_PaperFolding();
        solution.printAllFolds(4);
    }
}
