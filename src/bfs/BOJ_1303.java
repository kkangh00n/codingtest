package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303 {

    static int[][] moved = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};

    static char[][] map;
    static boolean[][] visited;

    static int N, M;

    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        visited = new boolean[M][N];


        for (int i = 0; i < M; i++) {
            char[] charArray = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                map[i][j] = charArray[j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }

                int temp = BFS(i, j);

                if (map[i][j] == 'W') {
                    white+=temp*temp;
                }
                else {
                    blue+=temp*temp;
                }
            }
        }

        System.out.println(white + " " + blue);
    }

    public static int BFS(int x, int y) {
        int result = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            char team = map[current[0]][current[1]];
            result++;

            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                if (nextX<0||nextX>=M||nextY<0||nextY>=N) {
                    continue;
                }

                if (map[nextX][nextY] != team) {
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
