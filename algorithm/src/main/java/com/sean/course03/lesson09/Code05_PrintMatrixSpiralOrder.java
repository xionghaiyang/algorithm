package com.sean.course03.lesson09;

/**
 * @Author xionghaiyang
 * @Date 2025-12-08 21:34
 * @Description 转圈打印矩阵
 */
public class Code05_PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix) {
        int aRow = 0, aCol = 0, bRow = matrix.length - 1, bCol = matrix[0].length - 1;
        while (aRow <= bRow && aCol <= bCol) {
            printEdge(matrix, aRow++, aCol++, bRow--, bCol--);
        }
    }

    private static void printEdge(int[][] matrix, int aRow, int aCol, int bRow, int bCol) {
        if (aRow == bRow) {
            for (int i = aCol; i <= bCol; i++) {
                System.out.print(matrix[aRow][i] + " ");
            }
        } else if (aCol == bCol) {
            for (int i = aRow; i <= bRow; i++) {
                System.out.print(matrix[i][aCol] + " ");
            }
        } else {
            int col = aCol, row = aRow;
            while (col != bCol) {
                System.out.print(matrix[aRow][col++] + " ");
            }
            while (row != bRow) {
                System.out.print(matrix[row++][col] + " ");
            }
            while (col != aCol) {
                System.out.print(matrix[bRow][col--] + " ");
            }
            while (row != aRow) {
                System.out.print(matrix[row--][aCol] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        spiralOrderPrint(matrix);
    }

}
