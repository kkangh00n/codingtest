package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 스택 자료구조 사용
 *
 * 1. 여는 괄호가 나올 경우
 *      - 괄호를 삽입한다.
 *      - 2배 혹은 3배 시킨다.
 * 2. 닫는 괄호가 나올 경우
 *      - 만약 바로 이전에 여는 괄호와 짝이라면 지금까지의 값을 더한다
 *      - 2배 혹은 3배 나눈다.
 *
 *      - 예외 상황
 *          - 스택에 여는 괄호가 없는 경우 예외
 *          - 스택에 여는 괄호가 짝이 다른 경우 예외
 *
 */
public class BOJ_2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        int result = 0;

        int value = 1;

        for (int i = 0; i < input.length; i++) {

            //여는 괄호일 경우 push
            if (input[i] == '(') {
                stack.push(input[i]);
                value*=2;
            }

            else if (input[i] == '[') {
                stack.push(input[i]);
                value*=3;
            }

            //닫는 괄호일 경우
            else if (input[i] == ')'){

                //스택이 비어있다면 -> 괄호 완성 X
                if (stack.isEmpty() || stack.pop() != '(') {
                    result = 0;
                    break;
                }
                if (input[i-1] == '(') {
                    result += value;
                }

                value/=2;
            }

            else if (input[i] == ']'){

                //스택이 비어있다면 -> 괄호 완성 X
                if (stack.isEmpty() || stack.pop() != '[') {
                    result = 0;
                    break;
                }
                if (input[i-1] == '[') {
                    result += value;
                }

                value/=3;
            }
        }

        if (!stack.isEmpty()) {
            result = 0;
        }

        System.out.println(result);
    }
}
