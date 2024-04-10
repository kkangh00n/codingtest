package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {

    //계단의 높이
    static final int H = 9;
    static final long mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //계단의 길이
        int N = Integer.parseInt(br.readLine());
        //길이가 N인 계단 수의 갯수
        long[][] D = new long[N + 1][H + 1];

        //길이가 1인 계단은 높이의 상관없이 계단 하나를 갖는다.
        for (int i = 1; i <= H; i++) {
            D[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            //길이 j번째 높이 0인 계단의 수 = 길이 j-1번째 높이 1인 계단의 수
            //길이 j번째 높이 9인 계단의 수 = 길이 j-1번째 높이 8인 계단의 수
            D[i][0] = D[i - 1][1];
            D[i][9] = D[i - 1][8];

            //높이가 0이나 9가 아닌 계단의 수 = 현재 높이-1인, 길이 j-1번 계단 수 + 현재 높이+1인, 길이 j+1번 계단 수
            for (int j = 1; j <= 8; j++) {
                D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % mod;
            }
        }

        long sum = 0;
        for (int i = 0; i <= H; i++) {
            sum = (sum + D[N][i]) % mod;
        }

        System.out.println(sum);
    }
}
