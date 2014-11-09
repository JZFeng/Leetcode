package com.leetcode.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by titan-developer on 11/7/14.
 * https://oj.leetcode.com/problems/subsets/
 */
public class Subsets {

    public static void main(String[] strings) {

        int[] s = {1, 2, 3};

        Subsets subsets = new Subsets();
        List<List<Integer>> lists = subsets.subsets(s);

        for (List<Integer> list : lists) {
            for (int a : list) {
                System.out.print(a + ", ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        int totalNumber = 1 << S.length;
        List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
        for (int i = 0; i < totalNumber; i++) {
            List<Integer> set = new LinkedList<Integer>();
            for (int j = 0; j < S.length; j++) {
                if ((i & (1 << j)) != 0) {
                    set.add(S[j]);
                }
            }
            collection.add(set);
        }
        return collection;
    }

}