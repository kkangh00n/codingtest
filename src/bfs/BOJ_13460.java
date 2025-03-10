package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * . : 빈칸 # : 벽 o : 구멍 R : 빨간 구슬 B : 파란 구슬
 */
public class BOJ_13460 {

    static int[][] moved = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static char[][] map;
    static boolean[][][][] visited;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        BeadLocation startBeadLocation = new BeadLocation();

        for (int i = 0; i < N; i++) {
            char[] input = sc.next().toCharArray();

            for (int j = 0; j < M; j++) {
                map[i][j] = input[j];

                if (map[i][j] == 'R') {
                    startBeadLocation.redX = i;
                    startBeadLocation.redY = j;
                }

                if (map[i][j] == 'B') {
                    startBeadLocation.blueX = i;
                    startBeadLocation.blueY = j;
                }
            }
        }

        BFS(startBeadLocation);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void BFS(BeadLocation startBeadLocation) {
        Queue<BeadLocation> queue = new LinkedList<>();
        queue.add(startBeadLocation);
        visited[startBeadLocation.redX][startBeadLocation.redY][startBeadLocation.blueX][startBeadLocation.blueY] = true;

        while (!queue.isEmpty()) {
            BeadLocation current = queue.poll();
            int index = current.index;

            //10번 초과하여 움직이면 break
            if (index >= 10) {
                break;
            }

            //위치 이동
            for (int[] m : moved) {
                int previousRedX = current.redX;
                int previousRedY = current.redY;
                int previousBlueX = current.blueX;
                int previousBlueY = current.blueY;

                BeadLocation next = new BeadLocation(
                        current.redX, current.redY, current.blueX, current.blueY, index + 1);

                //빨간색 구슬을 쭉 이동
                while (map[next.redX + m[0]][next.redY + m[1]] != '#') {
                    next.moveRed(m[0], m[1]);

                    if (map[next.redX][next.redY] == 'O') {
                        break;
                    }
                }

                //파란색 구슬 쭉 이동
                while (map[next.blueX + m[0]][next.blueY + m[1]] != '#') {
                    next.moveBlue(m[0], m[1]);

                    if (map[next.blueX][next.blueY] == 'O') {
                        break;
                    }
                }

                //파란색 구슬이 들어갔다면
                if (map[next.blueX][next.blueY] == 'O') {
                    continue;
                }

                //빨간색 구슬이 들어갔다면
                if (map[next.redX][next.redY] == 'O') {
                    answer = Math.min(answer, next.index);
                    return;
                }

                //파란색과 빨간색이 만났다면
                if (next.redX == next.blueX && next.redY == next.blueY) {

                    int redMoved =
                            Math.abs(next.redX - previousRedX) + Math.abs(next.redY - previousRedY);
                    int blueMoved = Math.abs(next.blueX - previousBlueX) + Math.abs(
                            next.blueY - previousBlueY);

                    //빨간 구슬이 더 이동거리가 많다면,
                    if (redMoved > blueMoved) {
                        next.redX -= m[0];
                        next.redY -= m[1];
                    } else {
                        next.blueX -= m[0];
                        next.blueY -= m[1];
                    }
                }

                if (visited[next.redX][next.redY][next.blueX][next.blueY]) {
                    continue;
                }

                queue.add(next);
                visited[next.redX][next.redY][next.blueX][next.blueY] = true;
            }
        }
    }
}

class BeadLocation {

    int redX;
    int redY;
    int blueX;
    int blueY;

    int index;

    BeadLocation() {
        this.index = 0;
    }

    BeadLocation(int redX, int redY, int blueX, int blueY, int index) {
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
        this.index = index;
    }

    void moveRed(int mX, int mY) {
        this.redX += mX;
        this.redY += mY;
    }

    void moveBlue(int mX, int mY) {
        this.blueX += mX;
        this.blueY += mY;
    }
}
