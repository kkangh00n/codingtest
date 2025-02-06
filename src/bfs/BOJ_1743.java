package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743 {

    //세로 길이
    static int N;
    //가로 길이
    static int M;
    //음식물 쓰레기 갯수
    static int K;

    //지도
    static int[][] MAP;

    //방문 여부
    static boolean[][] visited;

    //현재 음식물 쓰레기 크기
    static int size = 0;

    static int[][] moved = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        MAP = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        //지도 셋팅
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            MAP[x][y] = 1;
        }

        //BFS & DFS
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!visited[i][j] && MAP[i][j] == 1) {
                    size = 1;
//                    answer = Math.max(answer, BFS(new int[]{i, j}));
                    answer = Math.max(answer, DFS(new int[]{i, j}));
                }
            }
        }

        System.out.println(answer);
    }

    //BFS
    public static int BFS(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentX = current[0];
            int currentY = current[1];

            for (int[] m : moved) {
                int nextX = currentX + m[0];
                int nextY = currentY + m[1];

                if (nextX <= 0 || nextX > N || nextY <= 0 || nextY > M) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                if (MAP[nextX][nextY] == 0) {
                    continue;
                }

                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                size++;
            }
        }
        return size;
    }

    public static int DFS(int[] current) {
        int currentX = current[0];
        int currentY = current[1];

        visited[currentX][currentY] = true;

        for (int[] m : moved) {
            int nextX = currentX + m[0];
            int nextY = currentY + m[1];

            if (nextX <= 0 || nextX > N || nextY <= 0 || nextY > M) {
                continue;
            }

            if (visited[nextX][nextY]) {
                continue;
            }

            if (MAP[nextX][nextY] == 0) {
                continue;
            }

            size++;
            DFS(new int[]{nextX, nextY});

        }

        return size;
    }
}
