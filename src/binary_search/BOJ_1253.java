package binary_search;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1253 {

    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length-1;

        int sum = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;

        while(start<end) {
            int value = arr[start] + arr[end];

            if (Math.abs(sum)>Math.abs(value)) {
                sum = value;

                ans1 = arr[start];
                ans2 = arr[end];
            }

            if (value>0) {
                end--;
            }
            else {
                start++;
            }
        }
        System.out.println(ans1 + " " + ans2);

    }
}
