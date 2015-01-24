package com.practise1;

import com.leetcode.util.ListNode;
import com.leetcode.util.TreeNode;
import com.leetcode.util.TreeNodePrinter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by titan-developer on 12/29/14.
 */
public class TestPractise {

    public static void main(String[] strings) {

        char[][] a = {
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'},

        };


        char[][] b = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'X', 'O', 'X'},
        };

        int a1[] = {1, 3, 5, 7, 9};
        int b1[] = {6, 8, 10, 12, 14};

        TestPractise testPractise = new TestPractise();
        for (int i = 1; i <= 10; i ++)
            System.out.println(testPractise.findKth(a1, b1, i));
    }

    private static void outputTree(List<TreeNode> treeNodes) {
        for (TreeNode root : treeNodes) {
            TreeNodePrinter.printNode(root);
            System.out.println("------------------");
        }
    }

    public static <E> void outputList(List<E> list) {
        for (E elem : list) {
            System.out.print(elem + ", ");
        }
        System.out.println();
    }

    public int findKth(int[] A, int[] B, int k) {
        if (A == null || B == null || A.length == 0 || B.length == 0 || k > A.length + B.length || k <= 0)
            throw new IllegalArgumentException();
        int aLow = 0, bLow = 0, aLen = A.length, bLen = B.length;

        while (aLen > 0 || bLen > 0) {
            int i = (int) ((double) ((k - 1) * aLen / (aLen + bLen)));
            int j = k - 1 - i;

            int Ai_1 = aLow + i == 0 ? Integer.MIN_VALUE : A[aLow + i - 1];
            int Ai = aLow + i == A.length ? Integer.MAX_VALUE : A[aLow + i];

            int Bj_1 = bLow + j == 0 ? Integer.MIN_VALUE : B[bLow + j - 1];
            int Bj = bLow + j == B.length ? Integer.MAX_VALUE : B[bLow + j];

            if (Bj_1 < Ai && Ai < Bj)
                return Ai;
            else if (Ai_1 < Bj && Bj < Ai)
                return Bj;

            if (Ai < Bj_1) {
                aLow = aLow + i + 1;
                aLen = aLen - i - 1;
                bLen = j;
                k = k - i - 1;
            } else {
                aLen = i;
                bLow = bLow + j + 1;
                bLen = bLen - j - 1;
            }
        }

        return Integer.MIN_VALUE;
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

        int Ai_1 = aLow + i == 0 ? Integer.MIN_VALUE : A[aLow + i - 1];
        int Ai = aLow + i == A.length ? Integer.MAX_VALUE : A[aLow + i];

        int Bj_1 = bLow + j == 0 ? Integer.MIN_VALUE : B[bLow + j - 1];
        int Bj = bLow + j == B.length ? Integer.MAX_VALUE : B[bLow + j];

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


