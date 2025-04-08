package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * CCTV는 벽을 통과하여 볼 수 없다.
 * CCTV는 CCTV를 통과하여 볼 수 있다.
 * 각 종류의 CCTV를 알맞게 회전하여 사각지대를 없애자.
 *
 * 입력
 * 지도의 크기 N, M
 * 지도 현황
 *
 * 출력
 * 사각 지대의 최소 크기
 *
 * 풀이 -> 완전 탐색 : CCTV의 회전 상태를 모두 탐색하여 최소 사각 지대 확인
 * 1. 지도를 입력받는다. (이 때, 벽은 6->-1로 변환)
 * 2. implementation.CCTV 좌표를 입력받아 리스트에 저장한다.
 * 3. CCTV를 회전하며 탐색한다. (이 때, 회전은 90도)
 * - 이 때, CCTV가 확인할 수 있는 부분은 해당 유형을 + 한다.
 * 4. 탐색을 마치면, 탐색 부분을 롤백한다.
 * 5. 마지막 CCTV라면 최소 사각지대를 확인한다.
 */
public class BOJ_15683 {

    //움직이는 방향
    static int[][] moved = new int[][]{{0, 1},{1, 0},{0, -1},{-1, 0}};

    //implementation.CCTV 카테고리 별 확인 방향(moved)
    static int[][] cctvDirection = new int[][] {
            {},
            {0},
            {0, 2},
            {0, 3},
            {0, 2, 3},
            {0, 1, 2, 3}
    };

    static int[][] map;
    static List<CCTV> cctvs;

    static int N, M;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        cctvs = new ArrayList<>();

        //지도 셋팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                int input = Integer.parseInt(st.nextToken());
                //벽을 음수로 변환
                if (input == 6) {
                    input = -1;
                }

                map[i][j] = input;

                if (input >=1 && input<=5) {
                    cctvs.add(new CCTV(i, j, input));
                }
            }
        }

        //implementation.CCTV 순서, 회전 수
        for(int i=0; i<4; i++) {
            DFS(0, i);
        }

        System.out.println(answer);
    }

    public static void DFS(int cctvIndex, int turn) {

        //모든 CCTV를 모두 탐색했다면, 사각 지대(0의 갯수) 구한다.
        if (cctvIndex == cctvs.size()) {

            int result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        result++;
                    }
                }
            }

            answer = Math.min(result, answer);
            return;
        }


        //index번째 cctv
        CCTV cctv = cctvs.get(cctvIndex);
        //cctv 유형
        int cctvCategory = cctv.category;
        //해당 cctv가 바라보고 있는 방향
        int[] direction = cctvDirection[cctvCategory];

        //해당 유형의 cctv가 바라보고 있는 방향을 체크한다.
        for (int i=0; i<direction.length; i++) {
            //회전 후 방향
            int d = (direction[i] + turn) % 4;
            int[] m = moved[d];

            int tempx = cctv.x;
            int tempy = cctv.y;
            //지도 바깥으로 벗어나기 전 까지 이동
            while (true) {

                //해당 방향으로 이동
                tempx += m[0];
                tempy += m[1];

                //지도 바깥이라면, break
                if (tempx < 0 || tempx >= N || tempy < 0 || tempy >= M) {
                    break;
                }

                //벽이라면, break
                if (map[tempx][tempy] == -1) {
                    break;
                }

                map[tempx][tempy] += cctv.category;
            }
        }

        //다음 cctv 탐색
        for(int j=0; j<4; j++) {
            DFS(cctvIndex + 1, j);
        }

        //해당 유형의 cctv가 바라보고 있는 방향을 롤백
        for (int i=0; i<direction.length; i++) {
            //회전 후 방향
            int d = (direction[i] + turn) % 4;
            int[] m = moved[d];

            int tempx = cctv.x;
            int tempy = cctv.y;
            //지도 바깥으로 벗어나기 전 까지 이동
            while (true) {

                //해당 방향으로 이동
                tempx += m[0];
                tempy += m[1];

                //지도 바깥이라면, break
                if (tempx < 0 || tempx >= N || tempy < 0 || tempy >= M) {
                    break;
                }

                //벽이라면, break
                if (map[tempx][tempy] == -1) {
                    break;
                }

                map[tempx][tempy] -= cctv.category;
            }
        }
    }
}

class CCTV {
    int x;
    int y;
    int category;

    CCTV(int x, int y, int category) {
        this.x = x;
        this.y = y;
        this.category = category;
    }
}
