package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] D = new int[N + 1];
        D[1] = 0;

        for (int i = 2; i <= N; i++) {
            //i는 이전 숫자에서 1을 더한 것과 같다.
            D[i] = D[i - 1] + 1;

            //만약 i를 2로 나눌수 있다면, i는 i/2에서 곱하기 2를 한 것과 같다.
            //그렇다면 단지 방법은 i/2에서 연산이 한 번 더 추가된 것이다.
            //-> 더 적은 연산 횟수를 찾는다.
            if (i%2==0) {
                D[i] = Math.min(D[i], D[i/2] + 1);
            }

            //3도 마찬가지다
            if (i%3==0) {
                D[i] = Math.min(D[i], D[i/3] + 1);
            }
        }
        System.out.println(D[N]);
    }
}
