package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * input : 암호 (숫자), 5000자리 이하
 * output : 암호 해석 가짓 수
 *
 * 풀이
 * 1. dp
 * 2. dp[i] : i번쨰 자리까지 가능한 암호 해석 가짓 수
 * 3. 점화식 dp[i] =
 *      1) + dp[i-1]
 *      2) + dp[i-2] (연속된 자릿수 2개가 10<=value<=26 일 경우)
 */
public class BOJ_2011 {

    public static long mod = 1000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNumber = br.readLine().split("");
        List<Integer> arr = Arrays.stream(inputNumber).map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());

        long[] dp = new long[arr.size()+1];
        dp[0] = 1;

        for (int i = 1; i <= arr.size(); i++) {
            //현재 숫자
            Integer current = arr.get(i - 1);

            //현재 숫자가 0이 아니라면, 이전 경우의 수
            if (current>=1 && current <=9) {
                dp[i] += dp[i-1];
                dp[i] %= mod;
            }

            //첫 번째 자릿수일 경우 continue
            if (i==1) {
                continue;
            }

            //이전 숫자
            Integer previous = arr.get(i - 2);

            if (previous == 0) {
                continue;
            }

            //이전 숫자가 0이 아니라면
            int value = previous*10 + current;

            //이전 숫자와 합쳐서 나온 두 숫자가 복호화 가능하다면
            if (value >=10 && value <=26) {
                dp[i] += dp[i-2];
                dp[i] %= mod;
            }
        }

        System.out.println(dp[arr.size()]);
    }
}
