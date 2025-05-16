package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 수열의 수를 묶거나 묶지 않아서 모든 값을 더한 후 최대가 되게 한다.
 *
 * 다음과 같이 분리한다.
 * - 음수부터 0
 * - 1부터 양수
 *
 * 입력
 * N 수열의 크기
 * 수열 입력
 *
 * 출력
 * 결과가 최대가 되도록
 *
 * 반례
 * 양수에서 1이 존재할 경우, 1은 곱해주기보다 더해주는 것이 이득!
 */
public class BOJ_1744 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        //배열 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> minusNum = new ArrayList<>();
        List<Integer> plusNum = new ArrayList<>();

        //양수 음수 구분
        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) {
                plusNum.add(arr[i]);
            }
            else {
                minusNum.add(arr[i]);
            }
        }

        //음수 오름차순 정렬
        Collections.sort(minusNum);
        //양수 내림차순 정렬
        Collections.sort(plusNum, Collections.reverseOrder());

        int result = 0;

        if (minusNum.size()%2==1) {
            result += minusNum.get(minusNum.size()-1);
            minusNum.remove(minusNum.size()-1);
        }

        if (plusNum.size()%2==1) {
            result += plusNum.get(plusNum.size()-1);
            plusNum.remove(plusNum.size()-1);
        }

        int temp = 0;
        for (int i = 0; i < plusNum.size(); i++) {
            Integer current = plusNum.get(i);
            if (i % 2 == 0) {
                temp = current;
            }
            else {
                //만약 해당 값이 1이라면, 그냥 더한다!!!!!
                if (current == 1) {
                    result += (temp + current);
                } else {
                    result += temp * current;
                }
            }
        }

        for (int i = 0; i < minusNum.size(); i++) {
            if (i%2==0) {
                temp = minusNum.get(i);
            }
            else {
                result += temp*minusNum.get(i);
            }
        }

        System.out.println(result);
    }
}
