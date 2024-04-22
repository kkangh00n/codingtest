package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {

    static final int[][] moved = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] field;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            field = new int[N][M];
            visited = new boolean[N][M];
            answer = 0;

            //밭 셋팅
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                field[x][y] = 1;
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    BFS(new int[]{j, k});
                }
            }

            System.out.println(answer);
        }
    }

    public static void BFS(int[] start) {

        if (visited[start[0]][start[1]] || field[start[0]][start[1]] != 1) {
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            //다음이동
            for (int[] m : moved) {

                int nX = cur[0] + m[0];
                int nY = cur[1] + m[1];

                //지도 바깥이라면
                if (nX < 0 || nX >= field.length || nY < 0 || nY >= field[0].length) {
                    continue;
                }

                //배추가 없다면
                if (field[nX][nY] != 1) {
                    continue;
                }

                //이미 방문했다면
                if (visited[nX][nY]) {
                    continue;
                }

                queue.add(new int[]{nX, nY});
                visited[nX][nY] = true;
            }
        }
        answer++;

    }
}
