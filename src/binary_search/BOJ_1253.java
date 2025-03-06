package binary_search;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1253 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int answer = 0;

        for (int i = 0; i < N; i++) {
            int target = arr[i];

            int start = 0;
            int end = N - 1;

            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                }

                int result = arr[start] + arr[end];

                if (result == target) {
                    answer++;
                    break;
                }

                if (result < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(answer);
    }
}
