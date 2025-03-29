package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 배열의 숫자가 존재하지 않는다면, 바다
 * 배열의 숫자가 존재한다면 빙산의 높이
 *
 * 빙산은 1년마다 녹으며, 녹는 양은 접해있는 바다의 면과 같다.
 *
 * 출력
 * 빙산이 두 덩어리 이상으로 분리되는 최초의 시간
 *
 * 풀이
 * 0. BFS로 섬이 2개 이상으로 나눠졌는지 확인
 * 1. 배열의 바다부분을 리스트에 저장
 * 2. 배열의 사방을 체크하면서, 빙산인 부분에 대해서 빙산을 녹인다.
 * 3. time 1 증가
 * 4. 만일 빙산이 다 녹을 때까지 2개 이상으로 나눠지지 않는다면 0 출력
 */
public class BOJ_2573 {

    static int[][] d = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        //지도 셋팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //바다 좌표 리스트
        List<int[]> seaPoints;
        boolean seperated;

        while(true) {
            seaPoints = new ArrayList<>();

            //1. 배열의 바다 좌표를 리스트에 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        seaPoints.add(new int[]{i,j});
                    }
                }
            }

            //2. 빙산을 녹인다.
            for (int i = 0; i < seaPoints.size(); i++) {
                int[] point = seaPoints.get(i);

                for (int j = 0; j < 4; j++) {
                    int x = point[0] + d[j][0];
                    int y = point[1] + d[j][1];

                    //인접한 좌표가 지도 바깥이라면 continue
                    if (x<0 || x>=N || y<0 || y>=M) {
                        continue;
                    }

                    //인접한 좌표가 빙산일 경우 녹임
                    if (map[x][y] > 0) {
                        map[x][y]--;
                    }
                }
            }

            //3. time 1 증가
            time++;

            //4. 만일 빙산이 다 녹을 때까지 2개 이상으로 나눠지지 않는다면, 반복문 종료하고 0 출력
            int temp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp+=map[i][j];
                }
            }

            //만약 빙하가 모두 녹아있다면
            if (temp==0) {
                seperated = false;
                break;
            }

            //4-1. 만일 빙산이 2개 이상으로 나눠진다면, 반복문 종료하고 time 출력
            if (checkSeperated()) {
                seperated = true;
                break;
            }
        }

        System.out.println(seperated?time:0);
    }

    //빙산이 분리되었는지 확인
    public static boolean checkSeperated() {
        visited = new boolean[N][M];

        //빙산 탐색
        boolean isEnd = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //처음 발견한 빙산을 시작점으로 DFS 탐색
                if (map[i][j] !=0 && !visited[i][j]) {
                    DFS(i, j);
                    isEnd = true;
                    break;
                }
            }
            if (isEnd) {
                break;
            }
        }

        //빙하를 재탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //만약 방문 안 된 빙하가 있다면, 빙하가 분리됨
                if (map[i][j] !=0 && !visited[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void DFS(int x, int y) {
        visited[x][y] = true;
        for (int i=0; i<4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (map[nextX][nextY]!=0 && !visited[nextX][nextY]) {
                DFS(nextX, nextY);
            }
        }
    }
}
