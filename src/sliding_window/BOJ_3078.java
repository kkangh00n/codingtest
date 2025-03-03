package sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_3078 {

    public static void main(String[] args) {
        long friendsCount = 0;

        Scanner sc = new Scanner(System.in);

        //반 전체 학생 수
        int N = sc.nextInt();
        //친구가 되기 위한 범위
        int K = sc.nextInt();

        //친구 이름 저장
        int[] friends = new int[N];
        //범위 내 친구 이름 길이 별 수 저장
        Map<Integer, Integer> nameSizeMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            friends[i] = sc.next().length();
        }

        //초기 슬라이딩 윈도우 셋팅
        int start = 0;
        int end = K+1;

        for (int i = start; i < end; i++) {
            //친구 이름 길이 저장
            nameSizeMap.put(friends[i],
                    nameSizeMap.getOrDefault(friends[i], 0) + 1);
        }

        //투포인터 이동
        while (start < N-1) {
            //범위 내의 자신을 제외한 이름 길이가 같은 친구 수 저장
            friendsCount += nameSizeMap.get(friends[start]) - 1;

            //마지막 포인터 이동
            if (end < N) {
                nameSizeMap.put(friends[end],
                        nameSizeMap.getOrDefault(friends[end], 0) + 1);
                end++;
            }

            //첫 번째 포인터 이동
            nameSizeMap.put(friends[start],
                    nameSizeMap.get(friends[start]) - 1);
            start++;
        }

        System.out.println(friendsCount);
    }
}
