package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 그리디
 * 끝나는 시간을 기준으로 정렬
 * 시작 시간이 이전에 끝난 시간 이후인 것을 찾는다.
 *
 * 예외 케이스 : 끝나는 시간이 같다면 시작 시간을 기준으로 오름차순한다.
 */
public class BOJ_1931 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(start, end));
        }

        Collections.sort(lectures);

        int previousEnd = -1;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            Lecture currentLecture = lectures.get(i);

            if (currentLecture.start >= previousEnd) {
                previousEnd = currentLecture.end;
                answer++;
            }
        }

        System.out.println(answer);
    }
}

class Lecture implements Comparable<Lecture>{
    int start;
    int end;

    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o) {
        if (this.end == o.end) {
            return this.start - o.start;
        }
        return this.end - o.end;
    }
}
