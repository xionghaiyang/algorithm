package com.sean.base.chapter40;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-20 23:18
 * @Description: TODO
 */
public class Code08_PrintStar {

    public void printStar(int N) {
        int leftUP = 0;
        int rightDown = N - 1;
        char[][] m = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] = ' ';
            }
        }
        while (leftUP <= rightDown) {
            set(m, leftUP, rightDown);
            leftUP += 2;
            rightDown -= 2;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void set(char[][] m, int leftUp, int rightDown) {
        for (int col = leftUp; col <= rightDown; col++) {
            m[leftUp][col] = '*';
        }
        for (int row = leftUp + 1; row <= rightDown; row++) {
            m[row][rightDown] = '*';
        }
        for (int col = rightDown - 1; col > leftUp; col--) {
            m[rightDown][col] = '*';
        }
        for (int row = rightDown - 1; row > leftUp + 1; row--) {
            m[row][leftUp + 1] = '*';
        }
    }

    public static void main(String[] args) {
        Code08_PrintStar solution = new Code08_PrintStar();
        solution.printStar(9);
    }

}
