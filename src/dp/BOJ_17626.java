package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17626 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int min = 0;

        int[] D = new int[50001];
        D[1] = 1;

        //2부터 채워나간다.
        for (int i = 2; i <= N; i++) {

            min = Integer.MAX_VALUE;

            //j의 제곱이 i보다 작다면
            for (int j = 1; j * j <= i; j++) {
                //ex) 12-2*2 = 8
                int temp = i-j*j;
                //8의 제곱 수
                min = Math.min(min, D[temp]);
            }

            D[i] = min + 1;

        }

        System.out.println(D[N]);
    }
}
