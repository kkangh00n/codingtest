package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_2667 {

    static int N;
    //단지 수
    static int COUNT = 0;
    //단지 별 아파트 수
    static List<Integer> APARTMENT = new ArrayList<>();

    static int[][] arr;
    static boolean[][] visited;

    static int[][] moved = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //지도
        arr = new int[N][N];
        //방문 현황 초기화
        visited = new boolean[N][N];

        //지도 형성
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        //탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //방문하지 않은 단지라면 -> 탐색
                if (arr[i][j] == 1 && !visited[i][j]) {
                    //단지 찾음
                    COUNT++;
                    //단지 내 아파트 갯수
                    BFS(new int[]{i, j});
                }
            }
        }

        System.out.println(COUNT);
        Collections.sort(APARTMENT);
        for (Integer A : APARTMENT) {
            System.out.println(A);
        }

    }

    static void BFS(int[] current) {
        int nextX;
        int nextY;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(current);
        visited[current[0]][current[1]] = true;
        int apartmentCount = 1;

        //단지 내 아파트 갯수 찾기
        while (!queue.isEmpty()) {
            int[] currentP = queue.poll();

            //사방으로 탐색
            for (int[] mve : moved) {
                nextX = currentP[0] + mve[0];
                nextY = currentP[1] + mve[1];

                //지도 바깥이거나
                //이미 방문을 했거나
                //단지가 아니라면 skip
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N
                    || visited[nextX][nextY]
                    || arr[nextX][nextY] == 0
                ) {
                    continue;
                }

                //큐에 추가
                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                apartmentCount++;
            }
        }
        if (apartmentCount != 0) {
            APARTMENT.add(apartmentCount);
        }
    }
}
