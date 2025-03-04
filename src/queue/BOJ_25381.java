package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_25381 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();

        Queue<Character> queue = new LinkedList<>();

        int countB = 0;

        for (int i = 0; i < S.length(); i++) {

            char c = S.charAt(i);
            if (c == 'B') {
                countB++;
            }
            queue.add(c);
        }

        //--------AB를 만드는 경우-------//
        int stack = 0;
        int answerA = 0;

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Character c = queue.poll();

            if (c == 'A') {
                stack++;
            }
            if (c == 'B') {
                if (stack > 0) {
                    stack--;
                    answerA++;
                }
            }
            queue.add(c);
        }

        //--------BC를 만드는 경우-------//
        stack = 0;
        int answerB = 0;

        for (int i = 0; i < size; i++) {
            Character c = queue.poll();

            if (c == 'B') {
                stack++;
            }
            if (c == 'C') {
                if (stack > 0) {
                    stack--;
                    answerB++;
                }
            }
            queue.add(c);
        }

        System.out.println(Math.min(answerA + answerB, countB));
    }
}