package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11727 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        long[] D = new long[1001];

        D[1] = 1L;
        D[2] = 3L;

        for (int i = 3; i <= 1000; i++) {
            D[i] = (D[i-1] + D[i-2]*2) % 10007;
        }

        System.out.println(D[N]);
    }
}
