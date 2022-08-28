package com.sandbox;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println(fibonnaci(9));

        int[] nums = {3, 6, 1, 0, 1, 4, 2};
        System.out.println(mystery(nums));
    }

    public static int mystery(int[] arr) {
        int x = 0;

        for (int k = 0; k < arr.length; k = k + 2)
            x = x + arr[k];

        return x;
    }

    public static int fibonnaci(int num) {
        if (num <= 0) return 0;
        if (num <= 2) return num - 1;

        int n1 = 0;
        int n2 = 1;
        int result = 0;

        for (int i = 1; i < num; i++) {
            result = n1 + n2;
            n2 = n1;
            n1 = result;
        }

        return result;
    }
}
