package com.sean.course03.lesson03;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-04-02 21:48
 * @Description 在无序数组中求第K小的数
 * 1）改写快排的方法
 * 2）bfprt算法
 */
public class Code01_FindMinKth {

    //利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        int n = arr.length;
        for (int i = k; i < n; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    //改写快排，时间复杂度O(N)
    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    private static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //arr[left...right]范围上，如果排序的话（不是真的去排序），找位于index的数
    private static int process2(int[] arr, int left, int right, int index) {
        if (left == right) {
            return arr[left];
        }
        int pivot = arr[left + (int) ((right - left + 1) * Math.random())];
        int[] range = partition(arr, left, right, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, left, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, right, index);
        }
    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;
        int more = right + 1;
        int cur = left;
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

    private static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    //利用bfprt算法，时间复杂度O(N)
    public static int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int left, int right, int index) {
        if (left == right) {
            return arr[left];
        }
        int pivot = medianOfMedians(arr, left, right);
        int[] range = partition(arr, left, right, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, left, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, right, index);
        }
    }

    //arr[left...right]五个数一组
    //每个小组内部排序
    //每个小组中位数拿出来，组成mArr
    //mArr中的中位数，返回
    private static int medianOfMedians(int[] arr, int left, int right) {
        int size = right - left + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = left + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(right, teamFirst + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian(int[] arr, int left, int right) {
        insertionSort(arr, left, right);
        return arr[(left + right) / 2];
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i - 1; j >= left && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (maxSize * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (arr.length * Math.random()) + 1;
            int res1 = minKth1(arr, k);
            int res2 = minKth2(arr, k);
            int res3 = minKth3(arr, k);
            if (res1 != res2 || res2 != res3) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
