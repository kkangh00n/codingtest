package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1초 동안 일어나는 일
 * 1. 미세먼지 확산
 * - 공기청정기가 있지 않은 인접한 네 방향으로 확산된다.
 * - 확산되는 미세먼지 양 = 기존 양/5 이다.
 * - 기존 미세먼지 양 = 기존 양 - 확산되는 양 이다.
 *
 * 2. 공기청정기 작동
 * - 공기 청정기는 2칸을 차지한다.
 * - 공기 청정기의 위쪽과 아래쪽에서 바람이 각각 나온다.
 * - 위쪽에서 나오는 바람은 반시계방향 순환
 * - 아래쪽에서 나오는 바람은 시계방향 순환
 *
 * 문제
 * T초후에 방에 남아있는 미세먼지 양을 구하자
 *
 * 풀이
 * 1. 정보 입력 및 지도 초기화
 * - 공기 청정기 위치 확인 및 윗칸 아랫칸 확인
 * 2. 미세먼지 확산
 * - 지도를 모두 돌면서 각 4방향의 미세먼지를 확산 -> 동시에 확산되므로 큐 이용
 * - 만약 인접한 칸이 벽이거나 공기청정기일 경우, 확산 X
 * 3. 공기청정기 작동
 * - 윗칸과 동작
 * - 미세먼지 이동 로직
 * - 현재 미세먼지를 임시 저장 후, 현재 위치의 이전 미세먼지 저장 반복
 * - 방향지정하여 동작
 * 4. T초 반복
 */
public class BOJ_17144 {

    static int[][] moved = new int[][]{{0,1},{-1,0},{0,-1},{1,0}};

    public static void main(String[] args) throws IOException {

        //1. 정보 입력 및 지도 초기화
        //공기 청정기 위치 확인 및 윗칸 아랫칸 확인
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<int[]> cleaners = new ArrayList<>();

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == -1) {
                    cleaners.add(new int[]{i,j});
                }
            }
        }

        //4. T초 반복
        while(T-->0) {
            //2. 미세먼지 확산
            Queue<int[]> queue = new LinkedList<>();

            //지도를 모두 돌면서 각 4방향의 미세먼지를 위치 저장 후,
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if (map[i][j] >=5) {
                        queue.add(new int[]{i,j,map[i][j]});
                    }
                }
            }

            //미세먼지 동시 확산
            while(!queue.isEmpty()) {
                int[] current = queue.poll();
                int currentX = current[0];
                int currentY = current[1];
                int currentMunzi = current[2];

                for(int[] m : moved) {
                    int nextX = currentX + m[0];
                    int nextY = currentY + m[1];

                    //만약 인접한 칸이 벽이거나 공기청정기일 경우, 확산 X
                    if (nextX<0||nextX>=N||nextY<0||nextY>=M) {
                        continue;
                    }

                    if (map[nextX][nextY] == -1) {
                        continue;
                    }

                    map[nextX][nextY] += currentMunzi/5;
                    map[currentX][currentY] -= currentMunzi/5;
                }
            }

            //공기청정기 윗칸과 아랫칸 각각 작동
            for (int i=0; i<cleaners.size(); i++) {
                int[] cleaner = cleaners.get(i);
                int direction = 0;
                int turn;

                //윗칸일 경우와 아랫칸일 경우 벽에 부딪혔을 때, 움직일 방향을 구분
                if (i%2==0) {
                    turn = 1;
                }
                else {
                    turn = 3;
                }

                //미세먼지 이동 로직
                //현재 미세먼지를 임시 저장 후, 현재 위치의 이전 미세먼지 저장 반복
                int currentValue = 0;
                int currentX = cleaner[0];
                int currentY = cleaner[1];
                while(true) {
                    int nextX = currentX + moved[direction][0];
                    int nextY = currentY + moved[direction][1];

                    //벽에 다다른 경우, 방향 전환
                    if (nextX<0||nextY<0||nextX>=N||nextY>=M) {
                        direction = (direction + turn)%4;
                        nextX = currentX + moved[direction][0];
                        nextY = currentY + moved[direction][1];
                    }

                    if (nextX == cleaner[0] && nextY == cleaner[1]) {
                        break;
                    }

                    //미세먼지 이동
                    int temp = map[nextX][nextY];
                    map[nextX][nextY] = currentValue;
                    currentValue = temp;

                    currentX = nextX;
                    currentY = nextY;
                }
            }
        }

        int answer = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if (map[i][j]>=0) {
                    answer+=map[i][j];
                }
            }
        }
        System.out.println(answer);
    }
}
