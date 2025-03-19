package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * 1. 지도의 크기, 주사위 좌표, 명령 갯수
 * 2. 지도 현황
 * 3. 명령 1:동쪽 2:서쪽 3:북쪽 4:남쪽
 *
 * 조건
 * 0. 처음 주사위는 모두 0 이다.
 * 1. 시작 시, 주사위는 1이 위쪽, 3이 동쪽, 6이 밑면
 * 2. 이동한 지도의 칸이 0이라면, 주사위의 수가 지도 위에 복사
 * 3. 이동한 지도의 칸의 숫자가 존재하면, 지도의 칸의 숫자가 주사위로 이동, 지도의 칸은 0으로 변경
 * 4. 지도 바깥으로 이동한다면, 해당 명령은 무시 (출력 X)
 *
 * 주의 : 주사위의 이동 규칙
 * 동쪽으로 이동
 *   2
 * 6 4 1
 *   5
 *   3
 *
 * 남쪽으로 이동
 *   6
 * 4 2 3
 *   1
 *   5
 *
 * 출력 : 주사위의 윗 면에 쓰여 있는 수 출력
 */
public class BOJ_14499 {

    //동서북남
    static int[][] moved = new int[][] {{0,0},{0,1},{0,-1},{-1,0},{1,0}};

    // 1
    //234
    // 5
    // 6
    static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        //지도 셋팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int direction = Integer.parseInt(st.nextToken());

            //위치 이동 확인
            int nextX = x + moved[direction][0];
            int nextY = y + moved[direction][1];
            //지도를 벗어날 경우 통과
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }

            //주사위 이동
            //동쪽
            // 1
            //234
            // 5
            // 6
            //
            // 1
            //623
            // 5
            // 4

            int temp = dice[6];

            if (direction == 1) {
                dice[6] = dice[4];
                dice[4] = dice[3];
                dice[3] = dice[2];
                dice[2] = temp;
            }

            //서쪽
            // 1
            //346
            // 5
            // 2
            if (direction == 2) {
                dice[6] = dice[2];
                dice[2] = dice[3];
                dice[3] = dice[4];
                dice[4] = temp;
            }

            //남쪽
            // 1
            //234
            // 5
            // 6
            //
            // 6
            //214
            // 3
            // 5
            if (direction == 4) {
                dice[6] = dice[5];
                dice[5] = dice[3];
                dice[3] = dice[1];
                dice[1] = temp;
            }

            //북쪽
            // 3
            //254
            // 6
            // 1
            if (direction == 3) {
                dice[6] = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[5];
                dice[5] = temp;
            }

            //맨 위 숫자 출력
            System.out.println(dice[3]);

            x = nextX;
            y = nextY;

            //이동한 지도의 칸이 0이라면, 주사위 바닥면의 수가 지도 위에 복사
            if (map[x][y] == 0) {
                map[x][y] = dice[6];
            }
            //이동한 지도의 칸의 숫자가 존재하면, 지도의 칸의 숫자가 주사위 바닥면으로 이동, 지도의 칸은 0으로 변경
            else {
                dice[6] = map[x][y];
                map[x][y] = 0;
            }
        }
    }
}
