package binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_1477 {

    static int N;
    static int M;
    static int L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //현재 지어진 휴게소 갯수
        N = sc.nextInt();
        //더 지으려고 하는 휴게소 갯수
        M = sc.nextInt();
        //고속도로 길이
        L = sc.nextInt();
        //휴게소 배열
        List<Integer> list = new ArrayList<>();

        list.add(0);
        list.add(L);

        for (int i = 0; i < N; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);

        System.out.println(BinarySearch(list));
    }

    public static int BinarySearch(List<Integer> list) {

        //휴게소 별 최소 거리
        int start = 1;
        //휴게소 별 최대 거리
        int end = L;

        while(start <= end) {
            int mid = (start + end) /2;

            int count = 0;
            for (int i = 0; i < list.size()-1; i++) {
                int distance = list.get(i + 1) - list.get(i);
                count += distance / mid;

                if (distance%mid == 0) {
                    count--;
                }
            }

            if (count > M) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return start;
    }
}
