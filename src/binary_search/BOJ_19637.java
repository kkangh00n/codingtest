package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19637 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        //칭호 갯수
        int N = Integer.parseInt(st.nextToken());
        //캐릭터 전투력
        int M = Integer.parseInt(st.nextToken());

        //칭호 저장
        String[][] title = new String[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            title[i][0] = st.nextToken();
            title[i][1] = st.nextToken();
        }

        for (int i = 0; i < M; i++) {
            //전투력
            int score = Integer.parseInt(br.readLine());

            int start = 0;
            int end = N-1;
            int middle;
            int titleScore;

            while(start<=end) {

                middle = (start+end)/2;
                titleScore = Integer.parseInt(title[middle][1]);

                //현재 전투력이 기준 칭호점수보다 높다면
                if(score> titleScore) start = middle+1;
                //현재 전투력이 기준 칭호점수보다 낮다면
                else end = middle-1;
            }

            answer.append(title[start][0]+"\n");
        }
        System.out.println(answer);

    }
}
