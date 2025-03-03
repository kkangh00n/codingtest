package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {

    static int N;
    static int M;

    static int answer = 0;

    static int[][] map;
    static boolean[][][] visited;

    static int[][] moved = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        BFS();

        System.out.println(answer != 0 ? answer : -1);
    }

    public static void BFS() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0, false));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == N - 1 && point.y == M - 1) {
                answer = point.count + 1;
                break;
            }
            for (int[] m : moved) {
                int nextX = point.x + m[0];
                int nextY = point.y + m[1];
                int count = point.count;

                //행렬 범위를 벗어날 경우
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                //통로일 경우
                if (map[nextX][nextY] == 0) {
                    //이전에 벽을 부신 상태라면
                    if (point.boom && !visited[nextX][nextY][1]) {
                        visited[nextX][nextY][1] = true;
                        queue.add(new Point(nextX, nextY, count + 1, true));
                    }
                    //벽을 부시지 않았다면
                    else if (!point.boom && !visited[nextX][nextY][0]){
                        visited[nextX][nextY][0] = true;
                        queue.add(new Point(nextX, nextY, count + 1, false));
                    }
                }

                //벽을 만난 경우
                else if (map[nextX][nextY] == 1) {
                    //이전에 벽을 부수지 않았다면
                    if (!point.boom && !visited[nextX][nextY][1]) {
                        visited[nextX][nextY][1] = true;
                        queue.add(new Point(nextX, nextY, count + 1, true));
                    }
                }
            }
        }

    }
}

class Point {

    int x;
    int y;
    int count;
    boolean boom;

    public Point(int x, int y, int count, boolean boom) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.boom = boom;
    }
}
