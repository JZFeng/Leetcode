package com.leetcode.findmedian;

/**
 * Created by titan-developer on 12/2/14.
 */
public class FindKthInTwoSortedArray {

    static final int MIN = Integer.MIN_VALUE;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] strings) {
        int[] a = {1, 2, 4, 6, 7, 8, 9};
        int[] b = {3, 5, 9, 10, 11, 12};

        FindKthInTwoSortedArray findKthInTwoSortedArray = new FindKthInTwoSortedArray();
        System.out.println(findKthInTwoSortedArray.kthSmallest(a, b, 5));
    }

    public int kthSmallest(int[] A, int[] B, int k) {
        if (A == null || B == null || k > A.length + B.length)
            throw new IllegalArgumentException();
        return kthSmallest(A, 0, A.length, B, 0, B.length, k);
    }

    protected int kthSmallest(int[] A, int aLow, int aLength, int[] B, int bLow, int bLength, int k) {

        assert (aLow >= 0);
        assert (bLow >= 0);
        assert (aLength >= 0);
        assert (bLength >= 0);
        assert (aLength + bLength >= k);

        int i = (int) ((double) ((k - 1) * aLength / (aLength + bLength)));
        int j = k - 1 - i;

        int Ai_1 = aLow + i == 0 ? MIN : A[aLow + i - 1];
        int Ai = aLow + i == A.length ? MAX : A[aLow + i];

        int Bj_1 = bLow + j == 0 ? MIN : B[bLow + j - 1];
        int Bj = bLow + j == B.length ? MAX : B[bLow + j];

        if (Bj_1 < Ai && Ai < Bj)
            return Ai;
        else if (Ai_1 < Bj && Bj < Ai)
            return Bj;

        assert (Ai < Bj - 1 || Bj < Ai_1);

        if (Ai < Bj_1) // exclude A[aLow .. i] and A[j..bHigh], k was replaced by k - i - 1
            return kthSmallest(A, aLow + i + 1, aLength - i - 1, B, bLow, j, k - i - 1);
        else // exclude A[i, aHigh] and B[bLow .. j], k was replaced by k - j - 1
            return kthSmallest(A, aLow, i, B, bLow + j + 1, bLength - j - 1, k - j - 1);
    }
}
