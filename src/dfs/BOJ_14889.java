package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 브루트 포스 & 완전탐색
 *
 * 1. 팀을 N/2명 결성할 때까지 DFS
 * 2. N/2명 결성 시, 두 팀의 능력치 최솟값 구함.
 */

public class BOJ_14889 {

    static int answer = Integer.MAX_VALUE;

    static int N;
    static int[][] board;
    static boolean[] team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];
        team = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1부터 DFS
        for (int i = 1; i <= N; i++) {
            DFS(i, 0);
        }

        System.out.println(answer);
    }

    public static void DFS(int current, int depth) {
        //만약 현재 팀이 N/2명 결성 되었다면, 팀 능력치 최솟값 구함
        if (depth == N/2) {
            answer = Math.min(answer, result());
        }

        for (int i = current; i <= N; i++) {
            team[current] = true;
            DFS(i+1, depth+1);
            team[current] = false;
        }
    }

    //팀 간 능력치 차 구함
    public static int result() {
        int start = 0;
        int link = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                if (team[i] && team[j]) {
                    start += board[i][j];
                }
                else if (!team[i] && !team[j]) {
                    link += board[i][j];
                }
            }
        }

        return Math.abs(start-link);
    }
}
