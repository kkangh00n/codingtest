package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14502 {

    static int[][] moved = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;

    static List<Location> viruses;

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 2) {
                    viruses.add(new Location(i, j));
                }
            }
        }

        DFS(0);

        System.out.println(answer);
    }

    //벽 세개를 세우는 경우를 모두 탐색
    public static void DFS(int wallCount) {
        if (wallCount == 3) {
            BFS();
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = 1;
                        DFS(wallCount + 1);
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    //3개의 벽이 모두 세워졌을 경우, 최대 안전 영역 구함
    public static void BFS() {
        int[][] cloneMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            cloneMap[i] = map[i].clone();
        }
        visited = new boolean[N][M];

        Queue<Location> queue = new LinkedList<>();
        for (Location virus : viruses) {
            queue.add(virus);
            visited[virus.x][virus.y] = true;
        }

        while (!queue.isEmpty()) {
            Location virus = queue.poll();
            cloneMap[virus.x][virus.y] = 2;

            for (int[] m : moved) {
                int nextX = virus.x + m[0];
                int nextY = virus.y + m[1];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                if (cloneMap[nextX][nextY] != 0) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;

                queue.add(new Location(nextX, nextY));
            }
        }

        int temp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cloneMap[i][j] == 0) {
                    temp++;
                }
            }
        }
        answer = Math.max(answer, temp);
    }
}

class Location {

    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
