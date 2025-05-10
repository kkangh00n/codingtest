package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N*M 격자판이 있다
 * 그 중 선택한 K 칸의 숫자를 더한 최댓값을 구하려고 한다.
 * 단, 선택한 값은 인접할 수 없다.
 *
 * 입력
 * N,M,K
 * 격자판
 *
 * 출력
 * 최댓값
 */
public class BOJ_18290 {

    static int N, M, K;
    static int[][] map;
    static boolean[][] selected;

    static int[][] moved = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        selected = new boolean[N][M];

        //지도 셋팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0);

        System.out.println(answer);
    }

    //브루트 포스 탐색
    public static void solve(int current, int depth) {
        //숫자를 모두 선택했을 경우
        if (depth == K) {
            answer = Math.max(current, answer);
            return;
        }

        //브루트포스 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (selected[i][j]) {
                    continue;
                }

                if (isNotSelectNear(i, j)) {
                    selected[i][j] = true;
                    solve(current+map[i][j], depth+1);
                    selected[i][j] = false;
                }
            }
        }
    }

    public static boolean isNotSelectNear(int x, int y) {
        boolean flag = true;

        for (int[] m : moved) {

            int nearX = x+m[0];
            int nearY = y+m[1];

            if (nearX<0||nearX>=N||nearY<0||nearY>=M) {
                continue;
            }

            if (selected[nearX][nearY]) {
                flag = false;
                break;
            }
        }

        return flag;
    }
}
