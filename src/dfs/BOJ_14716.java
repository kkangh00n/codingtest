package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14716 {

    static int[][] moved = new int[][]{{0, 1},{1, 0},{-1, 0},{0, -1},{1, 1},{-1, -1},{1, -1},{-1, 1}};

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    DFS(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int i, int j) {
        for (int[] m : moved) {
            int nextI = i + m[0];
            int nextJ = j + m[1];

            if (nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
                continue;
            }

            if (map[nextI][nextJ] == 1 && !visited[nextI][nextJ]) {
                visited[nextI][nextJ] = true;
                DFS(nextI, nextJ);
            }
        }
    }
}
