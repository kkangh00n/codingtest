package dp;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14916 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] D = new int[100001];
        Arrays.fill(D, -1);

        D[2] = 1;
        D[4] = 2;
        D[5] = 1;

        for (int i = 6; i < D.length; i++) {
            if (D[i-2]!=-1) {
                D[i] = D[i-2]+1;
            }

            if(D[i-5]!=-1) {
                D[i] = Math.min(D[i], D[i-5]+1);
            }
        }

        int N = sc.nextInt();

        System.out.println(D[N]);
    }
}
