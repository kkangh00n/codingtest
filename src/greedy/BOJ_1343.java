package greedy;

import java.util.Scanner;

public class BOJ_1343 {

    public static void main(String[] args) {

        StringBuilder answer = new StringBuilder();

        //보드판 입력
        Scanner sc = new Scanner(System.in);
        String board = sc.next();

        board = board.replaceAll("XXXX", "AAAA");
        board = board.replaceAll("XX", "BB");


        if(board.contains("X"))
            System.out.println("-1");
        else {
            System.out.println(board);
        }

    }
}
