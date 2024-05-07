package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468 {

    static int N;
    static int MAX_HEIGHT;
    static int[][] arr;
    static boolean[][] visited;

    static int[][] moved = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        MAX_HEIGHT = 0;
        //지도
        arr = new int[N][N];

        //지도 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                //지역 최대 높이
                if (arr[i][j] > MAX_HEIGHT) {
                    MAX_HEIGHT = arr[i][j];
                }
            }
        }

        int answer = 0;

        //높이 별로 탐색
        for (int h = 0; h <= MAX_HEIGHT; h++) {

            //안전 지역 갯수
            int result = 0;

            //방문 여부
            visited = new boolean[N][N];

            //시작지점 옮기면서 탐색
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    //안전지역을 찾았고, 방문하지 않은 곳이라면, 그 영역에서부터 탐색 시작
                    if (arr[j][k] > h && !visited[j][k]) {
                        result += BFS(h, new int[]{j, k});
                    }
                }
            }

            if (result > answer) {
                answer = result;
            }
        }

        System.out.println(answer);
    }

    //현재 높이에서
    static int BFS(int height, int[] start) {
        int result = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                //지도 바깥이라면 continue
                if (nextX >= N || nextX < 0 || nextY >= N || nextY < 0) {
                    continue;
                }

                //이미 방문했다면 continue
                if (visited[nextX][nextY]) {
                    continue;
                }

                //높이가 후달린다면 continue
                if (arr[nextX][nextY] <= height) {
                    continue;
                }

                visited[nextX][nextY] = true;
                queue.add(new int[]{nextX, nextY});
            }
        }
        return 1;
    }
}
