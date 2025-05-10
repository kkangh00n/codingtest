package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 격자판 맨 왼쪽 위에서 맨 오른쪽 아래로 이동해야 한다.
 * 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법 갯수를 출력하라
 *
 * 풀이
 * 1. 0,0에서부터 이동할 수 있는 모든 경우의 수를 DFS 탐색한다.
 * 2. 만약 말처럼 움직인다면, 말 움직임 기회를 차감한다.
 *
 * 핵심 - 방문 여부 배열
 * 말 이동 횟수 여부에 따라 같은 위치라도 다르게 여부를 파악해야 함..!
 *
 * ex)
 * 2,3 위치의 말 이동횟수를 1번 사용해서 왔을 때,
 * 2,3 위치의 말 이동횟수를 2번 사용해서 왔을 때
 * 두 경우 모두 다른 경우이므로, 구분해주어야 함
 */
public class BOJ_1600 {

    static int[][] moved = new int[][]{{1, 0},{-1, 0},{0, 1},{0, -1}};
    static int[][] horseMoved = new int[][]{{-2, -1},{-2, 1},{2, -1},{2, 1},{-1, -2},{-1, 2},{1, -2},{1, 2}};

    static int K, W, H;

    static int[][] map;
    static boolean[][][] visited;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //원숭이가 말의 이동 방법으로 움직일 수 있는 횟수
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //1 이상 200 이하
        //격자판 가로 길이
        W = Integer.parseInt(st.nextToken());
        //격자판 세로 길이
        H = Integer.parseInt(st.nextToken());

        //격자판 셋팅
        map = new int[H][W];

        //방문 여부 배열
        //핵심!
        visited = new boolean[H][W][K+1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();

        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }

    //0,0에서부터 BFS 탐색한다.
    public static void BFS() {
        Queue<int[]> queue = new LinkedList<>();
        //위치, 현재까지 말 움직임 횟수, 현재 동작 횟수
        queue.add(new int[]{0,0,0,0});
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            //1. 도착지점에 도달했다면, 최소 거리 비교 및 방법 증가
            if (current[0] == H-1 && current[1] == W-1) {
                answer = Math.min(answer, current[3]);
                break;
            }

            //이미 말의 동작으로 움직일 수 있는 경우
            if (current[2] < K) {
                //말의 움직임으로 이동
                for (int[] m : horseMoved) {
                    int nextX = current[0] + m[0];
                    int nextY = current[1] + m[1];

                    //지도 바깥일 경우
                    if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
                        continue;
                    }

                    //장애물일 경우
                    if (map[nextX][nextY] == 1) {
                        continue;
                    }

                    //이미 지나온 곳일 경우
                    if (visited[nextX][nextY][current[2]+1]) {
                        continue;
                    }

                    queue.add(new int[]{nextX, nextY, current[2]+1, current[3]+1});
                    visited[nextX][nextY][current[2]+1] = true;
                }
            }

            //평소의 동작으로 이동
            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                //지도 바깥일 경우
                if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
                    continue;
                }

                //장애물일 경우
                if (map[nextX][nextY] == 1) {
                    continue;
                }

                //이미 지나온 곳일 경우
                if (visited[nextX][nextY][current[2]]) {
                    continue;
                }

                queue.add(new int[]{nextX, nextY, current[2], current[3]+1});
                visited[nextX][nextY][current[2]] = true;
            }
        }


    }
}
