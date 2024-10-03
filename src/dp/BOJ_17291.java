package dp;

import java.util.Scanner;

public class BOJ_17291 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        //년도 별 개체 수
        int[] arr = new int[21];

        //1년 2월 1마리
        arr[1] = 1;
        //2년 1월 첫 번째 분열
        arr[2] = 2;
        //3년 1월 두 번째 분열
        arr[3] = 4;
        //4년 1월 세 번째 분열 및 사망
        arr[4] = 7;

        for (int i = 5; i <= N; i++) {
            //분열
            //짝수년도 -> 3년전 태어난 개체, 4년전 태어난 개체 사망
            arr[i] = arr[i-1] * 2 - (i % 2 == 0 ? (arr[i - 4] + arr[i - 5]) : 0);
        }

        System.out.println(arr[N]);
    }
}
