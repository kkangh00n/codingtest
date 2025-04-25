package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1차 점수와 2차 점수가 다른 참가자에 비해 모두 낮은 참가자는 탈락
 */
public class BOJ_1946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while(T-->0) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            List<Result> results = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int one = Integer.parseInt(st.nextToken());
                int two = Integer.parseInt(st.nextToken());

                results.add(new Result(one, two));
            }

            //1차 순위 오름차순 정렬
            Collections.sort(results);

            int answer = 0;

            int minRank = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                Result result = results.get(i);

                //선발 조건 만족여부 확인
                //지원자의 2차 순위가 현재 최소 순위보다 작다면, 선발될 수 없음
                if (minRank>result.two) {
                    answer++;
                    minRank = result.two;
                }
            }

            System.out.println(answer);
        }
    }
}

class Result implements Comparable<Result>{
    int one;
    int two;

    Result(int one, int two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public int compareTo(Result o) {
        return this.one - o.one;
    }
}
