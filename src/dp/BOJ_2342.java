package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2342 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //각 위치에서 이동할 때, 드는 힘
        int[][] mp = {
            {0,2,2,2,2},
            {2,1,3,4,3},
            {2,3,1,3,4},
            {2,4,3,1,3},
            {2,3,4,3,1}
        };

        //수열의 수, 왼발이 갈 수 있는 경우, 오른발이 갈 수 있는 경우
        int[][][] dp = new int[100001][5][5];

        //충분히 큰 수로 초기화
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 100001; k++) {
                    dp[k][i][j] = 100001*4;
                }
            }
        }
        dp[0][0][0] = 0;

        int s=1;
        //모든 수열 확인
        while (st.hasMoreTokens()) {
            int step = Integer.parseInt(st.nextToken());
            if(step==0) break;

            //왼발이 i이고 오른발이 j인 상태에서 오른발을 step으로 옮겼을 때의 최소 힘
            for (int i = 0; i < 5; i++) {

                //왼발과 다음 오른발이 같을 수 없음
                if(step==i) continue;

                for (int j = 0; j < 5; j++) {
                    //오른발을 옮겼을 경우
                    dp[s][i][step] = Math.min(dp[s-1][i][j] + mp[j][step], dp[s][i][step]);
                }
            }

            //오른발이 i이고 왼발이 j인 상태에서 왼발을 step으로 옮겼을 때의 최소 힘
            for (int j = 0; j < 5; j++) {

                //왼발과 다음 오른발이 같을 수 없음
                if(step==j) continue;

                for (int i = 0; i < 5; i++) {
                    //오른발을 옮겼을 경우
                    dp[s][step][j] = Math.min(dp[s-1][i][j] + mp[i][step], dp[s][step][j]);
                }
            }
            s++;
        }
        s--;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[s][i][j]);
            }
        }
        System.out.println(min);

    }
}
