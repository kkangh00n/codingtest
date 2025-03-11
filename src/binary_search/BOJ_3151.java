package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {

    static long answer = 0;

    static int N;
    static int[] student;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        answer = 0;

        student = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            student[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(student);

        //수 2개를 선택
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int lower = BinarySearch(student[i] + student[j], j + 1, false);
                int upper = BinarySearch(student[i] + student[j], j + 1, true);

                answer += upper - lower;
            }
        }

        System.out.println(answer);
    }

    public static int BinarySearch(int target, int left, boolean isUpper) {
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            int current = student[mid] + target;

            if (isUpper) {
                //current가 0이 되는 시점에 left는 current가 0이 되는 값을 초과한다!
                if (current > 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                //current가 0이 되는 시점에 left는 current가 0이 되는 값을 초과한다!
                if (current >= 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }

        return left;
    }
}
