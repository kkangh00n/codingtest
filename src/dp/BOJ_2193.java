package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2193 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //각 자릿수마다 가질 수 있는 이친수의 갯수
        long[][] D = new long[N + 1][2];
        //한 자릿수에서 1로 끝나는 이친수 갯수는 1개
        D[1][0] = 0;
        D[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            D[i][0] = D[i - 1][0] + D[i - 1][1];
            D[i][1] = D[i - 1][0];
        }

        System.out.println(D[N][0] + D[N][1]);

    }
}
