package stack;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_9935 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        String bomb = sc.next();

        Stack<Character> stack = new Stack<>();

        //문자열 순회
        for (int i = 0; i < input.length(); i++) {
            //스택 삽입
            stack.push(input.charAt(i));

            //스택 사이즈가 폭탄 문자열의 길이와 크거나 같다면, 폭탄 존재 여부 확인
            if (stack.size() >= bomb.length()) {

                boolean isBomb = true;

                for (int j = 0; j < bomb.length(); j++) {
                    if (bomb.charAt(j) != stack.get(stack.size() - bomb.length() + j)) {
                        isBomb = false;
                        break;
                    }
                }

                //폭탄이 존재한다면
                if (isBomb) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder st = new StringBuilder();

            for (Character c : stack) {
                st.append(c);
            }

            System.out.println(st);
        }
    }
}

