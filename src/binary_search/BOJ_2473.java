package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2467번 (두 용액) 심화 문제
 * 투 포인터 응용
 *
 * 1. left와 right를 지정한다.
 * 2. 그 안에 값을 하나 지정한다. point
 * 3. arr의 세 값의 합의 절댓값이 0보다 크다면 right를 줄여나간다
 * 4. arr의 세 값의 합의 절댓값이 0보다 작다면 left를 줄여나간다.
 * 주의
 * 만약 left 혹은 right가 지정한 값을 만나게 되면 그 반복문은 종료한다.
 * 5. point값을 증가시키며 1번으로 돌아간다
 */
public class BOJ_2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answerLeft = 0;
        int answerRight = N-1;
        int answerMid = (answerLeft+answerRight)/2;

        long minAbs = Long.MAX_VALUE;

        boolean find = false;

        //1~N-2 를 point한다.
        for (int i = 1; i < N - 1; i++) {

            int point = i;

            int left = 0;
            int right = N-1;

            //left와 right를 줄여나간다.
            while(left<point && point<right) {
                long current = (long) arr[left] + arr[point] + arr[right];

                //세 값의 합의 절댓값이 최소라면 갱신한다.
                if (Math.abs(current) < minAbs) {
                    answerLeft = left;
                    answerRight = right;
                    answerMid = point;
                    minAbs = Math.abs(current);
                }

                //세 값의 합이 0이라면 반복문을 멈춘다.
                if (current == 0) {
                    find = true;
                    break;
                }

                //0보다 크다면 오른쪽을 한칸 줄인다.
                if (current > 0) {
                    right--;
                }
                else {
                    left++;
                }
            }


            if (find) {
                break;
            }
        }

        System.out.println(arr[answerLeft] + " " + arr[answerMid] + " " + arr[answerRight]);
    }
}
