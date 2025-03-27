package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 땅의 높이를 일정하게 한다.
 * 가장 위의 블록을 제거하여 인벤토리에 넣는 작업 -> 2초
 * 인벤토리에서 블록 하나를 꺼내어서 블록 위에 놓는 작업 -> 1초
 *
 * 입력
 * 지도의 크기, 현재 갖고있는 블록 수
 * 지도
 *
 * 출력
 * 작업 최소 시간, 땅의 높이 (시간이 같을 경우 땅의 높이가 높은 것 출력)
 *
 * 풀이 - 브루트 포스
 * 1. 땅의 최대 높이와 최소 높이를 구한다.
 * 2. 최소 높이부터 최대 높이까지 평탄하게 할 때, 시간을 구한다.
 * 3. 만약 해당 높이까지 평탄하게 하였을 때, 블록이 모자라다면 넘어간다.
 * 4. 만약 해당 높이까지 평탄하게 하였을 때 시간이 최소 시간보다 작거나 같다면, 최소 시간과 높이를 갱신한다.
 */
public class BOJ_18111 {

    static int minTime = Integer.MAX_VALUE;
    static int answerHeight = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        //지도 저장 및 땅의 최대 최소 높이 구함
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int cpBlock;
        int time;
        //땅의 최소 높이부터 최대 높이까지 반복
        for (int h = minHeight; h <= maxHeight; h++) {
            cpBlock = B;
            time = 0;

            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    int currentHeight = map[i][j];

                    //현재 높이와 기준 높이의 차이
                    int difference = Math.abs(currentHeight-h);

                    //현재 좌표의 높이가 h보다 높다면, 블록을 제거하여 인벤토리에 넣음
                    if (h<currentHeight) {
                        cpBlock += difference;
                        time += difference*2;
                    }
                    //현재 좌표의 높이가 h보다 낮다면
                    else if(h>currentHeight) {
                        cpBlock -= difference;
                        time += difference;
                    }
                }
            }

            //지도를 모두 평탄하게 하였을 때, 남은 블록의 갯수가 0 이하라면 continue
            if (cpBlock<0) {
                continue;
            }

            //지도를 모두 평탄하게 하였을 때, 시간이 최소 시간보다 작거나 같다면 정답을 갱신한다.
            if (time <= minTime) {
                minTime = time;
                answerHeight = h;
            }
        }

        System.out.println(minTime + " " + answerHeight);
    }
}

