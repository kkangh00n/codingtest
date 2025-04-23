package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 왼쪽 위에서 오른쪽 아래로 이동
 * 2. H가 높은 곳에서 낮은 곳으로만 이동
 *
 * DP + 깊이 우선 탐색
 * dp 배열 -> 해당 지점에서의 길 수
 */
public class BOJ_1520 {

    static int N,M;
    static int[][] map, dp;

    static int[][] moved = new int[][] {{0, 1},{0, -1},{1, 0},{-1 ,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(DFS(0,0));
    }

    //길 탐색
    public static int DFS(int x, int y) {
        if (x==N-1 && y==M-1) {
            return 1;
        }

        //만약 이미 갔던 길이라면, 리턴
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int[] m = moved[i];

            int nextX = x + m[0];
            int nextY = y + m[1];

            if (nextX<0||nextX>=N||nextY<0||nextY>=M) {
                continue;
            }

            if (map[nextX][nextY] >= map[x][y]) {
                continue;
            }

            //내리막길 수 저장
            dp[x][y] += DFS(nextX, nextY);
        }

        return dp[x][y];
    }

}
