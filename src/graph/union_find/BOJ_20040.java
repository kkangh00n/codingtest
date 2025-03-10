package graph.union_find;

import java.util.Scanner;

public class BOJ_20040 {

    static int[] unf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        unf = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            unf[i] = i;
        }

        int answer = 0;

        for (int i = 1; i <= m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            if (union(from, to)) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    public static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa == fb) {
            return true;
        } else {
            unf[fa] = fb;
            return false;
        }
    }

    public static int find(int i) {
        if (i == unf[i]) {
            return i;
        }

        unf[i] = find(unf[i]);
        return unf[i];
    }
}
