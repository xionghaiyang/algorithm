package com.sean.base.chapter29;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-29 19:37
 * @Description: TODO
 */
public class Code01_FindMinKth {

    //利用大根堆，时间复杂度O(N*logK)
    public int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    //改写快排，时间复杂度O(N)
    //k>=1
    public int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    private int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //arr第k小的数
    //process2(arr,0,N-1,K-1)
    //arr[L..R]范围上，如果排序的话（不是真的排序），找位于Index的数
    //index [L..R]
    private int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }

    private int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //利用bfprt算法，时间复杂度O(N)
    public int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    //arr[L..R] 如果排序的话，位于index位置的数，是什么，返回
    private int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        //L..R 每五个数一组
        // 每一个小组内部排好序
        //小组的中位数组成新数组
        //这个新数组的中位数返回
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    //arr[L..R] 五个数一组
    //每个小组内部排序
    //每个小组中位数拿出来，组成marr
    //marr中的中位数，返回
    private int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        //marr中，找到中位数
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    private void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        Code01_FindMinKth solution = new Code01_FindMinKth();
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int res1 = solution.minKth1(arr, k);
            int res2 = solution.minKth2(arr, k);
            int res3 = solution.minKth3(arr, k);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Oops!");
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(res3);
                break;
            }
        }
        System.out.println("test finish");
    }

}
