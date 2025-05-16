package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 회문 -> 거꾸로 뒤집었을 때, 같은 문자
 * 유사 회문 -> 하나의 문자만 삭제했을 때, 회문으로 만들수 있는 문자열
 *
 * 문자열이 들어오면 회문인지, 유사회문인지, 아무것도 아닌지 판단
 *
 * 투 포인터 이용
 */
public class BOJ_17609 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-->0) {
            //문자열
            char[] chars = br.readLine().toCharArray();

            int left = 0;
            int right = chars.length-1;

            int result = 0;

            //투 포인터로 팰린드롬 확인
            while (left < right) {

                //두 문자가 같지 않다면?
                if (chars[left] != chars[right]) {
                    //유사회문 확인
                    if (isPalindrome(chars, left+1, right) || isPalindrome(chars, left, right-1)) {
                        result = 1;
                    }
                    //유사회문도 아님
                    else {
                        result = 2;
                    }
                    break;
                }
                //두 문자가 같다면
                else {
                    left++;
                    right--;
                }
            }

            System.out.println(result);
        }
    }

    public static boolean isPalindrome(char[] chars, int left, int right) {
        boolean result = true;

        while(left < right) {
            if (chars[left] != chars[right]) {
                result = false;
                break;
            }
            left++;
            right--;
        }

        return result;
    }
}
