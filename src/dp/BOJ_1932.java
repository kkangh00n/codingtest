package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 정수 삼각형이 주어진다.
 * 2. 꼭짓점부터 시작하여 바로 아래 있는 수를 하나 선택하여 아래층으로 내려온다
 * 3. 그럴 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하라
 *
 * dp
 * 위에서 아래부터 X
 * 아래에서부터 위로 시작하여 최댓값을 찾는다.
 *
 * i : 층수
 * dp[i][0] -> i층의 0번째 칸에 최댓값
 *
 * 점화식
 * dp[i][j] = Math.max(dp[i+1][j] + triangles[i][j], dp[i+1][j+1] + triangles[i][j])
 */
public class BOJ_1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] triangles = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangles[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j <= i; j++) {
//                System.out.print(triangles[i][j]);
//            }
//            System.out.println();
//        }

        for (int i = triangles.length-2; i >=0 ; i--) {
            for (int j = 0; j <= i; j++) {
                int left = triangles[i + 1][j] + triangles[i][j];
                int right = triangles[i + 1][j + 1] + triangles[i][j];
                triangles[i][j] = Math.max(left, right);
            }
        }

        System.out.println(triangles[0][0]);
    }

}
