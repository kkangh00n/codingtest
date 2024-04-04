package greedy;

import java.util.Scanner;

public class BOJ_1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.next();

        //- 기준으로 식 분리
        String[] splitMinus = expression.split("-");

        int answer = 0;
        for (int i=0; i< splitMinus.length; i++) {
            //+ 기준으로 식 분리
            String[] splitPlus = splitMinus[i].split("[+]");

            int temp=0;
            for (String s1 : splitPlus) {
                temp+=Integer.parseInt(s1);
            }

            if(i==0) {
                answer+=temp;
            }
            else answer-=temp;
        }

        System.out.println(answer);
    }
}
