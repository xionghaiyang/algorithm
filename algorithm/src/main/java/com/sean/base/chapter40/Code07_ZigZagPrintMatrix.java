package com.sean.base.chapter40;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-20 22:31
 * @Description: TODO
 */
public class Code07_ZigZagPrintMatrix {

    public void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
    }

    private void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean fromUp) {
        if (fromUp) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        Code07_ZigZagPrintMatrix solution = new Code07_ZigZagPrintMatrix();
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        solution.printMatrixZigZag(matrix);
    }

}
