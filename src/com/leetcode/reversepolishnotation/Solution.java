package com.leetcode.reversepolishnotation;

import java.util.Stack;

/**
 * Created by titan-developer on 8/15/14.
 */
public class Solution {

    static String[] array_A = new String[]{"2", "1", "+", "3", "*"};
    static String[] array_B = new String[]{"4", "13", "5", "/", "+"};

    public static void main(String[] strings) {
        Solution solution = new Solution();
        int a = solution.evalRPN(array_A);
        int b = solution.evalRPN(array_B);

        System.out.println(a);
        System.out.println(b);
    }

        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0)
                return 0;

            Stack<Integer> stack = new Stack<Integer>();
            for (String str : tokens) {
                int c = isOperator(str);
                if (c > 0) {
                    int a = stack.pop();
                    int b = stack.pop();
                    int x = calc(b, a, (char)c);
                    stack.push(x);
                } else {
                    stack.push(Integer.valueOf(str));
                }
            }

            return stack.pop();
        }

        private int calc(int a, int b, char c) {
            if (c == '+') {
                return a + b;
            } else if (c == '-') {
                return a - b;
            } else if (c == '*') {
                return a * b;
            } else if (c == '/') {
                return a / b;
            }

            return 0;
        }

        int isOperator(String str) {
            if (str.length() > 1)
                return -1;

            char c = str.toCharArray()[0];
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                return c;
            }

            return -1;
        }
}