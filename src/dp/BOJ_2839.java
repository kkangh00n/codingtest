package dp;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2839 {

    public static void main(String[] args) {
        int[] D = new int[5001];
        Arrays.fill(D, -1);

        D[3] = 1;
        D[5] = 1;

        for (int i = 6; i < D.length; i++) {
            if (D[i-3] != -1) {
                D[i] = D[i-3] + 1;
            }

            if(D[i-5] != -1) {
                int temp = D[i-5] + 1;
                D[i] = D[i-3] != -1 ? Math.min(D[i] , temp) : temp;
            }
        }

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        System.out.println(D[N]);
    }
}
