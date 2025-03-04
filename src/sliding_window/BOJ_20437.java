package sliding_window;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_20437 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            //문자열
            char[] w = sc.next().toCharArray();
            //최소 포함 갯수
            int K = sc.nextInt();

            //각 문자열의 인덱스를 담을 리스트
            List<Integer>[] arr = new ArrayList[26];
            for (int j = 0; j < 26; j++) {
                arr[j] = new ArrayList<>();
            }

            //각 문자열을 돌면서 리스트에 담는다.
            for (int j = 0; j < w.length; j++) {
                //알파벳 번호
                int alphaNum = w[j] - 'a';
                //알파벳 인덱스 저장 리스트에 인덱스 저장
                List<Integer> alphaIndexes = arr[alphaNum];
                alphaIndexes.add(j);

                //알파벳이 아직 K개만큼 존재하지 않으면, continue
                if (alphaIndexes.size()<K) {
                    continue;
                }

                //알파벳이 K개만큼 존재한다면
                Integer previousIndex = alphaIndexes.get(alphaIndexes.size() - K);
                int result = j - previousIndex+1;

                max = Math.max(max, result);
                min = Math.min(min, result);
            }

            if (max == Integer.MIN_VALUE || min == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}
