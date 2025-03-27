package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 톱니바퀴가 4개, 각각 톱니는 8개 있다.
 * 하나가 회전하면 맞물려서 회전한다. 이 때, 맞물려 있는 곳의 극이 같다면, 맞물려있는 톱니바퀴는 회전하지 않는다.
 *
 * 입력
 * 톱니바퀴 4개
 * 회전수
 * 회전한 톱니바퀴, 회전 방향
 *
 * 출력
 * 네 톱니바퀴 점수의 합
 *
 * 풀이
 * 1. 회전할 톱니바퀴를 기준으로, 다른 톱니바퀴들의 극을 확인하여 맞물려 회전하는지 확인한다
 * 2. 맞물려 회전하는 톱니바퀴는 방향에 맞게 회전시킨다.
 *
 */
public class BOJ_14891 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //톱니바퀴 입력 :    N극 -> 0    S극 -> 1
        int[][] gears = new int[4][8];

        for (int i=0; i<4; i++) {
            String[] split = br.readLine().split("");
            for (int j=0; j<8; j++) {
                gears[i][j] = Integer.parseInt(split[j]);
            }
        }

        //회전수 입력
        int K = Integer.parseInt(br.readLine());

        while(K-->0) {
            st = new StringTokenizer(br.readLine());

            //회전할 톱니바퀴
            int gearNum = Integer.parseInt(st.nextToken());
            //방향 :    1 -> 시계   -1 -> 반시계    0 -> 회전 X
            int d = Integer.parseInt(st.nextToken());


            //회전 여부, 회전 방향
            //회전할 경우 1, 회전하지 않을 경우 0
            //시계 방향 1, 반시계 방향 1
            int[][] isTurn = new int[4][2];
            isTurn[gearNum-1][0] = 1;
            isTurn[gearNum-1][1] = d;

            //현재 기어의 왼쪽이 맞물려있는지 확인
            for (int i=gearNum-1; i>0; i--) {
                if (gears[i][6] != gears[i-1][2]) {
                    isTurn[i-1][0] = 1;
                    isTurn[i-1][1] = -isTurn[i][1];
                }
                else {
                    break;
                }
            }

            //현재 기어의 오른쪽이 맞물려있는지 확인
            for (int i=gearNum-1; i<3; i++) {
                if (gears[i][2] != gears[i+1][6]) {
                    isTurn[i+1][0] = 1;
                    isTurn[i+1][1] = -isTurn[i][1];
                }
                else {
                    break;
                }
            }

            //기어를 알맞게 회전
            for(int i=0; i<4; i++) {
                if (isTurn[i][0] == 0) {
                    continue;
                }

                int temp;

                //시계방향 회전일 경우
                if (isTurn[i][1] == 1) {
                    temp = gears[i][7];

                    //기어 이동
                    for(int j=6; j>=0; j--) {
                        gears[i][j+1] = gears[i][j];
                    }

                    gears[i][0] = temp;
                }
                //반시계방향 회전일 경우
                else {
                    temp = gears[i][0];

                    //기어 이동
                    for (int j=0; j<=6; j++) {
                        gears[i][j] = gears[i][j+1];
                    }

                    gears[i][7] = temp;
                }
            }
        }

        int answer = 0;
        if (gears[0][0] == 1) {
            answer += 1;
        }
        if (gears[1][0] == 1) {
            answer += 2;
        }
        if (gears[2][0] == 1) {
            answer += 4;
        }
        if (gears[3][0] == 1) {
            answer += 8;
        }
        System.out.println(answer);
    }
}
