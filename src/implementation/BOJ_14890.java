package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * N : 지도 범위, L : 경사로 길이
 *
 * 출력
 * 지나갈 수 있는 길의 수
 *
 * 풀이
 * 1. 지도를 입력받는다
 * 2. 가로부터 행의 길이 있는지 확인한다
 * - 해당 행의 경사로 여부 저장 배열을 생성한다.
 * - i와 i+1 높이를 비교한다.
 * - 만약 높이 차가 1 초과라면 -> 길 성립 X
 * - 만약 높이 차가 1일 경우
 *      - 다음 칸이 낮다면 -> i+2부터 i+L+1까지의 높이가 같은지 확인 -> 같다면 경사로 놓음 -> 만약 높이가 다르다면, 길 성립 X
 *      - 다음 칸이 높다면 -> i부터 i-L+1까지의 높이가 같은지 확인 -> 같다면 경사로 놓음 -> 만약 높이가 다르다면, 길 성립 X
 * - 길이 성립된다면, answer값 증가
 * - 길이 성립되지 않는다면 해당 행의 놓았던 경사로를 이전으로 롤백
 */
public class BOJ_14890 {

    static int N, L, answer = 0;

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        //1. 지도 입력, 경사로 존재 여부 배열 선언
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //0부터 N-1까지의 행과 열에 대한 길 찾기
        for(int i=0; i<N; i++) {
            if (checkRow(i)) answer++;
            if (checkCol(i)) answer++;
        }

        System.out.println(answer);
    }

    public static boolean checkRow(int row) {
        boolean[] searched = new boolean[N];

        for (int i=0; i<N-1; i++) {
            //만약 높이 차가 1 초과라면 -> 길 성립 X
            if (Math.abs(map[row][i] - map[row][i+1]) > 1) {
                return false;
            }

            //다음 칸이 낮다면
            if (map[row][i] - map[row][i+1]==1) {

                //i+2부터 i+L+1까지의 높이가 같은지 확인
                for(int j=i+1; j<=i+L; j++) {
                    //경사로를 놓을 수 없다면, 길 성립 X
                    if (j>=N || searched[j]) {
                        return false;
                    }

                    //만약 높이가 다르다면, 길 성립 X
                    if (map[row][i+1]!=map[row][j]) {
                        return false;
                    }

                    //경사로 놓음
                    searched[j] = true;
                }
            }
            //다음 칸이 높다면
            else if(map[row][i+1] - map[row][i] == 1) {

                //i부터 i-L+1까지의 높이가 같은지 확인
                for (int j=i; j>=i-L+1; j--) {
                    //경사로를 놓을 수 없다면, 길 성립 X
                    if (j<0 || searched[j]) {
                        return false;
                    }

                    //만약 높이가 다르다면, 길 성립 X
                    if (map[row][i]!=map[row][j]) {
                        return false;
                    }

                    searched[j] = true;
                }
            }
        }

        return true;
    }

    public static boolean checkCol(int col) {
        boolean[] searched = new boolean[N];

        for (int i=0; i<N-1; i++) {
            //만약 높이 차가 1 초과라면 -> 길 성립 X
            if (Math.abs(map[i][col] - map[i+1][col]) > 1) {
                return false;
            }

            //다음 칸이 낮다면
            if (map[i][col] - map[i+1][col]==1) {

                //i+2부터 i+L+1까지의 높이가 같은지 확인
                for(int j=i+1; j<=i+L; j++) {
                    //경사로를 놓을 수 없다면, 길 성립 X
                    if (j>=N || searched[j]) {
                        return false;
                    }

                    //만약 높이가 다르다면, 길 성립 X
                    if (map[i+1][col]!=map[j][col]) {
                        return false;
                    }

                    //경사로 놓음
                    searched[j] = true;
                }
            }
            //다음 칸이 낮다면
            else if(map[i+1][col] - map[i][col] == 1) {

                //i부터 i-L+1까지의 높이가 같은지 확인
                for (int j=i; j>=i-L+1; j--) {
                    //경사로를 놓을 수 없다면, 길 성립 X
                    if (j<0 || searched[j]) {
                        return false;
                    }

                    //만약 높이가 다르다면, 길 성립 X
                    if (map[i][col]!=map[j][col]) {
                        return false;
                    }

                    searched[j] = true;
                }
            }
        }

        return true;
    }
}
