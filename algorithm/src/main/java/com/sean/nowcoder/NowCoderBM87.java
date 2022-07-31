package com.sean.nowcoder;

public class NowCoderBM87 {

    public void merge(int A[], int m, int B[], int n) {
        int indexA = m - 1;
        int indexB = n - 1;
        int index = m + n - 1;
        while (indexA >= 0 && indexB >= 0) {
            if (A[indexA] > B[indexB]) {
                A[index--] = A[indexA--];
            } else {
                A[index--] = B[indexB--];
            }
        }
        while (indexA >= 0) {
            A[index--] = A[indexA--];
        }
        while (indexB >= 0) {
            A[index--] = B[indexB--];
        }
    }

}
