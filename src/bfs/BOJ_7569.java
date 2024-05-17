package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {

    static int[][] moved = new int[][]{
        {0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
    static Queue<int[]> queue = new LinkedList<>();

    static int[][][] D;
    static int M;
    static int N;
    static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //가로 수
        M = Integer.parseInt(st.nextToken());
        //세로 수
        N = Integer.parseInt(st.nextToken());
        //높이
        H = Integer.parseInt(st.nextToken());

        D = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    D[i][j][k] = Integer.parseInt(st.nextToken());
                    if(D[i][j][k] == 1) {
                        queue.add(new int[]{i,j,k});
                    }
                }
            }
        }

        System.out.println(BFS());
    }

    public static int BFS() {

        int result = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] move : moved) {
                int nextH = current[0] + move[0];
                int nextN = current[1] + move[1];
                int nextM = current[2] + move[2];

                //배열 크기가 오바되면 next
                if (nextH >= H || nextN >= N || nextM >= M || nextH < 0 || nextN < 0 || nextM < 0) {
                    continue;
                }

                //익지 않은 토마토가 아니라면,
                if (D[nextH][nextN][nextM] != 0) {
                    continue;
                }

                queue.add(new int[]{nextH, nextN, nextM});
                D[nextH][nextN][nextM] = D[current[0]][current[1]][current[2]] + 1;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(D[i][j][k] == 0) return -1;
                    result = Math.max(result, D[i][j][k]);
                }
            }
        }

        if(result == 1) return 0;

        return result - 1;
    }
}
