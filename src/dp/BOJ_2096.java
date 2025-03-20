package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 지도가 주어진다.
 * 맨 위 랜덤한 숫자부터 시작하여 아래로 내려나간다.
 * 이 때 아래에서 선택할 수 있는 수는 양 옆이나 바로 아래이다.
 * 최대값과 최솟값을 구한다
 *
 * 풀이 dp
 * 0. 배열을 하나 복사해놓는다
 * 1. 아래에서부터 더해나간다
 * 2. 최솟값과 최댓값을 각각 더해나간다.
 * 3. 맨 윗줄의 최댓값과 최솟값을 각각 구하여 출력한다.
 */
public class BOJ_2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][3];
        int[][] dpMin = new int[N][3];
        int[][] dpMax = new int[N][3];

        //지도 셋팅
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp 배열 셋팅
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dpMin[i][j] = Integer.MAX_VALUE;
                dpMax[i][j] = Integer.MIN_VALUE;
            }
        }

        //dp 배열 맨 밑 셋팅
        for (int i = 0; i < 3; i++) {
            dpMin[N-1][i] = map[N-1][i];
            dpMax[N-1][i] = map[N-1][i];
        }

        //점화식
        //배열 행만큼 반복
        for (int i = map.length-2; i >=0; i--) {
            //행의 열만큼 반복
            for (int j = 0; j < 3; j++) {
                int current = map[i][j];

                for (int next : nextValue(j)) {
                    if (next<0 || next>=3) {
                        continue;
                    }

                    dpMin[i][j] = Math.min(dpMin[i+1][next]+current, dpMin[i][j]);
                    dpMax[i][j] = Math.max(dpMax[i+1][next]+current, dpMax[i][j]);
                }
            }
        }

        System.out.println(Arrays.stream(dpMax[0]).max().getAsInt() + " " + Arrays.stream(dpMin[0]).min().getAsInt());
    }

    public static List<Integer> nextValue(int input) {
        return List.of(input-1, input, input+1);
    }
}
