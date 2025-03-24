package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 현재 칸을 청소 후,
 * 1. 현재 칸의 주변 4칸 중 청소되지 않은 칸이 없는 경우
 * - 바로 한칸 후진 후 주변 4칸 확인
 * - 후진할 수 없다면 작동 중지
 *
 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
 * - 반시계 방향으로 90도 회전
 * - 바라보는 방향이 청소되지 않은 경우 한 칸 전진
 *
 * 풀이
 * 사방이 모두 청소가 되었다면, 바로 한 칸 후진
 * 방향 -> 0 북쪽, 1 동쪽, 2 남쪽, 3 서쪽
 *
 * 로직
 * 1. 청소를 하게되면, cleaned 배열에 체크
 * 2. 주변에 청소할 곳이 있다면, 그곳으로 이동 후
 * 3. 주변에 청소할 곳이 없다면, 후진 -> 백 트래킹 인줄 알았지만 DFS..!
 */
public class BOJ_14503 {

    static int[][] directions = new int[][]{{-1, 0},{0, 1},{1, 0},{0,-1}};
    static int[][] map;
    static boolean[][] cleaned;

    static int N,M,answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cleaned = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(startX, startY, direction);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = cleaned[i][j] ? answer+1:answer;
            }
        }

        System.out.println(answer);

    }

    public static void clean(int x, int y, int d) {
        //해당 칸 청소
        cleaned[x][y] = true;

        //반시계 90도 방향으로 확인하며 해당 칸이 청소될 수 있다면 이동
        for(int i=1; i<=4; i++) {
            d = (d + 3)%4;
            int[] nextMoved = directions[d];

            int nextX = x + nextMoved[0];
            int nextY = y + nextMoved[1];

            //다음 코스가 지도 범위 밖이라면 continue
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }

            //다음 코스가 벽이라면 continue
            if (map[nextX][nextY] == 1) {
                continue;
            }

            //다음 코스가 이미 청소를 했다면 continue
            if (cleaned[nextX][nextY]) {
                continue;
            }

            clean(nextX, nextY, d);
            return;
        }

        //움직일 수 없는 경우..! 방향을 유지한 채 뒤로 후진
        int backDirection = (d+2)%4;
        int backX = x + directions[backDirection][0];
        int backY = y + directions[backDirection][1];

        //만약 해당 위치가 지도 바깥이라면
        if (backX < 0 || backX >= N || backY < 0 || backY >= M) {
            return;
        }

        //만약 해당 위치가 벽이라면
        if (map[backX][backY] == 1) {
            return;
        }
        clean(backX, backY, d);
    }
}
