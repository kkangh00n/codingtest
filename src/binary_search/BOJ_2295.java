package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 0. R4-R3 = R1+R2 꼴로 변환
 * 1. 배열을 내림차순 정렬한다.
 * 2. 반복문을 통하여, 0~N-3까지 반복하여 R4-R3을 구한다.
 * 3. 반대쪽에서 투포인터를 이용하여 R4-R3을 만족하는 R1+R2를 찾는다.
 */
public class BOJ_2295 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Integer[] nums = new Integer[N];

        //수 입력
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums, Collections.reverseOrder());

        //R1+R2 셋팅
        List<Integer> sumList = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumList.add(nums[i] + nums[j]);
            }
        }

        Collections.sort(sumList);

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                //R4-R3
                int target = nums[i] - nums[j];

                //R1+R2 == R4-R3 존재한다면
                if (Collections.binarySearch(sumList, target) > -1) {
                    max = Math.max(nums[i], max);
                }
            }
        }

        System.out.println(max);
    }
}
