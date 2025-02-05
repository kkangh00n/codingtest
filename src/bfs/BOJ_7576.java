package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {

    //가로
    static int M;
    //세로
    static int N;

    //토마토 상자
    static int[][] BOX;

    static Queue<int[]> queue = new LinkedList<>();

    static int[][] moved = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        BOX = new int[N][M];

        //상자 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                BOX[i][j] = Integer.parseInt(st.nextToken());

                if (BOX[i][j] == 1) queue.add(new int[]{i,j,0});
            }
        }

        int days = BFS();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (BOX[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(days);

    }


    public static int BFS() {
        int days=0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            days = current[2];

            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
                    continue;
                }

                if (BOX[nextX][nextY] == 0) {
                    queue.add(new int[]{nextX, nextY, days+1});
                    BOX[nextX][nextY] = 1;
                }
            }
        }

        return days;
    }
}
