package binary_search;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2110 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //집 갯수
        int N = sc.nextInt();
        int[] house = new int[N];

        //공유기 갯수
        int C = sc.nextInt();

        //집 좌표 입력
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            house[i] = x;
        }

        //집 좌표 순으로 정렬
        Arrays.sort(house);

        //최소 거리 -> 1
        //최대 거리 -> 맨 오른쪽 좌표 - 맨 왼쪽 좌표
        int start = 1;
        int end = house[N-1] - house[0];

        int max = Integer.MIN_VALUE;

        while (start <= end) {
            //거리 설정
            int midDistance = (start + end) / 2;

            //거리로 설정 시
            //1. 현재 사용한 공유기가 제공되는 공유기 수와 같거나 많다면
            // ->
            if (count(house, midDistance) >= C) {
                max = Math.max(midDistance, max);
                start = midDistance+1;
            }
            else {
                end = midDistance-1;
            }
        }

        System.out.println(max);
    }

    public static int count(int[] house, int standardDistance) {
        int index = 0;
        int result = 1;

        for (int i = 1; i < house.length; i++) {
            int currentDistance = house[i] - house[index];

            if (currentDistance>=standardDistance) {
                result++;
                index = i;
            }

        }

        return result;
    }
}
