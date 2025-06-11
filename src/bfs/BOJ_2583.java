package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583 {

    static int[][] moved = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    static int N, M;

    static int[][] map;

    static int answer = 0;
    static List<Integer> sizes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int m1 = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            for (int j = n1; j < n2; j++) {
                for (int k = m1; k < m2; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 1) {
                    sizes.add(BFS(i, j));
                    answer++;
                }
            }
        }

        Collections.sort(sizes);

        System.out.println(answer);
        for (Integer size : sizes) {
            System.out.print(size + " ");
        }
    }

    static int BFS(int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, m});
        map[n][m] = 1;

        int result = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] move : moved) {
                int nextN = current[0] + move[0];
                int nextM = current[1] + move[1];

                if (nextN < 0 || nextN >= N || nextM < 0 || nextM >= M) {
                    continue;
                }

                if (map[nextN][nextM] == 1) {
                    continue;
                }

                queue.add(new int[]{nextN, nextM});
                map[nextN][nextM] = 1;
            }

            result++;
        }

        return result;
    }
}
