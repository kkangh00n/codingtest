package graph.union_find;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_4195 {

    static int size = 100001;

    static Map<String, Integer> map;
    static int[] unf;
    static int[] friendCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuilder answer = new StringBuilder();
        while (T-- > 0) {
            int F = sc.nextInt();

            //친구 : 해당 친구의 id
            map = new HashMap<>();
            //해당 친구의 id : 해당 친구의 부모 친구 id
            unf = new int[size * 2];
            //해당 친구의 id : 해당 친구의 친구 수
            friendCount = new int[size * 2];

            for (int i = 0; i < size * 2; i++) {
                unf[i] = i;
                friendCount[i] = 1;
            }

            int idx = 1;
            for (int i = 0; i < F; i++) {
                String friend1 = sc.next();
                if (!map.containsKey(friend1)) {
                    map.put(friend1, idx++);
                }

                String friend2 = sc.next();
                if (!map.containsKey(friend2)) {
                    map.put(friend2, idx++);
                }

                Integer friend1Index = map.get(friend1);
                Integer friend2Index = map.get(friend2);

                answer.append(union(friend1Index, friend2Index)).append("\n");
            }
        }
        System.out.println(answer);
    }

    public static int union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            //union 작업
            //fb:부모
            unf[fa] = fb;

            //친구 네트워크 수 갱신
            //부모(fb)의 친구수를 증가
            friendCount[fb] += friendCount[fa];
        }

        return friendCount[fb];
    }

    public static int find(int i) {
        if (i == unf[i]) {
            return i;
        }

        return find(unf[i]);
    }
}
