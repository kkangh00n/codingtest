package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //2*N 직사각형을 채울 수 있는 방법 갯수
        long[] arr = new long[1001];

        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i <= N; i++) {
            arr[i] =
                (//N-1까지 타일이 채워져 있는 경우 세로로 타일 1개를 채우면 됨
                arr[i-1]
                //N-2까지 타일이 채워져 있는 경우 가로로 타일 2개를 채우면 됨
                + arr[i-2]
                )%10007;
        }

        System.out.println(arr[N]);
    }
}
