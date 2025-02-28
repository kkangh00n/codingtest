package dfs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ_1987 {

    static int R, C;
    static int answer;

    static char[][] map;
    static boolean[][] visited;
    static Set<Character> set = new HashSet<>();

    static int[][] moved = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String input = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visited[0][0] = true;
        set.add(map[0][0]);
        answer = 1;
        DFS(0, 0);

        System.out.println(answer);
    }

    public static void DFS(int x, int y) {
        for (int[] m : moved) {
            int nextX = x + m[0];
            int nextY = y + m[1];

            if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) {
                continue;
            }
            if (visited[nextX][nextY]) {
                continue;
            }
            if (set.contains(map[nextX][nextY])) {
                continue;
            }

            visited[nextX][nextY] = true;
            set.add(map[nextX][nextY]);
            answer = Math.max(answer, set.size());

            DFS(nextX, nextY);

            visited[nextX][nextY] = false;
            set.remove(map[nextX][nextY]);
        }
    }
}
