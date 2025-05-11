package sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 투 포인터, 슬라이딩 윈도우
 *
 * 입력
 * 참가자 수 N, 스터디 시간 T
 * 다음 줄 부터 참가하고자 하는 참가자들의 시간 정보가 N개 주어진다.
 *
 * 출력
 * 시간 만족도 최대인 시간을 구한다. 여러 개라면 가장 이른 시간을 출력한다.
 */
public class BOJ_23295 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //참가자 수
        int N = Integer.parseInt(st.nextToken());
        //스터디 시간
        int T = Integer.parseInt(st.nextToken());

        //각 시간 별 참여 가능한 인원 수 저장
        int[] times = new int[100001];

        //참여자 정보 저장
        for (int i = 0; i < N; i++) {
            //참여 가능한 시간의 수
            int K = Integer.parseInt(br.readLine());

            //각 참여 가능한 시간 저장
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                for (int k = start; k < end; k++) {
                    times[k]+=1;
                }
            }
        }

        int left = 0;
        int right = T;
        //시간 만족도
        int time = 0;

        //시간 만족도 최대인 시작 시간
        int maxIndex = Integer.MIN_VALUE;
        //최대 시간 만족도
        int maxTime = Integer.MIN_VALUE;

        //초기 시간 만족도 셋팅
        for (int i = 0; i < T; i++) {
            time+=times[i];
        }

        //슬라이딩 윈도우
        while(right < 100001) {
            //시간 만족도가 최대라면, 갱신
            if (time > maxTime) {
                maxIndex = left;
                maxTime = time;
            }

            time-=times[left++];
            time+=times[right++];
        }

        int maxIndexEndTime = maxIndex + T;

        System.out.println(maxIndex + " " + maxIndexEndTime);
    }
}
