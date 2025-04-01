package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * 바둑판 현황이 입력됨
 *
 * 출력
 * 검은색이 이길 경우 1, 흰색이 이길 경우 2, 승부가 결정되지 않았을 경우 0
 * 바둑알이 시작되는 부분의 가로줄 및 세로줄 번호
 *
 * 풀이
 *
 */
public class BOJ_2615 {

    static int[][] moved = new int[][]{{1,0},{0,1},{1,1},{-1,1}};

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[20][20];

        //지도 설정
        for (int i = 1; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 20; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //지도를 돌면서 확인
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                //바둑알이 놓여져 있다면,
                if (map[i][j] != 0) {
                    //우측, 아랫, 우하 대각선 방향 확인
                    for (int[] m: moved) {

                        //오목이 완성되었는지 확인한다.
                        boolean check = isCheckMate(i, j, m);

                        //확인되었다면 출력 후 종료
                        if (check) {
                            System.out.println(map[i][j]);
                            System.out.println(i + " " + j);
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static boolean isCheckMate(int x, int y, int[] m) {
        int startValue = map[x][y];

        //반대편 방향으로 같은 색깔의 바둑돌이 있을 경우, 오목 성립 X
        if (x-m[0]>=1 && x-m[0] < 20 && y-m[1] >=1 && y-m[1] < 20) {
            if (map[x-m[0]][y-m[1]] == startValue) {
                return false;
            }
        }

        //오목인지 확인
        for(int i=1; i<5; i++) {
            x = x + m[0];
            y = y + m[1];

            //현재 진행 방향이 지도 바깥이라면,
            if (x<1 || x >= 20 || y <1 || y >= 20) {
                return false;
            }

            //색깔이 다르거나, 돌이 없는 경우
            if (map[x][y] != startValue) {
                return false;
            }
        }

        //육목일 경우, 오목 X
        if (x+m[0]>=1 && x+m[0] < 20 && y+m[1] >=1 && y+m[1] < 20) {
            if (map[x + m[0]][y + m[1]] == startValue) {
                return false;
            }
        }

        return true;
    }
}
