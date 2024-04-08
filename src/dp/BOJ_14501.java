package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        //하루 최대 벌 수 있는 액수
        int[] D = new int[N + 2];
        //상담이 걸리는 시간
        int[] T = new int[N + 1];
        //받을 수 있는 액수
        int[] P = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            //현재 날짜 + 상담이 걸리는 시간 > 퇴사일
            if (i + T[i] > N + 1) {
                D[i] = D[i + 1];
            }
            //현재 날짜 + 상담이 걸리는 시간 < 퇴사일
            else {
                //현재 날짜에 벌 수 있는 금액
                D[i] = Math.max(
                    //i+1일 ~ 퇴사일까지 벌 수 있는 금액
                    D[i + 1],
                    //i일 상담과 T[i] 이후에 벌 수 있는 금액
                    P[i] + D[i + T[i]]
                );
            }
        }

        System.out.println(D[1]);
    }
}
