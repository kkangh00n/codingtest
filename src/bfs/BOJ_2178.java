package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {

    static int[][] moved = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] arr;
    static int[][] visited;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //미로 세팅
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }

        }

        //방문 현황 초기화
        visited = new int[N][M];

        BFS(new int[]{0, 0});

        System.out.println(visited[N-1][M-1]);
    }

    static void BFS(int[] start) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            //현재 위치
            int[] current = queue.poll();
            int n = current[0];
            int m = current[1];

            for (int[] mve : moved) {
                //다음 위치
                int nextN = n + mve[0];
                int nextM = m + mve[1];

                //다음 위치가 지도 바깥이거나,
                //벽이거나
                //이미 방문했다면 추가하지 않음
                if (nextN < 0 || nextN >= N || nextM < 0 || nextM >= M
                    || arr[nextN][nextM] == 0
                    || visited[nextN][nextM] >= 1) {
                    continue;
                }

                //방문 체크 후, 큐에 저장
                visited[nextN][nextM] = visited[n][m] + 1;
                queue.add(new int[]{nextN, nextM});
            }
        }
    }
}
