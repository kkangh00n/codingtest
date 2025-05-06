package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 브루트포스 + BFS
 * 전체를 돌면서 네모의 갯수와
 * 가장 큰 네모의 넓이를 출력
 */
public class BOJ_1926 {

    static int[][] moved = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};
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

        int squareCount = 0;
        int maxSquare = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                squareCount++;
                maxSquare = Math.max(BFS(i, j), maxSquare);
            }
        }

        System.out.println(squareCount);
        System.out.println(maxSquare);
    }

    public static int BFS(int x, int y) {
        int result = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            result++;

            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                if (nextX<0||nextX>=N || nextY<0||nextY>=M) {
                    continue;
                }

                if (map[nextX][nextY] == 0) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }

        return result;
    }
}
