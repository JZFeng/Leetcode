package com.leetcode.dynamicprogramming;

/**
 * Created by bod on 8/26/14.
 * Maximum Value Contiguous Subsequence. Given a sequence of n real numbers A(1) ... A(n),
 * determine a contiguous subsequence A(i) ... A(j) for which the sum of elements in the subsequence is maximized.
 */
public class MaxValueInSubSequence {

    static int[] a = {
            -1,
            0,
            -2,
            3,
            4,
            5,
            -1,
            -3,
            9,
            -2,
            8
    };

    public static void main(String[] strings) {
        MaxValueInSubSequence subSequence = new MaxValueInSubSequence();
        int value = subSequence.findMaxValue(a);
        System.out.println(value);
    }

    public int findMaxValue(int[] array) {
        if (array == null) {
            return Integer.MIN_VALUE;
        }

        if (array.length == 1) {
            return array[0];
        }

        int[] max = new int[array.length];

        max[0] = array[0];
        int minus = 0;
        for (int i = 1 ; i < array.length ; i ++) {
            if (array[i] > 0) {
                if (max[i - 1] <= 0) {
                    max[i] = array[i];
                    minus = 0;
                } else {
                    if (array[i] > - minus) {
                        max[i] = max[i - 1] + array[i] + minus;
                        minus = 0;
                    } else {
                        max[i] = max[i - 1];
                        minus += array[i];
                    }
                }
            } else if (array[i] < 0) {
                max[i] = max[i - 1];
                minus += array[i];
            } else {
                if (max[i - 1] < 0) {
                    max[i] = 0;
                    minus = 0;
                } else {
                    max[i] = max[i - 1];
                }
            }
        }

        return max[array.length - 1];
    }
}
