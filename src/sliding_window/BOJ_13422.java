package sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * T : 테스트 케이스 수
 * 첫쨋 줄
 * N : 집의 갯수
 * M : 도둑이 훔칠 연속된 집의 갯수
 * K : 방범 장치가 작동하는 돈의 양 (넘으면 안됨)
 * 둘쨋 줄
 * 각 집이 갖고있는 돈의 양
 *
 *
 * 풀이 : 슬라이딩 윈도우
 */

public class BOJ_13422 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while(T-->0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] houseMoney = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                houseMoney[i] = Integer.parseInt(st.nextToken());
            }

            long currentMoney = 0;

            //슬라이딩 윈도우 초기 셋팅
            for (int i = 0; i < M; i++) {
                currentMoney += houseMoney[i];
            }

            //만약 집의 수와 훔칠 집의 수가 같다면
            if (N == M) {
                if (currentMoney < K) {
                    System.out.println(1);
                }
                else {
                    System.out.println(0);
                }
                continue;
            }

            //슬라이딩 윈도우
            int answer = 0;
            int left = 0;
            int right = M-1;
            while(left<N) {

                if (currentMoney < K) {
                    answer++;
//                    System.out.println(currentMoney + " " + left + " " + right);
                }

                int leftHouseMoney = houseMoney[left++];
                int rightHouseMoney = houseMoney[(++right)%N];

                currentMoney = currentMoney-leftHouseMoney+rightHouseMoney;
            }

            System.out.println(answer);
        }
    }
}
